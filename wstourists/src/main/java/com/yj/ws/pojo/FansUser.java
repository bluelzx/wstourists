package com.yj.ws.pojo;

import java.io.Serializable;
import java.util.Date;

public class FansUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fansId;
	private Long fansUserId;    //加粉人
	private Long passiveUserId;        //被加粉人
	private String passivePhone;     //被加粉人电话
	private Date fansTime;					//加粉时间
	private String remark;
	private String fback1;
	private Integer isdelete;
	private Integer fansNumber;
	
	public String getPassivePhone() {
		return passivePhone;
	}
	public void setPassivePhone(String passivePhone) {
		this.passivePhone = passivePhone;
	}
	public String getFansId() {
		return fansId;
	}
	public void setFansId(String fansId) {
		this.fansId = fansId;
	}
	public Long getFansUserId() {
		return fansUserId;
	}
	public void setFansUserId(Long fansUserId) {
		this.fansUserId = fansUserId;
	}
	public Long getPassiveUserId() {
		return passiveUserId;
	}
	public void setPassiveUserId(Long passiveUserId) {
		this.passiveUserId = passiveUserId;
	}
	public Date getFansTime() {
		return fansTime;
	}
	public void setFansTime(Date fansTime) {
		this.fansTime = fansTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getFback1() {
		return fback1;
	}
	public void setFback1(String fback1) {
		this.fback1 = fback1;
	}
	public Integer getIsdelete() {
		return isdelete;
	}
	public void setIsdelete(Integer isdelete) {
		this.isdelete = isdelete;
	}
	public Integer getFansNumber() {
		return fansNumber;
	}
	public void setFansNumber(Integer fansNumber) {
		this.fansNumber = fansNumber;
	}
	
}







