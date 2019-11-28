package com.khnu.yakymchuk.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;
import com.khnu.yakymchuk.annotation.NotEmpty;
import com.khnu.yakymchuk.annotation.NotNull;
import com.khnu.yakymchuk.annotation.Range;
import com.khnu.yakymchuk.enums.DishType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "dt",
        visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = KitchenDish.class, name = "KITCHEN"),
        @JsonSubTypes.Type(value = BarDish.class, name = "BAR"),
}
)
@DynamoDBTable(tableName = "Dishes")
public class Dish {

    @DynamoDBHashKey(attributeName = "id")
    @JsonProperty(value = "id")
    @NotEmpty
    protected String id;

    @JsonProperty(value = "nm")
    @DynamoDBAttribute(attributeName = "nm")
    @NotEmpty
    protected String name;

    @JsonProperty(value = "cs")
    @DynamoDBAttribute(attributeName = "cs")
    @NotNull
    @Range(maxVal = 350)
    protected BigDecimal cost;

    @JsonProperty(value = "dt")
    @DynamoDBTypeConvertedEnum
    @DynamoDBAttribute(attributeName = "dt")
    protected DishType dishType;

    public Dish() {

    }

    protected Dish(String id, String name, BigDecimal cost, DishType dishType) {
        this.name = name;
        this.cost = cost;
        this.id = id;
        this.dishType = dishType;
    }

    public DishType getDishType() {
        return dishType;
    }

    public void setDishType(DishType dishType) {
        this.dishType = dishType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Dish)) {
            return false;
        }
        Dish dish = (Dish) obj;
        return dish.getId().equals(this.getId())
                && (dish.getName().equals(this.getName())
                && (dish.getCost().equals(this.getCost())));
    }

    @Override
    public String toString() {
        return "Dish " +
                "\n id : " + id +
                "\n name : " + name +
                "\n cost : " + cost +
                "\n dishType : " + dishType +
                "\n";
    }
}
