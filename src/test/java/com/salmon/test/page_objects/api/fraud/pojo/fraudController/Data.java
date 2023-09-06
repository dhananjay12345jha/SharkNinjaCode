package com.salmon.test.page_objects.api.fraud.pojo.fraudController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Data {
    private List<FraudHoldOrder> fraudHoldOrder;

    private String orderId;

    private static final Logger log = LoggerFactory.getLogger(Data.class);

    public List<FraudHoldOrder> getFraudHoldOrder() {
        return fraudHoldOrder;
    }

    public String getOrderId() {
        log.info("Returning the value of orderId which is-->"+orderId);
        return orderId;
    }
}
