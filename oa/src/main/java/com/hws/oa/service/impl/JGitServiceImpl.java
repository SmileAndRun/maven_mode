package com.hws.oa.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ResetCommand.ResetType;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.hws.oa.core.LoadConf;
import com.hws.oa.core.threadpool.MyThreadPoolExecutor;
import com.hws.oa.core.threadpool.UpdateTask;
import com.hws.oa.core.websocket.WebSocketServer;
import com.hws.oa.exception.CommonException;
import com.hws.oa.service.JGitService;

@Service
public class JGitServiceImpl implements JGitService{

	private static final String GITFILENAME=".git";
	   
	@Autowired
	WebSocketServer webSocketServer;
	
    /**
     * 完全覆盖本地代码
     * @throws GitAPIException 
     * @throws CommonException 
     * @throws IOException 
     * @throws TransportException 
     * @throws InvalidRemoteException 
     */
	@Override
    public  JSONObject update(Integer num,HttpServletRequest request) throws GitAPIException, CommonException, IOException {
    	//step0 检查是否已经初始化
    	Map<Integer,Map<String,String>> map = LoadConf.getSystemMap();
    	String remoteRepo = null;
    	String localRepo = null;
    	JSONObject obj = new JSONObject();
    	obj.put("updateFlag", false);
    	if(null != map){
			Map<String,String> sMap = map.get(num);
			remoteRepo = sMap.get("remote-repo");
			localRepo = sMap.get("local-repo");
		}
    	File repo = new File(localRepo);
    	if(repo.exists()&&repo.isDirectory()){
    		if(!Arrays.asList(repo.list()).contains(GITFILENAME))initGitRepo(remoteRepo,localRepo);
		
			String localRepoGitConfig = localRepo + File.separator + GITFILENAME;
			Git git = Git.open(new File(localRepoGitConfig));
			Repository repository = git.getRepository();
			//step1 git fetch --all 
	    	//step2 git reset --hard origin/master
	    	//step3 git pull
			git.fetch().call();
			long time = System.currentTimeMillis();
			Ref local = repository.findRef("refs/heads/master");
			Ref origin = repository.findRef("refs/remotes/origin/master");
			ObjectReader reader = repository.newObjectReader();
			CanonicalTreeParser oldTree = new CanonicalTreeParser();
			CanonicalTreeParser newTree = new CanonicalTreeParser();
			oldTree.reset(reader,repository.resolve(local.getObjectId().name()+"^{tree}"));
			newTree.reset(reader, repository.resolve(origin.getObjectId().name()+"^{tree}"));
			List<DiffEntry> list = git.diff().setOldTree(oldTree).setNewTree(newTree).call();
			if(null == list || list.size() == 0) return obj;
			
			obj.put("updateFlag", true);
			ThreadPoolExecutor executor = new MyThreadPoolExecutor().getThreadPoolExecutor();
			
			executor.submit(new UpdateTask(list,git,time,WebSocketServer.getMapCache().get(request.getSession().getId())));
			git.reset().setMode(ResetType.HARD).call();
			git.pull().call();
			
    	}
    	return obj;
    }
    
    /**
     * 初始化git仓库
     * @param num
     * @throws GitAPIException
     * @throws CommonException 
     */
    public  void initGitRepo(String remoteRepo,String localRepo) throws GitAPIException, CommonException{
	    if(null == remoteRepo || null == localRepo)throw new CommonException("remoteRepo/localRepo canot be null[initGitRepo]");
    	Git.cloneRepository().setURI(remoteRepo).setDirectory(new File(localRepo)).call();
    }

}
