package com.khnu.yakymchuk.service.impl;

import com.khnu.yakymchuk.dao.ITableDao;
import com.khnu.yakymchuk.exception.FreeTablesNotFoundException;
import com.khnu.yakymchuk.exception.OrderNotFoundException;
import com.khnu.yakymchuk.exception.TableNotFoundException;
import com.khnu.yakymchuk.model.Dish;
import com.khnu.yakymchuk.model.Order;
import com.khnu.yakymchuk.model.Table;
import com.khnu.yakymchuk.service.IOrderService;
import com.khnu.yakymchuk.service.ITableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

public class TableService implements ITableService {

    private static final Logger LOG = LoggerFactory.getLogger(TableService.class);

    private ITableDao tableDao;
    private IOrderService orderService;

    public TableService(ITableDao tableDAO, IOrderService orderService) {
        this.orderService = orderService;
        this.tableDao = tableDAO;
    }

    @Override
    public void deleteOrder(String tableId, Order order) {
        LOG.info("Method starts with parameters : tableId = {} , order = {}", tableId, order);
        tableDao.deleteOrder(tableId, order);
    }

    @Override
    public Table getTableById(String id) {
        return tableDao.findTableById(id);
    }

    @Override
    public Order getOrderByDisplayName(String tableID, String displayName) {
        Table table = tableDao.findTableById(tableID);
        return table.getOrders().stream().filter(order -> order.getDisplayName().equals(displayName)).
                findFirst().orElseThrow(() -> new OrderNotFoundException("Cannot find order with number " + displayName));
    }

    @Override
    public void makeOrder(String tableID, List<Dish> list, String displayName) {
        Table table = getTableById(tableID);
        LOG.info("Get the table by ID : {}", table);
        if (table == null) {
            throw new TableNotFoundException(String.format("Cannot find table with number %s", tableID));
        } else {
            String id = UUID.randomUUID().toString();
            int count = table.getOrdersCount();
            Order order = Order.openOrder(id, list, tableID, "Order№" + (++count));

            table.addOrder(order);
            orderService.addOrder(order);
            tableDao.addActiveTable(table);
        }
    }

    @Override
    public void makeDiscountForWholeTable(String tableID, int percent) {
        LOG.info("Make discount for table with parameters: tableID = {},percent = {}", tableID, percent);
        tableDao.makeDiscountForWholeTable(tableID, percent);
    }

    @Override
    public void makePaymentForAllTable(String idTable) {
        LOG.info("Making payment fro all table with parameters : table id = {}", idTable);
        Table table = tableDao.findTableById(idTable);
        if (table == null) {
            throw new TableNotFoundException(String.format("There is no table with number : %s ", idTable));
        }
        List<Order> orders = table.getOrders();
        for (Order order : orders) {
            orderService.addToDailyOrders(order);
            tableDao.deleteOrder(idTable, order);
        }
    }

    @Override
    public List<Table> getFreeTables() {
        if (tableDao.getFreeTables().isEmpty()) {
            throw new FreeTablesNotFoundException("There are no any free tables");
        }
        return tableDao.getFreeTables();
    }

    @Override
    public List<Table> getActiveTables() {
        return tableDao.getActiveTables();
    }


    @Override
    public List<Table> getAllTables() {
        return tableDao.getTables();
    }

    @Override
    public void addTable(Table table) {
        tableDao.addTable(table);
    }
}
