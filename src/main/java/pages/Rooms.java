package pages;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.CaptureScreenShot;
import commons.DriverHelpers;
import commons.Helpers;
import commons.TestBase;

public class Rooms extends TestBase {

	protected WebDriver driver;
	Helpers helper = new Helpers();
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
		driverHelper
				.waitForElementVisible(By.xpath("//div/div[@name='check_in']//span[contains(text(), " + zi + ")]/.."));
		inDay = driver.findElement(By.xpath("//div/div[@name='check_in']//span[contains(text(), " + zi + ")]/.."));
	}

	private WebElement outDay;

	private void outDate(String zi) {
		driverHelper
				.waitForElementVisible(By.xpath("//div/div[@name='check_out']//span[contains(text(), " + zi + ")]/.."));
		outDay = driver.findElement(By.xpath("//div/div[@name='check_out']//span[contains(text(), " + zi + ")]/.."));
	}

	@FindBy(xpath = "//h2[@class='s-title resultbar s-separator']/strong")
	private WebElement periodBooking;

	@FindBy(xpath = "//div/div[@name='check_in']/div")
	private WebElement checkInMonth;

	@FindBy(xpath = "//div/div[@name='check_out']/div")
	private WebElement checkOutMonth;

	/// timeperiod
	private int timePeriod;

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

	public void waitBodyFrame() throws InterruptedException {

		driverHelper.waitForFrameAndSwitch(By.xpath("//iframe[@title='Book a Room']"));
		// driverHelper.waitForElementVisibility(By.xpath("//iframe[@title='Book a
		// Room']"));
		// changeFrame(driverHelper.fluentWaitElementPresentBy(15, 1,
		// By.xpath("//iframe[@title='Book a Room']")));
		// changeFrame(bodyFrame);

	}

	///////////////////////
	//// Booking actions///
	///////////////////////

	public void waitRoomClickCheckIn() throws InterruptedException {
		driverHelper.waitForElementVisibility(By.xpath("//div[@id='check-in']"));
		click(checkIn);
		// click(driverHelper.fluentWaitElementPresentBy(15, 1,
		// By.xpath("//div[@id='check-in']")));
	}

	public void waitRoomClickCheckOut() throws InterruptedException {
		driverHelper.waitForElementVisibility(By.xpath("//div[@id='check-out']"));
		// click(driverHelper.fluentWaitElementPresentBy(15, 1,
		// By.xpath("//div[@id='check-out']")));
		click(checkOut);
	}

	public void waitRoomIncreaseAdults() throws InterruptedException {
		driverHelper.waitForElementVisibility(By.xpath("//li[@class='adults']/div/a"));
		// click(driverHelper.fluentWaitElementPresentBy(15, 1,
		// By.xpath("//li[@class='adults']/div/a")));
		click(adultsIncrease);
	}

	public void waitRoomDecreaseAdults() throws InterruptedException {
		driverHelper.waitForElementVisibility(By.xpath("//li[@class='adults']/div/a/following-sibling::a"));
//		click(driverHelper.fluentWaitElementPresentBy(15, 1,
//				By.xpath("//li[@class='adults']/div/a/following-sibling::a")));
		click(adultsDecrease);
	}

	public void waitRoomIncreaseKids() throws InterruptedException {
		driverHelper.waitForElementVisibility(By.xpath("//li[@class='children']/div/a"));
		// click(driverHelper.fluentWaitElementPresentBy(15, 1,
		// By.xpath("//li[@class='children']/div/a")));
		click(kidsIncrease);
	}

	public void waitRoomDecreaseKids() throws InterruptedException {
		driverHelper.waitForElementVisibility(By.xpath("//li[@class='children']/div/a/following-sibling::a"));
//		click(driverHelper.fluentWaitElementPresentBy(15, 1,
//				By.xpath("//li[@class='children']/div/a/following-sibling::a")));
		click(kidsDecrease);
	}

	public void waitRoomSearch() throws InterruptedException {
		driverHelper.waitForElementVisibility(By.xpath("//button[@class='search s-button']"));
		// click(driverHelper.fluentWaitElementPresentBy(15, 1,
		// By.xpath("//button[@class='search s-button']")));
		click(searchBtn);
	}

	public void waitRoomLowestRate() throws InterruptedException {
		driverHelper.waitForElementVisibility(By.xpath("//ul/li[1]/div/div[1]/img"));
		// click(driverHelper.fluentWaitElementPresentBy(15, 1,
		// By.xpath("//ul/li[1]/div/div[1]/img")));
		click(roomWithLowestRate);
	}

	public void waitRoomMediumRate() throws InterruptedException {
		driverHelper.waitForElementVisibility(By.xpath("//ul/li[2]/div/div[1]/img"));
		// click(driverHelper.fluentWaitElementPresentBy(15, 1,
		// By.xpath("//ul/li[2]/div/div[1]/img")));
		click(roomWithMediumRate);
	}

	public void waitRoomHighestRate() throws InterruptedException {
		driverHelper.waitForElementVisibility(By.xpath("//*[@id='content']/div/div[2]/div/ul/li[3]/div/div[1]/img"));
//		click(driverHelper.fluentWaitElementPresentBy(15, 1,
//				By.xpath("//*[@id='content']/div/div[2]/div/ul/li[3]/div/div[1]/img")));
		click(roomWithTheHighestRate);

	}

	///////////////// Calendar Actions //////////////////////

	public void waitNextMonth() throws InterruptedException {
		driverHelper.waitForElementVisibility(
				By.xpath("//*[@id='hotel-container']/section/div[1]/div/form/ul/li[1]/div[2]/div/nav/button[2]"));
		// click(driverHelper.fluentWaitElementPresentBy(30, 1,
		// By.xpath("//*[@id='hotel-container']/section/div[1]/div/form/ul/li[1]/div[2]/div/nav/button[2]")));
		click(nextMonth);
	}

//	public void waitPrevMonth() throws InterruptedException {
//		click(prevMonth);
//		click(driverHelper.fluentWaitElementPresentBy(10, 1, By.xpath("")));
//	}

//	private void waitInDate(String azi) {
//		inDay = driverHelper.fluentWaitElementPresentBy(15, 1,
//				By.xpath("//div/div[@name='check_out']//span[contains(text(), " + azi + ")]/.."));
//	}
//
//	private void waitOutDate(String azi) {
//		outDay = driverHelper.fluentWaitElementPresentBy(15, 1,
//				By.xpath("//div/div[@name='check_out']//span[contains(text(), " + azi + ")]/.."));
//	}

	public void waitAndClickInDay(String azi) throws InterruptedException {
		driverHelper.waitForElementVisibility(
				By.xpath("//div/div[@name='check_in']//span[contains(text(), " + azi + ")]/.."));
		inDate(azi);
		try {
			click(inDay);
		} catch (Exception e) {
			System.out.println("Exception!!!!!!!!!!");
			inDate(azi);
			click(inDay);
		}

	}

	public void waitAndClickOutDay(String zi) throws InterruptedException {
		driverHelper.waitForElementVisibility(
				By.xpath("//div/div[@name='check_out']//span[contains(text(), " + zi + ")]/.."));
		outDate(zi);
		click(outDay);
	}

	///////////////// Checks ///////////////////////

	public boolean isOutDateClickable(String zi) {
		Boolean flag = true;
		driverHelper
				.waitForElementVisible(By.xpath("//div/div[@name='check_out']//span[contains(text(), " + zi + ")]/.."));
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

	public boolean isOutDateGreyed(String zi) throws InterruptedException {
		Boolean flag = false;
		driverHelper.waitForElementVisible(
				By.xpath("//div/div[@name='check_in']//span[contains(text(), " + zi + ")]/.."));
		outDate(zi);
		if (outDay.getCssValue("opacity").equals("0.5")) {
			flag = true;
			System.out.println("The button is greyed out");
			return flag;
		}

		System.out.println("The button is not grayed out");
		return flag;
	}

	public boolean isInDateClickable(String zi) throws IOException {
		Boolean flag = true;
		driverHelper.waitForElementVisible(
				By.xpath("//div/div[@name='check_in']//span[contains(text(), " + zi + ")]/.."));
		inDate(zi);
		String isDisabled = inDay.getAttribute("disabled");
		if (isDisabled != null) {
			flag = false;
			System.out.println("Date is not clickable");
			CaptureScreenShot.captureScreen(driver, CaptureScreenShot.generateFileName("in_date unclickable"));
			return flag;
		}

		System.out.println("Date is clickable!");
		CaptureScreenShot.captureScreen(driver, CaptureScreenShot.generateFileName("in_date clickable"));
		return flag;
	}

	public boolean isCorrectTimeFrameOneMonth(String inDay, String outDay) throws IOException {
		Boolean flag = false;
		//// bad bad bad, will fix the dates later :(
		int intOutDay = Double.valueOf(outDay).intValue();
		int intInDay = Double.valueOf(inDay).intValue();
		timePeriod = intOutDay - intInDay;
		// if (periodBooking.getText().equals("Aug 25-31, 2019 | 6 night(s)")) {
		if (periodBooking.getText().equals("Aug " + String.valueOf(intInDay) + "-" + String.valueOf(intOutDay)
				+ ", 2019 | " + String.valueOf(timePeriod) + " night(s)")) {
			flag = true;
			System.out.println("Correct timeframe");
			CaptureScreenShot.captureScreen(driver, CaptureScreenShot.generateFileName("correct timeframe"));
			return flag;
		}
		System.out.println("Incorect timeframe");
		CaptureScreenShot.captureScreen(driver, CaptureScreenShot.generateFileName("incorrect timeframe"));
		return flag;
	}

	public boolean isOverBook() throws InterruptedException, IOException {
		boolean flag = false;
		waitBodyFrame();
		if (driverHelper.fluentWaitElementPresentBy(15, 1, By.xpath("//div[@class='content-body']/div/p/span"))
				.getText().equals("Sorry, we don’t take bookings over 365 nights. Try a shorter period.")) {

			flag = true;
			CaptureScreenShot.captureScreen(driver, CaptureScreenShot.generateFileName("overbook message"));
		}
		CaptureScreenShot.captureScreen(driver, CaptureScreenShot.generateFileName("no overbook message"));
		return flag;
	}

	public boolean isPriceRight() throws IOException {
		boolean flag = false;
		Double daylyPrice = Double.valueOf(roomPerDayPrice.getText().substring(1));
		int totalPrice = (int) Math.round(daylyPrice * timePeriod);
		if (driverHelper.fluentWaitElementPresentBy(5, 1, By.xpath("//tr[@class='total']/td/following-sibling::td"))
				.getText().equals("$" + String.valueOf(totalPrice))) {
			flag = true;
			System.out.println("Price is right");
			CaptureScreenShot.captureScreen(driver, CaptureScreenShot.generateFileName("total price - right"));
			return flag;
		}

		System.out.println("Price is not ok");
		CaptureScreenShot.captureScreen(driver, CaptureScreenShot.generateFileName("total price not ok"));
		return flag;
	}

/////////////////keyword////////////
	public String checkInXL(int index, ArrayList<String> arrayList) throws IOException {
		return arrayList.get(index);
	}

	public String preCheckInXL(int days, int index, ArrayList<String> arrayList) throws IOException {
//		String checkInString = helper.readFromExcel("System.getProperty(\"user.dir\")", "bookerDate_test.xls",
//				"CHECK-IN", cellColumn);
//		double checkInDaysPrior = Double.valueOf(checkInString) - days;
//		return String.valueOf(checkInDaysPrior);
		String checkInString = arrayList.get(index);
		int checkInDaysPrior = Double.valueOf(checkInString).intValue() - days;
		return String.valueOf(checkInDaysPrior);

	}

	public String preCheckOutXL(int days, int index, ArrayList<String> arrayList) throws IOException {
//		String checkOutString = helper.readFromExcel(System.getProperty("user.dir"), "bookerDate_test.xls",
//				"CHECK-OUT", cellColumn);
//		double checkInDaysPrior = Double.valueOf(checkOutString) - days;
//		return String.valueOf(checkInDaysPrior);
		String checkInString = arrayList.get(index);
		int checkOutDaysPrior = Double.valueOf(checkInString).intValue() + days;
		return String.valueOf(checkOutDaysPrior);

	}

	public String checkOutXL(int index, ArrayList<String> arrayList) throws IOException {
		return arrayList.get(index);
	}
}
