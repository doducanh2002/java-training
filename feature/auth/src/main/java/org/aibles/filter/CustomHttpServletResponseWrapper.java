package org.aibles.filter;

import lombok.extern.slf4j.Slf4j;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import java.io.CharArrayWriter;
import java.io.PrintWriter;
@Slf4j
public class CustomHttpServletResponseWrapper extends HttpServletResponseWrapper {

    private CharArrayWriter charArrayWriter = new CharArrayWriter();
    private PrintWriter writer = new PrintWriter(charArrayWriter);

    public CustomHttpServletResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    @Override
    public PrintWriter getWriter() {
        log.info("getWriter");
        return writer;
    }

    public String getCaptureAsString() {
        writer.flush();
        return charArrayWriter.toString();
    }
}
