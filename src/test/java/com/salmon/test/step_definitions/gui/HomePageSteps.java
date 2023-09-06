package com.salmon.test.step_definitions.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import com.salmon.test.page_objects.gui.HomePage;
import com.salmon.test.page_objects.gui.CartPage;
import com.salmon.test.page_objects.gui.RegisterMyGuaranteePage;
import com.salmon.test.page_objects.gui.CustomerCarePage;
import com.salmon.test.page_objects.gui.TipsAndAdvicePage;
import com.salmon.test.page_objects.gui.PrivacyNoticePage;
import com.salmon.test.page_objects.gui.OffersPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lombok.AllArgsConstructor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

@AllArgsConstructor
public class HomePageSteps extends PageObject {

    protected static final Logger LOG = LoggerFactory.getLogger(HomePageSteps.class);

    private HomePage homePage;
    private CartPage cartPage;
    private CustomerCarePage customerCarePage;
    private TipsAndAdvicePage tipsAndAdvicePage;
    private PrivacyNoticePage privacyNoticePage;
    private OffersPage offersPage;

    public HomePageSteps() {
        this.homePage = new HomePage();

    }


    //Rita Singh for Forgot Password functionality
    @When("^I click on Forgot your password link$")
    public void i_click_on_Forgot_Password_link() {
        homePage.clickForgotPasswordLink();
    }


    @Given("^I click on register link$")
    public void i_click_on_register_link() throws InterruptedException {
        homePage.clickRegisterLink();

    }

    @When("^I click on the cart icon$")
    public void click_on_the_cart_icon() {
        homePage = homePage.clickOnCartIconForUK();
        //Assert.assertTrue(HomePageSteps != null);
    }

    @Then("^verify whether the Cart is containing Products or Not$")
    public void verify_whether_the_Cart_is_containing_Products_Or_Not() throws InterruptedException {
        IsPageLoaded.waitAllRequest();
        homePage.YourCartIsEmptyText();
//        String actualString = homePage.YourCartIsEmptyText();
//        String expectedString = Props.getMessage("your.cart.is.empty.text");
//        Assert.assertTrue(actualString.contains(expectedString), "Actual-->" + actualString + " Expected-->" + expectedString);
    }

//    @Then("^I decrease the quantity of product to \"([^\"]*)\" for the product shown at row number for UK (\\d+)$")
//    public void decrease_quantity_of_product_shown_at_location(String quantity, int rowNumber) {
//        Assert.assertTrue(cartPage.setQuantityOfProduct(quantity, rowNumber));
//    }
    @When("^I click on sign in link$")
    public void i_click_on_sign_in_link() {
        homePage.clickSignInLink();
    }

    @When("^I click on Rate And Review Button$")
    public void i_click_on_review_button() {
        homePage.clickRateAndReviewButton();
    }

    @And("^I click on My Account button on home page$")
    public void clickOnMyAccountButtonOnHomePage() {
        homePage.clickOnMyAccountButton();
    }


//User Registration Functionality

    @When("^I click on create account link$")
    public void I_click_on_create_account_link() {
        homePage.clickCreateLink();
    }

    //Rita Singh Logout Functionality
    @When("^I click on logout button$")
    public void i_click_on_logout_button() {
        homePage.clickLogoutButton();
    }

    @Then("^I am successfully logout$")
    public void i_am_successfully_logged_out() {
        Assert.assertTrue(homePage.verifyLogout());
    }
    //Returning User functionality-Rita Singh

    @Then("^I should see account is not confirmed yet message$")
    public void Verify_Account_not_Confirmed_message() {
        Assert.assertEquals(homePage.verifyAccountNotConfirmedMessage(), Props.getProp("account.not.confirmed.message"));
    }

    @Then("^I should see rating of a product at plp level \"([^\"]*)\"$")
    public void Verify_rating_at_plp_level(String productSpecificSKU) {
        String productSKU = Props.getProp(productSpecificSKU);
        homePage.verifyRatingAtPlpLevel(productSKU);
    }

    @Then("^I should see the link to resend the verification email$")
    public void Verify_resend_Verification_link() {

        Assert.assertTrue((homePage.isElementPresent(homePage.VerifyResendVerificationLink())));
    }

    @When("^I click on resend email link$")
    public void i_click_on_resend_email_link() {
        homePage.clickResendEmailLink();
    }

    @Then("^I should get verification email sent message$")
    public void Verify_Verification_Email_Sent_message() {
        Assert.assertTrue(homePage.VerifyVerificationEmailSentMessage());

    }

    @When("^I click on register my guarantee link$")
    public void i_click_on_registerMyGuarantee_link() {
        homePage.clickRegisterMyGuaranteeLink();
    }

    @Then("^Go back to home page$")
    public void goBackToHomePage() throws InterruptedException {
        homePage.clickBrowserBackButton();
        Thread.sleep(3000);
    }

    @When("^I click on customer care link$")
    public void i_click_on_customerCare_link() {
        homePage.clickCustomerCareLink();
        IsPageLoaded.waitAllRequest();
    }

    @Then("^I should be on customer care page$")
    public void verifyCustomerCarePage() throws Exception {
        switchBetweenWindowTabs(1);
        IsPageLoaded.waitAllRequest();
        String expected = Props.getProp("customer.care.introTitle");
        String actual = customerCarePage.getCustomerCareIntroTitle();
        Assert.assertTrue(actual.contains(expected), "Actual=" + actual + " Expected=" + expected);
        switchBetweenWindowTabs(0);
    }

    @When("^I click on tipsAndAdvice link$")
    public void i_click_on_tipsAndAdvice_link() {
        homePage.clickTipsAndAdviceLink();
    }

    @Then("^I should be on tips and advice page$")
    public void verifyTipsAndAdvicePage() throws InterruptedException {
//        Assert.assertTrue(tipsAndAdvicePage.isElementPresent(tipsAndAdvicePage.getVacuumTipsFilter()));
        if (webDriver.getCurrentUrl().contains("de")) {
            Assert.assertTrue(tipsAndAdvicePage.isElementPresent(tipsAndAdvicePage.getShowMeText()));
            Assert.assertTrue(tipsAndAdvicePage.isElementPresent(tipsAndAdvicePage.getShowAllFilter()));
            Assert.assertTrue(tipsAndAdvicePage.isElementPresent(tipsAndAdvicePage.getGeneralCleaningFilter()));
            Assert.assertTrue(tipsAndAdvicePage.isElementPresent(tipsAndAdvicePage.getLifeHacksFilter()));
            Assert.assertTrue(tipsAndAdvicePage.isElementPresent(tipsAndAdvicePage.getTipsAndAdviceFilter()));
        } else {
            Assert.assertTrue(getWebDriver().getCurrentUrl().contains(Props.getProp("tips.advice.page")));
            webDriver.navigate().back();
        }
        Thread.sleep(3000);
    }

    @When("^I click on Offers link$")
    public void i_click_on_Offers_link() {
        homePage.clickOffersLink();
    }

    @Then("^I should be on Offers page$")
    public void verifyOffersPage() throws InterruptedException {
//        Assert.assertTrue(offersPage.getTeaserText().contains(Props.getProp("offerpage.teaser.text")));
        Assert.assertTrue(wait.until(ExpectedConditions.urlContains(Props.getProp("offerpage.url.contains"))));
        Thread.sleep(3000);
    }

    @When("^I click on cart link$")
    public void i_click_on_cart_link() {
        homePage.clickCartLink();
    }

    @Then("^I should see empty cart message$")
    public void verifyCartMessage() {
        if (UrlBuilder.isDesktop()) {
            Assert.assertTrue(homePage.getEmptyCartMessage().contentEquals(Props.getProp("minicart.message.popup")));
        } else if (UrlBuilder.isMobile() || UrlBuilder.isTablet()) {
            String expectedResult=Props.getProp("minicart.message.popup");
            String expectedResultalternate=Props.getProp("minicart.message.popup.alternate");
            String actualResult=cartPage.getEmptyCartMessage();
            boolean flag=false;
            if(actualResult.equalsIgnoreCase(expectedResult))
                flag=true;
            else if(actualResult.equalsIgnoreCase(expectedResultalternate))
                flag=true;
            Assert.assertTrue(flag,"Actual Result is "+actualResult+" Expected Result is "+ expectedResult +" OR " + expectedResultalternate);
        }
    }
;
    @Then("^I should see text Rating Snapshot$")
    public void verifyReviewText() {
        if (UrlBuilder.isDesktop()) {
            Assert.assertTrue(homePage.getReviewText().contentEquals(Props.getProp("review.text")));
        } else if (UrlBuilder.isMobile() || UrlBuilder.isTablet()) {
            String expectedResult=Props.getProp("minicart.message.popup");
            String expectedResultalternate=Props.getProp("minicart.message.popup.alternate");
            String actualResult=cartPage.getEmptyCartMessage();
            boolean flag=false;
            if(actualResult.equalsIgnoreCase(expectedResult))
                flag=true;
            else if(actualResult.equalsIgnoreCase(expectedResultalternate))
                flag=true;
            Assert.assertTrue(flag,"Actual Result is "+actualResult+" Expected Result is "+ expectedResult +" OR " + expectedResultalternate);
        }
    }
    @Then("^I should see Shark clean text in footer$")
    public void verifySharkCleanMessage() throws InterruptedException {
        Assert.assertTrue(homePage.getSharkCleanText().contentEquals("About Shark® Clean"));
        Thread.sleep(3000);
    }

    @Then("^I should see \"([^\"]*)\" link in footer$")
    public void verifyFooterLink(String link) throws InterruptedException {
        Assert.assertTrue(homePage.validateFooterLink(link));
        Thread.sleep(3000);
    }

    @Then("^I should see About Us link in footer$")
    public void verifyAboutUsLink() throws InterruptedException {
        Assert.assertTrue((homePage.isElementPresent(homePage.getAboutSharkLink())));
        Thread.sleep(3000);
    }

    @Then("^I should see Reviews link in footer$")
    public void verifyReviewsFooterLink() throws InterruptedException {
        Assert.assertTrue((homePage.isElementPresent(homePage.getTipsAndAdviceFooterLink())));
        Thread.sleep(3000);
    }

    @Then("^I should see Cleaning Tips & Advice link in footer$")
    public void verifyCleaningTipsAndAdviceLink() throws InterruptedException {
        Assert.assertTrue((homePage.isElementPresent(homePage.getCustomerCareFooterLink())));
        Thread.sleep(3000);
    }

    @Then("^I should see Terms and Conditions link in footer$")
    public void verifyTermsAndConditionsLink() throws InterruptedException {
        Assert.assertTrue((homePage.isElementPresent(homePage.getTermsAndPoliciesLink())));
        Thread.sleep(3000);
    }

    @Then("^I should see Privacy Notice link in footer$")
    public void verifyPrivacyNoticeLink() throws InterruptedException {
        Assert.assertTrue((homePage.isElementPresent(homePage.getPrivacyNoticeLink())));
        Thread.sleep(3000);
    }

    @Then("^I should see Uk Modern Slavery Statement link in footer$")
    public void verifyUkModernSlaveryStatementLink() throws InterruptedException {
        Assert.assertTrue((homePage.isElementPresent(homePage.getUkModernSlaveryStatementLink())));
        Thread.sleep(3000);
    }

    @Then("^I should see Gender Pay Gap Link in footer$")
    public void verifyGenderPayGapLink() throws InterruptedException {
        Assert.assertTrue((homePage.isElementPresent(homePage.getOnlineDisputeResolutionLink())));
        Thread.sleep(3000);
    }

    @Then("^I should see Consumer Rights link in footer$")
    public void verifyConsumerRightsLink() throws InterruptedException {
        Assert.assertTrue((homePage.isElementPresent(homePage.getIndependentBazaarVoiceReviewsLink())));
        Thread.sleep(3000);
    }

    @Then("^I should see Vulnerability Disclosure Policy link in footer$")
    public void verifyVulnerabilityDisclosurePolicyLink() throws InterruptedException {
        Assert.assertTrue((homePage.isElementPresent(homePage.getSharkNinjaWarrantyFairProcessingNoticeLink())));
        Thread.sleep(3000);
    }

    @Then("^I should see Shark Ninja Checkout Fair Processing Notice link in footer$")
    public void verifySharkNinjaCheckoutFairProcessingNoticeLink() throws InterruptedException {
        Assert.assertTrue((homePage.isElementPresent(homePage.getSharkNinjaCheckoutFairProcessingNoticeLink())));
        Thread.sleep(3000);
    }

    @Then("^I should see Shark Ninja Careers link in footer$")
    public void verifySharkNinjaCareersLink() throws InterruptedException {
        Assert.assertTrue((homePage.isElementPresent(homePage.getSharkNinjaCareersLink())));
        Thread.sleep(3000);
    }

    @Then("^I should see Register My Guarantee link in footer$")
    public void verifyRegisterMyGuaranteeLinkInFooter() throws InterruptedException {
        Assert.assertTrue((homePage.isElementPresent(homePage.getRegisterMyGuaranteeLinkFooterLink())));
        Thread.sleep(3000);
    }

    @Then("^I should see Log In link in footer$")
    public void verifyLogInLinkInFooter() throws InterruptedException {
        Assert.assertTrue((homePage.isElementPresent(homePage.getLogInLinkFooterLink())));
        Thread.sleep(3000);
    }

    @Then("^I should see Facebook Icon in footer$")
    public void verifyFacebookIconInFooter() throws InterruptedException {
        Assert.assertTrue((homePage.isElementPresent(homePage.getFacebookIconFooter())));
        Thread.sleep(3000);
    }

    @Then("^I should see Twitter Icon in footer$")
    public void verifyTwitterIconInFooter() throws InterruptedException {
        Assert.assertTrue((homePage.isElementPresent(homePage.getTwitterIconFooter())));
        Thread.sleep(3000);
    }

    @Then("^I should see Instagram Icon in footer$")
    public void verifyInstagramIconInFooter() throws InterruptedException {
        Assert.assertTrue((homePage.isElementPresent(homePage.getInstagramIconFooter())));
    }

    @Then("^I should see Pinterest Icon in footer$")
    public void verifyPinterestIconInFooter() throws InterruptedException {
        Assert.assertTrue((homePage.isElementPresent(homePage.getPinterestIconFooter())));
    }

    @Then("^I should see Youtube Icon in footer$")
    public void verifyYoutubeIconInFooter() throws InterruptedException {
        Assert.assertTrue((homePage.isElementPresent(homePage.getYoutubeIconFooter())));
    }

    @Then("^I should see Newsletter Email text box in footer$")
    public void verifyNewletterEmailTxtBoxInFooter() {
        Assert.assertTrue((homePage.isElementPresent(homePage.getNewsletterEmailTxtBoxInFooter())));
    }

    //Rita Singh ,29-01-2021,News Letter
    @Then("^I entered email \"([^\"]*)\" in email textbox$")
    public void ClickEmailTxtBoxInFooter(String MailingEmail) throws InterruptedException {
        //webDriver.navigate().refresh();
        String mailing_email = Props.getProp(MailingEmail);
        homePage.ClickEmailTxtBoxInFooter(mailing_email);
    }

    @Then("^I entered valid email \"([^\"]*)\" in email textbox on unsubscribe form$")
    public void EnterEmailForUnsubscription(String UnsubscribeEmail) throws InterruptedException {
        String unsubscribe_email = Props.getProp(UnsubscribeEmail);
        homePage.enterEmailforUnsubscription(unsubscribe_email);
    }

    @Then("^I checked or unchecked offerscheckbox base on \"([^\"]*)\"$")
    public void ValueforOffersCheckBox(String checkboxvalue) throws InterruptedException {
        String Checkbox_Value = Props.getProp(checkboxvalue);
        homePage.OffersCheckboxValue(Checkbox_Value);
    }

    //Rita Singh ,29-01-2021,News Letter
    @Then("^I click on submit button in footer$")
    public void ClickonSubmitButton() {
        homePage.ClickonSubmitButton();
    }

    @Then("^I should get valid email required error message$")
    public void verifyValidEmailErrorMessage() throws InterruptedException {
        Assert.assertTrue((homePage.verifyErrorClientMessage()));
    }

    @Then("^I should get Please enter a valid e-mail address error message for login$")
    public void verifyValidEmailErrorMessageforlogin() throws InterruptedException {
        Assert.assertTrue((homePage.verifyErrorMessageforLogin()));
    }

    @Then("^I should get your email or password is incorrect error message$")
    public void verifyInValidEmailErrorMessage() throws InterruptedException {
        String expected=Props.getProp("valid.email.required_forLogin").trim();
        String actual=homePage.verifyErrorMessage();
        Assert.assertTrue(actual.contains(expected));
    }

    @Then("^I should get Please enter a valid e-mail address error message$")
    public void verifyEnterValidEmailErrorMessage() throws InterruptedException {
        Assert.assertTrue((homePage.verifyErrorMessageServer()));
    }

    @Then("^I click on click here link$")
    public void ClickonClickHereLink() throws InterruptedException {
        homePage.ClickonClickHereLink();
    }

    @Then("^I click on submit button on form$")
    public void ClickonSubmitButtonforUnsubscription() throws InterruptedException {
        homePage.ClickonSubmitButtonforUnsubscription();
        Thread.sleep(3000);
    }

    @Then("^I should see click here link for unsubscribe on iframe$")
    public void VerifyClickHereLinkonUnsubscriptionForm() {
        IsPageLoaded.waitAllRequest();
        Assert.assertTrue((homePage.isElementPresent(homePage.getClickHereLink())));
    }

    @Then("^I click on unsubscribe link$")
    public void ClickonUnsubscribeLink() throws InterruptedException {
        homePage.ClickonUnsubscribeLink();
    }

    @Then("^I should be successfully subscribed$")
    public void VerifySuccessfulSubscription() throws InterruptedException {
        Assert.assertTrue(homePage.VerifySuccessfulSubscription());
    }

    @Then("^I should be successfully unsubscribed$")
    public void VerifySuccessfulUnSubscription() throws InterruptedException {
        Assert.assertTrue(homePage.VerifySuccessfulUnSubscription());
    }


    @Then("^I should see offers checkbox in footer$")
    public void verifyOffersCheckboxInFooter() {
        Assert.assertTrue((homePage.isElementPresent(homePage.getOffersCheckBoxInFooter())));
    }

    @Then("^I should see newsletter submit button in footer$")
    public void verifyNewsletterSubmitBtnInFooter() {
        Assert.assertTrue((homePage.isElementPresent(homePage.getSubmitBtnInFooter())));
    }

    @Then("^I should see unsubscribe link in footer$")
    public void verifyUnsubscribeLinkInFooter() {
        Assert.assertTrue((homePage.isElementPresent(homePage.getUnsubscribeLinkInFooter())));
    }
//	
//	@Then("^I should see Tips and advice link in footer$")
//	public void verifyTipsAndAdviceLink() throws InterruptedException {
//		Assert.assertTrue(homePage.getTipsAndAdviceFooterLink().linkText("abc"));
////		Assert.assertTrue(waitForExpectedElement(loginFlyInTitle).getText().trim().contains(flyInTitle));
//		Thread.sleep(3000);
//	}

    @When("^I click about shark link in footer$")
    public void clickAboutSharkLink() {
        homePage.clickAboutSharkLink();
    }

    @Then("^I should be on about shark page$")
    public void verifyAboutSharkPage() throws InterruptedException {
        Assert.assertTrue(homePage.checkPageTitle(Props.getMessage("aboutUs.page")));
        Thread.sleep(3000);
    }

    @When("^I click tips and advice link in footer$")
    public void clickTipsAndAdviceFooterLink() {
        homePage.clickTipsAndAdviceFooterLink();
    }

    @Then("^I should be on tips and advice page in new tab$")
    public void verifyTipsAndAdviceInNewTab() throws InterruptedException {

        try {
            tipsAndAdvicePage.switchBetweenWindowTabs(1);
            Assert.assertTrue(tipsAndAdvicePage.getCurrentUrl().contains("https://cleaning-hacks.sharkclean.co.uk/"));
            Assert.assertTrue(tipsAndAdvicePage.checkPageTitle("Shark Cleaning Hacks -"));
            tipsAndAdvicePage.switchBetweenWindowTabs(0);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Thread.sleep(3000);
    }

    @When("^I click customer care link in footer$")
    public void clickCustomerCareFooterLink() {
        homePage.clickCustomerCareFooterLink();
    }

    @Then("^I should be on customer care footer page$")
    public void verifyCustomerCareFooterPage() throws InterruptedException {
        Assert.assertTrue(customerCarePage.getCurrentUrl().contains("https://support.sharkclean.co.uk/hc/en-gb"));
        //Assert.assertTrue(customerCarePage.getCustomerCareIntroTitle().contentEquals("We're here to help."));
        Thread.sleep(3000);
    }

    @When("^I click terms and policies link in footer$")
    public void clickTermsAndPoliciesLink() {
        homePage.clickTermsAndPoliciesLink();
    }

    @Then("^I should be on Terms and Policies page$")
    public void verifyTermsAndPoliciesPage() throws InterruptedException {
        Assert.assertEquals(homePage.getTextForTermsAndCondition(), "Terms and Policies");
        Thread.sleep(3000);
    }

    @When("^I click privacy notice link in footer$")
    public void clickPrivacyNoticeLink() {
        homePage.clickPrivacyNoticeLink();
    }

    @Then("^I should be on Privacy Notice page$")
    public void verifyPrivacyNoticePage() throws InterruptedException {
        Assert.assertTrue(privacyNoticePage.getHeading().contentEquals("Privacy Notice"));
        Thread.sleep(3000);
    }

    @When("^I click UK Modern Slavery Statement link in footer$")
    public void clickUkModernSlaveryStatementLink() {
        homePage.clickUkModernSlaveryStatementLink();
    }

    @When("^I click online dispute resolution Link in footer$")
    public void clickOnlineDisputeResolutionLink() {
        homePage.clickOnlineDisputeResolutionLink();
    }

    @When("^I click independent bazaar voice reviews link in footer$")
    public void clickIndependentBazaarVoiceReviewsLink() {
        homePage.clickIndependentBazaarVoiceReviewsLink();
    }

    @When("^I click Shark ninja warranty fair processing notice link in footer$")
    public void clickSharkNinjaWarrantyFairProcessingNoticeLink() {
        homePage.clickSharkNinjaWarrantyFairProcessingNoticeLink();
    }

    @When("^I click Shark ninja checkout fair processing notice link in footer$")
    public void clickSharkNinjaCheckoutFairProcessingNoticeLink() {
        homePage.clickSharkNinjaCheckoutFairProcessingNoticeLink();
    }

    @When("^I click shark ninja careers link in footer$")
    public void clickSharkNinjaCareersLink() {
        homePage.clickSharkNinjaCareersLink();
    }

    @When("^I click Register My Guarantee link in footer$")
    public void clickRegisterMyGuaranteeLinkFooter() {
        homePage.clickRegisterMyGuaranteeLinkFooter();
    }


    @When("^I click Log In link in footer$")
    public void clickLogInLinkFooter() {
        homePage.clickLogInLinkFooter();
    }
}