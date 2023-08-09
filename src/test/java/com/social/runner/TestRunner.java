package com.social.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static io.cucumber.testng.CucumberOptions.SnippetType.CAMELCASE;

@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"com.social.steps"},
        plugin = {"pretty",
                "summary",
                "com.social.listener.CucumberExtentReportListener:",
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json",
        "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"},
        snippets = CAMELCASE,
        monochrome=true
)

public class TestRunner extends AbstractTestNGCucumberTests {

    @BeforeClass
    public void beforeClass(){
    }


    @AfterClass
    public void afterClass(){
    }

}
