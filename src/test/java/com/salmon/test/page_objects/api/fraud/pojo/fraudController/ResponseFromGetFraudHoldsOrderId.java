package com.salmon.test.page_objects.api.fraud.pojo.fraudController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResponseFromGetFraudHoldsOrderId
{
    private int status;

    private String message;

    private Data data;

    private static final Logger log = LoggerFactory.getLogger(ResponseFromGetFraudHoldsOrderId.class);

    public int getStatus() {
        log.info("Returning the value of status which is-->"+status);
        return status;
    }

    public String getMessage() {
        log.info("Returning the value of message which is-->"+message);
        return message;
    }

    public Data getData() {
        return data;
    }
}
