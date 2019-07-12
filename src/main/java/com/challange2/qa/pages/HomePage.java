package com.challange2.qa.pages;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.challange2.qa.base.TestBase;
import com.google.common.base.Function;

public class HomePage extends TestBase {

	// Page Factory or Object Repository

	@FindBy(linkText = "Flights")
	WebElement flightsLink;
	@FindBy(xpath = "//li[contains(text(),'Round Trip')]")
	WebElement roundTripRadioButton;
	@FindBy(id = "fromCity")
	WebElement fromCity;
	@FindBy(xpath = "//input[@id = 'fromCity']//following::input")
	WebElement fromCityInput;
	@FindBy(id = "toCity")
	WebElement toCity;
	@FindBy(xpath = "//input[@id = 'toCity']//following::input")
	WebElement toCityInput;
	@FindBy(xpath = "//p[contains(text(),'Delhi, India')]")
	WebElement fromCityDropDown;
	@FindBy(xpath = "//*[@id = 'react-autowhatever-1-section-0-item-0']")
	WebElement delhiDropDown;
	@FindBy(xpath = "//p[contains(text(),'Bangalore, India')]")
	WebElement toCityDropDown;
	@FindBy(xpath = "//input[@id,'departure']")
	WebElement departureDate;

	@FindBy(xpath = "//input[@id='departure']//following::div[@class='DayPicker-Month'][1]//div[@class='DayPicker-Week']/div/div/p")
	List<WebElement> departureDateList;
	@FindBy(xpath = "//input[@id='departure']//following::div[@class='DayPicker-Month'][2]//div[@class='DayPicker-Week']/div/div/p")
	List<WebElement> returnDateList;
	@FindBy(xpath = "//a[contains(text(),'Search')]")
	WebElement searchButton;
	/*@FindBy(xpath = "//span[contains(text(),'Non Stop')]")
	WebElement nonStopCheckBox;
	@FindBy(xpath = "//span[contains(text(),'1 Stop')]")
	WebElement oneStopCheckBox;

	int fromFlightSequence;
	int toFlightSequence;*/

	// Initialize the Page Objects
	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	// Method to click Flight Link
	public void clickFligtsLink() {
		flightsLink.click();
	}

	// Method to click Round Trip Radio Button
	public void clickRoundTripRadioButton() {
		roundTripRadioButton.click();
	}

	// Method to Enter the From and To Locations
	public void enterFromAndToLocations() {
		fromCity.click();
		fromCityInput.sendKeys("delhi");
		fromCityDropDown.click();
		toCityInput.sendKeys("bangalore");
		toCityDropDown.click();
	}

	public void selectDate() {

		Calendar cal = Calendar.getInstance();
		String dayofDeparture = Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
		int monthofDeparture = cal.get(Calendar.MONTH);

		for (WebElement ele : departureDateList) {

			String date = ele.getText();

			if (date.equalsIgnoreCase(dayofDeparture)) {
				ele.click();
				break;
			}

		}

		cal.add(Calendar.DAY_OF_MONTH, 7);
		int monthofReturn = cal.get(Calendar.MONTH);
		if (monthofDeparture != monthofReturn) {

			returnDateList = driver.findElements(By.xpath(
					"//input[@id='departure']//following::div[@class='DayPicker-Month'][2]//div[@class='DayPicker-Week']/div/div/p"));
		}

		String dayofReturn = Integer.toString(cal.get(Calendar.DAY_OF_MONTH));

		for (WebElement ele : returnDateList) {

			String date = ele.getText();

			if (date.equalsIgnoreCase(dayofReturn)) {
				ele.click();
				break;
			}

		}
	}

	public ResultsPage clickOnSearchForResults() {
		// click on Search Button
		searchButton.click();

		// Fluently wait until the results are loaded
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		WebElement flightLocator = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver
						.findElement(By.xpath("//div[@id='ow_domrt-jrny']/div/div[@class='fli-list splitVw-listing']"));
			}
		});

		// For now i have hardcoded to scroll down the page for 10 times to load
		// the results dynamically,i will work on this work dynamically
		/* Boolean readyStateComplete = false; */
		int count = 0;
		while (count <= 10) {
			count++;
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("window.scrollTo(0, document.body.offsetHeight)");
		}
		return new ResultsPage();
	}
}
