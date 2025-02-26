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
import java.io.File;
import java.nio.file.Files;

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
                // Capture screenshot in PNG format
                byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

                // Attach to Cucumber
                scenario.attach(screenshotBytes, "image/png", "Failed Screenshot");

                // Convert to Base64 for Qase
                String base64Screenshot = Base64.getEncoder().encodeToString(screenshotBytes);

                // Attach to Qase as an actual image
                Qase.attach("failed-screenshot.png", base64Screenshot, "image/png");

                // Save to file for debugging
                File screenshotFile = new File("target/screenshots/failed-test.png");
                screenshotFile.getParentFile().mkdirs();
                Files.write(screenshotFile.toPath(), screenshotBytes);

            } catch (Exception e) {
                System.err.println("❌ Error capturing/attaching screenshot: " + e.getMessage());
            }
        }
    }
}
