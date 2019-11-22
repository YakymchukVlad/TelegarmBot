package com.khnu.yakymchuk.dao;

import com.khnu.yakymchuk.model.User;

import java.util.List;

public interface IUserDao {

    void addUser(User user);

    User getUserById(String id);

    void deleteUser(String token);

    List<User> getAllUsers();

}
