package commons;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;
import com.google.common.base.Stopwatch;

import commons.TestBase;

public class DriverHelpers extends TestBase {

	WebDriverWait wait = new WebDriverWait(driver, 10);

	public void changeTab(int index) throws InterruptedException {
		Thread.sleep(5000);
		ArrayList<String> browserTabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(browserTabs.get(index));
	}

	public void waitPageLoad(int sec) {
		driver.manage().timeouts().pageLoadTimeout(sec, TimeUnit.SECONDS);
	}

	public void waitUntilElementNotDisplayed(WebElement element) throws InterruptedException {
		Stopwatch stopwatch = Stopwatch.createStarted();
		while (isElementDisplayed(element) && stopwatch.elapsed(TimeUnit.SECONDS) < 60) {

			Thread.sleep(1000);
		}
		stopwatch.stop();
	}

	public Boolean isElementDisplayed(WebElement element) throws InterruptedException {
		Boolean flag = false;

		for (int i = 0; i <= 5; i++) {
			try {
				flag = element.isDisplayed();
				break;
			} catch (Exception e) {
				Thread.sleep(2000);

			}
		}
		return flag;
	}

	public WebElement fluentWaitElementPresentBy(int secTimeOut, int secSearchInterval, By by) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(secTimeOut, TimeUnit.SECONDS)
				.pollingEvery(secSearchInterval, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class, StaleElementReferenceException.class);

		WebElement fluentElement = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(by);
			}
		});
		return fluentElement;
	}

	public WebElement fluentWait(int secTimeOut, int secSearchInterval, WebElement element) {

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(secTimeOut, TimeUnit.SECONDS)
				.pollingEvery(secSearchInterval, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

		WebElement fluentElement = wait.until(new Function<WebDriver, WebElement>() {

			public WebElement apply(WebDriver driver) {
				return element;
			}

		});
		return fluentElement;
	}

	public void waitForElementVisibility(By by) throws InterruptedException {
		int i = 5;
		while (i != 0) {
			try {
				wait.until(ExpectedConditions.elementToBeClickable(by));
			} catch (Exception e) {
				Thread.sleep(500);
				System.out.println("waitforvisielement exception" + e);
			}
			i--;
		}

	}

	public void waitForElementVisible(By by) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public void waitForFrameAndSwitch(By by) {
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(by));
	}

	// wait for javascript wait
	public void waitJavaScriptIsLoaded(int timeout) {
		WebDriverWait waitJavaScript = new WebDriverWait(driver, timeout);
		waitJavaScript.until(ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";"));
	}
	
}
