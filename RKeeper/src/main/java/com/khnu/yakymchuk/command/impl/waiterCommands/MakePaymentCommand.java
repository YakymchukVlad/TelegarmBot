package com.khnu.yakymchuk.command.impl.waiterCommands;

import com.khnu.yakymchuk.command.IActionCommand;
import com.khnu.yakymchuk.request.impl.waiterRequest.PaymentRequest;
import com.khnu.yakymchuk.service.ITableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MakePaymentCommand implements IActionCommand<PaymentRequest> {

    private ITableService tableService;

    private static final Logger LOG = LoggerFactory.getLogger(MakePaymentCommand.class);

    public MakePaymentCommand(ITableService tableService) {
        this.tableService = tableService;
    }

    @Override
    public String execute(PaymentRequest request) {
        String tableId = request.getTableId();
        tableService.makePaymentForAllTable(tableId);
        LOG.info("Make payment for the whole table with parameter tableID : {}", tableId);

        return "You made payment for table № : " + tableId;
    }

}
