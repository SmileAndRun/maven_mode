package com.server.feigncustomer.service.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Component;

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
 * @author hws
 *
 */
@Component
public class FeignServiceFallback implements FeignService {

	@Override
	public User getUserByUid(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserInfoByUid(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByUname(String uName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserInfoByUname(String uName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserSalt(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SessionModel findId(Integer sessioinId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SessionModel fingIdBySessionName(String sessionName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Log getLastMaxId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Images> getImages(Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WeChat> getAllChat() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WeChat> selectChatByTime(Timestamp startTime, Timestamp endTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Barrage> getAllBar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Barrage> getListBarByTime(Date time) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Barrage getBarById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Barrage> getListBarByLike(String content) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getBarrageCount(Timestamp time) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Barrage> getBarByImagesId(Integer imagesId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Barrage getMaxContentId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getSaltByUname(String uName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getFuzzyUsersByUid(String searchValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getFuzzyUserByUname(String searchValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Log getLogLastMaxId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> getAllRoleInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> getFuzzyRoleByUid(String searchValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> getFuzzyRoleByUname(String searchValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Permission getMaxPermissionId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role getMaxRoleId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<QrtzJobDetails> getJobDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<QrtzJobDetails> getJobDetailForJobName(String jobName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QrtzJobDetails getJobData(String jobName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<QuartzModel> getALlFromMyDefine() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QrtzJobData getMaxDataId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<QrtzJobData> getJobDataByJobName(String jobName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer registerUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer insert(SessionModel model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer insertLog(Log log) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer addImage(Images images) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer addChat(WeChat model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer addBarrage(Barrage barrage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer addPermission(Permission permission) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer addRole(Role role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer setPermanentStorage(String jobName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer insertSelfDifined(QuartzModel model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer insertJobData(QrtzJobData model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer changeUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(SessionModel model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateSelfDefined(QuartzModel model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteUserByUid(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteUserByUname(String uName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delImage(Integer imageId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delChatById(Integer w_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delBarrageBycontentId(Integer contentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteRole(Role role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteDataByJobName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteJobDetails(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deletePermission(Permission permission) {
		// TODO Auto-generated method stub
		return null;
	}

}
