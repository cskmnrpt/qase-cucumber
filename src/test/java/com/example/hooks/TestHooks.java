package com.example.hooks;

import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.example.utils.DriverManager;
import io.qase.cucumber7.Qase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestHooks {
    private static final Logger logger = LoggerFactory.getLogger(TestHooks.class);
    private static WebDriver driver;

    @BeforeAll
    public static void setUpSuite() {
        logger.info("Initializing WebDriver for test suite.");
        DriverManager.setupDriver();
        driver = DriverManager.getDriver();
    }

    @Before("@selenium")
    public void setUp(Scenario scenario) {
        logger.info("Starting browser for: {}", scenario.getName());
        if (driver == null) {
            DriverManager.setupDriver();
            driver = DriverManager.getDriver();
        }
    }

    @AfterStep
    public void takeScreenshotOnFailure(Scenario scenario) {
        if (scenario.isFailed() && driver != null) {
            try {
                byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                Qase.attach("failed-screenshot.png", screenshotBytes, "image/png");
            } catch (Exception e) {
                logger.error("Error capturing screenshot: {}", e.getMessage(), e);
            }
        }
    }

    @AfterAll
    public static void tearDownSuite() {
        if (driver != null) {
            try {
                driver.quit();
                logger.info("Browser closed after all tests.");
            } catch (Exception e) {
                logger.warn("Error while quitting WebDriver: {}", e.getMessage());
            } finally {
                driver = null;
            }
        }
    }
}
