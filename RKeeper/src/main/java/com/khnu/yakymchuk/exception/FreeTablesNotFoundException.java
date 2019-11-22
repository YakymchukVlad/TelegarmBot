package com.khnu.yakymchuk.exception;

public class FreeTablesNotFoundException extends RuntimeException {

    public FreeTablesNotFoundException(String message) {
        super(message);
    }
}
