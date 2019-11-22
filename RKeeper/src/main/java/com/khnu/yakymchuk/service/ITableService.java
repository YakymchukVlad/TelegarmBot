package com.khnu.yakymchuk.service;

import com.khnu.yakymchuk.model.Dish;
import com.khnu.yakymchuk.model.Order;
import com.khnu.yakymchuk.model.Table;

import java.util.List;

public interface ITableService {

    void deleteOrder(String tableID, Order order);

    Table getTableById(String id);

    Order getOrderByDisplayName(String tableId, String displayName);

    void makeOrder(String tableNumber, List<Dish> dishList, String displayName);

//    void makeDiscountForCurrentOrder(String tableId, String displayName, int perc);

//    void makePayment(String tableID, String displayName);

    void makePaymentForAllTable(String idTable);

    void makeDiscountForWholeTable(String tableID, int percent);

    List<Table> getFreeTables();

    List<Table> getActiveTables();

    List<Table> getAllTables();

    void addTable(Table table);

}
