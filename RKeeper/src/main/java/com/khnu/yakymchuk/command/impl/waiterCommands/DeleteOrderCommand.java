package com.khnu.yakymchuk.command.impl.waiterCommands;

import com.khnu.yakymchuk.command.IActionCommand;
import com.khnu.yakymchuk.constant.RkeperConstants;
import com.khnu.yakymchuk.model.Order;
import com.khnu.yakymchuk.request.impl.waiterRequest.DeleteRequest;
import com.khnu.yakymchuk.service.IOrderService;
import com.khnu.yakymchuk.service.ITableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeleteOrderCommand implements IActionCommand<DeleteRequest> {

    private ITableService tableService;
    private IOrderService orderService;

    private static final Logger LOG = LoggerFactory.getLogger(DeleteOrderCommand.class);

    public DeleteOrderCommand(ITableService tableService, IOrderService orderService) {
        this.tableService = tableService;
        this.orderService = orderService;
    }

    @Override
    public String execute(DeleteRequest request) {
        String tableId = request.getTableId();
        String displayName = request.getDisplayName();
        Order order = tableService.getOrderByDisplayName(tableId, displayName);

        tableService.deleteOrder(order);
        orderService.deleteOrder(order);

        LOG.info("Delete order with parameters {},{}", tableId, order);
        return String.format(RkeperConstants.DELETE_ORDER_RESULT, tableId);
    }

}
