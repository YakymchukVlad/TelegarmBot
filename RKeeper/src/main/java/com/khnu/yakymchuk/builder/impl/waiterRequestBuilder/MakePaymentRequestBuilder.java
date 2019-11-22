package com.khnu.yakymchuk.builder.impl.waiterRequestBuilder;

import com.khnu.yakymchuk.builder.IRequestBuilder;
import com.khnu.yakymchuk.model.Table;
import com.khnu.yakymchuk.request.impl.waiterRequest.PaymentRequest;
import com.khnu.yakymchuk.service.ITableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.stream.Collectors;

public class MakePaymentRequestBuilder implements IRequestBuilder<PaymentRequest> {

    private Logger LOG = LoggerFactory.getLogger(MakePaymentRequestBuilder.class);
    private ITableService tableService;

    public MakePaymentRequestBuilder(ITableService tableService) {
        this.tableService = tableService;
    }

    @Override
    public PaymentRequest getRequest(String message) {
        PaymentRequest paymentRequest = new PaymentRequest();
        return constructOrderRequest(paymentRequest, message);
    }

    private PaymentRequest constructOrderRequest(PaymentRequest orderRequest, String message) {
        String[] paramMass = message.split(" ");
        String tableId = paramMass[1];
        orderRequest.setTableId(tableId);
        LOG.debug("Construct request with parameters {}", tableId);

        return orderRequest;
    }

    @Override
    public List<String> getMenuParameters(String message) {
        return tableService.getActiveTables().stream().map(Table::getNumber).collect(Collectors.toList());
    }

    @Override
    public String getInstructionsToUser(Update update) {
        switch (update.getCallbackQuery().getData().split(" ").length) {
            case 1: {
                return "Choose the table number";
            }
            default: {
                return "Press finish to make payment";
            }
        }
    }

}
