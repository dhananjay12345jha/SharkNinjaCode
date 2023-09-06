package com.salmon.test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.DataProvider;


// , ~@regAll, ~@ignore
//@CucumberOptions(features = "src/test/resources/features", tags = {"@mytest,@forceFailureForReRun"}, monochrome = true, plugin = {

@CucumberOptions(features = "src/test/resources/features/web", tags = {"@regSNAPSuit"}, monochrome = true, plugin = {
    "pretty", "html:target/cucumber-report/runwebat",
    "json:target/cucumber-report/runwebat/cucumber.json",
    "rerun:target/cucumber-report/runwebat/rerun.txt"},
    //"rerun:target/rerun.txt"},
    glue = "com.salmon.test")
public class RunSnapUKWebATSuite extends AbstractTestNGCucumberTests {


}
