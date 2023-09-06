package com.salmon.test.step_definitions.gui;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.page_objects.gui.MyAccountEmailEditPage;
import com.salmon.test.page_objects.gui.MyAccountPwdEditPage;
import com.salmon.test.page_objects.gui.MyAccountProfilePage;
import com.salmon.test.page_objects.gui.MyAccountProfileEditPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class MyAccountProfileSteps {
    private final MyAccountProfilePage myAccountProfilePage;
    private final MyAccountEmailEditPage emailEditPage;
    private final MyAccountPwdEditPage passwordEditPage;
    private final MyAccountProfileEditPage profileEditPage;
    protected static final Logger LOG = LoggerFactory.getLogger(MyAccountProfileSteps.class);
    public MyAccountProfileSteps(MyAccountProfilePage myAccountProfilePage, MyAccountEmailEditPage emailEditPage, MyAccountPwdEditPage passwordEditPage, MyAccountProfileEditPage profileEditPage) {
        this.myAccountProfilePage = myAccountProfilePage;
        this.emailEditPage = emailEditPage;
        this.passwordEditPage = passwordEditPage;
        this.profileEditPage = profileEditPage;
    }

    @Then("^I should be on Profile page$")
    public void verifyProfilePage() {
        System.out.println((myAccountProfilePage.getHeading()));
LOG.info(myAccountProfilePage.getHeading());
        Assert.assertEquals(myAccountProfilePage.getHeading(), Props.getProp("profile.page.heading"));
    }

    @When("^I click on pencil icon next to email$")
    public void clickEmailPencilIcon() {
        Assert.assertTrue(myAccountProfilePage.clickEmailPencilIcon());
    }

    @When("^I click on pencil icon next to password$")
    public void clickPwdPencilIcon() {
        Assert.assertTrue(myAccountProfilePage.clickPwdPencilIcon());
    }

    @Then("^I should be on edit email preference page$")
    public void verifyEmailEditPage() {
        Assert.assertTrue(emailEditPage.verifyHeading());
    }

    @When("^I enter new email with \"([^\"]*)\"$")
    public void fillUpdateEmail(String updateEmail) {
        String email = Props.getProp(updateEmail);
        Assert.assertTrue(emailEditPage.fillNewEmail(email));
    }

    @And("^I fill repeat new email with \"([^\"]*)\"$")
    public void fillRepeatNewEmail(String confirmEmail) {
        String email = Props.getProp(confirmEmail);
        Assert.assertTrue(emailEditPage.fillRepeatNewEmail(email));
    }

    @Then("^I should get error message for invalid email.$")
    public void verifyErrorEmailConfirmation() {
        Assert.assertTrue(emailEditPage.verifyErrorEmailConfirmation());
    }

    @Then("^error message for invalid email should be gone$")
    public void verifyEmptyProductTypeErrorMessageNotShown() {
        Assert.assertTrue(emailEditPage.verifyErrorEmailConfirmationNotShown());
    }

    @When("^I enter password with \"([^\"]*)\"$")
    public void enterPassword(String validPassword) {
        String password = Props.getProp(validPassword);
        Assert.assertTrue(emailEditPage.enterPassword(password));
    }

    @And("^Click on save changes$")
    public void clickSaveChanges() {
        Assert.assertTrue(emailEditPage.clickSaveChanges());
    }

    @Then("^I should get error message for password incorrect$")
    public void verifyErrorPasswordIncorrect() {
        Assert.assertTrue(emailEditPage.verifyErrorPasswordIncorrect());
    }

    @Then("^email should be updated$")
    public void verifyEmailUpdateSuccessMsg() {
        Assert.assertEquals(emailEditPage.verifyEmailUpdateSuccessMsg(), Props.getMessage("email.update.success.message"));
    }

    @Then("^I should be on edit password page$")
    public void verifyPasswordEditPage() {
        Assert.assertTrue(passwordEditPage.verifyHeading());
    }

    @When("^I fill current password with \"([^\"]*)\"$")
    public void fillCurrentPassword(String pwd) {
        String password = Props.getProp(pwd);
        Assert.assertTrue(passwordEditPage.fillCurrentPassword(password));
    }

    @And("^I fill new password with \"([^\"]*)\"$")
    public void fillNewPassword(String pwd) {
        String password = Props.getProp(pwd);
        Assert.assertTrue(passwordEditPage.fillNewPassword(password));
    }

    @Then("^I should see error message for password$")
    public void verifyErrorPasswordInvalid() {
        Assert.assertTrue(passwordEditPage.verifyErrorPasswordInvalid());
    }

    @Then("^Error message for password should be gone$")
    public void verifyPasswordErrorMessageNotShown() {
        Assert.assertTrue(passwordEditPage.verifyPasswordErrorMessageNotShown());
    }

    @When("^I fill confirm password with \"([^\"]*)\"$")
    public void fillConfirmPassword(String pwd) {
        String password = Props.getProp(pwd);
        Assert.assertTrue(passwordEditPage.fillConfirmPassword(password));
    }

    @Then("^I should get error message as password do not match$")
    public void verifyConfirmPasswordMismatchError() {
        Assert.assertTrue(passwordEditPage.verifyConfirmPasswordMismatchError());
    }

    @Then("^Error message for confirm password should be gone$")
    public void verifyConfirmPasswordErrorMessageNotShown() {
        Assert.assertTrue(passwordEditPage.verifyConfirmPasswordErrorMessageNotShown());
    }

    @Then("^password should be updated$")
    public void verifyPasswordUpdateSuccessMsg() {
        Assert.assertTrue(passwordEditPage.verifyPasswordUpdateSuccessMsg());
    }

    @When("^I click on pencil icon next to profile$")
    public void clickProfilePencilIcon() {
        Assert.assertTrue(myAccountProfilePage.clickProfilePencilIcon());
    }

    @Then("^I should be on edit profile page$")
    public void verifyProfileEditPage() {
        Assert.assertTrue(profileEditPage.verifyHeading());
    }

    @When("^I select salutation by value \"([^\"]*)\"$")
    public void selectSalutationByValue(String salutation) {
        String salutationValue = Props.getProp(salutation);
        Assert.assertTrue(profileEditPage.selectSalutationByValue(salutationValue));
    }

    @And("^I fill first name with \"([^\"]*)\"$")
    public void fillFirstName(String fName) {
        String firstName = Props.getProp(fName);
        Assert.assertTrue(profileEditPage.fillFirstName(firstName));
    }

    @When("^I fill last name with \"([^\"]*)\"$")
    public void fillLastName(String lName) {
        String lastName = Props.getProp(lName);
        Assert.assertTrue(profileEditPage.fillLastName(lastName));
    }

    @When("^I fill phone number with \"([^\"]*)\"$")
    public void fillPhoneNumber(String phoneNo) {
        String phoneNumber = Props.getProp(phoneNo);
        Assert.assertTrue(profileEditPage.fillPhoneNumber(phoneNumber));
    }

    @Then("^I should see error message for invalid first name$")
    public void verifyErrorInvalidFirstName() {
        Assert.assertTrue(profileEditPage.verifyErrorInvalidFirstName());
    }

    @Then("^I should see error message for invalid last name$")
    public void verifyErrorInvalidLastName() {
        Assert.assertTrue(profileEditPage.verifyErrorInvalidLastName());
    }

    @Then("^I should see error message for invalid phone number$")
    public void verifyErrorInvalidPhone() {
        Assert.assertTrue(profileEditPage.verifyErrorInvalidPhone());
    }

    @Then("^Error message for first name invalid gone$")
    public void verifyFirstNameErrorMessageNotShown() {
        Assert.assertTrue(profileEditPage.verifyFirstNameErrorMessageNotShown());
    }

    @Then("^Error message for last name invalid gone$")
    public void verifyLastNameErrorMessageNotShown() {
        Assert.assertTrue(profileEditPage.verifyLastNameErrorMessageNotShown());
    }

    @Then("^Error message for phone number invalid gone$")
    public void verifyPhoneNumberErrorMessageNotShown() {
        Assert.assertTrue(profileEditPage.verifyPhoneNumberErrorMessageNotShown());
    }

    @When("^I select Preferred Language$")
    public void selectPreferredLanguage() {
        int languageIndex = Integer.parseInt(Props.getProp("account.preferred.language.index"));
        Assert.assertTrue(profileEditPage.selectPreferredLanguage(languageIndex));
    }

    @Then("^profile should be updated$")
    public void verifyProfileUpdateSuccessMsg() {
        String expected = Props.getMessage("profile.update.success.message");
        String actual = profileEditPage.verifyProfileUpdateSuccessMsg();
        Assert.assertEquals(actual, expected);
    }

    @And("^Changes reflected on profile page$")
    public void verifyProfileUpdateChanges() {
        Assert.assertTrue(myAccountProfilePage.verifyProfileUpdateChanges());
    }

    @And("^updated email shown on profile page$")
    public void verifyProfileEmailUpdated() {
        Assert.assertTrue(myAccountProfilePage.verifyProfileEmailUpdated());
    }

    @When("^I enter email in preference form$")
    public void enterEmailInPreferenceForm() {
        Assert.assertTrue(profileEditPage.enterEmailInPreferenceForm());
    }

    @And("^I enter first name in preference form$")
    public void enterFirstNameInPreferenceForm() {
        Assert.assertTrue(profileEditPage.enterFirstNameInPreferenceForm());
    }

    @And("^I enter last name in preference form$")
    public void enterLastNameInPreferenceForm() {
        Assert.assertTrue(profileEditPage.enterLastNameInPreferenceForm());
    }

    @And("^I check the checkbox for offers from shark ninja in preference form$")
    public void checkOffersInPreferenceForm() {
        Assert.assertTrue(profileEditPage.checkOffersInPreferenceForm());
    }

    @And("^I check the checkbox for cleaning tips and articles from shark in preference form$")
    public void checkTipsAndArticlesInPreferenceForm() {
        Assert.assertTrue(profileEditPage.checkTipsAndArticlesInPreferenceForm());
    }

    @And("^I check the checkbox for recipes and inspiration from ninja in preference form$")
    public void checkRecipeAndInspirationInPreferenceForm() {
        Assert.assertTrue(profileEditPage.checkRecipeAndInspirationInPreferenceForm());
    }

    @And("^I check the checkbox for competition and whats new at shark in preference form$")
    public void checkCompetitionsSharkInPreferenceForm() {
        Assert.assertTrue(profileEditPage.checkCompetitionsSharkInPreferenceForm());
    }

    @And("^I check the checkbox for competition and whats new at ninja in preference form$")
    public void checkCompetitionsNinjaInPreferenceForm() {
        Assert.assertTrue(profileEditPage.checkCompetitionsNinjaInPreferenceForm());
    }

    @And("^I check the checkbox for black friday shark offers in preference form$")
    public void checkBlackFridaySharkInPreferenceForm() {
        Assert.assertTrue(profileEditPage.checkBlackFridaySharkInPreferenceForm());
    }

    @Then("^I Should get email or password is incorrect error message$")
    public void verifyInValidEmailErrorMessage() throws InterruptedException {
        String expected=Props.getProp("valid.email.password.message").trim();
        String actual=emailEditPage.verifyErrorMessage();
        Assert.assertEquals(actual, expected);
    }

    @Then("^I Should get incorrect validation error message for email or password$")
    public void verifyInValidEmailErrorMessg() throws InterruptedException {
        String expected=Props.getProp("valid.email.password.validation.message").trim();
        String actual=emailEditPage.verifyErrorMessage();
        Assert.assertEquals(actual, expected);
    }

    @And("^I check the checkbox for black friday ninja offers in preference form$")
    public void checkBlackFridayNinjaInPreferenceForm() {
        Assert.assertTrue(profileEditPage.checkBlackFridayNinjaInPreferenceForm());
    }

    @And("^I uncheck the checkbox for offers from shark ninja in preference form$")
    public void uncheckOffersInPreferenceForm() {
        Assert.assertTrue(profileEditPage.uncheckOffersInPreferenceForm());
    }

    @And("^I uncheck the checkbox for cleaning tips and articles from shark in preference form$")
    public void uncheckTipsAndArticlesInPreferenceForm() {
        Assert.assertTrue(profileEditPage.uncheckTipsAndArticlesInPreferenceForm());
    }

    @And("^I uncheck the checkbox for recipes and inspiration from ninja in preference form$")
    public void uncheckRecipeAndInspirationInPreferenceForm() {
        Assert.assertTrue(profileEditPage.uncheckRecipeAndInspirationInPreferenceForm());
    }

    @And("^I uncheck the checkbox for competition and whats new at shark in preference form$")
    public void uncheckCompetitionsSharkInPreferenceForm() {
        Assert.assertTrue(profileEditPage.uncheckCompetitionsSharkInPreferenceForm());
    }

    @And("^I uncheck the checkbox for competition and whats new at ninja in preference form$")
    public void uncheckCompetitionsNinjaInPreferenceForm() {
        Assert.assertTrue(profileEditPage.uncheckCompetitionsNinjaInPreferenceForm());
    }

    @And("^I uncheck the checkbox for black friday shark offers in preference form$")
    public void uncheckBlackFridaySharkInPreferenceForm() {
        Assert.assertTrue(profileEditPage.uncheckBlackFridaySharkInPreferenceForm());
    }

    @And("^I uncheck the checkbox for black friday ninja offers in preference form$")
    public void uncheckBlackFridayNinjaInPreferenceForm() {
        Assert.assertTrue(profileEditPage.uncheckBlackFridayNinjaInPreferenceForm());
    }

    @And("^Click on save preferences$")
    public void clickSavePreferences() {
        Assert.assertTrue(profileEditPage.clickSavePreferences());
    }

    @Then("^preferences should be saved$")
    public void verifyPreferenceUpdateSuccessMsg() {
        Assert.assertTrue(profileEditPage.verifyPreferenceUpdateSuccessMsg());
    }

    @And("^all preferences shown checked$")
    public void verifyAllPreferencesChecked() {
        Assert.assertTrue(profileEditPage.verifyAllPreferencesChecked());
    }

    @And("^all preferences shown unchecked$")
    public void verifyAllPreferencesUnchecked() {
        Assert.assertFalse(profileEditPage.verifyAllPreferencesUnchecked());
    }
}
