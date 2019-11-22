package com.khnu.yakymchuk.dao.impl;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.khnu.yakymchuk.dao.BaseDao;
import com.khnu.yakymchuk.dao.IUserDao;
import com.khnu.yakymchuk.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class UserDynamoDao extends BaseDao implements IUserDao {

    private final static Logger LOG = LoggerFactory.getLogger(UserDynamoDao.class);

    private DynamoDBMapper dynamoDBMapper;

    public UserDynamoDao(AmazonDynamoDB amazonDynamoDB, DynamoDBMapper dynamoDBMapper) {
        super(amazonDynamoDB);
        this.dynamoDBMapper = dynamoDBMapper;
    }

    @Override
    public void addUser(User user) {
        LOG.info("Method addUser starts with parameter: user id,user name, user last name --> {},{},{}", user.getTelegramUserId(), user.getName(), user.getLastName());
        User u = new User();
        u.setTelegramUserId(user.getTelegramUserId());
        u.setName(user.getName());
        u.setLastName(user.getLastName());
        u.setRole(user.getRole());

        dynamoDBMapper.save(u);
    }

    @Override
    public User getUserById(String telegramUserId) {
        LOG.info("Method getUserById starts with parameter: id --> {}", telegramUserId);
        User user = new User();
        user.setTelegramUserId(telegramUserId);
        return dynamoDBMapper.load(user);
    }

    @Override
    public void deleteUser(String token) {
        LOG.info("Delete user with token {}", token);
        User user = getUserById(token);
        dynamoDBMapper.delete(user);
    }

    @Override
    public List<User> getAllUsers() {
        DynamoDBScanExpression dynamoDBScanExpression = new DynamoDBScanExpression();
        return new ArrayList<>(dynamoDBMapper.scan(User.class, dynamoDBScanExpression));
    }

}
