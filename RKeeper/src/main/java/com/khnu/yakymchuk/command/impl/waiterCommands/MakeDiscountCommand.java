package com.khnu.yakymchuk.command.impl.waiterCommands;

import com.khnu.yakymchuk.command.IActionCommand;
import com.khnu.yakymchuk.request.impl.waiterRequest.DiscountRequest;
import com.khnu.yakymchuk.service.ITableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MakeDiscountCommand implements IActionCommand<DiscountRequest> {

    private ITableService tableService;

    private static final Logger LOG = LoggerFactory.getLogger(MakeDiscountCommand.class);

    public MakeDiscountCommand(ITableService tableService) {
        this.tableService = tableService;
    }

    @Override
    public String execute(DiscountRequest request) {
        String tableId = request.getTableId();
        int percent = request.getPercent();
        tableService.makeDiscountForWholeTable(tableId, percent);
        LOG.debug("Make discount for order of table № {}, {} %", tableId, percent);

        return "You made discount for table №: " + tableId + " for " + percent + " % ";
    }

}
