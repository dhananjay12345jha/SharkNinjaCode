package com.salmon.test.step_definitions.gui.SNCA;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.page_objects.gui.SNCA.ForgotPasswordPageSNCA;
import com.salmon.test.page_objects.gui.SNCA.HomePageSNCA;
import com.salmon.test.page_objects.gui.SNUS.HomePageSNUS;
import com.salmon.test.page_objects.gui.SNCA.MyAccountPageSNCA;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class LoginProcessSteps {

    private HomePageSNCA homePageSNCA;
    private HomePageSNUS homePageSNUS;
    private MyAccountPageSNCA myAccountPageSNCA;

    public LoginProcessSteps() {
        homePageSNCA = new HomePageSNCA();
        homePageSNUS = new HomePageSNUS();
        myAccountPageSNCA=new MyAccountPageSNCA();
    }

    @When("^I click on sign in link for SNCA$")
    @And("^I click on sign in link for SNUS$")
    public void click_on_sign_in_link() {
        Assert.assertTrue(homePageSNCA.openLoginInPopUp(), "Unable to click on sign in button over SharkCA ");
    }

    @And("^select language defined in property file$")
    public void select_language_for_which_test_needs_to_run() {
        String language = Props.getProp("select.language.to.test");
        if (language.equalsIgnoreCase("fr")) {
            homePageSNCA.selectLanguage(language);
        }
        if (language.equalsIgnoreCase("en")) {
            if (!homePageSNCA.whichLanguageIsSelected().equalsIgnoreCase("en")) {
                homePageSNCA.selectLanguage(language);
            }
        }
    }

    @And("^click on accept cookies if visible$")
    public void click_to_accept_cookies() throws InterruptedException {
        homePageSNCA.clickToAcceptCookiesBtn();
    }
    @And("^click on accept email if visible$")
    public void click_to_accept_email() throws InterruptedException {

        homePageSNCA.clickToAcceptPopUpBtn();
    }

    @Then("^I should be on log in page of SNCA$")
    public void validate_i_should_be_on_login_page() {
        if (Props.getProp("select.language.to.test").equalsIgnoreCase("FR")) {
            Assert.assertEquals(homePageSNCA.getSignInText(), "Se connecter");
        } else {
            Assert.assertEquals(homePageSNCA.getSignInText(), "Sign In");
        }
    }

    @Then("^I should be on log in page of SNUS$")
    public void validate_i_should_be_on_login_page_SNUS() {
        String expected="";
        if (Props.getProp("select.language.to.test").equalsIgnoreCase("FR")) {
            expected = Props.getMessage("login.page.message.fr");
        } else {
            expected = Props.getMessage("login.page.message.en");
        }
        Assert.assertEquals(homePageSNUS.getLogInText(), expected);
    }

    @When("^I enter email as \"([^\"]*)\" and password as \"([^\"]*)\" over SNCA$")
    @And("^I enter email as \"([^\"]*)\" and password as \"([^\"]*)\" over SNUS$")
    public void enter_login_email_and_password(String email, String password) {
        String mail="";
        String pass;
        if(Props.getProp(email)!=null ){
            mail=Props.getProp(email);
        }
        else {
            mail=email;
        }
        if(Props.getProp(password)!=null){
            pass=Props.getProp(password);
        }
        else {
            pass=password;
        }

        Assert.assertTrue(homePageSNCA.enterEmail(mail));
        Assert.assertTrue(homePageSNCA.enterPassword(pass));
    }

    @And("^I clicked on sign in button$")
    public void clicked_on_signIn_button() {
        myAccountPageSNCA = homePageSNCA.clickSignInBtn();
        Assert.assertTrue(myAccountPageSNCA != null, "Unable to click sign in button over home page of SharkCA");
    }
    @Then("^I should be on My Account page$")
    public void i_should_be_on_my_account_page() {
        if (Props.getProp("select.language.to.test").equalsIgnoreCase("FR")) {
            Assert.assertEquals(myAccountPageSNCA.getTextMyAccount(), "Mon compte");
            //Assert.assertEquals(myAccountPageSNCA.getTextMyAccount(), Props.getProp("myAccount.title"));
        } else {
            Assert.assertEquals(myAccountPageSNCA.getTextMyAccount(), "My Account");
        }

    }

    @Then("^error message \"([^\"]*)\" or \"([^\"]*)\" should be shown depending upon data type send for \"([^\"]*)\" and \"([^\"]*)\"$")
    public void validate_error_message_depending_upon_data_type(String errorMessageEN, String errorMessageFR,
                                                                String dataTypeEmail, String dataTypePassword) {
        String actual, expected;

        if (Props.getProp("select.language.to.test").equalsIgnoreCase("FR")) {
            expected = errorMessageFR;
        } else {
            expected = errorMessageEN;
        }

        if (dataTypeEmail.equalsIgnoreCase("valid") && dataTypePassword.equalsIgnoreCase("valid")) {
            actual = homePageSNCA.getErrorMessageFromAlertOnSignInPopUp();
            Assert.assertEquals(actual, expected);
        } else {
            if (dataTypeEmail.equalsIgnoreCase("empty") && dataTypePassword.equalsIgnoreCase("empty")) {
                SoftAssert softAssert = new SoftAssert();
                String[] errorMessage = expected.split(",");
                List<String> messages = homePageSNCA.getErrorMessageWhenEnterInvalidEmailOrPassword();
                Assert.assertTrue(messages.size() > 0,
                        "Returning list is having size zero unable to compare error message");
                softAssert.assertEquals(messages.get(0), errorMessage[0]);
                softAssert.assertEquals(messages.get(1), errorMessage[1]);
                softAssert.assertAll();
            } else {
                actual = homePageSNCA.getErrorMessageWhenEnterInvalidEmailOrPassword().get(0);
                Assert.assertEquals(actual, expected);
            }
        }
    }

    @Then("^login error message \"([^\"]*)\" or \"([^\"]*)\" should be shown depending upon data type send for \"([^\"]*)\" and \"([^\"]*)\"$")
    public void validate_login_error_message_depending_upon_data_type(String errorMessageEN, String errorMessageFR,
                                                                      String dataTypeEmail, String dataTypePassword) {
        String actual, expected;

        if (Props.getProp("select.language.to.test").equalsIgnoreCase("FR")) {
            expected = errorMessageFR;
        } else {
            expected = errorMessageEN;
        }

        if (dataTypeEmail.equalsIgnoreCase("valid") && dataTypePassword.equalsIgnoreCase("valid")) {
            actual = homePageSNUS.getErrorMessageFromAlertOnSignInPopUp();
            Assert.assertEquals(actual, expected);
        } else {
            if (dataTypeEmail.equalsIgnoreCase("empty") && dataTypePassword.equalsIgnoreCase("empty")) {
                SoftAssert softAssert = new SoftAssert();
                String[] errorMessage = expected.split(",");
                List<String> messages = homePageSNCA.getErrorMessageWhenEnterInvalidEmailOrPassword();
                Assert.assertTrue(messages.size() > 0,
                        "Returning list is having size zero unable to compare error message");
                softAssert.assertEquals(messages.get(0), errorMessage[0]);
                softAssert.assertEquals(messages.get(1), errorMessage[1]);
                softAssert.assertAll();
            } else {
                actual = homePageSNCA.getErrorMessageWhenEnterInvalidEmailOrPassword().get(0);
                Assert.assertEquals(actual, expected);
            }
        }
    }

    @And("^language selected should be equal to \"([^\"]*)\"$")
    public void validate_language_selected_on_page_should_be_equal_to(String language) {
        String expected = Props.getProp(language);
        String actual = homePageSNCA.whichLanguageIsSelected();
        Assert.assertEquals(actual, expected, "Unable to match the language selected at home page and on this page");
    }

    @Then("^I am logout$")
    public void verifyUserLogout() throws InterruptedException {
        String expected="";
        if (Props.getProp("select.language.to.test").equalsIgnoreCase("FR")) {
            expected = Props.getMessage("login.page.message.fr");
        } else {
            expected = Props.getMessage("login.page.message.en");
        }
        Assert.assertTrue(homePageSNCA.openLoginInPopUp(), "Unable to click on sign in button over SharkCA ");
        Thread.sleep(2000);
        Assert.assertEquals(homePageSNUS.getLogInText(), expected);
    }


    @When("^I login with valid credentials$")
    public void i_login_with_valid_credentials() throws Throwable {
        myAccountPageSNCA = homePageSNCA.loginwithValidCredentials();
        Assert.assertTrue(myAccountPageSNCA != null, "Unable to click sign in button over home page of SharkCA");
    }


    @When("^I login with valid Update Profile credentials$")
    public void iLoginWithValidUpdateProfileCredentials() {
        myAccountPageSNCA = homePageSNCA.loginwithGivenCredentials("login.updateProfile.userName", "login.updateProfile.password");
        Assert.assertTrue(myAccountPageSNCA != null, "Unable to click sign in button over home page of SharkCA");

    }


    @When("^I login with given credentials \"([^\"]*)\" and \"([^\"]*)\"$")
    public void iLoginWithGivenCredentialsAnd(String userName, String password) {
        myAccountPageSNCA = homePageSNCA.loginwithGivenCredentials(userName, password);
        Assert.assertTrue(myAccountPageSNCA != null, "Unable to click sign in button over home page of SharkCA");

    }

    @When("^I click on resend email link in SNUSCA$")
    public void iClickOnResendEmailLinkInSNUSCA() {
        homePageSNCA.clickOnResendVerificationEmail();

    }
}
