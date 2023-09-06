package com.salmon.test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;



@CucumberOptions(features = "src/test/resources/features/api", tags = "@ApiTest", monochrome = true, plugin = {
    "pretty", "html:target/cucumber-report/runapiat",
    "json:target/cucumber-report/runapiat/cucumber.json",
    "rerun:target/cucumber-report/runapiat/rerun.txt"},
    glue = "com.salmon.test",dryRun = false)
public class RunApiATSuite extends AbstractTestNGCucumberTests {

}
