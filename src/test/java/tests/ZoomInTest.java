package tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import commons.TestBase;
import commons.DriverHelpers;
import commons.LogType;
import pages.Contact;
import pages.Rooms;
import pages.Header;
import pages.Footer;

public class ZoomInTest extends TestBase {

	Contact contact;
	DriverHelpers d_helper;
	Header header;
	Rooms room;
	Footer footer;

	@BeforeMethod
	public void elements() {
		contact = new Contact(driver);
		d_helper = new DriverHelpers();
		header = new Header(driver);
		room = new Rooms(driver);
		footer = new Footer(driver);
	}

	@Test(priority = 0)
	public void zoomIn() throws InterruptedException  {
		SoftAssert zoom = new SoftAssert();
		logReport(LogType.INFO, "Test1 started.");
		header.waitAndClickContact();
		contact.waitAndChangeIframe();
		contact.doubleClickToZoomIn();
		zoom.assertEquals(contact.checkZoomIn(), true);
		logReport(LogType.INFO, "Zoom test finished.");
		zoom.assertAll();
		
		
	}

	@Test(priority = 1)
	public void iconFacebook() throws InterruptedException {
		SoftAssert sa = new SoftAssert();
		logReport(LogType.INFO, "test2 started");
		header.waitAndClickContact();
		footer.waitAndClickFacebookIcon();
		d_helper.changeTab(1);
		sa.assertEquals(contact.compareCrtUrlToOtherURL(getCrtURL()), true);
		logReport(LogType.INFO, "facebook checked");
		sa.assertAll();
	}

	@Test(priority = 2)
	public void booking() throws InterruptedException {
		SoftAssert outDay = new SoftAssert();
		logReport(LogType.INFO, "test3 started");
		header.waitAndClickRooms();
		room.scrollDown(0, 2500);
		room.bodyFrame();
		room.roomClickCheckIn();
		room.nextMonth();
		room.clickInDay("25");
	
		outDay.assertEquals(room.isOutDateClickable("24"), false);
		logReport(LogType.INFO, "Click out date checked.");
	
		outDay.assertEquals(room.isOutDateGreyed("24"), true);
		
		room.clickOutDay("31");
		
		room.roomIncreaseAdults();
		room.roomSearch();
		
		outDay.assertEquals(room.isCorrectTimeFrame(), true);
		logReport(LogType.INFO, "Period booking is checked");
		
		room.scrollDown(0, 5000);
		room.roomHighestRate();
		
		
		outDay.assertEquals(room.isOutDateClickable("25+2"), false);
		logReport(LogType.INFO, "out date checked.");
	
		
		outDay.assertEquals(room.isPriceRight(), true);
		logReport(LogType.INFO, "Price checked.");
		outDay.assertAll();
	}
}
