package com.salmon.test.step_definitions.gui;


import com.salmon.test.enums.PermittedSiteMode;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.WebDriverHelper;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.commons.logging.Log;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.MalformedURLException;import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

public class NavigationSteps {


    @Given("^I navigate to the \"(.*?)\" page$")
    public void I_navigate_to_the_page(String pageName) {
        if (pageName.equalsIgnoreCase("HOME")) {
            UrlBuilder.startAtHomePage();
        }
    }


    //commenting out as not executing on mobile right now
//  /*
    @Given("^I navigate to the mobile \"(.*?)\" page$")
    public void I_navigate_to_the_mobile_page(String pageName) {
        if (pageName.equalsIgnoreCase("HOME")) {
            UrlBuilder.startAtMobileHomePage();
        }
    }
//*/

    @And("^again i open the site url$")
    public void open_the_site_url() {
        UrlBuilder.navigateToTheBaseUrl();
    }

    @And("^again i open the mainsite url$")
    public void open_the_mainsite_url() {
        UrlBuilder.navigateToGivenUrl(Props.getProp("mainsite.uat.url"));
    }

    @And("^I navigate to the Ninja Homepage$")
    public void open_the_Ninja_url() {
        try {
            if (Props.getExecutionEnv().equalsIgnoreCase("UAT"))
         UrlBuilder.navigateToGivenUrl(Props.getProp("ninja.uat.url"));
            else
            if (Props.getExecutionEnv().equalsIgnoreCase("UATA"))
                UrlBuilder.navigateToGivenUrl(Props.getProp("ninja.uata.url"));
        }
        catch(Exception e ){
            System.out.println(e);
        }
    }

    @And("^I navigate to the NinjaDE Homepage$")
    public void open_the_NinjaDE_url() {
        try {
            if (Props.getExecutionEnv().equalsIgnoreCase("UAT"))
        UrlBuilder.navigateToGivenUrl(Props.getProp("ninjaDE.uat.url"));
            else
                UrlBuilder.navigateToGivenUrl(Props.getProp("ninjaDE.uata.url"));
        }
        catch(Exception e ){
            System.out.println(e);
        }
    }

    @And("^I navigate to the SharkDE Homepage$")
    public void open_the_SharkDE_url() {
        try {
            if (Props.getExecutionEnv().equalsIgnoreCase("UAT"))
        UrlBuilder.navigateToGivenUrl(Props.getProp("sharkDE.uat.url"));
            else
                UrlBuilder.navigateToGivenUrl(Props.getProp("sharkDE.uata.url"));
        }
        catch(Exception e ){
            System.out.println(e);
        }
    }

    @And("^I navigate to the Shark Homepage$")
    public void open_the_Shark_url() {
        try {
            if (Props.getExecutionEnv().equalsIgnoreCase("UAT"))
                UrlBuilder.navigateToGivenUrl(Props.getProp("shark.uat.url"));
            else
                UrlBuilder.navigateToGivenUrl(Props.getProp("shark.uata.url"));
        }
            catch(Exception e ){
                System.out.println(e);
            }
        }


    @And("^I navigate to the Order Status Page$")
    public void open_the_order_status_page() {
        IsPageLoaded.waitAllRequest();
        UrlBuilder.navigateToGivenUrl(Props.getProp("order_status_page.uat.url"));
    }

    @And("^I navigate to the SharkCA Homepage$")
    public void open_the_SharkCA_url() {
        UrlBuilder.navigateToGivenUrl(Props.getProp("sharkCA.uat.url"));
    }

}


