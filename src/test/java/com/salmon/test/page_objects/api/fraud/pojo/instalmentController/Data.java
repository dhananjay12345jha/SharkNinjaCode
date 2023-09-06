package com.salmon.test.page_objects.api.fraud.pojo.instalmentController;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Data
{
        private int totalNumberOfInstalments;

        private int totalLeftInstalments;

        private int totalPaidInstalments;

        private int nextDueAmount;

        private int totalAmountDue;

        private int totalAmountPaid;

        private int amountInProcess;

        private int instalmentPlanTotal;

        private String planStatus;

        private int planCreatedDate;

        private int planUpdatedDate;

        private int planEndDate;

        private Order order;

        private String customerId;

        private String customerNo;

        private String emailId;

        private int nextPaymentPlanned;

        private List<Invoices> invoices;

        private String last4Digit;

        private String changePlanAllowed;

        private boolean payOutstandingBalance;

        private String merchantId;

        private String psp;

        private String paymentToken;

        private boolean badDebt;

        private int totalRefundAmount;

        private int actualRefundAmount;

    private static final Logger log = LoggerFactory.getLogger(Data.class);

    public int getActualRefundAmount() {
        log.info("returning value of actualRefundAmount which is-->"+actualRefundAmount);
        return actualRefundAmount;
    }

    public int getTotalRefundAmount() {
        log.info("returning value of totalRefundAmount which is-->"+totalRefundAmount);
        return totalRefundAmount;
    }

    public int getTotalNumberOfInstalments()
    {
        log.info("returning value of totalNumberOfInstalments which is-->"+totalNumberOfInstalments);
        return totalNumberOfInstalments;
    }

    public int getTotalLeftInstalments() {
        log.info("returning value of totalLeftInstalments which is-->"+totalLeftInstalments);
        return totalLeftInstalments;
    }

    public int getTotalPaidInstalments() {
        log.info("returning value of totalPaidInstalments which is-->"+totalPaidInstalments);
        return totalPaidInstalments;
    }

    public int getNextDueAmount() {
        log.info("returning value of nextDueAmount which is-->"+nextDueAmount);
        return nextDueAmount;
    }

    public int getTotalAmountDue() {
        log.info("returning value of totalAmountDue which is-->"+totalAmountDue);
        return totalAmountDue;
    }

    public int getTotalAmountPaid() {
        log.info("returning value of totalAmountPaid which is-->"+totalAmountPaid);
        return totalAmountPaid;
    }

    public int getAmountInProcess() {
        log.info("returning value of amountInProcess which is-->"+amountInProcess);
        return amountInProcess;
    }

    public int getInstalmentPlanTotal() {
        log.info("returning value of instalmentPlanTotal which is-->"+instalmentPlanTotal);
        return instalmentPlanTotal;
    }

    public String getPlanStatus() {
        log.info("returning value of planStatus which is-->"+planStatus);
        return planStatus;
    }

    public int getPlanCreatedDate() {
        log.info("returning value of planCreatedDate which is-->"+planCreatedDate);
        return planCreatedDate;
    }

    public int getPlanUpdatedDate() {
        log.info("returning value of planUpdatedDate which is-->"+planUpdatedDate);
        return planUpdatedDate;
    }

    public int getPlanEndDate() {
        log.info("returning value of planEndDate which is-->"+planEndDate);
        return planEndDate;
    }

    public Order getOrder() {
        log.info("returning value of order which is-->"+order);
        return order;
    }

    public String getCustomerId() {
        log.info("returning value of customerId which is-->"+customerId);
        return customerId;
    }

    public String getCustomerNo() {
        log.info("returning value of customerNo which is-->"+customerNo);
        return customerNo;
    }

    public String getEmailId() {
        log.info("returning value of emailId which is-->"+emailId);
        return emailId;
    }

    public int getNextPaymentPlanned() {
        log.info("returning value of nextPaymentPlanned which is-->"+nextPaymentPlanned);
        return nextPaymentPlanned;
    }

    public List<Invoices> getInvoices() {
        return invoices;
    }

    public String getLast4Digit() {
        log.info("returning value of last4Digit which is-->"+last4Digit);
        return last4Digit;
    }

    public String getChangePlanAllowed() {

        log.info("returning value of changePlanAllowed which is-->"+changePlanAllowed);
        return changePlanAllowed;
    }

    public boolean isPayOutstandingBalance() {
        log.info("returning value of payOutstandingBalance which is-->"+payOutstandingBalance);
        return payOutstandingBalance;
    }

    public String getMerchantId() {
        log.info("returning value of merchantId which is-->"+merchantId);
        return merchantId;
    }

    public String getPsp() {
        log.info("returning value of psp which is-->"+psp);
        return psp;
    }

    public String getPaymentToken() {
        log.info("returning value of paymentToken which is-->"+paymentToken);
        return paymentToken;
    }

    public boolean isBadDebt() {
        log.info("returning value of badDebt which is-->"+badDebt);
        return badDebt;
    }
}
