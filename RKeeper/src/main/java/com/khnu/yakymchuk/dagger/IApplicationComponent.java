package com.khnu.yakymchuk.dagger;

import com.khnu.yakymchuk.telegramBot.RKeeperBot;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {
        RequestProvider.class,
        CommandProvider.class,
        BuilderProvider.class,
        ServiceProvider.class,
        ExceptionHandlerProvider.class,
        ValidatorProvider.class,
        DisplayDataProvider.class,
        DataReaderProvider.class,
        DynamoDaoProvider.class,
        AmazonDynamoDBProvider.class,
        DynamoDBMapperProvider.class
})

public interface IApplicationComponent {

    void inject(RKeeperBot rKeeperBot);

}
