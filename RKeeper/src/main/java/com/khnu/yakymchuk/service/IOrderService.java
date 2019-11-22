package com.khnu.yakymchuk.service;

import com.khnu.yakymchuk.model.Order;

import java.util.List;

public interface IOrderService {

    List<Order> getDailyOrders();

    void addToDailyOrders(Order order);

    void addOrder(Order order);

    List<Order> getActiveOrders();

    void deleteOrder(Order order);

}
