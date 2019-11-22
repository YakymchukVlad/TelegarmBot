package com.khnu.yakymchuk.command.impl.adminCommands;

import com.khnu.yakymchuk.command.IActionCommand;
import com.khnu.yakymchuk.request.impl.adminRequest.UserRequest;
import com.khnu.yakymchuk.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeleteUserCommand implements IActionCommand<UserRequest> {

    private final static Logger LOG = LoggerFactory.getLogger(DeleteUserCommand.class);
    private IUserService userService;

    public DeleteUserCommand(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(UserRequest request) {
        LOG.info("Delete user with token number : " + request.getTelegramUserId());
        String telegramUserId = request.getTelegramUserId();
        userService.deleteUser(telegramUserId);

        return "User with token: " + telegramUserId + " was deleted.";
    }
}
