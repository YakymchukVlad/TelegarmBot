package com.khnu.yakymchuk.dagger;

import com.khnu.yakymchuk.request.IRequest;
import com.khnu.yakymchuk.request.impl.waiterRequest.DeleteRequest;
import com.khnu.yakymchuk.request.impl.waiterRequest.DiscountRequest;
import com.khnu.yakymchuk.request.EmptyRequest;
import com.khnu.yakymchuk.request.impl.waiterRequest.OrderRequest;
import com.khnu.yakymchuk.request.impl.waiterRequest.PaymentRequest;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class RequestProvider {

    @Singleton
    @Provides
    public IRequest deleteRequest() {
        return new DeleteRequest();
    }

    @Singleton
    @Provides
    public IRequest discountRequest() {
        return new DiscountRequest();
    }

    @Singleton
    @Provides
    public IRequest emptyRequest() {
        return new EmptyRequest();
    }

    @Singleton
    @Provides
    public IRequest orderRequest() {
        return new OrderRequest();
    }

    @Singleton
    @Provides
    public IRequest paymentRequest() {
        return new PaymentRequest();
    }

}
