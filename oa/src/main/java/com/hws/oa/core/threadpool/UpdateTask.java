package com.hws.oa.core.threadpool;

import java.io.File;
import java.util.List;
import java.util.concurrent.Callable;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.diff.DiffEntry;

import com.alibaba.fastjson.JSONObject;
import com.hws.oa.model.LogModel;

public class UpdateTask implements ITask, Callable<JSONObject> {

	@Override
	public void addLog(LogModel logModel) {
		// TODO Auto-generated method stub

	}

	@Override
	public JSONObject call() throws Exception {
		JSONObject obj = new JSONObject();
		String rootPath = git.getRepository().getDirectory().getAbsolutePath();
		for(DiffEntry diff:list){
			boolean flag = true;
			while(flag){
				File file = new File(rootPath+File.separator+diff.getNewPath());
				if(file.lastModified()>time){
					
				}
			}
		}
		return obj;
	}

	private List<DiffEntry> list;
	private Git git;
	private long time;
	public UpdateTask(List<DiffEntry> list,Git git,long time){
		this.list = list;
		this.git = git;
		this.time = time;
	}
}
