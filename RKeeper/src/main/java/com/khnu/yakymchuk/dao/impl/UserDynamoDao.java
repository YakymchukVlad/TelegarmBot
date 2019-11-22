package com.khnu.yakymchuk.dao.impl;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.khnu.yakymchuk.dao.BaseDao;
import com.khnu.yakymchuk.dao.IUserDao;
import com.khnu.yakymchuk.model.User;
import com.khnu.yakymchuk.utils.assertion.Assert;

import java.util.ArrayList;
import java.util.List;

public class UserDynamoDao extends BaseDao implements IUserDao {

    private DynamoDBMapper dynamoDBMapper;

    public UserDynamoDao(AmazonDynamoDB amazonDynamoDB, DynamoDBMapper dynamoDBMapper) {
        super(amazonDynamoDB);
        this.dynamoDBMapper = dynamoDBMapper;
    }

    @Override
    public void addUser(User user) {
        Assert.asserNotNull(user, "User cannot be null or empty");

        User u = new User();
        u.setTelegramUserId(user.getTelegramUserId());
        u.setName(user.getName());
        u.setLastName(user.getLastName());
        u.setRole(user.getRole());

        dynamoDBMapper.save(u);
    }

    @Override
    public User getUserById(String telegramUserId) {
        Assert.asserHasText(telegramUserId, "Telegram user id cannot be null or empty");

        User user = new User();
        user.setTelegramUserId(telegramUserId);
        return dynamoDBMapper.load(user);
    }

    @Override
    public void deleteUser(String userId) {
        Assert.asserHasText(userId, "User id cannot be null or empty");

        User user = getUserById(userId);
        dynamoDBMapper.delete(user);
    }

    @Override
    public List<User> getAllUsers() {
        DynamoDBScanExpression dynamoDBScanExpression = new DynamoDBScanExpression();
        return new ArrayList<>(dynamoDBMapper.scan(User.class, dynamoDBScanExpression));
    }

}
