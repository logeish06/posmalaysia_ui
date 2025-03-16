package com.posmalaysia.pages;


	import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

	public class WaitUtils {

	    private WebDriverWait wait;

	    // Constructor to initialize WebDriver and WebDriverWait
	    public WaitUtils(WebDriver driver, Duration timeout) {
	        this.wait = new WebDriverWait(driver, timeout);
	    }

	    // Wait for an element to be visible
	    public WebElement waitForVisibility(By locator) {
	        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	    }

	    // Wait for an element to be clickable
	    public WebElement waitForClickability(By locator) {
	        return wait.until(ExpectedConditions.elementToBeClickable(locator));
	    }

	    // Wait for the presence of an element in the DOM
	    public WebElement waitForPresence(By locator) {
	        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	    }

	    // Wait for an element to disappear
	    public boolean waitForInvisibility(By locator) {
	        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	    }

	    // Wait for a specific text to be present in an element
	    public boolean waitForText(By locator, String text) {
	        return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
	    }
	}
