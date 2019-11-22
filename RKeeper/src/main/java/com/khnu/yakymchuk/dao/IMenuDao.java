package com.khnu.yakymchuk.dao;

import com.khnu.yakymchuk.model.Dish;

import java.util.List;

public interface IMenuDao {

    List getMenu();

    Dish getDishById(String id);

    void addDish(Dish dish);

    Dish getDishByName(String name);

}
