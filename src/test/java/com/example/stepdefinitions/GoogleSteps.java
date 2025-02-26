package com.example.stepdefinitions;

import io.cucumber.java.en.Given;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.example.utils.DriverManager;

public class GoogleSteps {
    WebDriver driver = DriverManager.getDriver();

    @Given("I open Google")
    public void iOpenGoogle() {
        driver.get("https://www.google.com");
    }

    @Given("I search for {string}")
    public void iSearchFor(String query) {
        driver.findElement(By.name("q")).sendKeys(query);
        driver.findElement(By.name("q")).submit();
    }

    @Given("I verify the search results contain {string}")
    public void iVerifyTheSearchResultsContain(String expectedText) {
        String pageSource = driver.getPageSource();
        Assert.assertTrue(pageSource.contains(expectedText),
                "Expected text not found: " + expectedText);
    }

    @Given("I fail the test on purpose")
    public void iFailTheTestOnPurpose() {
        Assert.fail("This test is designed to fail!");
    }
}
