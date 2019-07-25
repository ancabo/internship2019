package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.TestBase;
import commons.DriverHelpers;

public class Header extends TestBase {

	protected WebDriver driver;
	DriverHelpers d_helper = new DriverHelpers();
	public Header(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/////////////
	// Elements//
	////////////

	@FindBy(xpath = "//*[@id=\'i6ksxrtk\']/h1/a")
	private WebElement homeLogo;

	@FindBy(id = "i6kl732v0label")
	private WebElement home;

	@FindBy(id = "i6kl732v1label")
	private WebElement explore;

	@FindBy(id = "i6kl732v2label")
	private WebElement rooms;

	@FindBy(id = "i6kl732v3label")
	private WebElement contact;

	@FindBy(id = "i6tj0u8xlabel")
	private WebElement book_now;
	
	///////////////////
	///// Actions /////
	//////////////////
	public void waitAndClickHomeLogo() throws InterruptedException {
		d_helper.waitForElementVisibility(By.xpath("//*[@id=\'i6ksxrtk\']/h1/a"));
		click(homeLogo);
	}

	public void waitAndClickHome() throws InterruptedException {
		d_helper.waitForElementVisibility(By.id("i6kl732v0label"));
		click(home);
	}

	public void waitAndClickExplore() throws InterruptedException {
		d_helper.waitForElementVisibility(By.id("i6kl732v1label"));
		click(explore);
	}

	public void waitAndClickRooms() throws InterruptedException {
		d_helper.waitForElementVisibility(By.id("i6kl732v2label"));
		click(rooms);
	
	}

	public void waitAndClickContact() throws InterruptedException {
		d_helper.waitForElementVisibility(By.id("i6kl732v3label"));
		contact.click();
	}

	public void waitAndClickBookNow() throws InterruptedException {
		d_helper.waitForElementVisibility(By.id("i6tj0u8xlabel"));
		click(book_now);
	}
	
	
}
