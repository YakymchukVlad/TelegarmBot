package com.khnu.yakymchuk.dagger;

import com.khnu.yakymchuk.builder.IRequestBuilder;
import com.khnu.yakymchuk.builder.impl.adminRequestBuilder.AddUserRequestBuilder;
import com.khnu.yakymchuk.builder.impl.adminRequestBuilder.DeleteUserRequestBuilder;
import com.khnu.yakymchuk.builder.impl.waiterRequestBuilder.DeleteOrderRequestBuilder;
import com.khnu.yakymchuk.builder.impl.waiterRequestBuilder.MakeDiscountRequestBuilder;
import com.khnu.yakymchuk.builder.impl.waiterRequestBuilder.MakeOrderRequestBuilder;
import com.khnu.yakymchuk.builder.impl.waiterRequestBuilder.MakePaymentRequestBuilder;
import com.khnu.yakymchuk.builder.impl.waiterRequestBuilder.ShowActiveOrdersRequestBuilder;
import com.khnu.yakymchuk.builder.impl.waiterRequestBuilder.ShowActiveTablesRequestBuilder;
import com.khnu.yakymchuk.builder.impl.waiterRequestBuilder.ShowDailyOrdersRequestBuilder;
import com.khnu.yakymchuk.builder.impl.waiterRequestBuilder.ShowFreeTablesRequestBuilder;
import com.khnu.yakymchuk.builder.impl.waiterRequestBuilder.ShowMenuRequestBuilder;
import com.khnu.yakymchuk.constant.RkeperConstants;
import com.khnu.yakymchuk.service.IMenuService;
import com.khnu.yakymchuk.service.ITableService;
import com.khnu.yakymchuk.service.IUserService;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;

import javax.inject.Singleton;

@Module(includes =
        RequestProvider.class
)
public class BuilderProvider {

    @IntoMap
    @StringKey(RkeperConstants.SHOW_MENU_COMMAND_NAME)
    @Provides
    @Singleton
    IRequestBuilder showMenuRequestBuilder() {
        return new ShowMenuRequestBuilder();
    }

    @IntoMap
    @StringKey(RkeperConstants.SHOW_ACTIVE_TABLES_COMMAND_NAME)
    @Provides
    @Singleton
    IRequestBuilder showActiveTablesRequestBuilder() {
        return new ShowActiveTablesRequestBuilder();
    }

    @IntoMap
    @StringKey(RkeperConstants.SHOW_FREE_TABLES_COMMAND_NAME)
    @Provides
    @Singleton
    IRequestBuilder showFreeTablesRequestBuilder() {
        return new ShowFreeTablesRequestBuilder();
    }

    @IntoMap
    @StringKey(RkeperConstants.SHOW_ACTIVE_ORDERS_COMMAND_NAME)
    @Provides
    @Singleton
    IRequestBuilder ShowActiveOrdersRequestBuilder() {
        return new ShowActiveOrdersRequestBuilder();
    }

    @IntoMap
    @StringKey(RkeperConstants.MAKE_ORDER_COMMAND_NAME)
    @Provides
    @Singleton
    IRequestBuilder makeOrderRequestBuilder(IMenuService menuService, ITableService tableService) {
        return new MakeOrderRequestBuilder(menuService, tableService);
    }

    @IntoMap
    @StringKey(RkeperConstants.DELETE_ORDER_COMMAND_NAME)
    @Provides
    @Singleton
    IRequestBuilder deleteOrderRequestBuilder(ITableService tableService) {
        return new DeleteOrderRequestBuilder(tableService);
    }

    @IntoMap
    @StringKey(RkeperConstants.MAKE_PAYMENT_COMMAND_NAME)
    @Provides
    @Singleton
    IRequestBuilder makePaymentRequestBuilder(ITableService tableService) {
        return new MakePaymentRequestBuilder(tableService);
    }

    @IntoMap
    @StringKey(RkeperConstants.SHOW_DAILY_ORDERS_COMMAND_NAME)
    @Provides
    @Singleton
    IRequestBuilder showDailyOrdersBuilder() {
        return new ShowDailyOrdersRequestBuilder();
    }

    @IntoMap
    @StringKey(RkeperConstants.MAKE_DISCOUNT_COMMAND_NAME)
    @Provides
    @Singleton
    IRequestBuilder makeDiscountRequestBuilder(ITableService tableService) {
        return new MakeDiscountRequestBuilder(tableService);
    }

    @IntoMap
    @StringKey(RkeperConstants.ADD_USER_COMMAND_NAME)
    @Provides
    @Singleton
    IRequestBuilder addUserRequestBuilder() {
        return new AddUserRequestBuilder();
    }

    @IntoMap
    @StringKey(RkeperConstants.DELETE_USER_COMMAND_NAME)
    @Provides
    @Singleton
    IRequestBuilder deleteUserRequestBuilder(IUserService userService) {
        return new DeleteUserRequestBuilder(userService);
    }

}
