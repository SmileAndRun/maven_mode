package com.hws.oa.core.threadpool;

import java.io.File;
import java.util.concurrent.Callable;

import com.alibaba.fastjson.JSONObject;
import com.hws.oa.model.LogModel;
import com.hws.oa.model.SystemModel;
import com.hws.oa.util.ReadResourceUtils;

public class UpdateSystemInfoTask implements ITask , Callable<JSONObject> {

	@Override
	public void addLog(LogModel logModel) {
		// TODO Auto-generated method stub

	}

	@Override
	public JSONObject call() throws Exception {
		File file = new File(System.getProperty("user.dir")+File.separator+"/conf");
		ReadResourceUtils.updateXML(file, systemModel);
		
		return null;
	}
	private SystemModel systemModel;
	public UpdateSystemInfoTask(SystemModel systemModel){
		this.systemModel = systemModel;
	}

}
