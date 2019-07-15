package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.TestBase;

public class Contact extends TestBase {
	protected WebDriver driver;

	public Contact(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	////////////////
	// Elements ////
	///////////////

	////////////////////////////// Navigation_bar//////////////////////////////

	@FindBy(xpath = "//a[contains(text(), 'HOME & AWAY')]")
	private WebElement homeLogo;

	@FindBy(xpath = "//p[@id='i6kl732v0label']")
	private WebElement home;

	@FindBy(xpath = "//p[@id='i6kl732v1label']")
	private WebElement explore;

	@FindBy(xpath = "//p[@id='i6kl732v2label']")
	private WebElement rooms;

	@FindBy(xpath = "//p[@id='i6kl732v3label']")
	private WebElement contact;

	@FindBy(xpath = "//a[@id='i6tj0u8xlink']")
	private WebElement bookNow;

	////////////////////////// Contact_block//////////////////////////////

	//// Social_bar ///////

	@FindBy(xpath = "//a[@id='i6m1143v0imagelink']")
	WebElement fb_contact;

	@FindBy(xpath = "//a[@id='i6m1143v1imagelink']")
	WebElement tweet_contact;

	@FindBy(xpath = "//a[@id='i6m1143v2imagelink']")
	WebElement pint_contact;

	//// Contact_fields ////

	@FindBy(xpath = "//input[@id='comp-jxbsa1e9input']")
	WebElement name;

	@FindBy(xpath = "//input[@id='comp-jxbsa1eminput']")
	WebElement email;

	@FindBy(xpath = "//input[@id='comp-jxbsa1evinput']")
	WebElement phone;

	@FindBy(xpath = "//textarea[@id='comp-jxbsa1f7textarea']")
	WebElement message;

	@FindBy(xpath = "//button[@id='comp-jxbsa1filink']")
	WebElement submit;

	//// Map //////////

	@FindBy(xpath = "//*[@id='map_canvas']/div/div/div[2]/a/div/img")
	WebElement google;

	@FindBy(xpath = "//button[@aria-label='Toggle fullscreen view']")
	WebElement fullscreenMap;

	@FindBy(xpath = "//*[@id='map_canvas']/div/div/div[1]/div[3]/div/div[3]/div/img")
	WebElement locationMarker;

	@FindBy(xpath = "//*[@id='content']/p/b")
	WebElement locationLabel;

	@FindBy(xpath = "//*[@id='map_canvas']/div/div/div[6]/div[2]/a")
	WebElement googleTerms;

	@FindBy(xpath = "//*[@id='map_canvas']/div/div/div[7]/div[2]/a")
	WebElement reportGoogleError;

	////////////////////////// Bottom_social_bar ////////////////////////////////
	
	@FindBy(xpath = "")
	WebElement fb_btn;
	
	@FindBy(xpath = "")
	WebElement twitter_btn;
	
	@FindBy(xpath ="")
	WebElement pint_btn;
	
	/////////////////////// Bottom_contact //////////////////////////////////////
	
	
	////////////////////////
	// Actions on Elements//
	//////////////////////

	public void clickSubmit(WebElement element) throws InterruptedException {
		click(submit);
	}

}
