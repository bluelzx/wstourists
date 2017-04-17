package com.yj.ws.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class User {
	private Long userId;
	private String userName;          //用户名
	private String realName;          //姓名
	private String phoneNumber;        //用户手机号码
	private String qqId;              
	private String emailNumber; 
	private String UserPassword; 
	private Integer userSex;           //性别，1，男；2，女（填写提现账户表时插入）
	private Integer fansNumber;           //总加粉人数
	private BigDecimal playTotal;            //用户充值总额
	private Date lastTime;
	private String lastIp;
	private BigDecimal totalMoney;                 //总资金
	private BigDecimal frozenFund;                  //冻结资金
	private Integer vipLevel;                       //vip等级
	private Date vipStart;              //当前vip开始时间
	private Date vipEnd;                  //结束时间
	private String fback1;       
	private String fback2;
	private Date crateDate;           //注册时间
	private Long accountId;           //提现管理账户,绑定挡墙
	private Integer isjoin;              //是否被加入1：禁用，2：允许
	private BigDecimal withdrawTotal;        //提现总额
	private Long parentId;               //父类
	private String cardNumber;           //身份证号
	private Integer isdelete;
	private Integer province;            //省 
	private Integer city;                 //城市
	private BigDecimal earnMoney;				//赚取资金
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getQqId() {
		return qqId;
	}
	public void setQqId(String qqId) {
		this.qqId = qqId;
	}
	public String getEmailNumber() {
		return emailNumber;
	}
	public void setEmailNumber(String emailNumber) {
		this.emailNumber = emailNumber;
	}
	public String getUserPassword() {
		return UserPassword;
	}
	public void setUserPassword(String userPassword) {
		UserPassword = userPassword;
	}
	public Integer getUserSex() {
		return userSex;
	}
	public void setUserSex(Integer userSex) {
		this.userSex = userSex;
	}
	public Integer getFansNumber() {
		return fansNumber;
	}
	public void setFansNumber(Integer fansNumber) {
		this.fansNumber = fansNumber;
	}
	public BigDecimal getPlayTotal() {
		return playTotal;
	}
	public void setPlayTotal(BigDecimal playTotal) {
		this.playTotal = playTotal;
	}
	public Date getLastTime() {
		return lastTime;
	}
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
	public String getLastIp() {
		return lastIp;
	}
	public void setLastIp(String lastIp) {
		this.lastIp = lastIp;
	}
	public BigDecimal getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}
	public BigDecimal getFrozenFund() {
		return frozenFund;
	}
	public void setFrozenFund(BigDecimal frozenFund) {
		this.frozenFund = frozenFund;
	}
	public Integer getVipLevel() {
		return vipLevel;
	}
	public void setVipLevel(Integer vipLevel) {
		this.vipLevel = vipLevel;
	}
	public Date getVipStart() {
		return vipStart;
	}
	public void setVipStart(Date vipStart) {
		this.vipStart = vipStart;
	}
	public Date getVipEnd() {
		return vipEnd;
	}
	public void setVipEnd(Date vipEnd) {
		this.vipEnd = vipEnd;
	}
	public String getFback1() {
		return fback1;
	}
	public void setFback1(String fback1) {
		this.fback1 = fback1;
	}
	public String getFback2() {
		return fback2;
	}
	public void setFback2(String fback2) {
		this.fback2 = fback2;
	}
	public Date getCrateDate() {
		return crateDate;
	}
	public void setCrateDate(Date crateDate) {
		this.crateDate = crateDate;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public Integer getIsjoin() {
		return isjoin;
	}
	public void setIsjoin(Integer isjoin) {
		this.isjoin = isjoin;
	}
	public BigDecimal getWithdrawTotal() {
		return withdrawTotal;
	}
	public void setWithdrawTotal(BigDecimal withdrawTotal) {
		this.withdrawTotal = withdrawTotal;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
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
	public Integer getProvince() {
		return province;
	}
	public void setProvince(Integer province) {
		this.province = province;
	}
	public Integer getCity() {
		return city;
	}
	public void setCity(Integer city) {
		this.city = city;
	}
	public BigDecimal getEarnMoney() {
		return earnMoney;
	}
	public void setEarnMoney(BigDecimal earnMoney) {
		this.earnMoney = earnMoney;
	}
	
}








