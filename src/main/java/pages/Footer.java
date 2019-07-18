package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.DriverHelpers;
import commons.TestBase;
public class Footer extends TestBase {

	protected WebDriver driver;
	DriverHelpers d_helper = new DriverHelpers();
	public Footer(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
		////////////////
		//// Elements////
		////////////////

	@FindBy(id = "i6rlbitx0imageimageimage")
	private WebElement fbIcon;

	@FindBy(id = "i6rlbitx1imagelink")
	private WebElement twitterIcon;

	@FindBy(id = "i6rlbitx2imagelink")
	private WebElement pinterestIcon;

	@FindBy(xpath = "//*[@id='i71ww6nk']/p[1]/object/a")
	private WebElement emailAdress;

	@FindBy(xpath = "//*[@id='i71wwqnj']/p[2]/span/a")
	private WebElement wixSite;
	
	@FindBy(xpath = "//iframe[@title='Wix Chat']")
	private WebElement wixChatFrame;

	@FindBy(xpath = "//*[@class='_2wjrm']")
	private WebElement chatBtn;

	@FindBy(xpath = "//div[@data-hook = 'chat-widget']/div")
	private WebElement chatWidget;
	
		///////////////////////////
		//// Actions on elements////
		///////////////////////////

	public void waitAndClickFacebookIcon() {
		d_helper.fluentWaitElementPresentBy(15, 1, By.xpath("//*[@id='i6rlbitx0imageimageimage']")).click();
		//d_helper.fluentWait(10, 1,fbIcon).click();s
		//fbIcon.click();
	}

	public void waitAndClickTwitterIcon() {
		d_helper.fluentWaitElementPresentBy(15, 1, By.id("i6rlbitx1imagelink"));
		twitterIcon.click();
	}

	public void waitAndClickPinterestIcon() {
		d_helper.fluentWaitElementPresentBy(15, 1, By.id("i6rlbitx2imagelink"));
		pinterestIcon.click();
	}

	public void waitAndClickEmailAdress() {
		d_helper.fluentWaitElementPresentBy(15, 1, By.xpath("//*[@id='i71ww6nk']/p[1]/object/a"));
		emailAdress.click();
	}

	public void waitAndClickWixSite() {
		d_helper.fluentWaitElementPresentBy(15, 1, By.xpath("//*[@id='i71wwqnj']/p[2]/span/a"));
		wixSite.click();
	}


	///////////////////////////////////////
	// Chat button actions and keywords //
	//////////////////////////////////////

	public void waitAndChangeToChatFrame() {
		d_helper.fluentWait(15, 1, wixChatFrame);
		changeFrame(wixChatFrame);
	}
	
	public void setFrameAndClickChat() throws InterruptedException {
		waitAndChangeToChatFrame();
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
