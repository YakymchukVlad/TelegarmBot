package com.khnu.yakymchuk.dao;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.GlobalSecondaryIndex;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.Projection;
import com.amazonaws.services.dynamodbv2.model.ProjectionType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class BaseDao<T> {

    private AmazonDynamoDB amazonDynamoDB;

    public BaseDao(AmazonDynamoDB amazonDynamoDB) {
        this.amazonDynamoDB = amazonDynamoDB;
    }

    protected void createTable(String tableName, String attributeDefinitionName, ScalarAttributeType attributeType) {

        AttributeDefinition idAttributeDefinition = new AttributeDefinition()
                .withAttributeName(attributeDefinitionName)
                .withAttributeType(attributeType);

        KeySchemaElement hashKeySchema = new KeySchemaElement()
                .withAttributeName(attributeDefinitionName)
                .withKeyType(KeyType.HASH);

        ProvisionedThroughput provisionedThroughput = new ProvisionedThroughput(1L, 1L);

        List<AttributeDefinition> attributeDefinitions = Collections.singletonList(idAttributeDefinition);
        List<KeySchemaElement> keySchemaElements = Collections.singletonList(hashKeySchema);

        if (!isTableExists(tableName)) {
            amazonDynamoDB.createTable(attributeDefinitions, tableName, keySchemaElements, provisionedThroughput);
        }
    }

    private boolean isTableExists(String tableName) {
        return amazonDynamoDB.listTables().getTableNames().contains(tableName);
    }


    protected void createTable(String tableName, String idDefinitionName,
                               ScalarAttributeType attributeType, String globalSecondaryIndexName) {
        AttributeDefinition idDefinition = new AttributeDefinition()
                .withAttributeName(idDefinitionName)
                .withAttributeType(attributeType);

        KeySchemaElement hashKeySchema = new KeySchemaElement()
                .withAttributeName(idDefinitionName)
                .withKeyType(KeyType.HASH);

        KeySchemaElement indexKeySchema = new KeySchemaElement()
                .withAttributeName("tu")
                .withKeyType(KeyType.HASH);


        ProvisionedThroughput provisionedThroughput = new ProvisionedThroughput(1L, 1L);

        Projection projection = new Projection()
                .withProjectionType(ProjectionType.ALL);

        List<AttributeDefinition> attributeDefinitions = Arrays.asList(idDefinition,
                new AttributeDefinition("tu", ScalarAttributeType.S));
        List<KeySchemaElement> keySchemaElements = Arrays.asList(hashKeySchema);

        GlobalSecondaryIndex index = new GlobalSecondaryIndex()
                .withIndexName(globalSecondaryIndexName)
                .withKeySchema(indexKeySchema)
                .withProvisionedThroughput(provisionedThroughput)
                .withProjection(projection);

        CreateTableRequest createTableRequest = new CreateTableRequest().
                withTableName(tableName).
                withKeySchema(keySchemaElements).
                withProvisionedThroughput(provisionedThroughput).
                withAttributeDefinitions(attributeDefinitions).
                withGlobalSecondaryIndexes(index);

        if (!isTableExists(tableName)) {
            amazonDynamoDB.createTable(createTableRequest);
        }
    }

}
