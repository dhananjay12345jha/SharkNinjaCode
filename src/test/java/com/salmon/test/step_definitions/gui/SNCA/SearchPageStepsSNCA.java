package com.salmon.test.step_definitions.gui.SNCA;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.page_objects.gui.SNCA.HomePageSNCA;
import com.salmon.test.page_objects.gui.SNCA.SearchPageSNCA;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class SearchPageStepsSNCA extends PageObject {
    private SearchPageSNCA searchPageSNCA;
    private HomePageSNCA homePageSNCA;

    String product;
    public SearchPageStepsSNCA() {
        homePageSNCA = new HomePageSNCA();
    }

    @When("^I open search bar and entered the keyword as \"(.*?)\" or \"(.*?)\"$")
    public void open_searchBar_and_entered_productDetails(String value1,String value2) {
        String product;
        if(Props.getProp("select.language.to.test").equalsIgnoreCase("en"))
        {
            product=value1;
        }
        else {
            product=value2;
        }
        Assert.assertTrue(homePageSNCA.openSearchBarAndEnterProduct(product));
    }


    @When("^I open search bar and entered the product as \"(.*?)\"$")
    public void open_searchBar_and_enter_productSKU(String productKey) {

        if(webDriver.getCurrentUrl().contains("uat")) {
            product="uat."+productKey;
        }
        else {
            product=productKey;
        }
        Assert.assertTrue(homePageSNCA.openSearchBarAndEnterProduct(Props.getProp(product)));
    }

    @And("^I clicked on the search button$")
    public void click_on_search_icon_in_searchProduct_bar() {
        searchPageSNCA = homePageSNCA.clickOnSearchIconInSearchBar();
        Assert.assertTrue(searchPageSNCA != null, "Unable to click on search button in search product bar ");
    }

    @Then("page title should contains text with keyword \"(.*?)\" or \"(.*?)\"$")
    public void validate_page_title_with_keyword(String value1, String value2) throws InterruptedException {
        Thread.sleep(4000);
        String expected;
        if (Props.getProp("select.language.to.test").equalsIgnoreCase("en")) {
            expected = value1;
        } else {
            expected = value2;
        }
        Assert.assertTrue(getCurrentPageTitle().contains(expected), "Page title not matched over search product page");
    }

    @Then("page title should contains text \"(.*?)\" or \"(.*?)\"$")
    public void validate_page_title(String value1, String value2) throws InterruptedException {
        String expected;
        if (Props.getProp("select.language.to.test").equalsIgnoreCase("en")) {
            expected = value1+"'"+Props.getProp(product)+"'";
        } else {
            expected = value2+"'"+Props.getProp(product)+"'";
        }

        Assert.assertTrue(checkPageTitleContains(expected), "Page title not matched over search product page");
    }

    @And("search result page should display text contains keyword \"(.*?)\" or \"(.*?)\"$")
    public void validate_text_displayed_on_search_product(String value1,String value2) {
        String expected;
        if(Props.getProp("select.language.to.test").equalsIgnoreCase("en"))
        {
            expected=value1;
        }
        else
            {
                expected=value2;
            }
        Assert.assertTrue(searchPageSNCA.getTextDisplayedOnPage().contains(expected));
    }

    @And("search result page should display text contains \"(.*?)\"$")
    public void validateSearchPageDisplayedWithText(String productKey) {
        String expected;
        if(webDriver.getCurrentUrl().contains("uat")) {
            expected="uat."+productKey;
        }
        else {
            expected=productKey;
        }
        Assert.assertTrue(searchPageSNCA.getTextDisplayedOnPage().contains(Props.getProp(expected)));
    }

    @And("^validate that number of product tiles shown should be greater than zero$")
    public void validate_number_of_product_tiles_display() {
        List<String> tiles = searchPageSNCA.getProductTilesTitle();
        Assert.assertFalse(tiles.size() == 0, "Not showing any product when searched");

    }

    @And("^validate that product tiles title should contains text \"(.*?)\"$")
    public void validate_product_tiles_tiles_should_contains_text(String text) {
        String[] textToFound = text.split(" ");
        List<String> tilesTitle = searchPageSNCA.getProductTilesTitle();
        boolean isContains = false;
        SoftAssert softAssert = new SoftAssert();
        for (int i = 0; i < tilesTitle.size(); i++) {
            int location = i;
            isContains = false;
            for (String e : textToFound) {
                if (tilesTitle.get(i).toLowerCase().contains(e.toLowerCase())) {
                    isContains = true;
                }
            }
            softAssert.assertTrue(isContains, "Tile Located at the location-->>" + (++location) + " does not containing product name of which it is searched");
        }
        softAssert.assertAll();
    }

    @Then("^I got text on result page as \"(.*?)\" or \"(.*?)\"$")
    public void validate_text_shown_on_search_page(String value1, String value2) {
        String expected;
        if (Props.getProp("select.language.to.test").equalsIgnoreCase("en")) {
            expected = value1;
        } else {
            expected = value2;
        }
        Assert.assertEquals(searchPageSNCA.getTextDisplayedOnPage(), expected);
    }

    @And("^validate that number of product tiles shown should be equal to zero$")
    public void validate_number_of_tiles_shown_should_be_zero() {
        Assert.assertTrue(searchPageSNCA.getProductTilesTitle().size() == 0);
    }

    @Then("^I opened product in new tab and add it into the cart if showing out of stock then moved to next item$")
    public void open_product_in_new_tab_and_click_on_add_to_cart_button() {

    }

}
