package com.bdcom.server.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.bdcom.server.model.MysqlModel;


public class JDBCUtils {
	private static Logger logger = Logger.getLogger(JDBCUtils.class.getClass());
	
	private static String DRIVER = "com.mysql.jdbc.Driver";
	private static String USERNAME = "root";
	private static String USERPWD = "root";
	
	static{
		try {
			Class.forName(DRIVER);//加载驱动
		} catch (ClassNotFoundException e) {
			logger.error("Mysql数据库驱动ClassNotFoundException", e);
		}
	}
	private static Connection conn;
	
	public static Connection getConnection(String url){
		try {
			conn = DriverManager.getConnection(url,USERNAME,USERPWD);
		} catch (SQLException e) {
			logger.error("Mysql数据库连接异常", e);
		}
		return conn;
	}
	private static PreparedStatement pre;
	private static ResultSet rs;
	public static MysqlModel excuteSql(String url,String sql){
		MysqlModel model = new MysqlModel();
		try {
			pre = getConnection(url).prepareStatement(sql);
			boolean flag = pre.execute();
			int count = pre.getUpdateCount();
			model.setFlag(flag);
			model.setSql(sql);
			if(count == -1){
				model.setAffectRow(0);
			}else{
				model.setAffectRow(count);
			}
			
			SQLWarning warning = pre.getWarnings();
			if(null != warning) model.setWarning(warning.getMessage());
			
			if(flag){
				rs = pre.executeQuery();
				ResultSetMetaData data = pre.getMetaData();
				//从1开始,获取列名
				int columnCount = data.getColumnCount();
				List<String> title = new ArrayList<String>();
				for(int i=1;i<=columnCount;i++){
					title.add(data.getColumnName(i));
				}
				model.setTitle(title);
				List<List<String>> content = new ArrayList<List<String>>();
				while(rs.next()){
					List<String> rowList = new ArrayList<String>();
					for(int i=1;i<=columnCount;i++){
						rowList.add(rs.getString(i));
					}
					content.add(rowList);
				}
				model.setContent(content);
			}
			
		} catch (SQLException e) {
			model.setError(e.getMessage());
			logger.error("sql语句异常", e);
		}
		return model;
	}

}
