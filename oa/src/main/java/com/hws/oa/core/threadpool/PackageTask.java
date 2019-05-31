package com.hws.oa.core.threadpool;

import java.util.concurrent.Callable;

import javax.websocket.Session;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.hws.oa.model.LogModel;
import com.hws.oa.util.RunTimeUtils;

public class PackageTask implements ITask, Callable<JSONObject> {
	private static Logger logger = Logger.getLogger(PackageTask.class);
	@Override
	public void addLog(LogModel logModel) {
		// TODO Auto-generated method stub

	}

	@Override
	public JSONObject call() throws Exception {
		JSONObject obj = new JSONObject();
		logger.info("开始打包");
		RunTimeUtils.excute(pomPath, command,session);
		logger.info("打包完成");
		return obj;
	}

	private Session session;
	private String pomPath;
	private String command;
	public PackageTask(Session session,String pomPath,String command){
		this.session = session;
		this.pomPath = pomPath;
		this.command = command;
	}
}
