package com.salmon.test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;


@CucumberOptions(features = "src/test/resources/features/web", tags = {"@regNinjaUKSuite2  and not @excludeNinjaUK"}, monochrome = true, plugin = {

        "pretty",
        "html:target/cucumber-report/suit2",
        "junit:target/cucumber-report/cucumber2.xml",
        "json:target/cucumber-report/suit2/cucumber.json",
        "rerun:target/suit2-rerun.txt"},
        glue = "com.salmon.test")
public class RunNinjaUKRegressionSuit2 extends AbstractTestNGCucumberTests {
}
