package com.salmon.test.page_objects.gui.SNCA;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HomePageSNCA extends PageObject {
//    private static final By signInButton = By.xpath("//a[@routerlink=\"/login\"]");
    private static final By signInButton=By.cssSelector("sn-login-status.login-lg-none");
    private static final By signInText = By.cssSelector("div.modal-header h2");
    private static final By enterEmail = By.cssSelector("input[data-testing-id=\"login\"]");
    private static final By enterAccountPassword = By.cssSelector("input[data-testing-id=\"password\"]");
    private static final By clickSignInBtn = By.name("login");
    private static final By forgotPasswordLink = By.xpath("//a[contains(@href,'forgotPassword')]");
    private static final By errorMessageForEmailOrPassword = By.xpath("//small");
    private static final By errorMessageInAlertOLD = By.xpath("//ish-error-message //span");
    private static final By getErrorMessageInAlertUS = By.xpath("//div[@class=\"checkError--sub\"]");
    private static final By searchIcon = By.xpath("//button[contains(@class,'search-icon')]");
    private static final By enterSearchProduct = By.xpath("//input[@formcontrolname=\"search\"]");
    private static final By clickSearchIconInSearchBar = By.name("search");
    private static final By registerNowBtn = By.xpath("//div[@data-testing-id=\"account-login-page\"] //a[starts-with(@href,'/register?')]");
    private static final By registerMyGuaranteeLink = By.xpath("//a[@href=\"/register/guarantee\" or @href=\"/fr/register/guarantee\"]");
    private static final By whichLanguageIsSelected = By.xpath("//div[@class=\"header-utility\"] //span[@class=\"chosen-language\"]/a");
    private static final By whichLanguageIsSelectedMobile = By.xpath("//ul[@class='mobile-menu submenu']//span[@class=\"chosen-language\"]/a");
    private static final By selectLanguageEnglish = By.xpath("//div[@class=\"header-utility\"] //span/a[contains(text(),'en')]");
    private static final By selectLanguageFrench = By.xpath("//div[@class=\"header-utility\"] //span/a[contains(text(),'fr')]");
    private static final By selectLanguageEnglishMobile = By.xpath("//ul[@class='mobile-menu submenu']//span/a[contains(text(),'en')]");
    private static final By selectLanguageFrenchMobile = By.xpath("//ul[@class='mobile-menu submenu']//span/a[contains(text(),'fr')]");
    private static final By acceptCookies = By.cssSelector("button[id=\"onetrust-accept-btn-handler\"]");
    private static final By closePopUp = By.xpath("//*[@id=\"ltkpopup-close-button\"]/a");
    private static final By resendVerificationEmailLink = By.xpath("//div[@class=\"checkError--sub\"]//u");
//	private static final By errorMessageInAlert = By.xpath("//ish-error-message //span");
    private static final By leftTogglebtn = By.xpath("//button[starts-with(@class,'hamburger-menu')]");
    private static final By mobileHeaderSignInLink = By.cssSelector("button[class='tab-btn-login']");
    public static final By mobileCloseMenu = By.cssSelector("button[class='close-menu-mobile']");

    private static final Logger log = LoggerFactory.getLogger(HomePageSNCA.class)   ;

    public String getSignInText() {
        IsPageLoaded.waitAllRequest();
        String text = "";
        try {
            text = webDriver.findElement(signInText).getText().trim();
            log.info("Text found over sign in modal which is -->" + text);
        } catch (Exception e) {
            log.error("Unable to get text \"Sign In\" from the sign modal over SharkCA");
        }
        return text;
    }

    public boolean enterEmail(String email) {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            waitClearAndEnterText(enterEmail, email);
            flag = true;
        } catch (Exception e) {
            log.error("Unable to enter email after clicking on sign in button for sharkCA-->" + e.getMessage());
            e.printStackTrace();
        }

        return flag;
    }

    public boolean enterPassword(String password) {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            waitClearAndEnterText(enterAccountPassword, password);
            flag = true;
        } catch (Exception e) {
            log.error("Unable to enter account password after clicking on sign in button for sharkCA-->"
                    + e.getMessage());
            e.printStackTrace();
        }

        return flag;
    }

    public MyAccountPageSNCA clickSignInBtn() {
        IsPageLoaded.waitAllRequest();
        MyAccountPageSNCA obj = null;
        try {
            wait.until(ExpectedConditions.elementToBeClickable(clickSignInBtn)).click();
            obj = new MyAccountPageSNCA();
            log.info("Successfully intialized MyAccountPageSNCA object returning the same");
        } catch (Exception e) {
            log.error("Unable to click on SignIn btn for sharkCA thus returing object as NULL for MyAccountPageSNCA-->"
                    + e.getMessage());
            e.printStackTrace();
        }

        return obj;
    }

    public boolean openHamburgerMenu(){
        boolean flag=false;
        try {
            WebElement element= wait.until(ExpectedConditions.presenceOfElementLocated(leftTogglebtn));
            if(!element.getAttribute("class").toLowerCase().contains("open")){
                wait.until(ExpectedConditions.elementToBeClickable(element)).click();
                log.info("Successfully opened the hamburger menu ");
                flag=true;
            }
            else {
                log.info("Hamburger menu is already opened ");
                flag=true;
            }
        }catch (Exception e){
            log.error("Some exception arises while opening the hamburger menu which is-->>"+ ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
        return flag;
    }

    public boolean openLoginInPopUp() {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            if (UrlBuilder.isDesktop()) {
                new Actions(getWebDriver()).moveToElement(wait.until(ExpectedConditions.visibilityOfElementLocated(signInButton))).click().build().perform();
            } else if (UrlBuilder.isMobile()) {
                openHamburgerMenu();
                waitForExpectedElement(mobileHeaderSignInLink).click();
            }

            flag = true;
            log.info("Successfully clicked on sign in button over SharkCA home page to open login in pop up");
        } catch (Exception e) {
            log.error("Unable to click on the sign in button over SharkCA home page-->" + e.getMessage());
            e.printStackTrace();
        }

        return flag;
    }

    public ForgotPasswordPageSNCA clickForgotPasswordLink() {
        IsPageLoaded.waitAllRequest();
        ForgotPasswordPageSNCA obj = null;
        wait.until(ExpectedConditions.elementToBeClickable(forgotPasswordLink)).click();
        obj = new ForgotPasswordPageSNCA();
        return obj;
    }

    public List<String> getErrorMessageWhenEnterInvalidEmailOrPassword() {
        IsPageLoaded.waitAllRequest();
        List<String> errorMessages = new ArrayList<>();
        try {
            errorMessages = webDriver.findElements(errorMessageForEmailOrPassword).stream().map(s -> s.getText())
                    .collect(Collectors.toList());
            log.info("Got error message/messages and size of the list is-->" + errorMessages.size());
        } catch (Exception e) {
            log.error("Unable to get the text when invalid email provided while logging-->" + e.getMessage());
            e.printStackTrace();
        }

        return errorMessages;
    }

    public String getErrorMessageFromAlertOnSignInPopUp() {
        IsPageLoaded.waitAllRequest();
        String text = "";
        try {
            if (webDriver.findElements(errorMessageInAlertOLD).size() != 0) {
                text = getTextFor(errorMessageInAlertOLD).trim();
            } else {
                text = getTextFor(getErrorMessageInAlertUS).trim();
            }
            log.info("Got error message when providing valid email and valid password but account doesn't exist which is-->" + text);
        } catch (Exception e) {
            log.error("Unable to get the text when from alert class over signIn pop up provided while logging-->" + e.getMessage());
            e.printStackTrace();
        }
        return text;
    }

    public boolean openSearchBarAndEnterProduct(String productName) {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            wait.until(ExpectedConditions.elementToBeClickable(searchIcon)).click();
            waitClearAndEnterText(enterSearchProduct, productName);
            log.info("Clicked on the search icon to open search bar on home page and entered product-->" + productName);
            flag = true;
        } catch (Exception e) {
            log.error("Unable to click on search icon and entered product on SharkCA homepage--->" + e.getMessage());
            e.printStackTrace();
        }

        return flag;
    }

    public SearchPageSNCA clickOnSearchIconInSearchBar() {
        IsPageLoaded.waitAllRequest();
        SearchPageSNCA obj = null;
        try {
            wait.until(ExpectedConditions.elementToBeClickable(clickSearchIconInSearchBar)).click();
            obj = new SearchPageSNCA();
            log.info("Clicked on search icon in search product bar");
        } catch (Exception e) {
            log.error("Unable to click on search icon in search product bar due to-->" + e.getMessage());
            e.printStackTrace();
        }
        return obj;
    }

    public CreateNewAccountPageSNCA clickRegisterNowButton() {
        IsPageLoaded.waitAllRequest();
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

    public RegisterMyGuaranteePageSNCA clickRegisterMyGuarantee() {
        IsPageLoaded.waitAllRequest();
        RegisterMyGuaranteePageSNCA obj = null;
        try {
//            if(UrlBuilder.isMobile())
//            openHamburgerMenu();
//            Thread.sleep(2000);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(registerMyGuaranteeLink));
            new Actions(getWebDriver()).moveToElement(element).build().perform();
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
//            wait.until(ExpectedConditions.elementToBeClickable(registerMyGuaranteeLink)).click();
            obj = new RegisterMyGuaranteePageSNCA();
            log.info("Successfully clicked on Register My Guarantee Link");
        } catch (Exception e) {
            log.error("Unable to click on register my guarantee link due to-->>" + e.getMessage());
            e.printStackTrace();
        }
        return obj;
    }

    public String whichLanguageIsSelected() {
        IsPageLoaded.waitAllRequest();
        String text = "Not Found";
        try {

            if (UrlBuilder.isDesktop()) {
                text = getTextFor(whichLanguageIsSelected).trim().toUpperCase();
                Thread.sleep(3000);
            } else if (UrlBuilder.isMobile()) {
                openHamburgerMenu();
                text = getTextFor(whichLanguageIsSelectedMobile).trim().toUpperCase();
                waitForExpectedElement(mobileCloseMenu).click();
            }
            log.info("Successfully found language selected over the page which is-->>" + text);
        } catch (Exception e) {
            log.error("Unable to fetch which language is selected over the page due to-->" + e.getMessage());
            e.printStackTrace();
        }
        return text;
    }

    public boolean selectLanguage(String language) {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        try {
            Thread.sleep(2000);
            if (language.toLowerCase().equalsIgnoreCase("english") || language.toLowerCase().equalsIgnoreCase("en")) {

                if (UrlBuilder.isDesktop()) {
                    wait.until(ExpectedConditions.elementToBeClickable(selectLanguageEnglish)).click();
                    wait.until(ExpectedConditions.elementToBeClickable(selectLanguageEnglish));
                } else if (UrlBuilder.isMobile()) {
                    openHamburgerMenu();
                    wait.until(ExpectedConditions.elementToBeClickable(selectLanguageEnglishMobile)).click();
//                wait.until(ExpectedConditions.elementToBeClickable(selectLanguageEnglishMobile));
                }
                if (whichLanguageIsSelected().equalsIgnoreCase("en")) {
                    log.info("Successfully selected language as EN");
                    flag = true;
                } else {
                    log.error("Unable to select language as EN");
                }
            }

            if (language.toLowerCase().equalsIgnoreCase("french") || language.toLowerCase().equalsIgnoreCase("fr")) {
                if (UrlBuilder.isDesktop()) {
                    wait.until(ExpectedConditions.elementToBeClickable(selectLanguageFrench)).click();
                    wait.until(ExpectedConditions.elementToBeClickable(selectLanguageFrench));
                } else if (UrlBuilder.isMobile()) {
                    openHamburgerMenu();
                    wait.until(ExpectedConditions.elementToBeClickable(selectLanguageFrenchMobile)).click();
//                wait.until(ExpectedConditions.elementToBeClickable(selectLanguageFrenchMobile));
                }
                if (whichLanguageIsSelected().equalsIgnoreCase("fr")) {
                    log.info("Successfully selected language as FR");
                    flag = true;
                } else {
                    log.error("Unable to select language as FR");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

    public boolean clickToAcceptCookiesBtn() throws InterruptedException {
//        Thread.sleep(1000);
       // getWebDriver().navigate().refresh();
     //   IsPageLoaded.waitAllRequest();
        boolean flag = false;
        if (Props.getExecutionEnv().equalsIgnoreCase("uat") || Props.getExecutionEnv().equalsIgnoreCase("uata")) {

            FluentWait<WebDriver> fluentWait = new FluentWait<>(getWebDriver());
            fluentWait.withTimeout(Duration.ofSeconds(1));
            fluentWait.ignoring(TimeoutException.class, NotFoundException.class);
            fluentWait.pollingEvery(Duration.ofSeconds(1));

            int count = 0;
            while (count < 2) {
                try {

                    fluentWait.until(ExpectedConditions.visibilityOfElementLocated(acceptCookies)).click();
                    wait.until(ExpectedConditions.invisibilityOf(webDriver.findElement(acceptCookies)));
                    flag = true;
                    break;
                } catch (Exception e) {
                    log.error(e.getMessage());
                    count++;
                }
            }
        }
        return flag;
    }
    public boolean clickToAcceptPopUpBtn() throws InterruptedException {
       // IsPageLoaded.waitAllRequest();
        boolean flag = false;
        if (Props.getExecutionEnv().equalsIgnoreCase("uat") || Props.getExecutionEnv().equalsIgnoreCase("uata")) {
            FluentWait<WebDriver> fluentWait = new FluentWait<>(getWebDriver());
            fluentWait.withTimeout(Duration.ofSeconds(3));
            fluentWait.ignoring(TimeoutException.class, NotFoundException.class);
            fluentWait.pollingEvery(Duration.ofSeconds(3));
            int count = 0;
            while (count < 2) {
                try {
                    fluentWait.until(ExpectedConditions.visibilityOfElementLocated(closePopUp)).click();
                    wait.until(ExpectedConditions.invisibilityOf(webDriver.findElement(closePopUp)));
                    flag = true;
                    break;
                } catch (Exception e) {
                    log.error(e.getMessage());
                    count++;
                }
            }
        }
        return flag;
    }

    public MyAccountPageSNCA loginwithValidCredentials() {
        IsPageLoaded.waitAllRequest();
        String email = Props.getProp("login.valid.userName");
        String password = Props.getProp("login.valid.password");
        if (UrlBuilder.isDesktop()) {
            clickByElement(signInButton);
        } else if (UrlBuilder.isMobile()) {
            openHamburgerMenu();
            waitForExpectedElement(mobileHeaderSignInLink).click();
        }
        waitClearAndEnterText(enterEmail, email);
        waitClearAndEnterText(enterAccountPassword, password);
        MyAccountPageSNCA obj = clickSignInBtn();
        return obj;
    }

    public MyAccountPageSNCA loginwithGivenCredentials(String userName, String pwd) {
        IsPageLoaded.waitAllRequest();
        String email = Props.getProp(userName);
        String password = Props.getProp(pwd);
        waitClearAndEnterText(enterEmail, email);
        waitClearAndEnterText(enterAccountPassword, password);
        MyAccountPageSNCA obj = clickSignInBtn();
        return obj;
    }

    public void clickOnResendVerificationEmail() {
        IsPageLoaded.waitAllRequest();
        try {
            waitForExpectedElement(resendVerificationEmailLink).click();
            log.info("Resend Verification Email link is successfully clicked");

        } catch (Exception e) {
            log.info("Some exception occurred while clicking on Resend Verification Email link-->>" + e.getMessage());
        }
    }
}