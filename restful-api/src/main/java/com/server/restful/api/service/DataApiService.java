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
	public User getUserByUid(@RequestParam Integer userId);
	@RequestMapping("/getUserInfoByUid")
	public User getUserInfoByUid(@RequestParam Integer userId);
	@RequestMapping("/getUsers")
	public List<User> getUsers();
	@RequestMapping("/getUserByUname")
	public User getUserByUname(@RequestParam String uName);
	@RequestMapping("/getUserInfoByUname")
	public User getUserInfoByUname(@RequestParam String uName);
	@RequestMapping("/getUser")
	public List<User> getUser(@RequestBody User user);
	@RequestMapping("/getUserSalt")
	public User getUserSalt(@RequestParam String userName);
	@RequestMapping("/findId")
	public SessionModel findId(@RequestParam Integer sessioinId);
	@RequestMapping("/fingIdBySessionName")
	public SessionModel fingIdBySessionName(@RequestParam String sessionName);
	@RequestMapping("/getLastMaxId")
	public Log getLastMaxId();
	@RequestMapping("/getImages")
	public List<Images> getImages(@RequestParam Integer pageNum, @RequestParam Integer pageSize);
	@RequestMapping("/getAllChat")
	public List<WeChat> getAllChat();
	@RequestMapping("/selectChatByTime")
	public List<WeChat> selectChatByTime(@RequestParam("startTime")Timestamp startTime,@RequestParam("endTime")Timestamp endTime);
	
	
	@RequestMapping("/getAllBar")
	public List<Barrage> getAllBar();
	@RequestMapping("/getListBarByTime")
	public List<Barrage> getListBarByTime(@RequestParam Date time);
	@RequestMapping("/getBarById")
	public Barrage getBarById(@RequestParam String id);
	@RequestMapping("/getListBarByLike")
	public List<Barrage> getListBarByLike(@RequestParam String content);
	@RequestMapping("/getBarrageCount")
	public Integer getBarrageCount(@RequestParam Timestamp time);
	@RequestMapping("/getBarByImagesId")
	public List<Barrage> getBarByImagesId(@RequestParam Integer imagesId);
	@RequestMapping("/getMaxContentId")
	public Barrage getMaxContentId();
	
	@RequestMapping("/getSaltByUname")
	public User getSaltByUname(@RequestParam String uName);
	@RequestMapping("/getFuzzyUsersByUid")
	public List<User> getFuzzyUsersByUid(@RequestParam String searchValue);
	@RequestMapping("/getFuzzyUserByUname")
	public List<User> getFuzzyUserByUname(@RequestParam String searchValue);
	@RequestMapping("/getLogLastMaxId")
	public Log getLogLastMaxId();
	@RequestMapping("/getAllRoleInfo")
	public List<Role> getAllRoleInfo();
	@RequestMapping("/getFuzzyRoleByUid")
	public List<Role> getFuzzyRoleByUid(@RequestParam String searchValue);
	@RequestMapping("/getFuzzyRoleByUname")
	public List<Role> getFuzzyRoleByUname(@RequestParam String searchValue);
	
	@RequestMapping("/getMaxPermissionId")
	public Permission getMaxPermissionId();
	@RequestMapping("/getMaxRoleId")
	public Role getMaxRoleId();
	
	@RequestMapping("/getJobDetails")
	public List<QrtzJobDetails> getJobDetails();
	@RequestMapping("/getJobDetailForJobName")
	public List<QrtzJobDetails> getJobDetailForJobName(@RequestParam String jobName);
	@RequestMapping("/getJobData")
	public QrtzJobDetails getJobData(@RequestParam String jobName);
	@RequestMapping("/getALlFromMyDefine")
	public List<QuartzModel> getALlFromMyDefine();
	@RequestMapping("/getMaxDataId")
	public QrtzJobData getMaxDataId();
	@RequestMapping("/getJobDataByJobName")
	public List<QrtzJobData> getJobDataByJobName(@RequestParam String jobName);
	
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
	public Integer setPermanentStorage(@RequestParam String jobName);
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
	public Integer deleteUserByUid(@RequestParam Integer userId);
	@RequestMapping("/deleteUserByUname")
	public Integer deleteUserByUname(@RequestParam String uName);
	@RequestMapping("/delImage")
	public Integer delImage(@RequestParam Integer imageId);
	@RequestMapping("/delChatById")
	public Integer delChatById(@RequestParam Integer w_id);
	
	@RequestMapping("/delBarrageBycontentId")
	public Integer delBarrageBycontentId(@RequestParam Integer contentId);
	@RequestMapping("/deleteRole")
	public Integer deleteRole(@RequestBody Role role);
	
	@RequestMapping("/deleteDataByJobName")
	public Integer deleteDataByJobName(@RequestParam String name);
	@RequestMapping("/deleteJobDetails")
	public Integer deleteJobDetails(@RequestParam String name);
	@RequestMapping("/deletePermission")
	public Integer deletePermission(@RequestBody Permission permission);
}
