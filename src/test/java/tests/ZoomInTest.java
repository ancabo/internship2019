package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import commons.TestBase;
import commons.DriverHelpers;
import pages.Contact;
import pages.Rooms;
import pages.Header;

public class ZoomInTest extends TestBase {

	Contact contact;
	DriverHelpers d_helper;
	Header header;
	Rooms room;

	@BeforeMethod
	public void elements() {
		contact = new Contact(driver);
		d_helper = new DriverHelpers();
		header = new Header(driver);
		room = new Rooms(driver);
	}

	@Test(priority = 0)
	public void zoomIn() throws InterruptedException {
		header.clickContact();
		contact.scrollDown(0, 2500);
		contact.changeIframe();
		contact.doubleClickToZoomIn();
		Thread.sleep(2000);
	}

	@Test(priority = 1)
	public void iconFacebook() throws InterruptedException {
		SoftAssert sa = new SoftAssert();
		contact.clickContact();
		contact.scrollDown(0, 3000);
		contact.clickIcon();
		d_helper.changeTab(1);
		contact.compareCrtUrlToOtherURL("https://www.facebook.com/wix");
		sa.assertEquals(contact.compareCrtUrlToOtherURL(getCrtURL()), true);
		sa.assertAll();
	}

	@Test(priority = 2)
	public void booking() throws InterruptedException {
		SoftAssert outDay = new SoftAssert();
		header.clickRooms();
		room.scrollDown(0, 2500);
		room.bodyFrame();
		room.roomClickCheckIn();
		room.nextMonth();
		room.clickInDay("25");
		Thread.sleep(2000);
		
		outDay.assertEquals(room.isOutDateClickable("24"), false);
		
		Thread.sleep(2000);
		outDay.assertEquals(room.isOutDateGreyed("24"), true);
		
		room.clickOutDay("31");
		
		room.roomIncreaseAdults();
		room.roomSearch();
		
		outDay.assertEquals(room.isCorrectTimeFrame(), true);
		
		
		room.scrollDown(0, 5000);
		room.roomHighestRate();
		
		
		outDay.assertEquals(room.isOutDateClickable("25+2"), false);

		Thread.sleep(2000);
		
		outDay.assertEquals(room.isPriceRight(), true);
		outDay.assertAll();
	}
}
