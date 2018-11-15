package org.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class ReadPropertiesUtils {
	public static String getData(String path,String alias){
		Properties propertie = new Properties();
		try {
			BufferedReader read = new BufferedReader(new FileReader(new File(path)));
			propertie.load(read);
			
		} catch (IOException e) {
			return null;
		}
		return propertie.getProperty(alias);
	}

}
