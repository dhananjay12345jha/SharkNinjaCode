package com.salmon.test.page_objects.gui.MicrositeSharkUS;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.utils.IsPageLoaded;
import com.salmon.test.page_objects.gui.SNCA.SecureCheckoutPageSNCA;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MSSecureCheckoutPage extends PageObject {

    private static final By radioButtonContinueAsAGuest = By.xpath("//input[@id=\"guest\"]");
    private static final By payByInstallmentButton = By.xpath("//input[@type=\"radio\" and @id=\"BRAINTREE_CREDITCARD_MULTIPAY\"]");

    private static final Logger log = LoggerFactory.getLogger(MSSecureCheckoutPage.class);

    public boolean selectPayByInstallmentOption() {
        IsPageLoaded.waitAllRequest();
        boolean flag = false;
        waitForPage();
        int count = 0;
        while (count < 6) {
            try {
                WebElement element = getWebDriver().findElement(payByInstallmentButton);
                new Actions(getWebDriver()).moveToElement(element).click().build().perform();

                if (element.isSelected()) {
                    flag = true;
                    log.info("Successfully selected the radio button \"PayByInstallment\"");
                    break;
                } else {
                    log.info("Unable to select the radio button PayByInstallment going to try one more time");
                    count++;
                }
            } catch (StaleElementReferenceException e1) {
                log.error(e1.getMessage() + " going to try one more time");
            }catch(NotFoundException e1){
                log.error("Unbale to find element now may be page is getting load trying again-->>"+ ExceptionUtils.getStackTrace(e1));
            }
            catch (Exception e) {
                log.error("Unable to select the radio button \"PayByInstallment\" due to-->" + e.getMessage());
                e.printStackTrace();
                break;
            }
            count++;
        }
        return flag;
    }
}
