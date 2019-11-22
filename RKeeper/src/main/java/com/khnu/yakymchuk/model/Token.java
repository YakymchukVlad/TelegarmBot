package com.khnu.yakymchuk.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonProperty;

@DynamoDBTable(tableName = "Token")
public class Token {

    @JsonProperty(value = "tu")
    @DynamoDBAttribute(attributeName = "tu")
    @DynamoDBHashKey(attributeName = "tu")
    private String token;

//    @JsonProperty(value = "ttl")
//    @DynamoDBAttribute(attributeName = "ttl")
//    private long timeToLive;

    public Token() {

    }

    public Token(String tokenNumber, long timeToLive) {
        this.token = tokenNumber;
        ///   this.timeToLive = timeToLive;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

//    public long getTimeToLive() {
//        return timeToLive;
//    }
//
//    public void setTimeToLive(long timeToLive) {
//        this.timeToLive = timeToLive;
//    }

    @Override
    public String toString() {
        return "Token{" +
                "token='" + token + '\'';
//                ", timeToLive=" + timeToLive +
//                '}';
    }
}
