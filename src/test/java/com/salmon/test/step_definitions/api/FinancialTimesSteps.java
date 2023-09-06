package com.salmon.test.step_definitions.api;

import static org.testng.Assert.assertEquals;

import com.salmon.test.framework.helpers.ApiHelper;
import com.salmon.test.services.FinancialTimesAPI;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import java.io.File;
import java.util.List;

public class FinancialTimesSteps {

  private static String RESOURCE_LOCATION =
      "src" + File.separator + "test" + File.separator + "resources" + File.separator + "data"
          + File.separator;
  Response response = null;
  String jsonFileAsString;

  @Given("^the search content \"([^\"]*)\" is created$")
  public void theSearchFilterIsCreated(String fileName) {

    jsonFileAsString = ApiHelper.convertJsonFileToJsonStr(fileName, RESOURCE_LOCATION);

  }

  @When("^the user send GET POST search content request$")
  public void theUserSendGETPOSTSearchContentRequest() {
    response = FinancialTimesAPI.getPOSTSearchContent(jsonFileAsString);

  }

  @Then("^the valid Json response is received with status code \"([^\"]*)\"$")
  public void theValidJsonResponseIsReceivedWithStatusCode(int statusCode) {
    assertEquals(statusCode, response.getStatusCode());
  }

  @And("^the post response match with the aspects$")
  public void thePostResponseMatchWithTheAspects(List<String> aspectName) {

  }
}
