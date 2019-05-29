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
		for(String temp : list){
			boolean ifFinish = (temp == list.get(list.size()-1));
			String message = "{type:'package',isFinished:"+ifFinish+",value:'"+temp+"'}";
			session.getBasicRemote().sendText(message);
		}
		
		obj.put("flag", true);
		return obj;
	}

	private Session session;
	private List<String> list;
	public PackageTask(List<String> list,Session session){
		this.session = session;
		this.list = list;
	}
}
