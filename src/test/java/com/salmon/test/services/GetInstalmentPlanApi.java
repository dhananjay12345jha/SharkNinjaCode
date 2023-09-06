package com.salmon.test.services;

import java.util.Map;

import com.salmon.test.framework.helpers.ApiHelper;

import io.restassured.response.Response;

public class GetInstalmentPlanApi extends ApiHelper {

//	private static final String PATH = "items/";
//	private static final String COLOURS_PATH = "/en/api/products/colors";

	private static final String GET_INSTALMENT_PLAN = "/getInstalmentPlan";

	Response response = null;
	
	
	public static Response getInstalmentPlan(Map<String, String> queryParam) {

	    return givenConfig()
	        .queryParams(queryParam)
	        .when().get(GET_INSTALMENT_PLAN).then().statusCode(200).log().body()
	        .extract().response();
	  }
	
	
	
	
	
}
