package com.salmon.test.services;

import static io.restassured.RestAssured.given;

import com.salmon.test.framework.helpers.ApiHelper;
import com.salmon.test.models.api.ItemModel;
import com.salmon.test.models.api.ItemsModel;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.List;
import java.util.Map;

public class SampleApi extends ApiHelper {

  private static final String PATH = "items/";
  private static final String COLOURS_PATH = "/en/api/products/colors";

  private static final String CURRENT_WEATHER = "/weather";

  Response response = null;

  public static Response getCurrentWeather(Map<String, String> queryParam) {

    return givenConfig()
        .queryParams(queryParam)
        .when().get(CURRENT_WEATHER).then().statusCode(200).log().body()
        .extract().response();
  }

  public static Response getRecipe(Map<String, String> queryParam) {

    RestAssured.baseURI = "https://recipe-puppy.p.rapidapi.com/";
    RestAssured.useRelaxedHTTPSValidation();
    return given().header("x-rapidapi-key", "fa0de3bcb1msh42d64b6d3803fcbp199d83jsn418aa32b67b9")
        .queryParams(queryParam).get().then().statusCode(200).extract().response();

  }

  public static Response getListOfColours() {
    return givenConfig().when().get(COLOURS_PATH);
  }

  public static Response postDetails(List<ItemModel> itemModels) {
    ItemsModel itemsData = buildItemsData(itemModels);
    String payLoad = gson().toJson(itemsData);
    return givenConfig().
        body(payLoad).
        //If there are Query Params in URL
        //queryParam("").
        //If there are Form Params in URL
        //formParam("").
            post(PATH);
  }

  private static ItemsModel buildItemsData(List<ItemModel> itemModels) {
    return ItemsModel.builder().id("123456").
        items(itemModels).
        build();
  }

  public static Response updateDetails(List<ItemModel> itemModels) {
    ItemsModel itemsData = buildItemsData(itemModels);
    String payLoad = gson().toJson(itemsData);
    return givenConfig().
        body(payLoad).
        when().
        put(PATH);
  }

  public static Response deleteItem(String uniqueId) {
    return givenConfig().
        when().delete(PATH + uniqueId);
  }


}
