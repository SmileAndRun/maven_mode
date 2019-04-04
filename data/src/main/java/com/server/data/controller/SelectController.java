package com.server.data.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;


















import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/database")
public class SelectController {
	@Autowired
	DataService dataService;
	
	
	@RequestMapping("/getUserByUid")
	public User getUserByUid(@RequestParam Integer userId){
		return dataService.getUserByUid(userId);
	}
	public User getUserInfoByUid(@RequestParam Integer userId){
		return dataService.getUserInfoByUid(userId);
		
	}
	public List<User> getUsers(){
		return dataService.getUsers();
		
	}
	public User getUserByUname(String uName){
		return dataService.getUserByUname(uName);}
	public User getUserInfoByUname(String uName){
		return dataService.getUserInfoByUname(uName);}
	public List<User> getUser(User user){
		return dataService.getUser(user);}
	public User getUserSalt(String userName){
		return dataService.getUserSalt(userName);}
	public SessionModel findId(Integer sessioinId){
		return dataService.findId(sessioinId);}
	public SessionModel fingIdBySessionName(String sessionName){
		return dataService.fingIdBySessionName(sessionName);}
	public Log getLastMaxId(){
		return dataService.getLastMaxId();}
	public List<Images> getImages(Integer pageNum, Integer pageSize){
		return dataService.getImages(pageNum, pageSize);}
	public List<WeChat> getAllChat(){
		return dataService.getAllChat();}
	public List<WeChat> selectChatByTime(@Param("startTime")Timestamp startTime,@Param("endTime")Timestamp endTime){
		return dataService.selectChatByTime(startTime, endTime);}
	
	
	public List<Barrage> getAllBar(){
		return dataService.getAllBar();}
	public List<Barrage> getListBarByTime(Date time){
		return dataService.getListBarByTime(time);}
	public Barrage getBarById(String id){
		return dataService.getBarById(id);}
	public List<Barrage> getListBarByLike(String content){
		return dataService.getListBarByLike(content);}
	public Integer getBarrageCount(Timestamp time){
		return dataService.getBarrageCount(time);}
	public List<Barrage> getBarByImagesId(Integer imagesId){
		return dataService.getBarByImagesId(imagesId);}
	public Barrage getMaxContentId(){
		return dataService.getMaxContentId();}
	
	public User getSaltByUname(String uName){
		return dataService.getSaltByUname(uName);}
	public List<User> getFuzzyUsersByUid(String searchValue){
		return dataService.getFuzzyUsersByUid(searchValue);}
	public List<User> getFuzzyUserByUname(String searchValue){
		return dataService.getFuzzyUserByUname(searchValue);}
	public Log getLogLastMaxId(){
		return dataService.getLogLastMaxId();}
	public List<Role> getAllRoleInfo(){
		return dataService.getAllRoleInfo();}
	public List<Role> getFuzzyRoleByUid(String searchValue){
		return dataService.getFuzzyRoleByUid(searchValue);}
	public List<Role> getFuzzyRoleByUname(String searchValue){
		return dataService.getFuzzyRoleByUname(searchValue);}
	public Integer deletePermission(Permission permission){
		return dataService.deletePermission(permission);}
	public Permission getMaxPermissionId(){
		return dataService.getMaxPermissionId();}
	public Role getMaxRoleId(){
		return dataService.getMaxRoleId();}
	
	public List<QrtzJobDetails> getJobDetails(){
		return dataService.getJobDetails();}
	public List<QrtzJobDetails> getJobDetailForJobName(String jobName){
		return dataService.getJobDetailForJobName(jobName);}
	public QrtzJobDetails getJobData(String jobName){
		return dataService.getJobData(jobName);}
	public List<QuartzModel> getALlFromMyDefine(){
		return dataService.getALlFromMyDefine();}
	public QrtzJobData getMaxDataId(){
		return dataService.getMaxDataId();}
	public List<QrtzJobData> getJobDataByJobName(String jobName){
		return dataService.getJobDataByJobName(jobName);}
}
