package com.server.data.utils;

import com.server.data.core.database.DatabaseContextHolder;
import com.server.data.core.database.DatabaseType;


public class DataBaseSourceUtils {
	public static String getDataSourceType(){
		//刚开始进来是空值所以需要进行默认设置
		DatabaseType dataType = DatabaseContextHolder.getDatabaseType();
		if(null == dataType){
			DatabaseContextHolder.setDatabaseType(DatabaseType.quartz);
			dataType = DatabaseContextHolder.getDatabaseType();
		}
		return dataType.name();
	}
}
