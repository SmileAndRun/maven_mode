package org.common.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.common.model.server.MysqlModel;

import com.alibaba.fastjson.JSONObject;


public class JDBCUtils {
	private static Logger logger = Logger.getLogger(JDBCUtils.class.getClass());
	
	private static String DRIVER = "com.mysql.jdbc.Driver";
	
	static{
		try {
			Class.forName(DRIVER);//加载驱动
		} catch (ClassNotFoundException e) {
			logger.error("Mysql数据库驱动ClassNotFoundException", e);
		}
	}
	private static Connection conn;
	
	public static Connection getConnection(String url,String userName,String userPwd){
		try {
			conn = DriverManager.getConnection(url,userName,userPwd);
		} catch (SQLException e) {
			logger.error("Mysql数据库连接异常", e);
		}
		return conn;
	}
	private static PreparedStatement pre;
	private static ResultSet rs;
	public static MysqlModel excuteSql(String url,String userName,String userPwd,String sql){
		MysqlModel model = new MysqlModel();
		try {
			
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
	public static JSONObject getMysqlDataBase(String url,String userName,String userPwd) throws SQLException{

		rs = getConnection(url,userName,userPwd).getMetaData().getTables(null, null, null,new String[] { "TABLE" });
		 JSONObject obj = new JSONObject();
		 Set<String> tables = new HashSet<String>(); 
		 Set<String> databaseName = new HashSet<String>();
		 Map<String, List<String>> colums = new LinkedHashMap<String,List<String>>();
		 while (rs.next()) {
			 //表名
			 tables.add(rs.getString("TABLE_NAME"));
			 //表类型
			 //rs.getString("TABLE_TYPE");
			 //数据库名
			 databaseName.add(rs.getString("TABLE_CAT"));
			 //用户名
			 //rs.getString("TABLE_SCHEM");
			 //表备注
			 //rs.getString("REMARKS");
         }
		 obj.put("tables", tables);
		 obj.put("databaseName",databaseName);
		 //遍历表名获取表字段
		 for(String tableName:tables){
			 String sql = "select * from " + tableName;
			 pre=conn.prepareStatement(sql);
			 rs = pre.executeQuery();
			 ResultSetMetaData mData = rs.getMetaData();
			 //无字段名则进行下一次循环
			 if(mData.getColumnCount()<=0) continue;
			 List<String> list = new ArrayList<String>();
			 for(int i=1;i<mData.getColumnCount();i++){
				 
				 list.add(mData.getColumnName(i));
			 }
			 colums.put(tableName, list);
		 }
		 obj.put("colums", colums);
		 
		return obj;
	}
	public static JSONObject getTableStructureData(String url,String userName,String userPwd,String tableName) throws SQLException{
		JSONObject obj = new JSONObject();
		conn = getConnection(url,userName,userPwd);
		pre = conn.prepareStatement("select * from "+tableName);
		rs= pre.executeQuery();
		ResultSetMetaData mData = rs.getMetaData();
		if(mData.getColumnCount()<=0) return obj = null;
		List<List<String>> list = new ArrayList<List<String>>();
		List<String> rowList = null;
		for(int i=1;i<mData.getColumnCount();i++){
			 rowList = new ArrayList<String>();
			 rowList.add(mData.getColumnName(i)) ;
			 rowList.add(mData.getColumnTypeName(i));
			 rowList.add(mData.getColumnDisplaySize(i)+"");
			 rowList.add(mData.isNullable(i)+"");
			 list.add(rowList);
		 }
		pre = conn.prepareStatement("SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE WHERE TABLE_NAME <> 'dtproperties' "
				+ "and table_name ='"+tableName+"'");
		rs= pre.executeQuery();
		List<String> primaryKey = new ArrayList<String>();
		while(rs.next()){
			primaryKey.add(rs.getString(1));
		}
		if(primaryKey.size() == 0){
			primaryKey = null;
		}
		obj.put("primaryKey", primaryKey);
		obj.put("list", list);
		return obj;
	}
	/**
	 * 关闭数据库连接
	 * @return
	 */
	public static boolean close(){
		try {
			if(null != rs){
				rs.close();
				if(null != pre){
					pre.close();
					if(null != conn){
						conn.close();
					}
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
