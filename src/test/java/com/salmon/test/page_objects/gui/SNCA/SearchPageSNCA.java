package com.salmon.test.page_objects.gui.SNCA;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import com.salmon.test.page_objects.gui.PDPPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SearchPageSNCA extends PageObject {
    private static final By searchResultText = By.cssSelector("ish-search-result h1");
    private static final By productTitlesNames = By.cssSelector("div.product-title");
    private static final Logger log = LoggerFactory.getLogger(SearchPageSNCA.class);

    public String getTextDisplayedOnPage() {
        String text = "";
        try {
            visibilityOf(webDriver.findElement(searchResultText));
            text = getTextFor(searchResultText);
            log.info("Text have been found over search product page which is-->" + text);
        } catch (Exception e) {
            log.error("Unable to fetch text from displayed on product search page due to-->" + e.getMessage());
            e.printStackTrace();
        }
        return text;
    }

    public List<String> getProductTilesTitle() {
        List<String> tiles = new ArrayList<>();
        visibilityOf(getWebDriver().findElement(searchResultText));
        try {
            tiles = visibilityOfAllElementsLocatedBy(productTitlesNames).stream().map(s -> s.getText()).collect(Collectors.toList());
            log.info("");
        } catch (Exception e) {
            log.error("Some exception occurred while finding the product detail tiles due to-->" + e.getMessage());
            e.printStackTrace();
        }
        return tiles;
    }

    public PDPPageSNCA openPDPPageOfProductWhichIsNotOutOfStock() {
        IsPageLoaded.waitAllRequest();
        PDPPageSNCA object = null;
        boolean flag = false;
        int i, count = 0;
         waitForPage();
//        visibilityOf(webDriver.findElement(searchResultText));
        getTextDisplayedOnPage();
        List<WebElement> productList = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productTitlesNames));
//        for (i = 0; i < productList.size(); i++) {
//            String parentWindow = webDriver.getWindowHandle();
//            while (count < 3) {
//                try {
//                    WebElement e = productList.get(i);
//                    waitForPage();
//                    int k = 0;
//                    do {
//                        wait.until(ExpectedConditions.visibilityOf(e));
//                        new Actions(getWebDriver()).moveToElement(e).keyDown(Keys.LEFT_CONTROL).click(e).keyUp(Keys.LEFT_CONTROL).build().perform();
//                        if (webDriver.getWindowHandles().size() == 2) {
//                            break;
//                        }
//                        else {
//                            k++;
//                        }
//                    }
//                    while (k < 2);
//                    String childWindow = webDriver.getWindowHandles().stream().filter(s -> !s.equalsIgnoreCase(parentWindow)).findFirst().get();
//                    webDriver.switchTo().window(childWindow);
//                    if (new PDPPageSNCA().isAddToCartButtonPresent()) {
//                        webDriver.close();
//                        webDriver.switchTo().window(parentWindow);
//                        wait.until(ExpectedConditions.visibilityOf(e));
//                        new Actions(getWebDriver()).moveToElement(e).click().build().perform();
//                        object = new PDPPageSNCA();
//                        flag = true;
//                        break;
//                    } else {
//                        webDriver.close();
//                        webDriver.switchTo().window(parentWindow);
//                        break;
//                    }
//                } catch (StaleElementReferenceException e1) {
//                    log.error("Stale element reference exception is there trying one more time");
//                    count++;
//                    productList = webDriver.findElements(productTitlesNames);
//                }
//            }
//            if (flag) {
//                break;
//            }
//
//        }

//        wait.until(ExpectedConditions.presenceOfElementLocated(productList.get(0)));
        new Actions(getWebDriver()).moveToElement(productList.get(0)).build().perform();
        wait.until(ExpectedConditions.visibilityOf(productList.get(0))).click();
        object = new PDPPageSNCA();
        return object;
    }


}
