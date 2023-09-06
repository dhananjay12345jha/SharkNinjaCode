package com.salmon.test.services;

import java.util.Map;

import com.salmon.test.framework.helpers.ApiHelper;

import io.restassured.response.Response;


public class PayFullBalanceApi extends ApiHelper {

	private static final String PAY_FULL_BALANCE = "/payFullBalance";

	Response response = null;
	
	
	public static Response payFullBalance(Map<String, String> queryParam) {

	    return givenConfig()
	        .queryParams(queryParam)
	        .when().post(PAY_FULL_BALANCE).then().statusCode(200).log().body()
	        .extract().response();
	  }
	
	
	
}
