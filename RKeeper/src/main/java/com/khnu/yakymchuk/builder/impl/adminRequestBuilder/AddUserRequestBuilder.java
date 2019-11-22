package com.khnu.yakymchuk.builder.impl.adminRequestBuilder;

import com.khnu.yakymchuk.builder.IRequestBuilder;
import com.khnu.yakymchuk.request.impl.adminRequest.UserRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Arrays;
import java.util.List;

public class AddUserRequestBuilder implements IRequestBuilder<UserRequest> {

    private final static Logger LOG = LoggerFactory.getLogger(AddUserRequestBuilder.class);

    public AddUserRequestBuilder() {
    }

    @Override
    public UserRequest getRequest(String message) {
        UserRequest userRequest = new UserRequest();
        return constructOrderRequest(userRequest, message);
    }

    private UserRequest constructOrderRequest(UserRequest userRequest, String message) {
        LOG.info("Construct order request..");

        String[] paramMas = message.split(" ");
        StringBuilder token = new StringBuilder();
        for (int i = 1; i < paramMas.length - 1; i++) {
            token.append(paramMas[i]);
        }
        userRequest.setTelegramUserId(token.toString());

        return userRequest;
    }

    @Override
    public List<String> getMenuParameters(String message) {
        switch (message) {
            case "au": {
                return Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
            }
            default: {
                return Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
            }
        }
    }

    @Override
    public String getInstructionsToUser(Update update) {
        switch (update.getCallbackQuery().getData().split(" ").length) {
            case 1: {
                return "Choose the digits of password";
            }
            default: {
                return "Choose the digits and finally, press finish to add user";
            }
        }
    }

}
