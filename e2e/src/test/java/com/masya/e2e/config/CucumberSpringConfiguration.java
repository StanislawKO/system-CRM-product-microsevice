package com.masya.e2e.config;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@CucumberContextConfiguration
@SpringBootTest
@ComponentScan(basePackages = "com.masya.product_service")
public class CucumberSpringConfiguration {

    @Bean
    public TestRestTemplate testRestTemplate() {
        return new TestRestTemplate();
    }
}
