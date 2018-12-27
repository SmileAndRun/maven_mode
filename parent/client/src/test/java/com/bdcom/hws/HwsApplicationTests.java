package com.bdcom.hws;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.common.model.Role;
import org.common.model.client.User;
import org.common.utils.EncryptionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bdcom.hws.mapper.RoleMapper;
import com.bdcom.hws.mapper.UserMapper;
import com.hws.mybatisgenerator.core.CodeGenerator;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HwsApplicationTests {


	@Test
	public void testRandom() throws NoSuchAlgorithmException{
		byte[] test = EncryptionUtils.getRandomNum(10);
		String  str = EncryptionUtils.transformByteToString(test);
		byte[] test1 = str.getBytes();
	}
	@Test
	public void testCombineArray() throws NoSuchAlgorithmException{
		byte[] test = EncryptionUtils.getRandomNum(10);
		byte[] test1 = EncryptionUtils.getRandomNum(10);
		byte[] test2 = EncryptionUtils.combineByteArray(test,test1);
	}	
	/* 当使用自动构建时取消注释，且在启动类上加上扫描类  jar中的类 com.hws.mybatisgenerator.core
	 * @Autowired
	CodeGenerator cg;
	@Test
	public void testMybatisGenerator(){
		cg.generator("com.bdcom.hws", "permission");
	}
	@Autowired
	UserMapper userMapper;
	@Autowired
	RoleMapper roleMapper;
	@Test
	public void testInserUser() throws NoSuchAlgorithmException, InvalidKeyException{
		User user = new User();
		user.setUserName("123456789");
		user.setuIsLock('1');
		byte[] salt = EncryptionUtils.getRandomNum(16);
		byte[] pwd = "912176173".getBytes();
		byte[] key = EncryptionUtils.combineByteArray(pwd,salt);
		String encryPwd	=EncryptionUtils.transformByteToString(EncryptionUtils.encryptHMAC(key, null));
		user.setuSalt(salt);
		user.setUserPwd(encryPwd);
		user.setUserId(1);
		userMapper.registerUser(user);
		
		Role role = new Role();
		role.setRole("Admin");
		role.setUserId(1);
		role.setRoleId(1);
		roleMapper.insert(role);
	}
	
	@Test
	public void testPwd() throws InvalidKeyException, NoSuchAlgorithmException{
		User user = userMapper.getUserByUid(1);
		String pwd = user.getUserPwd();
		byte[] salt = user.getuSalt();
		byte[] data = "912176173".getBytes();
		byte[] key = EncryptionUtils.combineByteArray(salt,data);
		String encryPwd	=EncryptionUtils.transformByteToString(EncryptionUtils.encryptHMAC(key, null));
		if(pwd.equals(encryPwd)){
			System.out.println("true");
		}else{
			System.out.println("false");
		}
	}
	@Test
	public void testUserMapper(){
		userMapper.getSaltByUname("123456789");
	}
	*/
}
