package com.salmon.test.services;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

public class FinancialTimesAPI {

  private static final String SEARCH_CONTENT = "/content/search/v1";

  Response response = null;


  public static Response getPOSTSearchContent(String jsonFile) {
    Map<String, String> queryParamMap;
    queryParamMap = new HashMap<>();
    queryParamMap.put("apiKey", "59cbaf20e3e06d3565778e7bd283c04500834443953b867bcd231980");

    RestAssured.baseURI = "https://api.ft.com";
    RestAssured.useRelaxedHTTPSValidation();
    return given().header("Content-Type", "application/json")
        .queryParams(queryParamMap)
        .body(jsonFile)
        .when().post(SEARCH_CONTENT).then().log().body().extract().response();
  }

}
