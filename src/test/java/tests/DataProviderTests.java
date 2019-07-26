package tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import commons.DataProviderBase;
import commons.DriverHelpers;
import commons.Helpers;
import commons.Constants;
import commons.TestBase;
import pages.Footer;
import pages.Header;
import pages.Home;
import pages.Rooms;


public class DataProviderTests extends TestBase {

	Home home;
	Rooms room;
	Header header;
	Helpers helper;
	Footer footer;
	DriverHelpers driverHelper;

	@BeforeMethod
	public void elements() {
		header = new Header(driver);
		home = new Home(driver);
		room = new Rooms(driver);
		helper = new Helpers();
		driverHelper = new DriverHelpers();
		footer = new Footer(driver);
	}

	@Test(dataProvider = "DateProviderA", dataProviderClass = DataProviderBase.class)
	public void bookTestA(String checkInDate, String checkOutDate) throws InterruptedException, IOException {

		SoftAssert softAssert = new SoftAssert();
		logReport(Constants.INFO, "Room booking test started");
		int intCheckInDate = Double.valueOf(checkInDate).intValue();
		// int intCheckOutDate = Double.valueOf(checkOutDate).intValue();

		header.waitAndClickRooms();
		// Thread.sleep(500);
		room.waitBodyFrame();
		// Thread.sleep(1000);
		room.waitRoomClickCheckIn();
		if (driverHelper.isElementDisplayed(driver.findElement(
				By.xpath("//*[@id=\"hotel-container\"]/section/div[1]/div/form/ul/li[1]/div[2]"))) == false)
			room.waitRoomClickCheckIn();

		///// calendar opens, calendar selection starts
		room.waitNextMonth();
		room.waitAndClickInDay(checkInDate);

		//// it moves automatically to checkout calendar selection
		//// We verify if the date 24 august is clickable
		AssertJUnit.assertEquals(room.isOutDateClickable(String.valueOf(intCheckInDate - 1)), false);
		logReport(Constants.INFO, "Verification that the button is unclickable complete.");
		// verify color
		AssertJUnit.assertEquals(room.isOutDateGreyed(String.valueOf(intCheckInDate - 1)), true);
		logReport(Constants.INFO, "Verification that the button greyed out is complete.");

		///// set check out to 31 aug
		room.waitAndClickOutDay(checkOutDate);
		// Thread.sleep(1000);
		room.waitRoomIncreaseAdults();
		room.waitRoomSearch();

		// we verify the timeframe is correct
		AssertJUnit.assertEquals(room.isCorrectTimeFrameOneMonth(checkInDate, checkOutDate), true);
		logReport(Constants.INFO, "Verification of the timeframe is complete.");

		// click highest rate room
		room.waitRoomHighestRate();
		Thread.sleep(1500);
		room.waitRoomClickCheckOut();

		AssertJUnit.assertEquals(room.isOutDateClickable(String.valueOf(intCheckInDate + 2)), false);
		logReport(Constants.INFO, "Verification that the outdate is unclickable on the desired room page is complete.");

		AssertJUnit.assertEquals(room.isPriceRight(), true);
		logReport(Constants.INFO, "Verification of the total price is complete.");

		softAssert.assertAll();
	}
}
