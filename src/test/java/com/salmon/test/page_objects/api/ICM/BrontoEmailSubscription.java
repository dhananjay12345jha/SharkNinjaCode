package com.salmon.test.page_objects.api.ICM;

import com.salmon.test.framework.helpers.Props;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;

public class BrontoEmailSubscription extends GetAuthenticationToken
{
    private String baseUri = Props.getProp("icm.base.uri");
    private Response response;
    private static final Logger log = LoggerFactory.getLogger(BrontoEmailSubscription.class);

    public boolean hitRequest()
    {
      boolean flag=false;

      try
      {
          String url=baseUri+"/snap/customers/-/brontoemailsubscription";
          RestAssured.baseURI=url;

          RequestSpecification httpRequest= given();

          httpRequest.header("Accept","application/json");
          httpRequest.header("authentication-token",token);

          log.info("Going to hit the request bronto email subscription with the URL-->"+url);
          response=httpRequest.request(Method.GET);
          log.info("Successfully hit the request, response code is-->"+response.getStatusCode());
          flag=true;

      }catch (Exception e)
      {
        log.error("Some exception occurred while hit the bronto email subscription api-->"+e.getMessage());
        e.printStackTrace();
      }

      return flag;
    }

    public int getResponseCode() {
        return response.getStatusCode();
    }

    public String getResponseBody() {
        return response.getBody().asString();
    }

    public String getValueForKeyBrontoNewsLetter()
    {
        return response.jsonPath().getJsonObject("BrontoNewsletterURL").toString();
    }

    public int hitBrontoNewsLetterUrl()
    {
        RestAssured.baseURI=getValueForKeyBrontoNewsLetter();
        RequestSpecification httpRequest=RestAssured.given();
        httpRequest.relaxedHTTPSValidation();
        return httpRequest.request(Method.GET).getStatusCode();
    }
}
