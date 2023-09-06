package com.salmon.test;


import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features/web", tags = {"@regSharkCASuit2","~@excludeForMobile"}, monochrome = true, plugin = {
        "pretty",
        "html:target/cucumber-report/runwebat",
        "junit:target/cucumber-report/cucumber1.xml",
        "json:target/cucumber-report/cucumber.json",
        "rerun:target/suit1-rerun.txt"},
        glue = "com.salmon.test")
public class RunMobileSharkCARegressionSuit2 extends AbstractTestNGCucumberTests {
}
