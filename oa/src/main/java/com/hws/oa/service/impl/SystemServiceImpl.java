package com.hws.oa.service.impl;

import java.util.ArrayList;
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
	public List<SystemModel> searchUsersAndColoring(String content, Integer type) throws NumberFormatException {
		List<SystemModel> list = LoadConf.getSystems();
		List<SystemModel> findList = new ArrayList<SystemModel>();
		if(type == 1){
			//为空查询所有信息
			if(null == content || "".equals(content))return list;
			if(Integer.parseInt(content)>list.size())return null;
			SystemModel temp = list.get(Integer.parseInt(content)-1);
			temp.setNum(Integer.parseInt(content));
			findList.add(temp);
		}else if(type == 2){
			for(int i=0;i<list.size();i++){
				SystemModel temp = list.get(i);
				if(temp.getLocalRepo().indexOf(content)!= -1||temp.getRemoteRepo().indexOf(content)!= -1){
					temp.setNum(i+1);
					findList.add(temp);
				}
			}
		}
		return findList;
	}

	@Override
	public boolean addSystemSet(SystemModel systemModel) {
		//先将配置加入到系统缓存中后续文件中通过线程异步更新
		boolean flag = LoadConf.getSystems().add(systemModel);
		MyThreadPoolExecutor.myThreadPoolExecutor.getThreadPoolExecutor().submit(new UpdateSystemInfoTask(systemModel,1));
		return flag;
	}

	@Override
	public boolean deleSystemSet(Integer num) {
		// TODO Auto-generated method stub
		return false;
	}

	/**必须提供num
	 * 更新首先是更新缓存中的数据
	 * 其次使用线程池后续更新文件
	 * 
	 */
	@Override
	public boolean updateSystemSet(SystemModel systemModel) {
		if(systemModel.getNum()==0)return false;
		List<SystemModel> list = LoadConf.getSystems();
		if(systemModel.getNum()>list.size())return false;
		//更新缓存
		SystemModel cache = list.get(systemModel.getNum()-1);
		cache.setLocalRepo(systemModel.getLocalRepo());
		cache.setRemoteRepo(systemModel.getRemoteRepo());
		//线程更新文件 
		MyThreadPoolExecutor.myThreadPoolExecutor.getThreadPoolExecutor().submit(new UpdateSystemInfoTask(systemModel,2));
		return false;
	}

}
