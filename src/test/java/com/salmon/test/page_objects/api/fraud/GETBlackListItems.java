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

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.Map;


public class GETBlackListItems
{
    private String propFilePath=System.getProperty("user.dir")+Props.getProp("file.path");
    private String url= Props.getProp("blacklist.base.uri")+"/blacklistitems";
    private Response response;
    private String valueType,value="";
    private int blId=-1,limit,pageNumber;
    private String channel=Props.getProp("channel.to.test.blacklist");

    private static final Logger log = LoggerFactory.getLogger(GETBlackListItems.class);

    public boolean hitRequest()
    {
        boolean flag=false;
        try {

            RestAssured.baseURI=url;

            RequestSpecification httpRequest=RestAssured.given();

            if(blId!=-1)
            {
                httpRequest.queryParams("blId",blId);
            }

            if(!valueType.equalsIgnoreCase(""))
            {
                httpRequest.queryParams("value",value);
            }
            httpRequest.queryParams("valueType",valueType);
            httpRequest.queryParams("limit",limit);
            httpRequest.queryParams("pageNumber",pageNumber);
            httpRequest.queryParams("channel",channel);

            httpRequest.headers("Accept","application/json");

            log.info("Going to hit GET/BlackList Items detail api with url-->"+url);
            response=httpRequest.request(Method.GET);
            log.info("Successfully hit the api having response code-->"+response.getStatusCode());
            flag=true;
        }
        catch (Exception e)
        {
            log.error("Unable to hit the GET/BlackList items detail api-->"+e.getMessage());
            e.printStackTrace();
        }
        finally {
            blId=-1;
        }

        return flag;
    }


    public int getResponseCode()
    {
        log.info("Response code is-->"+response.getStatusCode());
        return response.getStatusCode();
    }

    public String getResponseBody()
    {
        return response.getBody().asString();
    }

    public void setLimit(int data)
    {
        limit=data;
        log.info("Successfully set the limit as-->"+limit);
    }
    public void setPageNumber(int data)
    {
        pageNumber=data;
        log.info("Successfully set the page Number as-->"+pageNumber);
    }
    public void setBlId(int data)
    {
        blId=data;
        log.info("Successfully set the blId as-->"+blId);
    }

    public void setValueType(String data)
    {
        valueType=data;
        log.info("Successfully set the value Type as-->"+valueType);
    }

    public String getBlackListItemType()
    {
        log.info("Returning value against key blackListItemType from response-->"+response.jsonPath().get("data.blackListItemType").toString());
        return response.jsonPath().get("data.blackListItemType").toString();
    }

    public int getBlackListRecordsSize()
    {
        log.info("Returning number of records returned-->"+response.jsonPath().getInt("data.totalBlackListRecords"));
        return response.jsonPath().getInt("data.totalBlackListRecords");
    }

    public void setValue(String value) {
        this.value = value;
        log.info("Seting the value which need to be search inside a blaclist is-->"+this.value);
    }

    public Map<String,Object> getVariablesFromValueList()
    {
        if(valueType.equalsIgnoreCase("address"))
        {
            return response.getBody().jsonPath().getMap("data.address[0]");
        }
        return response.getBody().jsonPath().getMap("data.valueList[0]");
    }

    public boolean storeIdOfABlackListItemType()
    {
        boolean flag=false;
        int id= Integer.parseInt(String.valueOf(getVariablesFromValueList().get("id")));

        try
        {
            log.info("Value of black list item type before is-->>"+Props.getProp("id.of.black.list.item.type"));
            PropertiesConfiguration config=new PropertiesConfiguration(propFilePath);
            config.setProperty("id.of.black.list.item.type",id);
            config.save();
            log.info("Successfully updated the config file with value of id.of.black.list.item.type as-->"+Props.getProp("id.of.black.list.item.type"));
            flag=true;
        }catch (Exception e)
        {
            log.error("Some exception occurred while updating the config file with value of id.of.black.list.item.type--->"+e.getMessage());
            e.printStackTrace();
        }

        Props.loadRunConfigProps("/environment.properties");

        return flag;
    }
}
