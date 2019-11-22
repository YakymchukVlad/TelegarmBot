package com.khnu.yakymchuk.builder.impl.waiterRequestBuilder;

import com.khnu.yakymchuk.builder.IRequestBuilder;
import com.khnu.yakymchuk.model.Table;
import com.khnu.yakymchuk.request.impl.waiterRequest.DiscountRequest;
import com.khnu.yakymchuk.service.ITableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MakeDiscountRequestBuilder implements IRequestBuilder<DiscountRequest> {

    private static final Logger LOG = LoggerFactory.getLogger(MakeDiscountRequestBuilder.class);

    private ITableService tableService;

    public MakeDiscountRequestBuilder(ITableService tableService) {
        this.tableService = tableService;
    }

    @Override
    public DiscountRequest getRequest(String message) {
        DiscountRequest orderRequest = new DiscountRequest();
        return constructTableRequest(orderRequest, message);
    }

    private DiscountRequest constructTableRequest(DiscountRequest orderRequest, String message) {
        String[] paramMass = message.split(" ");
        String tableID = paramMass[1];
        orderRequest.setTableId(tableID);
        int percent = Integer.parseInt(paramMass[2]);
        orderRequest.setPercent(percent);
        LOG.debug("Construct request with parameters {},{}", tableID, percent);

        return orderRequest;
    }

    @Override
    public List<String> getMenuParameters(String message) {
        if ("md".equals(message)) {
            return tableService.getActiveTables().stream().map(Table::getNumber).collect(Collectors.toList());
        }
        return Arrays.asList("5", "10", "30", "50");
    }

    @Override
    public String getInstructionsToUser(Update update) {
        switch (update.getCallbackQuery().getData().split(" ").length) {
            case 1: {
                return "Choose the table number";
            }
            case 2: {
                return "Choose the percent";
            }
            default: {
                return "Press finish to make discount";
            }
        }
    }

}
