package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import static com.salmon.test.enums.PermittedCharacters.ALPHABETS;
import static com.salmon.test.framework.helpers.utils.RandomGenerator.random;
import java.util.List;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class RegisterMyGuaranteePage extends PageObject {

	private final By heading = By.xpath("//div[@class='product-registration-left']/h1");
	private final By registrationForm = By.xpath("//form[@id='ProductRegistrationForm']");
	private final By emailGuaranteeForm = By.cssSelector("#ProductRegistrationForm_Email");
	private final By addressLookup=By.xpath("//*[@id=\"js-lookup-entry\"]/span");
	private final By registerProductPostcode = By.xpath("//input[@id='regproduct-search-bar']");
    private final By registerProductPostcodeAfterError = By.xpath("//input[@id='ProductRegistrationForm_PostalCode']");
	private final By prefilledPostcode = By.xpath("//input[@id='ProductRegistrationForm_PostalCode']");
	private final By firstName = By.cssSelector("#ProductRegistrationForm_FirstName");
	private final By lastName = By.cssSelector("[id='ProductRegistrationForm_LastName']");
	private final By productType = By.cssSelector("[id='ProductRegistrationForm_ProductType']");
	private final By modelNumber = By.cssSelector("[id='ProductRegistrationForm_ProductModelNumber']");
	private final By purchaseDate = By.cssSelector("[id='ProductRegistrationForm_PurchaseDate']");
	private final By todayDate = By.xpath("//td[@class='today day']");
	private final By purchaseLocation = By.cssSelector("[id='ProductRegistrationForm_PurchaseLocation']");
	private final By checkboxWhatsNew = By.xpath("//div[@class='form-group checkbox col-sm-12 '][1]/label");
	private final By checkboxOffers = By.xpath("//div[@class='form-group checkbox col-sm-12 '][2]/label");
	private final By checkboxTipsAndArticles = By.xpath("//div[@class='form-group checkbox col-sm-12 '][3]/label");
	//private final By submitBtn = By.cssSelector("[id='product-registration-button']");
	private static final By submitBtn=  By.xpath("//*[@id=\"product-registration-button\"]");
	private final By confirmText = By.xpath("//div[@class='product-registration-left']/p[1]");
	private final By warrantyInfoMailText = By.xpath("//div[@class='product-registration-left']/p[2]");
	private final By errorEmptyEmailText = By.xpath("//div[@id='emailID']/small[@class='help-block'][2]");
	private final By errorEmptyPostalCode = By.xpath("//input[@id='ProductRegistrationForm_PostalCode']/following-sibling::small[@data-bv-result='INVALID']");
	private final By errorEmptyFirstName = By.xpath("//small[@data-bv-for='ProductRegistrationForm_FirstName'][1]");
	private final By errorEmptyLastName = By.xpath("//small[@data-bv-for='ProductRegistrationForm_LastName'][1]");
	private final By errorEmptyProductType = By.xpath("//small[@data-bv-for='ProductRegistrationForm_ProductType'][1]");
	private final By errorEmptyModelNumber = By.xpath("//small[@data-bv-for='ProductRegistrationForm_ProductModelNumber'][1]");
	private final By errorEmptyPurchaseDate = By.xpath("//small[@data-bv-for='ProductRegistrationForm_PurchaseDate'][1]");
	private final By errorEmptyPurchaseLocation = By.xpath("//small[@data-bv-for='ProductRegistrationForm_PurchaseLocation'][1]");
	private final By errorInvalidEmailText = By.xpath("//div[@id='emailID']/small[@class='help-block'][1]");
	private final By errorInvalidFirstName = By.xpath("//small[@data-bv-for='ProductRegistrationForm_FirstName'][3]");
	private final By errorInvalidLastName = By.xpath("//small[@data-bv-for='ProductRegistrationForm_LastName'][3]");
	private final By createAccountCheckbox = By.xpath("//div[@class='form-group checkbox col-xs-12']/label");
	private final By password = By.xpath("//input[@id='create-account-password']");
	private final By accountExistMessage = By.xpath("//div[@class='product-registration-left']/p[3]");
	//product overview

	private static final Logger log = LoggerFactory.getLogger(RegisterMyGuaranteePage.class);
	
	//For filling form details on Register Guarantee page - we are using Random method from Random Generator class in helpers.utils
	private String email = Props.getProp("login.email");
	private String registerProductPostCodeData = Props.getProp("register.guarantee.valid.postcode");
	private String registerProductPostCodeDataAustria = Props.getProp("register.guarantee.valid.postcode.Austria");
	private String FirstNameData = random(6, ALPHABETS);
	private String LastNameData = random(6, ALPHABETS);
	private String mNumber = Props.getProp("product.specificSKU");
	
	public String getRegisterProductHeading() {
		  return waitForExpectedElement(heading).getText();
	  }
	
	public By getRegistrationForm() {
		return registrationForm;
	}
	
	public void fillRegisterMyGuaranteeForm() throws InterruptedException {
		enterEmail(email);
		clickEnterAdressLookup();
		enterPostcode(registerProductPostCodeData);
		enterFirstName(FirstNameData);
		enterLastName(LastNameData);
		selectProductType();
		enterModelNumber(mNumber);
		selectPurchaseDate();
		selectPurchaseLocation();
		checkHearAboutCheckboxes();
	}
	public void fillRegisterMyGuaranteeFormAustria() throws InterruptedException {
		enterEmail(email);
		clickEnterAdressLookup();
		enterPostcodeAustria(registerProductPostCodeDataAustria);
		enterFirstName(FirstNameData);
		enterLastName(LastNameData);
		selectProductType();
		enterModelNumber(mNumber);
		selectPurchaseDate();
		selectPurchaseLocation();
		checkHearAboutCheckboxes();
	}

	public void fillRegisterMyGuaranteeFormAfterLogin() {
		selectProductType();
		enterModelNumber(mNumber);
		selectPurchaseDate();
		selectPurchaseLocation();
		checkHearAboutCheckboxes() ;
	}
	
	public void enterEmail(String email) {
		waitForExpectedElement(emailGuaranteeForm).clear();
		waitForExpectedElement(emailGuaranteeForm).sendKeys(email);
		log.info("Successfully eneterd the email id");
	}
	public void clickEnterAdressLookup() {
		waitForExpectedElement(addressLookup).click();
	}

	public void enterPostcode(String postcode) throws InterruptedException {
//		wait.until(ExpectedConditions.presenceOfElementLocated(registerProductPostcode)).sendKeys(postcode);
		if(webDriver.findElement(registerProductPostcode).isDisplayed()) {
			waitForExpectedElement(registerProductPostcode).sendKeys(postcode);
			log.info("Successfully entered the postcode");
		}
		else
			waitForExpectedElement(registerProductPostcodeAfterError).sendKeys(postcode);
		Thread.sleep(5000);
		//List <WebElement> myList = webDriver.findElements(By.xpath("//ul[@class='c2a_results']"));
		List <WebElement> myList = webDriver.findElements(By.xpath("//ul[@class='c2a_results']"));
		for (WebElement element:myList)
			if(element.getText().contains(Props.getProp("register.guarantee.valid.postcode")))
			{
				if (Props.getProp("locale").contains("DE")||Props.getProp("locale").contains("IT")||Props.getProp("locale").contains("FR")||Props.getProp("locale").contains("ES"))
				{
					element.click();
					Thread.sleep(1000);
					element.click();
					//element.findElement(By.xpath("li[1]")).click();
				}
				else {
					element.click();
				}

				System.out.println("element to be clicked-->"+element);

			}
	}
	public void enterPostcodeAustria(String postcode) throws InterruptedException {
//		wait.until(ExpectedConditions.presenceOfElementLocated(registerProductPostcode)).sendKeys(postcode);
		if(webDriver.findElement(registerProductPostcode).isDisplayed()) {
			waitForExpectedElement(registerProductPostcode).sendKeys(postcode);
		}
		else
			waitForExpectedElement(registerProductPostcodeAfterError).sendKeys(postcode);
		Thread.sleep(5000);
		//List <WebElement> myList = webDriver.findElements(By.xpath("//ul[@class='c2a_results']"));
		List <WebElement> myList = webDriver.findElements(By.xpath("//ul[@class='c2a_results']"));
		for (WebElement element:myList)
			if(element.getText().contains(Props.getProp("register.guarantee.valid.postcode.Austria")))
			{
				if(Props.getProp("locale").contains("DE"))
				{
					element.click();
					Thread.sleep(1000);
					//element.findElement(By.xpath("li[1]")).click();
				}
				else {
					element.click();
				}
				System.out.println("element to be clicked-->"+element);

			}
	}
	
	public void enterFirstName(String FirstNameData) {
		waitForExpectedElement(firstName).clear();
		waitForExpectedElement(firstName).sendKeys(FirstNameData);
		log.info("successfully entered the first name " + FirstNameData);
	}
	
	public void enterLastName(String LastNameData) {
		waitForExpectedElement(lastName).clear();
		waitForExpectedElement(lastName).sendKeys(LastNameData);
		log.info("successfully entered the last name " + LastNameData);
	}
	
	public void selectProductType() {
		selectValueFromDropDownByby(Props.getProp("Product.type"), productType);
		log.info("successfully entered the Product Type " + productType);
	}
	
	public void enterModelNumber(String modelNumber) {
		waitForExpectedElement(this.modelNumber).sendKeys(modelNumber);
		waitForExpectedElement(this.modelNumber).sendKeys(Keys.TAB);
		log.info("successfully entered the model number " + modelNumber);
	}
	
	public void selectPurchaseDate() {
		waitForExpectedElement(this.purchaseDate).click();
		waitForExpectedElement(this.todayDate).click();
	}
	
	public void selectPurchaseLocation() {
		selectValueFromDropDownByby("Amazon", purchaseLocation);
	}
	
	public void checkHearAboutCheckboxes()  {
		waitForExpectedElement(checkboxWhatsNew).click();

		//waitForExpectedElement(checkboxOffers).click();

		//waitForExpectedElement(checkboxTipsAndArticles).click();

	}
	
	public void clickSubmitBtn() {
		IsPageLoaded.waitAllRequest();
		//waitForExpectedElement(this.submitBtn).click();
		//waitForExpectedElement(submitBtn).click();
		if (UrlBuilder.isIphone()) {
			Actions act= new Actions(getWebDriver());
			act.moveToElement(webDriver.findElement(submitBtn)).build().perform();
			webDriver.findElement(submitBtn).click();
			log.info("Successfully clicked on the Submitbutton " + submitBtn);
		}
		else
		{
//			Actions act = new Actions(webDriver);
//			act.moveToElement(webDriver.findElement(submitBtn)).click().build().perform();
			wait.until(ExpectedConditions.elementToBeClickable(submitBtn));
			clickUsingJS(submitBtn);
			log.info("Successfully clicked on the Submitbutton " + submitBtn);
//			clickUsingJS(submitBtn);
		}


	}
	
	public String GetWarrantyRegisteredText() {
		return waitForExpectedElement(this.confirmText).getText();
	}
	
	
	
	public String GetWarrantyInfoMailText() {
		return waitForExpectedElement(this.warrantyInfoMailText).getText();
	}

	public boolean verifyPrefilledDetails() {
		boolean flag=false;
		try {
			flag = waitForExpectedElement(emailGuaranteeForm).getAttribute("value").contains(Props.getProp("warranty.verify.email")) &&
					!waitForExpectedElement(prefilledPostcode).getAttribute("value").isEmpty() &&
					!waitForExpectedElement(firstName).getAttribute("value").isEmpty() &&
					!waitForExpectedElement(lastName).getAttribute("value").isEmpty();
			log.info("Successfully found prefilled details");
		} catch (Exception e) {
			log.error("Some exception occurred while finding prefilled details-->>"+e.getMessage());
		}
		return flag;
	}

	public String verifyEmptyErrorMessageEmail() {
		String msg="No message";
		try {
			System.out.println("its running");
			msg = waitForExpectedElement(errorEmptyEmailText).getText();
			log.info("Successfully found empty email message");
		} catch (Exception e) {
			log.error("Some exception occurred while finding empty error message-->>"+e.getMessage());
		}
		return msg;
	}

	public String verifyEmptyPostalCode() {
		String msg="No message";
		try {
			msg = waitForExpectedElement(errorEmptyPostalCode).getText();
			log.info("Successfully found error message for empty postal code");
		} catch (Exception e) {
			log.error("Some exception occurred while finding error message for empty postal code-->>"+e.getMessage());
		}
		return msg;
	}

	public String verifyEmptyFirstName() {
		String msg="No message";
		try {
			msg = waitForExpectedElement(errorEmptyFirstName).getText();
			log.info("Successfully found error message for first name");
		} catch (Exception e) {
			log.error("Some exception occurred while finding error message for first name-->>"+e.getMessage());
		}
		return msg;
	}

	public String verifyEmptyLastName() {
		String msg="No message";
		try {
			msg = waitForExpectedElement(errorEmptyLastName).getText();
			log.info("Successfully found error message for last name");
		} catch (Exception e) {
			log.error("Some exception occurred while finding empty error message for last name-->>"+e.getMessage());
		}
		return msg;
	}

	public String verifyEmptyProductType() {
		String msg="No message";
		try {
			msg = waitForExpectedElement(errorEmptyProductType).getText();
			log.info("Successfully found error message for product type");
		} catch (Exception e) {
			log.error("Some exception occurred while finding error message for product type-->>"+e.getMessage());
		}
		return msg;
	}

	public String verifyEmptyModelNumber() {
		String msg="No message";
		try {
			msg = waitForExpectedElement(errorEmptyModelNumber).getText();
			log.info("Successfully found error message for model number");
		} catch (Exception e) {
			log.error("Some exception occurred while finding error message for model number-->>"+e.getMessage());
		}
		return msg;
	}

	public String verifyEmptyPurchaseDate() {
		String msg="No message";
		try {
			msg = waitForExpectedElement(errorEmptyPurchaseDate).getText();
			log.info("Successfully found error message for purchase date");
		} catch (Exception e) {
			log.error("Some exception occurred while finding error message for purchase date-->>"+e.getMessage());
		}
		return msg;
	}

	public String verifyEmptyPurchaseLocation() {
		String msg="No message";
		try {
			msg = waitForExpectedElement(errorEmptyPurchaseLocation).getText();
			log.info("Successfully found error message for purchase location");
		} catch (Exception e) {
			log.error("Some exception occurred while finding error message for purchase location-->>"+e.getMessage());
		}
		return msg;
	}

	public Boolean verifyInvalidErrorMessageEmail() {
		boolean flag=false;
		try {
			flag = waitForExpectedElement(errorInvalidEmailText).getText().contains(Props.getMessage("guarantee.error.invalid.email"));
			log.info("Successfully found invalid email message");
		} catch (Exception e) {
			log.error("Some exception occurred while finding invalid error message for email-->>"+e.getMessage());
		}
		return flag;
	}

	public Boolean verifyInvalidErrorMessageFirstName() {
		boolean flag=false;
		try {
			flag = waitForExpectedElement(errorInvalidFirstName).getText().contains(Props.getMessage("guarantee.error.invalid.firstname"));
			log.info("Successfully found invalid email message");
		} catch (Exception e) {
			log.error("Some exception occurred while finding invalid error message for email-->>"+e.getMessage());
		}
		return flag;
	}

	public Boolean verifyInvalidErrorMessageLastName() {
		boolean flag=false;
		try {
			flag = waitForExpectedElement(errorInvalidLastName).isDisplayed();
			log.info("Successfully found invalid email message");
		} catch (Exception e) {
			log.error("Some exception occurred while finding invalid error message for email-->>"+e.getMessage());
		}
		return flag;
	}

	public Boolean verifyInvalidEmailErrorMessageNotShown() {
		boolean flag=false;
		try {
			flag = invisibilityOfElementLocated(errorInvalidEmailText);
			log.info("Error message for email is not shown");
		} catch (Exception e) {
			log.error("Error message for email is shown-->>"+e.getMessage());
		}
		return flag;
	}

	public Boolean verifyEmptyPostcodeErrorMessageNotShown() {
		boolean flag=false;
		try {
			flag = invisibilityOfElementLocated(errorEmptyPostalCode);
			log.info("Error message for empty postcode is not shown");
		} catch (Exception e) {
			log.error("Error message for empty postcode is shown-->>"+e.getMessage());
		}
		return flag;
	}

	public Boolean verifyInvalidFirstNameErrorMessageNotShown() {
		boolean flag=false;
		try {
			flag = invisibilityOfElementLocated(errorInvalidFirstName);
			log.info("Error message for invalid first name is not shown");
		} catch (Exception e) {
			log.error("Error message for invalid first name is shown-->>"+e.getMessage());
		}
		return flag;
	}

	public Boolean verifyInvalidLastNameErrorMessageNotShown() {
		boolean flag=false;
		try {
			flag = invisibilityOfElementLocated(errorInvalidLastName);
			log.info("Error message for invalid last name is not shown");
		} catch (Exception e) {
			log.error("Error message for invalid last name is shown-->>"+e.getMessage());
		}
		return flag;
	}

	public Boolean verifyEmptyProductTypeErrorMessageNotShown() {
		boolean flag=false;
		try {
			flag = invisibilityOfElementLocated(errorEmptyProductType);
			log.info("Error message for empty product type is not shown");
		} catch (Exception e) {
			log.error("Error message for empty product type is shown-->>"+e.getMessage());
		}
		return flag;
	}

	public Boolean verifyEmptyProductModelErrorMessageNotShown() {
		boolean flag=false;
		try {
			flag = invisibilityOfElementLocated(errorEmptyModelNumber);
			log.info("Error message for empty product model is not shown");
		} catch (Exception e) {
			log.error("Error message for empty product model is shown-->>"+e.getMessage());
		}
		return flag;
	}

	public Boolean verifyEmptyPurchaseDateErrorMessageNotShown() {
		boolean flag=false;
		try {
			flag = invisibilityOfElementLocated(errorEmptyPurchaseDate);
			log.info("Error message for empty purchase date is not shown");
		} catch (Exception e) {
			log.error("Error message for empty purchase date is shown-->>"+e.getMessage());
		}
		return flag;
	}

	public Boolean verifyEmptyPurchaseFromErrorMessageNotShown() {
		boolean flag=false;
		try {
			flag = invisibilityOfElementLocated(errorEmptyPurchaseLocation);
			log.info("Error message for empty purchase location is not shown");
		} catch (Exception e) {
			log.error("Error message for empty purchase location is shown-->>"+e.getMessage());
		}
		return flag;
	}

	public Boolean clickCreateAccountCheckbox() {
		boolean flag=false;
		try {
			waitForExpectedElement(createAccountCheckbox).click();
			log.info("Successfully clicked on create account checkbox");
			flag=true;
		} catch (Exception e) {
			log.error("Error while click on create account checkbox-->>"+e.getMessage());
		}
		return flag;
	}

	public Boolean fillNewAccountPassword() {
		boolean flag=false;
		try {
			waitForExpectedElement(password).clear();
			waitForExpectedElement(password).sendKeys(Props.getProp("register.guarantee.password"));
			log.info("Successfully clicked on create account checkbox");
			flag=true;
		} catch (Exception e) {
			log.error("Error while click on create account checkbox-->>"+e.getMessage());
		}
		return flag;
	}

	public void fillGuaranteeFormWithPassword() throws InterruptedException{
		Assert.assertTrue(fillNewAccountPassword());
		clickEnterAdressLookup();
		enterPostcode(registerProductPostCodeData);
		enterFirstName(FirstNameData);
		enterLastName(LastNameData);
		selectProductType();
		enterModelNumber(mNumber);
		selectPurchaseDate();
		selectPurchaseLocation();
		checkHearAboutCheckboxes();
	}

	public Boolean verifyAccountExistMessage() {
		boolean flag=false;
		try {
			flag = waitForExpectedElement(accountExistMessage).getText().contains(Props.getMessage("guarantee.message.account.exist"));
			log.info("Successfully found account exist message");
		} catch (Exception e) {
			log.error("Some exception occurred while finding account exist message-->>"+e.getMessage());
		}
		return flag;
	}

	public Boolean verifyAccountExistMessageNotShown() {
		boolean flag=false;
		try {
			flag = invisibilityOfElementLocated(accountExistMessage);
			log.info("Message for account exist is not shown");
		} catch (Exception e) {
			log.error("Message for account exist is shown-->>"+e.getMessage());
		}
		return flag;
	}

}
