package com.khnu.yakymchuk.service.impl;

import com.khnu.yakymchuk.dao.IOrderDao;
import com.khnu.yakymchuk.model.Order;
import com.khnu.yakymchuk.service.IOrderService;

import java.util.List;

public class OrderService implements IOrderService {

    private IOrderDao orderDAO;

    public OrderService(IOrderDao orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    public List<Order> getDailyOrders() {
        return orderDAO.getDailyOrders();
    }

    public List<Order> getActiveOrders() {
        return orderDAO.getActiveOrders();
    }

    @Override
    public void deleteOrder(Order order) {
        orderDAO.deleteOrder(order);
    }

    @Override
    public void addToDailyOrders(Order order) {
        assert order != null;
        orderDAO.addToDailyOrders(order);
    }

    public void addOrder(Order order) {
        assert order != null;
        orderDAO.addActiveOrder(order);
    }

}
