package com.salmon.test.framework.helpers.screenshot_helper;

import com.salmon.test.framework.helpers.Props;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.salmon.test.framework.helpers.WebDriverHelper.BS_SessionId;


class BrowserStackhelper {
    private static final Logger log = LoggerFactory.getLogger(BrowserStackhelper.class);
    private static Response response;
    private static String bsVideoLink;

    public String getVideoLink() {
        try {
            RestAssured.baseURI = "https://api.browserstack.com/automate/sessions/";
            RequestSpecification httpRequest = RestAssured.given()
                    .auth().preemptive().basic(Props.getProp("bsusername"), Props.getProp("bsaccesskey"))
                    .param("sessionId", BS_SessionId);
            log.info("Hitting API to get the BrowserStack Video link");
            response = httpRequest.request(Method.GET);
            log.info("Request hit successfully and response code is-->" + response.getStatusCode());
            JsonPath jsonpath = new JsonPath(response.body().asString());
            bsVideoLink = jsonpath.get("automation_session.public_url").toString();
            bsVideoLink = bsVideoLink.substring(1, bsVideoLink.length() - 1);
        } catch (Exception e) {
            log.error("Some exception occurred while hitting the request->" + e.getMessage());
            e.printStackTrace();
        }

        return bsVideoLink;
    }
}