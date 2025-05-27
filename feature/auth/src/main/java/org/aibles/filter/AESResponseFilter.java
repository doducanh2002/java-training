package org.aibles.filter;

import lombok.extern.slf4j.Slf4j;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class AESResponseFilter implements Filter {

    private final String aesKey;

    public AESResponseFilter(String aesKey) {
        this.aesKey = aesKey;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        log.info("(AESResponseFilter) request: {}, response: {}", request, response);
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        CustomHttpServletResponseWrapper responseWrapper = new CustomHttpServletResponseWrapper(httpResponse);

        chain.doFilter(request, responseWrapper);

        if (httpResponse.getStatus() == HttpServletResponse.SC_OK) {
            try {
                log.info("Encrypting response...");

                String responseBody = responseWrapper.getCaptureAsString();
                String encryptedResponseBody = AESUtils.encrypt(responseBody, aesKey);
                log.info("Encrypted response: {}", encryptedResponseBody);
                log.info("(AESResponseFilter) responseBody: {}", responseBody);
                log.info("(AESResponseFilter) aesKey: {}", aesKey);
                httpResponse.setContentType("application/json");
                httpResponse.setCharacterEncoding("UTF-8");
                httpResponse.getOutputStream().write(encryptedResponseBody.getBytes());
                httpResponse.getOutputStream().flush();
            } catch (Exception e) {
                log.error("Error during response encryption", e);
                httpResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error during response encryption");
            }
        }
    }
}
