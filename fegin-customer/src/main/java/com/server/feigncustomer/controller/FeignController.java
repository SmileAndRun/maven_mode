package com.server.feigncustomer.controller;


import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


@RestController
@RequestMapping("/feign")
public class FeignController {

	@Autowired
	FeignService feignService;
	
	//select
	@RequestMapping("/getUserByUid")
	public User getUserByUid(Integer userId){
		User user = feignService.getUserByUid(userId);
		return user;
		}
	public User getUserInfoByUid(Integer userId){
		return null;}
	@RequestMapping("/getUsers")
	public List<User> getUsers(){
		return feignService.getUsers();}
	public User getUserByUname(String uName){
		return null;}
	public User getUserInfoByUname(String uName){
		return null;}
	@RequestMapping("/getUser")
	public List<User> getUser(User user){
		return feignService.getUser(user);
		}
	public User getUserSalt(String userName){
		return null;}
	public SessionModel findId(Integer sessioinId){
		return null;}
	public SessionModel fingIdBySessionName(String sessionName){
		return null;}
	public Log getLastMaxId(){
		return null;}
	public List<Images> getImages(Integer pageNum,Integer pageSize){
		return null;}
	public List<WeChat> getAllChat(){
		return null;}
	public List<WeChat> selectChatByTime(Timestamp startTime,Timestamp endTime){
		return null;}
	
	@RequestMapping("/getAllBar")
	public List<Barrage> getAllBar(){
		return feignService.getAllBar();}
	public List<Barrage> getListBarByTime(Date time){
		return null;}
	public Barrage getBarById(String id){
		return null;}
	public List<Barrage> getListBarByLike(String content){
		return null;}
	public Integer getBarrageCount(Timestamp time){
		return null;}
	public List<Barrage> getBarByImagesId(Integer imagesId){
		return null;}
	public Barrage getMaxContentId(){
		return null;}
	
	public User getSaltByUname(String uName){
		return null;}
	public List<User> getFuzzyUsersByUid(String searchValue){
		return null;}
	public List<User> getFuzzyUserByUname(String searchValue){
		return null;}
	public Log getLogLastMaxId(){
		return null;}
	public List<Role> getAllRoleInfo(){
		return null;}
	public List<Role> getFuzzyRoleByUid(String searchValue){
		return null;}
	public List<Role> getFuzzyRoleByUname(String searchValue){
		return null;}
	
	public Permission getMaxPermissionId(){
		return null;}
	public Role getMaxRoleId(){
		return null;}
	
	public List<QrtzJobDetails> getJobDetails(){
		return null;}
	public List<QrtzJobDetails> getJobDetailForJobName(String jobName){
		return null;}
	public QrtzJobDetails getJobData(String jobName){
		return null;}
	public List<QuartzModel> getALlFromMyDefine(){
		return null;}
	public QrtzJobData getMaxDataId(){
		return null;}
	public List<QrtzJobData> getJobDataByJobName(String jobName){
		return null;}
	
	//insert
	public Integer registerUser(User user){
		return null;}
	public Integer insert(SessionModel model){
		return null;}
	public Integer insertLog(Log log){
		return null;}
	public Integer addImage(Images images){
		return null;}
	public Integer addChat(WeChat model){
		return null;}
	
	public Integer addBarrage(Barrage barrage){
		return null;}
	public Integer addPermission(Permission permission){
		return null;}
	public Integer addRole(Role role){
		return null;}
	
	public Integer setPermanentStorage(String jobName){
		return null;}
	public Integer insertSelfDifined(QuartzModel model){
		return null;}
	public Integer insertJobData(QrtzJobData model){
		return null;}
	
	//update
	@RequestMapping("/changeUser")
	public Integer changeUser(User user){
		return feignService.changeUser(user);}
	public Integer update(SessionModel model){
		return null;}
	public Integer updateSelfDefined(QuartzModel model){
		return null;}
	
	//delete
	public Integer deleteUserByUid(Integer userId){
		return userId;}
	public Integer deleteUserByUname(String uName){
		return null;}
	public Integer delImage(Integer imageId){
		return imageId;}
	public Integer delChatById(Integer w_id){
		return w_id;}
	
	public Integer delBarrageBycontentId(Integer contentId){
		return contentId;}
	public Integer deleteRole(Role role){
		return null;}
	
	public Integer deleteDataByJobName(String name){
		return null;}
	public Integer deleteJobDetails(String name){
		return null;}
	public Integer deletePermission(Permission permission){
		return null;}
}
