package com.salmon.test.page_objects.api.fraud;

import com.salmon.test.framework.helpers.Props;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class GetBlacklist {
    private String baseUri = Props.getProp("blacklist.base.uri");
    private Response response;
    private String channel = Props.getProp("channel.to.test.blacklist");
    private String propFilePath = System.getProperty("user.dir") + Props.getProp("file.path");

    private static final Logger log = LoggerFactory.getLogger(GetBlacklist.class);

    public boolean hitRequest() {
        boolean flag = false;

        try {
            RestAssured.baseURI = baseUri;
            RequestSpecification httpRequest = RestAssured.given();

            httpRequest.queryParams("channel", channel);

            log.info("Going to hit the request GET/Blacklist to get the data list having the uri-->>" + baseUri);
            response = httpRequest.request(Method.GET);
            log.info("Request hit successflly response code is-->" + response.getStatusCode());

            flag = true;
        } catch (Exception e) {
            log.error("Some exception occurred while hitting the request GET/BlackList-->" + e.getMessage());
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

    public int getValueOfKeyTotalBlackListRecords() {
        int value = Integer.parseInt(response.jsonPath().get("data.totalBlackListRecords").toString());
        log.info("Value against key \"totalBlackListRecords\" i-->" + value);
        return value;
    }

    public int getDataListSize() {
        return response.jsonPath().getList("data.blackList").size();
    }

    public boolean saveBlackListIdInConfigFile(String key) {
        boolean flag = false;
        String data = response.getBody().jsonPath().getList("data.blackList.blId").get(0).toString();
        if (data.isEmpty() || data == null) {
            throw new RuntimeException("Unable to save blacklist id because in response not blId are there, Please check path or data in DB");
        } else {
            if (!data.equalsIgnoreCase("")) {
                try {
                    log.info("Value of order Number before is-->>" + Props.getProp(key));
                    PropertiesConfiguration config = new PropertiesConfiguration(propFilePath);
                    config.setProperty(key, data);
                    config.save();
                    log.info("Successfully updated the config file with value of id as-->" + data);
                    flag = true;
                } catch (Exception e) {
                    log.error("Some exception occurred while updating the config file with value of order Number--->" + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

}
