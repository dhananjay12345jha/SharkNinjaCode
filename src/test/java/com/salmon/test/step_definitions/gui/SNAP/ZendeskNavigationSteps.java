package com.salmon.test.step_definitions.gui.SNAP;


import com.salmon.test.framework.helpers.UrlBuilder;
import cucumber.api.java.en.Given;

public class ZendeskNavigationSteps {


  @Given("^I navigate to the zendesk \"(.*?)\" page$")
  public void I_navigate_to_the_zendesk_page(String pageName) {
    if (pageName.equalsIgnoreCase("HOME")) {
      UrlBuilder.startAtHomePage();
    }
  }

}
