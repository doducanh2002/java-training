package org.aibles.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@Slf4j
public class AESRequestFilter extends OncePerRequestFilter {

    private final String aesKey;

    public AESRequestFilter(String aesKey) {
        this.aesKey = aesKey;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        log.info("AES_KEY: {}", aesKey);
        if (!"POST".equals(request.getMethod()) && !"PUT".equals(request.getMethod())) {
            filterChain.doFilter(request, response);
            return;
        }

        String requestBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        if (requestBody == null || requestBody.isEmpty()) {
            log.error("Empty request body");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Empty request body");
            return;
        }

        try {
            log.info("Encrypted request body: {}", requestBody);

            String decryptedBody = AESUtils.decrypt(requestBody, aesKey);

            log.info("Decrypted body: {}", decryptedBody);

            WrappedHttpServletRequest wrappedRequest = new WrappedHttpServletRequest(request, decryptedBody);
            filterChain.doFilter(wrappedRequest, response);
        } catch (Exception e) {
            log.error("Decryption failed: {}", e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid encrypted request body");
        }
    }
}
