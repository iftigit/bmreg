package org.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility {
	
	public static boolean isValidDate(String datetooconvert, String format,String separator) 
	{
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		formatter.setLenient(false);
		Date testDate = null;
		try {
			testDate = formatter.parse(datetooconvert);
			String[] f = format.split(separator);
			String[] d = datetooconvert.split(separator);
			String k = "";
			for (int i = 0; i < f.length; i++) {
				if (f[i].equalsIgnoreCase("dd")) {
					if (d[i].length() == 1)
						d[i] = "0" + d[i];
				}
				if (f[i].equalsIgnoreCase("mm")) {
					if (d[i].length() == 1)
						d[i] = "0" + d[i];
				}

				k += d[i] ;
				if(i < (f.length-1))
					k+=separator;
			}

			if (!formatter.format(testDate).equals(k))
				return false;
		} catch (Exception e) {
			return false;
		}

		return true;
	}


}
