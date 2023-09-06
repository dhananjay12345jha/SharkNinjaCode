package com.salmon.test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;


@CucumberOptions(features = "src/test/resources/features/web", tags = {"@regNinjaFRSuite2  and not @excludeNinjaFR"}, monochrome = true, plugin = {

        "pretty",
        "html:target/cucumber-report/runwebat",
        "junit:target/cucumber-report/runwebat/cucumber2.xml",
        "json:target/cucumber-report/runwebat1/cucumber.json",
        "rerun:target/suit1-rerun.txt"},
        glue = "com.salmon.test")
public class RunNinjaFRRegressionSuit2 extends AbstractTestNGCucumberTests {
}
