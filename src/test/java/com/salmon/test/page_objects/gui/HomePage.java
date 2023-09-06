package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import com.salmon.test.framework.helpers.utils.RandomGenerator;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class HomePage extends PageObject {

    public static final By mobileHeaderSignInLink = By.xpath("//ul[@class='navbar-nav main-navigation-list']/li[6]/a[1]");
    public static final By LeftTogglebtn = By.xpath("//button[@data-target='#globalnav' and contains(@class,'navbar-toggle')]");

    private static final By miniCartProductList = By.xpath("//div[@id='miniCart']/div/div/div");
    //private static final By miniCartProductList = By.xpath("//div[@id='miniCart']/div/div/div/div[2]/a");
    //User Registration Locator
    private static final By CreateAccountLink = By.xpath("//a[@class=\"btn btn-primary\"]");

    private static final By signOutButtonUK = By.xpath("//a[@class=\"my-account-link my-account-logout\"]/span");
    private static final Logger log = LoggerFactory.getLogger(HomePage.class);
    static By selectAccountForm = By.xpath("//select[@class='form-control']");
    // Header Links

    private  By selectProductDynamic;
    public static String SearchProduct;
    private final By headerRegisterLink = By
            .xpath("//a[@class='ish-siteHeader-myAccountUtilitiesMenu-myAccount-register']/span");
    private static final By cartIconUk = By.xpath("//span[@class=\"shopping-cart-link-qtv\"]");

    private static final By clickOnIndividualRemoveButton = By.cssSelector(".shopping-cart-container .column-price .btn-tool");
    private static final By clickOnRemoveAllButton = By.xpath("//*[@class=\"shopping-cart-container\"]/../div/div[5]/a");
    private final By mobileHeaderRegisterLink = By.xpath("//ul[@class='navbar-nav main-navigation-list']/li[6]/a[2]");
    private final By headerSignInLink = By.xpath("//a[@class='my-account-links my-account-login']/span");
    private final By headerSignInLinkSharkDE = By.xpath("//div[@class='header-loginStatus-container hidden-xs']//a[1]");
    private final By rateAndReviewButton = By.xpath("//div[@class=\"bv_main_container\"]");

    private final By registerMyGuaranteeLink = By.xpath("//span[@class='header-product-registration-container hidden-xs']/a");
    private final By mobileRegisterMyGuaranteeLink = By.xpath("//ul[@class='navbar-nav main-navigation-list']/li[8]/a");
    private final By customerCareLink = By.xpath("//div[@class='col-sm-9 search-login-container']/span[3]/a");
    private final By mobileCustomerCareLink = By.xpath("//ul[@class='navbar-nav main-navigation-list']/li[7]/a");
    private final By tipsAndAdviceLink = By.xpath("//a[text()=\"Tips & Advice\"]");
    private final By offersLink = By.xpath("//li[@class='dropdown']/a/strong");
    private final By cartLink = By.xpath("//span[@class='shopping-cart-link-text']");
    private final By viewBasketButton = By.xpath("//*[@id=\"miniCart\"]/a");
    private static final By cartEmptyTextForUK = By.xpath("//*[@id=\"miniCart\"]");

    private static final By noItemInCartMesgForUK = By.xpath("//div[@class=\"empty-cart\"]/h2");

    private static final By emptyTextAfterItemDeletionUK = By.xpath("//*[@class=\"list-unstyled alert alert-danger\"]/li");

    private final By mobileCartCounter = By.xpath("//div[@class='mobile-cart-counter']");
    private final By mobileCartLink = By.xpath("//div[@class='user-links-container col-xs-3 hidden-sm hidden-md hidden-lg']/ul/li/a");
    private final By cartHeaderQuantity = By.xpath("//span[@class='shopping-cart-link-qtv']");
    private final By cartHeaderPrice = By.xpath("//span[@class='mini-cart-price']");
    private final By miniCart = By.xpath("//div[@id='miniCart']");

    private final By reviewText = By.xpath("//*[@class=\"bv-section-summary\"]//p");
    //Post Successful Login - links in header
    private final By headerMyAccountLink = By.xpath("//span[@class='header-loginStatus-container hidden-xs']/a[@class='my-account-link']");
    private final By mobileHeaderMyAccountLink = By.xpath("//select[@class='form-control']/option[1]");
    private final By headerLogoutLink = By.xpath("//span[@class='header-loginStatus-container hidden-xs']/a[@class='my-account-link my-account-logout']");
    private final By mobileHeaderLogoutLink = By.xpath("//select[@class='form-control']/option[7]");
    // Footer Links column 1 - added by Sumeet
    private final By sharkCleanText = By.xpath("//*[@class='col-sm-6 col-md-3 clearfix'][1]/h2");
    private final By aboutSharklink = By.xpath("//nav[@class='col-sm-6 col-md-3 clearfix'][1]/ul/li[1]/a");
    private final By tipsAndAdvice = By.xpath("//nav[@class='col-sm-6 col-md-3 clearfix'][1]/ul/li[2]/a");
    private final By customerCare = By.xpath("//nav[@class='col-sm-6 col-md-3 clearfix'][2]/ul[2]/li[1]/a");
    private final By termsAndPolicies = By.xpath("//a[contains(text(),'Terms and Conditions')]");
    private final By privacyNotice = By.xpath("(//a[contains(@href,'privacyPolicy.pagelet2-Page')])[1]");
    private final By ukModernSlaveryStatementLink = By.xpath("//nav[@class='col-sm-6 col-md-3 clearfix'][1]/ul/li[6]/a");
    private final By onlineDisputeResolution = By.xpath("//nav[@class='col-sm-6 col-md-3 clearfix'][1]/ul/li[7]/a");
    private final By independentBazaarVoiceReviews = By.xpath("//nav[@class='col-sm-6 col-md-3 clearfix'][1]/ul/li[8]/a");
    private final By sharkNinjaWarrantyFairProcessingNoticeLink = By
            .xpath("//nav[@class='col-sm-6 col-md-3 clearfix'][1]/ul/li[9]/a");
    private final By sharkNinjaCheckoutFairProcessingNoticeLink = By
            .xpath("//nav[@class='col-sm-6 col-md-3 clearfix'][1]/ul/li[10]/a");
    private final By sharkNinjaCareers = By.xpath("//nav[@class='col-sm-6 col-md-3 clearfix'][1]/ul/li[11]/a");
    private final By registerMyGuaranteeLinkFooter = By.xpath("//nav[@class='col-sm-6 col-md-3 clearfix'][2]/ul/li[1]/a");
    private final By logInLinkFooter = By.xpath("//nav[@class='col-sm-6 col-md-3 clearfix'][2]/ul/li[2]/a");
    private final By facebookIcon = By.xpath("//a[contains(@href,'facebook')]");
    private final By twitterIcon = By.xpath("//a[contains(@href,'twitter')]");
    private final By instagramIcon = By.xpath("//a[contains(@href,'instagram')]");
    private final By pinterestIcon = By.xpath("//a[contains(@href,'pinterest')]");
    private final By youtubeIcon = By.xpath("//a[contains(@href,'youtube')]");
    private final By newsletterEmailTxt = By.xpath("//input[@id='newsletter_email']");
    private final By offersCheckbox = By.xpath("//*[@class=\"checkbox\"]/label");
    private final By signupBtn = By.xpath("//button[@id='signup_button']");
    private final By unsubscribeLink = By.xpath("//a[@class='unsubscribe-link']");
    private final By unsubscribeEmailTxt = By.xpath("//a[@class='unsubscribe-link']");
    private final By unsubscribeBtn = By.xpath("//button[@id='unsubscribe_button']");
    private final By footerAllLinks = By.xpath("//*[@class='footer' or @class='footer shark-footer']//a");
    //Teaser Link
    private final By FiveYearsGuaranteeLink = By.xpath("//*[@id=\"Pagelet_h1sKAQMFQEoAAAFqry1CUUmN\"]/nav/a[1]/span");
    private final By FastFreeDeliveryLink = By.xpath("//*[@id=\"Pagelet_h1sKAQMFQEoAAAFqry1CUUmN\"]/nav/a[2]/span");
    private final By PriceMatchLink = By.xpath("//*[@id=\"Pagelet_h1sKAQMFQEoAAAFqry1CUUmN\"]/nav/a[3]/span");
    private final By signupsuccessmsg = By.xpath("//div[@class=\"signup-success-msg\"]/p");
    private final By unsubscriptionemailtextbox = By.xpath(" //input[@id='newsletter_email_unsubscribe']");
    private final By clickHereLink = By.xpath("//*[@class=\"container\"]/div/span/span/a");
    private final By unsubscribesuccessmessage = By.xpath("//div[@class=\"container\"]//strong");
    private final By invalidEmailmsg = By.xpath("//small[@data-bv-for='EmailSignupForm_Email'][1]");
    private final By invalidEmailEnteredmsg = By.xpath("//div[@class='signup-invalid-msg']/p");
    //Forgot Password locators
    private final By ForgotPasswordLink = By.xpath("//*[contains(@class,\"btn btn-link\")]");
    private final By invalidEmailerrormsg = By.xpath("//div[@class=\"section\"]//div/div[@class=\"alert alert-danger\"]");
    private final By invalid_email_login = By.xpath("//small[@data-bv-result='INVALID']");
    //Logout Locators
    private final By LogoutButoon = By.xpath("//span[@class=\"header-loginStatus-container hidden-xs\"]//a[2]//span");
    //Resend Link locator
    private final By account_not_confirmed_message = By.xpath("//form[@name='EmailConfirmForm']");
    private final By ratingAtPlpLevel = By.xpath("//*[@data-bv-product-id='WV200UK']/div/a");

    private final By Resend_link = By.xpath("//*[@id=\"emailConfirmationCheck\"]");
    private final By resend_link_success_message = By.xpath(" //div[@class=\"alert alert-success\"]//p");
    private final String billingEmailAddressData = RandomGenerator.randomEmailAddress(6);
    private final By termsAndPoliciesTitle = By.xpath("//span[contains(text(),'Terms and Policies')]");

    //-----Method Forgot Password------
    public void clickForgotPasswordLink() {
        try {
            waitForExpectedElement(ForgotPasswordLink).click();
            System.out.println("Forgot password link clicked");
        } catch (Exception e) {
            log.info("Some exception occurred while clicking on Forgot Password Link-->>" + e.getMessage());
        }
    }


    // --------------------- Methods Header ------------------------

    public void clickRegisterLink() {
        if (UrlBuilder.isDesktop() || UrlBuilder.isTablet()) {
            waitForExpectedElement(headerRegisterLink).click();
        } else if (UrlBuilder.isMobile()) {
            WebElement element = waitForExpectedElement(LeftTogglebtn);
            if (element.getAttribute("aria-expanded") == null || element.getAttribute("aria-expanded").equalsIgnoreCase("false")) {
                element.click();
            }
            waitForExpectedElement(mobileHeaderRegisterLink).click();
        }
    }

    public void clickSignInLink() {
        if (UrlBuilder.isDesktop() || UrlBuilder.isTablet()) {
//            if (Props.getProp("profile").contains("SharkDE")) {
//                waitForExpectedElement(headerSignInLinkSharkDE).click();
//            }

                waitForExpectedElement(headerSignInLink).click();

        } else if (UrlBuilder.isMobile()) {
            Actions actions = new Actions(getWebDriver());
            WebElement element = waitForExpectedElement(LeftTogglebtn);
            if (element.getAttribute("aria-expanded") == null || element.getAttribute("aria-expanded").equalsIgnoreCase("false")) {
                actions.moveToElement(element).build().perform();
                element.click();
            }
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(mobileHeaderSignInLink));
            actions.moveToElement(element).build().perform();
            element.click();
        }
    }

    public void clickRateAndReviewButton() {
        if (UrlBuilder.isDesktop() || UrlBuilder.isTablet()) {
            waitForExpectedElement(rateAndReviewButton).click();
        } else if (UrlBuilder.isMobile()) {
            Actions actions = new Actions(getWebDriver());
            WebElement element = waitForExpectedElement(LeftTogglebtn);
            if (element.getAttribute("aria-expanded") == null || element.getAttribute("aria-expanded").equalsIgnoreCase("false")) {
                actions.moveToElement(element).build().perform();
                element.click();
            }
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(mobileHeaderSignInLink));
            actions.moveToElement(element).build().perform();
            element.click();
        }
    }

    public void clickCreateLink() {
        try {
            waitForExpectedElement(CreateAccountLink).click();
        } catch (Exception e) {
            log.info("Some exception occurred while clicking on Create account link-->>" + e.getMessage());
        }
    }

    //Rita Singh Logout Functionality
    public void clickLogoutButton() {
        try {
            if (UrlBuilder.isDesktop()) {
                waitForExpectedElement(LogoutButoon).click();
                wait.until(ExpectedConditions.invisibilityOfElementLocated(LogoutButoon));
            } else if (UrlBuilder.isMobile() || UrlBuilder.isTablet()) {
                waitForExpectedElement(selectAccountForm).click();
                waitForExpectedElement(mobileHeaderLogoutLink).click();
                wait.until(ExpectedConditions.invisibilityOfElementLocated(mobileHeaderLogoutLink));
            }
        } catch (Exception e) {
            log.info("Some exception occurred while clicking on logout button-->>" + e.getMessage());
        }
    }

    public Boolean verifyLogout() {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
           log.info("getting current url" +webDriver.getCurrentUrl());
            flag = webDriver.getCurrentUrl().toLowerCase().contains("logout");
            log.info("Successfully logout");
        } catch (Exception e) {
            log.info("Some exception occurred while logout-->>" + e.getMessage());
        }
        return flag;
    }

    public String verifyAccountNotConfirmedMessage() {
        String text = "Not Found";
        try {
            text = wait.until(ExpectedConditions.visibilityOfElementLocated(account_not_confirmed_message)).getText().trim();
            log.info("Successfully found account not confirmed message which is--->>" + text);
        } catch (Exception e) {
            log.error("Some exception occurred while finding account not confirmed message-->>" + ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
        return text;
    }

    public void verifyRatingAtPlpLevel(String productSpecificSku) {
        IsPageLoaded.waitAllRequest();
        selectProductDynamic=By.xpath("//*[@data-bv-product-id='" + productSpecificSku + "']/div/a");
        log.info("Dyanmic xpath of the product has been created which is--->>" + selectProductDynamic);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(selectProductDynamic));
        SearchProduct = element.getText().trim();
        if(SearchProduct.contains("0.0")){
            log.info("The Rating of the product is still zero which is-->>" + SearchProduct);
        }
        else{
            log.info("Successfully found the rating the product which is-->>" + SearchProduct);
        }
    }

    public Boolean VerifyVerificationEmailSentMessage() {

        boolean flag = false;
        try {

            flag = waitForExpectedElement(resend_link_success_message).getText().contains(Props.getProp("verification.email.message"));
            log.info("Successfully sent verification email");

        } catch (Exception e) {
            log.info("Some exception occurred while sending verification email link-->>" + e.getMessage());
        }
        return flag;
    }

    public By VerifyResendVerificationLink() {

        try {
            waitForExpectedElement(Resend_link);
            log.info("Successfully found resend link");

        } catch (Exception e) {
            log.info("Some exception occurred while finding resend link-->>" + e.getMessage());
        }
        return Resend_link;
    }

    public void clickResendEmailLink() {
        try {
            waitForExpectedElement(Resend_link).click();
            log.info("Resend Email link is successfully clicked");

        } catch (Exception e) {
            log.info("Some exception occurred while clicking on resend email link-->>" + e.getMessage());
        }

    }

    public void clickRegisterMyGuaranteeLink() {
        if (UrlBuilder.isDesktop() || UrlBuilder.isTablet()) {
            waitForExpectedElement(registerMyGuaranteeLink).click();
        } else if (UrlBuilder.isMobile()) {
            WebElement element = waitForExpectedElement(LeftTogglebtn);
            if (element.getAttribute("aria-expanded") == null || element.getAttribute("aria-expanded").equalsIgnoreCase("false")) {
                element.click();
            }
            waitForExpectedElement(mobileRegisterMyGuaranteeLink).click();
        }
    }

    public void clickCustomerCareLink() {
        if (UrlBuilder.isDesktop() || UrlBuilder.isTablet()) {
            waitForExpectedElement(customerCareLink).click();
            log.info("Successfully clicked the Customer Care Link");
        } else if (UrlBuilder.isMobile()) {
            WebElement element = waitForExpectedElement(LeftTogglebtn);
            if (element.getAttribute("aria-expanded") == null || element.getAttribute("aria-expanded").equalsIgnoreCase("false")) {
                element.click();
            }
            waitForExpectedElement(mobileCustomerCareLink).click();
        }
    }

    public void clickTipsAndAdviceLink() {
        if (UrlBuilder.isDesktop() || UrlBuilder.isTablet()) {
            waitForExpectedElement(tipsAndAdviceLink).click();
        } else if (UrlBuilder.isMobile()) {
            WebElement element = waitForExpectedElement(LeftTogglebtn);
            if (element.getAttribute("aria-expanded") == null || element.getAttribute("aria-expanded").equalsIgnoreCase("false")) {
                element.click();
            }
            waitForExpectedElement(tipsAndAdviceLink).click();
        }
    }

    public void clickOffersLink() {
        if (UrlBuilder.isMobile()) {
            WebElement element = waitForExpectedElement(LeftTogglebtn);
            if (element.getAttribute("aria-expanded") == null ||
                    element.getAttribute("aria-expanded").equalsIgnoreCase("false")) {
                element.click();
            }
        }
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(offersLink));
        new Actions(getWebDriver()).moveToElement(element).build().perform();
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void clickCartLink() {
        if (UrlBuilder.isDesktop() || UrlBuilder.isTablet()) {
            waitForExpectedElement(cartLink).click();
        } else if (UrlBuilder.isMobile()) {
            waitForExpectedElement(mobileCartLink).click();
        }
    }

    public String getEmptyCartMessage() {
        return waitForExpectedElement(miniCart).getText();
    }

    public String getReviewText() {
        return waitForExpectedElement(reviewText).getText();
    }

    public boolean verifyHeaderMyAccountLink() {
        if (UrlBuilder.isDesktop() || UrlBuilder.isTablet()) {
            return waitForExpectedElement(headerMyAccountLink).isDisplayed();
        } else if (UrlBuilder.isMobile()) {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(mobileHeaderMyAccountLink)).isDisplayed();
        } else {
            return waitForExpectedElement(headerMyAccountLink).isDisplayed();
        }
    }

    public MyAccountOverviewPage clickOnMyAccountButton() {
        IsPageLoaded.waitAllRequest();
        MyAccountOverviewPage obj = null;
        try {
            wait.until(ExpectedConditions.elementToBeClickable(headerMyAccountLink)).click();
            obj = new MyAccountOverviewPage();
            log.info("Successfully intialized MyAccountOverviewPage object returning the same");
        } catch (Exception e) {
            log.error("Unable to click on MyAccount btn for sharkUK/DE thus returing object as NULL for MyAccountOverviewPage-->"
                    + e.getMessage());
            e.printStackTrace();
        }
        return obj;
    }

    public boolean verifyHeaderLogoutLink() {
        if (UrlBuilder.isDesktop() || UrlBuilder.isTablet()) {
            return waitForExpectedElement(headerLogoutLink).isDisplayed();
        } else if (UrlBuilder.isMobile()) {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(mobileHeaderLogoutLink)).isDisplayed();
        } else {
            return waitForExpectedElement(headerLogoutLink).isDisplayed();
        }
    }

    // --------------------- Methods Footer ------------------------

    public String getSharkCleanText() {
        return waitForExpectedElement(sharkCleanText).getText();
    }

    public By getAboutSharkLink() {
        waitForExpectedElement(aboutSharklink);
        return aboutSharklink;
    }

    public void clickAboutSharkLink() {
        waitForExpectedElement(aboutSharklink).click();
    }

    public By getTipsAndAdviceFooterLink() {
        waitForExpectedElement(tipsAndAdvice);
        return tipsAndAdvice;
    }

    public void clickTipsAndAdviceFooterLink() {
        waitForExpectedElement(tipsAndAdvice).click();
    }

    public By getCustomerCareFooterLink() {
        waitForExpectedElement(customerCare);
        return customerCare;
    }

    public void clickCustomerCareFooterLink() {
//        Actions action = new Actions(WebDriverHelper.getWebDriver());
//        action.moveToElement((WebElement) customerCare);
        waitForExpectedElement(customerCare).click();
    }

    public By getTermsAndPoliciesLink() {
        waitForExpectedElement(termsAndPolicies);
        return termsAndPolicies;
    }

    public void clickTermsAndPoliciesLink() {
        waitForExpectedElement(termsAndPolicies).click();
    }

    public By getPrivacyNoticeLink() {
        waitForExpectedElement(privacyNotice);
        return privacyNotice;
    }

    public void clickPrivacyNoticeLink() {
        waitForExpectedElement(privacyNotice).click();
    }

    public By getUkModernSlaveryStatementLink() {
        waitForExpectedElement(ukModernSlaveryStatementLink);
        return ukModernSlaveryStatementLink;
    }

    public void clickUkModernSlaveryStatementLink() {
        waitForExpectedElement(ukModernSlaveryStatementLink).click();
    }

    public By getOnlineDisputeResolutionLink() {
        waitForExpectedElement(onlineDisputeResolution);
        return onlineDisputeResolution;
    }

    public void clickOnlineDisputeResolutionLink() {
        waitForExpectedElement(onlineDisputeResolution).click();
    }

    public By getIndependentBazaarVoiceReviewsLink() {
        waitForExpectedElement(independentBazaarVoiceReviews);
        return independentBazaarVoiceReviews;
    }

    public void clickIndependentBazaarVoiceReviewsLink() {
        waitForExpectedElement(independentBazaarVoiceReviews).click();
    }

    public By getSharkNinjaWarrantyFairProcessingNoticeLink() {
        waitForExpectedElement(sharkNinjaWarrantyFairProcessingNoticeLink);
        return sharkNinjaWarrantyFairProcessingNoticeLink;
    }

    public void clickSharkNinjaWarrantyFairProcessingNoticeLink() {
        waitForExpectedElement(sharkNinjaWarrantyFairProcessingNoticeLink).click();
    }

    public By getSharkNinjaCheckoutFairProcessingNoticeLink() {
        waitForExpectedElement(sharkNinjaCheckoutFairProcessingNoticeLink);
        return sharkNinjaCheckoutFairProcessingNoticeLink;
    }

    public void clickSharkNinjaCheckoutFairProcessingNoticeLink() {
        waitForExpectedElement(sharkNinjaCheckoutFairProcessingNoticeLink).click();
    }

    public By getSharkNinjaCareersLink() {
        waitForExpectedElement(sharkNinjaCareers);
        return sharkNinjaCareers;
    }

    public void clickSharkNinjaCareersLink() {
        waitForExpectedElement(sharkNinjaCareers).click();
    }

    public By getRegisterMyGuaranteeLinkFooterLink() {
        waitForExpectedElement(registerMyGuaranteeLinkFooter);
        return registerMyGuaranteeLinkFooter;
    }

    public void clickRegisterMyGuaranteeLinkFooter() {
        waitForExpectedElement(registerMyGuaranteeLinkFooter).click();
    }

    public By getLogInLinkFooterLink() {
        waitForExpectedElement(logInLinkFooter);
        return logInLinkFooter;
    }

    public void clickLogInLinkFooter() {
        waitForExpectedElement(logInLinkFooter).click();
    }

    public By getFacebookIconFooter() {
        waitForExpectedElement(facebookIcon);
        return facebookIcon;
    }

    public String getTextForTermsAndCondition() {

        String text = "Not found";
        try {
            text = waitForExpectedElement(termsAndPoliciesTitle).getText();
            //log.info("Successfully entered email on email text box");
        } catch (Exception e) {
            log.info("Some exception occurred while entering email text box-->>" + e.getMessage());
        }
        return text;
    }

    public By getTwitterIconFooter() {
        waitForExpectedElement(twitterIcon);
        return twitterIcon;
    }

    public By getInstagramIconFooter() {
        waitForExpectedElement(instagramIcon);
        return instagramIcon;
    }

    public By getPinterestIconFooter() {
        waitForExpectedElement(pinterestIcon);
        return pinterestIcon;
    }

    public By getYoutubeIconFooter() {
        waitForExpectedElement(youtubeIcon);
        return youtubeIcon;
    }

    public By getNewsletterEmailTxtBoxInFooter() {
        waitForExpectedElement(newsletterEmailTxt);
        return newsletterEmailTxt;
    }

    public By getOffersCheckBoxInFooter() {
        waitForExpectedElement(offersCheckbox);
        return offersCheckbox;
    }

    public By getSubmitBtnInFooter() {
        waitForExpectedElement(signupBtn);
        return signupBtn;
    }

    public By getUnsubscribeLinkInFooter() {
        waitForExpectedElement(unsubscribeLink);
        return unsubscribeLink;
    }

    //Rita Singh-29-01-2021
    public void ClickEmailTxtBoxInFooter(String email) {

        try {
            System.out.println("email--" + email);
            waitForExpectedElement(newsletterEmailTxt).clear();
            waitForExpectedElement(newsletterEmailTxt).sendKeys(email);
            //log.info("Successfully entered email on email text box");
        } catch (Exception e) {
            log.info("Some exception occurred while entering email text box-->>" + e.getMessage());
        }

    }

    public HomePage clickOnCartIconForUK() {
        IsPageLoaded.waitAllRequest();
        HomePage obj = null;
        try {
            new Actions(getWebDriver()).moveToElement(wait.until(ExpectedConditions.elementToBeClickable(cartIconUk)))
                    .click().build().perform();
            log.info("Successfully clicked on the cart icon button ");
            obj = new HomePage();
        } catch (Exception e) {
            log.error("Unable to click on cart icon due to-->>" + e.getMessage());
            e.printStackTrace();
        }
        return obj;
    }

    public String YourCartIsEmptyText() {
        String text = "Not Found";
        wait.withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofSeconds(2)).ignoring(Exception.class);
        int count = 0;
        do {
            try {
                text = wait.until(ExpectedConditions.visibilityOfElementLocated(cartEmptyTextForUK)).getText().trim();
                if (text.contains(Props.getMessage("your.cart.is.empty.text"))) {
                    log.info("cart Empty Text Extracted From homePage Page is-->" + text);
                    break;
                } else {
                    try {
                        WebElement element = getWebDriver().findElement(viewBasketButton);
                        new Actions(getWebDriver()).moveToElement(element).build().perform();
                        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
                        List<WebElement> removeProductBtnList = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(clickOnIndividualRemoveButton));
                        System.out.println(" Total no of categories Products are " + removeProductBtnList.size());
//                        if (removeProductBtnList.size() >= 1) {
//                            getWebDriver().findElement(clickOnRemoveAllButton).click();
//                            log.info("Successfully clicked on Remove All Button");
////                        } else {
//////                            removeProductBtnList.get(0).click();
////                              getWebDriver().findElement(clickOnIndividualRemoveButton).click();
////                            log.info("Successfully clicked on Individual Remove Button");
                            for (int i = 0; i < removeProductBtnList.size(); i++) {
                                WebElement removeButton = getWebDriver().findElement(clickOnIndividualRemoveButton);
                                new Actions(getWebDriver()).moveToElement(removeButton).build().perform();
                                wait.until(ExpectedConditions.elementToBeClickable(removeButton)).click();
                                webDriver.navigate().refresh();
                                IsPageLoaded.waitAllRequest();
                                log.info("All the items have been cleared from the Cart");
                                count++;
                                System.out.println("the count is " + count);
                            }
//                        }
                        text = wait.until(ExpectedConditions.visibilityOfElementLocated(noItemInCartMesgForUK)).getText().trim();
                        if (text.contains(Props.getMessage("no.item.in.the.cart.text"))) {
                            log.info("cart Empty Text Extracted From homePage Page is-->" + text);
                            try {
                                clickByElement(signOutButtonUK);
                                log.info("successfully clicked sign out link-->");
                                IsPageLoaded.waitAllRequest();

                            } catch (Exception e) {
                                log.error("Unable to click signout link-->" + e.getMessage());
                                e.printStackTrace();
                            }
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
        if(count<1) {
            System.out.println("no of count after while loop " + count);
            text = wait.until(ExpectedConditions.visibilityOfElementLocated(cartEmptyTextForUK)).getText().trim();
            //text = wait.until(ExpectedConditions.visibilityOfElementLocated(noItemInCartMesgForUK)).getText().trim();
            log.info("cart Empty Text Extracted From homePage Page is-->" + text);
            try {
                clickByElement(signOutButtonUK);
                log.info("successfully clicked sign out link-->");
                IsPageLoaded.waitAllRequest();
            } catch (Exception e) {
                log.error("Unable to click signout link-->" + e.getMessage());
                e.printStackTrace();
            }
        }
        return text;
    }



    public void enterEmailforUnsubscription(String email) {

        try {
            waitForExpectedElement(unsubscriptionemailtextbox).clear();
            waitForExpectedElement(unsubscriptionemailtextbox).sendKeys(email);
            log.info("Successfully entered email for unsubscription");
        } catch (Exception e) {
            log.info("Some exception occurred while entering email for unsubscription-->>" + e.getMessage());
        }

    }

    //Rita Singh-29-01-2021
    public void ClickonSubmitButton() {

        try {
            waitForExpectedElement(signupBtn).click();
            log.info("Successfully clicked on submit button");
        } catch (Exception e) {
            log.info("Some exception occurred while clicking on submit button-->>" + e.getMessage());
        }

    }

    public void OffersCheckboxValue(String value) {

        try {
            if (value.equals("checked") && (waitForExpectedElement(offersCheckbox).isSelected() == false)) {
                waitForExpectedElement(offersCheckbox).click();
                log.info("Successfully checked offers checkbox");
            }

            if (value.equals("unchecked") && (waitForExpectedElement(offersCheckbox).isSelected() == true)) {
                waitForExpectedElement(offersCheckbox).click();
                log.info("Successfully unchecked offers checkbox");
            }


        } catch (Exception e) {
            log.info("Some exception occurred while clicking on offers checkbox-->>" + e.getMessage());
        }

    }

    public void ClickonClickHereLink() {
        try {
            waitForExpectedElement(clickHereLink).click();
            log.info("Successfully clicked on click here link");
        } catch (Exception e) {
            log.info("Some exception occurred while clicking on click here link-->>" + e.getMessage());
        }

    }

    public void ClickonSubmitButtonforUnsubscription() {

        try {
            waitForExpectedElement(unsubscribeBtn).click();
            log.info("Successfully clicked on submit button for Unsubscription");
        } catch (Exception e) {
            log.info("Some exception occurred while clicking on submit button for Unsubscription-->>" + e.getMessage());
        }

    }

    public By getClickHereLink() {
        IsPageLoaded.waitAllRequest();
        try {
            webDriver.switchTo().frame("brontoNewsletter");
            waitForExpectedElement(clickHereLink);
            log.info("Successfully found click here link for Unsubscription");
        } catch (Exception e) {
            log.info("Some exception occurred while checking click here link for Unsubscription-->>" + e.getMessage());
        }
        return clickHereLink;
    }

    public void ClickonUnsubscribeLink() {

        try {
            waitForExpectedElement(unsubscribeLink).click();
            log.info("Successfully clicked on Unsubscribe Link");
        } catch (Exception e) {
            log.info("Some exception occurred while clicking on Unsubscribe Link-->>" + e.getMessage());
        }

    }

    public Boolean VerifySuccessfulSubscription() {
        boolean flag = false;
        try {
            System.out.println("actual" + waitForExpectedElement(signupsuccessmsg).getText());
            System.out.println("expected" + Props.getProp("Successmsg"));
            flag = waitForExpectedElement(signupsuccessmsg).getText().equalsIgnoreCase(Props.getProp("Successmsg"));
            log.info("Successfully subscribed");
        } catch (Exception e) {
            log.info("Some exception occurred while subscription-->>" + e.getMessage());
        }
        return flag;
    }

    public Boolean verifyErrorClientMessage() {

        boolean flag = false;
        try {
            flag = waitForExpectedElement(invalidEmailmsg).getText().contentEquals(Props.getProp("valid.email.required"));
            log.info("Successfully got valid email required error message");

        } catch (Exception e) {
            log.info("Some exception occurred while validating valid email required error message-->>" + e.getMessage());
        }
        return flag;
    }

    public Boolean verifyErrorMessageforLogin() {

        boolean flag = false;
        try {
          log.info(waitForExpectedElement(invalid_email_login).getText());
            flag = waitForExpectedElement(invalid_email_login).getText().contentEquals(Props.getProp("invalid_forgot_email_error"));
            log.info("Successfully got valid email required error message");

        } catch (Exception e) {
            log.info("Some exception occurred while validating valid email required error message-->>" + e.getMessage());
        }
        return flag;
    }

    public String verifyErrorMessage() {
        String text="some error occurred please check";
        try {
            text = waitForExpectedElement(invalidEmailerrormsg).getText().trim();
            log.info("Successfully got valid email required error message");
        } catch (Exception e) {
            log.info("Some exception occurred while validating valid email required error message-->>" + e.getMessage());
        }
        return text;
    }

    public Boolean verifyErrorMessageServer() {

        boolean flag = false;
        try {
            flag = waitForExpectedElement(invalidEmailEnteredmsg).getText().contentEquals(Props.getProp("Invalid.email.error.msg"));
            log.info("Successfully got Please enter a valid e-mail address error message");

        } catch (Exception e) {
            log.info("Some exception occurred while validating Please enter a valid e-mail address error message-->>" + e.getMessage());
        }
        return flag;
    }

    public Boolean VerifySuccessfulUnSubscription() {
        boolean flag = false;
        try {
            System.out.println("waitForExpectedElement(unsubscribesuccessmessage).getText()--" + waitForExpectedElement(unsubscribesuccessmessage).getText());
            System.out.println("Props.getProp(\"SuccessmsgforUnsubscription\")---" + Props.getProp("SuccessmsgforUnsubscription"));
            flag = waitForExpectedElement(unsubscribesuccessmessage).getText().equalsIgnoreCase(Props.getProp("SuccessmsgforUnsubscription"));
            System.out.println("unsubscribed message---" + waitForExpectedElement(unsubscribesuccessmessage).getText());
            log.info("Successfully unsubscribed");

        } catch (Exception e) {
            log.info("Some exception occurred while usubscription-->>" + e.getMessage());
        }
        return flag;
    }

    public Boolean verifyMiniCartHeader() {
        boolean flag = false;
        try {
            flag = waitForExpectedElement(cartLink).getText().contentEquals(Props.getMessage("cart.header.item.text"))
                    && waitForExpectedElement(cartHeaderQuantity).getText().contentEquals("1")
                    && !(waitForExpectedElement(cartHeaderPrice).getText().trim().isEmpty());
            log.info("Successfully verified mini cart header");
            flag=true;
        } catch (Exception e) {
            log.info("Some exception occurred while verifying mini cart header-->>" + e.getMessage());
        }
        return flag;
    }

    public Boolean verifyMobileCartHeader() {
        boolean flag = false;
        try {
            flag = waitForExpectedElement(mobileCartCounter).getText().contentEquals("1");
            log.info("Successfully verified mobile cart header");
        } catch (Exception e) {
            log.info("Some exception occurred while verifying mobile cart header-->>" + e.getMessage());
        }
        return flag;
    }

    public Boolean verifyCartHeaderQuantityUpdated() {
        boolean flag = false;
        try {
            flag = waitForExpectedElement(mobileCartCounter).getText().contentEquals("2");
            log.info("Successfully verified mobile cart header");
        } catch (Exception e) {
            log.info("Some exception occurred while verifying mobile cart header-->>" + e.getMessage());
        }
        return flag;
    }

    public Boolean clickMiniCartHeader(String action) {
        boolean flag = false;
        int count = 0;
        do {
            try {
                log.info("Going to click on mini cart header");
                if (action.equalsIgnoreCase("open")) {
                    String k = webDriver.findElement(miniCart).getAttribute("aria-expanded");
                    if (k == null || k.equalsIgnoreCase("false")) {
                        webDriver.findElement(cartHeaderPrice).click();
                        wait.until(ExpectedConditions.attributeContains(miniCart, "class", "collapse in"));
                        flag = true;
                        log.info("Successfully clicked on mini cart header");
                        break;
                    } else {
                        flag = true;
                        break;
                    }

                }
                if (action.equalsIgnoreCase("close")) {
                    if (webDriver.findElement(miniCart).getAttribute("aria-expanded").equalsIgnoreCase("true")) {
                        webDriver.findElement(cartHeaderPrice).click();
                        wait.until(ExpectedConditions.attributeContains(miniCart, "class", "collapse"));
                        flag = true;
                        log.info("Successfully clicked on mini cart header");
                        break;
                    } else {
                        flag = true;
                        break;
                    }
                }
                log.info("Successfully clicked on mini cart header");
            } catch (Exception e) {
                log.error("Some exception occurred while click on mini cart header-->>" + e.getMessage());
                log.error(ExceptionUtils.getStackTrace(e));
                count++;
            }
        }
        while (flag != true && count < 5);
        return flag;
    }

    public Boolean verifyMiniCart() {
        boolean flag = false;
        try {
            flag = waitForExpectedElement(miniCart).getText().contains(Props.getProp("product.specificSKU.price")) && waitForExpectedElement(miniCart).getText().contains(Props.getProp("product.specificSKU")) && waitForExpectedElement(miniCart).getText().contains(Props.getProp("product.specificSKU.name"));
            log.info("Successfully verified mini cart");
        } catch (Exception e) {
            log.info("Some exception occurred while verifying mini cart-->>" + e.getMessage());
        }
        return flag;
    }

    public Float getCartHeaderQuantity() {
        float k = -1;
        int count = 0;
        while (count < 3) {
            try {
                k = Float.parseFloat(wait.until(ExpectedConditions.visibilityOfElementLocated(cartHeaderQuantity)).getText().trim());
                log.info("Successfully fetched quantity shown in cart header which is-->" + k);
            } catch (Exception e) {
                log.error(ExceptionUtils.getStackTrace(e));
            }
            count++;
        }
        return k;
    }

    public Float getCartHeaderPrice() {
        float k = -1;
        String text = "";
        int count = 0;
        while (count < 3) {
            try {
                text = wait.until(ExpectedConditions.visibilityOfElementLocated(cartHeaderQuantity)).getText().trim();
                if (text.contains("£")) {
                    text = text.replace("£", "").trim();
                }
                if (text.contains(",")) {
                    text = text.replace(",", ".").trim();
                }
                log.info("Successfully fetched quantity shown in cart header which is-->" + text);
            } catch (Exception e) {
                log.error(ExceptionUtils.getStackTrace(e));
            }
            count++;
        }
        return Float.parseFloat(text);
    }

    public Boolean verifyMiniCartHeaderAfterCartProductQtyUpdate() {
        boolean flag = false;
        float quantity = Float.parseFloat(Props.getProp("product.valid.quantity"));
        float price = Float.parseFloat(Props.getProp("product.specificSKU.price").replace(",", "."));
        float TotalPrice = quantity * price;
        try {
            if (webDriver.getCurrentUrl().contains("ninja")) {
                flag = waitForExpectedElement(cartHeaderQuantity).getText().contentEquals(Props.getProp("product.valid.quantity")) && waitForExpectedElement(cartHeaderPrice).getText().contains(String.valueOf(TotalPrice));
            } else {
                flag = waitForExpectedElement(cartHeaderQuantity).getText().contentEquals(Props.getProp("product.valid.quantity")) && waitForExpectedElement(cartHeaderPrice).getText().contains(String.valueOf(TotalPrice).replace(".", ","));
            }

            log.info("Successfully verified mini cart header");
        } catch (Exception e) {
            log.info("Some exception occurred while verifying mini cart header-->>" + e.getMessage());
        }
        return flag;
    }

    public List<String> getListOfProductDisplayedInMiniCart() {
        waitForPage();
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(miniCartProductList)).
                stream().map(s -> s.getText().trim()).collect(Collectors.toList());
    }

    public Boolean verifyMiniCartAfterCartProductQtyUpdate() {
        boolean flag = false;
        float quantity = Float.parseFloat(Props.getProp("product.valid.quantity"));
        float price = Float.parseFloat(Props.getProp("product.specificSKU.price").replace(",", "."));
        float TotalPrice = quantity * price;
        try {
            if (webDriver.getCurrentUrl().contains("ninja")) {
                flag = waitForExpectedElement(miniCart).getText().contains(String.valueOf(TotalPrice)) && waitForExpectedElement(miniCart).getText().contains(Props.getProp("product.specificSKU")) && waitForExpectedElement(miniCart).getText().contains(Props.getProp("product.specificSKU.name"));
            } else {
                flag = waitForExpectedElement(miniCart).getText().contains(String.valueOf(TotalPrice).replace(".", ",")) && waitForExpectedElement(miniCart).getText().contains(Props.getProp("product.specificSKU")) && waitForExpectedElement(miniCart).getText().contains(Props.getProp("product.specificSKU.name"));
            }
            log.info("Successfully verified mini cart");
        } catch (Exception e) {
            log.info("Some exception occurred while verifying mini cart-->>" + e.getMessage());
        }
        return flag;
    }

    public Boolean verifyMiniCartHeaderAfterAddingAnotherProduct() {
        boolean flag = false;
        float quantity = Float.parseFloat(Props.getProp("product.valid.quantity"));
        float priceSKU1 = Float.parseFloat(Props.getProp("product.specificSKU.price").replace(",", "."));
        float priceSKU2 = Float.parseFloat(Props.getProp("product.specificSKU2.price").replace(",", "."));
        float TotalPrice = quantity * priceSKU1 + priceSKU2;
        try {
            if (webDriver.getCurrentUrl().contains("ninja")) {
                flag = waitForExpectedElement(cartLink).getText().contentEquals(Props.getProp("mini.cart.items")) && waitForExpectedElement(cartHeaderQuantity).getText().contentEquals("3") && waitForExpectedElement(cartHeaderPrice).getText().contains(String.valueOf(String.format("%.02f", TotalPrice)));
            } else {
                flag = waitForExpectedElement(cartLink).getText().contentEquals(Props.getProp("mini.cart.items")) && waitForExpectedElement(cartHeaderQuantity).getText().contentEquals("3") && waitForExpectedElement(cartHeaderPrice).getText().contains(String.valueOf(TotalPrice).replace(".", ","));
            }
            log.info("Successfully verified mini cart header after adding another product");
        } catch (Exception e) {
            log.info("Some exception occurred while verifying mini cart header after adding another product-->>" + e.getMessage());
        }
        return flag;
    }

    public Boolean verifyMiniCartAfterAddingAnotherProduct() {
        boolean flag = false;
        float quantity = Float.parseFloat(Props.getProp("product.valid.quantity"));
        float priceSKU1 = Float.parseFloat(Props.getProp("product.specificSKU.price").replace(",", "."));
        float priceSKU2 = Float.parseFloat(Props.getProp("product.specificSKU2.price").replace(",", "."));
        float TotalPriceSKU1 = quantity * priceSKU1;
        try {
            if (webDriver.getCurrentUrl().contains("ninja")) {
                flag = waitForExpectedElement(miniCart).getText().contains(String.valueOf(TotalPriceSKU1)) && waitForExpectedElement(miniCart).getText().contains(String.valueOf(priceSKU2)) && waitForExpectedElement(miniCart).getText().contains(Props.getProp("product.specificSKU")) && waitForExpectedElement(miniCart).getText().contains(Props.getProp("product.specificSKU2")) && waitForExpectedElement(miniCart).getText().contains(Props.getProp("product.specificSKU.name")) && waitForExpectedElement(miniCart).getText().contains(Props.getProp("product.specificSKU2.name"));
            } else {
                flag = waitForExpectedElement(miniCart).getText().contains(String.valueOf(TotalPriceSKU1).replace(".", ",")) && waitForExpectedElement(miniCart).getText().contains(String.valueOf(priceSKU2).replace(".", ",")) && waitForExpectedElement(miniCart).getText().contains(Props.getProp("product.specificSKU")) && waitForExpectedElement(miniCart).getText().contains(Props.getProp("product.specificSKU2")) && waitForExpectedElement(miniCart).getText().contains(Props.getProp("product.specificSKU.name")) && waitForExpectedElement(miniCart).getText().contains(Props.getProp("product.specificSKU2.name"));
            }

            log.info("Successfully verified mini cart");
        } catch (Exception e) {
            log.info("Some exception occurred while verifying mini cart-->>" + e.getMessage());
        }
        return flag;
    }

    public boolean validateFooterLink(String link) {
        boolean flag = false;
        String availableFooterLinks = "";
        // Get all footer links from the page
        List<WebElement> availableFooterLinkList = getAllElements(footerAllLinks);
        for (WebElement availableFooterLink : availableFooterLinkList)
            availableFooterLinks = availableFooterLinks + "|" + availableFooterLink.getText();
        try {
            Assert.assertTrue(availableFooterLinks.toLowerCase().contains(link.toLowerCase()));
            log.info("Footer link found " + link);
            flag = true;
        } catch (Exception e) {
            log.error("Footer link not found " + e.getMessage());
            System.out.println(availableFooterLinks.toLowerCase()+"   " +availableFooterLinks.toLowerCase());

            e.printStackTrace();
        }
        return flag;
    }
}
