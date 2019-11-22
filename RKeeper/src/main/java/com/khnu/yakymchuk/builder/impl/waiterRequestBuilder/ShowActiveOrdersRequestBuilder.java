package com.khnu.yakymchuk.builder.impl.waiterRequestBuilder;

import com.khnu.yakymchuk.builder.IRequestBuilder;
import com.khnu.yakymchuk.request.IRequest;
import com.khnu.yakymchuk.request.EmptyRequest;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

public class ShowActiveOrdersRequestBuilder implements IRequestBuilder {

    public ShowActiveOrdersRequestBuilder() {

    }

    @Override
    public IRequest getRequest(String message) {
        return new EmptyRequest();
    }

    @Override
    public List<String> getMenuParameters(String message) {
        return null;
    }

    @Override
    public String getInstructionsToUser(Update update) {
        return null;
    }

}
