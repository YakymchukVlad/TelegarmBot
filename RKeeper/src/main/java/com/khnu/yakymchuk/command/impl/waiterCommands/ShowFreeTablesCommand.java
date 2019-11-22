package com.khnu.yakymchuk.command.impl.waiterCommands;

import com.khnu.yakymchuk.command.IActionCommand;
import com.khnu.yakymchuk.model.Table;
import com.khnu.yakymchuk.request.IRequest;
import com.khnu.yakymchuk.service.ITableService;

import java.util.List;
import java.util.stream.Collectors;

public class ShowFreeTablesCommand implements IActionCommand {

    private ITableService tableService;

    public ShowFreeTablesCommand(ITableService tableService) {
        this.tableService = tableService;
    }

    @Override
    public String execute(IRequest request) {
        return getFreeTables();
    }

    private String getFreeTables() {
        List<Table> tableList = tableService.getFreeTables();
        if (tableList.isEmpty()) {
            return "There are no free tables";
        } else {
            return tableList.
                    stream().
                    map(Table::toString).
                    collect(Collectors.joining("\n"));
        }
    }

}
