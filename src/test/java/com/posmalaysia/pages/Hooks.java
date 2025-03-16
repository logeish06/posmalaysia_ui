package com.posmalaysia.pages;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;


public class Hooks {
    private WebDriver driver;

    // Constructor to inject WebDriver
    public Hooks() {
    	   this.driver = DriverFactory.getDriver(); // Initialize WebDriver
           
        if (this.driver == null) {
            System.out.println("🚨 WebDriver is NULL in Hooks Constructor!");
        } else {
            System.out.println("✅ WebDriver initialized successfully in Hooks.");
        }
    
        new ScreenshotUtil(driver);
    }

    @Before
    public void setup(Scenario scenario) {
        System.out.println("Starting scenario: " + scenario.getName());
        ExtentReportUtil.setupReport();  // Initialize Extent Reports
        ExtentReportUtil.startTest(scenario.getName());  // Create test for scenario
        
        
    }

    @AfterStep
    public void takeScreenshotAfterStep(Scenario scenario) {
        try {
            if (driver == null) {
                System.out.println("🚨 WebDriver is NULL inside takeScreenshotAfterStep.");
                return;
            }

            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File screenshotDir = new File("screenshots");

            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }

            String screenshotPath = "screenshots/" + scenario.getName().replaceAll("[^a-zA-Z0-9]", "_") + ".png";
            Files.copy(screenshotFile.toPath(), Path.of(screenshotPath), StandardCopyOption.REPLACE_EXISTING);

            scenario.attach(Files.readAllBytes(Path.of(screenshotPath)), "image/png", "Step Screenshot");
            System.out.println("✅ Screenshot saved: " + screenshotPath);
        } catch (Exception e) {
            System.out.println("🚨 Screenshot capture failed: " + e.getMessage());
        }
    }
}
