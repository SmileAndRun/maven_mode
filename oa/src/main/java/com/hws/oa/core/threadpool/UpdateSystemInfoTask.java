package com.hws.oa.core.threadpool;

import java.io.File;
import java.util.concurrent.Callable;

import com.alibaba.fastjson.JSONObject;
import com.hws.oa.core.LoadConf;
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
		File file = new File(LoadConf.USER_DIR+File.separator+LoadConf.CONF+File.separator+LoadConf.SYSTEM_CONFIG);
		ReadResourceUtils.updateXML(file, systemModel,type);
		
		return null;
	}
	private SystemModel systemModel;
	private Integer type;
	public UpdateSystemInfoTask(SystemModel systemModel,Integer type){
		this.systemModel = systemModel;
		this.type = type;
	}

}
