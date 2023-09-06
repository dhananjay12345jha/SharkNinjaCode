package com.salmon.test.page_objects.gui;

import java.util.List;

import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.salmon.test.framework.PageObject;
import com.salmon.test.step_definitions.gui.RegisterMyGuaranteePageSteps;

public class ProductOverviewPage extends PageObject {
	
	private final By heading = By.xpath("//div[@class='row account-main']/div/h1");
	private final By registeredGuaranteeNumber=By.xpath("//div[@class='product-list-content']//div");
	//div[@class='product-list-content']//div
	private static final Logger log = LoggerFactory.getLogger(ProductOverviewPage.class);
	public String getHeading() {
		IsPageLoaded.waitAllRequest();
		wait.until(ExpectedConditions.visibilityOfElementLocated(heading));
		  return waitForExpectedElement(heading).getText();
	  }
  
public Boolean verifyRegisteredGuarantee() {
				boolean flag=false;
				try {
					System.out.println("from registration process--"+RegisterMyGuaranteePageSteps.Registered_Guarantee_number);
				
				//flag=(RegisterMyGuaranteePageSteps.Registered_Guarantee_number).contentEquals(waitForExpectedElement(registeredGuaranteeNumber).getText().substring(7).trim());
					 List<WebElement> elements = webDriver.findElements(registeredGuaranteeNumber);
					 System.out.println("size of list"+elements.size());
					for (WebElement element : elements) {
						System.out.println("element name -->" + element.getText());
						if (element.getText().contains(RegisterMyGuaranteePageSteps.Registered_Guarantee_number)) {
							flag = true;
							log.info("Successfully validated the registered Guarantee" + element.getText());
							break;
						}
					}
				} catch (Exception e) {
					log.info("Some exception occurred while comparing registered guarantee number-->>"+e.getMessage());
				}
				return flag;
			}
	  }

