package com.salmon.test.step_definitions.api;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.salmon.test.services.GetInstalmentPlanApi;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;

public class GetInstalmentPlanSteps {

//	private static String RESOURCE_LOCATION =
//		      "src" + File.separator + "test" + File.separator + "resources" + File.separator + "data"
//		          + File.separator;
////		  Response response = null;
//		  String jsonFileAsString;

	private Map<String, String> queryParamMap;
//	private Map<String, String> headerParamMap;
//	
	// private String city;
//	private Map<String, Object> node;
	private Response response;
	
	@Given("^the query param are$")
	public void theQueryParamAre(List<List<String>> queryParams) {
	    queryParamMap = new HashMap<>();
	    queryParamMap.put(queryParams.get(1).get(0), queryParams.get(1).get(1));

	  }
	
	@When("^the GET instalment plan request is triggered$")
	public void theGETInstalmentPlanRequestIsTriggered() {
	    response = GetInstalmentPlanApi.getInstalmentPlan(queryParamMap);
	  }
	
	@Then("^the JSON schema match with \"([^\"]*)\"$")
	public void theJSONSchemaMatchWith(File fileName) {
	    response.then().assertThat()
	        .body(matchesJsonSchema(fileName));

	  }
	
	
}
