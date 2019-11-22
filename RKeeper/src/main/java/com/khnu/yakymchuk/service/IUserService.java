package com.khnu.yakymchuk.service;

import com.khnu.yakymchuk.model.User;

import java.util.List;

public interface IUserService {

    void addUser(User user);

    void deleteUser(String userToken);

    User getUserByTelegramBotId(String botId);

    List<User> getAllUsers();

}
