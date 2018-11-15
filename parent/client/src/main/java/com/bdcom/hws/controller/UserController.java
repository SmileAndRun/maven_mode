package com.bdcom.hws.controller;


import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.common.model.Barrage;
import org.common.model.Log;
import org.common.model.client.SessionModel;
import org.common.model.client.User;
import org.common.utils.CookieUtils;
import org.common.utils.EncryptionUtils;
import org.common.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bdcom.hws.service.BarrageService;
import com.bdcom.hws.service.LogService;
import com.bdcom.hws.service.SessionService;
import com.bdcom.hws.service.UserService;

@Api(value="用户模块")
@Controller
@RequestMapping(value="/user")
public class UserController {
	@Autowired
	private UserService us;
	@Autowired
	private BarrageService barService;
	@Autowired
	private LogService logService;
	@Autowired
	private SessionService sessionService;

	@ApiOperation(value = "get User by uId", notes = "通过用户id获取该用户", response = User.class)
	@RequestMapping(value="getUserByUid",method=RequestMethod.GET)
	public User  getUserByUid(@ApiParam(value="用户id",required=true) int uId){
		User user = us.getUserByUid(uId);
		return user;
	}

	@RequestMapping(value="/validate",method=RequestMethod.POST)
	@ResponseBody
	public String login(HttpServletRequest request,HttpServletResponse response,boolean isRememberMe,boolean isCookie,User user){
		Subject subject = SecurityUtils.getSubject();
		if(isCookie){
			try{
				UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(),user.getUserPwd());
				subject.login(token);
			}catch(Exception e){
				return "false";
			}
		}else{
			byte[] salt = null;
			User saltUser = us.getUserSalt(user.getUserName());
			if(null == saltUser)return null;
			
			salt = saltUser.getuSalt();
			String pwd = null;
			try {
				pwd = EncryptionUtils.transformByteToString(EncryptionUtils.encryptHMAC(EncryptionUtils.combineByteArray(user.getUserPwd().getBytes(),salt), null));
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			//验证不通过shiro会自动跳转到设定好的链接中
			UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(),pwd);
			subject.login(token);
			if(isRememberMe){
				CookieUtils.setCookies(request,response, user.getUserName(), pwd);
			}
		}
		//更新sessionID
		Date date = new Date();
		User temp = us.getUserByUname(user.getUserName());
		SessionModel model = new SessionModel();
		model.setUserId(temp.getUserId());
		model.setSessionId(request.getSession().getId());
		model.setUpdateTime(new Timestamp(date.getTime()));
		if(sessionService.findId(temp.getUserId()) == null){
			sessionService.insert(model);
		}else{
			sessionService.update(model);
		}
		//subject.getSession().setTimeout(60*60*10);
		//插入log日志,暂定type=1为账号登录日志
		User userInfo = us.getUserByUname(user.getUserName());
		Log log = new Log();
		
		log.setUserid(userInfo.getUserId());
		log.setLogtype("login");
		log.setLogmessage("登录");
		log.setLogtime(new Timestamp(date.getTime()));
		log.setLogiserror("0");
		logService.insertLog(log);
		return "true";
	}
	
	/**
	 * 跳转到此路径表示已经认证成功
	 * @param req
	 * @param userName 用户名
	 * @param isRemenberMe 是否使用cookie
	 * @return 
	 */
	@RequestMapping(value="/index")
	public String initIndexPage(HttpServletRequest req){
		
		List<Barrage> barList = barService.getAllBar();
		req.setAttribute("barList", barList);
		
		
		
		return "index1";
	}
	@RequestMapping(value="/upload")
	@ResponseBody
	public String upload(@RequestParam MultipartFile[] file){
		try {
			if(null != file)UploadUtils.Upload(file);
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
			
		}
		return "true";
	}
	

}
