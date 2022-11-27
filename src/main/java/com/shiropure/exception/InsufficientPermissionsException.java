package com.shiropure.exception;

public class InsufficientPermissionsException extends  Exception{
    public InsufficientPermissionsException() {
    }

    public InsufficientPermissionsException(String message) {
        super(message);
    }
}
