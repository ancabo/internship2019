package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.DriverHelpers;
import commons.TestBase;

public class Rooms extends TestBase {

	protected WebDriver driver;
	DriverHelpers driverHelper = new DriverHelpers();

	public Rooms(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//////////////////////
	///// Elements //////
	/////////////////////

	/////////// Booking Elements //////////

	@FindBy(xpath = "//div[@id='check-in']")
	private WebElement checkIn;

	@FindBy(xpath = "//div[@id='check-out']")
	private WebElement checkOut;

	@FindBy(xpath = "//li[@class='adults']/div/a")
	private WebElement adultsIncrease;

	@FindBy(xpath = "//li[@class='adults']/div/a/following-sibling::a")
	private WebElement adultsDecrease;

	@FindBy(xpath = "//li[@class='children']/div/a")
	private WebElement kidsIncrease;

	@FindBy(xpath = "//li[@class='children']/div/a/following-sibling::a")
	private WebElement kidsDecrease;

	@FindBy(xpath = "//button[@class='search s-button']")
	private WebElement searchBtn;

	/////////////// Calendar Elements ////////////////////

	@FindBy(xpath = "//*[@id='hotel-container']/section/div[1]/div/form/ul/li[1]/div[2]/div/nav/button[2]")
	private WebElement nextMonth;

	@FindBy(xpath = "")
	private WebElement prevMonth;

	private WebElement inDay;

	private void inDate(String zi) {
		inDay = driver.findElement(By.xpath("//div/div[@name='check_in']//span[contains(text(), " + zi + ")]/.."));
	}

	private WebElement outDay;

	private void outDate(String zi) {
		outDay = driver.findElement(By.xpath("//div/div[@name='check_out']//span[contains(text(), " + zi + ")]/.."));
	}

	@FindBy(xpath = "//h2[@class='s-title resultbar s-separator']/strong")
	private WebElement periodBooking;

	@FindBy(xpath = "//div/div[@name='check_in']/div")
	private WebElement checkInMonth;

	@FindBy(xpath = "//div/div[@name='check_out']/div")
	private WebElement checkOutMonth;

	////////////// room element ///////////////////

	@FindBy(xpath = "//ul/li[1]/div/div[1]/img")
	private WebElement roomWithLowestRate;

	@FindBy(xpath = "//ul/li[2]/div/div[1]/img")
	private WebElement roomWithMediumRate;

	@FindBy(xpath = "//ul/li[3]/div/div[1]/img")
	private WebElement roomWithTheHighestRate;

	@FindBy(xpath = "//ul/li[1]/div/div[2]/div[3]/span[2]")
	private WebElement roomWithLowestRatePrice;

	@FindBy(xpath = "//ul/li[2]/div/div[2]/div[3]/span[2]")
	private WebElement roomWithMediumRatePrice;

	@FindBy(xpath = "//ul/li[3]/div/div[2]/div[3]/span[2]")
	private WebElement roomWithHighestRatePrice;

	@FindBy(xpath = "//ul/li[1]/div/div/div/p/span[@class='strans']")
	private WebElement lowRateRoomDescrip;

	@FindBy(xpath = "//ul/li[2]/div/div/div/p/span[@class='strans']")
	private WebElement mediumRateRoomDescrip;

	@FindBy(xpath = "//ul/li[3]/div/div/div/p/span[@class='strans']")
	private WebElement highRateRoomDescrip;

	//////////////// Errors //////////////////

	@FindBy(xpath = "//div[@class='content-body']/div/p/span")
	private WebElement error;

	////////////////////
	//// Frames ////////
	///////////////////

	@FindBy(xpath = "//iframe[@title='Book a Room']")
	private WebElement bodyFrame;

	///////////////////////////////////
	//// Individual Room Elements ////
	//////////////////////////////////

	@FindBy(xpath = "//div/div/p/span[@class='strans']")
	private WebElement roomDescription;

	@FindBy(xpath = "//div[2]/div[1]/span[2]")
	private WebElement roomPerDayPrice;

	@FindBy(xpath = "//tr[@class='total']/td/following-sibling::td")
	private WebElement roomTotalPrice;

	////////////////////
	///// Actions //////
	///////////////////

	///////////////////////
	//// Frame changes ////
	//////////////////////

	public void bodyFrame() {
		changeFrame(bodyFrame);
	}

	///////////////////////
	//// Booking actions///
	///////////////////////

	public void roomClickCheckIn() throws InterruptedException {
		click(checkIn);
	}

	public void roomClickCheckOut() throws InterruptedException {
		click(checkOut);
	}

	public void roomIncreaseAdults() throws InterruptedException {
		click(adultsIncrease);
	}

	public void roomDecreaseAdults() throws InterruptedException {
		click(adultsDecrease);
	}

	public void roomIncreaseKids() throws InterruptedException {
		click(kidsIncrease);
	}

	public void roomDecreaseKids() throws InterruptedException {
		click(kidsDecrease);
	}

	public void roomSearch() throws InterruptedException {
		click(searchBtn);
	}

	public void roomLowestRate() throws InterruptedException {
		click(roomWithLowestRate);
	}

	public void roomMediumRate() throws InterruptedException {
		click(roomWithMediumRate);
	}

	public void roomHighestRate() throws InterruptedException {
		click(roomWithTheHighestRate);
	}

	///////////////// Calendar Actions //////////////////////

	public void nextMonth() throws InterruptedException {
		nextMonth.click();
	}

	public void prevMonth() throws InterruptedException {
		click(prevMonth);
	}

	public Rooms clickInDay(String azi) throws InterruptedException {
		inDate(azi);
		click(inDay);
		return this;
	}

	public Rooms clickOutDay(String zi) throws InterruptedException {
		outDate(zi);
		click(outDay);
		return this;
	}

	public String returnCheckInMonth() {
		return checkInMonth.getText();
	}

	public String returnCheckOutMonth() {
		return checkOutMonth.getText();
	}

	/////////////////////////
	///// Wait and acts//////
	///////////////////////

	///////////////////////
	//// Frame changes ////
	//////////////////////

	public void waitBodyFrame() {
		changeFrame(driverHelper.fluentWaitElementPresentBy(10, 1, By.xpath("//iframe[@title='Book a Room']")));
	}

	///////////////////////
	//// Booking actions///
	///////////////////////

	public void waitRoomClickCheckIn() throws InterruptedException {

		click(driverHelper.fluentWaitElementPresentBy(10, 1, By.xpath("//div[@id='check-in']")));
	}

	public void waitRoomClickCheckOut() throws InterruptedException {

		click(driverHelper.fluentWaitElementPresentBy(10, 1, By.xpath("//div[@id='check-out']")));
	}

	public void waitRoomIncreaseAdults() throws InterruptedException {

		click(driverHelper.fluentWaitElementPresentBy(10, 1, By.xpath("//li[@class='adults']/div/a")));
	}

	public void waitRoomDecreaseAdults() throws InterruptedException {

		click(driverHelper.fluentWaitElementPresentBy(10, 1,
				By.xpath("//li[@class='adults']/div/a/following-sibling::a")));
	}

	public void waitRoomIncreaseKids() throws InterruptedException {

		click(driverHelper.fluentWaitElementPresentBy(10, 1, By.xpath("//li[@class='children']/div/a")));
	}

	public void waitRoomDecreaseKids() throws InterruptedException {

		click(driverHelper.fluentWaitElementPresentBy(10, 1,
				By.xpath("//li[@class='children']/div/a/following-sibling::a")));
	}

	public void waitRoomSearch() throws InterruptedException {

		click(driverHelper.fluentWaitElementPresentBy(10, 1, By.xpath("//button[@class='search s-button']")));
	}

	public void waitRoomLowestRate() throws InterruptedException {

		click(driverHelper.fluentWaitElementPresentBy(10, 1, By.xpath("//ul/li[1]/div/div[1]/img")));
	}

	public void waitRoomMediumRate() throws InterruptedException {

		click(driverHelper.fluentWaitElementPresentBy(10, 1, By.xpath("//ul/li[2]/div/div[1]/img")));
	}

	public void waitRoomHighestRate() throws InterruptedException {

		click(driverHelper.fluentWaitElementPresentBy(10, 1, By.xpath("//ul/li[2]/div/div[1]/img")));
	}

	///////////////// Calendar Actions //////////////////////

	public void waitNextMonth() throws InterruptedException {
		click(driverHelper.fluentWaitElementPresentBy(10, 1,
				By.xpath("//*[@id='hotel-container']/section/div[1]/div/form/ul/li[1]/div[2]/div/nav/button[2]")));
	}

//	public void waitPrevMonth() throws InterruptedException {
//		click(prevMonth);
//		click(driverHelper.fluentWaitElementPresentBy(10, 1, By.xpath("")));
//	}

	private void waitInDate(String azi) {
		inDay = driverHelper.fluentWaitElementPresentBy(10, 1,
				By.xpath("//div/div[@name='check_out']//span[contains(text(), " + azi + ")]/.."));
	}

	private void waitOutDate(String azi) {
		outDay = driverHelper.fluentWaitElementPresentBy(10, 1,
				By.xpath("//div/div[@name='check_out']//span[contains(text(), " + azi + ")]/.."));
	}

	public void waitAndClickInDay(String azi) throws InterruptedException {
		// waitInDate(azi);
		click(driverHelper.fluentWaitElementPresentBy(10, 1,
				By.xpath("//div/div[@name='check_out']//span[contains(text(), " + azi + ")]/..")));
	}

	public void waitAndClickOutDay(String zi) throws InterruptedException {
		waitOutDate(zi);
		click(outDay);
	}

	///////////////// Checks ///////////////////////

	public boolean isOutDateClickable(String zi) {
		Boolean flag = true;
		outDate(zi);
		String isDisabled = outDay.getAttribute("disabled");
		if (isDisabled != null) {
			flag = false;
			System.out.println("Date is not clickable");
			return flag;
		}

		System.out.println("Date is clickable!");
		return flag;
	}

	public boolean isOutDateGreyed(String zi) {
		Boolean flag = false;
		waitOutDate(zi);
		if (outDay.getCssValue("opacity").equals("0.5")) {
			flag = true;
			System.out.println("The button is greyed out");
			return flag;
		}

		System.out.println("The button is not grayed out");
		return flag;
	}

	public boolean isInDateClickable(String zi) {
		Boolean flag = true;
		waitInDate(zi);
		String isDisabled = inDay.getAttribute("disabled");
		if (isDisabled != null) {
			flag = false;
			System.out.println("Date is not clickable");
			return flag;
		}

		System.out.println("Date is clickable!");
		return flag;
	}

	public boolean isCorrectTimeFrame() {
		Boolean flag = false;
		if (periodBooking.getText().equals("Aug 25-31, 2019 | 6 night(s)")) {
			flag = true;
			System.out.println("Correct timeframe");
			return flag;
		}
		System.out.println("Incorect timeframe");
		return flag;
	}

	public boolean isOverBook() throws InterruptedException {
		boolean flag = false;
		waitBodyFrame();
		if (driverHelper.fluentWaitElementPresentBy(10, 1, By.xpath("//div[@class='content-body']/div/p/span"))
				.getText().equals("Sorry, we don’t take bookings over 365 nights. Try a shorter period."))
			flag = true;

		return flag;
	}

	public boolean isPriceRight() {
		boolean flag = false;
		if (roomTotalPrice.getText().equals("$620")) {
			flag = true;
			System.out.println("Price is right");
			return flag;
		}

		System.out.println("Price is not ok");
		return flag;
	}

}
