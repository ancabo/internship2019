package commons;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.google.common.base.Function;
import com.google.common.base.Stopwatch;


import commons.TestBase;

public class DriverHelpers extends TestBase {

	public void changeTab(int index) throws InterruptedException {
		Thread.sleep(5000);
		//fluentWaitElementPresentBy(7,1, By.xpath("//*[@id='js_k']/div/div/div[1]/div[1]/h1/a/span"));
		ArrayList<String> browserTabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(browserTabs.get(index));
	}

	
    public void waitPageLoad(int sec) {
        driver.manage().timeouts().pageLoadTimeout(sec, TimeUnit.SECONDS);
  }


  public void waitUntilElementNotDisplayed(WebElement element) throws InterruptedException {
        Stopwatch stopwatch = Stopwatch.createStarted();
<<<<<<< Updated upstream
        while (isElementDisplayed(element) && stopwatch.elapsed(TimeUnit.SECONDS) < 60) {
=======
        while (isElementDisplayed(element) && stopwatch.elapsed(TimeUnit.SECONDS) < 6000) {
>>>>>>> Stashed changes
               Thread.sleep(1000); 
        }
        stopwatch.stop();
  }

  
  public Boolean isElementDisplayed(WebElement element) throws InterruptedException {
        Boolean flag = false;
        
        for(int i=0; i<=5; i++)   {
               try {
                      flag = element.isDisplayed();
                      break;
               } 
               catch (Exception e) {
<<<<<<< Updated upstream
                      Thread.sleep(2000);                     
=======
            	   Thread.sleep(2000);                      
>>>>>>> Stashed changes
               }      
        }
        return flag;
  }

  public WebElement fluentWaitElementPresentBy(int secTimeOut, int secSearchInterval, By by){
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(secTimeOut, TimeUnit.SECONDS)
                      .pollingEvery(secSearchInterval, TimeUnit.SECONDS).ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
        
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



}
