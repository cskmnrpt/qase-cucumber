package com.example.runners;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.Test;

@Test
@CucumberOptions(features = "src/test/java/com/example/resources/features/", glue = "com.example", plugin = {
                                "io.qase.cucumber5.QaseEventListener",
})
public class TestRunner extends AbstractTestNGCucumberTests {
}
