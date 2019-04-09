package com.server.data.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.server.data.core.database.DatabaseType;
import com.server.data.core.database.TargetDataSource;
import com.server.data.mapper.BarrageMapper;
import com.server.data.mapper.ImagesMapper;
import com.server.data.mapper.LogMapper;
import com.server.data.mapper.ManagerMapper;
import com.server.data.mapper.PermissionMapper;
import com.server.data.mapper.QuartzMapper;
import com.server.data.mapper.RoleMapper;
import com.server.data.mapper.SessionMapper;
import com.server.data.mapper.UserMapper;
import com.server.data.mapper.WeChatMapper;
import com.server.data.server.DataService;
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

@RestController
public class DataController implements DataService {

	@Autowired
	UserMapper userMapper;
	@Autowired
	LogMapper logMapper;
	@Autowired
	ImagesMapper imagesMapper;
	@Autowired
	PermissionMapper permissionMapper;
	@Autowired
	RoleMapper roleMapper;
	@Autowired
	SessionMapper sessionMapper;
	@Autowired
	WeChatMapper wechatMapper;
	@Autowired
	BarrageMapper barrageMapper;
	@Autowired
	ManagerMapper managerMapper;
	@Autowired
	QuartzMapper quartzMapper;
	
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public User getUserByUid(Integer userId) {
		return managerMapper.getUserByUid(userId);
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public User getUserInfoByUid(Integer userId) {
		return managerMapper.getUserInfoByUid(userId);
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public List<User> getUsers() {
		return managerMapper.getUsers();
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public User getUserByUname(String uName) {
		return managerMapper.getUserByUname(uName);
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public User getUserInfoByUname(String uName) {
		return managerMapper.getUserInfoByUname(uName);
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public List<User> getUser(User user) {
		return managerMapper.getUser(user);
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public User getUserSalt(String userName) {
		return userMapper.getSaltByUname(userName);
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public SessionModel findId(Integer sessioinId) {
		return sessionMapper.findId(sessioinId);
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public SessionModel fingIdBySessionName(String sessionName) {
		return sessionMapper.fingIdBySessionName(sessionName);
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public Log getLastMaxId() {
		return logMapper.getLastMaxId();
	}

	
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public List<Barrage> getAllBar() {
		return barrageMapper.getAllBar();
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public List<Barrage> getListBarByTime(Date time) {
		return barrageMapper.getListBarByTime(time);
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public Barrage getBarById(String id) {
		return barrageMapper.getBarById(id);
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public List<Barrage> getListBarByLike(String content) {
		return barrageMapper.getListBarByLike(content);
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public Integer getBarrageCount(Timestamp time) {
		return barrageMapper.getBarrageCount(time);
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public List<Barrage> getBarByImagesId(Integer imagesId) {
		return barrageMapper.getBarByImagesId(imagesId);
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public Barrage getMaxContentId() {
		return barrageMapper.getMaxContentId();
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public User getSaltByUname(String uName) {
		return managerMapper.getSaltByUname(uName);
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public List<User> getFuzzyUsersByUid(String searchValue) {
		return managerMapper.getFuzzyUsersByUid(searchValue);
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public List<User> getFuzzyUserByUname(String searchValue) {
		return managerMapper.getFuzzyUserByUname(searchValue);
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public Log getLogLastMaxId() {
		return logMapper.getLastMaxId();
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public List<Role> getAllRoleInfo() {
		return managerMapper.getAllRoleInfo();
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public List<Role> getFuzzyRoleByUid(String searchValue) {
		return managerMapper.getFuzzyRoleByUid(searchValue);
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public List<Role> getFuzzyRoleByUname(String searchValue) {
		return managerMapper.getFuzzyRoleByUname(searchValue);
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public Integer deletePermission(Permission permission) {
		return managerMapper.deletePermission(permission);
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public Permission getMaxPermissionId() {
		return managerMapper.getMaxPermissionId();
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public Role getMaxRoleId() {
		return managerMapper.getMaxRoleId();
	}

	@TargetDataSource(dataBaseType = DatabaseType.quartz)
	@Override
	public List<QrtzJobDetails> getJobDetails() {
		return quartzMapper.getJobDetails();
	}

	@TargetDataSource(dataBaseType = DatabaseType.quartz)
	@Override
	public List<QrtzJobDetails> getJobDetailForJobName(String jobName) {
		return quartzMapper.getJobDetailForJobName(jobName);
	}

	@TargetDataSource(dataBaseType = DatabaseType.quartz)
	@Override
	public QrtzJobDetails getJobData(String jobName) {
		return quartzMapper.getJobData(jobName);
	}

	@TargetDataSource(dataBaseType = DatabaseType.quartz)
	@Override
	public List<QuartzModel> getALlFromMyDefine() {
		return quartzMapper.getALlFromMyDefine();
	}

	@TargetDataSource(dataBaseType = DatabaseType.quartz)
	@Override
	public QrtzJobData getMaxDataId() {
		return quartzMapper.getMaxDataId();
	}

	@TargetDataSource(dataBaseType = DatabaseType.quartz)
	@Override
	public List<QrtzJobData> getJobDataByJobName(String jobName) {
		return quartzMapper.getJobDataByJobName(jobName);
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public List<Images> getImages(Integer pageNum, Integer pageSize) {
		return imagesMapper.getAllImages();
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public List<WeChat> getAllChat() {
		return wechatMapper.getAllChat();
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public List<WeChat> selectChatByTime(Timestamp startTime, Timestamp endTime) {
		return wechatMapper.selectChatByTime(startTime, endTime);
	}
	
	
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public Integer registerUser(User user) {
		return managerMapper.registerUser(user);
	}
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public Integer insert(SessionModel model) {
		return sessionMapper.insert(model);
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public Integer insertLog(Log log) {
		return managerMapper.insertLog(log);
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public Integer addImage(Images images) {
		return imagesMapper.addImage(images);
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public Integer addChat(WeChat model) {
		return wechatMapper.addChat(model);
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public Integer addBarrage(Barrage barrage) {
		return barrageMapper.addBarrage(barrage);
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public Integer addPermission(Permission permission) {
		return managerMapper.addPermission(permission);
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public Integer addRole(Role role) {
		return managerMapper.addRole(role);
	}

	@TargetDataSource(dataBaseType = DatabaseType.quartz)
	@Override
	public Integer setPermanentStorage(String jobName) {
		return quartzMapper.setPermanentStorage(jobName);
	}

	@TargetDataSource(dataBaseType = DatabaseType.quartz)
	@Override
	public Integer insertSelfDifined(QuartzModel model) {
		return quartzMapper.insertSelfDifined(model);
	}

	@TargetDataSource(dataBaseType = DatabaseType.quartz)
	@Override
	public Integer insertJobData(QrtzJobData model) {
		return quartzMapper.insertJobData(model);
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public Integer changeUser(User user) {
		return managerMapper.changeUser(user);
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public Integer update(SessionModel model) {
		return sessionMapper.update(model);
	}

	@TargetDataSource(dataBaseType = DatabaseType.quartz)
	@Override
	public Integer updateSelfDefined(QuartzModel model) {
		return quartzMapper.updateSelfDefined(model);
	}
	
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public Integer deleteUserByUid(Integer userId) {
		return managerMapper.deleteUserByUid(userId);
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public Integer deleteUserByUname(String uName) {
		return managerMapper.deleteUserByUname(uName);
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public Integer delImage(Integer imageId) {
		return imagesMapper.delImage(imageId);
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public Integer delChatById(Integer w_id) {
		return wechatMapper.delChatById(w_id);
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public Integer delBarrageBycontentId(Integer contentId) {
		return barrageMapper.delBarrageBycontentId(contentId);
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public Integer deleteRole(Role role) {
		return managerMapper.deleteRole(role);
	}

	@TargetDataSource(dataBaseType = DatabaseType.quartz)
	@Override
	public Integer deleteDataByJobName(String name) {
		return quartzMapper.deleteDataByJobName(name);
	}

	@TargetDataSource(dataBaseType = DatabaseType.quartz)
	@Override
	public Integer deleteJobDetails(String name) {
		return quartzMapper.deleteJobDetails(name);
	}

}
