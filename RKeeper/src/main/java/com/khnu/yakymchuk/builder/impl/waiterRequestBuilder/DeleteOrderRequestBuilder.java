package com.khnu.yakymchuk.builder.impl.waiterRequestBuilder;

import com.khnu.yakymchuk.builder.IRequestBuilder;
import com.khnu.yakymchuk.model.Order;
import com.khnu.yakymchuk.model.Table;
import com.khnu.yakymchuk.request.impl.waiterRequest.DeleteRequest;
import com.khnu.yakymchuk.service.ITableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.stream.Collectors;

public class DeleteOrderRequestBuilder implements IRequestBuilder<DeleteRequest> {

    private Logger LOG = LoggerFactory.getLogger(DeleteOrderRequestBuilder.class);
    private ITableService tableService;

    public DeleteOrderRequestBuilder(ITableService tableService) {
        this.tableService = tableService;
    }

    @Override
    public DeleteRequest getRequest(String message) {
        DeleteRequest deleteRequest = new DeleteRequest();
        return constructOrderRequest(deleteRequest, message);
    }


    private DeleteRequest constructOrderRequest(DeleteRequest orderRequest, String message) {
        String[] paramMass = message.split(" ");

        String tableId = paramMass[1];
        orderRequest.setTableId(tableId);
        String displayName = paramMass[2];
        orderRequest.setDisplayName(displayName);

        LOG.debug("Construct request with parameters {},{}", tableId, displayName);

        return orderRequest;
    }

    @Override
    public List<String> getMenuParameters(String message) {
        switch (message) {
            case "do": {
                return tableService.getActiveTables().stream().map(Table::getNumber).collect(Collectors.toList());
            }
            default: {
                return tableService.getTableById(message.split(" ")[1]).getOrders().stream().map(Order::getDisplayName).collect(Collectors.toList());
            }
        }
    }

    @Override
    public String getInstructionsToUser(Update update) {
        switch (update.getCallbackQuery().getData().split(" ").length) {
            case 1: {
                return "Choose the table number";
            }
            case 2: {
                return "Choose the order to delete";
            }
            default: {
                return "Press finish to delete order";
            }
        }
    }

}
