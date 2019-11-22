package com.khnu.yakymchuk.dagger;

import com.khnu.yakymchuk.dao.IMenuDao;
import com.khnu.yakymchuk.dao.IOrderDao;
import com.khnu.yakymchuk.dao.ITableDao;
import com.khnu.yakymchuk.dao.ITokenDao;
import com.khnu.yakymchuk.dao.IUserDao;
import com.khnu.yakymchuk.service.IMenuService;
import com.khnu.yakymchuk.service.IOrderService;
import com.khnu.yakymchuk.service.ITableService;
import com.khnu.yakymchuk.service.ITokenService;
import com.khnu.yakymchuk.service.IUserService;
import com.khnu.yakymchuk.service.impl.MenuService;
import com.khnu.yakymchuk.service.impl.OrderService;
import com.khnu.yakymchuk.service.impl.TableService;
import com.khnu.yakymchuk.service.impl.TokenService;
import com.khnu.yakymchuk.service.impl.UserService;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module(includes = DynamoDaoProvider.class)
public class ServiceProvider {

    @Provides
    @Singleton
    public ITableService tableService(ITableDao tableDao, IOrderService orderService) {
        return new TableService(tableDao, orderService);
    }

    @Provides
    @Singleton
    public IOrderService orderService(IOrderDao orderDao) {
        return new OrderService(orderDao);
    }

    @Provides
    @Singleton
    public IMenuService menuService(IMenuDao menuDao) {
        return new MenuService(menuDao);
    }

    @Provides
    @Singleton
    public IUserService userService(IUserDao userDao) {
        return new UserService(userDao);
    }

    @Provides
    @Singleton
    public ITokenService tokenService(ITokenDao tokenDao) {
        return new TokenService(tokenDao);
    }

}
