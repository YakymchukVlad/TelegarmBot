package com.khnu.yakymchuk.command.impl.waiterCommands;

import com.khnu.yakymchuk.command.IActionCommand;
import com.khnu.yakymchuk.model.Table;
import com.khnu.yakymchuk.request.IRequest;
import com.khnu.yakymchuk.service.ITableService;

import java.util.List;
import java.util.stream.Collectors;

public class ShowActiveTablesCommand implements IActionCommand {

    private ITableService tableService;

    public ShowActiveTablesCommand(ITableService tableService) {
        this.tableService = tableService;
    }

    @Override
    public String execute(IRequest request) {
        return showActiveTables();
    }

    private String showActiveTables() {
        List<Table> tableList = tableService.getActiveTables();
        if (tableList.isEmpty()) {
            return "There are no active tables";
        } else {
            return tableList.
                    stream().
                    map(Table::toString).
                    collect(Collectors.joining("\n"));
        }
    }
}
