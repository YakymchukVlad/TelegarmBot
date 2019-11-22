package com.khnu.yakymchuk.command.impl.waiterCommands;

import com.khnu.yakymchuk.command.IActionCommand;
import com.khnu.yakymchuk.model.Dish;
import com.khnu.yakymchuk.request.IRequest;
import com.khnu.yakymchuk.service.IMenuService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ShowMenuCommand implements IActionCommand {

    private IMenuService menuService;

    public ShowMenuCommand(IMenuService menuService) {
        this.menuService = menuService;
    }

    @Override
    public String execute(IRequest request) {
        return showMenu();
    }

    private String showMenu() {
        List<Dish> dishList = menuService.getMenu();
        dishList.sort(Comparator.comparing(d -> Integer.valueOf(d.getId())));
        return dishList.
                stream().
                map(Dish::toString).
                collect(Collectors.joining("\n"));
    }

}
