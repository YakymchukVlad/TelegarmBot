package com.khnu.yakymchuk.service.impl;

import com.khnu.yakymchuk.dao.IMenuDao;
import com.khnu.yakymchuk.exception.DishNotFoundException;
import com.khnu.yakymchuk.model.Dish;
import com.khnu.yakymchuk.service.IMenuService;
import com.khnu.yakymchuk.utils.assertion.Assert;

import java.util.List;

public class MenuService implements IMenuService {

    private IMenuDao menuDao;

    public MenuService(IMenuDao menuDAO) {
        this.menuDao = menuDAO;
    }

    @Override
    public List<Dish> getMenu() {
        return menuDao.getMenu();
    }

    @Override
    public Dish getDishById(String id) {
        Assert.asserHasText(id, "id cannot be null or empty");

        if (menuDao.getDishById(id) == null) {
            throw new DishNotFoundException(String.format("Cannot find dish with number %s", id));
        }
        return menuDao.getDishById(id);
    }

    @Override
    public Dish getDishByName(String name) {
        Assert.asserHasText(name, "name cannot be null or empty");

        if (menuDao.getDishByName(name) == null) {
            throw new DishNotFoundException(String.format("Cannot find dish with name %s", name));
        }
        return menuDao.getDishByName(name);
    }

    @Override
    public void addDish(Dish dish) {
        Assert.asserNotNull(dish, "Dish cannot be null");
        menuDao.addDish(dish);
    }
}
