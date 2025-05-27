package org.aibles.framework.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.adapter.helper.AuthenticationHelper;
import org.aibles.adapter.repository.RoleRepository;
import org.aibles.entity.Role;
import org.aibles.enumiration.RoleEnum;
import org.aibles.framework.error_handle.AuthenticationErrorHandle;
import org.aibles.framework.error_handle.AuthorizationErrorHandle;
import org.aibles.framework.error_handle.SecurityException;
import org.aibles.framework.error_handle.UnauthorizedApiPathException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthorizationFilter extends OncePerRequestFilter {
    private final RoleRepository roleRepository;
    private final AuthenticationHelper authenticationHelper;
    private final AuthenticationErrorHandle authenticationErrorHandle;
    private final AuthorizationErrorHandle authorizationErrorHandle;
    private static final List<String> SIDECAR_SERVICE_API = List.of(
        "actuator",
        "swagger",
        "/v3/api-docs",
        "favicon"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String wildcardRequest = authenticationHelper.convertWildcardRequestUri(request.getRequestURI());
        if (isSidecarApiPath(wildcardRequest)) {
            filterChain.doFilter(request, response);
            return;
        }
        List<Role> apiAccessRoles = roleRepository.getAllRoleByApiEndpointAndMethod(wildcardRequest, request.getMethod());
        List<String> apiAccessRoleNames = new ArrayList<>(
            apiAccessRoles.stream().map(Role::getName).toList());
        if (apiAccessRoleNames.contains(RoleEnum.PERMIT_ALL.getValue())) {
            filterChain.doFilter(request, response);
        } else if (apiAccessRoleNames.contains(RoleEnum.AUTHENTICATED.getValue())) {
            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                UnauthorizedApiPathException unauthorizedApiPathException =
                    new UnauthorizedApiPathException(
                        SecurityException.AUTHENTICATION_EXCEPTION.getMsgCode());
                authenticationErrorHandle.commence(request, response, unauthorizedApiPathException);
                return;
            }
            filterChain.doFilter(request, response);
        } else {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            List<String> userRoles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
            apiAccessRoleNames.retainAll(userRoles);
            if (apiAccessRoleNames.isEmpty()) {
                AccessDeniedException accessDeniedException = new AccessDeniedException(
                    SecurityException.AUTHORIZATION_EXCEPTION.getMsgCode()
                );
                authorizationErrorHandle.handle(request, response, accessDeniedException);
                return;
            }
            filterChain.doFilter(request, response);
        }
    }

    private boolean isSidecarApiPath(String path) {
        return SIDECAR_SERVICE_API.stream().anyMatch(path::contains);
    }
}
