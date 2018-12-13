package org.common.model;

import java.util.List;

public class Role {
  
    private Integer roleId;
    private Integer userId;
    private String role;
    private List<Permission> permissionList;
    
    public List<Permission> getPermissionList() {
		return permissionList;
	}
	public void setPermissionList(List<Permission> permissionList) {
		this.permissionList = permissionList;
	}
    public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
}