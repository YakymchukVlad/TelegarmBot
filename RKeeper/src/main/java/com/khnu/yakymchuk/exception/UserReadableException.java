package com.khnu.yakymchuk.exception;

public class UserReadableException extends RuntimeException {

    public UserReadableException(Throwable throwable) {
        super(throwable);
    }

    public UserReadableException(String message) {
        super(message);
    }

}
