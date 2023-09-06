package com.salmon.test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;


@CucumberOptions(features = "src/test/resources/features/web", tags = {"@regSharkCASuit2"}, monochrome = true, plugin = {

        "pretty",
        "html:target/cucumber-report/suit2",
        "junit:target/cucumber-report/cucumber2.xml",
        "json:target/cucumber-report/suit2/cucumber.json",
        "rerun:target/suit2-rerun.txt"},
        glue = "com.salmon.test")
public class RunSharkCARegressionSuit2 extends AbstractTestNGCucumberTests {
}
