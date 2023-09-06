package com.salmon.test.step_definitions.gui;

import static org.assertj.core.api.Assertions.assertThat;

import com.salmon.test.framework.helpers.UrlBuilder;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.page_objects.gui.HomePage;
import com.salmon.test.page_objects.gui.LoginProcessPage;
import com.salmon.test.page_objects.gui.MyAccountOverviewPage;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginProcessSteps extends PageObject {

    private LoginProcessPage loginProcessPage;
    private HomePage homePage;
    private MyAccountOverviewPage myAccountPage;

    public LoginProcessSteps(LoginProcessPage loginProcessPage, HomePage homePage, MyAccountOverviewPage myAccountPage) {
        this.loginProcessPage = loginProcessPage;
        this.homePage = homePage;
        this.myAccountPage = myAccountPage;
    }


    @Then("^I should be on log in page$")
    public void I_should_be_on_log_in_page() {
        Assert.assertTrue(wait.until(ExpectedConditions.titleContains(Props.getProp("login.page"))));
    }

    @When("^I enter valid \"([^\"]*)\" and \"([^\"]*)\"$")
    public void i_enter_valid_and(String loginEmail, String loginPassword) {
        String emailLogin, passwordLogin;
        if (Props.getProp(loginEmail) != null && !Props.getProp(loginEmail).isEmpty()) {
            emailLogin = Props.getProp(loginEmail);
        } else {
            emailLogin = loginEmail;
        }

        if (Props.getProp(loginPassword) != null && !Props.getProp(loginPassword).isEmpty()) {
            passwordLogin = Props.getProp(loginPassword);
        } else {
            passwordLogin = loginPassword;
        }

        assertThat(loginProcessPage.checkLoginUserForm()).isTrue();

        loginProcessPage.emailLogin().click();
        loginProcessPage.emailLogin().clear();
        loginProcessPage.emailLogin().sendKeys(emailLogin);
        wait.until(ExpectedConditions.elementToBeClickable(loginProcessPage.passwordLogin())).click();
        loginProcessPage.passwordLogin().clear();
        loginProcessPage.passwordLogin().sendKeys(passwordLogin);
        loginProcessPage.signInButton();
    }

    @When("^I enter valid email and password \"([^\"]*)\" and \"([^\"]*)\"$")
    public void i_enter_valid_email_and_password_and(String loginEmail, String loginPassword) {
        String emailLogin, passwordLogin;
        if (Props.getProp(loginEmail) != null && !Props.getProp(loginEmail).isEmpty()) {
            emailLogin = Props.getProp(loginEmail);
        } else {
            emailLogin = loginEmail;
        }

        if (Props.getProp(loginPassword) != null && !Props.getProp(loginPassword).isEmpty()) {
            passwordLogin = Props.getProp(loginPassword);
        } else {
            passwordLogin = loginPassword;
        }

        assertThat(loginProcessPage.checkLoginUserForm()).isTrue();

        loginProcessPage.emailLogin().click();
        loginProcessPage.emailLogin().clear();
        loginProcessPage.emailLogin().sendKeys(emailLogin);
        wait.until(ExpectedConditions.elementToBeClickable(loginProcessPage.passwordLogin())).click();
        loginProcessPage.passwordLogin().clear();
        loginProcessPage.passwordLogin().sendKeys(passwordLogin);
        loginProcessPage.signInButton();
    }

    @Then("^I should enter \"(.*)\" order number$")
    public void i_should_enter_order_number(String orderId) {
        loginProcessPage.EnterOrderNo(Props.getProp(orderId));
    }

    @Then("^I should enter \"(.*)\" the delivery postal code$")
    public void i_should_enter_the_delivery_postal_code(String postalCode) {
        loginProcessPage.enterDeliveryPostalCode(Props.getProp(postalCode));
    }

    @When("^I click on Find My Order Button$")
    public void clickFindMyOrderButton() {
        loginProcessPage.clickFindMyOrderButton();
    }

    @Then("^I should be on Order Details page$")
    public void verifyOrderDetailsPage() throws InterruptedException {
        if (UrlBuilder.isDesktop() || UrlBuilder.isTablet())
        {
            Assert.assertTrue(loginProcessPage.getHeading().contentEquals(Props.getProp("orderStatus.details.page.heading")));
            Thread.sleep(3000);
        }
        else if(UrlBuilder.isMobile()) {
            Assert.assertTrue(loginProcessPage.getHeading().contentEquals(Props.getProp("orderStatus.details.page.heading")));
            Thread.sleep(3000);
        }

    }

    @Then("^I am successfully logged in$")
    public void i_am_successfully_logged_in() {
        Assert.assertTrue(homePage.verifyHeaderMyAccountLink());
        Assert.assertTrue(homePage.verifyHeaderLogoutLink());
    }

    @Then("^I should be on My Account Overview page$")
    public void I_should_be_on_My_Account_Overview_page() {
        Assert.assertTrue(myAccountPage.getHeading().contains(Props.getProp("myaccount.page.heading")));
    }

}
