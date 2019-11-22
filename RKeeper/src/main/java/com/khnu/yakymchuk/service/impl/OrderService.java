package com.khnu.yakymchuk.service.impl;

import com.khnu.yakymchuk.dao.IOrderDao;
import com.khnu.yakymchuk.model.Order;
import com.khnu.yakymchuk.service.IOrderService;
import com.khnu.yakymchuk.utils.assertion.Assert;

import java.util.List;

public class OrderService implements IOrderService {

    private IOrderDao orderDao;

    public OrderService(IOrderDao orderDAO) {
        this.orderDao = orderDAO;
    }

    @Override
    public List<Order> getDailyOrders() {
        return orderDao.getDailyOrders();
    }

    public List<Order> getActiveOrders() {
        return orderDao.getActiveOrders();
    }

    @Override
    public void deleteOrder(Order order) {
        Assert.asserNotNull(orderDao, "Order cannot be null");
        orderDao.deleteOrder(order);
    }

    @Override
    public void addToDailyOrders(Order order) {
        Assert.asserNotNull(orderDao, "Order cannot be null");
        orderDao.addToDailyOrders(order);
    }

    public void addOrder(Order order) {
        Assert.asserNotNull(orderDao, "Order cannot be null");
        orderDao.addActiveOrder(order);
    }

}
