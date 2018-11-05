package org.common.model.server;

import java.util.List;

public class MysqlModel {

	/**响应时间*/
	private double time;
	/**影响条数(主要对于更新修改操作)*/
	private int affectRow;
	/**执行的sql语句*/
	private String sql;
	/**执行sql语句产生的结果*/
	private List<List<String>> content;
	/**每列标题*/
	private List<String> title;
	/**判断是否sql语句的类别*/
	private boolean flag;
	/**告警*/
	private String warning;
	/**sql语句错误*/
	private String error;
	
	
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getWarning() {
		return warning;
	}
	public void setWarning(String warning) {
		this.warning = warning;
	}
	public double getTime() {
		return time;
	}
	public void setTime(double time) {
		this.time = time;
	}
	public int getAffectRow() {
		return affectRow;
	}
	public void setAffectRow(int affectRow) {
		this.affectRow = affectRow;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public List<List<String>> getContent() {
		return content;
	}
	public void setContent(List<List<String>> content) {
		this.content = content;
	}
	public List<String> getTitle() {
		return title;
	}
	public void setTitle(List<String> title) {
		this.title = title;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public MysqlModel() {
		super();
	}
}
