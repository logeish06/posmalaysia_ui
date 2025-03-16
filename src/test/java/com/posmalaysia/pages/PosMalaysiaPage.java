package com.posmalaysia.pages;

import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.Scenario;


public class PosMalaysiaPage {

	WebDriver driver;
	ConfigReader con;
	PosMalaysiaPage posmalaysiapage;

	ConfigReader utils;

	Hooks hooks;

	@FindBy(xpath = "//*[text()='From']")
	WebElement lblFromCountry;

	@FindBy(xpath = "//*[@class='country-container shadow-md rounded-lg px-3']//span[2]")
	WebElement inpfromCountry;

	@FindBy(xpath = "//*[text()='To']")
	WebElement lblToCountry;

	@FindBy(xpath = "//*[@name='country']")
	WebElement inptoCountry;

	@FindBy(xpath = "//*[@formcontrolname='postcodeFrom']")
	WebElement inpfromPostCode;

	@FindBy(xpath = "//div[1]/div[3]/input")
	WebElement inpfromState;

	@FindBy(xpath = "//*[@role='listbox']//mat-option//*[@class='country-list-item ng-star-inserted']")
	List<WebElement> toCountryList;

	@FindBy(xpath = "//*[@formcontrolname='postcodeTo']")
	WebElement inptoPostCode;

	@FindBy(xpath = "//*[@id=\"contentBody\"]/div/app-static-layout/app-rate-calculator-v2/div/div[3]/div[1]/div[2]/div[3]/input")
	WebElement inptoStateorpostcodeoptional;

	@FindBy(xpath = "//*[contains(text(),'(kg)')]")
	WebElement lblWeight;

	@FindBy(xpath = "//*[@formcontrolname='itemWeight']")
	WebElement inpitemWeight;

	@FindBy(xpath = "//a[contains(text(),'Calculate')]")
	WebElement btnCalculate;

	@FindBy(xpath = "//*[contains(text(),'Your')]")
	WebElement txtYourQuote;
	
	@FindBy(xpath = "//mat-option")
	 WebElement toSelectedCountry;
	
	private WaitUtils waitutils;
	  Scenario currentScenario; // Store Scenario instance

	// Constructor
	public PosMalaysiaPage(WebDriver driver) {
		this.driver = driver;
        this.waitutils = new WaitUtils(driver,Duration.ofSeconds(30)); 

		PageFactory.initElements(driver, this);

	}

	public void openBrowserAndNavigate() {
        String url = ConfigReader.getProperty("base.url");  
        driver.get(url);
    }

	public void getQuotation(String strfromCountry, String strfromPostCode, String strfromState, String strtoCountry,
			String strtopostcode, String strtoState, String stritemWeight) {
		assertTrue(lblFromCountry.isDisplayed());
		assertTrue(inpfromCountry.isDisplayed());
		assertTrue(inpfromCountry.getText().equals(strfromCountry));
		assertTrue(inpfromPostCode.isDisplayed());
		assertTrue(inpfromState.isDisplayed());
		inpfromPostCode.sendKeys(strfromPostCode);
			assertTrue(lblToCountry.isDisplayed());
		assertTrue(inptoCountry.isDisplayed());
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		 WebElement toCountryInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("mat-input-0"))); // Input field

		 toCountryInput.clear();
		    toCountryInput.click(); 
		    toCountryInput.sendKeys(strtoCountry); // Type "India"

		    WebElement suggestion = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-option//small[contains(text(),'"+strtoCountry+"')]")));
		    suggestion.click(); 

		
		if (strtoCountry.equals("Malaysia")) {
			assertTrue(inptoPostCode.isDisplayed());
			assertTrue(inptoStateorpostcodeoptional.isDisplayed());
			inptoPostCode.sendKeys(strtopostcode);
			inptoStateorpostcodeoptional.getText().equals(strtoState);
		} else {
			waitutils.waitForVisibility(By.xpath("//div[2]/div[3]/input"));
			assertTrue(inptoStateorpostcodeoptional.isDisplayed());
				
			    Actions action = new Actions(this.driver);

			    action.moveToElement(inptoStateorpostcodeoptional).sendKeys(strtopostcode).perform();
		}
		assertTrue(inpitemWeight.isDisplayed());
		waitutils.waitForVisibility(By.xpath("//*[@formcontrolname='itemWeight']"));
		
		inpitemWeight.sendKeys(stritemWeight);
		assertTrue(btnCalculate.isDisplayed());
		btnCalculate.click();
	    
	
	}

	public void verifyQuote() {
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", txtYourQuote);

		waitutils.waitForVisibility(By.xpath("//*[contains(text(),'Your')]"));
		txtYourQuote.isDisplayed();

		   List<WebElement> shipmentOptions = driver.findElements(By.className("border-b"));

		   try {
           if (shipmentOptions.size() > 1) {
               System.out.println("Multiple shipment options are displayed.");
               for (WebElement option : shipmentOptions) {
                   System.out.println("Option: " + option.getText());
               }
           } else {
               System.out.println("No multiple shipment options found.");
           }

       } catch (Exception e) {
           e.printStackTrace();
       }finally {
    	       

       }
       
}

	
}

