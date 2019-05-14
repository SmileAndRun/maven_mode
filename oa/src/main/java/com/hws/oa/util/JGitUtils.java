package com.hws.oa.util;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PullResult;
import org.eclipse.jgit.api.ResetCommand.ResetType;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.transport.FetchResult;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;

import com.hws.oa.core.LoadConf;
import com.hws.oa.exception.CommonException;

public class JGitUtils {

	private static final String GITFILENAME=".git";
   
    /**
     * 完全覆盖本地代码
     * @throws GitAPIException 
     * @throws CommonException 
     * @throws IOException 
     * @throws TransportException 
     * @throws InvalidRemoteException 
     */
    public static void update(Integer num) throws  CommonException, IOException, InvalidRemoteException, TransportException, GitAPIException{
    	//step0 检查是否已经初始化
    	Map<Integer,Map<String,String>> map = LoadConf.getSystemMap();
    	String remoteRepo = null;
    	String localRepo = null;
    	if(null != map){
			Map<String,String> sMap = map.get(num);
			remoteRepo = sMap.get("remote-repo");
			localRepo = sMap.get("local-repo");
		}
    	File repo = new File(localRepo);
    	if(repo.exists()&&repo.isDirectory()){
    		if(!Arrays.asList(repo.list()).contains(GITFILENAME)){
    			initGitRepo(remoteRepo,localRepo);
    		}else{
    			String localRepoGitConfig = localRepo + File.separator + GITFILENAME;
    			Git git = Git.open(new File(localRepoGitConfig));
    			//step1 git fetch --all 
    	    	//step2 git reset --hard origin/master
    	    	//step3 git pull
    			Repository repos = git.getRepository();
    			git.fetch().call();
    			Ref local = repos.findRef("refs/heads/master");
    			Ref origin = repos.findRef("refs/remotes/origin/master");
    			Iterable<RevCommit> list = git.log().addRange(local.getObjectId(), origin.getObjectId()).call();
    			for(RevCommit rc : list){
    				System.out.println(rc.getShortMessage());
    				System.out.println(Constants.typeString(rc.getType()));
    			}
    			
    			git.reset().setMode(ResetType.HARD).call();
    			git.pull().call();
    		}
    	}
    	
    }
    
    /**
     * 初始化git仓库
     * @param num
     * @throws GitAPIException
     * @throws CommonException 
     */
    public static void initGitRepo(String remoteRepo,String localRepo) throws GitAPIException, CommonException{
	    if(null == remoteRepo || null == localRepo)throw new CommonException("remoteRepo/localRepo canot be null[initGitRepo]");
    	Git.cloneRepository().setURI(remoteRepo).setDirectory(new File(localRepo)).call();
    }

}
