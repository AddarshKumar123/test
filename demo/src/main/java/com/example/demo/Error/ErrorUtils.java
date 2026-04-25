package com.example.demo.Error;

public class ErrorUtils {

    public static Throwable getRootCause(Throwable ex) {
        Throwable root = ex;
        while (root.getCause() != null && root.getCause() != root) {
            root = root.getCause();
        }
        return root;
    }

    public static StackTraceElement findApplicationFrame(
            Throwable root,
            String basePackage
    ) {
        for (StackTraceElement element : root.getStackTrace()) {
            if (element.getClassName().startsWith(basePackage)) {
                return element;
            }
        }
        return null;
    }
}


