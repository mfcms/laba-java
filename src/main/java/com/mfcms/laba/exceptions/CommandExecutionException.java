package com.mfcms.laba.exceptions;

public class CommandExecutionException extends Exception {
    
    public CommandExecutionException() {
    }

    public CommandExecutionException(String message) {
        super(message);
    }

    public CommandExecutionException(Throwable cause) {
        super(cause);
    }

    public CommandExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandExecutionException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
