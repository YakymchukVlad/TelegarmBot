package com.khnu.yakymchuk.exception.impl;

import com.khnu.yakymchuk.exception.CommandNotFoundException;
import com.khnu.yakymchuk.exception.FreeTablesNotFoundException;
import com.khnu.yakymchuk.exception.IExceptionHandler;
import com.khnu.yakymchuk.exception.OrderNotFoundException;
import com.khnu.yakymchuk.exception.RequestBuilderNotFoundException;
import com.khnu.yakymchuk.exception.TableNotFoundException;
import com.khnu.yakymchuk.exception.UserNotFoundException;
import com.khnu.yakymchuk.exception.UserReadableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.InputMismatchException;

public class TelegrammExceptionHandler implements IExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(TelegrammExceptionHandler.class);

    public void handleThrowable(Throwable throwable) {

        if (throwable instanceof TableNotFoundException) {
            LOG.error(throwable.getMessage());
            throw new UserReadableException(throwable.getMessage());
        }
        if (throwable instanceof OrderNotFoundException) {
            LOG.error(throwable.getMessage());
            throw new UserReadableException(throwable.getMessage());
        }
        if (throwable instanceof FreeTablesNotFoundException) {
            LOG.error(throwable.getMessage());
            throw new UserReadableException(throwable.getMessage());
        }
        if (throwable instanceof NullPointerException) {
            LOG.error(throwable.getMessage());
            throw new UserReadableException(throwable.getMessage());
        }
        if (throwable instanceof InputMismatchException) {
            LOG.error(throwable.getMessage());
            throw new UserReadableException(throwable.getMessage());
        }
        if (throwable instanceof IllegalArgumentException) {
            LOG.error("Incorrect input data : " + throwable.getMessage());
            throw new UserReadableException("Incorrect input data : " + throwable.getMessage());
        }
        if (throwable instanceof CommandNotFoundException) {
            LOG.error("Command not found : " + throwable.getMessage());
            throw new UserReadableException(throwable.getMessage());
        }
        if (throwable instanceof RequestBuilderNotFoundException) {
            LOG.error("Request not found : " + throwable.getMessage());
            throw new UserReadableException(throwable.getMessage());
        }
        if (throwable instanceof UserNotFoundException) {
            LOG.error("User not found : " + throwable.getMessage());
            throw new UserReadableException(throwable.getMessage());
        }
    }

}
