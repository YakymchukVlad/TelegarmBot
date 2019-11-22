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
        super(id, name, cost, DishType.BAR);
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
        return "BarDish{" +
                "withAlcohol=" + withAlcohol +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", dishType=" + dishType +
                '}';
    }
}
