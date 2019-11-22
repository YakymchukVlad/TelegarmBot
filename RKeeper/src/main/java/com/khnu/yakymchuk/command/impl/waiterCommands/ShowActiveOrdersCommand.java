package com.khnu.yakymchuk.command.impl.waiterCommands;

import com.khnu.yakymchuk.command.IActionCommand;
import com.khnu.yakymchuk.model.Order;
import com.khnu.yakymchuk.request.IRequest;
import com.khnu.yakymchuk.service.IOrderService;

import java.util.List;
import java.util.stream.Collectors;

public class ShowActiveOrdersCommand implements IActionCommand {

    private IOrderService orderService;

    public ShowActiveOrdersCommand(IOrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(IRequest request) {
        return showActiveOrders();
    }

    private String showActiveOrders() {
        List<Order> orderList = orderService.getActiveOrders();
        if (orderList.isEmpty()) {
            return "There are no active orders";
        } else {
            return orderList.
                    stream().
                    map(Order::toString).
                    collect(Collectors.joining("\n"));
        }
    }

}
