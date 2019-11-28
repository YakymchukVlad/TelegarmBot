package com.khnu.yakymchuk.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.khnu.yakymchuk.model.BarDish;
import com.khnu.yakymchuk.model.Dish;
import com.khnu.yakymchuk.model.KitchenDish;

import java.util.function.Supplier;

public enum DishType {

    KITCHEN("Kitchen", KitchenDish::new),
    BAR("Bar", BarDish::new);

    private String name;
    private Supplier<? extends Dish> entitySupplier;

    DishType(String name, Supplier<? extends Dish> entitySupplier) {
        this.entitySupplier = entitySupplier;
        this.name = name;
    }

    public Supplier<? extends Dish> getEntitySupplier() {
        return entitySupplier;
    }

    public String getName(){
        return name;
    }

    @JsonCreator
    public static DishType fromValue(String dishType){
        for (DishType type : values()){
            if (type.getName().equalsIgnoreCase(dishType)){
                return type;
            }
        }
        throw new IllegalArgumentException("Could not extract instance of dish type");
    }
}
