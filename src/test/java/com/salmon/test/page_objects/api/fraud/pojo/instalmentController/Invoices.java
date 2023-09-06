package com.salmon.test.page_objects.api.fraud.pojo.instalmentController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Invoices
{
    private String transactionId;

    private String invoiceId;

    private int amountPaid;

    private int attemptCount;

    private int instalmentNumber;

    private String status;

    private int createdDate;

    private String last4Digit;

    private int refundAmount;

    private int amountRemaining;

    private String refundId;



    private static final Logger log = LoggerFactory.getLogger(Invoices.class);

    public String getRefundId() {
        log.info("returning value of refundId which is-->"+refundId);
        return refundId;
    }

    public int getRefundAmount() {
        log.info("returning value of refundAmount which is-->"+refundAmount);
        return refundAmount;
    }

    public int getAmountRemaining() {
        log.info("returning value of amountRemaining which is-->"+amountRemaining);
        return amountRemaining;
    }

    public String getTransactionId() {
        log.info("returning value of transactionId which is-->"+transactionId);
        return transactionId;
    }

    public String getInvoiceId() {
        log.info("returning value of invoiceId which is-->"+invoiceId);
        return invoiceId;
    }

    public int getAmountPaid() {
        log.info("returning value of amountPaid which is-->"+amountPaid);
        return amountPaid;
    }

    public int getAttemptCount() {
        log.info("returning value of attemptCount which is-->"+attemptCount);
        return attemptCount;
    }

    public int getInstalmentNumber() {
        log.info("returning value of instalmentNumber which is-->"+instalmentNumber);
        return instalmentNumber;
    }

    public String getStatus() {
        log.info("returning value of status which is-->"+status);
        return status;
    }

    public int getCreatedDate() {
        log.info("returning value of createdDate which is-->"+createdDate);
        return createdDate;
    }

    public String getLast4Digit() {
        log.info("returning value of last4Digit which is-->"+last4Digit);
        return last4Digit;
    }
}
