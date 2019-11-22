package com.khnu.yakymchuk.service.impl;

import com.khnu.yakymchuk.dao.IUserDao;
import com.khnu.yakymchuk.model.User;
import com.khnu.yakymchuk.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UserService implements IUserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    private IUserDao userDao;

    public UserService(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void addUser(User user) {
        if (user != null) {
            LOG.info("Method start's with parameters telegramUserId , user name {},{}", user.getTelegramUserId(), user.getName());
            userDao.addUser(user);
        }
    }

    @Override
    public void deleteUser(String userToken) {
        userDao.deleteUser(userToken);
    }

    @Override
    public User getUserByTelegramBotId(String botId) {
        return userDao.getUserById(botId);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

}
