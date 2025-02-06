package org.example;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "stepDefinitions",  // Ensure this matches the actual package of your step definitions
        plugin = {
                "io.qase.cucumber5.QaseEventListener"
        }
)
public class CucumberTest extends AbstractTestNGCucumberTests {
}
