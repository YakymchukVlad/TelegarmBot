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
    @StringKey("Show menu")
    @Provides
    @Singleton
    IRequestBuilder showMenuRequestBuilder() {
        return new ShowMenuRequestBuilder();
    }

    @IntoMap
    @StringKey("Show active tables")
    @Provides
    @Singleton
    IRequestBuilder showActiveTablesRequestBuilder() {
        return new ShowActiveTablesRequestBuilder();
    }

    @IntoMap
    @StringKey("Show free tables")
    @Provides
    @Singleton
    IRequestBuilder showFreeTablesRequestBuilder() {
        return new ShowFreeTablesRequestBuilder();
    }

    @IntoMap
    @StringKey("Show active orders")
    @Provides
    @Singleton
    IRequestBuilder ShowActiveOrdersRequestBuilder() {
        return new ShowActiveOrdersRequestBuilder();
    }

    @IntoMap
    @StringKey("Make order")
    @Provides
    @Singleton
    IRequestBuilder makeOrderRequestBuilder(IMenuService menuService, ITableService tableService) {
        return new MakeOrderRequestBuilder(menuService, tableService);
    }

    @IntoMap
    @StringKey("Delete order")
    @Provides
    @Singleton
    IRequestBuilder deleteOrderRequestBuilder(ITableService tableService) {
        return new DeleteOrderRequestBuilder(tableService);
    }

    @IntoMap
    @StringKey("Make payment")
    @Provides
    @Singleton
    IRequestBuilder makePaymentRequestBuilder(ITableService tableService) {
        return new MakePaymentRequestBuilder(tableService);
    }

    @IntoMap
    @StringKey("Show daily orders")
    @Provides
    @Singleton
    IRequestBuilder showDailyOrdersBuilder() {
        return new ShowDailyOrdersRequestBuilder();
    }

    @IntoMap
    @StringKey("Make discount")
    @Provides
    @Singleton
    IRequestBuilder makeDiscountRequestBuilder(ITableService tableService) {
        return new MakeDiscountRequestBuilder(tableService);
    }

    @IntoMap
    @StringKey("AddUser")
    @Provides
    @Singleton
    IRequestBuilder addUserRequestBuilder() {
        return new AddUserRequestBuilder();
    }

    @IntoMap
    @StringKey("DeleteUser")
    @Provides
    @Singleton
    IRequestBuilder deleteUserRequestBuilder(IUserService userService) {
        return new DeleteUserRequestBuilder(userService);
    }

}
