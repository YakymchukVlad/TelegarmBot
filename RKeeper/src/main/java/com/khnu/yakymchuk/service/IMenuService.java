package com.khnu.yakymchuk.service;

import com.khnu.yakymchuk.model.Dish;

import java.util.List;

public interface IMenuService {

    List<Dish> getMenu();

    Dish getDishById(String id);

    Dish getDishByName(String name);

    void addDish(Dish dish);

}
