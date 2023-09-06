package com.salmon.test.framework.helpers;

import static org.openqa.selenium.ie.InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS;

import com.browserstack.local.Local;
import com.salmon.test.enums.PermittedSiteMode;
import com.salmon.test.framework.PageObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import cucumber.api.CucumberOptions;
import gherkin.ast.Scenario;
import gherkin.events.CucumberEvent;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebDriverHelper extends EventFiringWebDriver {

    private static final Logger LOG = LoggerFactory
            .getLogger(WebDriverHelper.class);

    private static final Dimension MOBILE_WINDOW_SIZE = new Dimension(411, 731);
    private static final Dimension TABLET_WINDOW_SIZE = new Dimension(800, 1024);
    private static final String browserstackLocal;
    private static final String browserstackLocalIdentifier;
    public static String VENDOR;
    public static RemoteWebDriver REAL_DRIVER = null;
    public static Local bsLocal = new Local();
    public static String BS_SessionId;
    private static final Thread CLOSE_THREAD = new Thread() {
        @Override
        public void run() {
            REAL_DRIVER.quit();
        }
    };
    private static String BS_USERNAME;

    private static String LT_USERNAME;

    private static String LT_ACCESS_KEY;
    private static String GRID_URL;


    private static String BS_ACCESS_KEY;
    private static String BSJenkins;

    private static String LambdaJenkins;
    private static String BROWSER;
    private static String PLATFORM;
    private static String DRIVER_PATH;
    private static String FILE_SEPARATOR;
    private static String SELENIUM_HOST;
    private static String SELENIUM_PORT;
    private static String SELENIUM_REMOTE_URL;
    private static String bsBrowserName, bsDeviceName, bsRealDeviceFlag, bsOSVersion, bsOSName;

    static {
        //Props.loadRunConfigProps("/environment.properties");
        SELENIUM_HOST = System.getProperty("driverhost");
        SELENIUM_PORT = System.getProperty("driverport");
        FILE_SEPARATOR = System.getProperty("file.separator");
        PLATFORM = Props.getProp("platform");
        BROWSER = System.getProperty("browser", Props.getProp("browser"));
        VENDOR = Props.getProp("vendor");

        BSJenkins = System.getenv("BROWSERSTACK_USERNAME");
        if (BSJenkins == null) {
            BS_USERNAME = Props.getProp("bsusername");
            BS_ACCESS_KEY = Props.getProp("bsaccesskey");
        }

        else {
            BS_USERNAME = System.getenv("BROWSERSTACK_USERNAME");
            BS_ACCESS_KEY = System.getenv("BROWSERSTACK_ACCESS_KEY");

        }
        browserstackLocal = System.getenv("BROWSERSTACK_LOCAL");
        browserstackLocalIdentifier = System.getenv("BROWSERSTACK_LOCAL_IDENTIFIER");

        LT_USERNAME = Props.getProp("lambdaUsername");
        LT_ACCESS_KEY = Props.getProp("lambdaAccessKey");
        GRID_URL = Props.getProp("lambdaUrl");

        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
    }

    private WebDriverHelper() {
        super(REAL_DRIVER);
    }


    public static WebDriver getWebDriver() {
        return REAL_DRIVER;
    }

    // below is working - just depends if we feel the need to use it
    public static WebDriver getSpecificWebDriver(PermittedSiteMode MODE) {
        //Options for execution devivces
        //Iphone,Ipad,Nexus,BSChorme,BSEdge,LocalChrome,LocalEdge
        //HeadlessChrome
        if (Props.getEnvProperty("profile.runMode").equalsIgnoreCase("Headless")) {
            startHeadlessChromeDriver();
        } else if (UrlBuilder.isIphone()) {
            bsBrowserName = Props.getEnvProperty("device.iphone.browserName");
            bsDeviceName = Props.getEnvProperty("device.iphone.deviceName");
            bsRealDeviceFlag = Props.getEnvProperty("device.iphone.realMobileFlag");
            bsOSVersion = Props.getEnvProperty("device.iphone.osVersion");
            startBrowserStackDriver();
        } else if (UrlBuilder.isIpad()) {
            bsBrowserName = Props.getEnvProperty("device.ipad.browserName");
            bsDeviceName = Props.getEnvProperty("device.ipad.deviceName");
            bsRealDeviceFlag = Props.getEnvProperty("device.ipad.realMobileFlag");
            bsOSVersion = Props.getEnvProperty("device.ipad.osVersion");
            startBrowserStackDriver();

        } else if (UrlBuilder.isNexus()) {
            bsBrowserName = Props.getEnvProperty("device.nexus6.browserName");
            bsDeviceName = Props.getEnvProperty("device.nexus6.deviceName");
            bsRealDeviceFlag = Props.getEnvProperty("device.nexus6.realMobileFlag");
            startBrowserStackDriver();
        } else if (UrlBuilder.isBsChrome()) {
            bsBrowserName = Props.getEnvProperty("device.bschrome.browserName");
            bsDeviceName = Props.getEnvProperty("device.bschrome.deviceName");
            bsRealDeviceFlag = Props.getEnvProperty("device.bschrome.realMobileFlag");
            bsOSVersion = Props.getEnvProperty("device.bschrome.osVersion");
            startBrowserStackDriver();
            REAL_DRIVER.manage().window().maximize();

        } else if (UrlBuilder.isBsedge()) {
            bsBrowserName = Props.getEnvProperty("device.bsedge.browserName");
            bsDeviceName = Props.getEnvProperty("device.bsedge.deviceName");
            bsRealDeviceFlag = Props.getEnvProperty("device.bsedge.realMobileFlag");
            bsOSVersion = Props.getEnvProperty("device.bsedge.osVersion");
            startBrowserStackDriver();

        } else if (UrlBuilder.isLambdaTestChrome()) {
                System.out.println("grid url is" +LT_USERNAME + LT_ACCESS_KEY + GRID_URL );
                startLambdaTestDriver();
                REAL_DRIVER.manage().window().maximize();

        } else if (UrlBuilder.isBsedge()) {
        bsBrowserName = Props.getEnvProperty("device.bsedge.browserName");
        bsDeviceName = Props.getEnvProperty("device.bsedge.deviceName");
        bsRealDeviceFlag = Props.getEnvProperty("device.bsedge.realMobileFlag");
        bsOSVersion = Props.getEnvProperty("device.bsedge.osVersion");
        startBrowserStackDriver();


        REAL_DRIVER.manage().window().maximize();

        } else if (UrlBuilder.islocaledge()) {
            startEdgeDriver();
            REAL_DRIVER.manage().window().maximize();
        } else if (UrlBuilder.isLocalChrome()) {
            startChromeDriver();
            if (UrlBuilder.isMobile()) {
                REAL_DRIVER.manage().window().setSize(MOBILE_WINDOW_SIZE);
            } else if (UrlBuilder.isTablet()) {
                REAL_DRIVER.manage().window().setSize(TABLET_WINDOW_SIZE);
            } else {
                REAL_DRIVER.manage().window().maximize();
            }
        } else if (UrlBuilder.isHeadlessChrome()) {
            startHeadlessChromeDriver();
        } else {
            throw new IllegalArgumentException(
                    "Browser type not supported: " + System.getProperty("browserName"));
        }
        LOG.info("WebDriver start up finished.");
        REAL_DRIVER.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        PageObject.setWebDriver(REAL_DRIVER);
        return REAL_DRIVER;
    }

    private static String getDriverPath() {
        if (BROWSER.equals("chrome") && PLATFORM.contains("win")) {
            DRIVER_PATH = "tools" + FILE_SEPARATOR + "chromedriver"
                    + FILE_SEPARATOR + PLATFORM + FILE_SEPARATOR
                    + "chromedriver.exe";
        } else if (BROWSER.equals("chrome") && PLATFORM.contains("linux")) {
            DRIVER_PATH = "tools" + FILE_SEPARATOR + "chromedriver"
                    + FILE_SEPARATOR + PLATFORM + FILE_SEPARATOR
                    + "chromedriver";

        } else if (BROWSER.equals("iexplore") && PLATFORM.contains("win")) {
            DRIVER_PATH = "tools" + FILE_SEPARATOR + "iedriver"
                    + FILE_SEPARATOR + PLATFORM + FILE_SEPARATOR
                    + "IEDriverServer.exe";
        } else if (BROWSER.equals("phantomjs") && PLATFORM.contains("linux")) {
            DRIVER_PATH = "tools" + FILE_SEPARATOR + "phantomjs"
                    + FILE_SEPARATOR + PLATFORM + FILE_SEPARATOR
                    + "phantomjs";
        } else if (BROWSER.equals("firefox") && PLATFORM.contains("win")) {
            DRIVER_PATH = "tools" + FILE_SEPARATOR + "geckodriver"
                    + FILE_SEPARATOR + PLATFORM + FILE_SEPARATOR
                    + "geckodriver.exe";
        }

        return DRIVER_PATH;
    }

    private static void startChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = getChromeOptions();
        if (SELENIUM_HOST == null) {
            REAL_DRIVER = new ChromeDriver(options);
        } else {
            try {
                REAL_DRIVER = getRemoteWebDriver(options);
            } catch (MalformedURLException e) {
                LOG.error(SELENIUM_REMOTE_URL + " Error " + e.getMessage());
            }
        }
        //REAL_DRIVER.manage().window().setSize(MOBILE_WINDOW_SIZE);

    }

    private static void startEdgeDriver() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = getEdgeOptions();
        if (SELENIUM_HOST == null) {
            REAL_DRIVER = new EdgeDriver(options);
        } else {
            try {
                REAL_DRIVER = getRemoteWebDriver(options);
            } catch (MalformedURLException e) {
                LOG.error(SELENIUM_REMOTE_URL + " Error " + e.getMessage());
            }
        }
        REAL_DRIVER.manage().window().maximize();
    }

    private static void startHeadlessChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = getChromeOptionsHeadless();
        if (SELENIUM_HOST == null) {
            REAL_DRIVER = new ChromeDriver(options);
        } else {
            try {
                REAL_DRIVER = getRemoteWebDriver(options);
            } catch (MalformedURLException e) {
                LOG.error(SELENIUM_REMOTE_URL + " Error " + e.getMessage());
            }
        }
        REAL_DRIVER.manage().window().maximize();
    }

    private static void startIEDriver() {
        InternetExplorerOptions options = getInternetExplorerOptions();
        if (SELENIUM_HOST == null) {
            REAL_DRIVER = new InternetExplorerDriver(options);
        } else {
            try {
                REAL_DRIVER = getRemoteWebDriver(options);
            } catch (MalformedURLException e) {
                LOG.error(SELENIUM_REMOTE_URL + " Error " + e.getMessage());
            }
        }

        REAL_DRIVER.manage().window().maximize();
    }

    private static void startFireFoxDriver() {
        FirefoxOptions options = getFireFoxOptions();
        if (SELENIUM_HOST == null) {
            System.out.println("starting driver");
            REAL_DRIVER = new FirefoxDriver(options);
            System.out.println("after starting driver");
        } else {
            try {
                REAL_DRIVER = getRemoteWebDriver(options);
            } catch (MalformedURLException e) {
                LOG.error(SELENIUM_REMOTE_URL + " Error " + e.getMessage());
            }
        }
        REAL_DRIVER.manage().window().maximize();
    }

    private static void startPhantomJsDriver() {
        DesiredCapabilities capabilities = getPhantomJsCapabilities();
        if (SELENIUM_HOST == null) {
            REAL_DRIVER = new PhantomJSDriver(capabilities);
        } else {
            try {
                REAL_DRIVER = getRemoteWebDriver(capabilities);
            } catch (MalformedURLException e) {
                LOG.error(SELENIUM_REMOTE_URL + " Error " + e.getMessage());
            }
        }
        REAL_DRIVER.manage().window().maximize();
    }

    // ******************************** MoTeL methods ********************************* //
    private static void startAppiumMoTelDriver() {
        DesiredCapabilities capabilities = getMoTelDesiredCapabilities();
        if (VENDOR.equalsIgnoreCase("Motel")) {
            if (REAL_DRIVER != null) {
                return;
            }
            try {
                System.out.println("Capabilities are:" + capabilities);
                REAL_DRIVER = new RemoteWebDriver(new URL(Props.getProp("motelTestURL")), capabilities);
            } catch (MalformedURLException e) {
                System.out.println("\n ******************* ERROR ");
                e.printStackTrace();
            }
        }
    }

    private static void startBrowserStackDriver() {
        DesiredCapabilities capabilities;
        capabilities = getBrowserStackDesiredCapabilities();
        if (VENDOR.equalsIgnoreCase("browserstack")) {
            if (REAL_DRIVER != null) {
                return;
            }
            try {
                System.out.println("Capabilities are:" + capabilities);
                REAL_DRIVER = new RemoteWebDriver(new URL("https://" + BS_USERNAME + ":" + BS_ACCESS_KEY
                        + "@hub-cloud.browserstack.com/wd/hub"), capabilities);
                BS_SessionId = REAL_DRIVER.getSessionId().toString();
            } catch (MalformedURLException e) {
                System.out.println("\n ******************* ERROR ");
                e.printStackTrace();
            }
        }
    }

    private static void startLambdaTestDriver(){
        DesiredCapabilities capabilities;
        capabilities = getLambdaTestDesiredCapabilities();
        try {
            REAL_DRIVER = new RemoteWebDriver(new URL("https://" + LT_USERNAME + ":" + LT_ACCESS_KEY + GRID_URL), capabilities);
            System.out.println("-------Lambda Test----------");
        } catch (MalformedURLException e) {
            System.out.println("Invalid grid URL");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    private static DesiredCapabilities getLambdaTestDesiredCapabilities(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("browserVersion", "104.0");
        capabilities.setCapability("platform", "win10"); // If this cap isn't specified, it will just get any available one.
        capabilities.setCapability("build", "LambdaTestSampleApp");
        capabilities.setCapability("name", "LambdaTestJavaSample");
        capabilities.setCapability("tunnel",true);
        capabilities.setAcceptInsecureCerts(true);
        capabilities.setJavascriptEnabled(true);
        capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
                UnexpectedAlertBehaviour.IGNORE);
        return capabilities;

    }

    //capabilities values for browserName, device, realMobile and os_version taken from config file
    private static DesiredCapabilities getBrowserStackDesiredCapabilities() {
        HashMap<String, String> bsLocalArgs = new HashMap<>();
        bsLocalArgs.put("key", Props.getProp("bsaccesskey"));
        try {
            if (!bsLocal.isRunning()) {
                bsLocal.start(bsLocalArgs);
                LOG.info("**********----Browser Stack Local Started-----********");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("os", bsOSName);
        caps.setCapability("os_version", bsOSVersion);
        caps.setCapability("browser", bsBrowserName);
        caps.setCapability("browser_version", "latest");
        caps.setCapability("project", System.getProperty("profileId"));
        caps.setCapability("device", bsDeviceName);
        caps.setCapability("name", "SharkNinja Test-" + ScenarioHook.getScenarioName());
        caps.setCapability("browserstack.local", "true");
        caps.setCapability("browserstack.networkLogs", "true");
        caps.setCapability("browserstack.selenium_version", "3.14.0");
        caps.setCapability("acceptSslCert", "true");
        caps.setCapability("autoAcceptAlerts", "true");
        caps.setCapability("autoGrantPermissions", "true");
        caps.setCapability("realMobile", bsRealDeviceFlag);
        if (bsBrowserName.toLowerCase().contains("chrome") ) {
            caps.setCapability(ChromeOptions.CAPABILITY, getChromeOptions());
            if(UrlBuilder.isDesktop()){
                caps.setCapability("resolution", "1440x900");
            }
        } else if (bsBrowserName.toLowerCase().contains("edge")) {
            caps.setCapability(ChromeOptions.CAPABILITY, getEdgeOptions());
           if(UrlBuilder.isDesktop()) {
            caps.setCapability("resolution", "1366x768");
            }
        } else if (bsBrowserName.toLowerCase().contains("safari")) {
            caps.setCapability(ChromeOptions.CAPABILITY, getSafariOptions());
        }
        return caps;
    }

    //capabilities values for browserName, device, realMobile and os_version taken from config file
    private static DesiredCapabilities getLambdaDesiredCapabilities() {
//        HashMap<String, String> bsLocalArgs = new HashMap<>();
//        bsLocalArgs.put("key", Props.getProp("lambdaAccessKey"));
//        try {
//            if (!bsLocal.isRunning()) {
//                bsLocal.start(bsLocalArgs);
//                LOG.info("**********----Browser Stack Local Started-----********");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browserName", bsBrowserName);
        caps.setCapability("version", bsOSVersion);
        caps.setCapability("platform", bsDeviceName); // If this cap isn't specified, it will just get any available one.
        caps.setCapability("build", "LambdaTestSampleApp");
        caps.setCapability("name", "SharkNinja Test-" + ScenarioHook.getScenarioName());
        caps.setCapability("version", "latest");
        caps.setCapability("projectName", System.getProperty("profileId"));
        caps.setCapability("video",true);
        caps.setCapability("visual",true);
        caps.setCapability("network",true);
        caps.setCapability("console",true);

      //  caps.setCapability("os", bsOSName);
       // caps.setCapability("os_version", bsOSVersion);
       // caps.setCapability("browser", bsBrowserName);


       // caps.setCapability("device", bsDeviceName);
       // caps.setCapability("name", "SharkNinja Test-" + ScenarioHook.getScenarioName());
      //  caps.setCapability("browserstack.local", "true");
        //caps.setCapability("browserstack.networkLogs", "true");
        //caps.setCapability("browserstack.selenium_version", "3.14.0");
       // caps.setCapability("acceptSslCert", "true");
       // caps.setCapability("autoAcceptAlerts", "true");
      //  caps.setCapability("autoGrantPermissions", "true");
       // caps.setCapability("realMobile", bsRealDeviceFlag);
//        if (bsBrowserName.toLowerCase().contains("chrome") ) {
//            caps.setCapability(ChromeOptions.CAPABILITY, getChromeOptions());
//            if(UrlBuilder.isDesktop()){
//                caps.setCapability("resolution", "1440x900");
//            }
//        } else if (bsBrowserName.toLowerCase().contains("edge")) {
//            caps.setCapability(ChromeOptions.CAPABILITY, getEdgeOptions());
//            if(UrlBuilder.isDesktop()) {
//                caps.setCapability("resolution", "1366x768");
//            }
//        } else if (bsBrowserName.toLowerCase().contains("safari")) {
//            caps.setCapability(ChromeOptions.CAPABILITY, getSafariOptions());
//        }
        return caps;
    }

    private static DesiredCapabilities getMoTelDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("newCommandTimeout", Props.getProp("motelTimeOut"));
        capabilities.setPlatform(Platform.ANY);
        capabilities.setCapability("browserName", Props.getProp("motel_browser"));
        capabilities.setCapability("motelio:serial", Props.getProp("motel_device_id"));
        capabilities.setCapability("motelio:token", Props.getProp("user_token"));
        return capabilities;
    }

    private static ChromeOptions getChromeOptions() {
        LoggingPreferences logs = new LoggingPreferences();
        logs.enable(LogType.DRIVER, Level.OFF);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-web-security");
        chromeOptions.addArguments("--test-type");
        chromeOptions.addArguments("allow-running-insecure-content");
        chromeOptions.setExperimentalOption("w3c", false);
        //This is for Certificate issue in CA webshop:Rita Singh
        chromeOptions.addArguments("--ignore-ssl-errors=yes");
        chromeOptions.addArguments("--ignore-certificate-errors");
//        chromeOptions.addArguments("--incognito");
        return chromeOptions;
    }

    private static SafariOptions getSafariOptions() {
        LoggingPreferences logs = new LoggingPreferences();
        logs.enable(LogType.DRIVER, Level.OFF);
        SafariOptions safariOptions = new SafariOptions();
        safariOptions.setUseTechnologyPreview(false);
        safariOptions.setCapability("disable-web-security", true);
        safariOptions.setCapability("test-type", false);
        safariOptions.setCapability("allow-running-insecure-content", true);
        safariOptions.setCapability("w3c", false);
        //This is for Certificate issue in CA webshop:Rita Singh
        safariOptions.setCapability("ignore-ssl-errors", true);
        safariOptions.setCapability("ignore-certificate-errors", true);
        return safariOptions;
    }

    private static EdgeOptions getEdgeOptions() {
        LoggingPreferences logs = new LoggingPreferences();
        logs.enable(LogType.DRIVER, Level.OFF);
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.setCapability("browserName", "MicrosoftEdge");
        edgeOptions.setPageLoadStrategy("normal");
        edgeOptions.setCapability("disable-web-security", false);
        edgeOptions.setCapability("test-type", false);
        edgeOptions.setCapability("allow-running-insecure-content", true);
        edgeOptions.setCapability("w3c", false);
        //This is for Certificate issue in CA webshop:Rita Singh
        edgeOptions.setCapability("ignore-ssl-errors", true);
        edgeOptions.setCapability("ignore-certificate-errors", true);

        return edgeOptions;
    }

    private static ChromeOptions getChromeOptionsHeadless() {
        LoggingPreferences logs = new LoggingPreferences();
        logs.enable(LogType.DRIVER, Level.OFF);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-web-security");
        chromeOptions.addArguments("--test-type");
        chromeOptions.addArguments("allow-running-insecure-content");
        chromeOptions.setExperimentalOption("w3c", false);
        //This is for Certificate issue in CA webshop:Rita Singh
        chromeOptions.addArguments("--ignore-ssl-errors=yes");
        chromeOptions.addArguments("--ignore-certificate-errors");
//        chromeOptions.addArguments("--incognito");
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--window-size=1920,1080");
        chromeOptions.addArguments("user-agent=\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36\"");

        return chromeOptions;
    }

    private static FirefoxOptions getFireFoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        FirefoxProfile profile = new FirefoxProfile();
        profile.setAcceptUntrustedCertificates(true);
        profile.setAssumeUntrustedCertificateIssuer(true);
        profile.setPreference("network.cookie.cookieBehavior", 1);
        profile.setPreference("startup.homepage_welcome_url.additional", "");
        profile.setPreference("network.proxy.type", 0);
        options.setCapability(FirefoxDriver.PROFILE, profile);
        options.setCapability("marionette", true);
        options.setCapability("platform", "WINDOWS");
        options.setCapability("disable-restore-session-state", true);
        options.setCapability("acceptInsecureCerts", true);
        return options;
    }

    private static InternetExplorerOptions getInternetExplorerOptions() {
        LoggingPreferences logs = new LoggingPreferences();
        logs.enable(LogType.DRIVER, Level.OFF);
        InternetExplorerOptions options = new InternetExplorerOptions();
        options.setCapability(INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        return options;
    }

    private static DesiredCapabilities getPhantomJsCapabilities() {
        LoggingPreferences logs = new LoggingPreferences();
        logs.enable(LogType.DRIVER, Level.OFF);
        DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
        capabilities.setCapability(CapabilityType.LOGGING_PREFS, logs);
        capabilities
                .setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                        getDriverPath());
        return capabilities;
    }

    private static RemoteWebDriver getRemoteWebDriver(DesiredCapabilities capabilities)
            throws MalformedURLException {
        SELENIUM_REMOTE_URL = "http://" + SELENIUM_HOST + ":" + SELENIUM_PORT + "/wd/hub";
        LOG.info(SELENIUM_REMOTE_URL + " Checking Selenium Remote URL");
        return new RemoteWebDriver(new URL(SELENIUM_REMOTE_URL), (capabilities));
    }

    private static RemoteWebDriver getRemoteWebDriver(ChromeOptions options)
            throws MalformedURLException {
        SELENIUM_REMOTE_URL = "http://" + SELENIUM_HOST + ":" + SELENIUM_PORT + "/wd/hub";
        LOG.info(SELENIUM_REMOTE_URL + " Checking Selenium Remote URL");
        return new RemoteWebDriver(new URL(SELENIUM_REMOTE_URL), (options));
    }

    private static RemoteWebDriver getRemoteWebDriver(EdgeOptions options)
            throws MalformedURLException {
        SELENIUM_REMOTE_URL = "http://" + SELENIUM_HOST + ":" + SELENIUM_PORT + "/wd/hub";
        LOG.info(SELENIUM_REMOTE_URL + " Checking Selenium Remote URL");
        return new RemoteWebDriver(new URL(SELENIUM_REMOTE_URL), (options));
    }

    private static RemoteWebDriver getRemoteWebDriver(InternetExplorerOptions options)
            throws MalformedURLException {
        SELENIUM_REMOTE_URL = "http://" + SELENIUM_HOST + ":" + SELENIUM_PORT + "/wd/hub";
        LOG.info(SELENIUM_REMOTE_URL + " Checking Selenium Remote URL");
        return new RemoteWebDriver(new URL(SELENIUM_REMOTE_URL), (options));
    }

    private static RemoteWebDriver getRemoteWebDriver(FirefoxOptions options)
            throws MalformedURLException {
        SELENIUM_REMOTE_URL = "http://" + SELENIUM_HOST + ":" + SELENIUM_PORT + "/wd/hub";
        LOG.info(SELENIUM_REMOTE_URL + " Checking Selenium Remote URL");
        return new RemoteWebDriver(new URL(SELENIUM_REMOTE_URL), (options));
    }

    private static RemoteWebDriver getRemoteWebDriver(SafariOptions options)
            throws MalformedURLException {
        SELENIUM_REMOTE_URL = "http://" + SELENIUM_HOST + ":" + SELENIUM_PORT + "/wd/hub";
        LOG.info(SELENIUM_REMOTE_URL + " Checking Selenium Remote URL");
        return new RemoteWebDriver(new URL(SELENIUM_REMOTE_URL), (options));
    }

    @Override
    public void close() {
        if (Thread.currentThread() != CLOSE_THREAD) {
            throw new UnsupportedOperationException(
                    "You shouldn't close this WebDriver. It's shared and will close when the JVM exits.");
        }
        super.close();
    }
}

