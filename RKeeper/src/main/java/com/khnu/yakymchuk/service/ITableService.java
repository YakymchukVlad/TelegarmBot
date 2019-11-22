package com.khnu.yakymchuk.service;

import com.khnu.yakymchuk.model.Dish;
import com.khnu.yakymchuk.model.Order;
import com.khnu.yakymchuk.model.Table;

import java.util.List;

public interface ITableService {

    void deleteOrder(Order order);

    void makeOrder(String tableNumber, List<Dish> dishList, String displayName, String waiterId);

    void makePaymentForAllTable(String idTable);

    void makeDiscountForWholeTable(String tableID, int percent);

    void addTable(Table table);

    Table getTableById(String id);

    Order getOrderByDisplayName(String tableId, String displayName);

    List<Table> getFreeTables();

    List<Table> getActiveTables();

    List<Table> getAllTables();
}
