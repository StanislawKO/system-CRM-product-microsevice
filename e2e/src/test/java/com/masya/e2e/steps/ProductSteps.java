package com.masya.e2e.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import com.masya.e2e.config.CucumberSpringConfiguration;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = CucumberSpringConfiguration.class)
public class ProductSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    private ResponseEntity<String> response;

    @Given("the product with ID {long} exists")
    public void theProductWithIDExists(Long id) {
        // Here you can set up the product in the database or mock the service
    }

    @When("I request the product with ID {long}")
    public void iRequestTheProductWithID(Long id) {
        response = restTemplate.getForEntity("/v1/products/" + id, String.class);
    }

    @Then("the response status should be {int}")
    public void theResponseStatusShouldBe(int statusCode) {
        assertEquals(statusCode, response.getStatusCodeValue());
    }

    @Then("the response should contain the product details")
    public void theResponseShouldContainTheProductDetails() {
        // Here you can add assertions to check the response body
    }
}
