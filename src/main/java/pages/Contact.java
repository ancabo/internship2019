package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.TestBase;

public class Contact extends TestBase {
	protected WebDriver driver;

	public Contact (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	////////////////
	// Elements ////
	///////////////


	
	//// Contact_fields ////

	@FindBy(xpath = "//input[@id='comp-jxbsa1e9input']")
	private WebElement name;

	@FindBy(xpath = "//input[@id='comp-jxbsa1eminput']")
	private WebElement email;

	@FindBy(xpath = "//input[@id='comp-jxbsa1evinput']")
	private WebElement phone;

	@FindBy(xpath = "//textarea[@id='comp-jxbsa1f7textarea']")
	private WebElement message;

	@FindBy(xpath = "//button[@id='comp-jxbsa1filink']")
	private WebElement submit;


	//// Map //////////

	@FindBy(xpath = "//*[@id='i6lyzjshmapContainer']/iframe")
	private WebElement iframe;
	
	@FindBy(xpath = "//*[@class='gm-style']/div[2]")
	private WebElement google;

	@FindBy(xpath = "//button[@aria-label='Toggle fullscreen view']")
	private WebElement fullscreenMap;

	@FindBy(xpath = "//*[@id='map_canvas']/div/div/div[1]/div[3]/div/div[3]/div/img")
	private WebElement locationMarker;

	@FindBy(xpath = "//*[@id='content']/p/b")
	private WebElement locationLabel;

	@FindBy(xpath = "//*[@id='map_canvas']/div/div/div[6]/div[2]/a")
	private WebElement googleTerms;

	@FindBy(xpath = "//*[@id='map_canvas']/div/div/div[7]/div[2]/a")
	private WebElement reportGoogleError;

	////Social bar////
	
	@FindBy(id = "i6m1143v0imagelink")
	private WebElement fbBtn;
	
	@FindBy(id = "i6m1143v1imagelink")
	private WebElement twitterBtn;
	
	@FindBy(id = "i6m1143v2imagelink")
	private WebElement pinterestBtn;
	
	
	/////////Action on elements////////////////
	
	public void changeIframe() throws InterruptedException {
		changeFrame(iframe);
	}
	
	public void clickFacebookButton() {
		fbBtn.click();
	}
	
	public void clickTwitterButton() {
		twitterBtn.click();
	}
	
	public void clickPinterestButton() {
		pinterestBtn.click();
	}
	
	
	public void doubleClickToZoomIn() throws InterruptedException {
		doubleClick(google,0,-35);
	}
	
	
	
	public boolean compareCrtUrlToOtherURL(String url) throws InterruptedException {
		boolean flag = false;
		Thread.sleep(1000);
		if (getCrtURL().equals(url))
			flag = true;
		return flag;
	}



	
}