package com.example.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.example.utils.DriverManager;

import io.qase.cucumber7.Qase;

import java.util.Base64;

public class TestHooks {
    WebDriver driver;

    @Before("@selenium")
    public void setUp(Scenario scenario) {
        System.out.println("Starting browser for: " + scenario.getName());
        DriverManager.setupDriver();
        driver = DriverManager.getDriver();
    }

    @After("@selenium")
    public void embedScreenshot(Scenario scenario) {
        if (scenario.isFailed() && driver != null) {
            try {
                // Capture screenshot as BASE64
                String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);

                // Attach to Cucumber Report
                byte[] decodedBytes = Base64.getDecoder().decode(base64Screenshot);
                scenario.attach(decodedBytes, "image/png", "Failed Screenshot");

                // Attach to Qase
                Qase.attach("failed-screenshot.png", base64Screenshot, "image/png");

            } catch (Exception e) {
                System.err.println("❌ Error capturing or attaching screenshot: " + e.getMessage());
            }
        }
    }
}
