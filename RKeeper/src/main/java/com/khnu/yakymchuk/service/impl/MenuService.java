package com.khnu.yakymchuk.service.impl;

import com.khnu.yakymchuk.dao.IMenuDao;
import com.khnu.yakymchuk.exception.DishNotFoundException;
import com.khnu.yakymchuk.model.Dish;
import com.khnu.yakymchuk.service.IMenuService;

import java.util.List;

public class MenuService implements IMenuService {

    private IMenuDao menuDAO;

    public MenuService(IMenuDao menuDAO) {
        this.menuDAO = menuDAO;
    }

    @Override
    public List<Dish> getMenu() {
        return menuDAO.getMenu();
    }

    @Override
    public Dish getDishById(String id) {
        if (menuDAO.getDishById(id) == null) {
            throw new DishNotFoundException("Cannot find dish with number " + id);
        }
        return menuDAO.getDishById(id);
    }

    @Override
    public Dish getDishByName(String name) {
        if (menuDAO.getDishByName(name) == null) {
            throw new DishNotFoundException("Cannot find dish with name " + name);
        }
        return menuDAO.getDishByName(name);
    }

    @Override
    public void addDish(Dish dish) {
        assert dish != null;
        menuDAO.addDish(dish);
    }

}
