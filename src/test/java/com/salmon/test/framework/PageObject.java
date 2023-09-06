package com.salmon.test.framework;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertTrue;

import com.salmon.test.framework.helpers.WebDriverHelper;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class PageObject {

    private static final Logger LOG = LoggerFactory.getLogger(PageObject.class);
    public static JavascriptExecutor js;
    @Getter
    @Setter
    protected static WebDriver webDriver;
    private static long DRIVER_WAIT_TIME = 30;
    @Getter
    protected WebDriverWait wait;

    protected PageObject() {
        this.webDriver = WebDriverHelper.getWebDriver();
        this.wait = new WebDriverWait(webDriver, DRIVER_WAIT_TIME);
    }

    public String returnHeader() {
        return webDriver.findElement(By.cssSelector("h1")).getText();
    }

    public void updateDriverWaitTimeTo(long waitTime) {
        DRIVER_WAIT_TIME = waitTime;
    }

    // ******************************** // FINDERS // ************************************ //
    public boolean isElementPresentByby(By by) {
        LOG.info("RUNNING : isElementPresent");
        webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.MILLISECONDS);
        boolean exists = webDriver.findElements(by).size() != 0;
        webDriver.manage().timeouts().implicitlyWait(DRIVER_WAIT_TIME, TimeUnit.SECONDS);
        LOG.info("Element : " + by + " exists : " + exists);
        return exists;
    }

    public boolean isElementPresentWithWait(By by) {
        LOG.info("RUNNING : isElementPresent");
        boolean exists = false;
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            exists = true;
            LOG.info("Element : " + by + " exists : " + exists);

        } catch (Exception Ex) {
            LOG.error(Ex.getMessage());
            LOG.error("Element not found");
        }
        return exists;
    }

    public boolean isPageLoadedWithPageTitleOf(String expectedPageTitle) {
        LOG.info("RUNNING : isPageLoadedWithTitleOf : " + expectedPageTitle);
        webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.MILLISECONDS);
        boolean pageLoaded = false;
        try {
            wait.until(ExpectedConditions.titleContains(expectedPageTitle));
            pageLoaded = true;
        } catch (Exception e) {
            LOG.error(e.getMessage());
            LOG.error("\nPAGE NOT LOADED WITH THE EXPECTED TITLE OF : " + expectedPageTitle);
            LOG.error("\nEXPECTED PAGE TITLE : " + expectedPageTitle);
            LOG.error("\nACTUAL PAGE TITLE : " + webDriver.getTitle());
        }
        webDriver.manage().timeouts().implicitlyWait(DRIVER_WAIT_TIME, TimeUnit.SECONDS);
        return pageLoaded;
    }

    // ******************************** // SCRAPERS // ************************************ //
    public void getAllButtons() {
        LOG.info("Running :  getAllButtons");
        List<WebElement> buttons = webDriver.findElements(By.tagName("button"));
        LOG.info("Total Number of buttons found : " + buttons.size());
        for (WebElement button : buttons) {
            if (button.getText().length() > 0) {
                LOG.info("BUTTON (With Text)PRESENT : " + button.getText());
            }
        }
    }

    public void getAllLinks() {
        LOG.info("Running : getAllLinks");
        List<WebElement> links = WebDriverHelper.getWebDriver().findElements(By.tagName("a"));
        Integer linksWithText = 0;
        for (WebElement link : links) {
            if (link.getText().length() > 0) {
                linksWithText++;
                LOG.info("LINKS : " + link.getText());
            }
        }
        LOG.info("Total number of links found : " + links.size());
        LOG.info("Total number of text links found : " + linksWithText);
    }

    public void getAllTables() {
        LOG.info("Running : getAllTables");
        List<WebElement> tables = WebDriverHelper.getWebDriver().findElements(By.tagName("table"));
        for (WebElement table : tables) {
            LOG.info("Tables : " + table.getAttribute("class"));
            System.out.println("table Name : " + table.getAttribute("class"));
        }
    }

    //TODO modify size to just displayed ul's with class name length > 0 - only care about dynamic content
    public void getAllLists() {
        LOG.info("Running :  getAllLists");
        List<WebElement> lists = webDriver.findElements(By.tagName("ul"));
        for (WebElement list : lists) {
            if (list.getAttribute("class").length() > 0) {
                LOG.info("LISTS : " + list.getAttribute("class"));
            }
        }
        LOG.info("Total number of LISTS found : " + lists.size());
    }

    public void getAllImages() {
        LOG.info("Running : getAllImages");
        List<WebElement> images = webDriver.findElements(By.tagName("img"));
        LOG.info("Total number of images found : " + images.size());
        for (WebElement image : images) {
            if (image.getAttribute("class").equals("desktop")) {
                LOG.info("IMAGE : " + image.getAttribute("alt"));
            }
        }
    }

    // ******************************** // ASSERTIONS // ************************************ //

    public void assertTrueWithCustomError(String expectedText, String actualText) {
        LOG.info("Running : assertTrue");
        assertTrue("\n\n ******** ERROR *********** \n" +
                "\n NOT THE EXPECTED RESULT!! " +
                "\n EXPECTED : " + expectedText +
                "\n ACTUAL   : " + actualText, expectedText.equals(actualText));
    }

    // ******************************** // GETTERS // ************************************ //

    /**
     * Returns the current Url from page
     **/
    public String getCurrentUrl() {
        LOG.info("Running : getCurrentUrl");
        return webDriver.getCurrentUrl();
    }

    public boolean isSelected(By by) {
        LOG.info("Running : isSelected");
        return waitForExpectedElement(by).isSelected();
    }

    /**
     * Returns the current page title from page
     */
    public String getCurrentPageTitle() {
        LOG.info("Running : getCurrentPageTitle");
        return getWebDriver().getTitle();
    }

    //TODO
    // get values - input boxes, check boxes, selects,
    public String getTextFor(By by) {
        LOG.info("Running : getTextFor : " + by);
        return waitForExpectedElement(by).getText();
    }

    public String getValue(By by) {
        LOG.info("Running : getValue");
        return waitForExpectedElement(by).getAttribute("value");
    }

    //public List<WebElement> getTableHeadersTest(By by){
    //    LOG.info("Running : getTableHeaders");
    //    return tableName = waitForExpectedElement(by).findElements(By.tagName("th"));
    // }

    public List<WebElement> getTableHeaders(By by) {
        LOG.info("Running : getTableHeaders");
        return waitForExpectedElement(by).findElements(By.tagName("th"));
    }

    public List<WebElement> getTableRows(By by) {
        LOG.info("Running : getTableRows");
        return waitForExpectedElement(by).findElements(By.tagName("tr"));
    }

    public List<WebElement> getTableCols(By by) {
        LOG.info("Running : getTableCols");
        return waitForExpectedElement(by).findElements(By.tagName("td"));
    }

    // Tables to be loop thur with enhanced for loop :
    //for (WebElement row : pageName.getTableRows()){
    //if (row.getText().contains(wantedInfor)){
    //   row.findElement(By.cssSelector("a.action.view")).click(); // or whatever action might be
    //   break; // to quit loop once test completed
    // }
    // }

    public Integer getTableCount(By by) {
        LOG.info("Running : getTableCount");
        List<WebElement> listOfRows = waitForExpectedElement(by).findElements(By.tagName("tr"));
        return listOfRows.size() - 1;
    }

    /**
     * Get all <code><option/></code> innerHTML attributes
     */
    public List<String> getAllSelectOptions(By by) {
        isElementPresentByby(by);
        List<String> options = new ArrayList<String>();
        for (WebElement option : new Select(webDriver.findElement(by)).getOptions()) {
            if (option.getAttribute("value") != "") {
                options.add(option.getText());
            }
            System.out.println("Select Option : " + option.getText());
        }
        return options;
    }

    public Set<org.openqa.selenium.Cookie> getCookiesViaSelenium() {
        return webDriver.manage().getCookies();
    }


    // ******************************** // ACTIONS // ************************************ //
    // *** HighLighter *** //
    public void highlightElement(By by, int duration) throws InterruptedException {
        WebElement element = webDriver.findElement(by);

        if (webDriver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", element);
            ((JavascriptExecutor) webDriver).executeScript("scroll(0, -250);");
            ((JavascriptExecutor) webDriver)
                    .executeScript("arguments[0].style.border='2px solid red'", element);
        }
        if (duration > 0) {
            Thread.sleep(duration * 1000);
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].style.border=''", element);
        }
    }

    // *** CLICKS *** //
    public void clickByElement(By by) {
        LOG.info("Running : clickByElement");
        if (isElementClickable(by)) {
            webDriver.findElement(by).click();
        } else {
            try {
                LOG.info("Couldn't find element on first pass, trying again");
                if (isElementClickable(by))
                    webDriver.findElement(by).click();
            } catch (Exception e) {
                LOG.info("**** ERROR FINDING ELEMENT : " + by);
                LOG.info("Failed to find element after 2 passes ");
                LOG.info("Page title : " + getCurrentPageTitle());
                LOG.info("Please ensure element is present on page via isElementPresent methods");
            }
        }
    }

    public void visit(String site) {
        webDriver.get(site);
    }

    public void clickByElementByQueryJSExecutor(final By by) {
        LOG.info("Running : clickElementByJSExecutor");
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        JavascriptExecutor jse = (JavascriptExecutor) webDriver;
        jse.executeScript("arguments[0].scrollIntoView();", waitForExpectedElement(by));
        jse.executeScript("arguments[0].click();", waitForExpectedElement(by));
    }

    public void clickElementByQueryJSExecutor(final WebElement ele) {
        LOG.info("Running : clickElementByQueryJSExecutor");
        wait.until(ExpectedConditions.visibilityOf(ele));
        JavascriptExecutor jse = (JavascriptExecutor) webDriver;
        jse.executeScript("arguments[0].scrollIntoView();", ele);
        ele.click();
    }

    // *** SELECT *** //
    public void selectValueFromDropDownByby(String itemToSelect, By by) {
        LOG.info("Running : selectValueFromDropDownByby");
        Select ProductDropDown = new Select(waitForExpectedElement(by));
        ProductDropDown.selectByVisibleText(itemToSelect);
    }

    public void selectValueFromDropDownCancellationReasonByby(String itemToSelect, By by) throws InterruptedException {
        LOG.info("Running : selectValueFromDropDownCancellationReasonByby");
        Select cancellationReasonDropDown = new Select(waitForExpectedElement(by));
        cancellationReasonDropDown.selectByVisibleText(itemToSelect);
        Thread.sleep(2000);

    }

    //  Created by Sumeet
//  *** Select by Value *** //
    public void selectByValueFromDropDownByBy(String itemValue, By by) {
        LOG.info("Running : selectByValueFromDropDownByBy");
        Select ProductDropDown = new Select(waitForExpectedElement(by));
        ProductDropDown.selectByValue(itemValue);
    }

    //  Created by Sumeet
//  *** Select by Index *** //
    public void selectByIndexFromDropDownByBy(int index, By by) {
        LOG.info("Running : selectByIndexFromDropDownByBy");
        Select ProductDropDown = new Select(waitForExpectedElement(by));
        ProductDropDown.selectByIndex(index);
    }

    public void selectValueFromDropDownByWebElement(WebElement ele, String text) {
        LOG.info("Running : selectValueFromDropDownByWebElement");
        Select dropdown = new Select(ele);
        dropdown.selectByVisibleText(text);
    }

    // *** POPULATE *** //
    public void waitClearAndEnterText(final By by, String textToEnter) {
        LOG.info("Running : waitClearAndEnterText");
        waitForExpectedElement(by).clear();
        waitForExpectedElement(by).sendKeys(textToEnter);
    }

    public void waitAndClear(final By by) {
        LOG.info("Running : waitAndClear");
        waitForExpectedElement(by).clear();
    }

    // Created By Sanket

    public boolean setTextIntoField(By by, String data) {
        boolean flag = false;
        int count = 0;
        IsPageLoaded.waitAllRequest();
        do {
            try {
                WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
                new Actions(getWebDriver()).moveToElement(element).build().perform();
                wait.until(ExpectedConditions.elementToBeClickable(element));
                element.clear();
                element.sendKeys(data);
                Thread.sleep(1000);
                String fetch1, fetch2, fetch3, fetch4;
                fetch1 = element.getText().trim();
                fetch2 = element.getAttribute("value");
                fetch3 = element.getAttribute("innerHTML");
                fetch4 = element.getAttribute("outerHTML");

                if (!fetch1.isEmpty()) {
                    if (fetch1.equalsIgnoreCase(data)) {
                        LOG.info("Successfully set text-->>"+data+" into the field-->>"+by);
                        flag = true;
                    } else {
                        count++;
                    }
                } else if (!fetch2.isEmpty()) {
                    if (fetch2.equalsIgnoreCase(data)) {
                        LOG.info("Successfully set text-->>"+data+" into the field-->>"+by);
                        flag = true;
                    } else {
                        count++;
                    }
                } else if (!fetch3.isEmpty()) {
                    if (fetch3.equalsIgnoreCase(data)) {
                        LOG.info("Successfully set text-->>"+data+" into the field-->>"+by);
                        flag = true;
                    } else {
                        count++;
                    }
                } else if (!fetch4.isEmpty()) {
                    if (fetch4.equalsIgnoreCase(data)) {
                        LOG.info("Successfully set text-->>"+data+" into the field-->>"+by);
                        flag = true;
                    } else {
                        count++;
                    }
                }
            } catch (NotFoundException e1) {
                LOG.error("Element not found please check locator strategy-->>"+by);
                e1.printStackTrace();
                break;
            } catch (TimeoutException e2) {
                count++;
                LOG.error("Timeout exception occured while setting text in field trying-->>" + count + " time");
                e2.printStackTrace();
            } catch (Exception e) {
                LOG.error("Some exception ocurred while setting up text in field which is--->>" + ExceptionUtils.getStackTrace(e));
                e.printStackTrace();
                break;
            }
        } while (count < 2 && !flag);
        return flag;
    }

    // *** INJECT CSS *** Experimental //
    public void injectCSS(String cssToBeInjected) {
        LOG.info("Running : injectCSS");
        // Example CSS : '<style type=\"text/css\">.toast-box { display: none !important; }</style>';"
        try {
            ((JavascriptExecutor) webDriver).executeScript(
                    "document.body.innerHTML = document.body.innerHTML + " + cssToBeInjected + "");
            webDriver.navigate().refresh();
        } catch (Exception e) {
            LOG.info("\n*** ERROR ***");
            LOG.info("\n*** Injecting CSS - Please check your CSS Block and speak to Front End Dev ***");
        }
    }

    // *** NAVIGATION *** //
    public void clickBrowserBackButton() {
        LOG.info("Running : clickBrowserBackButton()");
        webDriver.navigate().back();
    }

    public void clickBrowserForwardButton() {
        LOG.info("Running : clickBrowserForwardButton()");
        webDriver.navigate().forward();
    }

    public void refreshBrowser() {
        LOG.info("Running : refreshBrowser");
        webDriver.navigate().refresh();
    }

    public void clearCache() {
        LOG.info("Running : clearCache");
        webDriver.manage().deleteAllCookies();
        refreshBrowser();
    }

    public void getAllCookies() {
        LOG.info("Running : getAllCookies");
        webDriver.manage().getCookies();
    }

    /**
     * Switch to another tab within window. tabNum is 0 to 1++ from left to right.
     */
    public void switchBetweenWindowTabs(int tabNum) throws Exception {
        LOG.info("Running : switchBetweenWindowTabs");
        ArrayList<String> windowTabs = new ArrayList<String>(webDriver.getWindowHandles());
        webDriver.switchTo().window(windowTabs.get(tabNum));
    }

    public void basicAuthentation(By byUserName, By byPassword, By submitButton, String userName,
                                  String passWord) {
        waitClearAndEnterText(byUserName, userName);
        waitClearAndEnterText(byPassword, passWord);
        clickByElement(submitButton);
    }

    //public

    // ******************************** // API CMD // ************************************ //

    public String restAssuredReturnAllInfo(String webAddress) throws Throwable {
        LOG.info("Running : restAssuredReturnAllInfo");
        Response response = get(webAddress).andReturn();
        String jsonResponce = response.getBody().asString();
        return jsonResponce;
    }

    public Integer restAssuredReturnStatusCode() throws Throwable {
        LOG.info("Running : restAssuredReturnAllInfo");
        Response response = get(webDriver.getCurrentUrl()).andReturn();
        Integer jsonResponce = response.getStatusCode();

        return jsonResponce;
    }

    public void restAssuredAssertStatusCode(Integer expectedStatusCode) {
        RestAssured.given().get(webDriver.getCurrentUrl()).then().statusCode(expectedStatusCode);
    }

    public void restAssuredBasicAuthentication(String webAddress, String un, String pw,
                                               boolean success) throws Throwable {
        LOG.info("Running : restAssuredBasicAuthentication");
        String authAppApi = webAddress;
        String username = un;
        String incorrectUserName = "InC0rr3ctPa55W0rd";
        String password = pw;
        if (success == true) {
            given().auth().preemptive().basic(username, password).when().get(authAppApi).then()
                    .statusCode(200).log().all();
        }
        if (success == false) {
            given().auth().preemptive().basic(incorrectUserName, password).when().get(authAppApi).then()
                    .statusCode(401).log().all();
        }
    }

    public void restAssuredCookieExtraction(String webAddress) throws Throwable {
        LOG.info("Running : restAssuredCookieExtraction");
        Response response = get(webAddress);
        Map<String, String> cookies = response.getCookies();
        for (Map.Entry<String, String> cookie : cookies.entrySet()) {
            System.out.println("\nkey : " + cookie.getKey() + " : value : " + cookie.getValue());
        }
    }

    public void restAssuredCookieExtractionSingle(String webAddress, String cookieName)
            throws Throwable {
        LOG.info("Running : restAssuredCookieExtractionSingle");
        Response response = get(webAddress);
        Cookie cookies = response.getDetailedCookie(cookieName);

        LOG.info("\nCookie has expiry date: " + cookies.hasExpiryDate());
        LOG.info("\nCookie expiry date: " + cookies.getExpiryDate());
        LOG.info("\nCookie value: " + cookies.getValue());
        LOG.info("\nCookie value: " + cookies.getComment());
    }

    // ******************************** // PATTEN MATCHING // **************************** //

    private String extractTokenViaPattenMatch(String startmatchText, String endMatchText,
                                              String totaltext) {
        LOG.info("Running : extractTokenViaPattenMatch");
        String parsedString = "";
        Pattern p = Pattern.compile(startmatchText + "(.*?)" + endMatchText);
        Matcher m = p.matcher(totaltext);
        if (m.find()) {
            parsedString = m.group(1);
        }
        return parsedString;
    }

    // ******************************** // WAITERS // ************************************ //

    public void waitForPage() {
        LOG.info("Running : waiting for page to load");
        new WebDriverWait(getWebDriver(), 2).until(webDriver ->
                ((JavascriptExecutor) webDriver).executeScript("return document.readyState")
                        .equals("complete"));
    }

    /**
     * An expectation for checking the title of a page.
     *
     * @param title the expected title, which must be an exact match
     * @return true when the title matches, false otherwise
     */
    public boolean checkPageTitle(String title) {
        LOG.info("Running : checkPageTitle");
        return new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME)
                .until(ExpectedConditions.titleIs(title));
    }

    /**
     * An expectation for checking that the title contains a case-sensitive substring
     *
     * @param title the fragment of title expected
     * @return true when the title matches, false otherwise
     */
    public boolean checkPageTitleContains(String title) {
        LOG.info("Running : checkPageTitleContains");
        return new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME)
                .until(ExpectedConditions.titleContains(title));
    }

    /**
     * An expectation for the URL of the current page to be a specific url.
     *
     * @param url the url that the page should be on
     * @return <code>true</code> when the URL is what it should be
     */
    public boolean checkPageUrlToBe(String url) {
        LOG.info("Running : checkPageUrlToBe");
        return new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME)
                .until(ExpectedConditions.urlToBe(url));
    }

    /**
     * An expectation for the URL of the current page to contain specific text.
     *
     * @param fraction the fraction of the url that the page should be on
     * @return <code>true</code> when the URL contains the text
     */
    public boolean checkPageUrlContains(String fraction) {
        LOG.info("Running : checkPageUrlContains");
        return new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME)
                .until(ExpectedConditions.urlContains(fraction));
    }

    /**
     * Expectation for the URL to match a specific regular expression
     *
     * @param regex the regular expression that the URL should match
     * @return <code>true</code> if the URL matches the specified regular expression
     */
    public boolean checkPageUrlMatches(String regex) {
        LOG.info("Running : checkPageUrlMatches");
        return new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME)
                .until(ExpectedConditions.urlMatches(regex));
    }

    /**
     * Clear the text in the element Find the dynamic element wait until its visible
     *
     * @param by Element location found by css, xpath, id etc...
     **/
    protected WebElement waitForExpectedElementToClear(final By by) {
        LOG.info("Running : waitForExpectedElementToClear");
        waitForExpectedElement(by).clear();
        return waitForExpectedElement(by);
    }

    /**
     * Find the dynamic element wait until its visible
     *
     * @param by Element location found by css, xpath, id etc...
     **/
    protected WebElement waitForExpectedElement(final By by) {
        LOG.info("Running : waitForExpectedElement");
        return wait.until(visibilityOfElementLocated(by));
//    return waitForExpectedElement(by);
    }

    /**
     * Find the dynamic element wait until its visible for a specified time
     *
     * @param by                Element location found by css, xpath, id etc...
     * @param waitTimeInSeconds max time to wait until element is visible
     **/
    public WebElement waitForExpectedElement(final By by, long waitTimeInSeconds) {
        LOG.info("Running : waitForExpectedElement with custom timeout");
        try {
            WebDriverWait wait = new WebDriverWait(webDriver, waitTimeInSeconds);
            return wait.until(visibilityOfElementLocated(by));
        } catch (NoSuchElementException e) {
            LOG.info(e.getMessage());
            return null;
        } catch (TimeoutException e) {
            LOG.info(e.getMessage());
            return null;
        }
    }

    private ExpectedCondition<WebElement> visibilityOfElementLocated(final By by)
            throws NoSuchElementException {
        LOG.info("Running : visibilityOfElementLocated");
        return driver -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                LOG.error(e.getMessage());
            }
            WebElement element = getWebDriver().findElement(by);
            return element.isDisplayed() ? element : null;
        };
    }

    /**
     * An expectation for checking if the given text is present in the specified element.
     *
     * @param element the WebElement
     * @param text    to be present in the element
     * @return true once the element contains the given text
     */
    public boolean textToBePresentInElement(WebElement element, String text) {
        LOG.info("Running : textToBePresentInElement");
        return new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME)
                .until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    /**
     * An expectation for checking if the given text is present in the element that matches the given
     * locator.
     *
     * @param by   used to find the element
     * @param text to be present in the element found by the locator
     * @return true once the first element located by locator contains the given text
     */
    public boolean textToBePresentInElementLocated(final By by, final String text) {
        LOG.info("Running : textToBePresentInElementLocated");
        return new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME)
                .until(ExpectedConditions.textToBePresentInElementLocated(by, text));
    }

    /**
     * An expectation for checking if the given text is present in the specified elements value
     * attribute.
     *
     * @param element the WebElement
     * @param text    to be present in the element's value attribute
     * @return true once the element's value attribute contains the given text
     */
    public boolean textToBePresentInElementValue(final WebElement element, final String text) {
        LOG.info("Running : textToBePresentInElementValue");
        return new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME)
                .until(ExpectedConditions.textToBePresentInElementValue(element, text));
    }

    /**
     * An expectation for checking if the given text is present in the specified elements value
     * attribute.
     *
     * @param by   used to find the element
     * @param text to be present in the value attribute of the element found by the locator
     * @return true once the value attribute of the first element located by locator contains the
     * given text
     */
    public boolean textToBePresentInElementValue(final By by, final String text) {
        LOG.info("Running : textToBePresentInElementValue");
        return new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME)
                .until(ExpectedConditions.textToBePresentInElementValue(by, text));
    }

    /**
     * An expectation for checking whether the given frame is available to switch to. <p> If the frame
     * is available it switches the given driver to the specified frame.
     *
     * @param frameLocator used to find the frame (id or name)
     */
    public WebDriver frameToBeAvailableAndSwitchToIt(final String frameLocator) {
        LOG.info("Running : frameToBeAvailableAndSwitchToIt");
        return new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
    }

    /**
     * An expectation for checking whether the given frame is available to switch to. <p> If the frame
     * is available it switches the given driver to the specified frame.
     *
     * @param by used to find the frame
     */
    public WebDriver frameToBeAvailableAndSwitchToIt(final By by) {
        LOG.info("Running : frameToBeAvailableAndSwitchToIt");
        return new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(by));
    }

    /**
     * An expectation for checking that an element is either invisible or not present on the DOM.
     *
     * @param by used to find the element
     */
    public boolean invisibilityOfElementLocated(By by) {
        LOG.info("Running : invisibilityOfElementLocated");
        return (new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME))
                .until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    /**
     * An expectation for checking that an element with text is either invisible or not present on the
     * DOM.
     *
     * @param by   used to find the element
     * @param text of the element
     */
    public boolean invisibilityOfElementWithText(final By by, final String text) {
        LOG.info("Running : invisibilityOfElementWithText");
        return (new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME))
                .until(ExpectedConditions.invisibilityOfElementWithText(by, text));
    }

    /**
     * An expectation for checking an element is visible and enabled such that you can click it.
     *
     * @param by used to find the element
     * @return the WebElement once it is located and clickable (visible and enabled)
     */
    public WebElement elementToBeClickable(By by) {
        LOG.info("Running : elementToBeClickable");
        return (new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME))
                .until(ExpectedConditions.elementToBeClickable(by));
    }

    /**
     * An expectation for checking an element is visible and enabled such that you can click it.
     *
     * @param element the WebElement
     * @return the (same) WebElement once it is clickable (visible and enabled)
     */
    public WebElement elementToBeClickable(final WebElement element) {
        LOG.info("Running : elementToBeClickable");
        return (new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Wait until an element is no longer attached to the DOM.
     *
     * @param element The element to wait for.
     * @return false is the element is still attached to the DOM, true otherwise.
     */
    public boolean stalenessOf(final WebElement element) {
        LOG.info("Running : stalenessOf");
        return (new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME))
                .until(ExpectedConditions.stalenessOf(element));
    }

    /**
     * An expectation for checking if the given element is selected.
     */
    public boolean elementToBeSelected(final By by) {
        LOG.info("Running : elementToBeSelected");
        return (new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME))
                .until(ExpectedConditions.elementToBeSelected(by));
    }

    /**
     * An expectation for checking if the given element is selected.
     */
    public boolean elementToBeSelected(final WebElement element) {
        LOG.info("Running : elementToBeSelected");
        return (new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME))
                .until(ExpectedConditions.elementToBeSelected(element));
    }

    /**
     * An expectation for checking if the given element is selected.
     */
    public boolean elementSelectionStateToBe(final WebElement element, final boolean selected) {
        LOG.info("Running : elementSelectionStateToBe");
        return (new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME))
                .until(ExpectedConditions.elementSelectionStateToBe(element, selected));
    }

    /**
     * An expectation for checking if the given element is selected.
     */
    public boolean elementSelectionStateToBe(final By by, final boolean selected) {
        LOG.info("Running : elementSelectionStateToBe");
        return (new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME))
                .until(ExpectedConditions.elementSelectionStateToBe(by, selected));
    }

    public void waitForAlert() {
        LOG.info("Running : waitForAlert");
        (new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME))
                .until(ExpectedConditions.alertIsPresent());
    }

    /**
     * An expectation for checking that all elements present on the web page that match the locator
     * are visible. Visibility means that the elements are not only displayed but also have a height
     * and width that is greater than 0.
     *
     * @param by used to find the element
     * @return the list of WebElements once they are located
     */
    public List<WebElement> visibilityOfAllElementsLocatedBy(final By by) {
        LOG.info("Running : visibilityOfAllElementsLocatedBy");
        return (new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    /**
     * An expectation for checking that all elements present on the web page that match the locator
     * are visible. Visibility means that the elements are not only displayed but also have a height
     * and width that is greater than 0.
     *
     * @param elements list of WebElements
     * @return the list of WebElements once they are located
     */
    public List<WebElement> visibilityOfAllElements(final List<WebElement> elements) {
        LOG.info("Running : visibilityOfAllElements");
        return (new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME))
                .until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    /**
     * An expectation for checking that there is at least one element present on a web page.
     *
     * @param by used to find the element
     * @return the list of WebElements once they are located
     */
    public List<WebElement> presenceOfAllElementsLocatedBy(final By by) {
        LOG.info("Running : presenceOfAllElementsLocatedBy");
        return (new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    /**
     * An expectation for checking that an element, known to be present on the DOM of a page, is
     * visible. Visibility means that the element is not only displayed but also has a height and
     * width that is greater than 0.
     *
     * @param element the WebElement
     * @return the (same) WebElement once it is visible
     */
    public WebElement visibilityOf(final WebElement element) {
        LOG.info("Running : visibilityOf");
        return (new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME))
                .until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * An expectation for checking that an element is present on the DOM of a page. This does not
     * necessarily mean that the element is visible.
     *
     * @param by used to find the element
     * @return the WebElement once it is located
     */
    public boolean isElementPresent(final By by) {
        LOG.info("Running : isElementPresent");
        try {
            new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME)
                    .until(ExpectedConditions.presenceOfElementLocated(by));

        } catch (TimeoutException exception) {
            LOG.info(exception.getMessage());
            return false;
        }
        return true;
    }

    public WebDriver getBrowserByPageTitle(String pageTitle) {
        LOG.info("Running : getBrowserByPageTitle");
        for (String windowHandle : webDriver.getWindowHandles()) {
            webDriver = webDriver.switchTo().window(windowHandle);
            if (pageTitle.equalsIgnoreCase(webDriver.getTitle())) {
                return webDriver;
            }
        }
        return null;
    }

    public void clickWithinElementWithXYCoordinates(WebElement webElement, int x, int y) {
        LOG.info("Running : clickWithinElementWithXYCoordinates");
        Actions builder = new Actions(webDriver);
        builder.moveToElement(webElement, x, y);
        builder.click();
        builder.perform();
    }

    public String getElementByTagNameWithJSExecutor(String tagName) {
        LOG.info("Running : getElementByTagNameWithJSExecutor");
        return ((JavascriptExecutor) webDriver).executeScript(
                        "return window.getComputedStyle(document.getElementsByTagName('" + tagName + "')")
                .toString();
    }

    public String getElementByQueryJSExecutor(String cssSelector) {
        LOG.info("Running : getElementByQueryJSExecutor");
        return ((JavascriptExecutor) webDriver).executeScript(
                "return window.getComputedStyle(document.querySelector('" + cssSelector + "')").toString();
    }


    protected boolean isElementClickable(By by) {
        LOG.info("Running : isElementClickable");
        try {
            new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME)
                    .until(ExpectedConditions.elementToBeClickable(by));
        } catch (TimeoutException exception) {
            return false;
        }
        return true;
    }

    public void hoverOnElement(WebElement element) {
        LOG.info("Running : hoverOnElement");
        Actions action = new Actions(webDriver);
        action.moveToElement(element).build().perform();
    }

    public void hoverOnElement(By by) {
        LOG.info("Running : hoverOnElement");
        Actions action = new Actions(webDriver);
        action.moveToElement(webDriver.findElement(by)).build().perform();
    }

    public void clickUsingJS(By by) {
        LOG.info("Running : clickUsingJS");
        WebElement element = getWebDriver().findElement(by);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click()", element);
    }

    public List<WebElement> getAllElements(By by) {
        LOG.info("Running :  getAllElements");
        List<WebElement> lists = webDriver.findElements(by);
        LOG.info("Total number of LISTS found : " + lists.size());
        return lists;
    }

    public String getUpdatedPropertyValue(String propFilePath,String orderNumber)
    {
        String orderID= null;
        try {
            PropertiesConfiguration config = new PropertiesConfiguration(propFilePath);
            orderID = config.getProperty(orderNumber).toString();
            LOG.info("Updated Order Number information found successfully");
        } catch (Exception e) {
            LOG.error("Updated Order Number information not found" + e.getMessage());
            e.printStackTrace();
        }
        return orderID;
    }

    public void selectDropDownValue(By locator, String type, String value)
    {
        Select select = new Select(webDriver.findElement(locator));
        switch (type){
            case"index":
                select.selectByIndex(Integer.parseInt(value));
                break;
            case"value":
                select.selectByValue(value);
                break;
            case "visibleText":
                select.selectByVisibleText(value);
                break;
            default:
                LOG.info("please pass the correct selection criteria");
                break;
        }
    }
}