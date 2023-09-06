package com.salmon.test.page_objects.api.fraud;

import com.salmon.test.framework.helpers.Props;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class POSTBlacklist {
    private String baseUri = Props.getProp("blacklist.base.uri");
    private Response response;
    private String propFilePath = System.getProperty("user.dir") + Props.getProp("file.path");
    private String action, name;
    private String channel = Props.getProp("channel.to.test.blacklist");

    private static final Logger log = LoggerFactory.getLogger(POSTBlacklist.class);

    public boolean hitRequest() {
        boolean flag = false;

        try {
            RestAssured.baseURI = baseUri;
            RequestSpecification httpRequest = RestAssured.given();


            httpRequest.headers("Accept", "application/json");
            httpRequest.headers("Content-Type", "application/json");

            JSONObject jsonObject = new JSONObject();

            jsonObject.put("action", action);
            jsonObject.put("name", name);
            jsonObject.put("channel", channel);

            httpRequest.body(jsonObject.toJSONString());

            log.info("Going to hit the request POST/Blacklist to get the data list having the uri-->>" + baseUri);
            response = httpRequest.request(Method.POST);
            log.info("Request hit successfully response code is-->" + response.getStatusCode());

            flag = true;
        } catch (Exception e) {
            log.error("Some exception occurred while hitting the request POST/BlackList-->" + e.getMessage());
            e.printStackTrace();
        }

        return flag;
    }

    public int getResponseCode() {
        log.info("Returning response code of api POST/Blacklist which is-->" + response.getStatusCode());
        return response.getStatusCode();
    }

    public String getResponseBody() {
        log.info("Returning response body of api POST/Blacklist which is-->" + response.getBody().asString());
        return response.getBody().asString();
    }

    public String getMessageFromResponse() {
        log.info("Returning response message of api POST/Blacklist which is-->" + response.jsonPath().getString("message"));
        return response.jsonPath().getString("message");
    }

    public int getBlIdFromResponse() {
        log.info("Returning blId from the response of api POST/Blacklist which is-->" + response.jsonPath().getInt("data.blId"));
        return response.jsonPath().getInt("data.blId");
    }

    public String getBlacklistNameFromResponse() {
        log.info("Returning newly created blacklist name from the response of api POST/Blacklist which is-->" + response.jsonPath().getString("data.name"));
        return response.jsonPath().getString("data.name");
    }

    public boolean storeBlIdIntoTheConfigFile() {
        boolean flag = false;
        int blId = getBlIdFromResponse();

        try {
            log.info("Value of blid before is-->>" + Props.getProp("blId.captured.from.newly.created.request"));
            PropertiesConfiguration config = new PropertiesConfiguration(propFilePath);
            config.setProperty("blId.captured.from.newly.created.request", blId);
            config.save();
            log.info("Successfully updated the config file with value of blId as-->" + blId);
            flag = true;
        } catch (Exception e) {
            log.error("Some exception occurred while updating the config file with value of blId--->" + e.getMessage());
            e.printStackTrace();
        }

        Props.loadRunConfigProps("/environment.properties");
        return flag;
    }

    public void setValueAction(String action1) {
        log.info("Setting up the value of action-->" + action1);
        action = action1;
    }

    public void setValueName(String name1) {
        log.info("Setting up the value of the name-->" + name1);
        name = name1;
    }

}
