package com.salmon.test.page_objects.api.fraud.instalmentController;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.step_definitions.api.InstalmentAndPayment.InstallmentAndPaymentSteps;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.restassured.http.ContentType;
import io.restassured.http.Method;


public class GetInstalmentPlanDetails {
    private String baseUri = Props.getProp("get.environment");
    private Response response;
    private int pageNumber;
    private String sortOrder;

    private static final Logger log = LoggerFactory.getLogger(GetInstalmentPlanDetails.class);

    public boolean hitRequest() {
        boolean flag = false;
        String url = baseUri + "/instalmentPlansDetails";
        try {
            RestAssured.baseURI = url;
            RequestSpecification httpRequest = RestAssured.given();
            httpRequest.headers("accept", "application/json");
            httpRequest.contentType(ContentType.JSON);
            httpRequest.auth().basic(Props.getProp("ipms.auth.service.username"), Props.getProp("ipms.auth.service.password"));
            httpRequest.queryParams("planModifiedDateFrom", InstallmentAndPaymentSteps.startingPlanDate);
            httpRequest.queryParams("planModifiedDateTo", InstallmentAndPaymentSteps.endingPlanDate);
            httpRequest.queryParams("pageNumber", InstallmentAndPaymentSteps.pNumber);
            httpRequest.queryParams("sortOrder", InstallmentAndPaymentSteps.sOrder);
            log.info("Going to hit the request Get Instalment Plan details Api to get the data list having the uri-->>" + url);
            response = httpRequest.request(Method.GET);
            log.info("Request hit successfully response code is-->" + response.getStatusCode());
            flag = true;

        } catch (Exception e) {
            log.error("Unable to hit the GET/instalmentPlansDetails api-->" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean hitRequestWithWrongParameters() {
        boolean flag = false;
        String url = baseUri + "/instalmentPlansDetails";
        try {
            RestAssured.baseURI = url;
            RequestSpecification httpRequest = RestAssured.given();
            httpRequest.headers("accept", "application/json");
            httpRequest.contentType(ContentType.JSON);
            httpRequest.auth().basic(Props.getProp("ipms.auth.service.username"), Props.getProp("ipms.auth.service.password"));
            httpRequest.queryParams("planModifiedDateFrom", InstallmentAndPaymentSteps.endingPlanDate);
            httpRequest.queryParams("planModifiedDateTo", InstallmentAndPaymentSteps.startingPlanDate);
            httpRequest.queryParams("pageNumber", InstallmentAndPaymentSteps.pNumber);
            httpRequest.queryParams("sortOrder", InstallmentAndPaymentSteps.sOrder);
            log.info("Going to hit the request Get Instalment Plan details Api to get the data list having the uri-->>" + url);
            response = httpRequest.request(Method.GET);
            log.info("Request hit successfully response code is-->" + response.getStatusCode());
            flag = true;

        } catch (Exception e) {
            log.error("Unable to hit the GET/instalmentPlansDetails api-->" + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public int getResponseCode() {
        return response.getStatusCode();
    }

    public String getMessageFromResponse() {
        log.info("Message from the response is-->" + response.jsonPath().getString("message"));
        return response.jsonPath().getString("message");
    }

    public String getResponseBody() {
        return response.getBody().asString();
    }

    public void setPageNumber(int data) {
        pageNumber = data;
        log.info("Successfully set the page Number as-->" + pageNumber);
    }

    public void setSortOrder(String sOrder) {
        sortOrder = sOrder;
        log.info("Successfully set the sorting-->" + sOrder);
    }

    public int getDataListSize() {
        return response.jsonPath().getList("instalmentPlanList").size();
    }

    public int getValueOfKeyTotalRecords() {
        int value = Integer.parseInt(response.jsonPath().get("totalRecords").toString());
        log.info("Value against key \"totalRecords\" is-->" + value);
        return value;
    }
}
