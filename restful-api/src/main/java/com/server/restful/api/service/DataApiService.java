package com.server.restful.api.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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



@RequestMapping("/database")
public interface DataApiService {

	//select
	@RequestMapping("/getUserByUid")
	public User getUserByUid(@RequestParam("userId") Integer userId);
	@RequestMapping("/getUserInfoByUid")
	public User getUserInfoByUid(@RequestParam("userId") Integer userId);
	@RequestMapping("/getUsers")
	public List<User> getUsers();
	@RequestMapping("/getUserByUname")
	public User getUserByUname(@RequestParam("uName") String uName);
	@RequestMapping("/getUserInfoByUname")
	public User getUserInfoByUname(@RequestParam("uName") String uName);
	@RequestMapping("/getUser")
	public List<User> getUser(@RequestBody User user);
	@RequestMapping("/getUserSalt")
	public User getUserSalt(@RequestParam("userName") String userName);
	@RequestMapping("/findId")
	public SessionModel findId(@RequestParam("sessioinId") Integer sessioinId);
	@RequestMapping("/fingIdBySessionName")
	public SessionModel fingIdBySessionName(@RequestParam("sessionName") String sessionName);
	@RequestMapping("/getLastMaxId")
	public Log getLastMaxId();
	@RequestMapping("/getImages")
	public List<Images> getImages(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize);
	@RequestMapping("/getAllChat")
	public List<WeChat> getAllChat();
	@RequestMapping("/selectChatByTime")
	public List<WeChat> selectChatByTime(@RequestParam("startTime")Timestamp startTime,@RequestParam("endTime")Timestamp endTime);
	
	
	@RequestMapping("/getAllBar")
	public List<Barrage> getAllBar();
	@RequestMapping("/getListBarByTime")
	public List<Barrage> getListBarByTime(@RequestParam("time") Date time);
	@RequestMapping("/getBarById")
	public Barrage getBarById(@RequestParam("id") String id);
	@RequestMapping("/getListBarByLike")
	public List<Barrage> getListBarByLike(@RequestParam("content") String content);
	@RequestMapping("/getBarrageCount")
	public Integer getBarrageCount(@RequestParam("time") Timestamp time);
	@RequestMapping("/getBarByImagesId")
	public List<Barrage> getBarByImagesId(@RequestParam("imagesId") Integer imagesId);
	@RequestMapping("/getMaxContentId")
	public Barrage getMaxContentId();
	
	@RequestMapping("/getSaltByUname")
	public User getSaltByUname(@RequestParam("uName") String uName);
	@RequestMapping("/getFuzzyUsersByUid")
	public List<User> getFuzzyUsersByUid(@RequestParam("searchValue") String searchValue);
	@RequestMapping("/getFuzzyUserByUname")
	public List<User> getFuzzyUserByUname(@RequestParam("searchValue") String searchValue);
	@RequestMapping("/getLogLastMaxId")
	public Log getLogLastMaxId();
	@RequestMapping("/getAllRoleInfo")
	public List<Role> getAllRoleInfo();
	@RequestMapping("/getFuzzyRoleByUid")
	public List<Role> getFuzzyRoleByUid(@RequestParam("searchValue") String searchValue);
	@RequestMapping("/getFuzzyRoleByUname")
	public List<Role> getFuzzyRoleByUname(@RequestParam("searchValue") String searchValue);
	
	@RequestMapping("/getMaxPermissionId")
	public Permission getMaxPermissionId();
	@RequestMapping("/getMaxRoleId")
	public Role getMaxRoleId();
	
	@RequestMapping("/getJobDetails")
	public List<QrtzJobDetails> getJobDetails();
	@RequestMapping("/getJobDetailForJobName")
	public List<QrtzJobDetails> getJobDetailForJobName(@RequestParam("jobName") String jobName);
	@RequestMapping("/getJobData")
	public QrtzJobDetails getJobData(@RequestParam("jobName") String jobName);
	@RequestMapping("/getALlFromMyDefine")
	public List<QuartzModel> getALlFromMyDefine();
	@RequestMapping("/getMaxDataId")
	public QrtzJobData getMaxDataId();
	@RequestMapping("/getJobDataByJobName")
	public List<QrtzJobData> getJobDataByJobName(@RequestParam("jobName") String jobName);
	
	//insert
	@RequestMapping("/registerUser")
	public Integer registerUser(@RequestBody User user);
	@RequestMapping("/insert")
	public Integer insert(@RequestBody SessionModel model);
	@RequestMapping("/insertLog")
	public Integer insertLog(@RequestBody Log log);
	@RequestMapping("/addImage")
	public Integer addImage(@RequestBody Images images);
	@RequestMapping("/addChat")
	public Integer addChat(@RequestBody WeChat model);
	
	@RequestMapping("/addBarrage")
	public Integer addBarrage(@RequestBody Barrage barrage);
	@RequestMapping("/addPermission")
	public Integer addPermission(@RequestBody Permission permission);
	@RequestMapping("/addRole")
	public Integer addRole(@RequestBody Role role);
	
	@RequestMapping("/setPermanentStorage")
	public Integer setPermanentStorage(@RequestParam("jobName") String jobName);
	@RequestMapping("/insertSelfDifined")
	public Integer insertSelfDifined(@RequestBody QuartzModel model);
	@RequestMapping("/insertJobData")
	public Integer insertJobData(@RequestBody QrtzJobData model);
	
	//update
	@RequestMapping("/changeUser")
	public Integer changeUser(@RequestBody User user);
	@RequestMapping("/update")
	public Integer update(@RequestBody SessionModel model);
	@RequestMapping("/updateSelfDefined")
	public Integer updateSelfDefined(@RequestBody QuartzModel model);
	
	//delete
	@RequestMapping("/deleteUserByUid")
	public Integer deleteUserByUid(@RequestParam("userId") Integer userId);
	@RequestMapping("/deleteUserByUname")
	public Integer deleteUserByUname(@RequestParam("uName") String uName);
	@RequestMapping("/delImage")
	public Integer delImage(@RequestParam("imageId") Integer imageId);
	@RequestMapping("/delChatById")
	public Integer delChatById(@RequestParam("w_id") Integer w_id);
	
	@RequestMapping("/delBarrageBycontentId")
	public Integer delBarrageBycontentId(@RequestParam("contentId") Integer contentId);
	@RequestMapping("/deleteRole")
	public Integer deleteRole(@RequestBody Role role);
	
	@RequestMapping("/deleteDataByJobName")
	public Integer deleteDataByJobName(@RequestParam("name") String name);
	@RequestMapping("/deleteJobDetails")
	public Integer deleteJobDetails(@RequestParam("name") String name);
	@RequestMapping("/deletePermission")
	public Integer deletePermission(@RequestBody Permission permission);
}
