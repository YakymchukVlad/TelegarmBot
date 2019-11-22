package com.khnu.yakymchuk.dagger;

import com.khnu.yakymchuk.reader.IDataReader;
import com.khnu.yakymchuk.reader.IDisplayData;
import com.khnu.yakymchuk.reader.impl.ConsoleDataReader;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module(includes = DisplayDataProvider.class)
public class DataReaderProvider {

    @Provides
    @Singleton
    public IDataReader consoleDataReader(IDisplayData displayData) {
        return new ConsoleDataReader(displayData);
    }

}
