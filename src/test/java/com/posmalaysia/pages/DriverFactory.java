package com.posmalaysia.pages;

	import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

	public class DriverFactory {
	    private static WebDriver driver;

	    // Singleton WebDriver instance
	    public static WebDriver getDriver() {
	    	 if (driver == null) {
	             String browser = System.getProperty("browser", "chrome"); // Default: Chrome
	             
	             switch (browser.toLowerCase()) {
	                 case "firefox":
	                     WebDriverManager.firefoxdriver().setup();
	                     driver = new FirefoxDriver();
	                     break;
	                 case "edge":
	                     WebDriverManager.edgedriver().setup();
	                     driver = new EdgeDriver();
	                     break;
	                 case "chrome":
	                 default:
	                     WebDriverManager.chromedriver().setup();
	                     driver = new ChromeDriver();
	                     break;
	             }

	             driver.manage().window().maximize();
	             driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	         }
	         return driver;
	    }

	    public static void quitDriver() {
	        if (driver != null) {
	            driver.quit();
	            driver = null;
	        }
	    }
	}

