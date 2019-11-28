package com.khnu.yakymchuk.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBConvertedBool;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.khnu.yakymchuk.annotation.NotEmpty;
import com.khnu.yakymchuk.annotation.NotNull;
import com.khnu.yakymchuk.converter.DishConverter;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@DynamoDBTable(tableName = "Orders")
public class Order {

    @JsonProperty(value = "id")
    @DynamoDBHashKey(attributeName = "id")
    @NotEmpty
    private String number;

    @DynamoDBTypeConverted(converter = DishConverter.class)
    @JsonProperty(value = "dl")
    @DynamoDBAttribute(attributeName = "dl")
    @NotEmpty
    private List<Dish> dishList;

    @JsonProperty(value = "ti")
    @DynamoDBAttribute(attributeName = "ti")
    @NotEmpty
    private String tableId;

    @JsonProperty(value = "pa")
    @DynamoDBAttribute(attributeName = "pa")
    @NotNull
    private BigDecimal paymentAmount;

    @JsonProperty(value = "tb")
    @DynamoDBAttribute(attributeName = "tb")
    private long timeBegin;

    @JsonProperty(value = "bl")
    @DynamoDBConvertedBool(DynamoDBConvertedBool.Format.true_false)
    @DynamoDBIndexHashKey(attributeName = "bl", globalSecondaryIndexName = "index-on-status")
    @DynamoDBAttribute(attributeName = "bl")
    private boolean payed;

    @JsonProperty(value = "dn")
    @DynamoDBAttribute(attributeName = "dn")
    private String displayName;

    @JsonProperty(value = "wi")
    @DynamoDBAttribute(attributeName = "wi")
    private String waiterId;

    public Order() {

    }

    public Order(List<Dish> dishList, String number, String tableId, long timeBeg, boolean payed, String displayName, String waiterId) {
        this.dishList = dishList;
        this.number = number;
        this.tableId = tableId;
        this.timeBegin = timeBeg;
        this.paymentAmount = new BigDecimal(0);
        this.payed = false;
        this.displayName = displayName;
        this.waiterId = waiterId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    public List<Dish> getDishList() {
        return dishList;
    }

    public void setDishList(List<Dish> dishList) {
        this.dishList = dishList;
    }

    public String getTableId() {
        return tableId;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public long getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(long timeBegin) {
        this.timeBegin = timeBegin;
    }

    public String getNumber() {
        return number;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getWaiterId() {
        return waiterId;
    }

    public void makeDiscount(int percent) {
        for (Dish dish : dishList) {
            dish.setCost(dish.getCost().subtract(dish.getCost().divide(BigDecimal.valueOf(100)).multiply(BigDecimal.valueOf(percent))));
        }
    }

    @JsonIgnore
    @DynamoDBIgnore
    public BigDecimal getPaymentAmount() {
        BigDecimal res = new BigDecimal(0);
        for (Dish dish : dishList) {
            res = res.add(dish.getCost());
        }
        this.paymentAmount = res;
        return res;
    }

    @Override
    public String toString() {
        return "Order : "
                + displayName + "'" +
                "\nDishes in order : " +
                "\n" + dishList.stream().map(Dish::getName).collect(Collectors.joining("\n")) +
                "\nTable № : " + tableId +
                "\n";
    }

    public static Order openOrder(String id, List<Dish> dishList, String tableId, String displayName, String waiterId) {
        return new Order(dishList, id, tableId, System.currentTimeMillis(), false, displayName, waiterId);
    }

    public static Order closeOrder(String id, List<Dish> dishList, String tableId, String displayName, String waiterId) {
        return new Order(dishList, id, tableId, System.currentTimeMillis(), true, displayName, waiterId);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Order)) {
            return false;
        }
        Order order = (Order) obj;
        return (this.getNumber().equals(order.getNumber())
                && (this.getTableId().equals(order.getTableId()))
        );
    }

}
