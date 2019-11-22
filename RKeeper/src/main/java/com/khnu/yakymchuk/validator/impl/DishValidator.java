package com.khnu.yakymchuk.validator.impl;

import com.khnu.yakymchuk.annotation.NotEmpty;
import com.khnu.yakymchuk.annotation.NotNull;
import com.khnu.yakymchuk.annotation.Range;
import com.khnu.yakymchuk.model.Dish;
import com.khnu.yakymchuk.validator.IValidator;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DishValidator implements IValidator<Dish> {

    public void validate(Dish dish) {

        Class<? extends Dish> dishClass = dish.getClass();
        Map<String, String> errorMap = new HashMap<>();

        for (Field field : dishClass.getSuperclass().getDeclaredFields()) {
            field.setAccessible(true);

            NotNull notNullAnnotation = field.getAnnotation(NotNull.class);
            if (notNullAnnotation != null) {
                Object object = null;
                try {
                    object = field.get(dish);
                } catch (IllegalAccessException e) {
                    e.getMessage();
                }
                if (object == null) {
                    errorMap.put(field.getName(), "Null value is not allowed!");
                }
            }

            NotEmpty notEmptyAnnotation = field.getAnnotation(NotEmpty.class);
            if (notEmptyAnnotation != null) {
                Class<?> type = field.getType();
                if (type == String.class || type == List.class) {
                    String value = null;
                    try {
                        value = (String) field.get(dish);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    if (value == null || value.isEmpty()) {
                        errorMap.put(field.getName(), "null or empty value is not allowed");
                    }
                } else {
                    throw new RuntimeException("Not empty annotation should be used with String or List fields");
                }
            }

            Range annotation = field.getAnnotation(Range.class);
            if (annotation != null) {
                Class<?> type = field.getType();
                if (type != BigDecimal.class) {
                    throw new RuntimeException("The range annotation should be used with BigDecimal fields");
                } else {
                    BigDecimal value = null;
                    try {
                        value = (BigDecimal) field.get(dish);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    BigDecimal min = new BigDecimal(annotation.min());
                    BigDecimal max = new BigDecimal(annotation.maxVal());

                    if (min.compareTo(value) > 0) {
                        errorMap.put(field.getName(), "The value should be greater than min value");
                    }
                    if (max.compareTo(value) < 0) {
                        errorMap.put(field.getName(), "The value should be less than max value");
                    }
                }
            }
        }
        if (!errorMap.isEmpty()) {
            throw new IllegalArgumentException(errorMap.entrySet().stream()
                    .map(entry -> "Field '" + entry.getKey() + "' is invalid: " + entry.getValue() + "; ")
                    .collect(Collectors.joining()));
        }
    }
}
