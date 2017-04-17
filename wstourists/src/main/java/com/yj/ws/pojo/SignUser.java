package com.yj.ws.pojo;

import java.util.Date;

/**
 * 签到
 * @author yj
 *
 */
public class SignUser {
	private Long signId;
	private Long userId;			//签到人
	private Date signTime;			//签到时间
	private Integer signTotal;      //签到标示：1未被加粉 ,1 已经被加粉
	private String remark;
	private String fback1;
	public Long getSignId() {
		return signId;
	}
	public void setSignId(Long signId) {
		this.signId = signId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Date getSignTime() {
		return signTime;
	}
	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}
	public Integer getSignTotal() {
		return signTotal;
	}
	public void setSignTotal(Integer signTotal) {
		this.signTotal = signTotal;
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
	
}
