package com.salmon.test.step_definitions.gui.SNAP;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.utils.RandomGenerator;
import com.salmon.test.page_objects.gui.SNAP.*;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.testng.Assert;

public class ProductRegistrationPageSteps
{
    private SNAPNewProductRegistrationPage newProductRegistrationPage;
    private ZendeskAgentDashboardPage zendeskAgentDashboardPage;
    private SNAPProductRegistrationPage snapProductRegistrationPage;
    private CreateReplacementForProductPage createReplacementForProductPage;
    private ShoppingCartPage shoppingCartPage;
    private AgentProfilePage agentProfilePage;
    private CheckoutProductPage checkoutProductPage;
    private String modelName;

    public ProductRegistrationPageSteps()
    {
        newProductRegistrationPage=new SNAPNewProductRegistrationPage();
        zendeskAgentDashboardPage=new ZendeskAgentDashboardPage();
        agentProfilePage=new AgentProfilePage();
        shoppingCartPage = new ShoppingCartPage();
        createReplacementForProductPage = new CreateReplacementForProductPage();
        checkoutProductPage = new CheckoutProductPage();
    }

    @And("^Enter product model number as \"([^\"]*)\" and select that from drop down$")
    public void enterProductModelNumber(String model)
    {
      Assert.assertTrue(newProductRegistrationPage.setProductModelNumber(Props.getProp(model)));
    }

    @And("^Enter purchase date as \"([^\"]*)\"$")
    public void setPurchaseDate(String date)
    {
        Assert.assertTrue(newProductRegistrationPage.setPurchaseDate(Props.getProp(date)));
    }

    @And("^Select purchase location as \"([^\"]*)\"$")
    public void setPurchaseLocation(String location){
        Assert.assertTrue(newProductRegistrationPage.setPurchaseLocation(Props.getProp(location)));
    }

    @Given("^Capture the product model name$")
    public void getProductModelName()
    {
        modelName=newProductRegistrationPage.getProducModelName();

        Assert.assertTrue(modelName!="" && modelName!=null,"Unable to fetch product model name");
    }

    @Then("^Clicks on create button product registration should be done$")
    public void clicksOnCreateButtonThenRegistrationShouldGetDoneSuccessfully()
    {
        Assert.assertTrue(newProductRegistrationPage.clickOnCreateButton().
                getTextRegistrationAddedSuccessfully().equalsIgnoreCase("Registration created successfully"),"Unable to find text \"Registration created successfully\"");
    }

    @Then("^Clicks on create button product registration should be done for CA$")
    public void clicksOnCreateButton()
    {
        Assert.assertTrue(newProductRegistrationPage.clickOnCreateButton().
                getTextRegistrationAddedSuccessfully().equalsIgnoreCase("Product warranty registered successfully."),"Unable to find text \"Registration created successfully\"");
    }

    @Then("^User clicks on create product registration button$")
    public void clickOnCreateProductRegistration()
    {
       Assert.assertTrue(zendeskAgentDashboardPage.clicksOnCreateProductRegistrationButton());
    }

    @And("^set email address$")
    public void setEmailAddress()
    {
        Assert.assertTrue(newProductRegistrationPage.setEmailAddress(RandomGenerator.randomEmailAddress(6)));
    }

    @And("^set first name as \"([^\"]*)\"$")
    public void setFirstName(String firstName)
    {
        Assert.assertTrue(newProductRegistrationPage.setFirstName(Props.getProp(firstName)));
    }

    @And("^set first name as Random name$")
    public void setFirstNameRandomly()
    {
        Assert.assertTrue(newProductRegistrationPage.setFirstName());
    }

    @And("^set last name as \"([^\"]*)\"$")
    public void setLastName(String lastName)
    {
        Assert.assertTrue(newProductRegistrationPage.setLastName(Props.getProp(lastName)));
    }

    @And("^select country as \"([^\"]*)\"$")
    public void selectCountry(String country)
    {
        Assert.assertTrue(newProductRegistrationPage.setCountry(Props.getProp(country)));
    }

    @And("^set postal code as \"([^\"]*)\" and select first address from dropdown$")
    public void setPostalCode(String postalCode)
    {
        Assert.assertTrue(newProductRegistrationPage.setPostalCode(Props.getProp(postalCode)));
    }

    @And("^I Clicks on Products Registrations$")
    public void clicksOnProductRegistrations()
    {
        snapProductRegistrationPage=agentProfilePage.clickProductsRegistrationsLink();
        Assert.assertTrue(snapProductRegistrationPage!=null,"Unable to click on Product Registration Link " +
                "on agent profile page please check");
    }


    @Then("^I click on order option button at postion (\\d+)$")
    public void selectOrderOptionButtonAtPosition(int position)
    {
        Assert.assertTrue(snapProductRegistrationPage.clickOnOrderOptionsButtonPositionAt(position),
                "Unable to click on Order option button positioned at "+position);
    }

    @And("^I select the option \"(.*)\" from the dropdown$")
    public void selectOptionFromDropDownOrderOption(String option)
    {
        createReplacementForProductPage=snapProductRegistrationPage.selectOptionShownUnderOrderOptionButton(option);
        Assert.assertTrue(createReplacementForProductPage!=null,"Unable to select option as "+option
                +" from drop down order option");
    }

    @And("^I select shark products list for canada$")
    public void selectSharkProductsForCanada(){
        Assert.assertTrue(createReplacementForProductPage.selectSharkProductsForCanada());
    }

    @And("^I select shark products$")
    public void i_select_shark_products(){
        createReplacementForProductPage.selectSharkProducts();
    }

    @And("^I select first item shown under Handheld vaccums$")
    public void selectProductOfHandheldVaccum(){
        Assert.assertTrue(createReplacementForProductPage.selectProductOfHandheldVaccum());
    }

    @Then("^I select proof of purchase as \"Pending\"$")
    public void selectProofOfPurchaseAsPending()
    {
        Assert.assertTrue(createReplacementForProductPage.selectProofOfPurchaseAsPending(),"Unable to select proof of purchase as \"Pending\"");
    }
    @And("^I select valid fault reason$")
    public void selectFaultReason(){
        Assert.assertTrue(createReplacementForProductPage.selectFaultReason(),"Unable to select fault reason");
    }

    @And("^I click on create button$")
    public void clickOnCreateButton()
    {
        Assert.assertTrue(createReplacementForProductPage.clickCreate(),
                "Unable to click on create button please check logs");
    }

    @Then("^I should click on continue button when pop-up arise$")
    public void i_should_click_on_continue_button_if_cart_is_not_empty(){
        zendeskAgentDashboardPage.clickOnPopUpContinueButtonForEUChannel();
    }


    @Then("^I select first item shown under replacement$")
    public void selectFirstItemShownUnderReplacement()
    {
        Assert.assertTrue(createReplacementForProductPage.selectFirstItemShownUnderReplacement(),
                "Unable to select first item shown under Replacement heading please check there might be no order to select");
    }

    @Then("^I select replacement product option$")
    public void selectReplacementProductOption()
    {
        Assert.assertTrue(createReplacementForProductPage.selectReplacementOption(),
                "Unable to select first item shown under Replacement heading please check there might be no order to select");
    }

    @Then("^I should click on Create button given at below$")
    public void i_should_click_on_Create_button_given_at_below() {
        createReplacementForProductPage.clickOnBelowCreateButton();
    }

    @Then("^I click add to cart button to add the item$")
    public void clickOnAddToCart()
    {
        Assert.assertTrue(createReplacementForProductPage.clickOnAddToCartButton(),
                "Unable to clicked on add to cart button");
    }

    @Then("^I click add to cart button to add the item at position (\\d+)$")
    public void clickOnAddToCartAtFirstPosition(int position)
    {
        Assert.assertTrue(createReplacementForProductPage.clickOnAddToCartAtFirstPosition(position));
    }

    @And("^I click on cart button$")
    public void clickOnCartButton()
    {
        shoppingCartPage=agentProfilePage.clickOnCartBasket();
        Assert.assertTrue(shoppingCartPage!=null,"Unable to click on cart button and unable to move to checkout page");
    }


    @Then("^Same item should be shown in cart and price should be zero$")
    public void validateItemNameAndPrice()
    {
     Assert.assertTrue(shoppingCartPage.getNameOfTheDisplayedProduct());
     agentProfilePage.returnNumberOfItemsShownAboveCartButton();

     float price=shoppingCartPage.getPriceOfTheDisplayedProduct();
        Assert.assertTrue(price==0||price==0.0,"Price is not showing zero on checkout page for order replacement part");
    }

    @And("^I click on checkout button$")
    public void clickOnCheckoutButton()
    {
        checkoutProductPage=shoppingCartPage.clicksOnCheckoutButton();
        Assert.assertTrue(checkoutProductPage!=null,"Unable to clicks on checkout button please check");
    }

    @And("^select payment method as \"Free of Charge\"$")
    public void setPayementMethodToFreeOfCharge()
    {
        Assert.assertTrue(checkoutProductPage.setPaymentMethodToFreeOfCharge(),"Unable to set payment method to Free Of Charge ");
    }

    @And("^select payment method as \"Pay with Credit Card\"$")
    public void setPaymentMethodToCreditCard()
    {
        Assert.assertTrue(checkoutProductPage.setPaymentMethodToCreditCard());
    }

    @And("^fill card holder name as \"(.*)\" card number as \"(.*)\" and validity as \"(.*)\" and cvc number as \"(.*)\" and postal code as \"(.*)\"$")
    public void completeCreditCardPayment(String cardHolderName, String cardNumber, String expirynumber, String cvvNumber, String postalCode) {
        Assert.assertTrue(checkoutProductPage.creditCardPayment(Props.getProp(cardHolderName), Props.getProp(cardNumber), Props.getProp(expirynumber), Props.getProp(cvvNumber), Props.getProp(postalCode)));
    }

    @And("^I click on place order button$")
    public void clickOnPlaceOrder()
    {
        Assert.assertTrue(checkoutProductPage.clickOnPlaceOrderButton(),"Unable to click on place order button please check");
    }

    @Then("^verify that order of replacement part done successfully$")
    public void validateOrderForReplacementPartDone()
    {
        Assert.assertTrue(checkoutProductPage.getAcknowledgementMessage().contains("The order is put On Hold until the Proof of Purchase is verified."),
                "Unable to place order for replacement part please check acknowledgement message is not there");
    }

    @Then("^verify that order is process through Express Replacement successfully$")
    public void validateOrderForExpressReplacementPartDone()
    {
        Assert.assertTrue(checkoutProductPage.getAcknowledgementMessage().contains("has been successfully placed"),
                "Unable to place order for  Express Replacement please check acknowledgement message is not there");
    }

    @And("^get the order id from successful order place page for SNAP CA$")
    public void fetch_order_id_from_secure_checkout_page()
    {
        String text=checkoutProductPage.getOrderIdAfterPlacingOrder();
        Assert.assertFalse(text.equalsIgnoreCase("not found"),"Actual Value Of OrderId is-->>"+text);
    }

    @And("^update order id against key \"(.*?)\" in config file for SNAP CA$")
    public void save_order_id_in_config_file_for(String key){
        String orderId=checkoutProductPage.getOrderIdAfterPlacingOrder();
        Assert.assertTrue(checkoutProductPage.saveOrderIdInConfigFile(key,orderId));
    }


    @Then("^I should click on continue button when pop-up arise for NA$")
    public void iShouldClickOnContinueButtonWhenPopUpAriseForNA() {
        Assert.assertTrue(zendeskAgentDashboardPage.clickOnPopUpContinueButton(),"Unable to click continue button please check");
    }
}
