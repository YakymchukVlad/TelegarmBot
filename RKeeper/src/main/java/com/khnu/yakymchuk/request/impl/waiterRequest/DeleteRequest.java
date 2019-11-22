package com.khnu.yakymchuk.request.impl.waiterRequest;

import com.khnu.yakymchuk.request.IRequest;

public class DeleteRequest implements IRequest {

    private String tableId;
    private String orderId;
    private String displayName;

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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
