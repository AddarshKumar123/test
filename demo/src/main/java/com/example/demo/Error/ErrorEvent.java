package com.example.demo.Error;

import jakarta.servlet.http.HttpServletRequest;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

public record ErrorEvent(
        String exceptionType,
        String message,
        List<String> stackTrace,
        String endpoint,
        Integer lineNumber,
        String className,
        String fileName,
        String methodName,
        Instant timestamp
) {

    public static ErrorEvent from(Throwable ex, HttpServletRequest req) {

        Throwable root = ErrorUtils.getRootCause(ex);

        List<String> stack = Arrays.stream(root.getStackTrace())
                .map(StackTraceElement::toString)
                .toList();

        StackTraceElement frame =
                ErrorUtils.findApplicationFrame(root, "com.example");

        // Defaults (in case frame is null)
        Integer lineNumber = null;
        String className = null;
        String fileName = null;
        String methodName = null;

        if (frame != null) {
            lineNumber = frame.getLineNumber();
            className = frame.getClassName();
            fileName = frame.getFileName();
            methodName = frame.getMethodName();
        }

        return new ErrorEvent(
                root.getClass().getName(),
                root.getMessage(),
                stack,
                req.getMethod() + " " + req.getRequestURI(),
                lineNumber,
                className,
                fileName,
                methodName,
                Instant.now()
        );
    }
}
