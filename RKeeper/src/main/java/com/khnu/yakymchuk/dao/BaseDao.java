package com.khnu.yakymchuk.dao;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;

public abstract class BaseDao<T> {

    private AmazonDynamoDB amazonDynamoDB;

    public BaseDao(AmazonDynamoDB amazonDynamoDB) {
        this.amazonDynamoDB = amazonDynamoDB;
    }
}
