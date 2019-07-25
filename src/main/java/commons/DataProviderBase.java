package commons;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

public class DataProviderBase {
	@DataProvider(name = "DateProvider")
	public static Object[][] getDatesProvider(Method met) {
		if(met.getName().equalsIgnoreCase("bookTestA")) {
		return new Object[][] { 
			{ "25", "31" }, 
			{ "12", "23" }, 
			{ "15", "26" } 
		};
	}
		else {
			return new Object[][] {
			{ "25", "24", "31", "27"},
			{ "14", "13", "23", "16"}
		};
	}
	}

}
