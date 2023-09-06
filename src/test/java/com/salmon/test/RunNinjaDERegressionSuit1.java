package com.salmon.test;


import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features/web", tags = {"@regNinjaDESuite1 and not @excludeNinjaDE"}, monochrome = true, plugin = {

        "pretty", "html:target/cucumber-report/runwebat",
        "json:target/cucumber-report/cucumber.json",
        "rerun:target/cucumber-report/rerun.txt"},
        glue = "com.salmon.test")
public class RunNinjaDERegressionSuit1 extends AbstractTestNGCucumberTests {
}
