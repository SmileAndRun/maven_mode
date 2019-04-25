package com.bdcom.hws.core.shiro;



import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.common.model.Permission;
import org.common.model.Role;
import org.common.model.client.User;
import org.springframework.beans.factory.annotation.Autowired;

import com.bdcom.hws.service.UserService;


public class UserRealm extends AuthorizingRealm{

	@Autowired
	UserService userService;
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		String userName = (String)principals.fromRealm(getName()).iterator().next();
		User user = userService.getUserInfoByUname(userName);
		if(null == user)return null;
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		for(Role role:user.getRoleList()){
			info.addRole(role.getRole());
			for(Permission p:role.getPermissionList()){
				info.addStringPermission(p.getPermission());
			}
		}
		return info;
	}

	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String userName = (String) token.getPrincipal();
		if(null != userName && !userName.equals("")){
			User user = userService.getUserByUname(userName);
			if(null != user ){
				
				return new SimpleAuthenticationInfo(userName,user.getUserPwd(),getName());
			}
			//如果用户都不存在那么一定验证失败
			throw new AuthenticationException();
		}
		throw new AuthenticationException();
	}

}
