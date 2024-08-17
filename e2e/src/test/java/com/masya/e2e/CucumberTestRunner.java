package com.masya.e2e;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import com.masya.e2e.config.CucumberSpringConfiguration;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.iprody.e2e.steps",
        plugin = {"pretty", "html:target/cucumber-reports.html"}
)
@ContextConfiguration(classes = CucumberSpringConfiguration.class)
public class CucumberTestRunner {
}
