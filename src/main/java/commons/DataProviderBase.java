package commons;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.testng.annotations.DataProvider;

public class DataProviderBase {
	@DataProvider(name = "DateProviderA")
	public static Object[][] getDatesProvider(Method met) {
		return new Object[][] { { "25", "31" }, { "12", "23" }, { "15", "26" } };
	}

	@DataProvider(name = "DateProviderS")
	public static Object[][] getDatesProvider() {
		return new Object[][] { { "25", "24", "31", "27" }, { "14", "13", "23", "16" } };
	}
	
	@DataProvider(name = "dangerousData")
	public static Object[][] getDates() throws IOException {
		File file = new File("C:\\Users\\z0043dba\\eclipse-workspace\\intership\\internship2019\\Data_PMI.xls");
		FileInputStream inputStream = new FileInputStream(file);
		Workbook bookerbook = null;
		bookerbook = new HSSFWorkbook(inputStream);
		Sheet testSheet = bookerbook.getSheet("Sheet1");
		int lastRowNum = testSheet.getLastRowNum();
		int lastCellNum = testSheet.getRow(0).getLastCellNum();
		Object[][] obj = new Object[lastRowNum][1];

		for (int i = 0; i < lastRowNum; i++) {
			Map<Object, Object> datamap = new HashMap<>();
			for (int j = 0; j < lastCellNum; j++) {
				datamap.put(testSheet.getRow(0).getCell(j).toString(), testSheet.getRow(i + 1).getCell(j).toString());
			}
			obj[i][0] = datamap;

		}
		return obj;
	}
}  

