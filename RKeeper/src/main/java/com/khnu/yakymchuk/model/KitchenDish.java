package com.khnu.yakymchuk.model;

import com.khnu.yakymchuk.annotation.Range;
import com.khnu.yakymchuk.enums.DishType;

import java.math.BigDecimal;

public class KitchenDish extends Dish {

    @Range
    private int timePreparing;

    public KitchenDish() {

    }

    public KitchenDish(String id, String name, BigDecimal cost, int time) {
        super(id, name, cost, DishType.KITCHEN);
        this.timePreparing = time;
    }

    public int getTimePreparing() {
        return timePreparing;
    }

    public void setTimePreparing(int timePreparing) {
        this.timePreparing = timePreparing;
    }

    @Override
    public String toString() {
        return "Dish [" + name +
                "\n № : " + id + ", " +
                "\n timePreparing = " + timePreparing +
                "\n cost = " + cost + "] \n";
    }
}
