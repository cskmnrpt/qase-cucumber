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
import java.nio.file.StandardCopyOption;

public class TestHooks {
    WebDriver driver;

    @Before("@selenium")
    public void setUp(Scenario scenario) {
        System.out.println("Starting browser for: " + scenario.getName());
        DriverManager.setupDriver();
        driver = DriverManager.getDriver();
    }

    @After("@selenium")
    public void attachScreenshot(Scenario scenario) {
        if (scenario.isFailed() && driver != null) {
            try {
                // Generate unique filename using Unix timestamp
                String fileName = "screenshot-" + System.currentTimeMillis() + ".png";
                File screenshotFile = new File("target/screenshots/" + fileName);
                screenshotFile.getParentFile().mkdirs();

                // Capture and save screenshot
                File tempFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                Files.copy(tempFile.toPath(), screenshotFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                // Attach to Cucumber report
                scenario.attach(Files.readAllBytes(screenshotFile.toPath()), "image/png", "Failed Screenshot");

                // Attach to Qase
                Qase.attach(screenshotFile.getPath());

            } catch (Exception e) {
                System.err.println("❌ Screenshot capture failed: " + e.getMessage());
            }
            driver.quit();
        }
    }
}
