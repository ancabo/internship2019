package commons;

import java.util.ArrayList;


import commons.TestBase;

public class DriverHelpers extends TestBase {

	public void changeTab(int index) throws InterruptedException {
		Thread.sleep(2000);
		ArrayList<String> browserTabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(browserTabs.get(index));
	}
	
}
