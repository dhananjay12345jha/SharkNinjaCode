package com.salmon.test.framework.helpers;

import com.salmon.test.enums.PermittedSiteMode;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UrlBuilder {

  private static final Logger LOG = LoggerFactory.getLogger(UrlBuilder.class);
  private static final String RUN_CONFIG_PROPERTIES = "/environment.properties";
  private static URL basePath;
  private static URL apiUrl;

  static {
    try {
      //Props.loadRunConfigProps(RUN_CONFIG_PROPERTIES);
      if(Props.getExecutionEnv().equalsIgnoreCase("UAT"))
      basePath = new URL(Props.getProp("site.uat.url"));
      else if(Props.getExecutionEnv().equalsIgnoreCase("UATA"))
        basePath = new URL(Props.getProp("site.uata.url"));
      else basePath = new URL(Props.getProp("site.url"));
      apiUrl = new URL(Props.getProp("api.url"));
      
    } catch (MalformedURLException e) {
      LOG.error(e.getMessage());
    }

  }

  public static void startAtHomePage()  {
    WebDriverHelper.getSpecificWebDriver(PermittedSiteMode.DESKTOP);
    WebDriverHelper.getWebDriver().navigate().to((basePath));
   // WebDriverHelper.getWebDriver().manage().deleteAllCookies();
  }

  public static void navigateToTheBaseUrl(){
    WebDriverHelper.getWebDriver().navigate().to(basePath);
  }

  public static void navigateToGivenUrl(String url){
    WebDriverHelper.getWebDriver().navigate().to(url);
  }

  public static void startAtMobileHomePage() {
    WebDriverHelper.getSpecificWebDriver(PermittedSiteMode.MOBILE);
    WebDriverHelper.getWebDriver().navigate().to((basePath));
  }

  public static URL getApiUrlForEndPoint(String endpoint) {
    return createApiUrl(endpoint);
  }

  public static URI getBasePathURI() {
    return URI.create(Props.getProp("api.url"));
  }


  public static String getUrl(String applicationUrl) {
    return Props.getProp(applicationUrl);
  }

  public static URL createApiUrl(String endpoint) {
    try {
      return new URL(apiUrl.getProtocol(), apiUrl.getHost(), apiUrl.getPort(), endpoint);
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }


  public static URL createUrl(String path) {
    try {
      return new URL(basePath.getProtocol(), basePath.getHost(), basePath.getPort(), path);
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }

  // Check whether device is Mobile
  public  static boolean isMobile(){
    boolean flag=Props.getdevice("application.run.mode").equalsIgnoreCase("Mobile");
    return flag;
  }

  // Check whether device is Desktop
  public  static boolean isDesktop(){
    boolean flag=Props.getdevice("application.run.mode").equalsIgnoreCase("Desktop");
    return flag;
  }

  // Check whether device is tablet
  public  static boolean isTablet(){
    boolean flag=Props.getdevice("application.run.mode").equalsIgnoreCase("Tablet");
    return flag;
  }
  // Check whether device is Iphone
  public  static boolean isIphone(){
    boolean flag=Props.getdevice("execution.on.device").equalsIgnoreCase("Iphone");
    return flag;
  }

  // Check whether device is tablet
  public  static boolean isIpad(){
    boolean flag=Props.getdevice("execution.on.device").equalsIgnoreCase("Ipad");
    return flag;
  }

  // Check whether device is Nexus
  public  static boolean isNexus(){
    boolean flag=Props.getdevice("execution.on.device").equalsIgnoreCase("Nexus");
    return flag;
  }

  // Check whether device is HeadlessChrome
  public  static boolean isHeadlessChrome(){
    boolean flag=Props.getdevice("execution.on.device").equalsIgnoreCase("HeadlessChrome");
    return flag;
  }

  // Check whether device is LocalChrome
  public  static boolean isLocalChrome(){
    boolean flag=Props.getdevice("execution.on.device").equalsIgnoreCase("LocalChrome");
    return flag;
  }

  // Check whether device is BrowserStackedge
  public  static boolean isBsedge(){
    boolean flag=Props.getdevice("execution.on.device").equalsIgnoreCase("bsedge");
    return flag;
  }

  public  static boolean isLambdaTestChrome(){
    boolean flag=Props.getdevice("execution.on.device").equalsIgnoreCase("LTChrome");
    return flag;
  }

  // Check whether device is BrowserBschrome
  public  static boolean isBsChrome(){
    boolean flag=Props.getdevice("execution.on.device").equalsIgnoreCase("bsChrome");
    return flag;
  }

  // Check whether device is localedge
  public  static boolean islocaledge(){
    boolean flag=Props.getdevice("execution.on.device").equalsIgnoreCase("localedge");
    return flag;
  }
}
