package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.DriverHelpers;
import commons.TestBase;

public class Home extends TestBase {
	protected WebDriver driver;

	DriverHelpers driveHelper = new DriverHelpers();

	public Home(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//////////////////////
	/// Booking Elements///
	//////////////////////

	@FindBy(id = "check-in")
	private WebElement checkIn;

	@FindBy(id = "check-out")
	private WebElement checkOut;

	@FindBy(xpath = "//*[@id=\'adults\']/a")
	private WebElement adultsUp;

	@FindBy(xpath = "//*[@id=\'adults\']/a/following-sibling::a")
	private WebElement adultsDown;

	@FindBy(xpath = "//*[@id=\'children\']/a")
	private WebElement kidsUp;

	@FindBy(xpath = "//*[@id=\'children\']/a/following-sibling::a")
	private WebElement kidsDown;

	@FindBy(xpath = "//span[contains(text(), 'Search')]")
	private WebElement searchBtn;

	@FindBy(xpath = "//button[@aria-label='Next month']")
	private WebElement nextMonth;

	@FindBy(xpath = "//button[@aria-label='Previous month']")
	private WebElement prevMonth;

	private WebElement in_day;

	private void inDate(String azi) throws InterruptedException {
		driveHelper.waitForElementVisibility(By.xpath("//span[contains(text(), " + azi + ")]"));
		in_day = driver.findElement(By.xpath("//span[contains(text(), " + azi + ")]"));
	}

	private WebElement out_day;

	private void outDate(String zi) throws InterruptedException {
		driveHelper.waitForElementVisibility(By.xpath("//span[contains(text(), " + zi + ")]"));
		out_day = driver.findElement(By.xpath("//span[contains(text(), " + zi + ")]"));
	}

	////////////////////////
	///// Frames //////////
	//////////////////////

	@FindBy(xpath = "//iframe[@title='Wix Hotels']")
	private WebElement bookerFrame;

	@FindBy(xpath = "//iframe[@class='s_yOSHETPAPopupSkiniframe']")
	private WebElement calendarFrame;

	/////////////////////////
	// Actions on Elements//
	///////////////////////

	////////////////////////////////////////////////
	// Booking and Calendar Actions and Keywords //
	///////////////////////////////////////////////

	public void clickCheckIn() throws InterruptedException {
		click(checkIn);
	}

	public void clickCheckOut() throws InterruptedException {
		click(checkOut);
	}

	public void clickSearchBtnt() throws InterruptedException {
		click(searchBtn);
	}

	/////////////////////////////// Calendar actions ////////////////////////////

	public void nextMonth() throws InterruptedException {
		click(nextMonth);
	}

	public void prevMonth() throws InterruptedException {
		click(prevMonth);
	}

	public void clickInDay(String azi) throws InterruptedException {
		inDate(azi);
		click(in_day);
		waitForCalendarFrame();
	}

	public void clickOutDay(String zi) throws InterruptedException {
		outDate(zi);
		click(out_day);
		parentFrame();
		Thread.sleep(500);
	}

	///////////////////////////// Booking keywords ///////////////////////

	public void setFrameAndCheckIn() throws InterruptedException {

		waitForBookingFrame();
		clickCheckIn();
		parentFrame();
		waitForCalendarFrame();
	}

	public void setFrameAndCheckOut() throws InterruptedException {
		waitForBookingFrame();
		click(checkOut);
		parentFrame();
		waitForCalendarFrame();
	}

	public void setFrameAndClickSearchBtn() throws InterruptedException {
		waitForBookingFrame();
		click(searchBtn);
		parentFrame();
	}

	////////////////////////
	///// Wait n acts///////
	//////////////////////

	public void waitAndClickCheckIn() throws InterruptedException {
		driveHelper.waitForElementVisibility(By.id("check-in"));
		click(checkIn);
	}

	public void waitAndClickCheckOut() throws InterruptedException {
		driveHelper.waitForElementVisibility(By.id("check-out"));
		click(checkOut);
	}

	public void waitAndClickSearchBtn() throws InterruptedException {
		driveHelper.waitForElementVisibility(By.xpath("//span[contains(text(), 'Search')]"));
		click(searchBtn);
	}

	//// Calendar wait acts /////

	public void waitAndNextMonth() throws InterruptedException {
		driveHelper.waitForElementVisibility(By.xpath("//button[@aria-label='Next month']"));
		click(nextMonth);
	}

	public void waitAndPrevMonth() throws InterruptedException {
		driveHelper.waitForElementVisibility(By.xpath("//button[@aria-label='Previous month']"));
		click(prevMonth);
	}

	private void waitInDate(String azi) throws InterruptedException {
		driveHelper.waitForElementVisibility(By.xpath("//span[contains(text(), " + azi + ")]"));
		in_day = driver.findElement(By.xpath("//span[contains(text(), " + azi + ")]"));
	}

	public void waitAndClickInDay(String azi) throws InterruptedException {
		waitInDate(azi);
		click(in_day);
		waitForCalendarFrame();
	}

	public void waitAndClickOutDay(String zi) throws InterruptedException {
		outDate(zi);
		click(out_day);
		parentFrame();
	}

	public void waitFrameAndCheckIn() throws InterruptedException {

		waitForBookingFrame();
		waitAndClickCheckIn();
		parentFrame();
		waitForCalendarFrame();
	}

	public void waitFrameAndCheckOut() throws InterruptedException {
		waitForBookingFrame();
		waitAndClickCheckOut();
		parentFrame();
		waitForCalendarFrame();
	}

	public void waitFrameAndClickSearchBtn() throws InterruptedException {
		waitForBookingFrame();
		waitAndClickSearchBtn();
		parentFrame();
	}

	///////////////////////////
	// Frame Change Actions //
	//////////////////////////

	public void waitForBookingFrame() {
		driveHelper.waitForFrameAndSwitch(By.xpath("//iframe[@title='Wix Hotels']"));
	}

	public void waitForCalendarFrame() {
		driveHelper.waitForFrameAndSwitch(By.xpath("//iframe[@class='s_yOSHETPAPopupSkiniframe']"));
	}

}
