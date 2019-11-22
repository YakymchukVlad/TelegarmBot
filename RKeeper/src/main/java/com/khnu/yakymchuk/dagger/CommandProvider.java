package com.khnu.yakymchuk.dagger;

import com.khnu.yakymchuk.command.IActionCommand;
import com.khnu.yakymchuk.command.impl.adminCommands.AddUserCommand;
import com.khnu.yakymchuk.command.impl.adminCommands.DeleteUserCommand;
import com.khnu.yakymchuk.command.impl.waiterCommands.DeleteOrderCommand;
import com.khnu.yakymchuk.command.impl.waiterCommands.MakeDiscountCommand;
import com.khnu.yakymchuk.command.impl.waiterCommands.MakeOrderCommand;
import com.khnu.yakymchuk.command.impl.waiterCommands.MakePaymentCommand;
import com.khnu.yakymchuk.command.impl.waiterCommands.ShowActiveOrdersCommand;
import com.khnu.yakymchuk.command.impl.waiterCommands.ShowActiveTablesCommand;
import com.khnu.yakymchuk.command.impl.waiterCommands.ShowDailyOrdersCommand;
import com.khnu.yakymchuk.command.impl.waiterCommands.ShowFreeTablesCommand;
import com.khnu.yakymchuk.command.impl.waiterCommands.ShowMenuCommand;
import com.khnu.yakymchuk.service.IMenuService;
import com.khnu.yakymchuk.service.IOrderService;
import com.khnu.yakymchuk.service.ITableService;
import com.khnu.yakymchuk.service.ITokenService;
import com.khnu.yakymchuk.service.IUserService;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;

import javax.inject.Singleton;

@Module(includes = {
        ServiceProvider.class,
        ExceptionHandlerProvider.class
})
public class CommandProvider {

    @IntoMap
    @Singleton
    @StringKey("Show menu")
    @Provides
    IActionCommand showMenuCommand(IMenuService menuService) {
        return new ShowMenuCommand(menuService);
    }

    @IntoMap
    @Singleton
    @StringKey("Show active tables")
    @Provides
    IActionCommand showActiveTablesCommand(ITableService tableService) {
        return new ShowActiveTablesCommand(tableService);
    }

    @IntoMap
    @Singleton
    @StringKey("Show free tables")
    @Provides
    IActionCommand showFreeTablesCommand(ITableService tableService) {
        return new ShowFreeTablesCommand(tableService);
    }

    @IntoMap
    @Singleton
    @StringKey("Show active orders")
    @Provides
    IActionCommand showActiveOrdersCommand(IOrderService orderService) {
        return new ShowActiveOrdersCommand(orderService);
    }

    @IntoMap
    @Singleton
    @StringKey("Make order")
    @Provides
    IActionCommand makeOrderCommand(ITableService tableService) {
        return new MakeOrderCommand(tableService);
    }

    @IntoMap
    @Singleton
    @StringKey("Delete order")
    @Provides
    IActionCommand deleteOrderCommand(ITableService tableService, IOrderService orderService) {
        return new DeleteOrderCommand(tableService, orderService);
    }

    @IntoMap
    @Singleton
    @StringKey("Make payment")
    @Provides
    IActionCommand makePaymentCommand(ITableService tableService) {
        return new MakePaymentCommand(tableService);
    }

    @IntoMap
    @Singleton
    @StringKey("Show daily orders")
    @Provides
    IActionCommand showDailyOrdersCommand(IOrderService orderService) {
        return new ShowDailyOrdersCommand(orderService);
    }

    @IntoMap
    @Singleton
    @StringKey("Make discount")
    @Provides
    IActionCommand makeDiscountCommand(ITableService tableService) {
        return new MakeDiscountCommand(tableService);
    }


    @IntoMap
    @Singleton
    @StringKey("AddUser")
    @Provides
    IActionCommand addUserCommand(ITokenService tokenService) {
        return new AddUserCommand(tokenService);
    }


    @IntoMap
    @Singleton
    @StringKey("DeleteUser")
    @Provides
    IActionCommand deleteUserCommand(IUserService userService) {
        return new DeleteUserCommand(userService);
    }


}
