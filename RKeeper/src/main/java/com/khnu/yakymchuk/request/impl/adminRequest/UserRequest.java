package com.khnu.yakymchuk.request.impl.adminRequest;

import com.khnu.yakymchuk.request.IRequest;

public class UserRequest implements IRequest {

    private String userName;
    private String userLastName;
    private String telegramUserId;

    public String getTelegramUserId() {
        return telegramUserId;
    }

    public void setTelegramUserId(String telegramUserId) {
        this.telegramUserId = telegramUserId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }
}
