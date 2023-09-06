package com.salmon.test.framework.helpers;


import static io.restassured.RestAssured.given;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.File;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;


/**
 * Every Api Step definition class should extend this class
 */

public class ApiHelper {

  private static Gson gson;

  static {
    RestAssured.baseURI = UrlBuilder.getBasePathURI().toString();
  }


  protected static RequestSpecification givenConfig() {

    //Below is a duplicate step from static block. Remove this line for the project as there will be only one URI per project
    RestAssured.baseURI = UrlBuilder.getBasePathURI().toString();
    RestAssured.useRelaxedHTTPSValidation();
//    return given().header("x-rapidapi-key", "2bc91bfe83msh5378410431dad05p1b8ccajsn71e9a64b4c8a"); it has been commented by Sumit
    return given().header("authentication-token","encryption0@PBEWithMD5AndTripleDES:P+f5dfFuKKA=|yYLC4ieK8vNjoPuXSFCdIPlqZP/XOg6pT/PHumSvXIahvOyqDof7aQ==");
    //    header("Accept-Language", "en").header("Content-Type", "application/json");
  }

  //Specify all one time default Gson config
  public static Gson gson() {
    GsonBuilder gsonBuilder = new GsonBuilder();
    // if uncommented will also create Json for fields which are null
    //   gsonBuilder.serializeNulls();
    gson = gson(gsonBuilder);
    return gson;
  }

  //Custom Gson config to override Default Gson  configuration
  public static Gson gson(GsonBuilder gsonBuilder) {
    gson = gsonBuilder.create();
    return gson;
  }

  public static List<HashMap<String, Object>> extractListOfNodesFromJson(Response res,
      String nodeName) {
    JsonPath jsonPath = new JsonPath(res.body().asString());
    return jsonPath.getList(nodeName);
  }

  public static String convertJsonFileToJsonStr(String fileName, String location) {

    String json = null;
    try {
      json = FileUtils.readFileToString(new File(location + fileName), Charset.defaultCharset());
    } catch (Exception e) {
      e.getMessage();
    }
    return json;
  }

  public static String getJsonValue(String jsonReq, String key) {
    JSONObject json = new JSONObject(jsonReq);
    boolean exists = json.has(key);
    Iterator<?> keys;
    String nextKeys;
    String val = "";
    if (!exists) {
      keys = json.keys();
      while (keys.hasNext()) {
        nextKeys = (String) keys.next();
        try {
          if (json.get(nextKeys) instanceof JSONObject) {
            return getJsonValue(json.getJSONObject(nextKeys).toString(), key);
          } else if (json.get(nextKeys) instanceof JSONArray) {
            JSONArray jsonArray = json.getJSONArray(nextKeys);
            int i = 0;
            if (i < jsonArray.length()) do {
              String jsonArrayString = jsonArray.get(i).toString();
              JSONObject innerJson = new JSONObject(jsonArrayString);
              return getJsonValue(innerJson.toString(),key);
            } while (i < jsonArray.length());
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    } else {
      val = json.get(key).toString();
    }
    return val;
  }
}