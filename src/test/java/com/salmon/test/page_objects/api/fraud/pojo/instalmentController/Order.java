package com.salmon.test.page_objects.api.fraud.pojo.instalmentController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Order
{
    private String orderId;

    private int totalOrderAmount;

    private String currency;

    private int shippingAmount;

    private static final Logger log = LoggerFactory.getLogger(Order.class);

    public String getOrderId() {
        log.info("returning value of orderId which is-->"+orderId);
        return orderId;
    }

    public int getTotalOrderAmount() {
        log.info("returning value of totalOrderAmount which is-->"+totalOrderAmount);
        return totalOrderAmount;
    }

    public String getCurrency() {
        log.info("returning value of currency which is-->"+currency);
        return currency;
    }

    public int getShippingAmount() {
        log.info("returning value of shippingAmount which is-->"+shippingAmount);
        return shippingAmount;
    }
}
