package com.khnu.yakymchuk.builder;

import com.khnu.yakymchuk.request.IRequest;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

public interface IRequestBuilder<T extends IRequest> {

    T getRequest(String message);

    List<String> getMenuParameters(String message);

    String getInstructionsToUser(Update update);

}
