package com.salmon.test.step_definitions.api;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.page_objects.api.fraud.instalmentController.PayFullBalance;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.testng.Assert;

public class PayFullBalanceSteps {
	private final PayFullBalance payFullBalance;
		
	public PayFullBalanceSteps() {
		payFullBalance = new PayFullBalance();
	}

	@Then("^hit POST Pay full balance to update instalment plan$")
	public void hitPostPayFullBalance() throws InterruptedException{
		Thread.sleep(2000);
		Assert.assertTrue(payFullBalance.hitRequest(), "Unable to hit POST Pay full balance please check logs");
		Thread.sleep(2000);
	}

	@Then("^response of pay full balance should be (\\d+)$")
	public void responsePayFullBalance(int code) {
		Assert.assertEquals(payFullBalance.getResponseCode(), code);
	}

	@Then("^verify instalment plan not found message \"([^\"]*)\"$")
	@And("^verify success message of pay full balance \"([^\"]*)\"$")
	public void verifySuccessMessage(String successMessage) {
		String sMessage = Props.getProp(successMessage);
		Assert.assertEquals(payFullBalance.getMessageFromResponse(),sMessage,"Unable to verify success message please check logs");
	}

}
