package com.khnu.yakymchuk.exception;

public class TableNotFoundException extends RuntimeException {

    public TableNotFoundException(String message) {
        super(message);
    }

}
