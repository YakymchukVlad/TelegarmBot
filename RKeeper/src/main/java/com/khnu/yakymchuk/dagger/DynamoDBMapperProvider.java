package com.khnu.yakymchuk.dagger;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module(includes = AmazonDynamoDBProvider.class)
public class DynamoDBMapperProvider {

    @Provides
    @Singleton
    public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB) {
        return new DynamoDBMapper(amazonDynamoDB);
    }

}
