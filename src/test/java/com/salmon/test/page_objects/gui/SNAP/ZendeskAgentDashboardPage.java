package com.salmon.test.page_objects.gui.SNAP;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.SourceType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class ZendeskAgentDashboardPage extends PageObject {

	private String env=Props.getProp("zendesk.testing.env");
	private String orderIdDynamic = Props.getProp("order.id");
	private static final By dashboardButton = By.xpath("//button[contains(text(),'Dashboard')]");
	private static final By OrderTitle=By.xpath("//*[@class='container--title']/div[1]");
	private static final By ReplaceUnitTitle=By.xpath("//*[@class='snap-create-return-request row']/h1");
	private static final By Createbtn=By.xpath("//*[@name='Create']");
	private static final By CartHeader=By.xpath("//div[@class='cart-header']/h1/span");
	private static final By replaceUnitCheckout=By.xpath("//button[contains(text(),' Checkout ')]");
	private static final By Checkoutheader=By.xpath("//*[@class='header-left-container']/h2");
	private static final By replaceUnitPaymentMethodRadiobtn=By.xpath("//*[@for='paymentOption_ZeroPayment']");
	private static final By placeOrder=By.xpath( "//*[contains(text(),' Place order ')]" );
	private static final By placeOrderText=By.xpath( "//*[@class='c-callout c-callout--success']" );
	private static final By snapCheckoutHeader=By.xpath( "//*[@class='snap-checkout-header']/div" );
	private static final By addText = By.xpath("//span[text()='add']/..");
	private static final By AppsBtn = By.xpath("//button[normalize-space()='Apps']");
	private static final By newTicketText=By.xpath("//div[@data-test-id=\"header-tab-title\"]");
	private static final By selectOptionTicket = By.xpath("//li[text()=\"Ticket\"]");
	private static final By rightInfoPane=By.xpath("//div[@class=\"app_container\"]");
	private static final By appsButton=By.xpath("//button[@value=\"apps\"]");
	private static final By selectChannel=By.cssSelector("#select-channel");
	private  final By iframe=By.xpath("//iframe[contains(@name,'"+env+"')]");
	//	private static final By UATFrame = By.xpath("//iframe[starts-with(@name,'app_UAT')]");
//	private static final By INTFrame= By.xpath("//iframe[starts-with(@name,'app_INT')]");
	private static final By lastName=By.xpath("//label[text()=\"Last Name\"]/../following-sibling::div/input");
	private static final By searchButton=By.xpath("//button[@type=\"submit\"]");
	private static final By customerLastName=By.xpath("//label[text()=\"Customer:\"]/..");
	private static final By customerNumber=By.xpath("//label[text()=\"Customer No\"]/../following-sibling::div/input");
	private static final By customerNo=By.xpath("//label[text()=\"Customer No:\"]/..");
	private static final By emailID=By.xpath("//label[text()=\"Email\"]/../following-sibling::div/input");
	private static final By getEmailID=By.xpath("//label[text()=\"Email:\"]/../div");
	private static final By errorMessage=By.xpath("//div[@id=\"app\"]/div/div/div/p");
	private static final By OrderSearchbtn = By.xpath("//*[@class='u-mt-sm']//div[2]/div/button[1]");
	public By SelectChannel = By.xpath("//*[@id=\"select-channel\"]");
	private static final By radioBtnSalesChannelNinja = By.xpath("/html/body/ish-root/div/snap-tabs-container/div/div/snap-order-search-page/div/div/form/div[1]/div/div[2]/label");
	public By SelectCancellationReason = By.xpath("(//select[@data-testing-id='reason'])");
	private static final By getRadioBtnSalesChannelShark = By.xpath("//div/label[@for='brand-11002']");
	//	private static final By getRadioBtnSalesChannelShark=By.xpath("//input[@id='brand-11002']");
	private static final By OrderNo = By.xpath("//*[@placeholder='Order number']");
	public By ColumnData = By.xpath("//*[@class='c-table u-bg-white']/tbody/tr/td[1]");
	private static final By orderHistory =By.xpath("//*[text()=\"Order History\"]");
	private static final By SearchBtn = By.xpath("//*[@value='Login']");
	private static final By productResults=By.xpath("//tr[@class='c-table__row']/td[2]/a");
	private static final By orderOptionsBtn=By.xpath("//button[normalize-space()='Order options']");

	private static final By cancelOrderLine=By.xpath("//snap-order-options/div/div/ul/li/a");
	private static final By orderTypestatus= By.xpath("//snap-order-type-and-reason[normalize-space()='New Order']");
	private static final By orderStatus= By.xpath("//div[@class='data col-7']//snap-order-status");
    private static final By replaceUnit= By.xpath("//a[text()=' Replace unit ']");
    private static final By faultOnDelivery=By.xpath("//a[text()=' Faulty on delivery']");
    private static final By returnSuccessfullyTitle =By.xpath("//*[@class='c-dialog__header ']");
    private static final By quantityBox=By.xpath("//snap-input[@type=\"number\"]/div/input");
    private static final By maxQuantityNo=By.xpath("//td[@colspan='3'][2]");
    private static final By replaceUnitContinueButton=By.xpath("//button[contains(text(),' Continue')]");
    private static final By productID=By.xpath( "//*[@class='product-id']" );
    private static final By addToCartBtn=By.xpath( "//*[@name='addProduct']" );
    private static final By ClickOnCart=By.xpath( "//*[@routerlink='/basket']" );
	private static final By selectButton=By.xpath("//button[contains(text(),'Select')]");
	private static final By numberOfCustomerContainers=By.xpath("//ul[contains(@class,'customer-results')]");
	private static final By rowData=By.xpath("//table[@class='c-table u-bg-white']/tbody/tr/td[5]");

	private static final By quantityboxValue=By.xpath("//table[@class='c-table u-bg-white']/tbody/tr/td[6]");

	private static final By quantitybox=By.xpath("//input[@data-testing-id='qty'");
	private static final By createProductRegistrationButton=By.xpath("//button[text()=\"Create product registration\"]");
	private static final By phoneNumber = By.xpath("//input[@placeholder=\"Phone number\"]");
	private static final By cutomerEmailId = By.xpath("//input[@placeholder=\"Customer email address\"]");
	private static final By orderOptionReplaceUnit = By.xpath("//a[text()=' Replace unit']");
	private static final By returnRequestTitle = By.xpath("//div[@class=\"snap-create-return-request row\"]/h1");
	private static final By returnSuccessfulTitle = By.xpath("//h1[text()=' Return successfully created ']");
	private static final By continueButton = By.xpath("//button[@data-testing-id=\"confirm\"]");
	private static final By orderOptionReplacePart = By.xpath("//a[text()=' Replace part']");
	private static final By createButton = By.xpath("//button[@data-testing-id=\"create\"]");

	private static final By confirmButton = By.xpath("//button[@name='confirm']");

	private static final By faultErrorMessage = By.xpath("//small[text()='You must select a fault reason to continue']");
	private static final By faultReasonButton = By.xpath("//span[@class=\"btn-inner-text\"]");
	private static final By faultReason = By.xpath("//input[@id=\"NO_POWER/NOT_CHARGING\"]");
	private static final By batteryFailureReason = By.xpath("//input[@id=\"BATTERY_FAILURE\"]");
	private static final By orderOptionCreateRefund = By.xpath("//a[text()=' Create refund return']");
	private static final By buyersOption = By.xpath("//option[@value=\"RET190\"]");
	private static final By returnRequestReason = By.xpath("//select[@data-testing-id=\"reasonSelect\"]");
	private static final By orderOptionExpressReplacement = By.xpath("//a[text()=' Create express replacement']");
	private static final By lostInTransitOption = By.xpath("//a[text()=' Lost in transit']");
	private static final By missingInBox = By.xpath("//a[text()=' Missing from box']");
	private static final By numberOfOrderHistory = By.xpath("//tr[@class=\"c-table__row\"]");
	private static final By orderNumber = By.xpath("//snap-order-type-and-reason[contains(text(),'New Order')]/../following-sibling::td/snap-order-status[normalize-space()=\"Dispatched\"]/../../td/a");
	private static final By orderNumberCA = By.xpath("//snap-order-type-and-reason[contains(text(),'New Order')]/../following-sibling::td/snap-order-status[normalize-space()=\"Shipped\"]/../../td/a");
	private static final By continueButtonPopUp = By.xpath("//button[@data-testing-id=\"confirm\"]");
	private final By iframe3=By.xpath("(//iframe[contains(@name,'"+env+"')])[4]");
	private final By viewOrdersButton = By.xpath("(//td[@class=\"c-table__row__cell link-view-order\"])[1]");
	private final By columnData1 = By.xpath("(//*[@class='c-table u-bg-white']/tbody/tr/td[1])[1]");

	private static final By BackWebNeworderNumber = By.xpath("//snap-order-type-and-reason[contains(text(),'New Order')]/../following-sibling::td/snap-order-type-and-reason[contains(text(),'Web Order')]/../following-sibling::td/snap-order-status[normalize-space()=\"Back-order\"]/../../td/a");

	private final By phoneNumberValue = By.xpath("(//div[contains(text(),'" + Props.getProp("zendesk.phone.no") + "')])[1]");
	private final By emailId = By.xpath("(//div[contains(text(),'" + Props.getProp("zendesk.email.id") + "')])[1]");
	private final By buyerRemoteOption = By.xpath("//select[@data-testing-id=\"reasonSelect\"]/option[@value=\"RET190\"]");
	private final By refundReason = By.xpath("//select[@data-testing-id=\"reasonSelect\"]");
	private final By firstOrderId = By.xpath("(//table[@class=\"c-table u-bg-white\"]/tbody/tr/td/a)[1]");
	private final By sharkChannel = By.xpath("(//label[@class=\"c-chk__label c-chk__label--radio u-fs-sm\"])[1]");
	private final By freePaymentMethod = By.xpath("//label[@for=\"paymentOption_Zero Payment\"]");
	private final By orderNewCustomer = By.xpath("//button[@class=\"c-btn\" and text()='Create order for new customer']");
	private  final By orderIdOnOrderHistoryPage=By.xpath("//a[contains(@href,'"+orderIdDynamic+"')]");
	private static final By rapidResponse = By.xpath("(//div[@class=\"c-txt__input disabled-option\"])[3]");
	private static final By RMANumber = By.xpath("(//div[@class=\"c-txt__input disabled-option\"])[2]");
	private static final By orderId = By.xpath("(//div[@class=\"c-txt__input disabled-option\"])[1]");
	private static final By getRadioBtnEbayShark = By.xpath("//div/label[@for='brand-11102']");
	private static final By orderOptionButton = By.xpath("//button[@class='c-btn c-btn--full']");

	private static final Logger log = LoggerFactory.getLogger(ZendeskAgentDashboardPage.class);
	
	public boolean checkDashboardButtonIsDisplayed() {
		return waitForExpectedElement(dashboardButton).isDisplayed();
	}
	
//	public By getAddText() {
//		return addText;
//	}
	
	public String getAddText() {
		return waitForExpectedElement(addText).getText();
	}
	
	public String addTicket() {

		String text="";

		try {
			hoverOnElement(addText);
			log.info("Successfully Hover on Add Text Button");

			Actions actions = new Actions(webDriver);

			WebElement element = waitForExpectedElement(selectOptionTicket);

			actions.moveToElement(element).click().build().perform();
			log.info("Successfully selected the option \"TICKET\" from the dropdown");

			text=getTextFor(newTicketText);

		}catch (Exception e)
		{
			System.out.println("Some exception occurred in performing action hover on add button and click Ticket-->>"+e.getMessage());
		}
		return text;
	}

	public boolean clickOnNewCustomerOrder() {
		boolean bool=false;
		try {
			webDriver.switchTo().frame(waitForExpectedElement(iframe));
			log.info("Successfully switched to Iframe INT SNAP Customer Search");

			waitForExpectedElement(orderNewCustomer).click();
			bool = true;
		}
		catch (Exception e){
			System.out.println("Couldn't click on new customer button due to -->>"+e.getMessage());
		}
		return bool;
	}


	public boolean openSNAPCustomerSearchAndSelectChannelAs(String channel)
	{
		boolean bool=false;
		try{

			waitForExpectedElement(appsButton).click();
			System.out.println("Info Pane wasn't open before clicking in apps button to open");


			webDriver.switchTo().frame(waitForExpectedElement(iframe));
			System.out.println("Successfully switched to Iframe INT SNAP Customer Search");


			//selectValueFromDropDownByWebElement(waitForExpectedElement(selectChannel),"UK");
			selectValueFromDropDownByby(channel,selectChannel);
			System.out.println("Successfully set the channel dropdown value to "+channel);


			bool=true;

		}catch (Exception e)
			{
				System.out.println("Some exception occurred while selecting channel as UK for INT SNAP Customer Search-->>"+e.getMessage());
			}
		finally {
			webDriver.switchTo().defaultContent();
		}
		return bool;
	}

	public boolean enterLastNameAndClickSearch(String name)
	{
		boolean bool=false;

		try
		{
			webDriver.switchTo().frame(waitForExpectedElement(iframe));
			log.info("Successfully switched to Iframe INT SNAP Customer Search");

			waitClearAndEnterText(lastName,name);
			log.info("Successfully set the last name ");

			waitForExpectedElement(searchButton).click();
			log.info("Cliked on Search button");

			bool=true;

		}catch (Exception e)
		{
			log.info("Some exception occurred while searching the last name -->>"+e.getMessage());
		}
		finally
		{
			webDriver.switchTo().defaultContent();
		}

		return bool;
	}

	public boolean validateCustomerWithLastName(String lastName)
	{
		boolean bool=false;

		try
		{
			webDriver.switchTo().frame(waitForExpectedElement(iframe));
			System.out.println("Successfully switched to Iframe INT SNAP Customer Search");

			List<String> names = new ArrayList<>();

			log.info("Going to fetch customer's last name showing in tiles");

			for (WebElement s : visibilityOfAllElementsLocatedBy(customerLastName))
			{
				String[] split = s.getText().replace("\n","").split(":")[1].split(" ");
				names.add(split[split.length-1]);
			}
			log.info("Successfully fetched all customers last names showing in tiles");

			bool=names.stream().anyMatch(s->s.equalsIgnoreCase(lastName));

			log.info("Successfully found tile having customer last name as "+lastName);

		}catch (Exception e)
		{
			log.info("Some Exception occurred while validating last name-->>"+e.getMessage());
		}finally
		{
			webDriver.switchTo().defaultContent();
		}

		return bool;
	}


	public boolean enterCustomerNumberAndClickSearch(String number)
	{
		boolean bool=false;

		try
		{
			webDriver.switchTo().frame(waitForExpectedElement(iframe));
			log.info("Successfully switched to Iframe INT SNAP Customer Search");

			waitClearAndEnterText(customerNumber,number);
			log.info("Successfully set the customer number in text field ");

			Thread.sleep(2000);
			waitForExpectedElement(searchButton).click();
			log.info("Cliked on Search button");

			bool=true;

		}catch (Exception e)
		{
			log.info("Some exception occurred while populating the field customer number -->>"+e.getMessage());
		}
		finally
		{
			webDriver.switchTo().defaultContent();
		}

		return bool;
	}

	public String validateCustomerNumberIsShowingOnTileAs()
	{
		String text="";

		webDriver.switchTo().frame(waitForExpectedElement(iframe));
		log.info("Successfully switched to Iframe INT SNAP Customer Search");

		text=waitForExpectedElement(customerNo).getText().trim().split(":")[1].trim();

		webDriver.switchTo().defaultContent();

		return text;
	}

	public int numberOfTilesHavingCustomerNumber()
	{
		webDriver.switchTo().frame(waitForExpectedElement(iframe));

		List<WebElement> list=visibilityOfAllElementsLocatedBy(customerNo);

		webDriver.switchTo().defaultContent();

		return list.size() ;
	}

	public boolean enterEmailIdAndClickSearch(String emailId)
	{
		boolean bool=false;

		try
		{
			webDriver.switchTo().frame(waitForExpectedElement(iframe));
			log.info("Successfully switched to Iframe INT SNAP Customer Search");

			waitClearAndEnterText(emailID,emailId);
			log.info("Successfully enter the email id in text field ");

			waitForExpectedElement(searchButton).click();
			log.info("Cliked on Search button");

			bool=true;

		}catch (Exception e)
		{
			log.info("Some exception occurred while populating the email ID -->>"+e.getMessage());
		}
		finally
		{
			webDriver.switchTo().defaultContent();
		}

		return bool;
	}


	public boolean getEmailIdShownInTiles(String emailId)
	{
		String text="";
		boolean bool=false;
		try
		{
			webDriver.switchTo().frame(waitForExpectedElement(iframe));
			log.info("Successfully switched to Iframe INT SNAP Customer Search");

			for(WebElement e: visibilityOfAllElementsLocatedBy(getEmailID))
			{
				text=e.getText().trim();
				log.info("Email from tile shown over UI is "+text);

				if(text.equalsIgnoreCase(emailId))
				{
					log.info("Email has been matched");
					bool=true;
					break;
				}
			}

		}catch (Exception e)
		{
			log.info("Some Exception occurred while validating email id shown over tiles "+e.getMessage());
		}
		finally
		{
			webDriver.switchTo().defaultContent();
		}

		return bool;
	}

	public String getErrorMessage()
	{
		String text="";

		try
		{
			webDriver.switchTo().frame(waitForExpectedElement(iframe));
			log.info("Successfully switched to Iframe INT SNAP Customer Search");

			text=waitForExpectedElement(errorMessage).getText().trim();

		}catch (ElementNotFoundException e)
		{
			log.info("Unable to find the error message please check Xpath of the locator-->>"+e.getMessage());
		}catch (Exception e)
		{
			log.info("Some exception occurred while searching for error message-->>"+e.getMessage());
		}
		finally
		{
			webDriver.switchTo().defaultContent();
		}

		return text;
	}

	public boolean clickOnSelectButtonWhereEmailIdIs(String emailId)
	{
		boolean bool=false;

		try
		{
			Thread.sleep(12000);

		    log.info("Going to switch to Iframe");
			webDriver.switchTo().frame(waitForExpectedElement(iframe));
			log.info("Successfully switched to Iframe INT SNAP Customer Search");

			List<WebElement> container= visibilityOfAllElementsLocatedBy(numberOfCustomerContainers);
			log.info("Successfully fetched all the container");

			for(int i=0;i<container.size();i++)
			{
				List<WebElement> list=container.get(i).findElements(By.xpath("li"));
				log.info("Successfully fetched all the labels in the container moving to validate email id ");

				for(int j=0;j<list.size();j++)
				{
					if(list.get(j).getText().trim().contains(emailId))
					{
						container.get(i).findElement(By.tagName("button")).click();
						log.info("Successfully clicked on Select button where email is -->>"+emailId);
						bool=true;
					}
				}
			}

		}catch (Exception e)
		{
			log.info("Some exception occurred please check error message-->>"+e.getMessage());
		}
		finally
		{
			webDriver.switchTo().defaultContent();
		}

		return bool;
	}

	public boolean clickDispatachedANdNewOrderType()
	{
		boolean flag=false;
		List<WebElement> ele;
		try
		{
//
//			webDriver.switchTo().frame(waitForExpectedElement(iframe3));
//			log.info("Successfully switch into the frame");

			if(Props.getProp("country").equals("NA"))
			{
				ele = presenceOfAllElementsLocatedBy(orderNumberCA);
			}
			else
				ele = presenceOfAllElementsLocatedBy(orderNumber);

			wait.until(ExpectedConditions.elementToBeClickable(ele.get(0))).click();

			log.info("Successfully clicked on Create Product Registration Button");

			flag=true;
		}catch (Exception e)
		{
			log.info("Some Exception occurred when clicking on creat product registration button-->>"+e.getMessage());
		}finally {
			webDriver.switchTo().defaultContent();
		}

		return flag;
	}

	public boolean clicksOnCreateProductRegistrationButton()
	{	boolean flag=false;
		try
		{
			webDriver.switchTo().frame(waitForExpectedElement(iframe));
			log.info("Successfully switch into the frame");

			waitForExpectedElement(createProductRegistrationButton).click();
			log.info("Successfully clicked on Create Product Registration Button");

			flag=true;
		}catch (Exception e)
		{
			log.info("Some Exception occurred when clicking on creat product registration button-->>"+e.getMessage());
		}finally {
			webDriver.switchTo().defaultContent();
		}

		return flag;
	}


	public boolean clickBackOrderANDWebOrderANdNewOrderType() {
		boolean flag = false;
		try {

			webDriver.switchTo().frame(waitForExpectedElement(iframe3));
			log.info("Successfully switch into the frame");

			List<WebElement> ele = presenceOfAllElementsLocatedBy(BackWebNeworderNumber);

			wait.until(ExpectedConditions.elementToBeClickable(ele.get(0))).click();

			log.info("Successfully clicked on order ID Button");

			flag = true;
		} catch (Exception e) {
			log.info("Some Exception occurred when clicking on order ID-->>" + e.getMessage());
		}

//		finally {
//			webDriver.switchTo().defaultContent();
//		}

		return flag;
	}

	public boolean clickOnPopUpContinueButton() {

		boolean flag = false;

		try {
			webDriver.switchTo().defaultContent();
			webDriver.switchTo().frame(waitForExpectedElement(By.xpath("(//iframe[contains(@name,'UAT')])[4]")));
			waitForExpectedElement(continueButtonPopUp).click();
			log.info("click on continue button");
			flag = true;
		} catch (Exception e) {
			log.info("Unable to click on Replace Unit Option Due to --- " + e.getMessage());
			e.printStackTrace();
		}
		return flag;

	}

	public boolean clickOnPopUpContinueButtonForEUChannel() {

		boolean flag = false;

		try {
			webDriver.switchTo().defaultContent();
			webDriver.switchTo().frame(waitForExpectedElement(By.xpath("(//iframe[contains(@name,'UAT')])[3]")));

			if (getWebDriver().findElement(continueButtonPopUp).isDisplayed()) {
				waitForExpectedElement(continueButtonPopUp).click();
				log.info("click on continue button");
			}

			flag = true;
		} catch (Exception e) {
			log.info("Unable to click on Replace Unit Option Due to --- " + e.getMessage());
			e.printStackTrace();
		}
		finally {
			webDriver.switchTo().defaultContent();
		}
		return flag;

	}

	public void ClickOnAppsBtn()
	{
		new Actions(getWebDriver()).moveToElement(waitForExpectedElement(appsButton)).click().build().perform();
//		waitForExpectedElement(appsButton).click();
	}
	public void UATSNAP() {
		webDriver.switchTo().frame(waitForExpectedElement(iframe));
	}
	public void SelectChannel()
	{
		waitForExpectedElement(OrderNo).sendKeys(Props.getProp("zendesk.select.channel"));
	}
	public void ClickOrderSearchbtn() {
		waitForExpectedElement(OrderSearchbtn).click();
	}
	public void SelectSalesChannelNinja() {


			waitForExpectedElement(radioBtnSalesChannelNinja).click();

			log.info( "Agent is able to select the Ninja sales channel" );
	}
	public boolean SelectSalesChannelShark() {
		boolean flag=false;
		try {
			webDriver.switchTo().defaultContent();
			webDriver.switchTo().frame(waitForExpectedElement(By.xpath("(//iframe[contains(@name,'UAT')])[4]")));
			waitForExpectedElement(getRadioBtnSalesChannelShark).click();
			log.info("Agent is able to select the Shark sales channel");
			flag = true;
		}catch (Exception e){
			log.error(ExceptionUtils.getStackTrace(e.fillInStackTrace()));
		}
		return flag;
	}

	public boolean selectSharkChannel() {
		boolean flag=false;
		try {
			webDriver.switchTo().defaultContent();
			webDriver.switchTo().frame(waitForExpectedElement(By.xpath("(//iframe[contains(@name,'UAT')])[4]")));
			waitForExpectedElement(sharkChannel).click();
			log.info("Agent is able to select the Shark sales channel");
			flag = true;
		}catch (Exception e){
			log.error(ExceptionUtils.getStackTrace(e.fillInStackTrace()));
		}
		return flag;
	}

	public String EnterOrderNo(String orderId) {

			waitForExpectedElement(OrderNo).sendKeys(orderId);
			log.info( "Agent is able to enter the order no" );

		return null;
	}

	public boolean enterPhoneNo() {

		boolean flag = false;

		try {
			waitForExpectedElement(phoneNumber).sendKeys(Props.getProp("zendesk.phone.no"));
			log.info("Agent is able to enter the phone no");
			flag = true;
		} catch (Exception e) {
			log.info("Agent is unable to enter phone no due to " + e.getMessage());
			e.printStackTrace();

		}

		return flag;
	}

	public boolean enterEmailId() {

		boolean flag = false;

		try {
			waitForExpectedElement(cutomerEmailId).sendKeys(Props.getProp("zendesk.email.id"));
			log.info("Agent is able to enter the email Id");
			flag = true;
		} catch (Exception e) {
			log.info("Agent is unable to enter email Id due to " + e.getMessage());
			e.printStackTrace();

		}

		return flag;
	}


	public void ClickOnSearchBtn() {


			waitForExpectedElement(SearchBtn).click();
			log.info( "Agent is able to enter the order no" );



	}
//
//	public boolean ColumnData() {
//		if( return waitForExpectedElement( ColumnData ).isDisplayed())
//		{
//
//		}



	public void ClickOnOrder() {
		Actions a=new Actions(webDriver);
		a.moveToElement(waitForExpectedElement(ColumnData)).doubleClick().build().perform();
	//	waitForExpectedElement(ColumnData).click();
	}

	public void ClickOnBackOrder() {
		Actions a=new Actions(webDriver);
		a.moveToElement(waitForExpectedElement(ColumnData)).doubleClick().build().perform();
		//	waitForExpectedElement(ColumnData).click();
	}

	public void ClickOnNewBackWebOrder() {
		Actions a=new Actions(webDriver);
		a.moveToElement(waitForExpectedElement(BackWebNeworderNumber)).doubleClick().build().perform();
		//	waitForExpectedElement(BackWebNeworderNumber).click();
	}
	public void clickOn1stOrder() {
		Actions a=new Actions(webDriver);
		a.moveToElement(waitForExpectedElement(columnData1)).doubleClick().build().perform();
		//	waitForExpectedElement(ColumnData).click();
	}

	public void clickOnFirstOrder() {
		//Actions a = new Actions(webDriver);
		//a.moveToElement(waitForExpectedElement(firstOrderId)).doubleClick().build().perform();
		//	waitForExpectedElement(ColumnData).click();
		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		js.executeScript("window.scrollBy(0,1000)");

		waitForExpectedElement(firstOrderId).click();

	}

	public void OrderHistory()
	{
		waitForExpectedElement(orderHistory).isDisplayed();
	}

	public String verifyOrderNo()
	{	String text="not found";
		try {
			JavascriptExecutor js = (JavascriptExecutor) webDriver;
			js.executeScript("window.scrollBy(0,1000)");
			text=waitForExpectedElement(ColumnData).getText().trim();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return text;
	}

	public String verifyPhoneNo()
	{	String text="not found";
		try {
			webDriver.switchTo().defaultContent();
			webDriver.switchTo().frame(waitForExpectedElement(iframe3));
			JavascriptExecutor js = (JavascriptExecutor) webDriver;
			js.executeScript("window.scrollBy(0,1000)");
			text = waitForExpectedElement(phoneNumberValue).getText().trim();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return text;
	}

	public String verifyEmailId()
	{	String text="not found";
		try {
			webDriver.switchTo().defaultContent();
			webDriver.switchTo().frame(waitForExpectedElement(iframe3));

			JavascriptExecutor js = (JavascriptExecutor) webDriver;
			js.executeScript("window.scrollBy(0,2000)");

			text=waitForExpectedElement(emailId).getText().trim();
		} catch (Exception e) {
			log.info("unable to extract email id because of --- " + e.getMessage());
			e.printStackTrace();
		}
		return text;
	}

	public void  AssertOrderTitle()
	{
		try {
			String Orderno=Props.getProp("zendesk.order.no");
			String Expected= "Order Number ";
			Assert.assertEquals( waitForExpectedElement(OrderTitle).getText(),Expected+Orderno );
		} catch (Exception e) {
			System.out.println( e.getMessage());
		}
	}
	public int AssertProductResults()
	{
		try {
			List<WebElement> productLink = presenceOfAllElementsLocatedBy(productResults);
			for(WebElement product : productLink) {
				System.out.println("Link---"+product.getAttribute("href"));
				return productLink.size();
			}
//			waitForExpectedElement(productResults).getAttribute( "href" );
//			waitForExpectedElement(productResults).getText();
			log.info( "Agent is able to see the product link" );
//			System.out.println( "link" );
Assert.assertNotEquals(  productLink.size(),0 );
		} catch (Exception e) {
			System.out.println( e.getMessage());
		}

		return 0;
	}
	public void CheckOrderType()
	{
		Assert.assertEquals( waitForExpectedElement(orderTypestatus).getText(),"New Order" );
		log.info( "Agent is able to see the order type as New Order" );
	}

	public void CheckOrderStatus() {
		try {
			webDriver.switchTo().frame(waitForExpectedElement(iframe3));
			log.info("Successfully switch into the frame");

			Assert.assertTrue(waitForExpectedElement(orderStatus).getText().contains("Dispatched") || waitForExpectedElement(orderStatus).getText().contains("Shipped"));
			log.info("Agent is able to see the order status as Dispatched");

		} catch (Exception e) {
			log.info("Some Exception occurred when clicking on create product registration button-->>" + e.getMessage());
		}

	}

	public String CheckOrderStatusAsBackOrder() {
		String text = "";
		try {
//			webDriver.switchTo().frame(waitForExpectedElement(iframe3));
//			log.info("Successfully switch into the frame");

			text = waitForExpectedElement(orderStatus).getText();
			log.info("Agent is able to see the order status as Back-order");

		} catch (Exception e) {
			log.info("Some Exception occurred when getting the status text-->>" + e.getMessage());
		}

		return text;

	}
	public void ClickOnOrderOptionsBtn()
	{
		waitForExpectedElement(orderOptionsBtn).click();
		log.info( "Agent is able to click on order options button" );

	}

	public void ClickOnCancelOrderBtn()
	{
		waitForExpectedElement(cancelOrderLine).click();
		log.info( "Agent is able to click on order options button" );

	}

	public void ClickOnReplaceUnit()
	{
		new Actions(getWebDriver()).moveToElement(getWebDriver().findElement(replaceUnit)).click().build().perform();
		log.info( "Agent is able to click on Replace Unit" );

	}
	public void AssertReplaceUnitTitle()
	{
		try {
			String Orderno=Props.getProp("zendesk.order.no");
			String Expected= "Create return request for order number ";
			Assert.assertEquals( waitForExpectedElement(ReplaceUnitTitle).getText(),Expected+Orderno );
		System.out.println( "Titile Asserted" );
		} catch (Exception e) {
			System.out.println( e.getMessage());
		}
	}
	public void ClickOnCreateBtn()
	{
		waitForExpectedElement(Createbtn).click();
	}

	public boolean clickOnOrderHistory()
	{
		boolean flag = false;

		try{
			webDriver.switchTo().defaultContent();
			webDriver.switchTo().frame(waitForExpectedElement(iframe3));
			waitForExpectedElement(orderHistory).click();
			log.info("successfully click on Order History Button");
			flag = true;
		}
		catch (Exception e)
		{
			log.info("Unable to click on Order History Button due to --" + e.getMessage());
			e.printStackTrace();
		}
//		finally {
//			webDriver.switchTo().defaultContent();
//		}
		return flag;

	}

	public boolean clickOnOrderIdOrderHistoryPage(String key)
	{
		boolean flag = false;

		try{
//			webDriver.switchTo().defaultContent();
//			webDriver.switchTo().frame(waitForExpectedElement(iframe));
			waitForExpectedElement(orderIdOnOrderHistoryPage).click();
			log.info("successfully click on Order ID");
			flag = true;
		}
		catch (Exception e)
		{
			log.info("Unable to click on Order ID due to --" + e.getMessage());
			e.printStackTrace();
		}
//		finally {
//			webDriver.switchTo().defaultContent();
//		}
		return flag;

	}

	public boolean clickOnViewOrders()
	{
		boolean flag = false;

		try{
			waitForExpectedElement(viewOrdersButton).click();
			log.info("successfully click on Order History Button");
			flag = true;
		}
		catch (Exception e)
		{
			log.info("Unable to click on Order History Button due to --" + e.getMessage());
			e.printStackTrace();
		}
		return flag;

	}

	public boolean clickOnCreateButton()
	{
		boolean flag = false;

		try{
			waitForExpectedElement(createButton).click();
			log.info("successfully click on create button");
			flag = true;
		}
		catch (Exception e)
		{
			log.info("Unable to click on create button due to --" + e.getMessage());
			e.printStackTrace();
		}
		return flag;

	}

	public boolean clickOnConfirmButton() {
		boolean flag = false;

		try {
			waitForExpectedElement(confirmButton).click();
			Thread.sleep(2000);
			log.info("successfully click on confirm button");
			flag = true;
		} catch (Exception e) {
			log.info("Unable to click on confirm button due to --" + e.getMessage());
			e.printStackTrace();
		}
		return flag;

	}

	public boolean EnterTheQuantity() {
		boolean flag = false;
		try {
			webDriver.switchTo().defaultContent();
			webDriver.switchTo().frame(waitForExpectedElement(iframe3));
			String QuantityNo = waitForExpectedElement(maxQuantityNo).getText();
			Thread.sleep(2000);
			log.info("Successfully read the quantity" + QuantityNo);
			waitClearAndEnterText(quantityBox, QuantityNo);
			flag = true;

		} catch (Exception e) {
			log.info("Unable to Enter the quantity due to " + e.getMessage());
		}
		return flag;
	}

	public boolean selectbackOrderQuantity() {
		boolean flag = false;
		try {
			webDriver.switchTo().defaultContent();
			webDriver.switchTo().frame(waitForExpectedElement(iframe3));

			List<WebElement> Allrows = visibilityOfAllElementsLocatedBy(rowData);
			log.info("Successfully fetched all the rows");
			System.out.println("All rows are " + Allrows);

			for (int i = 0; i <Allrows.size(); ++i) {
				int j=0;

				if(Allrows.get(i).getText().equals("1")){
					j=i+1;
					String colData = "(//input[@type=\"number\"])" + "[" + j + "]";
					WebElement EnterQuantity = webDriver.findElement(By.xpath(colData));
					EnterQuantity.clear();
					Thread.sleep(3000);
					EnterQuantity.sendKeys("1");
					log.info("Successfully entered the value in the inputbox ");
					String SelectCancellationReason= "(//select[@data-testing-id='reason'])" + "[" + j +"]";
					//WebElement cancelReasonSelect = webDriver.findElement(By.xpath(SelectCancellationReason));
					Select cancellationReasonDropDown = new Select(waitForExpectedElement(By.xpath(SelectCancellationReason)));
					cancellationReasonDropDown.selectByVisibleText("Cancelled by customer");
					log.info("Successfully select the cancellation reason from the dropdown box");
					Thread.sleep(2000);
					System.out.println("Cancellation reason has been selected successfully");
					flag=true;
					break;
				}
			}
		} catch (Exception e) {
			log.info("Unable to Enter the quantity due to " + e.getMessage());
		}
		return flag;

	}

	public boolean enterTheQuantity() {
		boolean flag = false;
		try {


			String QuantityNo = waitForExpectedElement(maxQuantityNo).getText();
			Thread.sleep(2000);
			webDriver.switchTo().defaultContent();
			webDriver.switchTo().frame(waitForExpectedElement(iframe3));
			System.out.println("Successfully switched to Iframe INT SNAP Customer Search");


			log.info("Successfully read the quantity " + QuantityNo);
			waitClearAndEnterText(quantityBox, QuantityNo);
			flag = true;

		} catch (Exception e) {
			log.info("Unable to Enter the quantity due to " + e.getMessage());
		}
//		  finally {
//			webDriver.switchTo().defaultContent();
//		}
		return flag;
	}

	public void AssertReturnSuccessfullyTitle()
	{

		try {


			Thread.sleep( 10000 );
			String Expected= waitForExpectedElement(returnSuccessfullyTitle).getText();
			Assert.assertEquals( "Return successfully created",Expected );

		} catch  (Exception e) {
		e.getMessage();
		}
	}
	public void ClickOnContinueBtn()
	{
		waitForExpectedElement( replaceUnitContinueButton ).click();
	}

	public void CartHeaderTitle()
	{

		try {
			Thread.sleep( 1000 );
			//			WebDriverWait wait = new WebDriverWait( webDriver,20);

			String CartHeadertitle=waitForExpectedElement( CartHeader ).getText();
			Assert.assertEquals( "Shopping Cart",CartHeadertitle );
		} catch (Exception e) {
			e.getMessage();		}
	}
	public void ClickOnCheckoutBtn()
	{
		try {
			webDriver.switchTo().defaultContent();

			webDriver.switchTo().frame(waitForExpectedElement(iframe3));
			log.info("Successfully switch into the frame");
			Thread.sleep( 2000 );

			waitForExpectedElement(replaceUnitCheckout).click();
//			new Actions(getWebDriver()).moveToElement(getWebDriver().findElement(replaceUnitCheckout)).click().build().perform();
			log.info("successfully clicked on checkout button");

		} catch (Exception e) {
			log.info("Some Exception occurred when clicking on checkout button-->>" + e.getMessage());
		}
		finally {
            webDriver.switchTo().defaultContent();
        }
	}

	public void AssertCheckoutHeader()
	{
		try {
			webDriver.switchTo().frame(waitForExpectedElement(iframe3));
			log.info("Successfully switch into the frame");

		String CheckoutHeader=waitForExpectedElement( Checkoutheader ).getText();
		Assert.assertEquals( "Checkout",CheckoutHeader );
		} catch (Exception e) {
			log.info("Some Exception occurred when clicking on checkout button-->>" + e.getMessage());
		}
		finally {
			webDriver.switchTo().defaultContent();
		}
	}

	public void ClickOnFreePaymentMethodRadioBtn()
	{
		try{
			waitForExpectedElement(freePaymentMethod).click();
			log.info("successully clicked on radio button");
		}catch (Exception e)
		{
			log.info("Some Exception while clicking on radio button which is -->>"+e.getMessage());
		}
	}

	public void ClickOnPaymentMethodRadioBtn()
	{
		try{
			webDriver.switchTo().frame(waitForExpectedElement(iframe3));
			waitForExpectedElement(replaceUnitPaymentMethodRadiobtn).click();
			log.info("successully clicked on radio button");
		}catch (Exception e)
		{
			log.info("Some Exception while clicking on radio button which is -->>"+e.getMessage());
		}
//		finally {
//			webDriver.switchTo().defaultContent();
//		}
	}
	public void ClickOnPlaceOrderBtn()
	{
		waitForExpectedElement( placeOrder ).click();
	}

	public String SnapCheckOutHeader()
	{
		IsPageLoaded.waitAllRequest();
		String text = "Not Found";
		try {
			text = getTextFor(snapCheckoutHeader).trim();
			log.info("Successfully fetched the text from Place Order page which is-->>" + text);
		} catch (Exception e) {
			log.error("Unable to fetch text from Place Order page due to-->" + e.getMessage());
			e.printStackTrace();
		}
		return text;
	}

	public void OrderPlaced()
	{
		waitForExpectedElement( placeOrderText ).isDisplayed();
		String PlacedOrderText= waitForExpectedElement( placeOrderText ).getText();
		System.out.println( PlacedOrderText );
	}
	public void ClickOnFaultyOnDeliveryOptn()
	{
		new Actions(getWebDriver()).moveToElement(getWebDriver().findElement(faultOnDelivery)).click().build().perform();
		log.info( "Agent is able to click on Faulty On Delivery" );

	}
	public boolean clickOnReplacePartOption()
	{
		boolean flag = false;

		try{
			new Actions(getWebDriver()).moveToElement(getWebDriver().findElement(orderOptionReplacePart)).click().build().perform();
			log.info( "Agent is able to click on Replace Part Option" );
			flag = true;
		}
		catch (Exception e)
		{
			log.info("Unable to click on Replace Unit Part Due to --- " + e.getMessage());
			e.printStackTrace();
		}
		return flag;

	}

	public boolean clickOnExpressReplacementOption()
	{
		boolean flag = false;

		try{
			new Actions(getWebDriver()).moveToElement(getWebDriver().findElement(orderOptionExpressReplacement)).click().build().perform();
			log.info( "Agent is able to click on Express Replacement Option" );
			flag = true;
		}
		catch (Exception e)
		{
			log.info("Unable to click on Express Replacement Option Due to --- " + e.getMessage());
			e.printStackTrace();
		}
		return flag;

	}

	public boolean clickOnCreateRefundReturnOption()
	{
		boolean flag = false;

		try{
			waitForExpectedElement(orderOptionCreateRefund).click();
			log.info( "Agent is able to click on Create refund return Option" );
			flag = true;
		}
		catch (Exception e)
		{
			log.info("Unable to click on Create refund return option Due to --- " + e.getMessage());
			e.printStackTrace();
		}
		return flag;

	}

	public boolean clickOnReplaceUnitOption()
	{
		boolean flag = false;

		try{
			new Actions(getWebDriver()).moveToElement(getWebDriver().findElement(orderOptionReplaceUnit)).click().build().perform();
			log.info( "Agent is able to click on Replace Unit Option" );
			flag = true;
		}
		catch (Exception e)
		{
			log.info("Unable to click on Replace Unit Option Due to --- " + e.getMessage());
			e.printStackTrace();
		}
		return flag;

	}
	public boolean clickOnContinueButton()
	{
		boolean flag = false;

		try{
			waitForExpectedElement(continueButton).click();
			log.info( "Agent is able to click on Replace Unit Option" );
			flag = true;
		}
		catch (Exception e)
		{
			log.info("Unable to click on Replace Unit Option Due to --- " + e.getMessage());
			e.printStackTrace();
		}
		return flag;
	}

	public boolean selectProperReturnRequestReason()
	{
		boolean flag = false;

		try{
			waitForExpectedElement(returnRequestReason).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(buyersOption)).click();
			log.info("Agent able to select proper fault reason");
			flag = true;
		}
		catch (Exception e)
		{
			log.info("Unable to select proper fault reason Due to --- " + e.getMessage());
			e.printStackTrace();
		}
		return flag;
	}

	public boolean selectProperFaultReason()
	{
		boolean flag = false;

		try{
//			waitForExpectedElement(faultReasonButton).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(faultReasonButton)).click();

//			new Actions(getWebDriver()).moveToElement(getWebDriver().findElement(faultReasonButton)).click().build().perform();
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(faultReason)).click();
			log.info("Agent able to select proper fault reason");
			flag = true;
		}
		catch (Exception e)
		{
			log.info("Unable to select proper fault reason Due to --- " + e.getMessage());
			e.printStackTrace();
		}
		return flag;
	}

	public boolean selectRapidReasonFault()
	{
		boolean flag = false;

		try{
//			waitForExpectedElement(faultReasonButton).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(faultReasonButton)).click();

//			new Actions(getWebDriver()).moveToElement(getWebDriver().findElement(faultReasonButton)).click().build().perform();
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(batteryFailureReason)).click();
			log.info("Agent able to select proper fault reason");
			flag = true;
		}
		catch (Exception e)
		{
			log.info("Unable to select proper fault reason Due to --- " + e.getMessage());
			e.printStackTrace();
		}
		return flag;
	}

	public boolean selectProperRapidReason()
	{
		boolean flag = false;

		try{
//			waitForExpectedElement(faultReasonButton).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(faultReasonButton)).click();

//			new Actions(getWebDriver()).moveToElement(getWebDriver().findElement(faultReasonButton)).click().build().perform();
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(faultReason)).click();
			log.info("Agent able to select proper fault reason");
			flag = true;
		}
		catch (Exception e)
		{
			log.info("Unable to select proper fault reason Due to --- " + e.getMessage());
			e.printStackTrace();
		}
		return flag;
	}

	public boolean selectRefundReturnReason()
	{
		boolean flag = false;

		try{
			waitForExpectedElement(refundReason).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(buyerRemoteOption)).click();
			log.info("Agent able to select proper fault reason");
			flag = true;
		}
		catch (Exception e)
		{
			log.info("Unable to select proper fault reason Due to --- " + e.getMessage());
			e.printStackTrace();
		}
		return flag;
	}

	public String getRapidResponse() {
		IsPageLoaded.waitAllRequest();
		String text = "Not Found";
		try {
			Thread.sleep(3000);
			webDriver.switchTo().defaultContent();
			webDriver.switchTo().frame(waitForExpectedElement(iframe3));
			text = getTextFor(rapidResponse).trim();
			log.info("Successfully fetch out the rapid response");
		} catch (Exception e) {
			log.info("Not able to fetch due to -- " + e.getMessage());
			e.printStackTrace();
		}
//		finally {
//			webDriver.switchTo().defaultContent();
//		}

		return text;
	}

	public String getRMANumber() {

		String text = "Not Found";
		try {
			webDriver.switchTo().defaultContent();
			webDriver.switchTo().frame(waitForExpectedElement(iframe3));
			text = getTextFor(RMANumber).trim();
			log.info("Successfully fetch out the RMA number");
		} catch (Exception e) {
			log.info("Not able to fetch due to -- " + e.getMessage());
			e.printStackTrace();
		}

		return text;
	}

	public String getOrderId() {

		String text = "Not Found";
		try {
			webDriver.switchTo().defaultContent();
			webDriver.switchTo().frame(waitForExpectedElement(iframe3));
			text = getTextFor(orderId).trim();
			log.info("Successfully fetch out the Order Id");
		} catch (Exception e) {
			log.info("Not able to fetch due to -- " + e.getMessage());
			e.printStackTrace();
		}

		return text;
	}

	public void AssertReShipTitle()
	{
		try {
			String Orderno=Props.getProp("zendesk.order.no");
			String Expected= "Reship order number ";
			Assert.assertEquals( waitForExpectedElement(ReplaceUnitTitle).getText(),Expected+Orderno );
			System.out.println( "ReShip Titile Asserted" );
		} catch (Exception e) {
			System.out.println( e.getMessage());
		}
	}

	public String getReturnRequestTitle()
	{
		IsPageLoaded.waitAllRequest();
		String text = "Not Found";
		try {
			text = getTextFor(returnRequestTitle).trim();
			log.info("Successfully fetched the text from Return request page which is-->>" + text);
		} catch (Exception e) {
			log.error("Unable to fetch text from Return request page due to-->" + e.getMessage());
			e.printStackTrace();
		}
		return text;
	}

	public String getFaultErrorMessage()
	{
		IsPageLoaded.waitAllRequest();
		String text = "Not Found";
		try {
			webDriver.switchTo().frame(waitForExpectedElement(iframe3));
			text = getTextFor(faultErrorMessage).trim();
			log.info("Successfully fetched the fault error message which is-->>" + text);
		} catch (Exception e) {
			log.error("Unable to fetch text from Replacement page due to-->" + e.getMessage());
			e.printStackTrace();
		}
		return text;
	}


	public String getReturnSuccessfulTitle()
	{
		IsPageLoaded.waitAllRequest();
		String text = "Not Found";
		try {
			text = getTextFor(returnSuccessfulTitle).trim();
			log.info("Successfully fetched the text from Return request page which is-->>" + text);
		} catch (Exception e) {
			log.error("Unable to fetch text from Return request page due to-->" + e.getMessage());
			e.printStackTrace();
		}
		return text;
	}

	public void VerfiyProductID()
	{
		waitForExpectedElement(productID).isDisplayed();
	}
	public void ClickOnAddToCart()
	{
		waitForExpectedElement( addToCartBtn ).click();
	}
	public void NavigateToCart()
	{
		waitForExpectedElement(ClickOnCart ).click();
	}

	public boolean clickLostInTransit()
	{
		boolean flag = false;

		try{
			new Actions(getWebDriver()).moveToElement(getWebDriver().findElement(lostInTransitOption)).click().build().perform();
			log.info( "Agent is able to click on lost in Transit Option" );
			flag = true;
		}
		catch (Exception e)
		{
			log.info("Unable to click on lost in Transit Option Due to --- " + e.getMessage());
			e.printStackTrace();
		}
		return flag;

	}

	public boolean clickMissingInBoxOption()
	{
		boolean flag = false;

		try{
			new Actions(getWebDriver()).moveToElement(getWebDriver().findElement(missingInBox)).click().build().perform();
			log.info( "Agent is able to click on missing In the Box Option" );
			flag = true;
		}
		catch (Exception e)
		{
			log.info("Unable to click on lost in missing In the Box Option Due to --- " + e.getMessage());
			e.printStackTrace();
		}
		return flag;

	}

	public boolean SelectSalesChannelEbayShark() {
		boolean flag=false;
		try {
			webDriver.switchTo().defaultContent();
			webDriver.switchTo().frame(waitForExpectedElement(By.xpath("(//iframe[contains(@name,'UAT')])[4]")));
			waitForExpectedElement(getRadioBtnEbayShark).click();
			log.info("Agent is able to select the Shark sales channel");
			flag = true;
		}catch (Exception e){
			log.error(ExceptionUtils.getStackTrace(e.fillInStackTrace()));
		}
		return flag;
	}
	public boolean enterPhoneNumber(String key) {

		boolean flag = false;

		try {
			waitForExpectedElement(phoneNumber).sendKeys(Props.getProp(key));
			log.info("Agent is able to enter the phone no");
			flag = true;
		} catch (Exception e) {
			log.info("Agent is unable to enter phone no due to " + e.getMessage());
			e.printStackTrace();

		}

		return flag;
	}

	public boolean enterEbayEmailId(String key) {

		boolean flag = false;

		try {
			waitForExpectedElement(cutomerEmailId).sendKeys(Props.getProp(key));
			log.info("Agent is able to enter the email Id");
			flag = true;
		} catch (Exception e) {
			log.info("Agent is unable to enter email Id due to " + e.getMessage());
			e.printStackTrace();

		}

		return flag;
	}

	public boolean verifyOrderOptionButton()
	{
		boolean flag =false;
		String text="not found";
		try {
			webDriver.switchTo().defaultContent();
			webDriver.switchTo().frame(waitForExpectedElement(iframe3));

			JavascriptExecutor js = (JavascriptExecutor) webDriver;
			js.executeScript("window.scrollBy(0,2000)");

			text=waitForExpectedElement(orderStatus).getText().trim();
			WebElement button = waitForExpectedElement(orderOptionButton);
			flag = (text.equals("Back-order") && button.isEnabled()) ? true : (button.isEnabled() ? false : true);

		} catch (Exception e) {
			log.info("unable to extract order status --- " + e.getMessage());
			e.printStackTrace();
		}
		return flag;
	}




}
