package com.khnu.yakymchuk.builder.impl.adminRequestBuilder;

import com.khnu.yakymchuk.builder.IRequestBuilder;
import com.khnu.yakymchuk.constant.LambdaConstant;
import com.khnu.yakymchuk.model.User;
import com.khnu.yakymchuk.request.impl.adminRequest.UserRequest;
import com.khnu.yakymchuk.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.stream.Collectors;

public class DeleteUserRequestBuilder implements IRequestBuilder<UserRequest> {

    private final static Logger LOG = LoggerFactory.getLogger(DeleteUserRequestBuilder.class);

    private IUserService userService;

    public DeleteUserRequestBuilder(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public UserRequest getRequest(String message) {
        UserRequest userRequest = new UserRequest();
        return constructUserRequest(userRequest, message);
    }

    private UserRequest constructUserRequest(UserRequest userRequest, String message) {
        LOG.info("Construct request to delete user");

        String[] paramMass = message.split(" ");
        String userToken = paramMass[1];
        userRequest.setTelegramUserId(userToken);

        return userRequest;
    }

    @Override
    public List<String> getMenuParameters(String message) {
        return userService.getAllUsers().stream().
                filter(user -> !user.getTelegramUserId().equals(LambdaConstant.MY_TELEGRAM_BOT_ID)).
                map(User::getTelegramUserId).collect(Collectors.toList());
    }

    @Override
    public String getInstructionsToUser(Update update) {
        if (update.getCallbackQuery().getData().split(" ").length == 1) {
            return "Choose the user token";
        }
        return "Press finish to delete user";
    }

}
