package com.khnu.yakymchuk.converter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.khnu.yakymchuk.exception.UserReadableException;
import com.khnu.yakymchuk.model.Dish;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class DishConverter implements DynamoDBTypeConverter<String, List<Dish>> {

    @Override
    public String convert(List<Dish> object) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new UserReadableException(e);
        }
    }

    @Override
    public List<Dish> unconvert(String objectsString) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(objectsString, new TypeReference<List<Dish>>() {
            });
        } catch (IOException e) {
            throw new UserReadableException(e);
        }
    }

}
