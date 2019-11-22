package com.khnu.yakymchuk.enums;

import com.khnu.yakymchuk.model.BarDish;
import com.khnu.yakymchuk.model.Dish;
import com.khnu.yakymchuk.model.KitchenDish;

import java.util.function.Supplier;

public enum DishType {

    kitchen(KitchenDish::new),
    bar(BarDish::new);

    private Supplier<? extends Dish> entitySupplier;

    DishType(Supplier<? extends Dish> entitySupplier) {
        this.entitySupplier = entitySupplier;
    }

    public Supplier<? extends Dish> getEntitySupplier() {
        return entitySupplier;
    }

}
