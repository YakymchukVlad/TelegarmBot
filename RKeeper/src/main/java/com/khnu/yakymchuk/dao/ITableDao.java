package com.khnu.yakymchuk.dao;

import com.khnu.yakymchuk.model.Order;
import com.khnu.yakymchuk.model.Table;

import java.util.List;

public interface ITableDao {

    void addActiveTable(Table table);

    void deleteOrder(String tableID, Order order);

    Table findTableById(String id);

    List<Table> getFreeTables();

    List<Table> getActiveTables();

    List<Table> getTables();

    void makeDiscountForWholeTable(String tableID, int percent);

    void addTable(Table table);

}
