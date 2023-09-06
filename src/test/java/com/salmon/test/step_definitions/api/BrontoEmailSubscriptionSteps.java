package com.salmon.test.step_definitions.api;

import com.salmon.test.page_objects.api.ICM.BrontoEmailSubscription;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.testng.Assert;

public class BrontoEmailSubscriptionSteps {
    private BrontoEmailSubscription brontoEmailSubscription;

    public BrontoEmailSubscriptionSteps() {
        brontoEmailSubscription = new BrontoEmailSubscription();
    }


    @Then("^hit the bronto email subscription api$")
    public void hit_the_bronto_email_subscription_api() {
        Assert.assertTrue(brontoEmailSubscription.hitRequest(), "Unable to hit the bronto email subscription request ");
    }

    @Then("^response of bronto api should be equal to (\\d+)$")
    public void response_of_bronto_api_should_be_equal_to(int code) {
        Assert.assertEquals(brontoEmailSubscription.getResponseCode(), code, "Response codes are mismatched");
    }

    @And("^in response link should be there against key \"BronotoNewsletterURL\"$")
    public void in_response_link_should_be_there_against_key() {
        Assert.assertTrue(!brontoEmailSubscription.getValueForKeyBrontoNewsLetter().isEmpty(),
                "Unable to find the value against the key in response it is showing-->"+brontoEmailSubscription.getValueForKeyBrontoNewsLetter());
    }

    @And("^hit the link response should be (\\d+)$")
    public void hit_the_link_response_should_be(int code) {
        Assert.assertEquals(brontoEmailSubscription.hitBrontoNewsLetterUrl(),code,"Unable to verify that url has been constructed correctly showing status code-->"+brontoEmailSubscription.hitBrontoNewsLetterUrl());
    }


}
