package org.common.utils;

import java.text.SimpleDateFormat;

public class TimeUtisl {
	private static String DEFULTTIMEPATTERN = "yyyy-mm-dd  HH:mm:ss";
	public static SimpleDateFormat get(String pattern){
		if(null == pattern) pattern = DEFULTTIMEPATTERN;
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf;
	}
}
