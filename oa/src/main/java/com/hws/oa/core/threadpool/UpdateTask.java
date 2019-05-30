package com.hws.oa.core.threadpool;

import java.io.File;
import java.util.List;
import java.util.concurrent.Callable;

import javax.websocket.Session;

import org.apache.log4j.Logger;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.diff.DiffEntry;

import com.alibaba.fastjson.JSONObject;
import com.hws.oa.model.LogModel;

public class UpdateTask implements ITask, Callable<JSONObject> {
	private static Logger logger = Logger.getLogger(UpdateTask.class);
	@Override
	public void addLog(LogModel logModel) {
		// TODO Auto-generated method stub

	}

	@Override
	public JSONObject call() throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("updateFlag", false);
		String rootPath = git.getRepository().getDirectory().getAbsolutePath().split("\\.")[0];
		int i=0;
		for(DiffEntry diff:list){
			i++;
			boolean isFinish = (i==list.size());
			logger.info("代码更新状态："+isFinish);
			boolean flag = true;
			while(flag){
				File file = new File(rootPath+File.separator+diff.getNewPath());
				if(file.lastModified()>time){
					String message = "{type:'update',isFinish:"+isFinish+",value:'"+diff.getChangeType() +"      "+diff.getNewPath()+"'}";
					session.getBasicRemote().sendText(message);
					break;
				}
			}
		}
		obj.put("updateFlag", true);
		return obj;
	}

	private List<DiffEntry> list;
	private Git git;
	private long time;
	private Session session;
	public UpdateTask(List<DiffEntry> list,Git git,long time,Session session){
		this.list = list;
		this.git = git;
		this.time = time;
		this.session = session;
	}
}
