package com.salmon.test.framework.helpers.utils;
/************
 @author Sanket Jha*
 */

import com.salmon.test.framework.PageObject;
import com.salmon.test.page_objects.gui.SNCA.HomePageSNCA;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IsPageLoaded extends PageObject {

    private static final Logger log = LoggerFactory.getLogger(IsPageLoaded.class);
    private static WebDriver jsWaitDriver;
    private static WebDriverWait jsWait;
    private static JavascriptExecutor jsExec;

    //Get the driver
    private static void setDriver() {
        jsWaitDriver = getWebDriver();
        jsWait = new WebDriverWait(jsWaitDriver, 10);
        jsExec = (JavascriptExecutor) jsWaitDriver;
    }

    private static void ajaxComplete() {
        try {
            setDriver();
            jsExec.executeScript("var callback = arguments[arguments.length - 1];"
                    + "var xhr = new XMLHttpRequest();" + "xhr.open('GET', '/Ajax_call', true);"
                    + "xhr.onreadystatechange = function() {" + "  if (xhr.readyState == 4) {"
                    + "    callback(xhr.responseText);" + "  }" + "};" + "xhr.send();");
        } catch (Exception e) {
            log.error("ajaxComplete--->>" + ExceptionUtils.getStackTrace(e));
        }
    }

    private static void waitForJQueryLoad() {
        try {
            ExpectedCondition<Boolean> jQueryLoad = driver -> ((Long) ((JavascriptExecutor) driver)
                    .executeScript("return jQuery.active") == 0);

            boolean jqueryReady = (Boolean) jsExec.executeScript("return jQuery.active==0");

            if (!jqueryReady) {
                jsWait.until(jQueryLoad);
            }
        } catch (WebDriverException ignored) {
        }
        catch (Exception e) {
            log.error("waitForJQueryLoad--->>" + ExceptionUtils.getStackTrace(e));
        }
    }

    private static void waitForAngularLoad() {
        String angularReadyScript = "return angular.element(document).injector().get('$http').pendingRequests.length === 0";
        angularLoads(angularReadyScript);
    }

    private static void waitUntilJSReady() {
        setDriver();
        try {
            ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");

            boolean jsReady = jsExec.executeScript("return document.readyState").toString().equals("complete");

            if (!jsReady) {
                jsWait.until(jsLoad);
            }
        } catch (WebDriverException ignored) {
            log.error("Some error occured in JS Ready " + ExceptionUtils.getStackTrace(ignored));
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
        }
    }

    private static void waitUntilJQueryReady() {
        try {
            setDriver();
            Boolean jQueryDefined = (Boolean) jsExec.executeScript("return typeof jQuery != 'undefined'");
            if (jQueryDefined) {
                poll(500);

                waitForJQueryLoad();

                poll(500);
            }
        } catch (Exception e) {
            log.error("Some Exception occured waitUntilJQueryReady-->>" + ExceptionUtils.getStackTrace(e));
        }
    }

    public static void waitUntilAngularReady() {
        setDriver();
        try {
            Boolean angularUnDefined = (Boolean) jsExec.executeScript("return window.angular === undefined");
            //System.out.println("Is Angular Present "+angularUnDefined);
            if (!angularUnDefined) {
                Boolean angularInjectorUnDefined = (Boolean) jsExec.executeScript("return angular.element(document).injector() === undefined");
                //System.out.println("Value of angular injector "+angularInjectorUnDefined);
                if (!angularInjectorUnDefined) {
                    System.out.println("angular is Present");
                    poll(500);

                    waitForAngularLoad();

                    poll(500);
                }
            }
        } catch (WebDriverException ignored) {
            System.out.println("Method wait untill Angular ready throwing error-->>" + ignored.getMessage());
        } catch (Exception e) {
            log.error("Method waitUntilAngularReady-->>" + ExceptionUtils.getStackTrace(e));
        }
    }

    public void waitUntilAngular5Ready() {
        try {
            Object angular5Check = jsExec.executeScript("return getAllAngularRootElements()[0].attributes['ng-version']");
            if (angular5Check != null) {
                Boolean angularPageLoaded = (Boolean) jsExec.executeScript("return window.getAllAngularTestabilities().findIndex(x=>!x.isStable()) === -1");
                if (!angularPageLoaded) {
                    poll(500);

                    waitForAngular5Load();

                    poll(500);
                }
            }
        } catch (WebDriverException ignored) {
            //System.out.println("Method wait waitUntilAngular5Read throwing error-->>"+ignored.getMessage());
        } catch (Exception e) {
            log.error("Method waitUntilAngular5Ready-->>" + ExceptionUtils.getStackTrace(e));
        }
    }

    private void waitForAngular5Load() {
        String angularReadyScript = "return window.getAllAngularTestabilities().findIndex(x=>!x.isStable()) === -1";
        angularLoads(angularReadyScript);
    }

    private static void angularLoads(String angularReadyScript) {
        try {
            ExpectedCondition<Boolean> angularLoad = driver -> Boolean.valueOf(((JavascriptExecutor) driver).executeScript(angularReadyScript).toString());

            //System.out.println("is angular script is ready-->"+angularLoad);

            boolean angularReady = Boolean.valueOf(jsExec.executeScript(angularReadyScript).toString());

            if (!angularReady) {
                jsWait.until(angularLoad);
            }
        } catch (WebDriverException ignored) {
        } catch (Exception e) {
            log.error("Method angularLoads-->>" + ExceptionUtils.getStackTrace(e));
        }
    }

    public static void waitAllRequest() {
        setDriver();
        waitUntilAngularReady();
        waitUntilJQueryReady();
        waitUntilJSReady();
        ajaxComplete();
        //waitUntilAngular5Ready();
    }

    /**
     * Method to make sure a specific element has loaded on the page
     *
     * @param by
     * @param expected
     */
    public static void waitForElementAreComplete(By by, int expected) {
        setDriver();
        ExpectedCondition<Boolean> angularLoad = driver ->
        {
            int loadingElements = driver.findElements(by).size();
            return loadingElements >= expected;
        };
        jsWait.until(angularLoad);
    }

    /**
     * Waits for the elements animation to be completed
     *
     * @param css
     */
    public void waitForAnimationToComplete(String css) {
        ExpectedCondition<Boolean> angularLoad = driver -> {
            int loadingElements = driver.findElements(By.cssSelector(css)).size();
            return loadingElements == 0;
        };
        jsWait.until(angularLoad);
    }

    private static void poll(long milis) {
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
