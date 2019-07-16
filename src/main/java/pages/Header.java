package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import commons.TestBase;

public class Header extends TestBase {

	protected WebDriver driver;

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
	public void clickHomeLogo() throws InterruptedException {
		click(homeLogo);
	}

	public void clickHome() throws InterruptedException {
		click(home);
	}

	public void clickExplore() throws InterruptedException {
		click(explore);
	}

	public void clickRooms() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		WebElement rooms = wait.until(ExpectedConditions.elementToBeClickable(By.id("i6kl732v2label")));
		click(rooms);
	
	}

	public void clickContact() throws InterruptedException {
		contact.click();
	}

	public void clickBookNow() throws InterruptedException {
		click(book_now);
	}
	
	
}
