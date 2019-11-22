package com.khnu.yakymchuk.model;

import com.khnu.yakymchuk.annotation.NotNull;
import com.khnu.yakymchuk.enums.DishType;

import java.math.BigDecimal;

public class BarDish extends Dish {

    @NotNull
    private boolean withAlcohol;

    public BarDish() {

    }

    public BarDish(String id, String name, BigDecimal cost, boolean withAlcohol) {
        super(id, name, cost, DishType.bar);
        this.withAlcohol = withAlcohol;
    }

    public boolean isWithAlcohol() {
        return withAlcohol;
    }

    public void setWithAlcohol(boolean withAlcohol) {
        this.withAlcohol = withAlcohol;
    }

    @Override
    public String toString() {
        return "BarDish " +
                "\n dish № : " + id +
                "\n Dish name : " + name +
                "\n With alcohol : " + withAlcohol +
                "\n Cost : " + cost +
                "\n";
    }

}
