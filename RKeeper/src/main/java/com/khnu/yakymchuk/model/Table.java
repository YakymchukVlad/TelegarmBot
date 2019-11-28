package com.khnu.yakymchuk.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBConvertedBool;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.khnu.yakymchuk.annotation.NotEmpty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@DynamoDBTable(tableName = "Tables")
public class Table {

    @JsonProperty(value = "id")
    @DynamoDBHashKey(attributeName = "id")
    @NotEmpty
    private String number;

    @JsonProperty(value = "tk")
    @DynamoDBConvertedBool(DynamoDBConvertedBool.Format.true_false)
    @DynamoDBIndexHashKey(attributeName = "tk", globalSecondaryIndexName = "index-on-status")
    @DynamoDBAttribute(attributeName = "tk")
    private boolean taken;

    @JsonProperty(value = "o")
    @DynamoDBAttribute(attributeName = "o")
    @NotEmpty
    private List<Order> orders;

    @JsonProperty(value = "c")
    @DynamoDBAttribute(attributeName = "c")
    private int ordersCount;

    public Table() {

    }

    public Table(String number, boolean taken) {
        this.number = number;
        this.taken = taken;
        this.orders = new ArrayList<>();
        this.ordersCount = orders.size();
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void setOrdersCount(int ordersCount) {
        this.ordersCount = ordersCount;
    }

    public String getNumber() {
        return number;
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isTaken() {
        return taken;
    }

    public void deleteOrder(Order order) {
        orders.remove(order);
    }

    public void deleteAll(List<Order> list) {
        orders.removeAll(list);
        setTaken(false);
    }

    @DynamoDBIgnore
    @JsonIgnore
    public BigDecimal getPaymentAmount() {
        BigDecimal payment = new BigDecimal(0);
        for (Order order : orders) {
            payment = payment.add(order.getPaymentAmount());
        }
        return payment;
    }

    public int getOrdersCount() {
        return orders.size();
    }

    public void setTaken(boolean flag) {
        this.taken = flag;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Table)) {
            return false;
        }
        Table table = (Table) obj;
        return table.getNumber().equals(this.getNumber());
    }

    @Override
    public String toString() {
        return "Table : " +
                "\n Table № : " + number +
                "\n OrdersCount : " + ordersCount +
                "\n";
    }
}
