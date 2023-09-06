package com.salmon.test.step_definitions.api;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static io.restassured.path.json.JsonPath.from;
import static org.assertj.core.api.Assertions.assertThat;

import com.google.gson.Gson;
import com.salmon.test.models.api.ItemModel;
import com.salmon.test.models.api.ResponseModel;
import com.salmon.test.services.SampleApi;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.testng.Assert;

//import com.salmon.test.services.SampleApi;

//import com.jayway.restassured.response.Response;
//import static com.jayway.restassured.path.json.JsonPath.from;

/**
 * Step Definition implementation class for Cucumber Steps defined in Feature file
 */

public class SampleApiSteps {

  private Map<String, String> queryParamMap;

  // private String city;
  private Map<String, Object> node;
  private Response response;

  /* Example with JsonPath to extract names of colour form JSON response

   * Convert Response Object to asString(), which is Json Representation
   * use JsonPath "from" to convert the Response Object to Json String Representation
   * Access converted JSON String representation using locator e.g "colors.name"
   *  Example with XmlPath
   * String xml = post("/shopping").andReturn().body().asString()
   * Node category = from(xml).get("shopping.category[0]");

   */


  @Given("^the query params are$")
  public void theQueryParamsAre(List<List<String>> queryParams) {
    queryParamMap = new HashMap<>();
    queryParamMap.put(queryParams.get(1).get(0), queryParams.get(1).get(1));

  }

  @When("^the GET current weather request is triggered$")
  public void theGETCurrentWeatherRequestIsTriggered() {
    response = SampleApi.getCurrentWeather(queryParamMap);
  }


  @Then("^the \"([^\"]*)\" value is \"([^\"]*)\" in the response$")
  public void theValueIsInTheResponse(String city, String countryValue) {
    node = response.getBody().jsonPath().getMap("sys");
    Assert.assertEquals(node.get(city), countryValue);
  }

  @And("^\"([^\"]*)\" is \"([^\"]*)\"$")
  public void is(String cityName, String cityValue) {
    Assert.assertEquals(response.getBody().jsonPath().get(cityName), cityValue);
  }


  @When("^the GET recipe request is sent$")
  public void theGETRecipeRequestIsSent() {
    response = SampleApi.getRecipe(queryParamMap);

  }

  @Then("^ingredients of \"([^\"]*)\" is received$")
  public void ingredientsOfIsReceived(String recipeName) {

    //JsonPath jsonPath = extractValueFromJson(response);

    Assert.assertEquals(response.getBody().jsonPath().get("results[1].title"), recipeName);
    String ingredientsList = response.getBody().jsonPath().get("results[1].ingredients");
    System.out.println(ingredientsList);


  }

  @And("^list of titles is displayed$")
  public void listOfTitlesIsDisplayed() {

    List<HashMap<String, Object>> nodeList = response.getBody().jsonPath().getList("results");

    nodeList.forEach(p -> System.out.println(p.get("title")));

    /*for (HashMap<String, Object> node : nodeList) {
      System.out.println(node.get("title"));
    }*/
  }

  @Then("^the JSON schema matches with \"([^\"]*)\"$")
  public void theJSONSchemaMatchesWith(File fileName) {
    response.then().assertThat()
        .body(matchesJsonSchema(fileName));


  }


  /*   Perform a HTTP GET request for an endpoint*/
  @When("^I get the list of colours$")
  public void iGetTheListOfColours() {
    response = SampleApi.getListOfColours();
  }

  @Then("^the colour collections contain colour name$")
  public void the_colour_collections_contain_colour_name() {

    //Example with simple JsonPath
    List<String> colourNames = from(response.asString()).get("colors.name");
    assertThat(colourNames.size()).isGreaterThan(0);

    List<ResponseModel.Colors> colors = from(response.asString()).get("colors");

    assertThat(colors.size()).isGreaterThan(0);

    Gson gson = SampleApi.gson();
    ResponseModel responseModel = gson.fromJson(response.asString(), ResponseModel.class);
    assertThat(responseModel.getColors().size()).isGreaterThan(0);

  }

  @When("^I create an Item$")
  public void I_create_an_Item(List<ItemModel> items) {
    response = SampleApi.postDetails(items);
  }

  @When("^I update an Item$")
  public void I_update_an_Item(List<ItemModel> items) {
    response = SampleApi.updateDetails(items);
  }

  @When("^I delete an Item \"([^\"]*)\"$")
  public void I_delete_an_Item(String uniqueId) {
    response = SampleApi.deleteItem(uniqueId);
  }

  /*   Verify HTTP Status code from response*/
  @Then("^the (?:Item is|Items are) \"([^\"]*)\"$")
  public void the_Item_is(String result) {
    int statusCode = response.getStatusCode();
    switch (result) {
      case ("created"):

        assertThat(statusCode).isEqualTo(201);
        break;
      case "updated":
      case ("retrieved"):
        assertThat(statusCode).isEqualTo(200);
        break;
      case "deleted":
        assertThat(statusCode).isEqualTo(204);
        break;
    }

  }
}