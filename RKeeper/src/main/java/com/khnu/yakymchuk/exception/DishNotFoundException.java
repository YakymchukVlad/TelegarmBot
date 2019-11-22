package com.khnu.yakymchuk.exception;

public class DishNotFoundException extends RuntimeException {

    public DishNotFoundException(String message) {
        super(message);
    }

}
