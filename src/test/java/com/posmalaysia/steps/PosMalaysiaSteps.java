package com.posmalaysia.steps;
import org.openqa.selenium.WebDriver;

import com.posmalaysia.pages.DriverFactory;
import com.posmalaysia.pages.PosMalaysiaPage;
import com.posmalaysia.pages.ScreenshotUtil;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PosMalaysiaSteps {

	
	   private WebDriver driver;
	  
     	  private PosMalaysiaPage posMalaysiaPage;

		public PosMalaysiaSteps() {
             this.driver = DriverFactory.getDriver(); // Initialize WebDriver
             this.posMalaysiaPage = new PosMalaysiaPage(driver); 
             new ScreenshotUtil(driver);
         }
		    
		    @Given("user is on the pos malaysia rate calculator url")
		    public void user_is_on_the_pos_malaysia_rate_calculator_url() {
		        posMalaysiaPage.openBrowserAndNavigate();
		    
		         
		    }

		    @When("user enters {string}, {string}, {string}, {string}, {string}, {string}, {string} and click calculate button")
		    public void user_enters_and_click_calculate_button(String fromcountry, String frompostcode, String fromstate, String tocountry, String topostcode, String toState, String itemweight) {
		        posMalaysiaPage.getQuotation(fromcountry, frompostcode, fromstate, tocountry, topostcode, toState, itemweight);
		  
		    }

		    @Then("verify multiple quotes are displayed for user")
		    public void verify_multiple_quotes_are_displayed_for_user() {
		    	posMalaysiaPage.verifyQuote();
		 
		    }

			public WebDriver getDriver() {
				return driver;
			}

			public void setDriver(WebDriver driver) {
				this.driver = driver;
			}

			public PosMalaysiaPage getPosMalaysiaPage() {
				return posMalaysiaPage;
			}

			public void setPosMalaysiaPage(PosMalaysiaPage posMalaysiaPage) {
				this.posMalaysiaPage = posMalaysiaPage;
			}
}
