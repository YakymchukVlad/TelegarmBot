package com.khnu.yakymchuk.dagger;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.khnu.yakymchuk.dao.IMenuDao;
import com.khnu.yakymchuk.dao.IOrderDao;
import com.khnu.yakymchuk.dao.ITableDao;
import com.khnu.yakymchuk.dao.ITokenDao;
import com.khnu.yakymchuk.dao.IUserDao;
import com.khnu.yakymchuk.dao.impl.MenuDynamoDao;
import com.khnu.yakymchuk.dao.impl.OrderDynamoDao;
import com.khnu.yakymchuk.dao.impl.TableDynamoDao;
import com.khnu.yakymchuk.dao.impl.TokenDynamoDao;
import com.khnu.yakymchuk.dao.impl.UserDynamoDao;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module(includes = DynamoDBMapperProvider.class)
public class DynamoDaoProvider {

    @Singleton
    @Provides
    public ITableDao tableDynamoDao(DynamoDBMapper dynamoDBMapper, AmazonDynamoDB amazonDynamoDB) {
        return new TableDynamoDao(amazonDynamoDB, dynamoDBMapper);
    }

    @Singleton
    @Provides
    public IOrderDao orderDynamoDao(AmazonDynamoDB amazonDynamoDB, DynamoDBMapper dynamoDBMapper) {
        return new OrderDynamoDao(amazonDynamoDB, dynamoDBMapper);
    }

    @Singleton
    @Provides
    public IMenuDao menuDynamoDao(AmazonDynamoDB amazonDynamoDB, DynamoDBMapper dynamoDBMapper) {
        return new MenuDynamoDao(amazonDynamoDB, dynamoDBMapper);
    }

    @Singleton
    @Provides
    public IUserDao userDynamoDao(AmazonDynamoDB amazonDynamoDB, DynamoDBMapper dynamoDBMapper) {
        return new UserDynamoDao(amazonDynamoDB, dynamoDBMapper);
    }

    @Singleton
    @Provides
    public ITokenDao tokenDynamoDao(AmazonDynamoDB amazonDynamoDB, DynamoDBMapper dynamoDBMapper) {
        return new TokenDynamoDao(amazonDynamoDB, dynamoDBMapper);
    }

}
