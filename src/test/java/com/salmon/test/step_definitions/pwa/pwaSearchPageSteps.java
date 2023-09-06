package com.salmon.test.step_definitions.pwa;
import com.salmon.test.step_definitions.gui.SearchPageSteps;
import cucumber.api.java.en.And;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;

import com.salmon.test.page_objects.pwa.pwaSearchPage;

public class pwaSearchPageSteps extends PageObject{
    private static final Logger log = LoggerFactory.getLogger(SearchPageSteps.class);
    String product;
    private com.salmon.test.page_objects.pwa.pwaSearchPage pwaSearchPage;

    public pwaSearchPageSteps() {
        this.pwaSearchPage = new pwaSearchPage();

    }

    @And("^I click on search icon$")
    public void I_click_on_search_icon() {

        pwaSearchPage.clickonSearchButton();
    }
    @And("^I search for product \"([^\"]*)\"$")
    public void i_search_for_product(String searchTerm) throws InterruptedException {
        product = Props.getProp(searchTerm);
        System.out.println("product--"+product);
        pwaSearchPage.searchTxtbx().sendKeys(product);
        pwaSearchPage.searchButton().click();
    }
    @And("^I select product from search result page \"([^\"]*)\"$")
    public void i_select_product_from_search_result_page(String productSpecificSKU) throws InterruptedException {
        String productSKU = Props.getProp(productSpecificSKU);
        pwaSearchPage.selectProductFromSearchResults(productSKU);
    }

}
