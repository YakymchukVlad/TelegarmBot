package com.khnu.yakymchuk.dagger;

import com.khnu.yakymchuk.reader.IDisplayData;
import com.khnu.yakymchuk.reader.impl.ConsoleDisplay;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class DisplayDataProvider {

    @Provides
    @Singleton
    public IDisplayData displayData() {
        return new ConsoleDisplay();
    }

}
