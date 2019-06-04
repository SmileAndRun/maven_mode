package com.hws.oa.core.threadpool;

import java.util.concurrent.Callable;

import javax.websocket.Session;

import com.alibaba.fastjson.JSONObject;
import com.hws.oa.model.LogModel;
import com.hws.oa.util.RunTimeUtils;

public class PackageTask implements ITask, Callable<JSONObject> {
	@Override
	public void addLog(LogModel logModel) {
		// TODO Auto-generated method stub

	}

	@Override
	public JSONObject call() throws Exception {
		JSONObject obj = new JSONObject();
		RunTimeUtils.excute(pomPath, command,session);
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
