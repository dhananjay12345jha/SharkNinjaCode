package com.salmon.test.page_objects.gui.SNAP;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.function.Function;

public class AgentLogin  extends PageObject {

 //   private static final By AgentLoginEmail=By.xpath("/html/body/ish-root/div/snap-tabs-container/div/div/snap-login-page/div/div/div/div/snap-login-form/form/snap-input[1]/div/input");
    private String env=Props.getProp("zendesk.testing.env");
    private static final By AgentLoginEmail=By.xpath("//*[@placeholder='Username']");
    private static final By AgentLoginPassword=By.xpath("//*[@placeholder='Password']");
//    private static final By AgentLoginPassword=By.xpath("/html/body/ish-root/div/snap-tabs-container/div/div/snap-login-page/div/div/div/div/snap-login-form/form/snap-input[2]/div/input");
    private static final By AgentLogInButton=By.xpath("//*[@name='login']");
   // private static final By iframe = By.xpath("//iframe[starts-with(@name,'app_INT')]");
   // private static final By UATFrame = By.xpath("//iframe[starts-with(@name,'app_UAT-SNAP_modal')]");
    private  final By iframe= By.xpath("(//iframe[contains(@name,'"+env+"')])[4]");
    private static final By Display =By.xpath("//*[@class='snap-login-form-container']");
    private static final By logoutBtn =By.xpath("//*[@class='my-account-link my-account-logout']");
//    Added by Arun
    private static final By UATFrame = By.xpath( "(//iframe[contains(@name,'UAT')])[2]" );
    
    private static final Logger log = LoggerFactory.getLogger(AgentLogin.class);


    public AgentProfilePage submitAgentLoginCredentials ()
    {
        boolean agentLoggedInBefore=false;

        try {
            webDriver.switchTo().defaultContent();
            webDriver.switchTo().frame(waitForExpectedElement(iframe));
            log.info("Waiting for presence of agent dashboard incase agent is logged in before");
            Wait<WebDriver> wait = new FluentWait<WebDriver>(webDriver)
                    .withTimeout(Duration.ofSeconds(10))
                    .pollingEvery(Duration.ofSeconds(2))
                    .ignoring(NoSuchElementException.class);

            WebElement element = wait.until(new Function<WebDriver, WebElement>() {
                public WebElement apply(WebDriver driver) {
                    return driver.findElement(logoutBtn);
                }
            });

            if(element.getText().trim().equalsIgnoreCase("logout"))
            {
                log.info("Agent is logged in before and dashboard in getting displayed");
                log.info("Text from logout button is displaying as ->>"+element.getText().trim());
                agentLoggedInBefore=true;
            }
        }catch (ElementNotFoundException e1)
        {
            log.info("Agent is not logged in before trying log in with valid credentials");
        }catch (Exception e)
        {
            log.info("Some exception occurred please check-->>"+e.getMessage());
        }


        if(!agentLoggedInBefore)
        {

            Actions actions=new Actions(getWebDriver());
            WebElement userName=waitForExpectedElement(AgentLoginEmail);
            actions.moveToElement(userName).click().build().perform();
            userName.sendKeys(Props.getProp("agent.login.username"));

            WebElement password=waitForExpectedElement(AgentLoginPassword);
            actions.moveToElement(password).click().build().perform();
            password.sendKeys(Props.getProp("agent.login.password"));

            actions.moveToElement(waitForExpectedElement(AgentLogInButton)).click().build().perform();

            if(waitForExpectedElement(logoutBtn).getText().equalsIgnoreCase("logout"))
            {
                agentLoggedInBefore=true;
                log.info("Successfully logged into the agent account");
            }
        }

        webDriver.switchTo().defaultContent();
       if(!agentLoggedInBefore)
        return null;
       else
           return new AgentProfilePage();

    }
}
