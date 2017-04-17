package com.yj.ws.pojo;

import java.util.Date;

public class SysRole {
private Integer roleid;;
	
	private String roleName;			//
		
	private Integer roleState;		//角色状态0正常，1预备（预留），3关闭
	
	private Date createTime;		

	private Integer roleAddByUser;		//
	
	private Date roleDelTime;
	
	private Integer roleDelByUser;

	public Integer getRoleid() {
		return roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getRoleState() {
		return roleState;
	}

	public void setRoleState(Integer roleState) {
		this.roleState = roleState;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getRoleAddByUser() {
		return roleAddByUser;
	}

	public void setRoleAddByUser(Integer roleAddByUser) {
		this.roleAddByUser = roleAddByUser;
	}

	public Date getRoleDelTime() {
		return roleDelTime;
	}

	public void setRoleDelTime(Date roleDelTime) {
		this.roleDelTime = roleDelTime;
	}

	public Integer getRoleDelByUser() {
		return roleDelByUser;
	}

	public void setRoleDelByUser(Integer roleDelByUser) {
		this.roleDelByUser = roleDelByUser;
	}
	
}


