package com.khnu.yakymchuk.command.impl.waiterCommands;

import com.khnu.yakymchuk.command.IActionCommand;
import com.khnu.yakymchuk.model.Dish;
import com.khnu.yakymchuk.request.impl.waiterRequest.OrderRequest;
import com.khnu.yakymchuk.service.ITableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class MakeOrderCommand implements IActionCommand<OrderRequest> {

    private ITableService tableService;
    private static final Logger LOG = LoggerFactory.getLogger(MakeOrderCommand.class);

    public MakeOrderCommand(ITableService tableService) {
        this.tableService = tableService;
    }

    @Override
    public String execute(OrderRequest request) {
        String tableID = request.getTableId();
        List<Dish> dishes = request.getDishList();
        String displayName = request.getDisplayName();
        String waiterId = request.getWaiterId();

        tableService.makeOrder(tableID, dishes, displayName, waiterId);

        LOG.debug("Make order with parameters {},{}", tableID, dishes);
        return "You made order for table № : " + tableID;
    }
}
