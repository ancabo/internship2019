package tests;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import commons.DataProviderBase;
import commons.Helpers;
import commons.LogType;
import commons.TestBase;
import pages.Footer;
import pages.Header;
import pages.Home;
import pages.Rooms;

@Listeners(commons.ListenersTest.class)
public class DataProviderTests extends TestBase {

	Home home;
	Rooms room;
	Header header;
	Helpers helper;
	Footer footer;

	@BeforeMethod
	public void elements() {
		header = new Header(driver);
		home = new Home(driver);
		room = new Rooms(driver);
		helper = new Helpers();
		footer = new Footer(driver);
	}

	@Test(dataProvider = "DateProvider", dataProviderClass = DataProviderBase.class)
	public void bookTestA(String checkInDate, String checkOutDate) throws InterruptedException, IOException {

		SoftAssert softAssert = new SoftAssert();
		logReport(LogType.INFO, "Room booking test started");
		int intCheckInDate = Double.valueOf(checkInDate).intValue();
		// int intCheckOutDate = Double.valueOf(checkOutDate).intValue();

		header.waitAndClickRooms();
		//Thread.sleep(3000);
		room.waitBodyFrame();
		//Thread.sleep(5000);
		room.waitRoomClickCheckIn();

		///// calendar opens, calendar selection starts
		room.waitNextMonth();
		room.waitAndClickInDay(checkInDate);

		//// it moves automatically to checkout calendar selection
		//// We verify if the date 24 august is clickable
		softAssert.assertEquals(room.isOutDateClickable(String.valueOf(intCheckInDate - 1)), false);
		logReport(LogType.INFO, "Verification that the button is unclickable complete.");
		// verify color
		softAssert.assertEquals(room.isOutDateGreyed(String.valueOf(intCheckInDate - 1)), true);
		logReport(LogType.INFO, "Verification that the button greyed out is complete.");

		///// set check out to 31 aug
		room.waitAndClickOutDay(checkOutDate);
		// Thread.sleep(1000);
		room.waitRoomIncreaseAdults();
		room.waitRoomSearch();

		// we verify the timeframe is correct
		softAssert.assertEquals(room.isCorrectTimeFrameOneMonth(checkInDate, checkOutDate), true);
		logReport(LogType.INFO, "Verification of the timeframe is complete.");

		// click highest rate room
		room.waitRoomHighestRate();
		Thread.sleep(1500);
		room.waitRoomClickCheckOut();

		softAssert.assertEquals(room.isOutDateClickable(String.valueOf(intCheckInDate + 2)), false);
		logReport(LogType.INFO, "Verification that the outdate is unclickable on the desired room page is complete.");

		softAssert.assertEquals(room.isPriceRight(), true);
		logReport(LogType.INFO, "Verification of the total price is complete.");

		softAssert.assertAll();
	}
}
