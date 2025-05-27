package org.aibles.framework.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.aibles.adapter.helper.AuthenticationHelper;
import org.aibles.adapter.repository.RoleRepository;
import org.aibles.entity.Role;
import org.aibles.enumiration.RoleEnum;
import org.aibles.framework.error_handle.AuthenticationErrorHandle;
import org.aibles.framework.error_handle.InvalidHeaderException;
import org.aibles.framework.error_handle.InvalidTokenException;
import org.aibles.framework.error_handle.SecurityException;
import org.aibles.framework.error_handle.UnauthorizedApiPathException;
import org.aibles.framework.security.AuthenticationFactory;
import org.aibles.framework.security.AuthenticationProvider;
import org.assertj.core.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {

    private final AuthenticationHelper authenticationHelper;
    private final AuthenticationErrorHandle authenticationErrorHandle;
    private final RoleRepository roleRepository;
    private final AuthenticationFactory authenticationFactory;
    private static final Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);
    private static final List<String> SIDECAR_SERVICE_API = List.of(
        "actuator",
        "swagger",
        "/v3/api-docs",
        "favicon"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authenticationHeader = request.getHeader("Authorization");
        String tokenType = request.getHeader("X-Token-Type");
        String wildcardRequest = authenticationHelper.convertWildcardRequestUri(request.getRequestURI());
        if (isSidecarApiPath(wildcardRequest)) {
            filterChain.doFilter(request, response);
            return;
        }
        log.info("(doAuthenticationFiler) uri {} method {}", wildcardRequest, request.getMethod());
        List<Role> userRoles = roleRepository.getAllRoleByApiEndpointAndMethod(wildcardRequest, request.getMethod());
        List<String> userRoleNames = userRoles.stream().map(Role::getName).toList();
        if (userRoleNames.isEmpty()) {
            UnauthorizedApiPathException unauthorizedApiPathException =
                new UnauthorizedApiPathException(SecurityException.INVALID_API_PATH.getMsgCode());
            authenticationErrorHandle.commence(request, response, unauthorizedApiPathException);
            return;
        }
        if (userRoleNames.contains(RoleEnum.PERMIT_ALL.getValue())) {
            filterChain.doFilter(request,response);
            return;
        }

        if (Strings.isNullOrEmpty(tokenType)) {
            InvalidHeaderException invalidHeaderException = new InvalidHeaderException(
                SecurityException.INVALID_TOKEN_TYPE_EXCEPTION.getMsgCode()
            );
            authenticationErrorHandle.commence(request, response, invalidHeaderException);
            return;
        }
        if (authenticationHeader == null || !authenticationHeader.startsWith("Bearer ")) {
            InvalidHeaderException invalidHeaderException = new InvalidHeaderException(
                SecurityException.INVALID_HEADER.getMsgCode()
            );
            authenticationErrorHandle.commence(request, response, invalidHeaderException);
            return;
        }
        String accessToken = authenticationHeader.substring(7);
        AuthenticationProvider authenticationProvider = authenticationFactory.getAuthenticationProvider(tokenType);
        if (!authenticationProvider.validateAccessToken(accessToken)) {
            InvalidTokenException invalidTokenException = new InvalidTokenException(
                SecurityException.INVALID_TOKEN.getMsgCode()
            );
            authenticationErrorHandle.commence(request, response, invalidTokenException);
            return;
        }
        authenticationProvider.addAuthentication(accessToken);
        filterChain.doFilter(request,response);
    }

    private boolean isSidecarApiPath(String path) {
        return SIDECAR_SERVICE_API.stream().anyMatch(path::contains);
    }
}
