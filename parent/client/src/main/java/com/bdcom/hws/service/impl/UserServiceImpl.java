package com.bdcom.hws.service.impl;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.common.core.annotation.TargetDataSource;
import org.common.core.datasource.DatabaseType;
import org.common.model.Log;
import org.common.model.client.SessionModel;
import org.common.model.client.User;
import org.common.utils.CookieUtils;
import org.common.utils.EncryptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	public JSONObject validateAccount(HttpServletRequest request,HttpServletResponse response,boolean isRememberMe, boolean isCookie, User user) throws InvalidKeyException, NoSuchAlgorithmException {
		JSONObject obj = new JSONObject();
		Subject subject = SecurityUtils.getSubject();
		String pwd = null;
		if(isCookie){
			UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(),user.getUserPwd());
			subject.login(token);
		}else{
			byte[] salt = null;
			User saltUser = getUserSalt(user.getUserName());
			if(null == saltUser)return null;
			salt = saltUser.getuSalt();
			pwd = EncryptionUtils.transformByteToString(EncryptionUtils.encryptHMAC(EncryptionUtils.combineByteArray(user.getUserPwd().getBytes(),salt), null));
		
			//验证不通过shiro会自动跳转到设定好的链接中
			UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(),pwd);
			subject.login(token);
		}
		if(isRememberMe){
			CookieUtils.setCookies(request,response, user.getUserName(), pwd);
		}
		//更新sessionID
		Date date = new Date();
		User temp = getUserByUname(user.getUserName());
		SessionModel model = new SessionModel();
		model.setUserId(temp.getUserId());
		model.setSessionId(request.getSession().getId());
		model.setUpdateTime(new Timestamp(date.getTime()));
		/*if(sessionService.findId(temp.getUserId()) == null){
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
