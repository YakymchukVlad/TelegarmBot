package com.khnu.yakymchuk.dao;

import com.khnu.yakymchuk.model.Order;

import java.util.List;

public interface IOrderDao {

    List<Order> getDailyOrders();

    void addToDailyOrders(Order order);

    void addActiveOrder(Order order);

    List<Order> getActiveOrders();

    void deleteOrder(Order order);

}
