package com.khnu.yakymchuk.command.impl.adminCommands;

import com.khnu.yakymchuk.command.IActionCommand;
import com.khnu.yakymchuk.constant.RkeperConstants;
import com.khnu.yakymchuk.request.impl.adminRequest.UserRequest;
import com.khnu.yakymchuk.service.ITokenService;
import com.khnu.yakymchuk.utils.assertion.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddUserCommand implements IActionCommand<UserRequest> {

    private final static Logger LOG = LoggerFactory.getLogger(AddUserCommand.class);
    private ITokenService tokenService;

    public AddUserCommand(ITokenService userService) {
        this.tokenService = userService;
    }


    @Override
    public String execute(UserRequest request) {
        Assert.asserNotNull(request, "request cannot be null or empty");

        LOG.info("Add user with token number : " + request.getTelegramUserId());
        String userToken = request.getTelegramUserId();
        tokenService.addToken(userToken);
        return String.format(RkeperConstants.ADD_TOKEN_RESULT, userToken);
    }
}
