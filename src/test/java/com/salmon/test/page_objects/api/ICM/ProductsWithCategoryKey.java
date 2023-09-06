package com.salmon.test.page_objects.api.ICM;

import com.salmon.test.framework.helpers.Props;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class ProductsWithCategoryKey {

    private String baseuri = Props.getProp("icm.base.uri");
    private Response response;
    private ArrayList<String> productNames;

    private static final Logger log = LoggerFactory.getLogger(ProductsWithCategoryKey.class);

    public boolean hitRequestWithCategoryKeyAs(String categoryKey) {
        boolean flag = false;

        try {
            RestAssured.baseURI = baseuri;

            RequestSpecification httpRequest = RestAssured.given();

            log.info("Going to the hit the URL which is-->>" + baseuri + "/-/categories/" + categoryKey + "/products");

            response = httpRequest.request(Method.GET, "/-/categories/" + categoryKey + "/products");

            log.info("Successfully hit the api and received response ");

            log.info("Status Line is : " + response.getStatusLine());

            flag = true;
            //System.out.println("Response Body : " + response.getBody().asString());

        } catch (Exception e) {
            log.error("Some exception occurred while hitting the request please check-->>" + e.getMessage());
            e.printStackTrace();
        }

        return flag;

    }

    public int getResponseCode() {
        if (response.getStatusCode() == 200) {
            log.info("Api hit is success returning response code which is " + response.getStatusCode());
            return response.getStatusCode();
        } else {
            log.info("Some error occurred while fething response code please check if api is hit before ?");
            return -1;
        }
    }

    public int getNumberOfProductsReturned() {
        Object[] list = response.getBody().jsonPath().getList("elements.uri").toArray();
        return list.length;
    }

    public ArrayList<String> getAllProductNames() {
        Object[] list = response.getBody().jsonPath().getList("elements.uri").toArray();

        productNames = new ArrayList<>();

        for (Object e : list) {
            String[] k = e.toString().split("/");
            productNames.add(k[k.length - 1]);
        }

        return productNames;
    }


}
