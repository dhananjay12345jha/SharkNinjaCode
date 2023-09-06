package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CatalogNavigation extends HomePage {
    private final By productsCatalogTab = By.xpath("//ul[@class='navbar-nav main-navigation-list']/li[2]/a[1]");

    private final By productCategoryAllLinksCA = By.xpath("//ul[@class='navbar-nav main-navigation-list']/ish-content-pagelet[1]/sn-cms-sn-navigation-menu-link/li/ul/ul/li[1]/ul/li/a");

    private final By productCategoryAllLinksSharkCAVaccum = By.xpath("//ul[@class='navbar-nav main-navigation-list']/ish-content-pagelet[1]/sn-cms-sn-navigation-menu-link/li/ul/ul/li[1]/ul[1]/li/a");

    private final By productCategoryAllLinksSharkCAAirPurifiers = By.xpath("//ul[@class='navbar-nav main-navigation-list']/ish-content-pagelet[1]/sn-cms-sn-navigation-menu-link/li/ul/ul/li[1]/ul[2]/li/a");

    private final By productCategoryAllLinksSharkCAHairCare = By.xpath("//ul[@class='navbar-nav main-navigation-list']/ish-content-pagelet[1]/sn-cms-sn-navigation-menu-link/li/ul/ul/li[1]/ul[3]/li/a");

    private final By productCategoryAllLinksSharkCAMobsIron = By.xpath("//ul[@class='navbar-nav main-navigation-list']/ish-content-pagelet[1]/sn-cms-sn-navigation-menu-link/li/ul/ul/li[1]/ul[4]/li/a");

    private final By productsCatalogTabCA = By.xpath("//ul[@class='navbar-nav main-navigation-list']/ish-content-pagelet[1]/sn-cms-sn-navigation-menu-link/li/a");
    private final By mobileProductsCatalogTab = By.xpath("//ul[@class='navbar-nav main-navigation-list']/li[2]/a[2]");
    private final By partsAccessoriesTab = By.xpath("//*[@id=\"globalnav\"]/div[1]/ul/li[3]/a[1]");
    private static final Logger log = LoggerFactory.getLogger(CatalogNavigation.class);

    private By ProductsCategoryCATabDynamic;
    private By partsAndAccessoriesTabDynamic;
    private static final By partsAndAccessorieslink = By.xpath("//a[text()='Parts & Accessories' or text()='Ersatzteile & Zubehör']");
    private static final By partsAndAccessorieslinkMobile = By.xpath("//a[@data-target='#mobileCat-Parts_and_Accessoires']");
    // //ul[@class="navbar-nav main-navigation-list"]//li[@class="dropdown main-navigation-overlay"][2]
    private static final By tipsAndAdviceLink = By.xpath("//*[@id=\"globalnav\"]/div[1]/ul/li[4]/a[1]");
    private static final By headerLinks = By.xpath("//ul[@class='navbar-nav main-navigation-list']/li/a");

    //	private static final By categoryLevel1Option;
    private final By categoryLevel1List = By.xpath("//div[@id='mobileCat-Products']//li[@class='main-navigation-level1-item']/a");
    private final By partsCategoryLevel1List = By.xpath("//div[@id='mobileCat-Parts_and_Accessoires']//li[@class=\"main-navigation-level1-item\"]/a");
    private final By LeftTogglebtn = By.xpath("//*[@class='navbar-toggle collapsed']");

    public void productsCatalogTab() {
        if (UrlBuilder.isDesktop() || UrlBuilder.isTablet()) {
            Actions actions = new Actions(webDriver);
            actions.moveToElement(webDriver.findElement(productsCatalogTab)).build().perform();
        } else if (UrlBuilder.isMobile()) {
            WebElement element = waitForExpectedElement(LeftTogglebtn);
            if (element.getAttribute("aria-expanded") == null || element.getAttribute("aria-expanded").equalsIgnoreCase("false")) {
                element.click();
            }
            waitForExpectedElement(mobileProductsCatalogTab).click();
        }
    }

    public void partsAndAccessoriesTab() {

        if (UrlBuilder.isDesktop() || UrlBuilder.isTablet()) {
            Actions actions = new Actions(webDriver);
            actions.moveToElement(webDriver.findElement(partsAndAccessorieslink)).build().perform();
            log.info(" clicked on Parts and Accessories ");
        } else if (UrlBuilder.isMobile()) {
            WebElement element = waitForExpectedElement(LeftTogglebtn);
            if (element.getAttribute("aria-expanded") == null || element.getAttribute("aria-expanded").equalsIgnoreCase("false")) {
                element.click();
            }
            waitForExpectedElement(partsAndAccessorieslinkMobile).click();
        }
    }
    public void productCategoryForCA() {

        if (UrlBuilder.isDesktop() || UrlBuilder.isTablet()) {
            Actions actions = new Actions(webDriver);
            actions.moveToElement(webDriver.findElement(productsCatalogTabCA)).build().perform();
        } else if (UrlBuilder.isMobile()) {
            WebElement element = waitForExpectedElement(LeftTogglebtn);
            if (element.getAttribute("aria-expanded") == null || element.getAttribute("aria-expanded").equalsIgnoreCase("false")) {
                element.click();
            }
            waitForExpectedElement(partsAndAccessorieslinkMobile).click();
        }
    }

    public void tipsAndAdviceTab() {
        try {
            waitForExpectedElement(tipsAndAdviceLink).click();
        } catch (Exception e) {
            log.info("some exception occured while clicking on tips and advice tab");
        }
    }

    public void clickPartsAndAccessoriesOption(String partsAndaccesoriesOption, int index) {
        partsAndAccessoriesTabDynamic = By.xpath("//*[@id=\"mobileCat-Parts_and_Accessoires\"]/ul/li[@class=\"main-navigation-level1-item\"][" + index + "]");
        Actions actions = new Actions(webDriver);
        actions.moveToElement(wait.until(ExpectedConditions.elementToBeClickable(partsAndAccessoriesTabDynamic))).click().build().perform();
    }

    public void clickProductsCategoryCAOption(String ProductsCategoryCAOption, int index) throws InterruptedException {
        ProductsCategoryCATabDynamic = By.xpath("//*[@class='dropdown-menu category-level1']/ul/li/ul/li[" + index + "]");
        Actions actions = new Actions(webDriver);
        actions.moveToElement(wait.until(ExpectedConditions.elementToBeClickable(ProductsCategoryCATabDynamic))).click().build().perform();
    }

    public void clickProductsCategorySharkCAOptionAirPurifier(String ProductsCategoryCAOption, int index) throws InterruptedException {
        ProductsCategoryCATabDynamic = By.xpath("//*[@class='dropdown-menu category-level1']/ul/li/ul[2]/li[" + index + "]");
        Actions actions = new Actions(webDriver);
        actions.moveToElement(wait.until(ExpectedConditions.elementToBeClickable(ProductsCategoryCATabDynamic))).click().build().perform();
    }

    public void clickProductsCategorySharkCAOptionHairCare(String ProductsCategoryCAOption, int index) throws InterruptedException {
        ProductsCategoryCATabDynamic = By.xpath("//*[@class='dropdown-menu category-level1']/ul/li/ul[3]/li[" + index + "]");
        Actions actions = new Actions(webDriver);
        actions.moveToElement(wait.until(ExpectedConditions.elementToBeClickable(ProductsCategoryCATabDynamic))).click().build().perform();
    }

    public void clickProductsCategorySharkCAOptionMopsIrons(String ProductsCategoryCAOption, int index) throws InterruptedException {
        ProductsCategoryCATabDynamic = By.xpath("//*[@class='dropdown-menu category-level1']/ul/li/ul[4]/li[" + index + "]");
        Actions actions = new Actions(webDriver);
        actions.moveToElement(wait.until(ExpectedConditions.elementToBeClickable(ProductsCategoryCATabDynamic))).click().build().perform();
    }
    public List<WebElement> productCategoryLinks() {
        return webDriver.findElements(categoryLevel1List);
    }

    public boolean clickCategory(String categoryOption) {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(categoryLevel1List));
        try {
            Thread.sleep(2000);
            for (WebElement productTile : elements) {
                if (productTile.getText().contains(categoryOption)) {
                    new Actions(getWebDriver()).moveToElement(productTile).build().perform();
                    wait.until(ExpectedConditions.elementToBeClickable(productTile)).click();
                    flag = true;
                    break;
                }
            }
        } catch (Exception e) {
            log.error("Some exception occured while selecting option from header-->>" + ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        }
        return flag;
    }

    public void clickCategorylevel1Option(String categoryOption, int i) throws InterruptedException {
        System.out.println("value of i is: " + i);
        By productCategory1TabDynamic = By.xpath("//div[@id='mobileCat-Products']//li[@class=\"main-navigation-level1-item\"][" + i + "]");
        Actions actions = new Actions(webDriver);
        actions.moveToElement(webDriver.findElement(productCategory1TabDynamic)).click().perform();
//		Thread.sleep(15000);
//		return webDriver.getTitle();
//		return new ProductCategoryPage();
    }


    public void partsAccessoriesTab() throws InterruptedException {
        Actions actions = new Actions(webDriver);
        actions.moveToElement(webDriver.findElement(partsAccessoriesTab)).build().perform();
//		Thread.sleep(2000);
    }

    public List<WebElement> partsCategoryLinks() {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(partsCategoryLevel1List));
    }

    public List<WebElement> productsCategoryLinksCA() {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productCategoryAllLinksCA));
    }

    public List<WebElement> productCategoryAllLinksSharkCAVaccum() {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productCategoryAllLinksSharkCAVaccum));
    }

    public List<WebElement> productCategoryAllLinksSharkCAAirPurifiers() {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productCategoryAllLinksSharkCAAirPurifiers));
    }

    public List<WebElement> productCategoryAllLinksSharkCAHairCare() {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productCategoryAllLinksSharkCAHairCare));
    }

    public List<WebElement> productCategoryAllLinksSharkCAHairMobsIron() {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productCategoryAllLinksSharkCAMobsIron));
    }

    public void selectProductfromCategoryPage(String productSpecificSKU) {
        By selectProductFromCategoryPageDynamic = By.xpath("//a[contains(@href, '" + productSpecificSKU + "')][text()='" + Props.getProp("pdp.moreinfo") + "']");
        System.out.println("value of xpath is: " + selectProductFromCategoryPageDynamic);
        waitForExpectedElement(selectProductFromCategoryPageDynamic).click();
    }

    public boolean clickHeaderLink(String linkToClick) {
        boolean flag=false;
        try{
        List<WebElement> AllLinks = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(headerLinks));
        for (WebElement link : AllLinks) {
            if (link.getText().trim().contains(linkToClick)) {
                wait.until(ExpectedConditions.elementToBeClickable(link)).click();
                flag=true;
                break;
            }
        }
    }catch (Exception e){
            log.error("Some exception occured while clicking on header link-->>"+ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return flag;
    }
}
