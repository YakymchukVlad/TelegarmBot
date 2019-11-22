package com.khnu.yakymchuk.request.impl.waiterRequest;

import com.khnu.yakymchuk.request.IRequest;

public class PaymentRequest implements IRequest {

    private String tableId;
    private boolean paymentForWholeTable;
    private String displayName;

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean isPaymentForWholeTable() {
        return paymentForWholeTable;
    }

    public void setPaymentForWholeTable(boolean paymentForWholeTable) {
        this.paymentForWholeTable = paymentForWholeTable;
    }
}
