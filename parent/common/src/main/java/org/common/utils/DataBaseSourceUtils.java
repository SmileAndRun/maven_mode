package org.common.utils;

import org.common.core.datasource.DatabaseContextHolder;
import org.common.core.datasource.DatabaseType;

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
