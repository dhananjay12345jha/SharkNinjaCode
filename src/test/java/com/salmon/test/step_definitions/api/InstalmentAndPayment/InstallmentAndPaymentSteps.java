package com.salmon.test.step_definitions.api.InstalmentAndPayment;

import com.salmon.test.enums.PermittedCharacters;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.utils.IcmOrderImportExportService;
import com.salmon.test.framework.helpers.utils.RandomGenerator;
import com.salmon.test.page_objects.api.fraud.fraudController.PutFraudHolds;
import com.salmon.test.page_objects.api.fraud.instalmentController.*;
import com.salmon.test.page_objects.api.fraud.orderController.PostCancelOrRefundPlan;
import com.salmon.test.page_objects.api.fraud.paymentController.PostCapturePayment;
import com.salmon.test.page_objects.api.fraud.paymentController.PostPartPayment;
import cucumber.api.java.bs.A;
import com.salmon.test.page_objects.api.fraud.pojo.instalmentController.Invoices;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class InstallmentAndPaymentSteps {

    private IcmOrderImportExportService icmOrderImportExportService;
    private PutFraudHolds putFraudHolds;
    private GetInstalmentPlan getInstalmentPlan;
    private PostCapturePayment postCapturePayment;
    private PostCancelOrRefundPlan postCancelOrRefundPlan;
    private PostPartPayment postPartPayment;
    private PostSendUpdateCardLinkEmail postSendUpdateCardLinkEmail;
    private int totalAmountDue, totalAmountPaid, paymentToBeDone;
    private List<String> transactionId = new ArrayList<>();

    private FileReader reader;
    private Properties properties = new Properties();
    private final GetInstalmentPlanDetails getInstalmentPlanDetails;
    private final UpdateInstalmentPlan updateInstalmentPlan;

    private SoftAssert softAssert=new SoftAssert();

    public static String startingPlanDate;
    public static String endingPlanDate;
    public static int pNumber;
    public static String sOrder;

    public InstallmentAndPaymentSteps() {
        icmOrderImportExportService = new IcmOrderImportExportService();
        putFraudHolds = new PutFraudHolds();
        getInstalmentPlan = new GetInstalmentPlan();
        postCapturePayment = new PostCapturePayment();
        postCancelOrRefundPlan = new PostCancelOrRefundPlan();
        postSendUpdateCardLinkEmail = new PostSendUpdateCardLinkEmail();
        this.getInstalmentPlanDetails = new GetInstalmentPlanDetails();
        this.updateInstalmentPlan = new UpdateInstalmentPlan();
        postPartPayment = new PostPartPayment();
    }

    private void refreshPropertyFile() {
        try {
            reader = new FileReader(System.getProperty("user.dir") + Props.getProp("file.path"));
            properties.load(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Then("^run the icm import and export service select checkbox for job defined by key \"icm.importExport.job.name\"$")
    public void run_import_export_service_and_select_checkbox() {
        Assert.assertTrue(icmOrderImportExportService.runOrderImportExportService(),
                "Unable to run import and export icm service please check logs");
    }

    @Then("^set the parameters of PUT FraudHoldsOrderUpdate api action as \"([^\"]*)\",comment as \"([^\"]*)\" and updatedBy \"([^\"]*)\"$")
    public void set_action_value_and_hit_api_for_order_number(String action, String comment, String updatedBy) {
        putFraudHolds.setAction(action);
        putFraudHolds.setComment(comment);
        putFraudHolds.setUpdatedBy(updatedBy);
    }

    @Then("^hit PUT FraudHoldsOrderUpdate api to approve the fraud holds against the order \"([^\"]*)\"$")
    public void hit_put_fraud_hold_api_having_orderIs(String orderId) throws InterruptedException {
        refreshPropertyFile();
        List<String> list = new ArrayList<>();
        list.add(properties.getProperty(orderId));
        putFraudHolds.setOrderIdList(list);
        Thread.sleep(2000);
        Assert.assertTrue(putFraudHolds.hitRequest(), "Unable to hit PUT FraudHolds request please check logs");
        Thread.sleep(2000);
    }

    @Then("^validate status code should be (\\d+) and response message should be \"([^\"]*)\"$")
    public void validate_put_fraud_holds_api_response(int code, String message) {
        Assert.assertEquals(putFraudHolds.getResponseCode(), code);
        Assert.assertEquals(putFraudHolds.getMessageFromResponse(), message);
    }

    @Then("^explicitly wait for (\\d+) seconds so that instalment plan gets updated at backend$")
    public void explicity_wait_for_instalmentPlan_updated_at_backend(int seconds) {
        try {
            Thread.sleep((seconds*1000));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Given("^authentication token and order id has been set and Get Instalment Plan api has been hit$")
    public void set_authentication_token_and_orderId_And_hit_api() throws InterruptedException {
        Thread.sleep(2000);
        Assert.assertTrue(getInstalmentPlan.hitRequest());
        Thread.sleep(2000);
    }

    @Then("^from response capture the transaction id of first instalment and set as transaction id in PostCapturePayment Request$")
    public void from_response_capture_transactionId_and_set_it_in_PostCapturePayment() {
        String transactionId = getInstalmentPlan.getResponseAsAClass().getData().getInvoices().get(0).getTransactionId();
        postCapturePayment.setTransactionId(transactionId);
    }

    @And("^set the value of order id as \"([^\"]*)\" and request transaction id as random number$")
    public void set_orderId_transactionId_in_capture_payment_api(String orderId) {
        postCapturePayment.setOrderId(properties.getProperty(orderId));
        postCapturePayment.setRequestTransactionId(Integer.parseInt(RandomGenerator.random(6, PermittedCharacters.NUMERIC)));
    }

    @Then("^hit the Post Capture Payment request$")
    public void hit_post_capture_payment_api() throws InterruptedException {
        Thread.sleep(2000);
        Assert.assertTrue(postCapturePayment.hitRequest());
        Thread.sleep(2000);
    }

    @Then("^validate in response (\\d+) should be there and message \"([^\"]*)\"$")
    public void validate_code_and_message_of_capture_payment_request(int code, String message) {
        Assert.assertEquals(postCapturePayment.getResponseCode(), code);
        Assert.assertEquals(postCapturePayment.getMessageFromResponse(), message);
    }

    @Then("^validate in response plan status should be \"([^\"]*)\"$")
    public void validate_respone_plan_status_of_getinstalmentPlanApi(String expected) {
        Assert.assertEquals(getInstalmentPlan.getResponseAsAClass().getData().getPlanStatus(), expected);
    }

    @And("^first instalment status should be \"([^\"]*)\"$")
    public void validate_first_instalmet_status_of_getinstalmentApiResponse(String expected) {
        Assert.assertEquals(getInstalmentPlan.getResponseAsAClass().getData().getInvoices().get(0).getStatus(), expected);
    }

    @And("^value of \"totalPaidInstalments\" should be (\\d+)$")
    public void validate_totalPaid_instalment_status_of_getinstalmentApiResponse(int expected) {
        Assert.assertEquals(getInstalmentPlan.getResponseAsAClass().getData().getTotalPaidInstalments(), expected);
    }

    @And("^value of \"totalAmountPaid\" should be (\\d+)$")
    public void value_of_totalAmountPaid_of_getinstalmentApiResponse(int expected) {
        Assert.assertEquals(getInstalmentPlan.getResponseAsAClass().getData().getTotalAmountPaid(), expected);
    }

    @And("^value of \"nextDueAmount\" should be (\\d+)$")
    public void value_of_nextDueAmount_should_be(int expected) {
        Assert.assertEquals(getInstalmentPlan.getResponseAsAClass().getData().getNextDueAmount(), expected, "Next due amount value is not matched");
    }


    @And("^value of \"totalAmountDue\" should be (\\d+)$")
    public void value_of_totalAmountDue_should_be(int expected) {
        Assert.assertEquals(getInstalmentPlan.getResponseAsAClass().getData().getTotalAmountDue(), expected,
                "TotalAmountDue is not matched");
    }

    @And("^value of \"totalAmountPaid\" should be greater than zero$")
    public void value_of_totalAmountPaid_of_getInstalmentApiResponse_after_payment() {
        int value = getInstalmentPlan.getResponseAsAClass().getData().getTotalAmountPaid();
        Assert.assertTrue(value > 0, "Total amount value showing is-->" + value + " ideally it should not be zero after first instalment-->");
    }

    @And("^capture the \"transactionId\" where status is \"PAID\" in invoices list$")
    public void capture_transactionId_where_status_is_PAID() {
        Iterator<Invoices> iterator = getInstalmentPlan.getResponseAsAClass().getData().getInvoices().iterator();

        while (iterator.hasNext()) {
            Invoices invoices = iterator.next();

            if (invoices.getStatus().equalsIgnoreCase("PAID")) ;
            {
                transactionId.add(invoices.getTransactionId());
            }

        }
    }

    @And("^search by the \"transactionId\" captured above having status as \"REFUND-IN-PROGRESS\"$")
    public void validate_transactionIds_should_have_status_REFUND_IN_PROGRESS() {

        SoftAssert softAssert = new SoftAssert();

        for (int i = 0; i < transactionId.size(); i++) {
            boolean flag = false;
            Iterator<Invoices> iterator = getInstalmentPlan.getResponseAsAClass().getData().getInvoices().iterator();
            while (iterator.hasNext()) {
                Invoices invoices = iterator.next();

                if (invoices.getStatus().equalsIgnoreCase("REFUND-IN-PROGRESS")) ;
                {
                    if (invoices.getTransactionId().equalsIgnoreCase(transactionId.get(i))) {
                        flag = true;
                    }
                }

            }

            softAssert.assertTrue(flag, "Unable to find the status as REFUND-IN-PROGRESS against transacation Id-->" + transactionId.get(i));
        }

        softAssert.assertAll();
    }


    //---------------POST Part Payment API--------------------//

    @And("^calculating the payment amount depending upon the \"nextDueAmount\"$")
    public void calculate_how_much_payment_inMultiplesOfHundered() {
        int amountDueFromGetInstalment = getInstalmentPlan.getResponseAsAClass().getData().getNextDueAmount();
        int numberOfDigits = postPartPayment.calculateHowManyDigitsAreThereInNumber(amountDueFromGetInstalment);
        paymentToBeDone = 1;
        for (int k = 0; k < numberOfDigits - 1; k++) {
            paymentToBeDone = (paymentToBeDone * 10);
        }
    }

    @And("^saving the values of \"totalAmountDue\" and \"totalAmountPaid\" for further validation$")
    public void saving_the_values() {
        totalAmountDue = getInstalmentPlan.getResponseAsAClass().getData().getTotalAmountDue();
        totalAmountPaid = getInstalmentPlan.getResponseAsAClass().getData().getTotalAmountPaid();
    }

    @Then("^hit the POST partPayment Api to do partial payment with amount calculated above and order Id \"([^\"]*)\"$")
    public void hit_post_partPayment_api(String orderId) throws InterruptedException {
        postPartPayment.setAmount(paymentToBeDone);
        refreshPropertyFile();
        postPartPayment.setOrderId(properties.getProperty(orderId));
        Thread.sleep(2000);
        Assert.assertTrue(postPartPayment.hitRequest(), "Unable to hit the request ");
        Thread.sleep(2000);
    }

    @Then("^response code of POST partPayment api should be (\\d+) and message shown should be \"([^\"]*)\"$")
    public void validate_responseCode_and_message_of_post_part_payment_api(int code, String message) {
        Assert.assertEquals(postPartPayment.getResponseCode(), code, "POST Part payment api status code mismatch");

        Assert.assertEquals(postPartPayment.getMessageFromResponse(), message);
    }

    @Then("^validate \"totalAmountDue\" should get reduced by the amount calculated above$")
    public void validate_the_total_amount_due() {
        int expected = totalAmountDue - paymentToBeDone;
        int actual = getInstalmentPlan.getResponseAsAClass().getData().getTotalAmountDue();
        Assert.assertEquals(actual, expected, "Unable to do part payment please check logs Part Payment API not working ");
    }

    @Then("^validate \"totalAmountPaid\" should get increased by the amount calculated above$")
    public void validate_totalAmountPaid() {
        int expected = totalAmountPaid + paymentToBeDone;
        int actual = getInstalmentPlan.getResponseAsAClass().getData().getTotalAmountPaid();
        Assert.assertEquals(actual, expected, "There is mismatch in previous (total amount paid + part payment done) and in api response");

    }

    @Then("^hit the POST partPayment Api to do payment having amount greater than totalAmountDue and order Id \"([^\"]*)\"$")
    public void do_payment_which_is_greater_than_totalAmountDue(String orderId) throws InterruptedException {
        postPartPayment.setAmount(totalAmountDue + 1);
        refreshPropertyFile();
        postPartPayment.setOrderId(properties.getProperty(orderId));
        Thread.sleep(2000);
        Assert.assertTrue(postPartPayment.hitRequest(), "Unable to hit the request ");
        Thread.sleep(2000);
    }

    @Then("^error code (\\d+) should be there along with message \"([^\"]*)\"$")
    public void validate_error_code_and_message_for_partPaymentApi(int code, String message) {
        Assert.assertEquals(postPartPayment.getResponseCode(), code);
        String expected = message.split("amount")[1];
        Assert.assertTrue(postPartPayment.getMessageFromResponse().contains(expected), "Actuals String-->" + postPartPayment.getMessageFromResponse()
                + ", Actual string should contains expected-->>" + expected);
    }

    @Then("^hit the POST partPayment Api to do payment having amount zero and order Id \"([^\"]*)\"$")
    public void doing_part_payment_having_amount_zero(String orderId) throws InterruptedException {
        postPartPayment.setAmount(0);
        refreshPropertyFile();
        postPartPayment.setOrderId(properties.getProperty(orderId));
        Thread.sleep(2000);
        Assert.assertTrue(postPartPayment.hitRequest(), "Unable to hit the request ");
        Thread.sleep(2000);
    }


    @Then("^error code (\\d+) should be there and message \"([^\"]*)\"$")
    public void validate_error_code_and_message_when_doing_partyPayment_as_zero(int code, String expectedMessage) {
        String actual = postPartPayment.getResponseBody().replace("{", "")
                .replace("\"", "").replace(":", " ").replace("}", "");

        Assert.assertEquals(postPartPayment.getResponseCode(), code);
        Assert.assertEquals(actual, expectedMessage);
    }


    //    ------------- Instalment Plan Details steps --------------------------


    @Given("^The plan modified starting date is set$")
    public void setPlanModifiedStartingDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -10);
        Date futureDate = calendar.getTime();
//        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat crunchifyFormat = new SimpleDateFormat("MMM dd yyyy HH:mm:ss.SSS zzz");
        String currentTime = crunchifyFormat.format(futureDate);
        long epochTime = 0;
        try {

            // parse() parses text from the beginning of the given string to produce a date.
            Date date = crunchifyFormat.parse(currentTime);

            // getTime() returns the number of milliseconds since January 1, 1970, 00:00:00 GMT represented by this Date object.
            epochTime = date.getTime();
            epochTime = epochTime / 1000;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        startingPlanDate = String.valueOf(epochTime);
//        startingPlanDate = Props.getProp(startingDate);
    }

    @And("^the plan modified ending date is set$")
    public void setPlanModifiedEndingDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -5);
        Date futureDate = calendar.getTime();
//        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat crunchifyFormat = new SimpleDateFormat("MMM dd yyyy HH:mm:ss.SSS zzz");
        String currentTime = crunchifyFormat.format(futureDate);
        long epochTime = 0;
        try {

            // parse() parses text from the beginning of the given string to produce a date.
            Date date = crunchifyFormat.parse(currentTime);

            // getTime() returns the number of milliseconds since January 1, 1970, 00:00:00 GMT represented by this Date object.
            epochTime = date.getTime();
            epochTime = epochTime / 1000;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        endingPlanDate = String.valueOf(epochTime);
    }

    @And("^Page number is set to (\\d+)$")
    public void setPageNumber(int pageNumber) {
        pNumber = pageNumber;
    }

    @And("^sorting order is set to \"([^\"]*)\"$")
    public void setSortingOrder(String sortingOrder) {
        sOrder = sortingOrder;
    }

    @When("^GET Instalment Plan details api has been hit$")
    public void get_blacklists_api_has_been_hit() throws InterruptedException {
        Thread.sleep(2000);
        Assert.assertTrue(getInstalmentPlanDetails.hitRequest(), "Unable to hit request GET BlackList");
        Thread.sleep(2000);
    }

    @When("^GET Instalment Plan details api has been hit with incorrect Date$")
    public void get_blacklists_api_has_been_hit_with_wrong_parameters() throws InterruptedException {
        Thread.sleep(2000);
        Assert.assertTrue(getInstalmentPlanDetails.hitRequestWithWrongParameters(), "Unable to hit request GET BlackList");
        Thread.sleep(2000);
    }

    @Then("^response of Instalment plan details api should be (\\d+)$")
    public void response_of_the_api_should_be(int code) {
        Assert.assertEquals(getInstalmentPlanDetails.getResponseCode(), code);
    }

    @And("^data list returned in response of instalment plan details should be greater than zero$")
    public void data_list_returned_in_response_should_be_greater_than_zero() {
        int size = getInstalmentPlanDetails.getDataListSize();
        Assert.assertTrue(size > 0, "Data List size is not greater than zero which is-->" + size);
    }

    @And("^value of the key \"([^\"]*)\" in instalment details API should be greater than zero$")
    public void value_of_the_key_should_be_greater_than_zero(String key) {
        int value = getInstalmentPlanDetails.getValueOfKeyTotalRecords();
        Assert.assertTrue(value > 0, "Value size is not greater than zero which is-->" + value);
    }

    //    ----------------- Update Instalment Plan -----------------------------
    @Then("^set the value of first instalment amount to \"([^\"]*)\"$")
    public void setFirstInstalmentAmount(String firstInstalmentAmount) {
        String fInstalmentAmount = Props.getProp(firstInstalmentAmount);
        updateInstalmentPlan.setFirstInstalmentAmount(fInstalmentAmount);
    }

    @And("^set the value of number of instalments to \"([^\"]*)\"$")
    public void setNumberOfInstalments(String numberOfInstalments) {
        String Instalments = Props.getProp(numberOfInstalments);
        updateInstalmentPlan.setNumberOfInstalment(Instalments);
    }

    @And("^set the value of subsequent instalment amount to \"([^\"]*)\"$")
    public void setSubsequentInstalmentAmount(String subsequentInstalmentAmount) {
        String subsequentInstalments = Props.getProp(subsequentInstalmentAmount);
        updateInstalmentPlan.setSubsequentInstalmentAmount(subsequentInstalments);
    }

    @And("^hit POST Update Instalment Plan to update instalment plan$")
    public void hitPostUpdateInstalmentPlan() throws InterruptedException {
        Thread.sleep(2000);
        Assert.assertTrue(updateInstalmentPlan.hitRequest(), "Unable to hit POST update instalment plan request please check logs");
        Thread.sleep(2000);
    }

    @Then("^response of update Instalment plan should be (\\d+)$")
    public void responseUpdateInstalmentPlan(int code) {
        Assert.assertEquals(updateInstalmentPlan.getResponseCode(), code);
    }

    @And("^verify success message of instalment update \"([^\"]*)\"$")
    public void verifySuccessMessage(String successMessage) {
        String sMessage = Props.getProp(successMessage);
        Assert.assertTrue(updateInstalmentPlan.verifySuccessMessage(sMessage), "Unable to verify success message please check logs");
    }

    @Then("^verify instalment plan gets updated$")
    public void verifyInstalmentPlanUpdated() {
        Iterator<Invoices> invoicesIterator = getInstalmentPlan.getResponseAsAClass().getData().getInvoices().iterator();
        Assert.assertTrue(invoicesIterator.next().getAmountPaid() == Integer.parseInt(Props.getProp("instalment.first.amount")), "First Instalment not updated");
        while (invoicesIterator.hasNext()) {
            Assert.assertTrue(invoicesIterator.next().getAmountPaid() == Integer.parseInt(Props.getProp("instalment.subsequent.amount")), "Subsequent Instalments not updated");
        }
    }

    //--------------------------- Cancel Or Refund Order Steps-----------------------------//

    @And("^fetch the \"totalOrderAmount\" from the response of GET InstalmentPlan Api and set as \"refundAmount\" inside body of Post cancelOrRefund Api$")
    public void fetch_totalOrderAmount_value_and_set_this_value_in_postCancelOrRefundOrderApi() {
        int amount = getInstalmentPlan.getResponseAsAClass().getData().getOrder().getTotalOrderAmount();
        postCancelOrRefundPlan.setRefundAmount(amount);
    }

    @And("^set the value of \"nonRefundAmount\" to (\\d+)$")
    public void set_value_of_nonRefundAmount(int amount) {
        postCancelOrRefundPlan.setNonRefundAmount(amount);
    }

    @And("^set the value of \"orderId\" as \"([^\"]*)\"$")
    public void set_value_of_orderId(String orderId) {
        refreshPropertyFile();
        postCancelOrRefundPlan.setOrderId(properties.getProperty(orderId));
    }

    @And("^set the value of \"refundId\" any numeric value of 6 digits$")
    public void set_value_of_refundId() {
        postCancelOrRefundPlan.setRefundId(RandomGenerator.random(6, PermittedCharacters.NUMERIC));
    }

    @And("^set the value of \"saveSaleRefund\" as false$")
    public void set_the_value_of_saveSaleRefund_as_false() {
        postCancelOrRefundPlan.setSaveSaleRefund(false);
    }

    @And("^set the value of \"status\" as \"([^\"]*)\"$")
    public void set_value_of_status(String status) {
        postCancelOrRefundPlan.setStatus(status);
    }

    @Then("^hit the Post Cancel Or Refund Plan api$")
    public void hit_post_cancelOrRefund_api() throws InterruptedException {
        Thread.sleep(2000);
        Assert.assertTrue(postCancelOrRefundPlan.hitRequest(), "Unable to hit request please check logs");
        Thread.sleep(2000);
    }

    @Then("^in response (\\d+) should be there along with message \"([^\"]*)\"$")
    public void validate_response_code_and_message_of_postCancelOrRefund_api(int code, String message) {
        Assert.assertEquals(postCancelOrRefundPlan.getResponseCode(), code);
        Assert.assertEquals(postCancelOrRefundPlan.getMessageFromResponse(), message);
    }

    //----------------------POST Send Update Card Link Email API steps----------------------//

    @When("^set the \"orderId\" value as \"([^\"]*)\"$")
    public void set_orderId_In_postSendUpdateCardLinkApi(String orderId) {
        refreshPropertyFile();
        postSendUpdateCardLinkEmail.setOrderId(properties.getProperty(orderId));
    }

    @Then("^hit the Post SendUpdateCardLink Api$")
    public void hit_PostSendUpdateCardLink_Api() throws InterruptedException {
        Thread.sleep(2000);
        Assert.assertTrue(postSendUpdateCardLinkEmail.hitRequest());
        Thread.sleep(2000);
    }

    @Then("^status code in response should be (\\d+)$")
    public void validate_Status_Response_Of_api(int code) {
        softAssert.assertEquals(postSendUpdateCardLinkEmail.getResponseCode(), code);
//        Assert.assertEquals(postSendUpdateCardLinkEmail.getResponseCode(), code);
    }

    @And("^Message shown should be \"([^\"]*)\" \"([^\"]*)\"$")
    public void validate_message_from_PostSendUpdateCardLinkEmail_response(String message, String emailId) {
        String expected = message + " " + properties.getProperty(emailId);
//        Assert.assertEquals(postSendUpdateCardLinkEmail.getMessageFromResponse(), expected);
        softAssert.assertEquals(postSendUpdateCardLinkEmail.getMessageFromResponse(), expected);
        softAssert.assertAll();
    }

    @Then("^verify all instalments paid$")
    public void verifyInstalmentsPaid() {
        Assert.assertTrue(getInstalmentPlan.getResponseAsAClass().getData().getTotalLeftInstalments() == 0, "Instalment detail not updated");
        Assert.assertTrue(getInstalmentPlan.getResponseAsAClass().getData().getTotalNumberOfInstalments() == getInstalmentPlan.getResponseAsAClass().getData().getTotalPaidInstalments(), "Instalment detail not updated");
//        Iterator<Invoices> invoicesIterator = getInstalmentPlan.getResponseAsAClass().getData().getInvoices().iterator();
//        while(invoicesIterator.hasNext()) {
//            Assert.assertTrue(invoicesIterator.next().getStatus().contentEquals("PAID"), "instalments not updated");
//        }
    }


}