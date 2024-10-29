package com.example.petstore.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features", glue = { "com.example.petstore.stepdefinitions",
        "com.example.petstore.hooks" }, monochrome = true, plugin = {
                "pretty",
                "html:target/reports/cucumber_report.html",
                "json:target/reports/cucumber_report.json",
                "rerun:target/rerun.txt",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        }, tags = "")
public class TestRunner extends AbstractTestNGCucumberTests {
}
