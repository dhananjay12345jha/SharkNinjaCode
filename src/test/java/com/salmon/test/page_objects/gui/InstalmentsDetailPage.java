package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import org.openqa.selenium.By;

//This page is shown when click on Instalment link on Order Overview page
public class InstalmentsDetailPage extends PageObject {
    private final By heading = By.xpath("//h1[@class='account-welcome-text']");
    private final By installmentPlanHeaderText = By.xpath("//div[@class='col-xs-12 col-sm-7']/h3");
    private final By outstandingBalanceHeader = By.xpath("//div[@class='col-sm-5']/h3");
    private final By paymentHistory = By.xpath("//div[@class='col-sm-12']/h3");

    public String getHeading() {
        return waitForExpectedElement(heading).getText();
    }

    public String getInstallmentPlanHeaderText() {
        return waitForExpectedElement(installmentPlanHeaderText).getText();
    }

    public String getOutstandingBalanceHeader() {
        return waitForExpectedElement(outstandingBalanceHeader).getText();
    }

    public String getPaymentHistoryHeader() {
        return waitForExpectedElement(paymentHistory).getText();
    }
}
