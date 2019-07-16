package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.TestBase;

public class Footer extends TestBase {

	protected WebDriver driver;

	public Footer(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
		////////////////
		//// Elements////
		////////////////

	@FindBy(id = "i6rlbitx0imagelink")
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

	public void clickFacebookIcon() {
		fbIcon.click();
	}

	public void clickTwitterIcon() {
		twitterIcon.click();
	}

	public void clickPinterestIcon() {
		pinterestIcon.click();
	}

	public void clickEmailAdress() {
		emailAdress.click();
	}

	public void clickWixSite() {
		wixSite.click();
	}


	///////////////////////////////////////
	// Chat button actions and keywords //
	//////////////////////////////////////

	public void chatFrame() {
		changeFrame(wixChatFrame);
	}
	
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
