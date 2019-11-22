package com.khnu.yakymchuk.exception;

public class CommandNotFoundException extends RuntimeException {

    public CommandNotFoundException(String message) {
        super(message);
    }

}
