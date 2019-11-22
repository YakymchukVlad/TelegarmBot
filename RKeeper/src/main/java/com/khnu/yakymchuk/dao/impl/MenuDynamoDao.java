package com.khnu.yakymchuk.dao.impl;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.khnu.yakymchuk.dao.IMenuDao;
import com.khnu.yakymchuk.dao.BaseDao;
import com.khnu.yakymchuk.model.Dish;
import com.khnu.yakymchuk.utils.assertion.Assert;

import java.util.ArrayList;
import java.util.List;

public class MenuDynamoDao extends BaseDao<Dish> implements IMenuDao {

    private DynamoDBMapper dynamoDBMapper;

    public MenuDynamoDao(AmazonDynamoDB amazonDynamoDB, DynamoDBMapper dynamoDBMapper) {
        super(amazonDynamoDB);
        this.dynamoDBMapper = dynamoDBMapper;
    }

    @Override
    public List<Dish> getMenu() {
        DynamoDBScanExpression dynamoDBScanExpression = new DynamoDBScanExpression();
        return new ArrayList<>(dynamoDBMapper.scan(Dish.class, dynamoDBScanExpression));
    }

    @Override
    public Dish getDishById(String id) {
        Assert.asserHasText(id, "Id cannot be null");

        Dish dish = new Dish();
        dish.setId(id);
        return dynamoDBMapper.load(dish);
    }

    @Override
    public void addDish(Dish dish) {
        dynamoDBMapper.save(dish);
    }

    @Override
    public Dish getDishByName(String name) {
        Assert.asserHasText(name, "Name cannot be null");

        Dish dish = new Dish();
        dish.setName(name);

        return dynamoDBMapper.load(dish);
    }

}
