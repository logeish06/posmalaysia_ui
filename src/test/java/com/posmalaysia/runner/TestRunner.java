package com.posmalaysia.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",  // Path to your feature files
    glue = {"com.posmalaysia.steps", "com.posmalaysia.pages"},  // Path to your step definitions
    plugin = {
        "pretty",
        "html:target/cucumber-reports/html/cucumber.html",
        "json:target/cucumber-reports/json/cucumber.json",
        "junit:target/cucumber-reports/xml/cucumber.xml",   "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        
    },
    monochrome = true
)
public class TestRunner {

}
