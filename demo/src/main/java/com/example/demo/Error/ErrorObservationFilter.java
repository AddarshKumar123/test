package com.example.demo.Error;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class ErrorObservationFilter extends OncePerRequestFilter {

    private final ErrorReporter errorReporter;

    public ErrorObservationFilter(ErrorReporter errorReporter) {
        this.errorReporter = errorReporter;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws ServletException, IOException {

        try {
            chain.doFilter(request, response);
        } catch (Throwable ex) {
            errorReporter.sendAsync(ErrorEvent.from(ex, request));
            throw ex;
        }
    }
}


