package com.khnu.yakymchuk.command.impl.waiterCommands;

import com.khnu.yakymchuk.command.IActionCommand;
import com.khnu.yakymchuk.model.Order;
import com.khnu.yakymchuk.request.IRequest;
import com.khnu.yakymchuk.service.IOrderService;

import java.util.List;
import java.util.stream.Collectors;

public class ShowDailyOrdersCommand implements IActionCommand {

    private IOrderService orderService;

    public ShowDailyOrdersCommand(IOrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(IRequest request) {
        return getDailyOrders();
    }

    private String getDailyOrders() {
        List<Order> dailyOrders = orderService.getDailyOrders();
        if (dailyOrders.isEmpty()) {
            return "There list of daily orders is empty";
        } else {
            return dailyOrders.
                    stream().
                    map(Order::toString).
                    collect(Collectors.joining("\n"));
        }
    }

}
