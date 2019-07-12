package com.challange2.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.challange2.qa.base.TestBase;

public class ResultsPage extends TestBase {

	@FindBy(xpath = "//span[contains(text(),'Non Stop')]")
	WebElement nonStopCheckBox;
	@FindBy(xpath = "//span[contains(text(),'1 Stop')]")
	WebElement oneStopCheckBox;

	int fromFlightSequence;
	int toFlightSequence;

	// Initialize the Page Objects
	public ResultsPage() {
		PageFactory.initElements(driver, this);
	}

	// Print the Number of Fllights from Delhi to Bangalore and Bangalore to
	// Delhi
	public void printNumberOfFlights() {
		List<WebElement> listOfFromFlights = driver
				.findElements(By.xpath("//div[@id='ow_domrt-jrny']/div/div[@class='fli-list splitVw-listing']"));
		System.out.println(
				"*********** Number Of Flights from Delhi to Bangalore *************: " + listOfFromFlights.size());

		List<WebElement> listOfToFlights = driver
				.findElements(By.xpath("//div[@id='rt-domrt-jrny']/div/div[@class='fli-list splitVw-listing']"));
		System.out.println(
				"*********** Number Of Flights from Bangalore to Delhi *************: " + listOfToFlights.size());
	}

	// Print the Number of Non-Stop Flights from Delhi to Bangalore and
	// Bangalore to Delhi
	public void printNumberOfNonStopFlights() {
		JavascriptExecutor executor1 = (JavascriptExecutor) driver;
		executor1.executeScript("window.scrollTo(0, -document.body.offsetHeight)");

		nonStopCheckBox.click();
		int count1 = 0;
		while (count1 <= 10) {
			count1++;
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("window.scrollTo(0, document.body.offsetHeight)");
		}
		List<WebElement> listOfFromFlights1 = driver
				.findElements(By.xpath("//div[@id='ow_domrt-jrny']/div/div[@class='fli-list splitVw-listing']"));
		System.out.println("*********** Number Of Non-Stop Flights from Delhi to Bangalore *************: "
				+ listOfFromFlights1.size());

		List<WebElement> listOfToFlights1 = driver
				.findElements(By.xpath("//div[@id='rt-domrt-jrny']/div/div[@class='fli-list splitVw-listing']"));
		System.out.println("*********** Number Of Non-Stop Flights from Bangalore to Delhi *************: "
				+ listOfToFlights1.size());
	}

	// Print the Number of One-Stop Flights from Delhi to Bangalore and
	// Bangalore to Delhi
	public void printNumberOfOneStopFlights() {

		// Scrolling to the top of the page
		JavascriptExecutor executor2 = (JavascriptExecutor) driver;
		executor2.executeScript("window.scrollTo(0, -document.body.offsetHeight)");

		// Unchekcing Non-Stop
		nonStopCheckBox.click();
		// Checking One-Stop
		oneStopCheckBox.click();

		int count2 = 0;
		while (count2 <= 10) {
			count2++;
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("window.scrollTo(0, document.body.offsetHeight)");
		}
		List<WebElement> listOfFromFlights2 = driver
				.findElements(By.xpath("//div[@id='ow_domrt-jrny']/div/div[@class='fli-list splitVw-listing']"));
		System.out.println("*********** Number Of One-Stop Flights from Delhi to Bangalore *************: "
				+ listOfFromFlights2.size());

		List<WebElement> listOfToFlights2 = driver
				.findElements(By.xpath("//div[@id='rt-domrt-jrny']/div/div[@class='fli-list splitVw-listing']"));
		System.out.println("*********** Number Of One-Stop Flights from Bangalore to Delhi *************: "
				+ listOfToFlights2.size());
	}

	// Dynamically selecting the From and To Flights based on our input
	public void selectingTheFlightsDynamically(int fromFlightNumber, int toFlightNumber) {
		fromFlightSequence = fromFlightNumber;
		toFlightSequence = toFlightNumber;
		// Scrolling to the top of the page and Unselecting the One-Stop check
		// box
		JavascriptExecutor executor3 = (JavascriptExecutor) driver;
		executor3.executeScript("window.scrollTo(0, -document.body.offsetHeight)");
		oneStopCheckBox.click();

		WebElement thirdFlightFromList = driver.findElement(By.xpath(
				"(//div[@id='ow_domrt-jrny']/div/div[@class='fli-list splitVw-listing'])[" + fromFlightNumber + "]"));
		thirdFlightFromList.click();
		WebElement secondFlightToList = driver.findElement(By.xpath(
				"(//div[@id='rt-domrt-jrny']/div/div[@class='fli-list splitVw-listing'])[" + toFlightNumber + "]"));
		secondFlightToList.click();

	}

	// Getting the Flight Codes of the selected From and To Flights and
	// verifying if the same are displayed at the botton of the page based on
	// our prior selection

	public void validatingTheFlightCodes() {

		// Getting the flight code for FROM Flight
		WebElement fromFlightcode = driver
				.findElement(By.xpath("//div[@id='ow_domrt-jrny']/div/div[@class='fli-list splitVw-listing']["
						+ fromFlightSequence + "]//div//following-sibling::span//span[1]"));
		String fromFlightCodeString = fromFlightcode.getText();
		System.out.println("************** FROM FLIGHT CODE TEXT ************* " + fromFlightCodeString);

		// Getting the flight code for TO Flight
		WebElement toFlightcode = driver
				.findElement(By.xpath("//div[@id='rt-domrt-jrny']/div/div[@class='fli-list splitVw-listing']["
						+ toFlightSequence + "]//div//following-sibling::span//span[1]"));
		String toFlightString = toFlightcode.getText();
		System.out.println("************** TO FLIGHT CODE TEXT ************* " + toFlightString);

		// Getting the flight code for FROM Flight from bottom
		WebElement fromFlightCodeBottom = driver
				.findElement(By.xpath("(//div[@class='splitVw-footer-left ']//span)[1]"));
		String fromFlightStringBottom = fromFlightCodeBottom.getText();
		System.out.println("************** FROM FLIGHT CODE TEXT BOTTOM ************* " + fromFlightStringBottom);

		// Getting the flight code for FROM Flight from bottom
		WebElement toFlightCodeBottom = driver
				.findElement(By.xpath("(//div[@class='splitVw-footer-right ']//span)[1] "));
		String toFlightStringBottom = toFlightCodeBottom.getText();
		System.out.println("************** TO FLIGHT CODE TEXT BOTTOM ************* " + toFlightStringBottom);

		Assert.assertEquals(fromFlightCodeString.replaceAll("\\s+", ""), fromFlightStringBottom.replaceAll("\\s+", ""));
		Assert.assertEquals(toFlightString.replaceAll("\\s+", ""), toFlightStringBottom.replaceAll("\\s+", ""));
	}

	// Getting the Flight Cost of the From and To Flights and Comparing with the
	// Total Cost displayed at the bottom of the page
	public void validatingTheTotalFlightCost() {
		// from flight cost
		WebElement fromFlightCost = driver.findElement(
				By.xpath("(//div[@class='splitVw-footer-left ']//div[@class='pull-right marL5 text-right']//span)"));
		String fromFlightCostString = fromFlightCost.getText();
		System.out.println("************** FROM FLIGHT COST ************* " + fromFlightCostString);
		fromFlightCostString = fromFlightCostString.substring(2);
		fromFlightCostString = fromFlightCostString.replaceAll(",", "");
		System.out.println(
				"************** FROM FLIGHT COST AFTER REMOVING SYMBOLS ************* " + fromFlightCostString);

		// to flight cost
		WebElement toFlightCost = driver.findElement(
				By.xpath("(//div[@class='splitVw-footer-right ']//div[@class='pull-right marL5 text-right']//span)"));
		String toFlightCostString = toFlightCost.getText();
		System.out.println("************** TO FLIGHT COST ************* " + toFlightCostString);
		toFlightCostString = toFlightCostString.substring(2);
		toFlightCostString = toFlightCostString.replaceAll(",", "");
		System.out.println("************** TO FLIGHT COST AFTER REMOVING SYMBOLS ************* " + toFlightCostString);

		int expectedTotalCostofFlight = Integer.parseInt(fromFlightCostString.trim())
				+ Integer.parseInt(toFlightCostString.trim());
		System.out.println("************** EXPECTED TOTAL FLIGHT COST AFTER REMOVING SYMBOLS ************* "
				+ expectedTotalCostofFlight);

		// Get the total cost
		WebElement totalCost = driver.findElement(By.xpath("(//div[@class='footer-fare']//span)"));
		String totalCostString = totalCost.getText();
		System.out.println("************** TO FLIGHT COST ************* " + totalCostString);
		totalCostString = totalCostString.substring(2);
		totalCostString = totalCostString.replaceAll(",", "").trim();
		int actualTotalCostofFlight = Integer.parseInt(totalCostString);
		System.out.println("************** ACTUAL TOTAL FLIGHT COST AFTER REMOVING SYMBOLS ************* "
				+ actualTotalCostofFlight);

		Assert.assertEquals(actualTotalCostofFlight, expectedTotalCostofFlight);
	}

}
