package com.hws.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hws.oa.core.LoadConf;
import com.hws.oa.core.threadpool.MyThreadPoolExecutor;
import com.hws.oa.core.threadpool.UpdateSystemInfoTask;
import com.hws.oa.model.SystemModel;
import com.hws.oa.service.SystemService;

@Service
public class SystemServiceImpl implements SystemService {

	@Override
	public List<SystemModel> searchUsersAndColoring(String content, String type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addSystemSet(SystemModel systemModel) {
		//先将配置加入到系统缓存中后续文件中通过线程异步更新
		boolean flag = LoadConf.getSystems().add(systemModel);
		new MyThreadPoolExecutor().getThreadPoolExecutor().submit(new UpdateSystemInfoTask(systemModel));
		return flag;
	}

	@Override
	public boolean deleSystemSet(Integer num) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateSystemSet(SystemModel systemModel) {
		// TODO Auto-generated method stub
		return false;
	}

}
