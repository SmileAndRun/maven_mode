package com.hws.oa.core.threadpool;

import java.util.List;
import java.util.concurrent.Callable;

import javax.websocket.Session;


import com.alibaba.fastjson.JSONObject;
import com.hws.oa.model.LogModel;

public class PackageTask implements ITask, Callable<JSONObject> {

	@Override
	public void addLog(LogModel logModel) {
		// TODO Auto-generated method stub

	}

	@Override
	public JSONObject call() throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("flag", false);
		int num=0;
		for(String temp : list){
			num++;
			boolean ifFinish = (num == list.size());
			String message = "{type:'package',isFinished:"+ifFinish+",value:'"+temp+"',flag:'"+flag+"'}";
			session.getBasicRemote().sendText(message);
		}
		
		obj.put("flag", true);
		return obj;
	}

	private Session session;
	private List<String> list;
	private boolean flag;
	public PackageTask(List<String> list,Session session,boolean flag){
		this.session = session;
		this.list = list;
		this.flag = flag;
	}
}
