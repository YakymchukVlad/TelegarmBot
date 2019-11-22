package com.khnu.yakymchuk.request.impl.waiterRequest;

import com.khnu.yakymchuk.model.Dish;
import com.khnu.yakymchuk.request.IRequest;

import java.util.List;

public class OrderRequest implements IRequest {

    private String tableId;
    private String orderId;
    private String displayName;
    private String waiterId;
    private List<Dish> dishList;

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<Dish> getDishList() {
        return dishList;
    }

    public void setDishList(List<Dish> dishList) {
        this.dishList = dishList;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getWaiterId() {
        return waiterId;
    }

    public void setWaiterId(String waiterId) {
        this.waiterId = waiterId;
    }

    @Override
    public String toString() {
        return "OrderRequest{" +
                "tableId='" + tableId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", dishList=" + dishList +
                ", displayName='" + displayName + '\'' +
                ", waiterId='" + waiterId + '\'' +
                '}';
    }
}
