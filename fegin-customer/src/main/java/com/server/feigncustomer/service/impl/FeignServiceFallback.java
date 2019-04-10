package com.server.feigncustomer.service.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.server.feigncustomer.controller.FeignController;
import com.server.feigncustomer.service.FeignService;
import com.server.restful.api.pojo.Barrage;
import com.server.restful.api.pojo.Log;
import com.server.restful.api.pojo.Permission;
import com.server.restful.api.pojo.QrtzJobData;
import com.server.restful.api.pojo.QrtzJobDetails;
import com.server.restful.api.pojo.QuartzModel;
import com.server.restful.api.pojo.Role;
import com.server.restful.api.pojo.client.Images;
import com.server.restful.api.pojo.client.SessionModel;
import com.server.restful.api.pojo.client.WeChat;
import com.server.restful.api.pojo.server.User;


/**
 * 服务降级措施
 * 占时：全部返回空
 * @author hws
 * 因为实现了FeignService，FeignService又继承了DataService
 * DataService中有路径因此此时FeignService中的方法中也有路径。
 * 当构建FeignServiceFallBack Bean的时候会因为路径相同而无法构建因此需要再加一层路径
 */
@Component
@RequestMapping("/fallback")
public class FeignServiceFallback implements FeignService {

	private static Logger log= LoggerFactory.getLogger(FeignController.class);
	
	public User getUserByUid(Integer userId) {
		log.error("getUserByUid method is fused");
		return null;
	}

	public User getUserInfoByUid(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public User getUserByUname(String uName) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public User getUserInfoByUname(String uName) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<User> getUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public User getUserSalt(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public SessionModel findId(Integer sessioinId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public SessionModel fingIdBySessionName(String sessionName) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Log getLastMaxId() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<Images> getImages(Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<WeChat> getAllChat() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<WeChat> selectChatByTime(Timestamp startTime, Timestamp endTime) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<Barrage> getAllBar() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<Barrage> getListBarByTime(Date time) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Barrage getBarById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<Barrage> getListBarByLike(String content) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Integer getBarrageCount(Timestamp time) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<Barrage> getBarByImagesId(Integer imagesId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Barrage getMaxContentId() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public User getSaltByUname(String uName) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<User> getFuzzyUsersByUid(String searchValue) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<User> getFuzzyUserByUname(String searchValue) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Log getLogLastMaxId() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<Role> getAllRoleInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<Role> getFuzzyRoleByUid(String searchValue) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<Role> getFuzzyRoleByUname(String searchValue) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Permission getMaxPermissionId() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Role getMaxRoleId() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<QrtzJobDetails> getJobDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<QrtzJobDetails> getJobDetailForJobName(String jobName) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public QrtzJobDetails getJobData(String jobName) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<QuartzModel> getALlFromMyDefine() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public QrtzJobData getMaxDataId() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<QrtzJobData> getJobDataByJobName(String jobName) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Integer registerUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Integer insert(SessionModel model) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Integer insertLog(Log log) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Integer addImage(Images images) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Integer addChat(WeChat model) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Integer addBarrage(Barrage barrage) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Integer addPermission(Permission permission) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Integer addRole(Role role) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Integer setPermanentStorage(String jobName) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Integer insertSelfDifined(QuartzModel model) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Integer insertJobData(QrtzJobData model) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Integer changeUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Integer update(SessionModel model) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Integer updateSelfDefined(QuartzModel model) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Integer deleteUserByUid(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Integer deleteUserByUname(String uName) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Integer delImage(Integer imageId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Integer delChatById(Integer w_id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Integer delBarrageBycontentId(Integer contentId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Integer deleteRole(Role role) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Integer deleteDataByJobName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Integer deleteJobDetails(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Integer deletePermission(Permission permission) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public FeignService create(Throwable arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
