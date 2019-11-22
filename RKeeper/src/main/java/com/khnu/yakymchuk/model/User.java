package com.khnu.yakymchuk.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonProperty;

@DynamoDBTable(tableName = "Users")
public class User {

    @DynamoDBAttribute(attributeName = "tu")
    @DynamoDBHashKey(attributeName = "tu")
    @JsonProperty(value = "tu")
    private String telegramUserId;

    @DynamoDBAttribute(attributeName = "nm")
    @JsonProperty(value = "nm")
    private String name;

    @DynamoDBAttribute(attributeName = "lm")
    @JsonProperty(value = "lm")
    private String lastName;

    @DynamoDBAttribute(attributeName = "rn")
    @JsonProperty(value = "rn")
    private String role;

    public User() {

    }

    public User(String name, String lastName, String telegramUserId, String role) {
        this.telegramUserId = telegramUserId;
        this.name = name;
        this.lastName = lastName;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTelegramUserId() {
        return telegramUserId;
    }

    public void setTelegramUserId(String telegramUserId) {
        this.telegramUserId = telegramUserId;
    }

    @Override
    public String toString() {
        return "User: " + name + " " + lastName + "\n";
    }

}
