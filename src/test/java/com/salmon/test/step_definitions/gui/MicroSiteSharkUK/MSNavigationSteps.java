package com.salmon.test.step_definitions.gui.MicroSiteSharkUK;


import com.salmon.test.framework.helpers.UrlBuilder;
import cucumber.api.java.en.Given;

public class MSNavigationSteps {


  @Given("^I navigate to the \"(.*?)\" page on MicroSite$")
  public void I_navigate_to_the_page(String pageName) {
    if (pageName.equalsIgnoreCase("HOME")) {
      UrlBuilder.startAtHomePage();

    }
  }
}
