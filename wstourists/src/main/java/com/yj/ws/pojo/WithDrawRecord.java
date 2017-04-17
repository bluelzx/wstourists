package com.yj.ws.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 资金记录表
 * @author yj
 *
 */
public class WithDrawRecord implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long recordId;
	private Integer recordClss;			//类型 ：资金类型，1,提现，2签到获取，3vip充值
	private BigDecimal recordMomey;    //资金金额
	private Date recordTime;			//资金时间
	private Integer recordState;      //资金状态,如果类型是1，提现，状态用  1.申请；2.处理      3.未返回   4失败   5取消 3,4,5在vip充值时候用    
	private Date handleTime;			//提现处理时间
	private Long userId;				//资金记录人
	private Long sysuserId;				//提现处理人
	private String remark;
	private String fback1;           //支付码
	private Long accountId;
	private Integer inorout;          //1收入，2支出
	private Integer caclss;       
	private String phone;
	private String cardNumber;
	private Integer accountClass;
	public Long getRecordId() {
		return recordId;
	}
	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}
	public Integer getRecordClss() {
		return recordClss;
	}
	public void setRecordClss(Integer recordClss) {
		this.recordClss = recordClss;
	}
	public BigDecimal getRecordMomey() {
		return recordMomey;
	}
	public void setRecordMomey(BigDecimal recordMomey) {
		this.recordMomey = recordMomey;
	}
	public Date getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}
	public Integer getRecordState() {
		return recordState;
	}
	public void setRecordState(Integer recordState) {
		this.recordState = recordState;
	}
	public Date getHandleTime() {
		return handleTime;
	}
	public void setHandleTime(Date handleTime) {
		this.handleTime = handleTime;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getSysuserId() {
		return sysuserId;
	}
	public void setSysuserId(Long sysuserId) {
		this.sysuserId = sysuserId;
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
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public Integer getInorout() {
		return inorout;
	}
	public void setInorout(Integer inorout) {
		this.inorout = inorout;
	}
	public Integer getCaclss() {
		return caclss;
	}
	public void setCaclss(Integer caclss) {
		this.caclss = caclss;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public Integer getAccountClass() {
		return accountClass;
	}
	public void setAccountClass(Integer accountClass) {
		this.accountClass = accountClass;
	}
	
}
