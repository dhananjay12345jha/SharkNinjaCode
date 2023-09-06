package com.salmon.test.page_objects.gui.SNCA;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyAccountPageSNCA extends PageObject {
    private static final By myAccountText = By.cssSelector("*.text-mobile");
    private static final By orderandReturn = By.cssSelector("div[class*='navigation-desktop'] a[routerlink='/account/orders']");
    private static final By orderAndReturnMobile = By.cssSelector("div.select-navigation a[routerlink='/account/orders']");
    private static final By addressBookBtn = By.cssSelector("div[class*='navigation-desktop'] a[routerlink='/account/addresses']");
    private static final By addressBookBtnMobile = By.cssSelector("div.select-navigation a[routerlink='/account/addresses']");
    private static final By accountSetting = By.cssSelector("div[class*='navigation-desktop'] a[routerlink='/account/profile']");
    private static final By accountSettingMobile = By.cssSelector("div.select-navigation a[routerlink='/account/profile']");
    private static final By myProductWarranties = By.cssSelector("div[class*='navigation-desktop'] a[routerlink='/account/product-registration']");

    private static final By partsAccessories = By.xpath("//*[@class='product-register-links-container']/div[1]/a/p");

    private static final By userManuals = By.xpath("//*[@class='product-register-links-container']/div[2]/a/p");

    private static final By fiveYearLtdWarranty = By.xpath("//*[@class='product-register-links-container']/div[3]/a/p");

    private static final By faqs = By.xpath("//*[@class='p-link faq-link']/a/p");

    private static final By myMyProductWarrantiesMobile = By.cssSelector("div.select-navigation a[routerlink='/account/product-registration']");
    private static final By signoutLink = By.cssSelector("div.account-wrapper a");
    private static final By myAccountMobileMenu = By.cssSelector("a.dashboard-item");

    private static final Logger log = LoggerFactory.getLogger(MyAccountPageSNCA.class);

    public String getTextMyAccount() {
        IsPageLoaded.waitAllRequest();
        String text = "";

        try {
            text = getTextFor(myAccountText).trim();
            log.info("Over my account page text has been found which is-->" + text);
        } catch (Exception e) {
            log.error("Unable to fetch text with the locator \"" + myAccountText + "\" over my account page for SharkCA-->" + e.getMessage());
            e.printStackTrace();
        }

        return text;
    }

    public MyAccountAddressPageSNCA clickOnAddressBookBtn() {
        IsPageLoaded.waitAllRequest();
        MyAccountAddressPageSNCA obj = null;
        if (UrlBuilder.isMobile()) {
            clickByElement(myAccountMobileMenu);
            clickByElement(addressBookBtnMobile);
            obj = new MyAccountAddressPageSNCA();
        } else {
            clickByElement(addressBookBtn);
            obj = new MyAccountAddressPageSNCA();
        }
        return obj;
    }

    public MyAccountOrdersPageSNCA clickOnOrderandReturn() {
        IsPageLoaded.waitAllRequest();
        MyAccountOrdersPageSNCA obj = null;
        if (UrlBuilder.isMobile()) {
            clickByElement(myAccountMobileMenu);
            clickByElement(orderAndReturnMobile);
            obj = new MyAccountOrdersPageSNCA();
        } else {
            clickByElement(orderandReturn);
            obj = new MyAccountOrdersPageSNCA();
        }
        return obj;
    }

    public MyAccountSettingPageSNCA clickOnAccountSetting() {
        IsPageLoaded.waitAllRequest();
        MyAccountSettingPageSNCA obj = null;
        if (UrlBuilder.isMobile()) {
            clickByElement(myAccountMobileMenu);
            clickByElement(accountSettingMobile);
            obj = new MyAccountSettingPageSNCA();
        } else {
            clickByElement(accountSetting);
            obj = new MyAccountSettingPageSNCA();
        }
        return obj;
    }

    public MyAccountProductWarrentiesSNCA clickOnMyProductWarranties() {
        IsPageLoaded.waitAllRequest();
        MyAccountProductWarrentiesSNCA obj = null;
        if (UrlBuilder.isMobile()) {
            clickByElement(myAccountMobileMenu);
            clickByElement(myMyProductWarrantiesMobile);
            obj = new MyAccountProductWarrentiesSNCA();
            log.info("Successfully clicked on Product/Warranties Button in my account");
        } else {
            waitForExpectedElement(myProductWarranties);
            clickByElement(myProductWarranties);
            obj = new MyAccountProductWarrentiesSNCA();
            log.info("Successfully clicked on Product/Warranties Button in my account");
        }
        return obj;
    }
    public MyAccountProductWarrentiesSNCA clickOnPartsAccessories() {
        IsPageLoaded.waitAllRequest();
        MyAccountProductWarrentiesSNCA obj = null;
        if (UrlBuilder.isMobile()) {
            clickByElement(myAccountMobileMenu);
            clickByElement(myMyProductWarrantiesMobile);
            obj = new MyAccountProductWarrentiesSNCA();
            log.info("Successfully clicked on Product/Warranties Button in my account");
        } else {
            waitForExpectedElement(partsAccessories);
            clickByElement(partsAccessories);
            obj = new MyAccountProductWarrentiesSNCA();
            log.info("Successfully clicked on Parts accessories Button in Product Registration Confirmation Page");
        }
        return obj;
    }


    public MyAccountProductWarrentiesSNCA clickOnUserManuals() {
        IsPageLoaded.waitAllRequest();
        MyAccountProductWarrentiesSNCA obj = null;
        if (UrlBuilder.isMobile()) {
            clickByElement(myAccountMobileMenu);
            clickByElement(myMyProductWarrantiesMobile);
            obj = new MyAccountProductWarrentiesSNCA();
            log.info("Successfully clicked on Product/Warranties Button in my account");
        } else {
            waitForExpectedElement(userManuals);
            clickByElement(userManuals);
            obj = new MyAccountProductWarrentiesSNCA();
            log.info("Successfully clicked on User Manuals Button in Product Registration Confirmation Page");
        }
        return obj;
    }

    public MyAccountProductWarrentiesSNCA clickOnFiveYearLimitedWarranty() {
        IsPageLoaded.waitAllRequest();
        MyAccountProductWarrentiesSNCA obj = null;
        if (UrlBuilder.isMobile()) {
            clickByElement(myAccountMobileMenu);
            clickByElement(myMyProductWarrantiesMobile);
            obj = new MyAccountProductWarrentiesSNCA();
            log.info("Successfully clicked on Product/Warranties Button in my account");
        } else {
            waitForExpectedElement(fiveYearLtdWarranty);
            clickByElement(fiveYearLtdWarranty);
            obj = new MyAccountProductWarrentiesSNCA();
            log.info("Successfully clicked on Five Year Ltd Warranty Button in Product Registration Confirmation Page");
        }
        return obj;
    }

    public MyAccountProductWarrentiesSNCA clickOnFaqs() {
        IsPageLoaded.waitAllRequest();
        MyAccountProductWarrentiesSNCA obj = null;
        if (UrlBuilder.isMobile()) {
            clickByElement(myAccountMobileMenu);
            clickByElement(myMyProductWarrantiesMobile);
            obj = new MyAccountProductWarrentiesSNCA();
            log.info("Successfully clicked on Product/Warranties Button in my account");
        } else {
            waitForExpectedElement(faqs);
            clickByElement(faqs);
            obj = new MyAccountProductWarrentiesSNCA();
            log.info("Successfully clicked on faqs Button in Product Registration Confirmation Page");
        }
        return obj;
    }

    public boolean clickSignoutButton() {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            clickByElement(signoutLink);
            log.info("successfully clicked sign out link-->");
            flag = true;
        } catch (Exception e) {
            log.error("Unable to click signout link-->" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }
}
