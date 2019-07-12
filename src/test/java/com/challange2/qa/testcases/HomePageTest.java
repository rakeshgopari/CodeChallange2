package com.challange2.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.challange2.qa.base.TestBase;
import com.challange2.qa.pages.HomePage;
import com.challange2.qa.pages.ResultsPage;

public class HomePageTest extends TestBase {

	public HomePage homePage;
	public ResultsPage resultsPage;

	public HomePageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		homePage = new HomePage();
	}

	@Test(priority = 1)
	public void searchResultsTest() {
		homePage.clickFligtsLink();
		homePage.clickRoundTripRadioButton();
		homePage.enterFromAndToLocations();
		homePage.selectDate();
		resultsPage = homePage.clickOnSearchForResults();
		resultsPage.printNumberOfFlights();
		resultsPage.printNumberOfNonStopFlights();
		resultsPage.printNumberOfOneStopFlights();
		resultsPage.selectingTheFlightsDynamically(3, 2);
		resultsPage.validatingTheFlightCodes();
		resultsPage.validatingTheTotalFlightCost();
	}
	
	@AfterMethod
	public void tearDown() {
		// driver.quit();
	}
}
