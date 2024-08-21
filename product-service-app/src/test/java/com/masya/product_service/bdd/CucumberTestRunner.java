package com.masya.product_service.bdd;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"com.masya.product_service.bdd.steps"},
        plugin = {"pretty", "html:target/cucumber-reports"}
)
public class CucumberTestRunner {
}
