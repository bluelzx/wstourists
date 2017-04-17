package com.yj.ws.pojo;

import java.io.Serializable;
import java.util.Date;

public class Ips implements Serializable{
	private Long id;
	private String phone;
	private String ip;
	private String fback1;
	private Date times;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getFback1() {
		return fback1;
	}
	public void setFback1(String fback1) {
		this.fback1 = fback1;
	}
	public Date getTimes() {
		return times;
	}
	public void setTimes(Date times) {
		this.times = times;
	}
}
