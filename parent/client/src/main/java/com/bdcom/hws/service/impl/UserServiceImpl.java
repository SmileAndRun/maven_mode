package com.bdcom.hws.service.impl;

import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





















import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.ExpiredSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.StoppedSessionException;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.common.core.annotation.TargetDataSource;
import org.common.core.datasource.DatabaseType;
import org.common.model.Log;
import org.common.model.OnlineCountModel;
import org.common.model.client.User;
import org.common.utils.CookieUtils;
import org.common.utils.EncryptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.bdcom.hws.mapper.LogMapper;
import com.bdcom.hws.mapper.UserMapper;
import com.bdcom.hws.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;
	
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public User getUserByUid(int uId) {
		
		return userMapper.getUserByUid(uId);
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public List<User> getUsers() {
		return userMapper.getUsers();
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public User getUserByUname(String uName) {
		return userMapper.getUserByUname(uName);
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public List<User> getUser(User user) {
		return null;
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public boolean registerUser(User user) {
		//默认是普通用户
		user.setuIsManage('0');
		if(userMapper.registerUser(user)==0)return false;
		return true;
		
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public boolean changeUser(User user) {
		if(userMapper.changeUser(user)==0)return false;
		return true;
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public boolean deleteUserByUid(int uId) {
		if(userMapper.deleteUserByUid(uId)==0)return false;
		return true;
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public boolean deleteUserByUname(String uName) {
		if(userMapper.deleteUserByUname(uName)==0)return false;
		return true;
	}


	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public User getUserInfoByUid(int userId) {
		return userMapper.getUserInfoByUid(userId);
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public User getUserInfoByUname(String uName) {
		return userMapper.getUserInfoByUname(uName);
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public User getUserSalt(String userName) {
		return userMapper.getSaltByUname(userName);
	}
	@Autowired
	LogMapper logMapper;
	
	
	@Autowired
	@Qualifier("sessionManager")
	DefaultWebSessionManager sessionManager;
	
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public JSONObject validateAccount(HttpServletRequest request,HttpServletResponse response,boolean isRememberMe, boolean isCookie, User user) throws InvalidKeyException, NoSuchAlgorithmException,ExpiredSessionException,StoppedSessionException {
		JSONObject obj = new JSONObject();
		Subject subject = SecurityUtils.getSubject();
		String pwd = null;
		if(isCookie){
			UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(),user.getUserPwd());
			subject.login(token);
		}else{
			byte[] salt = null;
			User saltUser = getUserSalt(user.getUserName());
			//账号都不存在，验证一定失败
			if(null == saltUser) throw new AuthenticationException();
			salt = saltUser.getuSalt();
			pwd = EncryptionUtils.transformByteToString(EncryptionUtils.encryptHMAC(EncryptionUtils.combineByteArray(user.getUserPwd().getBytes(),salt), null));
		
			//验证不通过shiro会自动跳转到设定好的链接中
			UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(),pwd);
			subject.login(token);
		}
		Session session = subject.getSession();
		if(subject.isAuthenticated()){
			
			synchronized(session){
	       	 OnlineCountModel.activeSessions++;
			}
			//获取当前用户登录名
	    	String userName = user.getUserName();
	    	Map<String, Session> beforeCache = OnlineCountModel.getBeforeCache();
	    	Map<String, Session> afterCache = OnlineCountModel.getAfterCache();
	    	Map<Serializable, String> idCache = OnlineCountModel.getIdCache();
	    	if(afterCache.containsKey(userName)){
	    		beforeCache.put(userName,afterCache.get(userName));
	    		idCache.put(afterCache.get(userName).getId(), userName);
	    	}
	    	afterCache.put(userName,session);
	    	idCache.put(session.getId(), userName);
			if(beforeCache.containsKey(userName)){
				//让上一个用户的session失效
				if(sessionManager.isValid(new DefaultSessionKey(beforeCache.get(userName).getId()))){
					synchronized(session){
				       	 OnlineCountModel.activeSessions--;
					}
					beforeCache.get(userName).setTimeout(0);
				}else{
					idCache.remove(beforeCache.get(userName).getId(), userName);
					beforeCache.remove(userName);
					synchronized(session){
				       	 OnlineCountModel.activeSessions--;
					}
				}
			}
		}
		if(isRememberMe){
			CookieUtils.setCookies(request,response, user.getUserName(), pwd);
		}
		//更新sessionID
		Date date = new Date();
		User temp = getUserByUname(user.getUserName());
		obj.put("isManage",false);
		if(temp.getuIsManage()=='1')obj.put("isManage",true);
		
		//一下注释待完善
		/*SessionModel model = new SessionModel();
		model.setUserId(temp.getUserId());
		model.setSessionId(request.getSession().getId());
		model.setUpdateTime(new Timestamp(date.getTime()));
		if(sessionService.findId(temp.getUserId()) == null){
			sessionService.insert(model);
		}else{
			sessionService.update(model);
		}*/
		//插入log日志,暂定type=1为账号登录日志
		User userInfo = getUserByUname(user.getUserName());
		Log log = logMapper.getLastMaxId();
		if(null == log){
			log = new Log();
			log.setLogid(1);
		}else{
			log.setLogid(log.getLogid() + 1);
		}
		log.setUserid(userInfo.getUserId());
		log.setLogtype("1");
		log.setLogmessage("login");
		log.setLogtime(new Timestamp(date.getTime()));
		log.setLogiserror("0");
		logMapper.insert(log);
		obj.put("flag", true);
		return obj;
	}

}
