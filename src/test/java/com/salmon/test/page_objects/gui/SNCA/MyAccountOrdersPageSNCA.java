package com.salmon.test.page_objects.gui.SNCA;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class MyAccountOrdersPageSNCA extends PageObject {
    private static final Logger log = LoggerFactory.getLogger(MyAccountAddressPageSNCA.class);
    public By orderPageEmptyList = By.xpath("//p[@data-testing-id='emptyList']");
    public By orderPageOrderListHeader = By.xpath("//div[@class='list-header d-md-flex']");
    private static final By orderNumbers=By.xpath("//div[starts-with(@class,'col-7')]/a");

    public boolean checkOrderPageFound() {
        boolean flag = false;
        flag = isElementPresentWithWait(orderPageEmptyList);
        if (flag == false)
            flag = isElementPresentWithWait(orderPageOrderListHeader);
        return flag;

    }

    public List<String> getListOfOrderIds(){
        getWebDriver().navigate().refresh();
        IsPageLoaded.waitAllRequest();
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(orderNumbers))
                .stream().map(s->s.getText().trim()).collect(Collectors.toList());
    }

}
