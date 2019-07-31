package commons;

import java.io.BufferedReader;
//import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
// java.io.FileNotFoundException;
import java.io.FileReader;
//import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class Helpers {

	public String azi() {
		LocalDateTime data_azi = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String format_azi = data_azi.format(format);
		String azi = format_azi.substring(0, 2);
		return azi;
	}

	public String readFromAFile(int line) throws IOException {
		// Create File In D: Driver.
		String TestFile = System.getProperty("user.dir") + "\\" + "test.txt";
		// String TestFile = System.getProperty("user.dir");
		File FC = new File(TestFile);// Created object of java File class.
		FC.createNewFile();// Create file.

		FileReader FR = new FileReader(TestFile);
		BufferedReader BR = new BufferedReader(FR);
		String Content = "";

		// Loop to read all lines one by one from file and return the line
		while (line >= 0) {
			Content = BR.readLine();
			line--;
		}
		BR.close();
		FR.close();
		return Content;
	}

	public void addExcelToList(String filePath, String fileName, ArrayList<String> arrayList) throws IOException {
		double retValue = 0;
		File file = new File(filePath + "\\" + fileName);
		///// creating input stream to read excel file
		FileInputStream inputStream = new FileInputStream(file);
		Workbook bookerbook = null;
		bookerbook = new HSSFWorkbook(inputStream);
		//// sheet reading
		Sheet testSheet = bookerbook.getSheet("Tabelle1");
		// Find number of rows in excel file
		int rowCount = testSheet.getLastRowNum() - testSheet.getFirstRowNum();
		int i = 0, j = 0;
		for (i = 1; i <= rowCount; i++) {
			for (j = 0; j <= 1; j++) {
				retValue = testSheet.getRow(i).getCell(j).getNumericCellValue();
				arrayList.add(String.valueOf(retValue));
			}
		}

	}

	public Object addExcelToDataProvider(String filePath, String fileName) throws IOException {
		File file = new File(filePath + "\\" + fileName);
		FileInputStream inputStream = new FileInputStream(file);
		Workbook bookerbook = null;
		bookerbook = new HSSFWorkbook(inputStream);
		Sheet testSheet = bookerbook.getSheet("Tabelle1");
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
