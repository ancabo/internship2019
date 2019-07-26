package tests;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import commons.DataProviderBase;
import commons.DriverHelpers;
import commons.Helpers;
import commons.Constants;
import commons.TestBase;
import pages.Contact;
import pages.Footer;
import pages.Header;
import pages.Rooms;



public class TestsDataProvider extends TestBase {
	
	Contact contact;
	DriverHelpers d_helper;
	Header header;
	Rooms room;
	Footer footer;
	Helpers helper;

	@BeforeMethod
	public void elements() {
		contact = new Contact(driver);
		d_helper = new DriverHelpers();
		header = new Header(driver);
		room = new Rooms(driver);
		footer = new Footer(driver);
		helper = new Helpers();
	}

	
	@Test(dataProvider="DateProviderS", dataProviderClass = DataProviderBase.class)
	public void bookTestS(String checkInDay, String checkOutDayClickable, String checkOutDay, String checkOutDayClick) throws InterruptedException, IOException {
		SoftAssert outDay = new SoftAssert();
		logReport(Constants.INFO, "test3 started");
	
		header.waitAndClickRooms();
		room.waitBodyFrame();
		room.waitRoomClickCheckIn();
		room.waitNextMonth();
		room.clickInDay(checkInDay);
		
		outDay.assertEquals(room.isOutDateClickable(checkOutDayClickable), false);
		logReport(Constants.INFO, "Click out date checked.");
		outDay.assertEquals(room.isOutDateGreyed(checkOutDayClickable), true);
		
		room.waitAndClickOutDay(checkOutDay);
		room.waitRoomIncreaseAdults();
		room.waitRoomSearch();
		outDay.assertEquals(room.isCorrectTimeFrameOneMonth(checkInDay, checkOutDay), true);
		logReport(Constants.INFO, "Period booking is checked");
		room.waitRoomHighestRate();
		Thread.sleep(1500);
		room.waitRoomClickCheckOut();
		outDay.assertEquals(room.isOutDateClickable(checkOutDayClick), false);
		logReport(Constants.INFO, "out date checked.");
		outDay.assertEquals(room.isPriceRight(), true);
		logReport(Constants.INFO, "Price checked.");
		
		outDay.assertAll();

	}
	}

