package com.salmon.test.page_objects.gui.SNUS;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import com.salmon.test.page_objects.gui.SNCA.CreateNewAccountPageSNCA;
import com.salmon.test.page_objects.gui.SNCA.HomePageSNCA;
import com.salmon.test.page_objects.gui.SNCA.MyAccountAddressPageSNCA;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;
import java.util.Locale;

public class HomePageSNUS extends PageObject {
    //    private static final By logInText = By.xpath("//div[@class='rectangle']/h3"); ---xpath changed
    private static final By logInText = By.xpath("//div[@class='rectangle']/h1");

    private static final By cartItemCount = By.xpath("//sn-mini-basket[@data-testing-id=\"mini-basket-desktop\"]//a[contains(@href,'basket')]/span");

    private static final By signoutLink = By.cssSelector("div.account-wrapper a");
    private static final By errorMessageInAlert = By.xpath("//div[@class='checkError--sub']");
    private static final By registerNowBtn = By.xpath("(//a[@routerlink=\"/register\"])[2]");
    private static final By closeButton = By.xpath("//img[@aria-label=\"Popup Close Button\"]");
    // Product List
    private static final By productList = By.xpath("//div[@class='manual-product-list']");
    private static final By productListRow = By.xpath("//div[@class='product-list row']");

    //  Header links
    private static final By sharkLogo = By.xpath("//a[contains(@class,'logo')]");
    private static final By productsLink = By.xpath("(//ul[@class='navbar-nav main-navigation-list']/ish-content-pagelet)[1]");
    private static final By sharkHandledVaccumMobile = By.xpath("(//div[@class='submenu'] //li/a)[4]");
    private static final By selectSharkVacuumMobile = By.xpath("(//div[@class='submenu'] //li/a)[1]");
    private static final By productsLinkFrance = By.xpath("//a[@aria-label='Produits']");
    private static final By technologiesLink = By.xpath("//a[@aria-label='Technologies']");
    private static final By registerWarrantyHeaderEn = By.xpath("(//a[contains(@href,'/register/guarantee')])[1]");
    private static final By registerWarrantyHeaderFR = By.xpath("(//a[contains(@href,'/register/guarantee')])[1]");
    private static final By partsAndAccessoriesLink = By.xpath("//a[@href='/find-parts']");
    private static final By ninjaPartsAndAccessories = By.xpath("//a[@aria-label='Parts & accessories ']");
    private static final By recipesLinkHeaderEn = By.xpath("//a[@aria-label='Recipes']");
    private static final By recipesLinkHeaderFr = By.xpath("//a[@aria-label='Recettes']");
    private static final By supportLink = By.xpath("//a[@aria-label='Support']");
    private static final By offersLink = By.xpath("//a[@aria-label='Offers']");
    private static final By customerCareLinkHeaderEn = By.xpath("//a[@aria-label='Customer Care']");
    private static final By customerCareLinkHeaderFr = By.xpath("//a[@aria-label='Soutien & Service']");
    private static final By tipsAndAdviceLinkHeaderEn = By.xpath("//a[@aria-label=\"Tips & Tricks\"]");
    private static final By tipsAndAdviceLinkHeaderFr = By.xpath("//a[@aria-label=\"Trucs & Conseils\"]");
    private static final By adrianaLink = By.xpath("//ul[@class='navbar-nav main-navigation-list']/ish-content-pagelet[5]");
    private static final By uprightVacuum = By.xpath("(//ul[@class='dropdown-menu category-level1']/ul/li[1]/ul[@class='category-level2']/li[1]/a)[1]");
    private static final By uprightVacuumFr = By.xpath("//ul[@class='dropdown-menu category-level1']/ul/li[1]/ul[@class='category-level2']/li[1]");
    private static final By uprightVacuumFrMobile = By.xpath("(//div[@class='submenu']//p[@id='tw-target-text'])[1]/a");
    private static final By uprightVaccumFrMobileShark = By.xpath("(//div[@class='submenu']//li)[1]/a");
    private static final By tipsAndAdviceLinkEr = By.xpath("//li[@class='dropdown'][1]/a[@aria-label=\"Tips & Advice\"]");
    private static final By tipsAndAdviceLinkFr = By.xpath("//li[@class='dropdown'][1]/a[@aria-label=\"Soutien & Service\"]");
    private static final By emptyCartMessage = By.xpath("//*[@id=\"main-content\"]/sn-basket-page/div/sn-shopping-basket-empty/div/h5");

    private static final By accountIcon = By.xpath("//a[@class=\"my-account-link\"]/fa-icon");
    private static final By miniCartBag = By.xpath("//a[@aria-label=\"basket icon\"]");
    private static final By hamburger = By.xpath("//button[@class=\"hamburger-menu\"]");
    private static final By partsAndAccessoriesLinkMobile = By.xpath("//a[@href=\"/find-parts\"]");
    private static final By backButtonHamburger = By.xpath("//div[@class=\"submenu\"]//div[@class=\"back-nav\"]");
    private static final By uprightVaccumForShark = By.xpath("(//a[contains(@href,'/handheld-vacuum-cleaners')])[1]");
    private static final By uprightVaccumForSharkFr = By.xpath("(//a[contains(@href,'/handheld-vacuum-cleaners')])[1]");
    private static final By sharkCALogo = By.xpath("//a[@data-testing-id=\"header-home-link-desktop\"]");
    private static final By sharkCALogoMobile = By.xpath("//a[@class=\"mobile-logo\"]");

    private static final By removeButtonClickCA = By.xpath("//button[contains(@class,'product-remove')]");
    private static final By sharkCaCustomerLogo = By.xpath("//div[@class=\"logo\"]");
    // Footer links
    private static final By footerAllLinks = By.xpath("//div[@class='footer']//a");
    private static final By footerShopHeading = By.xpath("//div[@class='footer']/div/div/div[1]/h2");
    private static final By footerVacuumsLink = By.xpath("//div[@class='footer']/div/div/div[1]/div[@class='link-group']/ul/li[1]/a");
    private static final By footerMopsLink = By.xpath("(//div[@class='link-group']/ul/li[2])[1]");

    private static final By footerPartsAndAccessoriesLink = By.xpath("//div[@class='footer']/div/div/div[1]/div[@class='link-group']/ul/li[3]/a");
    private static final By footerSupportHeading = By.xpath("//div[@class='footer']/div/div/div[2]/h2");
    private static final By footerFoodiFamilyCookingLink = By.xpath("(//a[text()='" + Props.getProp("footer.foodi.family.cooking") + "'])[2]");
    private static final By footerCookwareLink = By.xpath("(//a[text()='" + Props.getProp("footer.cookware") + "'])[2]");
    private static final By footerBlendersLink = By.xpath("//a[text()='" + Props.getProp("footer.blenders") + "']");
    private static final By footerCoffeeLink = By.xpath("(//a[text()='" + Props.getProp("footer.coffee") + "'])[2]");
    private static final By footerFAQsLink = By.xpath("//div[@class='footer']/div/div/div[2]/div/ul/li[1]/a");
    private static final By footerWarrantyInformationLink = By.xpath("//a[text()='" + Props.getProp("footer.warranty.information") + "']");
    private static final By footerOrderStatusLink = By.xpath("//a[text()='" + Props.getProp("footer.order.status") + "']");
    private static final By footerMyAccountLink = By.xpath("//a[text()='" + Props.getProp("footer.my.account") + "']");
    private static final By footerUnsubscribeLink = By.xpath("//a[text()='" + Props.getProp("footer.unsubscribe") + "']");
    private static final By footerAboutHeading = By.xpath("//div[@class='footer']/div/div/div[3]/h2");
    private static final By footerTechnologiesLink = By.xpath("//div[@class='footer']/div/div/div[3]/div/ul/li[1]/a");
    private static final By footerSharkBrandLink = By.xpath("//div[@class='footer']/div/div/div[3]/div/ul/li[2]/a");
    private static final By footerSharkNinjaCorporateLink = By.xpath("//a[text()='" + Props.getProp("footer.sharkninja.corporate") + "']");
    private static final By footerLocationsLink = By.xpath("//div[@class='footer']/div/div/div[3]/div/ul/li[4]/a");
    private static final By footerPatentsLink = By.xpath("//div[@class='footer']/div/div/div[3]/div/ul/li[5]/a");
    private static final By footerCareersLink = By.xpath("//a[text()='" + Props.getProp("footer.careers") + "']");
    private static final By footerSocialHeading = By.xpath("//div[@class='footer']/div/div/div[4]/h2");
    private static final By leftTogglebtn = By.xpath("//button[starts-with(@class,'hamburger-menu')]");
    private static final Logger log = LoggerFactory.getLogger(HomePageSNUS.class);
    private final By footerRegisterMyGuaranteeLink = By.xpath("//a[text()='" + Props.getProp("footer.register.guaranty") + "']");
    private final By footerRegisterMyWarrantyLink = By.xpath("//a[text()='" + Props.getProp("footer.register.warranty") + "']");

    public String getLogInText() {
        String text = "";

        try {
            text = webDriver.findElement(logInText).getText().trim();
            log.info("Text found over sign in modal which is -->" + text);
        } catch (Exception e) {
            log.error("Unable to get text \"Sign In\" from the sign modal over SNCA");
            e.printStackTrace();
        }
        return text;
    }

    public String getErrorMessageFromAlertOnSignInPopUp() {
        String text = "";

        try {
            text = getTextFor(errorMessageInAlert).trim();
            log.info("Got error message when providing valid email and valid password but account doesn't exist which is-->" + text);
        } catch (Exception e) {
            log.error("Unable to get the text when from alert class over signIn pop up provided while logging-->" + e.getMessage());
            e.printStackTrace();
        }
        return text;
    }

    public CreateNewAccountPageSNCA clickRegisterNowButton() {
        CreateNewAccountPageSNCA obj = null;
        try {
            elementToBeClickable(registerNowBtn).click();
            obj = new CreateNewAccountPageSNCA();
            log.info("Successfully clicked on \"Register Now\" button");
        } catch (ElementClickInterceptedException e1) {
            log.error("ElementClickInterceptedException is there trying one more time");
            elementToBeClickable(registerNowBtn).click();
            obj = new CreateNewAccountPageSNCA();
            log.info("In 2nd time Successfully clicked on \"Register Now\" button");
        } catch (Exception e) {
            log.error("Unable to click on \"Register Now\" button due to-->" + e.getMessage());
            e.printStackTrace();
        }
        return obj;
    }

    public boolean isLogoDisplayed() {
        boolean flag = false;
        try {
            flag = wait.until(ExpectedConditions.presenceOfElementLocated(sharkLogo)).isDisplayed();
            log.info("Shark logo is displayed");
        } catch (Exception e) {
            log.error("Some exception occurred while showing shark logo-->>" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean navigateToSharkCAHomePage() {
        boolean flag = false;
        try {
            if(UrlBuilder.isMobile())
            {
                new Actions(getWebDriver()).moveToElement(wait.until(ExpectedConditions.visibilityOfElementLocated(sharkCALogoMobile))).click().build().perform();
            }
            else
            {
                new Actions(getWebDriver()).moveToElement(wait.until(ExpectedConditions.visibilityOfElementLocated(sharkCALogo))).click().build().perform();
            }
            log.info("Shark logo is clicked");
            flag = true;

        } catch (Exception e) {
            log.error("Some exception occurred while click shark logo-->>" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean clickSharkCALogo() {
        boolean flag = false;
        try {
            new Actions(getWebDriver()).moveToElement(wait.until(ExpectedConditions.visibilityOfElementLocated(sharkCaCustomerLogo))).click().build().perform();
            log.info("Shark logo is clicked");
            flag = true;

        } catch (Exception e) {
            log.error("Some exception occurred while click shark logo-->>" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean openHamburgerMenu() {
        boolean flag = false;
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(leftTogglebtn));
            if (!element.getAttribute("class").toLowerCase().contains("open")) {
                wait.until(ExpectedConditions.elementToBeClickable(element)).click();
                log.info("Successfully opened the hamburger menu ");
                flag = true;
            } else {
                log.info("Hamburger menu is already opened ");
                flag = true;
            }
        } catch (Exception e) {
            log.error("Some exception arises while opening the hamburger menu which is-->>" + ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
        return flag;
    }

    public boolean isProductsLinkDisplayed() {
        openHamburgerMenu();
        boolean flag = false;
        if (Props.getProp("select.language.to.test").equalsIgnoreCase("en")) {
            try {
                flag = webDriver.findElement(productsLink).isDisplayed();
                log.info("Products link is displayed");
            } catch (Exception e) {
                log.error("Some exception occurred while showing products link-->>" + e.getMessage());
                e.printStackTrace();
            }
        } else {
            try {
                flag = webDriver.findElement(productsLinkFrance).isDisplayed();
                log.info("Products link france is displayed");

            } catch (Exception e) {
                log.error("Some exception occurred while showing products link france-->>" + e.getMessage());
                e.printStackTrace();
            }
        }
        return flag;
    }

    public boolean isRegisterMyWarrantyLinkDisplayed() {
        boolean flag = false;
        if (Props.getProp("select.language.to.test").equalsIgnoreCase("en")) {
            try {
                flag = webDriver.findElement(registerWarrantyHeaderEn).isDisplayed();
                log.info("register my warranty link is displayed");

            } catch (Exception e) {
                log.error("Some exception occurred while showing register my warranty link-->>" + e.getMessage());
                e.printStackTrace();
            }
        } else {
            try {
                flag = webDriver.findElement(registerWarrantyHeaderFR).isDisplayed();
                log.info("register my warranty link france is displayed");

            } catch (Exception e) {
                log.error("Some exception occurred while showing register my warranty france-->>" + e.getMessage());
                e.printStackTrace();
            }
        }
        return flag;
    }

    public boolean isTechnologiesLinkDisplayed() {
        boolean flag = false;
        try {
            flag = webDriver.findElement(technologiesLink).isDisplayed();
            log.info("technologies link is displayed");

        } catch (Exception e) {
            log.error("Some exception occurred while showing technologies link-->>" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean isPartsAccessoriesLinkDisplayed() {
        boolean flag = false;
        try {
            flag = webDriver.findElement(partsAndAccessoriesLink).isDisplayed();
            log.info("Parts and accessories link is displayed");

        } catch (Exception e) {
            log.error("Some exception occurred while showing parts and accessories link-->>" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean isNinjaPartsAccessoriesLinkDisplayed() {
        boolean flag = false;
        try {
            flag = webDriver.findElement(ninjaPartsAndAccessories).isDisplayed();
            log.info("Ninja Parts and accessories link is displayed");

        } catch (Exception e) {
            log.error("Some exception occurred while showing ninja parts and accessories link-->>" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean isCustomerCareLinkDisplayed() {
        boolean flag = false;
        if (Props.getProp("select.language.to.test").equalsIgnoreCase("en")) {
            try {
                flag = webDriver.findElement(customerCareLinkHeaderEn).isDisplayed();
                log.info("Customer care link is displayed");

            } catch (Exception e) {
                log.error("Some exception occurred while showing customer care link-->>" + e.getMessage());
                e.printStackTrace();
            }
        } else {
            try {
                flag = webDriver.findElement(customerCareLinkHeaderFr).isDisplayed();
                log.info("Customer care link is displayed");

            } catch (Exception e) {
                log.error("Some exception occurred while showing customer care link-->>" + e.getMessage());
                e.printStackTrace();
            }
        }
        return flag;
    }

    public boolean isRecipesLinkDisplayed() {
        boolean flag = false;
        if (Props.getProp("select.language.to.test").equalsIgnoreCase("en")) {
            try {
                flag = webDriver.findElement(recipesLinkHeaderEn).isDisplayed();
                log.info("Recipes link is displayed");

            } catch (Exception e) {
                log.error("Some exception occurred while showing Recipes link-->>" + e.getMessage());
                e.printStackTrace();
            }
        } else {
            try {
                flag = webDriver.findElement(recipesLinkHeaderFr).isDisplayed();
                log.info("Recipes link is displayed");

            } catch (Exception e) {
                log.error("Some exception occurred while showing Recipes link-->>" + e.getMessage());
                e.printStackTrace();
            }
        }
        return flag;
    }

    public boolean isSupportLinkDisplayed() {
        boolean flag = false;
        try {
            flag = webDriver.findElement(supportLink).isDisplayed();
            log.info("Support link is displayed");

        } catch (Exception e) {
            log.error("Some exception occurred while showing support link-->>" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean isOffersLinkDisplayed() {
        boolean flag = false;
        try {
            flag = webDriver.findElement(offersLink).isDisplayed();
            log.info("Support link is displayed");

        } catch (Exception e) {
            log.error("Some exception occurred while showing support link-->>" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean isTipsAndAdviceLinkDisplayed() {
        boolean flag = false;
        if (Props.getProp("select.language.to.test").equalsIgnoreCase("en")) {
            try {
                flag = webDriver.findElement(tipsAndAdviceLinkHeaderEn).isDisplayed();
                log.info("Tips and advice link is displayed");

            } catch (Exception e) {
                log.error("Some exception occurred while showing tips and advice link-->>" + e.getMessage());
                e.printStackTrace();
            }
        } else {
            try {
                flag = webDriver.findElement(tipsAndAdviceLinkHeaderFr).isDisplayed();
                log.info("Tips and advice link is displayed");

            } catch (Exception e) {
                log.error("Some exception occurred while showing tips and advice link-->>" + e.getMessage());
                e.printStackTrace();
            }
        }

        return flag;
    }

    public boolean isAdrianLinkDisplayed() {
        boolean flag = false;
        try {
            flag = webDriver.findElement(adrianaLink).isDisplayed();
            log.info("Adriana link is displayed");

        } catch (Exception e) {
            log.error("Some exception occurred while showing adriana link-->>" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean clickProductsLink() {
        boolean flag = false;
        try {
            clickByElement(productsLink);
            log.info("Successfully clicked on products link");
            flag = true;
        } catch (Exception e) {
            log.error("Unable to click on products link -->" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean hoverProductsLink() {
        boolean flag = false;
        if (UrlBuilder.isMobile()) {
            openHamburgerMenu();
            wait.until(ExpectedConditions.elementToBeClickable(productsLink)).click();
//            wait.until(ExpectedConditions.elementToBeClickable(productsLink)).click();
            wait.until(ExpectedConditions.elementToBeClickable(selectSharkVacuumMobile)).click();
            flag = true;
        } else {
            try {
                hoverOnElement(webDriver.findElement(productsLink));
                log.info("Successfully hovered on products link");
                flag = true;
            } catch (Exception e) {
                log.error("Unable to hover on products link -->" + e.getMessage());
                e.printStackTrace();
            }
        }
        return flag;
    }

    public boolean clickPartsAndAccessoriesLink() {
        boolean flag = false;
        try {

            if (UrlBuilder.isMobile()) {
                openHamburgerMenu();
                waitForExpectedElement(partsAndAccessoriesLinkMobile).click();
                waitForExpectedElement(HomePageSNCA.mobileCloseMenu).click();
                log.info("Successfully clicked on parts and accessories link");
                flag = true;
            }
            else
            {
                waitForExpectedElement(partsAndAccessoriesLink).click();
                log.info("Successfully clicked on parts and accessories link");
                flag = true;
            }
        } catch (Exception e) {
            log.error("Unable to click on parts and accessories link -->" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean clickCustomerCareLink() {
        boolean flag = false;
        IsPageLoaded.waitAllRequest();
        if(UrlBuilder.isMobile()){
            openHamburgerMenu();
        }
        if (Props.getProp("select.language.to.test").equalsIgnoreCase("en")) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(customerCareLinkHeaderEn)).click();
                log.info("Successfully clicked on customer care link");
                flag = true;
            } catch (Exception e) {
                log.error("Unable to click on customer care link -->" + e.getMessage());
                e.printStackTrace();
            }
        } else {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(customerCareLinkHeaderFr)).click(); // Redirecting towards English page for now
                log.info("Successfully clicked on customer care link");
                flag = true;
            } catch (Exception e) {
                log.error("Unable to click on customer care link -->" + e.getMessage());
                e.printStackTrace();
            }
        }

        return flag;
    }

    public boolean clickTipsAndAdviceLink() {
        boolean flag = false;
        IsPageLoaded.waitAllRequest();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(closeButton)).click();
        if(UrlBuilder.isMobile()){
            openHamburgerMenu();
        }
        if (Props.getProp("select.language.to.test").equalsIgnoreCase("en")) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(tipsAndAdviceLinkHeaderEn)).click();
                log.info("Successfully clicked on tips and advice link");
                flag = true;
            } catch (Exception e) {
                log.error("Unable to click on tips and advice link -->" + e.getMessage());
                e.printStackTrace();
            }
        } else {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(tipsAndAdviceLinkHeaderFr)).click(); // Redirecting towards English page for now
                log.info("Successfully clicked on tips and advice link");
                flag = true;
            } catch (Exception e) {
                log.error("Unable to click on tips and advice link -->" + e.getMessage());
                e.printStackTrace();
            }
        }

        return flag;
    }

    public boolean clickAdrianaLink() {
        boolean flag = false;
        try {
            clickByElement(adrianaLink);
            log.info("Successfully clicked on adriana link");
            flag = true;
        } catch (Exception e) {
            log.error("Unable to click on adriana link -->" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean verifyProductsPage() {
        boolean flag = false;
        return flag;
    }

    public boolean verifyPartsAndAccessoriesPage() {
        boolean flag = false;
        return flag;
    }

    public boolean verifyCustomerCarePage() {
        boolean flag = false;
        return flag;
    }

    public boolean verifyTipsAndAdvicePage() {
        boolean flag = false;
        return flag;
    }

    public boolean verifyAdrianaPage() {
        boolean flag = false;
        return flag;
    }

    public boolean isFooterShopHeadingDisplayed() {
        boolean flag = false;
        try {
            flag = webDriver.findElement(footerShopHeading).isDisplayed() && webDriver.findElement(footerShopHeading).getText().contentEquals(Props.getProp("footer.shop.heading"));
            log.info("Footer shop heading is displayed");

        } catch (Exception e) {
            log.error("Some exception occurred while showing footer shop heading-->>" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean isFooterVacuumsLinkDisplayed() {
        boolean flag = false;
        try {
            flag = webDriver.findElement(footerVacuumsLink).isDisplayed() && webDriver.findElement(footerVacuumsLink).getText().contentEquals(Props.getProp("footer.vacuums"));
            log.info("Footer vacuum link is displayed");

        } catch (Exception e) {
            log.error("Some exception occurred while showing footer vacuum link-->>" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean isFooterMopsAndIronsLinkDisplayed() {
        boolean flag = false;
        if (webDriver.getCurrentUrl().contains("uat")) {
            try {
                flag = webDriver.findElement(footerMopsLink).isDisplayed() && webDriver.findElement(footerMopsLink).getText().contentEquals(Props.getProp("footer.mops.iron"));
                log.info("Footer mops and iron link is displayed");

            } catch (Exception e) {
                log.error("Some exception occurred while showing footer mops and iron link-->>" + e.getMessage());
                e.printStackTrace();
            }
        } else {
            try {
                flag = webDriver.findElement(footerMopsLink).isDisplayed() && webDriver.findElement(footerMopsLink).getText().contentEquals(Props.getProp("footer.mops"));
                log.info("Footer mops and iron link is displayed");

            } catch (Exception e) {
                log.error("Some exception occurred while showing footer mops and iron link-->>" + e.getMessage());
                e.printStackTrace();
            }
        }
        return flag;
    }

    public boolean isFooterPartsAndAccessoriesLinkDisplayed() {
        boolean flag = false;
        try {
            flag = webDriver.findElement(footerPartsAndAccessoriesLink).isDisplayed() && webDriver.findElement(footerPartsAndAccessoriesLink).getText().contentEquals(Props.getProp("footer.parts.accessories")) || webDriver.findElement(footerPartsAndAccessoriesLink).getText().contentEquals("Parts & accessories");
            log.info("Footer parts and accessories link is displayed");

        } catch (Exception e) {
            log.error("Some exception occurred while showing footer parts and accessories link-->>" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean isFooterSupportHeadingDisplayed() {
        boolean flag = false;
        try {
            flag = webDriver.findElement(footerSupportHeading).isDisplayed() && webDriver.findElement(footerSupportHeading).getText().contentEquals(Props.getProp("footer.support.heading"));
            log.info("Footer support heading is displayed");

        } catch (Exception e) {
            log.error("Some exception occurred while showing footer support heading-->>" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean isFooterFoodiFamilyCookingLinkDisplayed() {
        boolean flag = false;
        try {
            flag = webDriver.findElement(footerFoodiFamilyCookingLink).isDisplayed() && webDriver.findElement(footerFoodiFamilyCookingLink).getText().contentEquals(Props.getProp("footer.foodi.family.cooking"));
            log.info("Foodi family cooking link is displayed");

        } catch (Exception e) {
            log.error("Some exception occurred while showing Foodi family cooking link-->>" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean isFooterCookwareLinkDisplayed() {
        boolean flag = false;
        try {
            flag = webDriver.findElement(footerCookwareLink).isDisplayed();
            log.info("Cookware link is displayed");

        } catch (Exception e) {
            log.error("Some exception occurred while showing Cookware link-->>" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean isFooterBlendersLinkDisplayed() {
        boolean flag = false;
        try {
            flag = webDriver.findElement(footerBlendersLink).isDisplayed();
            log.info("Blenders link is displayed");

        } catch (Exception e) {
            log.error("Some exception occurred while showing Blenders link-->>" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean isFooterCoffeeDisplayed() {
        boolean flag = false;
        try {
            flag = webDriver.findElement(footerCoffeeLink).isDisplayed();
            log.info("Coffee link is displayed");

        } catch (Exception e) {
            log.error("Some exception occurred while showing Coffee link-->>" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean isFooterFAQsLinkDisplayed() {
        boolean flag = false;
        try {
            flag = webDriver.findElement(footerFAQsLink).isDisplayed() && webDriver.findElement(footerFAQsLink).getText().contentEquals("FAQs") || webDriver.findElement(footerFAQsLink).getText().contentEquals("Support & FAQs");
            log.info("Footer FAQs link is displayed");

        } catch (Exception e) {
            log.error("Some exception occurred while showing footer FAQs link-->>" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean isFooterWarrantyInformationLinkDisplayed() {
        boolean flag = false;
        try {
            flag = webDriver.findElement(footerWarrantyInformationLink).isDisplayed() && webDriver.findElement(footerWarrantyInformationLink).getText().contentEquals(Props.getProp("footer.warranty.information"));
            log.info("Footer Warranty Information link is displayed");

        } catch (Exception e) {
            log.error("Some exception occurred while showing footer Warranty Information link-->>" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean isFooterOrderStatusLinkDisplayed() {
        boolean flag = false;
        try {
            flag = webDriver.findElement(footerOrderStatusLink).isDisplayed() && webDriver.findElement(footerOrderStatusLink).getText().contentEquals(Props.getProp("footer.order.status"));
            log.info("Footer order status link is displayed");

        } catch (Exception e) {
            log.error("Some exception occurred while showing footer order status link-->>" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean isFooterRegisterMyGuaranteeLinkDisplayed() {
        boolean flag = false;
        if (webDriver.getCurrentUrl().contains("uat")) {
            try {
                flag = webDriver.findElement(footerRegisterMyWarrantyLink).isDisplayed() && webDriver.findElement(footerRegisterMyWarrantyLink).getText().contentEquals(Props.getProp("footer.register.warranty"));
                log.info("Footer register my warranty link is displayed");

            } catch (Exception e) {
                log.error("Some exception occurred while showing footer register my warranty link-->>" + e.getMessage());
                e.printStackTrace();
            }
        } else {
            try {
                flag = webDriver.findElement(footerRegisterMyGuaranteeLink).isDisplayed() && webDriver.findElement(footerRegisterMyGuaranteeLink).getText().contentEquals(Props.getProp("footer.register.guaranty"));
                log.info("Footer register my guarantee link is displayed");

            } catch (Exception e) {
                log.error("Some exception occurred while showing footer register my guarantee link-->>" + e.getMessage());
                e.printStackTrace();
            }
        }
        return flag;
    }

    public boolean isFooterRegisterMyWarrantyLinkDisplayed() {
        boolean flag = false;
        try {
            flag = webDriver.findElement(footerRegisterMyWarrantyLink).isDisplayed() && webDriver.findElement(footerRegisterMyWarrantyLink).getText().contentEquals(Props.getProp("footer.register.warranty"));
            log.info("Footer register my warranty link is displayed");

        } catch (Exception e) {
            log.error("Some exception occurred while showing footer register my warranty link-->>" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean isFooterMyAccountLinkDisplayed() {
        boolean flag = false;
        try {
            flag = webDriver.findElement(footerMyAccountLink).isDisplayed() && webDriver.findElement(footerMyAccountLink).getText().contentEquals(Props.getProp("footer.my.account"));
            log.info("Footer My Account link is displayed");

        } catch (Exception e) {
            log.error("Some exception occurred while showing footer My Account link-->>" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean isFooterUnsubscribeLinkDisplayed() {
        boolean flag = false;
        try {
            flag = webDriver.findElement(footerUnsubscribeLink).isDisplayed() && webDriver.findElement(footerUnsubscribeLink).getText().contentEquals(Props.getProp("footer.unsubscribe"));
            log.info("Footer Unsubscribe link is displayed");

        } catch (Exception e) {
            log.error("Some exception occurred while showing footer unsubscribe link-->>" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean isFooterAboutHeadingDisplayed() {
        boolean flag = false;
        try {
            flag = webDriver.findElement(footerAboutHeading).isDisplayed() && webDriver.findElement(footerAboutHeading).getText().contentEquals(Props.getProp("footer.about"));
            log.info("Footer About heading is displayed");

        } catch (Exception e) {
            log.error("Some exception occurred while showing footer About heading-->>" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean isFooterTechnologiesLinkDisplayed() {
        boolean flag = false;
        try {
            flag = webDriver.findElement(footerTechnologiesLink).isDisplayed() && webDriver.findElement(footerTechnologiesLink).getText().contentEquals(Props.getProp("footer.technologies"));
            log.info("Footer technologies link is displayed");

        } catch (Exception e) {
            log.error("Some exception occurred while showing footer Technologies link-->>" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean isFooterSharkBrandLinkDisplayed() {
        boolean flag = false;
        try {
            flag = webDriver.findElement(footerSharkBrandLink).isDisplayed() && webDriver.findElement(footerSharkBrandLink).getText().contentEquals(Props.getProp("footer.shark.brand"));
            log.info("Footer shark brand link is displayed");

        } catch (Exception e) {
            log.error("Some exception occurred while showing footer Shark Brand link-->>" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean isFooterSharkNinjaCorporateLinkDisplayed() {
        boolean flag = false;
        try {
            flag = webDriver.findElement(footerSharkNinjaCorporateLink).isDisplayed() && webDriver.findElement(footerSharkNinjaCorporateLink).getText().contentEquals(Props.getProp("footer.sharkninja.corporate"));
            log.info("Footer Shark Ninja corporate link is displayed");

        } catch (Exception e) {
            log.error("Some exception occurred while showing footer Shark Ninja corporate link-->>" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean isFooterLocationsLinkDisplayed() {
        boolean flag = false;
        try {
            flag = webDriver.findElement(footerLocationsLink).isDisplayed() && webDriver.findElement(footerLocationsLink).getText().contentEquals(Props.getProp("footer.locations"));
            log.info("Footer locations link is displayed");

        } catch (Exception e) {
            log.error("Some exception occurred while showing footer locations link-->>" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean isFooterPatentsLinkDisplayed() {
        boolean flag = false;
        try {
            flag = webDriver.findElement(footerPatentsLink).isDisplayed() && webDriver.findElement(footerPatentsLink).getText().contentEquals(Props.getProp("footer.patents"));
            log.info("Footer patents link is displayed");

        } catch (Exception e) {
            log.error("Some exception occurred while showing footer patents link-->>" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean isFooterCareersLinkDisplayed() {
        boolean flag = false;
        try {
            flag = webDriver.findElement(footerCareersLink).isDisplayed() && webDriver.findElement(footerCareersLink).getText().contentEquals(Props.getProp("footer.careers"));
            log.info("Footer careers link is displayed");

        } catch (Exception e) {
            log.error("Some exception occurred while showing footer careers link-->>" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean isFooterSocialHeadingDisplayed() {
        boolean flag = false;
        try {
            flag = webDriver.findElement(footerSocialHeading).isDisplayed() && webDriver.findElement(footerSocialHeading).getText().contentEquals("Social");
            log.info("Footer Social heading is displayed");

        } catch (Exception e) {
            log.error("Some exception occurred while showing footer Social heading-->>" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean clickUprightVacuum() {
        boolean flag = false;
        By uprightVacuumOption = uprightVacuum;
        switch (Props.getProp("select.language.to.test")) {
            case "EN": {
                if (UrlBuilder.isMobile()) {
                    uprightVacuumOption = sharkHandledVaccumMobile;
                    if(getWebDriver().getCurrentUrl().toLowerCase().contains("ninjakitchen"))
                    {
                        uprightVacuumOption = uprightVaccumFrMobileShark;
                    }
                } else {
                    uprightVacuumOption = uprightVacuum;
                }
                break;
            }
            case "FR": {
                if (UrlBuilder.isMobile()) {
                    uprightVacuumOption = uprightVaccumFrMobileShark;
                    if(getWebDriver().getCurrentUrl().toLowerCase().contains("sharkclean"))
                    {
                        uprightVacuumOption = sharkHandledVaccumMobile;
                    }
                } else {
                    uprightVacuumOption = uprightVacuumFr;
                }
            }
        }
        try {
            Thread.sleep(1000);
            wait.until(ExpectedConditions.elementToBeClickable(uprightVacuumOption)).click();

//            if(UrlBuilder.isMobile() && getWebDriver().getCurrentUrl().toLowerCase().contains("sharkclean") && Props.getProp("select.language.to.test").equals("FR"))
//            {
//                wait.until(ExpectedConditions.elementToBeClickable(backButtonHamburger)).click();
//                wait.until(ExpectedConditions.elementToBeClickable(backButtonHamburger)).click();
//                waitForExpectedElement(HomePageSNCA.mobileCloseMenu).click();
//            }

            log.info("Successfully clicked on upright vacuum");
            flag = true;
        } catch (Exception e) {
            log.error("Unable to click on upright vacuum -->" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean clickUprightVacuumForShark() {
        boolean flag = false;
        By uprightVacuumOption = uprightVacuum;
        switch (Props.getProp("select.language.to.test")) {
            case "EN": {
                if (UrlBuilder.isMobile()) {
                    uprightVacuumOption = sharkHandledVaccumMobile;
                } else {
                    uprightVacuumOption = uprightVaccumForShark;
                }
                break;
            }
            case "FR": {
                if (UrlBuilder.isMobile()) {
                    uprightVacuumOption = uprightVacuumFrMobile;
                    if(getWebDriver().getCurrentUrl().toLowerCase().contains("sharkclean"))
                    {
                        uprightVacuumOption = sharkHandledVaccumMobile;
                    }
                } else {
                    uprightVacuumOption = uprightVaccumForSharkFr;
                }
            }
        }
        try {
            Thread.sleep(1000);
            wait.until(ExpectedConditions.elementToBeClickable(uprightVacuumOption)).click();

//            if(UrlBuilder.isMobile() && getWebDriver().getCurrentUrl().toLowerCase().contains("sharkclean") && Props.getProp("select.language.to.test").equals("FR"))
//            {
//                wait.until(ExpectedConditions.elementToBeClickable(backButtonHamburger)).click();
//                wait.until(ExpectedConditions.elementToBeClickable(backButtonHamburger)).click();
//                waitForExpectedElement(HomePageSNCA.mobileCloseMenu).click();
//            }

            log.info("Successfully clicked on upright vacuum");
            flag = true;
        } catch (Exception e) {
            log.error("Unable to click on upright vacuum -->" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean toClickCartLink() {
        boolean flag = false;
        if(UrlBuilder.isMobile()){
            if(openHamburgerMenu()){
                wait.until(ExpectedConditions.elementToBeClickable(HomePageSNCA.mobileCloseMenu)).click();
            }
        }
        try {
            waitForExpectedElement(miniCartBag).click();
            log.info("Successfully clicked on cart bag");
            flag = true;
        } catch (Exception e) {
            log.error("Unable to click on cart bag -->" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public String toGetEmptyCartMessage() {
        return waitForExpectedElement(emptyCartMessage).getText().trim();
    }

    public String toGetEmptyCartMessageIfNoItemAvailable() throws InterruptedException {
            int cartCount = -1;
            String text = "Not Found";
            //wait.withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofSeconds(2)).ignoring(Exception.class);
            int count = -1;
            do {
                try {

                    cartCount = Integer.parseInt(
                            wait.until(ExpectedConditions.visibilityOfElementLocated(cartItemCount)).getText().trim());
                    if(cartCount == 0){
                        count++;
                        log.info("the Cart is Empty and the value of cartCount must be Zero (0) which is " + cartCount);
                    break;
                } else {
                        try {
                            List<WebElement> removeProductBtnList = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(removeButtonClickCA));
                            System.out.println(" Total no of categories Products are " + removeProductBtnList.size());
                            for (int i = 0; i < removeProductBtnList.size(); i++) {
                                WebElement removeButton = getWebDriver().findElement(removeButtonClickCA);
                                new Actions(getWebDriver()).moveToElement(removeButton).build().perform();
                                wait.until(ExpectedConditions.elementToBeClickable(removeButton)).click();
                                webDriver.navigate().refresh();
                                IsPageLoaded.waitAllRequest();
                                log.info("All the items have been cleared from the Cart");
                                count++;
                                System.out.println("the count is " + count);
                            }

//                            text = wait.until(ExpectedConditions.presenceOfElementLocated(emptyCartMessage)).getText().trim();
//                            if (text.contains(Props.getMessage("shopping.cart.is.Empty.msg"))) {
//                                log.info("cart Empty Text Extracted From homePage Page is-->" + text);

                                try {
                                    clickByElement(accountIcon);
                                    log.info("successfully clicked on Cart Icon-->");
                                    Thread.sleep(2000);
                                    clickByElement(signoutLink);
                                    log.info("successfully clicked sign out link-->");
                                    IsPageLoaded.waitAllRequest();

                                } catch (Exception e) {
                                    log.error("Unable to click signout link-->" + e.getMessage());
                                    e.printStackTrace();
                                }


                            count++;

                        } catch (TimeoutException timeoutException) {
                            log.error("TimeOut occured due to remove order button is either not clickable or not visible-->>" + ExceptionUtils.getStackTrace(timeoutException));
                            timeoutException.printStackTrace();
                            count++;
                        } catch (NotFoundException e1) {
                            log.error("remove order button not found please check strategy-->>" + ExceptionUtils.getStackTrace(e1));
                            e1.printStackTrace();
                            break;
                        } catch (Exception e) {
                            log.error("Unable to click on remove order button due to-->>" + e.getMessage() + "\n");
                            log.error(ExceptionUtils.getStackTrace(e));
                            break;
                        }
                    }
                } catch (Exception e) {
                    log.error("Unable to click on remove item button located at position-->>" + " due to-->" + e.getMessage());
                    e.printStackTrace();
                    break;
                }
            }

            while (count==0 );
            if(count == 0) {
                System.out.println("no of count after while loop " + count);
                text = wait.until(ExpectedConditions.visibilityOfElementLocated(emptyCartMessage)).getText().trim();
                //text = wait.until(ExpectedConditions.visibilityOfElementLocated(noItemInCartMesgForUK)).getText().trim();
                log.info("cart Empty Text Extracted From homePage Page is-->" + text);
                try {
                    clickByElement(accountIcon);
                    log.info("successfully clicked on Cart Icon-->");
                    Thread.sleep(2000);
                    clickByElement(signoutLink);
                    log.info("successfully clicked sign out link-->");
                    IsPageLoaded.waitAllRequest();
                } catch (Exception e) {
                    log.error("Unable to click signout link-->" + e.getMessage());
                    e.printStackTrace();
                }
            }
            return text;
        }

    public boolean verifyProductList() {
        boolean flag = false;
        try {
            if (webDriver.findElements(productList).size() != 0) {
                flag = webDriver.findElement(productList).isDisplayed();
            } else {
                flag = webDriver.findElement(productListRow).isDisplayed();
            }
            log.info("Successfully verified product list");
        } catch (Exception e) {
            log.error("Unable to click on upright vacuum -->" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean clickRegisterMyGuaranteeFooter() {
        boolean flag = false;
        try {
            if (webDriver.findElements(footerRegisterMyGuaranteeLink).size() > 0) {
                clickElementByQueryJSExecutor(webDriver.findElement(footerRegisterMyGuaranteeLink));
//                clickByElement(footerRegisterMyGuaranteeLink);
            } else {
                waitForExpectedElement(footerRegisterMyWarrantyLink).click();
            }
            log.info("Successfully clicked on register my guarantee link in footer");
            flag = true;
        } catch (Exception e) {
            log.error("Unable to click on register my guarantee link in footer -->" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean verifyPartsAndAccessories() {
        boolean flag = false;
        try {
            if (webDriver.findElements(productList).size() != 0) {
                flag = webDriver.findElement(productList).isDisplayed();
            } else {
                flag = webDriver.findElement(productListRow).isDisplayed();
            }
            log.info("Successfully verified product list");
        } catch (Exception e) {
            log.error("Unable to click on upright vacuum -->" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public void verifyFooterLinks() throws NullPointerException {
//        boolean flag = false;
        IsPageLoaded.waitAllRequest();
        SoftAssert softAssert = new SoftAssert();
        List<WebElement> availableFooterLinks = getAllElements(footerAllLinks);
        int count = 1;
        for (WebElement footerLink : availableFooterLinks) {
            if(count == 16)
                break;
            softAssert.assertTrue(footerLink.getText().trim().equalsIgnoreCase(Props.getProp("footer.link" + count).trim()), "Footer link is not shown-->>" + Props.getProp("footer.link" + count));
            count++;
            try {
                if (Props.getProp("footer.link" + count).length() == 0)
                    break;
            } catch (NullPointerException e) {
                log.error("Key does not exist -->" + e.getMessage());
                e.printStackTrace();
                break;
            }
        }
        softAssert.assertAll();
    }
}