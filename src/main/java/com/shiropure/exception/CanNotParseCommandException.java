package com.shiropure.exception;

public class CanNotParseCommandException extends  Exception{
    public CanNotParseCommandException() {
        super();
    }

    public CanNotParseCommandException(String message) {
        super(message);
    }

    public CanNotParseCommandException(String message, Throwable cause) {
        super(message, cause);
    }

    public CanNotParseCommandException(Throwable cause) {
        super(cause);
    }

    protected CanNotParseCommandException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
