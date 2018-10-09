package com.bdcom.server.common;

import java.io.IOException;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bdcom.server.lucene.IndexUtils;
import com.bdcom.server.model.MysqlModel;
import com.bdcom.server.utils.JDBCUtils;

public class InitLucene {

	public static void main(String[]args){
		/*try {
			new IndexUtils().creatIndex();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		/*String url = "jdbc:mysql://localhost:3306/xlt?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8";
		String sql = "CREATE TABL `test` (`test`  int NULL ,PRIMARY KEY (`test`));";
		//String sql = "update t_barrage set content = '628' where contentId = 2;";
		//String sql = "select * from test;";
		//String sql = "drop table test;";
		JDBCUtils.excuteSql(url, sql);*/
			
		
	}
}
