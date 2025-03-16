package com.posmalaysia.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class ScreenshotUtil {
    private WebDriver driver;

    public ScreenshotUtil(WebDriver driver) {
        this.driver = driver;
    }

    public void captureFullPageScreenshot(String filename) {
        try {
            if (driver == null) {
                System.out.println("🚨 WebDriver is NULL inside ScreenshotUtil!");
                return;
            }

            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Path screenshotPath = Path.of("screenshots", filename + ".png");

            if (!Files.exists(screenshotPath.getParent())) {
                Files.createDirectories(screenshotPath.getParent());
            }

            Files.copy(screenshotFile.toPath(), screenshotPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("✅ Full page screenshot saved: " + screenshotPath);
        } catch (Exception e) {
            System.out.println("🚨 ScreenshotUtil error: " + e.getMessage());
        }
    }
}
