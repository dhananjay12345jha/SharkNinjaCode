package com.salmon.test.page_objects.gui.SNCA;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PartsAndAccessoriesPageSNCA extends PageObject {
    private static final Logger log = LoggerFactory.getLogger(MyAccountAddressPageSNCA.class);
//    public By partsAndAccessoriesPage = By.xpath("//*[@id='main-content']//*[@class='mt-4 category-title']");
    public By partsAndAccessoriesPage = By.cssSelector("div[class='container replacements']");
    public By partsAndAccessoriesPageHeading = By.xpath("//div[@class='container replacements']/div/h2");


    public boolean checkPartsAndAccessoriesPageFound() {
        boolean flag = false;
        try {
            flag = isElementPresentWithWait(partsAndAccessoriesPage);
            String title = Props.getProp("parts.accessory.heading");
            String actualTitle = getTextFor(partsAndAccessoriesPageHeading);
            if (actualTitle.equalsIgnoreCase(title)) {
                log.info("Parts And Accessories Page heading found successfully");
            } else {
                log.info("Expected Title =" + title + " and Actual title =" + actualTitle);
            }
            log.info("Parts And Accessories Page heading found successfully");
        } catch (Exception e) {
            log.error("Parts And Accessories Page heading not found" + e.getMessage());
            e.printStackTrace();
        }
        return flag;

    }

}