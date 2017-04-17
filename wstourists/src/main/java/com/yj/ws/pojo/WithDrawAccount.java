package com.yj.ws.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 绑定提现
 * @author yj
 *
 */
public class WithDrawAccount implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long accountId;
	private Integer accountClass;
	private Date accountTime;
	private Long userId;
	private String cardNumber;
	private Integer isdelete;
	private String remark;
	private String fback1;
	private String realName;
	
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public Integer getAccountClass() {
		return accountClass;
	}
	public void setAccountClass(Integer accountClass) {
		this.accountClass = accountClass;
	}
	public Date getAccountTime() {
		return accountTime;
	}
	public void setAccountTime(Date accountTime) {
		this.accountTime = accountTime;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public Integer getIsdelete() {
		return isdelete;
	}
	public void setIsdelete(Integer isdelete) {
		this.isdelete = isdelete;
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








