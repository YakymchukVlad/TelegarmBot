package com.khnu.yakymchuk.dagger;

import com.khnu.yakymchuk.exception.IExceptionHandler;
import com.khnu.yakymchuk.exception.impl.ExceptionHandler;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class ExceptionHandlerProvider {

    @Provides
    @Singleton
    public IExceptionHandler exceptionHandler() {
        return new ExceptionHandler();
    }

}
