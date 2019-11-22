package com.khnu.yakymchuk.request.impl.waiterRequest;

import com.khnu.yakymchuk.request.IRequest;

public class DiscountRequest implements IRequest {

    private String tableId;
    private boolean discountForWholeTable;
    private int percent;
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

    public boolean isDiscountForWholeTable() {
        return discountForWholeTable;
    }

    public void setDiscountForWholeTable(boolean discountForWholeTable) {
        this.discountForWholeTable = discountForWholeTable;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public int getPercent() {
        return percent;
    }
}
