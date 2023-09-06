package com.salmon.test.page_objects.api.fraud.instalmentController;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.page_objects.api.ICM.GetAuthenticationToken;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import java.io.FileReader;
import java.util.Properties;

public class UpdateInstalmentPlan extends GetAuthenticationToken {
    private String baseUri = Props.getProp("get.environment");
    private Response response;
    private String orderId;
    private String emailId=Props.getProp("login.email");
    private FileReader reader;
    private Properties properties=new Properties();
    private String firstInstalmentAmount, subsequentInstalmentAmount, numberOfInstalment;

    private static final Logger log = LoggerFactory.getLogger(GetInstalmentPlanDetails.class);

    private void refreshPropertyFile()
    {
        try{
            reader=new FileReader(System.getProperty("user.dir")+ Props.getProp("file.path"));
            properties.load(reader);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public boolean hitRequest()
    {
        boolean flag=false;
        String url=baseUri+"/updateInstalmentPlan";
        try {
            RestAssured.baseURI=url;
            RequestSpecification httpRequest=RestAssured.given();
            httpRequest.headers("accept", "application/json");
            httpRequest.contentType(ContentType.JSON);

            fetchCustomerDetailsGET(emailId);
            generateCustomerToken();

            httpRequest.headers("authentication-token",token);

            setOrderId();
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("firstInstalmentAmount", firstInstalmentAmount);
            jsonObject.put("instalmentDate", getEpocTime());
            jsonObject.put("numberOfInstalments", numberOfInstalment);
            jsonObject.put("orderId", orderId);
            jsonObject.put("subsequentAmount", subsequentInstalmentAmount);

            httpRequest.body(jsonObject.toJSONString());

            log.info("Going to hit the request Get Instalment Plan details Api to get the data list having the uri-->>"+url);
            response=httpRequest.request(Method.POST);
            log.info("Request hit successfully response code is-->"+response.getStatusCode());
            flag=true;

        }catch (Exception e)
        {
            log.error("Unable to hit the GET/instalmentPlansDetails api-->"+e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public void setOrderId() {
        refreshPropertyFile();
        orderId=properties.getProperty("order.number.MS.webFront");
    }

    public long getEpocTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 2);
        Date futureDate = calendar.getTime();
//        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat crunchifyFormat = new SimpleDateFormat("MMM dd yyyy HH:mm:ss.SSS zzz");
        String currentTime = crunchifyFormat.format(futureDate);
        long epochTime=0;
        try {

            // parse() parses text from the beginning of the given string to produce a date.
            Date date = crunchifyFormat.parse(currentTime);

            // getTime() returns the number of milliseconds since January 1, 1970, 00:00:00 GMT represented by this Date object.
            epochTime = date.getTime();
            epochTime = epochTime/1000;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return epochTime;
    }

    public void setFirstInstalmentAmount(String firstInstalmentAmount) {
        log.info("Setting up the value of firstInstalmentAmount-->" + firstInstalmentAmount);
        this.firstInstalmentAmount = firstInstalmentAmount;
    }

    public void setNumberOfInstalment(String numberOfInstalment) {
        log.info("Setting up the value of numberOfInstalment-->" + numberOfInstalment);
        this.numberOfInstalment = numberOfInstalment;
    }

    public void setSubsequentInstalmentAmount(String subsequentInstalmentAmount) {
        log.info("Setting up the value of subsequentInstalmentAmount-->" + subsequentInstalmentAmount);
        this.subsequentInstalmentAmount = subsequentInstalmentAmount;
    }

    public int getResponseCode() {
        return response.getStatusCode();
    }

    public String getMessageFromResponse() {
        log.info("Message from the response is-->"+response.jsonPath().getString("message"));
        return response.jsonPath().getString("message");
    }

    public boolean verifySuccessMessage(String successMessage) {
        return getMessageFromResponse().contentEquals(successMessage);
    }
}
