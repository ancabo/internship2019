package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
//import java.time.LocalDateTime;
//mport java.time.format.DateTimeFormatter;
import commons.TestBase;

public class Home extends TestBase {
	protected WebDriver driver;

	public Home(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// LocalDateTime data_azi= LocalDateTime.now();
	// DateTimeFormatter format=DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	// String format_azi=data_azi.format(format);
	// String azi=format_azi.substring(0,2);


	//////////////////////
	/// Booking Elements///
	//////////////////////

	@FindBy(id = "check-in")
	private WebElement checkIn;

//	@FindBy(xpath = "//span[contains(text(), "+azi+")]")
//	private WebElement inDay;
	// WebElement in_day=driver.findElement(By.xpath("//span[contains(text(),
	// "+azi+")]"));

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

	private void inDate(String azi) {
		in_day = driver.findElement(By.xpath("//span[contains(text(), " + azi + ")]"));
	}

	private WebElement out_day;

	private void outDate(String zi) {
		out_day = driver.findElement(By.xpath("//span[contains(text(), " + zi + ")]"));
	}

	///////////////////
	/// Chat Element///
	//////////////////

	@FindBy(xpath = "//*[@class='_2wjrm']")
	private WebElement chatBtn;

	@FindBy(xpath = "//div[@data-hook = 'chat-widget']/div")
	private WebElement chatWidget;

	/////////////////////////
	/// Social Bar Elements///
	/////////////////////////

	@FindBy(id = "i6rlbitx0imagelink")
	private WebElement fbBtn;

	@FindBy(id = "i6rlbitx1imagelink")
	private WebElement twitterBtn;

	@FindBy(id = "i6rlbitx2imagelink")
	private WebElement pintBtn;

	@FindBy(xpath = "//*[@id='i71ww6nk']/p/object/a")
	private WebElement mailAddr;

	@FindBy(xpath = "//*[@id='i71wwqnj']/p[2]/span/a")
	private WebElement wixSite;

	////////////////////////
	///// Frames //////////
	//////////////////////

	@FindBy(xpath = "//iframe[@title='Wix Hotels']")
	private WebElement bookerFrame;

	@FindBy(xpath = "//iframe[@class='s_yOSHETPAPopupSkiniframe']")
	private WebElement calendarFrame;

	@FindBy(xpath = "//iframe[@title='Wix Chat']")
	private WebElement wixChatFrame;

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
		calendarFrame();
	}

	public void clickOutDay(String zi) throws InterruptedException {
		outDate(zi);
		click(out_day);
		parentFrame();
		Thread.sleep(500);
	}

	///////////////////////////// Booking keywords ///////////////////////

	public void setFrameAndCheckin() throws InterruptedException {
		Thread.sleep(7000);
		bookingFrame();
		clickCheckIn();
		parentFrame();
		calendarFrame();
	}

	public void setFrameAndCheckOut() throws InterruptedException {
		bookingFrame();
		click(checkOut);
		parentFrame();
		calendarFrame();
	}

	public void setFrameAndClickSearchBtn() throws InterruptedException {
		bookingFrame();
		click(searchBtn);
		parentFrame();
		Thread.sleep(3000);
	}

	///////////////////////////
	// Frame Change Actions //
	//////////////////////////

	public void bookingFrame() {
		changeFrame(bookerFrame);
	}

	public void calendarFrame() {
		changeFrame(calendarFrame);
	}

	public void chatFrame() {
		changeFrame(wixChatFrame);
	}

	///////////////////////////////////////
	// Chat button actions and keywords //
	//////////////////////////////////////

	public void setFrameAndClickChat() throws InterruptedException {
		Thread.sleep(2500);
		chatFrame();
		click(chatBtn);
	}

	/////////////////////// Chat button keywords //////////////////////

	public boolean isChatOpen() {
		String chatVal;
		chatVal = chatWidget.getAttribute("aria-expanded");
		if (chatVal.equals("true"))
			return true;
		else
			return false;
	}

}
