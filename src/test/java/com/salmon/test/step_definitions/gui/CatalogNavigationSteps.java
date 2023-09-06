package com.salmon.test.step_definitions.gui;

import com.mysql.jdbc.log.Log;
import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import com.salmon.test.page_objects.gui.*;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import static com.salmon.test.page_objects.gui.HomePage.LeftTogglebtn;

public class CatalogNavigationSteps extends PageObject {

    private CatalogNavigation catalogNavigation;
    private ProductCategoryPage productCategoryPage;
    private PartsAndAccessories partsAndAccessories;
    private TipsAndAdvicePage tipsAndAdvicePage;

    private static final Logger log = LoggerFactory.getLogger(PDPPage.class);
    public CatalogNavigationSteps(CatalogNavigation catalogNavigation, ProductCategoryPage productCategoryPage, PartsAndAccessories partsAndAccessories, TipsAndAdvicePage tipsAndAdvicePage) {

        this.catalogNavigation = catalogNavigation;
        this.productCategoryPage = productCategoryPage;
        this.partsAndAccessories = partsAndAccessories;
        this.tipsAndAdvicePage = tipsAndAdvicePage;
    }

    @When("^I move cursor over Products$")
    public void I_move_cursor_over_Products() {
        catalogNavigation.productsCatalogTab();
        log.info(" mousehover on products ");
    }
    //Rita Singh

    @When("^I move cursor over Parts and Accessories$")
    public void I_move_cursor_over_Parts_and_Accessories() {
        IsPageLoaded.waitAllRequest();
        catalogNavigation.partsAndAccessoriesTab();
    }

    @When("^I move cursor over Product Category For CA$")
    public void iMoveCursorOverProductCategoryForCA() {
        IsPageLoaded.waitAllRequest();
        catalogNavigation.productCategoryForCA();
    }
    @When("^I click on tips And Advice link$")
    public void I_click_on_tipsAndAdvice_link() throws InterruptedException {
        Thread.sleep(15000);
        catalogNavigation.tipsAndAdviceTab();
    }

    @When("^I click on \"([^\"]*)\" link$")
    public void I_click_on_header_link(String linkToClick) {
        if (UrlBuilder.isMobile()) {
            WebElement element = waitForExpectedElement(LeftTogglebtn);
            if (element.getAttribute("aria-expanded") == null || element.getAttribute("aria-expanded").equalsIgnoreCase("false")) {
                element.click();
            }
        }
        Assert.assertTrue(catalogNavigation.clickHeaderLink(linkToClick), "Unable to click on header link->>" + linkToClick);
    }

    @When("^I click on \"([^\"]*)\"$")
    public void I_click_on_value_from_parts_and_accessories(String selectedvalue) {
        String PartsAndAccessoriesSelectedValue = Props.getProp(selectedvalue);
        int j = catalogNavigation.partsCategoryLinks().size();
        System.out.println(j);
        for (int i = 0; i < j; i++) {
            String temp = catalogNavigation.partsCategoryLinks().get(i).getText();
            System.out.println(temp);
            if (temp.contains(PartsAndAccessoriesSelectedValue)) {
                //int optionValue = Integer.parseInt(PartsAndAccessoriesSelectedValue.substring(PartsAndAccessoriesSelectedValue.length() - 1));
                System.out.println("option valus is: " + i);
                catalogNavigation.clickPartsAndAccessoriesOption(PartsAndAccessoriesSelectedValue, i + 1);
                break;
            }
        }
    }


    @When("^I clicked on \"([^\"]*)\"$")
    public void I_click_on_value_from_parts_and_accessories_0(String selectedvalue0) {
        String PartsAndAccessoriesSelectedValue = Props.getProp(selectedvalue0);
        int j = catalogNavigation.partsCategoryLinks().size();
        //System.out.println("part category links are " + j);
        for (int i = 0; i < j; i++) {
            String temp = catalogNavigation.partsCategoryLinks().get(i).getText();
            log.info("The image is displaying at position " + i);
            System.out.println(temp);
            if (temp.contains(PartsAndAccessoriesSelectedValue)) {
                //int optionValue = Integer.parseInt(PartsAndAccessoriesSelectedValue.substring(PartsAndAccessoriesSelectedValue.length() - 1));
                System.out.println("option valus is: " + i);
                catalogNavigation.clickPartsAndAccessoriesOption(PartsAndAccessoriesSelectedValue, i + 1);
                break;
            }
        }
    }

    @And("^I clicked on CA product Category \"([^\"]*)\"$")
    public void I_click_on_value_from_products_Category_Links_CA(String selectedvalue) throws InterruptedException {
        String ProductCategoriesSelectedValue = Props.getProp(selectedvalue);
        int j = catalogNavigation.productsCategoryLinksCA().size();
        System.out.println("Total  no of Categories Products are "+ j);
        for (int i = 0; i < j; i++) {
            String temp = catalogNavigation.productsCategoryLinksCA().get(i).getText();
            System.out.println("All Links are " + temp);
            if (temp.contains(ProductCategoriesSelectedValue)) {
                //int optionValue = Integer.parseInt(PartsAndAccessoriesSelectedValue.substring(PartsAndAccessoriesSelectedValue.length() - 1));
                System.out.println("option valus is: " + i);
                catalogNavigation.clickProductsCategoryCAOption(ProductCategoriesSelectedValue, i + 1);
                log.info("successfully clicked on " + temp);
                break;
            }
        }
    }

    @And("^I clicked on CA product Category SharkCA Vaccum \"([^\"]*)\"$")
    public void I_click_on_value_from_products_Category_Links_SharkCA_Vaccum(String selectedvalue) throws InterruptedException {
        String ProductCategoriesSelectedValue = Props.getProp(selectedvalue);
        int j = catalogNavigation.productCategoryAllLinksSharkCAVaccum().size();
        System.out.println("Total  no of Categories Products are "+ j);
        for (int i = 0; i < j; i++) {
            String temp = catalogNavigation.productCategoryAllLinksSharkCAVaccum().get(i).getText();
            System.out.println("All Links are " + temp);
            if (temp.contains(ProductCategoriesSelectedValue)) {
                //int optionValue = Integer.parseInt(PartsAndAccessoriesSelectedValue.substring(PartsAndAccessoriesSelectedValue.length() - 1));
                System.out.println("option valus is: " + i);
                catalogNavigation.clickProductsCategoryCAOption(ProductCategoriesSelectedValue, i + 1);
                log.info("successfully clicked on " + temp);
                break;
            }
        }
    }


    @And("^I clicked on CA product Category Air Purifiers \"([^\"]*)\"$")
    public void I_click_on_value_from_products_Category_Links_Shark_CA_AirPurifier(String selectedvalue) throws InterruptedException {
        String ProductCategoriesSelectedValue = Props.getProp(selectedvalue);
        int j = catalogNavigation.productCategoryAllLinksSharkCAAirPurifiers().size();
        System.out.println("Total  no of Categories Products are "+ j);
        for (int i = 0; i < j; i++) {
            String temp = catalogNavigation.productCategoryAllLinksSharkCAAirPurifiers().get(i).getText();
            if (temp.contains(ProductCategoriesSelectedValue)) {
                //int optionValue = Integer.parseInt(PartsAndAccessoriesSelectedValue.substring(PartsAndAccessoriesSelectedValue.length() - 1));
                System.out.println("option valus is: " + i);
                catalogNavigation.clickProductsCategorySharkCAOptionAirPurifier(ProductCategoriesSelectedValue, i + 1);
                log.info("successfully clicked on " + temp);
                break;
            }
        }
    }

    @And("^I clicked on CA product Category Hair Care \"([^\"]*)\"$")
    public void I_click_on_value_from_products_Category_Links_Shark_CA_HairCare(String selectedvalue) throws InterruptedException {
        String ProductCategoriesSelectedValue = Props.getProp(selectedvalue);
        int j = catalogNavigation.productCategoryAllLinksSharkCAHairCare().size();
        System.out.println("Total  no of Categories Products are "+ j);
        for (int i = 0; i < j; i++) {
            String temp = catalogNavigation.productCategoryAllLinksSharkCAHairCare().get(i).getText();
            if (temp.contains(ProductCategoriesSelectedValue)) {
                //int optionValue = Integer.parseInt(PartsAndAccessoriesSelectedValue.substring(PartsAndAccessoriesSelectedValue.length() - 1));
                System.out.println("option valus is: " + i);
                catalogNavigation.clickProductsCategorySharkCAOptionHairCare(ProductCategoriesSelectedValue, i + 1);
                log.info("successfully clicked on " + temp);
                break;
            }
        }
    }

    @And("^I clicked on CA product Category Mops & Irons \"([^\"]*)\"$")
    public void I_click_on_value_from_products_Category_Links_Shark_CA_MopsIrons(String selectedvalue) throws InterruptedException {
        String ProductCategoriesSelectedValue = Props.getProp(selectedvalue);
        int j = catalogNavigation.productCategoryAllLinksSharkCAHairMobsIron().size();
        System.out.println("Total  no of Categories Products are "+ j);
        for (int i = 0; i < j; i++) {
            String temp = catalogNavigation.productCategoryAllLinksSharkCAHairMobsIron().get(i).getText();
            if (temp.contains(ProductCategoriesSelectedValue)) {
                //int optionValue = Integer.parseInt(PartsAndAccessoriesSelectedValue.substring(PartsAndAccessoriesSelectedValue.length() - 1));
                System.out.println("option valus is: " + i);
                catalogNavigation.clickProductsCategorySharkCAOptionMopsIrons(ProductCategoriesSelectedValue, i + 1);
                log.info("successfully clicked on " + temp);
                break;
            }
        }
    }
    @When("^I click on products category level 1 option \"([^\"]*)\"$")
    public void i_click_on_products_category_level_1_option(String productCategoryLevel1Option) throws InterruptedException {
        String categoryLevel1Option = Props.getProp(productCategoryLevel1Option);
       Assert.assertTrue(catalogNavigation.clickCategory(categoryLevel1Option),"Not able to click on the category" + productCategoryLevel1Option);

//		int j = catalogNavigation.productCategoryLinks().size();
//		System.out.println(j);
//		for (int i = 0; i < j; i++) {
//			String temp = catalogNavigation.productCategoryLinks().get(i).getText();
//			System.out.println(temp);
//			if (temp.equals(categoryLevel1Option)) {
//				int optionValue = Integer.parseInt(productCategoryLevel1Option.substring(productCategoryLevel1Option.length() - 1));
//				System.out.println("option valus is: "+optionValue);
//				catalogNavigation.clickCategorylevel1Option(categoryLevel1Option,optionValue);
//				break;
//			}
//		}
//		Thread.sleep(15000);
    }

//	@When("^I move cursor over Parts and Accessories$")
//	public void i_move_cursor_over_Parts_and_Accessories() throws InterruptedException {
//		Thread.sleep(10000);
//		catalogNavigation.partsAccessoriesTab();
//		Thread.sleep(3000);
//		int j = catalogNavigation.partsCategoryLinks().size();
//		System.out.println(j);
//		for (int i = 0; i < j; i++) {
//			String temp = catalogNavigation.partsCategoryLinks().get(i).getText();
//			System.out.println(temp);
//		}
////		Thread.sleep(10000);
//
//	}
//

    @Then("^I successfully navigate to chosen product list \"([^\"]*)\"$")
    public void i_successfully_navigate_to_chosen_product_list(String productCategoryLevel1Option1PageTitle) {
        IsPageLoaded.waitAllRequest();
        String expectedPageUrl = Props.getProp(productCategoryLevel1Option1PageTitle);
        System.out.println("expected is " + expectedPageUrl);
        System.out.println(productCategoryPage.getProductCategoryPageTitle());
//        Assert.assertTrue(productCategoryPage.getProductCategoryPageTitle().contentEquals(expectedPageTitle));
        Assert.assertTrue(webDriver.getCurrentUrl().contains(expectedPageUrl));
    }

    @Then("^I successfully navigate to chosen parts and acessories page$")
    public void I_successfully_navigate_to_chosen_parts_and_acessories_page() {
        String expectedPageTitle = Props.getProp("parts.accessory.value");
        System.out.println("expectedPageTitle--" + expectedPageTitle);
        System.out.println("ActualPageTitle--" + getWebDriver().getTitle());
        //Assert.assertTrue(partsAndAccessories.getPartsAndAccessoriesPageTitle().contains(expectedPageTitle));
        Assert.assertTrue(wait.until(ExpectedConditions.titleContains(expectedPageTitle)));

    }

    @Then("^I successfully navigate to tips and Advice page$")
    public void I_successfully_navigate_to_tips_and_Advice_page() {

        Assert.assertTrue(tipsAndAdvicePage.TipsAndAdvicePageTitle().contains("Shark"));

    }

    @Then("^I successfully navigate to recipes and tips page$")
    public void I_successfully_navigate_to_recipe_and_tips_page() {
        Assert.assertEquals(tipsAndAdvicePage.verifyRecipeTipsHeading(), Props.getProp("recipe.tips.heading"));
        webDriver.navigate().back();
    }

    @Then("^I select the product from the listed category page \"([^\"]*)\"$")
    public void I_select_the_product_from_the_listed_category_page(String productCategoryLevel1Option1SpecificProduct) {
        String productSpecificSKU = Props.getProp(productCategoryLevel1Option1SpecificProduct);
        catalogNavigation.selectProductfromCategoryPage(productSpecificSKU);
        IsPageLoaded.waitAllRequest();
        Assert.assertTrue(wait.until(ExpectedConditions.titleContains(productSpecificSKU)), "Title is-->>" + webDriver.getTitle());
    }
}
