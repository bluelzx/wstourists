package com.yj.ws.pojo;

import java.io.Serializable;
import java.util.Date;


public class ShortMessageLogs implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String serialNumber;
	
	private String telePhone;             //手机号码
	
	private String content;            //内容
	
	private Integer phinoClss;         //
	
	private Date createTime;                 //发送时间
	 
	private String channel;				//短信通道

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getTelePhone() {
		return telePhone;
	}

	public void setTelePhone(String telePhone) {
		this.telePhone = telePhone;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getPhinoClss() {
		return phinoClss;
	}

	public void setPhinoClss(Integer phinoClss) {
		this.phinoClss = phinoClss;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
	
}
