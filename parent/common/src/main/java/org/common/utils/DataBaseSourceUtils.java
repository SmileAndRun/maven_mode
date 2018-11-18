package org.common.utils;

import org.common.core.datasource.DatabaseContextHolder;
import org.common.core.datasource.DatabaseType;

public class DataBaseSourceUtils {
	public static void switchDataSource(){
		DatabaseType dataType = DatabaseContextHolder.getDatabaseType();
		if(null == dataType){
			DatabaseContextHolder.setDatabaseType(DatabaseType.quartz);
		}else{
			String name = dataType.name();
			System.out.println();
		}
		
	}
}
