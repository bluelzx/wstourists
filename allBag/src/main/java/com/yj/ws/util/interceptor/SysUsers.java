package com.yj.ws.util.interceptor;

import java.util.Date;

public class SysUsers {
	private Integer userId;
	private String userName;	//用户�?
	private String passWord;		//密码
	private String name;			//真实姓名
	private String imageUrl;			//图片地址
	private String email;			//Email地址
	private String phone;			//用户电话号码
	private Integer userState;			//用户状�?  1正常    2禁止
	private Date passwordChange;		//密码修改日期
	private Date emailChange;
	private Date lastLoginDate;				//�?��登陆时间
	private Date lastActivityDate;
	private Date lastLockoutDate;
	private Date createTime;				//创建时间
	private Integer isDisable;
	private Integer userRight;			//1为个人权限，2为角色强袭
	private Integer userRole;			//个人角色
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getUserState() {
		return userState;
	}
	public void setUserState(Integer userState) {
		this.userState = userState;
	}
	public Date getPasswordChange() {
		return passwordChange;
	}
	public void setPasswordChange(Date passwordChange) {
		this.passwordChange = passwordChange;
	}
	public Date getEmailChange() {
		return emailChange;
	}
	public void setEmailChange(Date emailChange) {
		this.emailChange = emailChange;
	}
	public Date getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	public Date getLastActivityDate() {
		return lastActivityDate;
	}
	public void setLastActivityDate(Date lastActivityDate) {
		this.lastActivityDate = lastActivityDate;
	}
	public Date getLastLockoutDate() {
		return lastLockoutDate;
	}
	public void setLastLockoutDate(Date lastLockoutDate) {
		this.lastLockoutDate = lastLockoutDate;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getIsDisable() {
		return isDisable;
	}
	public void setIsDisable(Integer isDisable) {
		this.isDisable = isDisable;
	}
	public Integer getUserRight() {
		return userRight;
	}
	public void setUserRight(Integer userRight) {
		this.userRight = userRight;
	}
	public Integer getUserRole() {
		return userRole;
	}
	public void setUserRole(Integer userRole) {
		this.userRole = userRole;
	}
	
}







