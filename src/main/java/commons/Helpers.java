package commons;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Helpers {
	
	public String azi() {
		LocalDateTime data_azi = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String format_azi = data_azi.format(format);
		String azi = format_azi.substring(0, 2);
		return azi;
	}
	
	public String readFromAFile(int line) throws IOException {
		  //Create File In D: Driver.  
		  String TestFile = "C:\\Users\\z0043dba\\eclipse-workspace\\intership\\internship2019\\test.txt";
		  File FC = new File(TestFile);//Created object of java File class.
		  FC.createNewFile();//Create file.
		  
//		  //Writing In to file.
//		  //Create Object of java FileWriter and BufferedWriter class.
//		  FileWriter FW = new FileWriter(TestFile);
//		  BufferedWriter BW = new BufferedWriter(FW);
//		  BW.write("This Is First Line."); //Writing In To File.
//		  BW.newLine();//To write next string on new line.
//		  BW.write("This Is Second Line."); //Writing In To File.
//		  BW.close();
		  
		  //Reading from file.
		  //Create Object of java FileReader and BufferedReader class.
		  FileReader FR = new FileReader(TestFile);
		  BufferedReader BR = new BufferedReader(FR);
		  String Content = "";
		  
		  //Loop to read all lines one by one from file and print It.
		  while(line>=0){
			  Content = BR.readLine();	
			  line--;
		  }
		  return Content;
		 }

}
