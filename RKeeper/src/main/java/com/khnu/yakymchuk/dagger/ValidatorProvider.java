package com.khnu.yakymchuk.dagger;

import com.khnu.yakymchuk.validator.IValidator;
import com.khnu.yakymchuk.validator.impl.DishValidator;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class ValidatorProvider {

    @Singleton
    @Provides
    public IValidator dishValidator() {
        return new DishValidator();
    }

}
