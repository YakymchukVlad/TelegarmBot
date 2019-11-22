package com.khnu.yakymchuk.dao.impl;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.khnu.yakymchuk.dao.BaseDao;
import com.khnu.yakymchuk.dao.ITableDao;
import com.khnu.yakymchuk.model.Order;
import com.khnu.yakymchuk.model.Table;
import com.khnu.yakymchuk.utils.assertion.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TableDynamoDao extends BaseDao implements ITableDao {

    private static final Logger LOG = LoggerFactory.getLogger(TableDynamoDao.class);

    private static final String INDEX_NAME = "index-on-status";

    private DynamoDBMapper dynamoDBMapper;

    public TableDynamoDao(AmazonDynamoDB amazonDynamoDB, DynamoDBMapper dynamoDBMapper) {
        super(amazonDynamoDB);
        this.dynamoDBMapper = dynamoDBMapper;
    }

    @Override
    public void addActiveTable(Table table) {
        Assert.asserNotNull(table, "Table cannot be null");

        Table t = new Table();
        t.setNumber(table.getNumber());
        t.setTaken(true);
        t.setOrders(table.getOrders());
        t.setOrdersCount(table.getOrdersCount());

        dynamoDBMapper.save(t);
    }


    @Override
    public void deleteOrder(Order order) {
        Assert.asserNotNull(order, "Order cannot be null");

        String tableID = order.getTableId();
        Table table = findTableById(tableID);

        table.deleteOrder(order);
        LOG.debug("Order deleted from the table");
        if (table.getOrders().size() == 0) {
            table.setTaken(false);
            LOG.debug("The list of orders in table with id : ", tableID + " is empty");
        }
        dynamoDBMapper.save(table);
    }

    @Override
    public Table findTableById(String id) {
        Assert.asserHasText(id, "Id cannot be null or empty");

        Table table = new Table();
        table.setNumber(id);
        return dynamoDBMapper.load(table);
    }

    @Override
    public List<Table> getFreeTables() {
        return getTablesByStatus(false);
    }

    @Override
    public List<Table> getActiveTables() {
        return getTablesByStatus(true);
    }

    @Override
    public List<Table> getTables() {
        DynamoDBScanExpression dynamoDBScanExpression = new DynamoDBScanExpression();
        List<Table> tableList = new ArrayList<>(dynamoDBMapper.scan(Table.class, dynamoDBScanExpression));
        tableList.sort(Comparator.comparing(t -> Integer.valueOf(t.getNumber())));
        return tableList;
    }

    @Override
    public void makeDiscountForWholeTable(String tableID, int percent) {
        Assert.asserHasText(tableID, "Table id cannot be null or empty");

        Table table = findTableById(tableID);
        table.getOrders().forEach(order -> order.makeDiscount(percent));
        dynamoDBMapper.save(table);
    }

    @Override
    public void addTable(Table table) {
        dynamoDBMapper.save(table);
    }

    private List<Table> getTablesByStatus(boolean status) {
        Table table = new Table();
        table.setTaken(status);

        DynamoDBQueryExpression<Table> indexQueryExpression = new DynamoDBQueryExpression<Table>()
                .withIndexName(INDEX_NAME)
                .withHashKeyValues(table)
                .withConsistentRead(false);

        List<Table> tableList = new ArrayList<>(dynamoDBMapper.query(Table.class, indexQueryExpression));

        tableList.sort(Comparator.comparing(t -> Integer.valueOf(t.getNumber())));
        return tableList;
    }

}
