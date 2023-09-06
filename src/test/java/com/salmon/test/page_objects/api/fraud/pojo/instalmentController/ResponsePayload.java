package com.salmon.test.page_objects.api.fraud.pojo.instalmentController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResponsePayload
{
    private int status;

    private String message;

    private Data data;

    private static final Logger log = LoggerFactory.getLogger(ResponsePayload.class);

    public int getStatus() {
        log.info("returning value of status which is-->"+status);
        return status;
    }

    public String getMessage() {
        log.info("returning value of message which is-->"+message);
        return message;
    }

    public Data getData() {
        return data;
    }
}
