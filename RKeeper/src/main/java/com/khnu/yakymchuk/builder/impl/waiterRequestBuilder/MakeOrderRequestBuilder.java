package com.khnu.yakymchuk.builder.impl.waiterRequestBuilder;

import com.khnu.yakymchuk.builder.IRequestBuilder;
import com.khnu.yakymchuk.model.Dish;
import com.khnu.yakymchuk.model.Table;
import com.khnu.yakymchuk.request.impl.waiterRequest.OrderRequest;
import com.khnu.yakymchuk.service.IMenuService;
import com.khnu.yakymchuk.service.ITableService;
import com.khnu.yakymchuk.utils.assertion.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MakeOrderRequestBuilder implements IRequestBuilder<OrderRequest> {

    private static final Logger LOG = LoggerFactory.getLogger(MakeOrderRequestBuilder.class);

    private ITableService tableService;
    private IMenuService menuService;

    public MakeOrderRequestBuilder(IMenuService menuService, ITableService tableService) {
        this.menuService = menuService;
        this.tableService = tableService;
    }

    @Override
    public OrderRequest getRequest(String message) {
        OrderRequest orderRequest = new OrderRequest();
        return constructOrderRequest(orderRequest, message);
    }

    private OrderRequest constructOrderRequest(OrderRequest orderRequest, String message) {
        String[] paramMass = message.split(" ");

        String tableId = paramMass[1];
        orderRequest.setTableId(tableId);
        List<Dish> dishes = new ArrayList<>();

        for (int i = 2; i < message.split(" ").length - 1; i++) {
            addDishesToOrder(dishes, paramMass[i]);
        }
        orderRequest.setDishList(dishes);
        LOG.debug("Construct request with parameters {},{}", tableId, dishes);

        return orderRequest;
    }

    private void addDishesToOrder(List<Dish> dishes, String name) {
        Assert.asserHasText(name, "name cannot be null or empty");
        for (Dish dish : menuService.getMenu()) {
            if (name.equals(dish.getName())) {
                dishes.add(dish);
            }
        }
    }

    @Override
    public List<String> getMenuParameters(String message) {
        if ("mo".equals(message)) {
            return tableService.getAllTables().stream().map(Table::getNumber).collect(Collectors.toList());
        }
        return menuService.getMenu().stream().map(Dish::getName).collect(Collectors.toList());
    }

    @Override
    public String getInstructionsToUser(Update update) {
        Assert.asserNotNull(update, "update data cannot be null");

        switch (update.getCallbackQuery().getData().split(" ").length) {
            case 1: {
                return "Choose the table number";
            }
            case 2: {
                return "Choose the dish";
            }
            default: {
                return "Choose the dish and finally press Finish to make order";
            }
        }
    }

}
