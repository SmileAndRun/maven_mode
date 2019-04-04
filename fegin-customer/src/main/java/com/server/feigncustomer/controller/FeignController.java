package com.server.feigncustomer.controller;


import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
public class FeignController {

	@Autowired
	FeignService feignService;
	
	//select
	@RequestMapping("/getUserByUid")
	public User getUserByUid(@RequestParam Integer userId){
		return null;}
	@RequestMapping("/getUserInfoByUid")
	public User getUserInfoByUid(@RequestParam Integer userId){
		return null;}
	@RequestMapping("/getUsers")
	public List<User> getUsers(){
		return null;}
	@RequestMapping("/getUserByUname")
	public User getUserByUname(@RequestParam String uName){
		return null;}
	@RequestMapping("/getUserInfoByUname")
	public User getUserInfoByUname(@RequestParam String uName){
		return null;}
	@RequestMapping("/getUser")
	public List<User> getUser(@RequestBody User user){
		return null;}
	@RequestMapping("/getUserSalt")
	public User getUserSalt(@RequestParam String userName){
		return null;}
	@RequestMapping("/findId")
	public SessionModel findId(@RequestParam Integer sessioinId){
		return null;}
	@RequestMapping("/fingIdBySessionName")
	public SessionModel fingIdBySessionName(@RequestParam String sessionName){
		return null;}
	@RequestMapping("/getLastMaxId")
	public Log getLastMaxId(){
		return null;}
	@RequestMapping("/getImages")
	public List<Images> getImages(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
		return null;}
	@RequestMapping("/getAllChat")
	public List<WeChat> getAllChat(){
		return null;}
	@RequestMapping("/selectChatByTime")
	public List<WeChat> selectChatByTime(@RequestParam("startTime")Timestamp startTime,@RequestParam("endTime")Timestamp endTime){
		return null;}
	
	
	@RequestMapping("/getAllBar")
	public List<Barrage> getAllBar(){
		return null;}
	@RequestMapping("/getListBarByTime")
	public List<Barrage> getListBarByTime(@RequestParam Date time){
		return null;}
	@RequestMapping("/getBarById")
	public Barrage getBarById(@RequestParam String id){
		return null;}
	@RequestMapping("/getListBarByLike")
	public List<Barrage> getListBarByLike(@RequestParam String content){
		return null;}
	@RequestMapping("/getBarrageCount")
	public Integer getBarrageCount(@RequestParam Timestamp time){
		return null;}
	@RequestMapping("/getBarByImagesId")
	public List<Barrage> getBarByImagesId(@RequestParam Integer imagesId){
		return null;}
	@RequestMapping("/getMaxContentId")
	public Barrage getMaxContentId(){
		return null;}
	
	@RequestMapping("/getSaltByUname")
	public User getSaltByUname(@RequestParam String uName){
		return null;}
	@RequestMapping("/getFuzzyUsersByUid")
	public List<User> getFuzzyUsersByUid(@RequestParam String searchValue){
		return null;}
	@RequestMapping("/getFuzzyUserByUname")
	public List<User> getFuzzyUserByUname(@RequestParam String searchValue){
		return null;}
	@RequestMapping("/getLogLastMaxId")
	public Log getLogLastMaxId(){
		return null;}
	@RequestMapping("/getAllRoleInfo")
	public List<Role> getAllRoleInfo(){
		return null;}
	@RequestMapping("/getFuzzyRoleByUid")
	public List<Role> getFuzzyRoleByUid(@RequestParam String searchValue){
		return null;}
	@RequestMapping("/getFuzzyRoleByUname")
	public List<Role> getFuzzyRoleByUname(@RequestParam String searchValue){
		return null;}
	
	@RequestMapping("/getMaxPermissionId")
	public Permission getMaxPermissionId(){
		return null;}
	@RequestMapping("/getMaxRoleId")
	public Role getMaxRoleId(){
		return null;}
	
	@RequestMapping("/getJobDetails")
	public List<QrtzJobDetails> getJobDetails(){
		return null;}
	@RequestMapping("/getJobDetailForJobName")
	public List<QrtzJobDetails> getJobDetailForJobName(@RequestParam String jobName){
		return null;}
	@RequestMapping("/getJobData")
	public QrtzJobDetails getJobData(@RequestParam String jobName){
		return null;}
	@RequestMapping("/getALlFromMyDefine")
	public List<QuartzModel> getALlFromMyDefine(){
		return null;}
	@RequestMapping("/getMaxDataId")
	public QrtzJobData getMaxDataId(){
		return null;}
	@RequestMapping("/getJobDataByJobName")
	public List<QrtzJobData> getJobDataByJobName(@RequestParam String jobName){
		return null;}
	
	//insert
	@RequestMapping("/registerUser")
	public Integer registerUser(@RequestBody User user){
		return null;}
	@RequestMapping("/insert")
	public Integer insert(@RequestBody SessionModel model){
		return null;}
	@RequestMapping("/insertLog")
	public Integer insertLog(@RequestBody Log log){
		return null;}
	@RequestMapping("/addImage")
	public Integer addImage(@RequestBody Images images){
		return null;}
	@RequestMapping("/addChat")
	public Integer addChat(@RequestBody WeChat model){
		return null;}
	
	@RequestMapping("/addBarrage")
	public Integer addBarrage(@RequestBody Barrage barrage){
		return null;}
	@RequestMapping("/addPermission")
	public Integer addPermission(@RequestBody Permission permission){
		return null;}
	@RequestMapping("/addRole")
	public Integer addRole(@RequestBody Role role){
		return null;}
	
	@RequestMapping("/setPermanentStorage")
	public Integer setPermanentStorage(@RequestParam String jobName){
		return null;}
	@RequestMapping("/insertSelfDifined")
	public Integer insertSelfDifined(@RequestBody QuartzModel model){
		return null;}
	@RequestMapping("/insertJobData")
	public Integer insertJobData(@RequestBody QrtzJobData model){
		return null;}
	
	//update
	@RequestMapping("/changeUser")
	public Integer changeUser(@RequestBody User user){
		return null;}
	@RequestMapping("/update")
	public Integer update(@RequestBody SessionModel model){
		return null;}
	@RequestMapping("/updateSelfDefined")
	public Integer updateSelfDefined(@RequestBody QuartzModel model){
		return null;}
	
	//delete
	@RequestMapping("/deleteUserByUid")
	public Integer deleteUserByUid(@RequestParam Integer userId){
		return userId;}
	@RequestMapping("/deleteUserByUname")
	public Integer deleteUserByUname(@RequestParam String uName){
		return null;}
	@RequestMapping("/delImage")
	public Integer delImage(@RequestParam Integer imageId){
		return imageId;}
	@RequestMapping("/delChatById")
	public Integer delChatById(@RequestParam Integer w_id){
		return w_id;}
	
	@RequestMapping("/delBarrageBycontentId")
	public Integer delBarrageBycontentId(@RequestParam Integer contentId){
		return contentId;}
	@RequestMapping("/deleteRole")
	public Integer deleteRole(@RequestBody Role role){
		return null;}
	
	@RequestMapping("/deleteDataByJobName")
	public Integer deleteDataByJobName(@RequestParam String name){
		return null;}
	@RequestMapping("/deleteJobDetails")
	public Integer deleteJobDetails(@RequestParam String name){
		return null;}
	@RequestMapping("/deletePermission")
	public Integer deletePermission(@RequestBody Permission permission){
		return null;}
}
