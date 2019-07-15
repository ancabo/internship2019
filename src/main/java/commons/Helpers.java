package commons;

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

}
