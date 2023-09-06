package com.salmon.test.step_definitions.gui.SNAP;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import com.salmon.test.page_objects.gui.SNAP.*;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en_scouse.An;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.checkerframework.checker.units.qual.C;
import org.testng.Assert;

import static com.salmon.test.framework.helpers.WebDriverHelper.getWebDriver;

public class AgentPlacesOrderSteps
{
    AgentProfilePage agentProfilePage;
    SearchResultPage searchResultPage;
    ShoppingCartPage shoppingCartPage;
    CheckoutProductPage checkoutProductPage;
    ZendeskAgentDashboardPage zendeskAgentDashboardPage;

    public AgentPlacesOrderSteps()
    {
        agentProfilePage=new AgentProfilePage();
        checkoutProductPage = new CheckoutProductPage();
        zendeskAgentDashboardPage = new ZendeskAgentDashboardPage();
        shoppingCartPage = new ShoppingCartPage();

    }

    @And("^Enter the product \"(.*)\" in search bar and hit search button$")
    public void enterTheProductInSearchBar(String product)
    {
        searchResultPage=agentProfilePage.findItemThroughSearchBar(Props.getProp(product));
        Assert.assertTrue( searchResultPage!=null,"Unable to search the product with product id "+product);
    }

    @Then("^I place shark uk bulk order using credit card$")
    public void placeBulkOrder(){

        for(int i=2;i<100;i++)
        {
            getWebDriver().navigate().to(Props.getProp("site.uat.url"));
            Assert.assertTrue(zendeskAgentDashboardPage.addTicket().equalsIgnoreCase("New Ticket"),
                    "Unable to find text \"New Ticket\" after hovering on Add button and clicking on Ticket Option");
            Assert.assertTrue(zendeskAgentDashboardPage.openSNAPCustomerSearchAndSelectChannelAs(Props.getProp("zendesk.select.channel")),
                    "Unable to set channel as "+Props.getProp("zendesk.select.channel"));

            Assert.assertTrue(zendeskAgentDashboardPage.enterCustomerNumberAndClickSearch(Props.getProp("zendesk.customer.search.valid.customerId")),
                    "Unable to enter customer number and search");

            Assert.assertTrue(zendeskAgentDashboardPage.clickOnSelectButtonWhereEmailIdIs(Props.getProp("zendesk.customer.search.valid.emailId")));

            searchResultPage=agentProfilePage.findItemThroughSearchBar(Props.getProp("product.bulk.order"));
            Assert.assertTrue( searchResultPage!=null,"Unable to search the product with product id "+Props.getProp("product.bulk.order"));

            Assert.assertTrue(searchResultPage.clickOnAddToCartWhereProductIdIs(Props.getProp("product.bulk.order")),"Unable to add product into the cart as it might be unavailable");

            shoppingCartPage=agentProfilePage.clickOnCartBasket();
            Assert.assertTrue(shoppingCartPage!=null,"Unable to click on cart basket button please check");

            checkoutProductPage=shoppingCartPage.clicksOnCheckoutButton();
            Assert.assertTrue(checkoutProductPage!=null,"Unable to click on checkout button might be product is not added into the cart");

            Assert.assertTrue(checkoutProductPage.selectPayByCreditCard());

            Assert.assertTrue(checkoutProductPage.fillCreditCardDetails(Props.getProp("stripe.card.number"),Props.getProp("stripe.card.expiryDate"),Props.getProp("stripe.card.cvcNumber"), Props.getProp("strip.card.postalCode")));

            Assert.assertTrue(checkoutProductPage.clickOnPlaceOrderButton(),"Unable to clicked on place order button please check ");

            Assert.assertTrue(checkoutProductPage.getAcknowledgementMessage().contains(Props.getProp("order.success.message")),
                    "Either acknowledgement message is not present or some mismatch is there please check");

            System.out.println("Creating order:" + i );
        }
    }

    @Given("^Agent should be able to add product \"(.*)\" into the cart$")
    public void shouldBeAbleToAddProductIntoTheCartHavingProductId(String productId)
    {
        Assert.assertTrue(searchResultPage.clickOnAddToCartWhereProductIdIs(Props.getProp(productId)),"Unable to add product into the cart as it might be unavailable");
    }

    @Then("^Value of M should be displayed as zero$")
    public void verifyValueOfM()
    {
        Assert.assertTrue(shoppingCartPage.getValueOfM().contains(Props.getProp("back.order.value")));
    }

    @And("^click on cart button$")
    public void clickOnCartButton()
    {
        shoppingCartPage=agentProfilePage.clickOnCartBasket();
        Assert.assertTrue(shoppingCartPage!=null,"Unable to click on cart basket button please check");
    }

    @And("^i check the sales tax for product on cart page$")
    @Then("^i check te sales tax for product on checkout page$")
    public void verifySalesTaxOnCartPage()
    {
        String text = shoppingCartPage.getSalesTaxOnCartPage();
        Assert.assertEquals(text, "$26.52");
    }

    @And("^i check the sales tax for product on cart page for victoria city$")
    @Then("^i check te sales tax for product on checkout page for victoria city$")
    public void verifySalesTaxOnCartPageForVictoriaCity()
    {
        String text = shoppingCartPage.getSalesTaxOnCartPage();
        Assert.assertEquals(text, "$24.48");
    }

    @And("^click on checkout button$")
    public void clickOnCheckOutButton()
    {
        checkoutProductPage=shoppingCartPage.clicksOnCheckoutButton();
        Assert.assertTrue(checkoutProductPage!=null,"Unable to click on checkout button might be product is not added into the cart");
    }

    @And("i click on continue with checkout button")
    public void clickOnContinueWithCheckoutButton(){
        Assert.assertTrue(shoppingCartPage.clicksOnContinueWithCheckoutButton());
    }

    @And("i click on check box for email Id")
    public void clickOnCheckBoxForEmailId(){
        Assert.assertTrue(shoppingCartPage.tickOnNoEmailId());
    }

    @Then("verify shipping charge for order less than 75 should not be zero")
    public void verifyShippingCharge(){
        Assert.assertTrue(checkoutProductPage.checkShippingCharge());
    }

    @And("^select payment method as stripe$")
    public void selectPaymentMethodAsStripe()
    {
        Assert.assertTrue(checkoutProductPage.setPaymentMethodToStrip(),"Unable to select payment method as stripe");
    }

    @And("^fill card number as \"(.*)\" and validity as \"(.*)\" and cvc number as \"(.*)\" and postal code as \"(.*)\"$")
    public void completeCardDetails(String cardNumber,String validity,String cvc, String postalCode)
    {
        Assert.assertTrue(checkoutProductPage.fillCreditCardDetails(Props.getProp(cardNumber),Props.getProp(validity),Props.getProp(cvc), Props.getProp(postalCode)));
    }

    @And("^select payment method as credit card$")
    public void payByCreditCard()
    {
        Assert.assertTrue(checkoutProductPage.selectPayByCreditCard());
    }

    @And("^select payment method as credit card for DE$")
    public void payByCreditCardForDE()
    {
        Assert.assertTrue(checkoutProductPage.selectPayByCreditCardForDe());
    }

    @And("^select payment method as credit card for CA$")
    public void payByCreditCardForCA()
    {
        Assert.assertTrue(checkoutProductPage.selectPayByCreditCardForCa());
    }

    @Then("^click on place order button$")
    public void clickOnPlaceOrderButton()
    {
        Assert.assertTrue(checkoutProductPage.clickOnPlaceOrderButton(),"Unable to clicked on place order button please check ");
    }

    @Then("^order should be successfully placed$")
    public void validateAcknwMessage()
    {
        Assert.assertTrue(checkoutProductPage.getAcknowledgementMessage().contains(Props.getProp("order.success.message")),
                "Either acknowledgement message is not present or some mismatch is there please check");
    }

    @Then("^get the order id from successfully placed order$")
    public void getOrderId(){
        String text=checkoutProductPage.getOrderId();
        Assert.assertFalse(text.equalsIgnoreCase("not found"),"Actual Value Of OrderId is-->>"+text);

    }

    @Then("^update the value of \"(.*?)\" in property file$")
    public void updateOrderId(String key){
        String orderId=checkoutProductPage.getOrderId();
        Assert.assertTrue(checkoutProductPage.saveOrderIdInConfigFile(key,orderId));
    }

    @And("^select payment method as credit card for NA$")
    public void selectPaymentMethodAsCreditCardForNA() {

            Assert.assertTrue(checkoutProductPage.selectPayByCreditCardForNa());

    }
}
