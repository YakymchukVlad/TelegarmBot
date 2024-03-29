package com.khnu.yakymchuk.dao.impl;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.khnu.yakymchuk.dao.BaseDao;
import com.khnu.yakymchuk.dao.IOrderDao;
import com.khnu.yakymchuk.model.Order;
import com.khnu.yakymchuk.utils.assertion.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class OrderDynamoDao extends BaseDao implements IOrderDao {

    private static final Logger LOG = LoggerFactory.getLogger(OrderDynamoDao.class);

    private static final String INDEX_NAME = "index-on-status";

    private DynamoDBMapper dynamoDBMapper;

    public OrderDynamoDao(AmazonDynamoDB amazonDynamoDB, DynamoDBMapper dynamoDBMapper) {
        super(amazonDynamoDB);
        this.dynamoDBMapper = dynamoDBMapper;
    }

    @Override
    public List<Order> getDailyOrders() {
        return getOrdersByStatus(true);
    }

    @Override
    public void addToDailyOrders(Order order) {
        Assert.asserNotNull(order, "Order cannot be null");

        Order o = Order.openOrder(order.getNumber(), order.getDishList(), order.getTableId(), order.getDisplayName(), order.getWaiterId());

        o.setPayed(true);
        o.setPaymentAmount(order.getPaymentAmount());
        o.setTimeBegin(order.getTimeBegin());

        dynamoDBMapper.save(o);
    }

    @Override
    public void addActiveOrder(Order order) {
        Assert.asserNotNull(order, "Order cannot be null");

        Order o = Order.openOrder(order.getNumber(), order.getDishList(), order.getTableId(), order.getDisplayName(), order.getWaiterId());
        o.setPayed(false);
        o.setTimeBegin(order.getTimeBegin());

        dynamoDBMapper.save(o);
    }

    @Override
    public List<Order> getActiveOrders() {
        return getOrdersByStatus(false);
    }

    @Override
    public void deleteOrder(Order order) {
        Assert.asserNotNull(order, "Order cannot be null");

        LOG.info("Delete order with parameters displayName : {} , table number : {} ", order.getTableId());
        Order o = Order.openOrder(order.getNumber(), order.getDishList(), order.getTableId(), order.getDisplayName(), order.getWaiterId());
        dynamoDBMapper.delete(o);
        LOG.debug("Order deleted from table");
    }

    private List<Order> getOrdersByStatus(boolean status) {
        Order order = new Order();
        order.setPayed(status);

        DynamoDBQueryExpression<Order> indexQueryExpression = new DynamoDBQueryExpression<Order>()
                .withIndexName(INDEX_NAME)
                .withHashKeyValues(order)
                .withConsistentRead(false);

        return new ArrayList<>(dynamoDBMapper.query(Order.class, indexQueryExpression));
    }

}
