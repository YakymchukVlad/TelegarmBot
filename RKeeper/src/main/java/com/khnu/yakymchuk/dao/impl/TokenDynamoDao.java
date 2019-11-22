package com.khnu.yakymchuk.dao.impl;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.khnu.yakymchuk.dao.BaseDao;
import com.khnu.yakymchuk.dao.ITokenDao;
import com.khnu.yakymchuk.model.Token;
import com.khnu.yakymchuk.utils.assertion.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TokenDynamoDao extends BaseDao<Token> implements ITokenDao {

    private final static Logger LOG = LoggerFactory.getLogger(TokenDynamoDao.class);

    private DynamoDBMapper dynamoDBMapper;

    public TokenDynamoDao(AmazonDynamoDB amazonDynamoDB, DynamoDBMapper dynamoDBMapper) {
        super(amazonDynamoDB);
        this.dynamoDBMapper = dynamoDBMapper;
    }

    @Override
    public void addToken(String tokenNumber) {
        Assert.asserHasText(tokenNumber, "Token number cannot be null or empty");

        LOG.info("Add new token with number : " + tokenNumber);
        Token t = new Token();
        t.setToken(tokenNumber);
        dynamoDBMapper.save(t);
    }

    @Override
    public Token getTokenAndDelete(String tokenNumber) {
        Assert.asserHasText(tokenNumber, "Token cannot be null or empty");

        LOG.info("Load token by number : " + tokenNumber);
        Token token = new Token();
        token.setToken(tokenNumber);
        try {
            return dynamoDBMapper.load(token);
        } finally {
            LOG.info("Delete token after returning");
            dynamoDBMapper.delete(token);
        }
    }

}
