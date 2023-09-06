package com.salmon.test.page_objects.api.fraud.pojo.fraudController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FraudHoldOrder
{
    private int id;

    private String blackListName;

    private String source;

    private String matchedValueType;

    private String matchedValue;

    private String status;

    private String comment;

    private int createdAt;

    private int updateAt;

    private String updatedBy;


    private static final Logger log = LoggerFactory.getLogger(FraudHoldOrder.class);

    public int getId() {
        log.info("Returning value of id which is-->>"+id);
        return id;
    }

    public String getSource() {
        log.info("Returning value of source which is-->>"+source);
        return source;
    }

    public String getStatus() {
        log.info("Returning value of status which is-->>"+status);
        return status;
    }

    public String getComment() {
        log.info("Returning value of comment which is-->>"+comment);
        return comment;
    }

    public int getCreatedAt() {
        log.info("Returning value of createdAt which is-->>"+createdAt);
        return createdAt;
    }

    public int getUpdateAt() {
        log.info("Returning value of updatedAt which is-->>"+updateAt);
        return updateAt;
    }

    public String getBlackListName() {
        log.info("Returning value of BlackList Name which is-->>"+blackListName);
        return blackListName;
    }

    public String getMatchedValueType() {
        log.info("Returning value of Matched Value Type which is-->>"+matchedValueType);
        return matchedValueType;
    }

    public String getMatchedValue() {
        log.info("Returning value of matched Value which is-->>"+matchedValue);
        return matchedValue;
    }

    public String getUpdatedBy() {
        log.info("Returning value of updated By which is-->>"+updatedBy);
        return updatedBy;
    }
}
