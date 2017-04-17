package com.yj.ws.dto;

import java.math.BigDecimal;
import java.util.Date;

public class OrderTto {
	private Long orderId;       //订单状态
	private Integer orderClass;   //订单类型
	private BigDecimal orderMoney;   //金额
	private Date orderTime;        //订单时间
	private Integer orderState;    //订单状态       //1.申请；2.处理      3.未返回   4失败   5取消
	private Long userId;		//订单用户
	private String payNo;        //支付码

	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Integer getOrderClass() {
		return orderClass;
	}
	public void setOrderClass(Integer orderClass) {
		this.orderClass = orderClass;
	}
	public BigDecimal getOrderMoney() {
		return orderMoney;
	}
	public void setOrderMoney(BigDecimal orderMoney) {
		this.orderMoney = orderMoney;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public Integer getOrderState() {
		return orderState;
	}
	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getPayNo() {
		return payNo;
	}
	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}
	
}
