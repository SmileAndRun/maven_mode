package com.bdcom.server.service.impl;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.Query;
import org.common.core.annotation.TargetDataSource;
import org.common.core.datasource.DatabaseType;
import org.common.model.Log;
import org.common.model.OnlineCountModel;
import org.common.model.Permission;
import org.common.model.Role;
import org.common.model.server.User;
import org.common.utils.EncryptionUtils;
import org.common.utils.FontColourUtils;
import org.common.utils.ReadResourceUtils;
import org.common.utils.RedisUtils;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.bdcom.server.lucene.SearchMethod;
import com.bdcom.server.mapper.ManagerMapper;
import com.bdcom.server.service.ManagerService;

@Service
public class ManagerServiceImpl extends SearchMethod implements ManagerService {

	@Autowired
	ManagerMapper mp;
	@Autowired
	RedisUtils redisUtils;
	
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public User getUserByUid(int userId) {
		return mp.getUserByUid(userId);
	}
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public User getUserInfoByUid(int userId) {
		return mp.getUserInfoByUid(userId);
	}
	@SuppressWarnings("unchecked")
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public List<User> getUsers() {
		
		List<User> list = null;
		if(redisUtils.hasKey("getAllUsers")){
			Object object = redisUtils.get("getAllUsers");
			list = (List<User>) object;
		}else{
			list = mp.getUsers();
			redisUtils.set("getAllUsers",list);
		}
		
		return list;
	}
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public User getUserByUname(String uName) {
		return mp.getUserByUname(uName);
	}
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public User getUserInfoByUname(String uName) {
		return mp.getUserInfoByUname(uName);
	}
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public List<User> getUser(User user) {
		return mp.getUser(user);
	}
	@Transactional
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public int registerUser(User user,String role) throws NoSuchAlgorithmException, InvalidKeyException {
		
		User temp = new User();
		temp.setUserName(user.getUserName());
		temp.setuIsLock('0');
		temp.setuIsManage('0');
		byte[] salt = EncryptionUtils.getRandomNum(16);
		byte[] key = EncryptionUtils.combineByteArray(user.getUserPwd().getBytes(),salt);
		temp.setUserPwd(EncryptionUtils.transformByteToString(EncryptionUtils.encryptHMAC(key, null)));
		temp.setuSalt(salt);
		
		Log log = new Log();
		Date date = new Date();
		log.setLogtype("3");
		log.setLogmessage("register");
		log.setLogiserror("0");
		log.setLogtime(new Timestamp(date.getTime()));
		
		int id = getLastMaxId()+1;
		temp.setUserId(id);
		log.setLogid(mp.getLogLastMaxId()+1);
		log.setUserid(id);
		//t_log表中有user外键故需先插t_user表
		int row = mp.registerUser(temp);
		//更新缓存
		if(row == 0)throw new RuntimeException("注册失败");
		if(row>0&&redisUtils.hasKey("getAllUsers")){
			Object object = redisUtils.get("getAllUsers");
			@SuppressWarnings("unchecked")
			List<User> list = (List<User>) object;
			list.add(temp);
			redisUtils.set("getAllUsers",list);
		}
		mp.insertLog(log);
		
		if(null == role||"".equals(role.trim()))role="General";
		Role roleObj = mp.getMaxRoleId();
		if(null == roleObj){
			roleObj = new Role();
			roleObj.setRoleId(1);
		}else{
			roleObj.setRoleId(roleObj.getRoleId() + 1);
		}
		roleObj.setUserId(id);
		roleObj.setRole(role);
		mp.addRole(roleObj);
		
		
		return row;
	}
	@Transactional
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public JSONObject changeUser(User user,String[] roleList,String[] roleListO) {
		JSONObject obj = new JSONObject();
		List<String> tempList ;
		if(null == roleList){
			tempList = new ArrayList<String>();
		}else{
			tempList = Arrays.asList(roleList);
		}
		List<String> tempListO ;
		if(null == roleListO) {
			tempListO = new ArrayList<String>();
		}else{
			tempListO = Arrays.asList(roleListO);
		}
		boolean flag = true;
		if (null != roleList )
		for(String temp:roleList){
			//新增
			if(tempListO.contains(temp)) continue;
			Role role = mp.getMaxRoleId();
			if(null == role){
				role = new Role();
				role.setRoleId(1);
			}else{
				role.setRoleId(role.getRoleId() + 1);
			}
			role.setUserId(user.getUserId());
			role.setRole(temp);
			if(mp.addRole(role)==0){
				flag = false;
				throw new  RuntimeException("修改失败");
			}
		}
		if (null != roleListO)
		for(String temp:roleListO){
			//删除
			if(tempList.contains(temp)) continue;
			Role role = new Role();
			role.setUserId(user.getUserId());
			role.setRole(temp);
			if(mp.deleteRole(role)== 0){
				flag = false;
				throw new  RuntimeException("修改失败");
			}
		}
		if(mp.changeUser(user)==0){
			flag = false;
			throw new  RuntimeException("修改失败");
		}
		obj.put("changeFlag", flag);
		return obj;
	}
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public int deleteUserByUid(int userId) {
		return mp.deleteUserByUid(userId);
	}
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public int deleteUserByUname(String uName) {
		return mp.deleteUserByUname(uName);
	}
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public User getSaltByUname(String uName) {
		return mp.getSaltByUname(uName);
	}
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public List<User> getFuzzyUsersByUid(String searchValue) {
		return mp.getFuzzyUsersByUid(searchValue);
	}
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public List<User> getFuzzyUserByUname(String searchValue){
		return mp.getFuzzyUserByUname(searchValue);
	}
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public List<Role> getFuzzyRoleByUid(String searchValue) {
		return mp.getFuzzyRoleByUid(searchValue);
	}
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public List<Role> getFuzzyRoleByUname(String searchValue){
		return mp.getFuzzyRoleByUname(searchValue);
	}
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public int getLastMaxId() {
		return mp.getLastMaxId();
	}
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public int insertLog(Log record) {
		return mp.insertLog(record);
	}
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public int getLogLastMaxId() {
		return mp.getLogLastMaxId();
	}
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public JSONObject getAllRoleInfo() {
		JSONObject obj = new JSONObject();
		List<Role> roleList = mp.getAllRoleInfo();
		if(null == roleList||roleList.size()==0)return null;
		obj.put("roleList", roleList);
		return obj;
	}
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public JSONObject changeRole(Integer roleId,  String[] preList,
			String[] preListO) {
		JSONObject obj = new JSONObject();
		List<String> tempList ;
		if(null == preList){
			tempList = new ArrayList<String>();
		}else{
			tempList = Arrays.asList(preList);
		}
		List<String> tempListO ;
		if(null == preListO) {
			tempListO = new ArrayList<String>();
		}else{
			tempListO = Arrays.asList(preListO);
		}
		boolean flag = true;
		if (null != preList )
		for(String temp:preList){
			//新增
			if(tempListO.contains(temp)) continue;
			Permission permission = mp.getMaxPermissionId();
			if(null == permission){
				permission = new Permission();
				permission.setpId(1);
			}else{
				permission.setpId(permission.getpId() + 1);
			}
			permission.setRoleId(roleId);
			permission.setPermission(temp);
			if(mp.addPermission(permission)==0){
				flag = false;
				throw new  RuntimeException("修改角色失败");
			}
		}
		if (null != preListO)
		for(String temp:preListO){
			//删除
			if(tempList.contains(temp)) continue;
			Permission permission = new Permission();
			permission.setRoleId(roleId);
			permission.setPermission(temp);
			if(mp.deletePermission(permission)== 0){
				flag = false;
				throw new  RuntimeException("修改角色失败");
			}
		}
		obj.put("flag", flag);
		return obj;
	}
	@Override
	public List<String> getElement(List<String> attribute, String path,String type) throws DocumentException, IOException {
		List<String> list = new ArrayList<String>();
		Element rootElement = ReadResourceUtils.getXmlRootElement(ReadResourceUtils.getClassPathResource(path));
		List<Map<String, String>> typeList = ReadResourceUtils.getAttributeValues(attribute, rootElement);
		for(Map<String, String> map :typeList){
			if(map.get("type").equals(type))
				list.add(map.get("name"));
		}
		return list;
	}
	@Override
	public void initGraphAnalysis(HttpServletRequest request) {
		List<Integer> list = new ArrayList<Integer>();
		String initData = "";
	    boolean flag = true;
	    long time = System.currentTimeMillis();
	    int activeSessions = OnlineCountModel.activeSessions;
		list.add(activeSessions);
		initData += activeSessions+",";
	    while(flag){
	    	if(System.currentTimeMillis()-time == 1000){
	    		time = System.currentTimeMillis();
	    		activeSessions = OnlineCountModel.activeSessions;
	    		list.add(activeSessions);
	    		initData += activeSessions+",";
	    		if(list.size()>=10){
	    			flag = false;
	    		}
	    	}
	    }
	    request.setAttribute("initData", initData);
	}
	Map<List<Document>, Query> map = null;
	List<String> strList = null;
	List<Document> docList = null;
	@Override
	public List<String> getSuggestion(String context) throws IOException {
		map = searchByPrefixQuery(new Term("keyword", context));
		if(null != map){
			for(List<Document> list:map.keySet()){
				docList = list;
				break;
			}
			strList = new ArrayList<String>();
			for(Document doc:docList){
				if(null == doc) return null;
				String[] temp = doc.get("keyword").split(context);
				String text = "";
				if(doc.get("keyword").equals(context.trim())){
					text = "<font style='font-weight:bold;'>" + context + "</font>";
				}else{
					text = "<font style='font-weight:bold;'>" + context + "</font>" +temp[1];
				}
				
				strList.add(text);
			}
		}else{
			strList = null;
		}
		return strList;
	}
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public List<User> searchUsersAndColoring(String content, String type) {
		String color = "red";
		List<User> list = null;
		if(type.equals("1")){
			list = getFuzzyUsersByUid(content);
			for(User user : list){
				user.setuId(FontColourUtils.colour(String.valueOf(user.getUserId()), color, content));
			}
		}else{
			list = getFuzzyUserByUname(content);
			for(User user : list){
				user.setUserName(FontColourUtils.colour(user.getUserName(), color, content));
			}
		}
		return list;
	}
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public List<Role> searchsRoleAndColoring(String content, String type) {
		String color = "red";
		List<Role> list = null;
		if(type.equals("1")){
			list = getFuzzyRoleByUid(content);
			for(Role role : list){
				role.setrId(FontColourUtils.colour(String.valueOf(role.getRoleId()), color, content));
			}
		}else{
			list = getFuzzyRoleByUname(content);
			for(Role role : list){
				role.setRole(FontColourUtils.colour(role.getRole(), color, content));
			}
		}
		return list;
	}

}
