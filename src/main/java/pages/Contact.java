package pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.TestBase;
import commons.CaptureScreenShot;
import commons.DriverHelpers;

public class Contact extends TestBase {
	protected WebDriver driver;

	DriverHelpers d_helper = new DriverHelpers();
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

	@FindBy(xpath = "//*[@id='map_canvas']/div/div/div[4]")
	private WebElement mapCoord;
	
	////Social bar////
	
	@FindBy(id = "i6m1143v0imagelink")
	private WebElement fbBtn;
	
	@FindBy(id = "i6m1143v1imagelink")
	private WebElement twitterBtn;
	
	@FindBy(id = "i6m1143v2imagelink")
	private WebElement pinterestBtn;
	
	
	/////////Action on elements////////////////
	
	public void waitAndChangeIframe() throws InterruptedException {
		d_helper.fluentWaitElementPresentBy(15, 1, By.xpath("//*[@id='i6lyzjshmapContainer']/iframe"));
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
		d_helper.fluentWait(10, 1, google);
		doubleClick(google,0,-35);
	}
	
	
	public boolean checkZoomIn() throws IOException {
		boolean flag = false;
		
		String zoomX = mapCoord.getAttribute("right");
		String zoomY = mapCoord.getAttribute("width");
		if(zoomX != "280px" || zoomY != "121px") {
			flag = true;
			System.out.println("The map is zoomed");
			CaptureScreenShot.captureScreen(driver, CaptureScreenShot.generateFileName("Map is zoomed"));
			return flag;
		}
		System.out.println("Zoom does not work");
		CaptureScreenShot.captureScreen(driver, CaptureScreenShot.generateFileName("Zoom map doesn't work"));
		return flag;
	}
	
	
	public boolean compareCrtUrlToOtherURL(String url) throws InterruptedException {
		boolean flag = false;
		Thread.sleep(1000);
		if (getCrtURL().equals(url))
			flag = true;
		return flag;
	}



	
}
