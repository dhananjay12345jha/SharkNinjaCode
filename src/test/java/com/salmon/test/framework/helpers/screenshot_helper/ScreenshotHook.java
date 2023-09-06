package com.salmon.test.framework.helpers.screenshot_helper;

import static com.salmon.test.framework.helpers.WebDriverHelper.REAL_DRIVER;
import static com.salmon.test.framework.helpers.WebDriverHelper.getWebDriver;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.ScenarioHook;
import com.salmon.test.framework.helpers.WebDriverHelper;
import com.salmon.test.step_definitions.gui.PDPPageSteps;
import com.salmon.test.step_definitions.gui.SNCA.CartPageStepsSNCA;
import com.salmon.test.step_definitions.gui.SNCA.PDPPageStepsSNCA;
import cucumber.api.Scenario;
import cucumber.api.java.After;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;


public class ScreenshotHook {
    private static final Logger LOG = LoggerFactory.getLogger(ScreenshotHook.class);
    BrowserStackhelper browserStackhelper = new BrowserStackhelper();

    //Not Execute for any api or mobile tests
    @After
    public void embedScreenshot(Scenario scenario) {
        Collection<String> tags = scenario.getSourceTagNames();
        if (tags.contains("@api") || tags.contains("@database")) {
            return;
        }
        try {
            Map<String, Object> screenShots = ScreenshotHelper.getScreenShotsForCurrentTest();
            for (Map.Entry<String, Object> screenShot : screenShots.entrySet()) {
                scenario.write(screenShot.getKey());
                scenario.embed((byte[]) screenShot.getValue(), "image/png");
            }

            ScreenshotHelper.tidyUpAfterTestRun();

            if (scenario.isFailed() && WebDriverHelper.getWebDriver() != null) {
                scenario.write(WebDriverHelper.getWebDriver().getCurrentUrl());
                String videoLink = Props.getdevice("execution.on.device").toLowerCase();
                if (!videoLink.contains("local") || !videoLink.contains("headless")) {
                    scenario.write(browserStackhelper.getVideoLink());
                }
//                byte[] screenShot = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
//                scenario.embed(screenShot, "image/png");
                Object windowPixel = ((JavascriptExecutor) getWebDriver()).executeScript("return window.devicePixelRatio");
                String value = String.valueOf(windowPixel);
                float dpr = Float.parseFloat(value);
                Screenshot screenshot = new AShot().
                        shootingStrategy(ShootingStrategies.viewportPasting(ShootingStrategies.scaling(dpr), 100)).
                        takeScreenshot(getWebDriver());
                try (ByteArrayOutputStream byteArray = new ByteArrayOutputStream()) {
                    ImageIO.write(screenshot.getImage(), "png", byteArray);
                    byteArray.flush();
                    scenario.embed(byteArray.toByteArray(), "image/png");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (WebDriverException | ClassCastException wde) {
            LOG.error(wde.getMessage());
        } catch (Exception e) {
            LOG.error(e.getMessage());
        } finally {
            //getWebDriver().switchTo().defaultContent();

//            if (tags.contains("@desktop") || !WebDriverHelper.VENDOR.equalsIgnoreCase("motel")) {
//                getWebDriver().quit();
//                WebDriverHelper.REAL_DRIVER = null;
//            }

            if (getWebDriver() != null || REAL_DRIVER != null) {
                getWebDriver().quit();
                WebDriverHelper.REAL_DRIVER = null;
            }

            PDPPageStepsSNCA.productTitle.clear();
            PDPPageStepsSNCA.productModel.clear();
            PDPPageStepsSNCA.productPrice.clear();
            CartPageStepsSNCA.newPrice.clear();
            CartPageStepsSNCA.discountPriceAtOrder = 0;
            CartPageStepsSNCA.promoCodeShown = "";
            PDPPageSteps.productPrice.clear();

        }
    }
}


