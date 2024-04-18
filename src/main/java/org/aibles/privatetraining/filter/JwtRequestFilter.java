package org.aibles.privatetraining.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.privatetraining.service.UserProfileService;
import org.aibles.privatetraining.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

@Component
@Slf4j
@AllArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;
    private final UserProfileService userProfileService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        log.info("(doFilterInternal)");
        final String accessToken = request.getHeader("Authorization");
        if (Objects.isNull(accessToken)) {
            filterChain.doFilter(request, response);
            return;
        }

        if (!accessToken.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        var jwtToken = accessToken.substring(7);
        String userId;
        try {
            userId = jwtTokenUtil.extractUsername(jwtToken);
        } catch (Exception ex) {
            ex.printStackTrace();
            filterChain.doFilter(request, response);
            return ;
        }

        if (Objects.nonNull(userId) && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
            var user = userProfileService.getById(userId);
            UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                    user.getUsername(), user.getPassword(), Collections.emptyList());
            if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                Collection<GrantedAuthority> authorities = Collections.singletonList(
                        new SimpleGrantedAuthority("ROLE_" + user.getRole()));
                var usernamePasswordAuthToken = new UsernamePasswordAuthenticationToken(
                        user.getUsername(), user.getPassword(), authorities);
                usernamePasswordAuthToken.setDetails(user);
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
