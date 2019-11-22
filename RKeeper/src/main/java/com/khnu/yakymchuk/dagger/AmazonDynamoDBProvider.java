package com.khnu.yakymchuk.dagger;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class AmazonDynamoDBProvider {

    @Provides
    @Singleton
    public AmazonDynamoDB amazonDynamoDB() {
        return AmazonDynamoDBClientBuilder.defaultClient();
    }

}
