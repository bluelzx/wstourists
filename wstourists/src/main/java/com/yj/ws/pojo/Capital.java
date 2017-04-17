package com.yj.ws.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 根据资金类型，得到金额
 * @author yj
 *
 */
public class Capital implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long cid;
	private BigDecimal cmoney;
	private Integer caclss;     // 类型1.一级vip 2 二级vip  3，三级vip  4 签到
	private String remark;    //备注
	private Integer bestClass;     //打类下子类   3.vip          2敲到
	private String explains;       //说明
	public Long getCid() {
		return cid;
	}
	public void setCid(Long cid) {
		this.cid = cid;
	}
	public BigDecimal getCmoney() {
		return cmoney;
	}
	public void setCmoney(BigDecimal cmoney) {
		this.cmoney = cmoney;
	}
	public Integer getCaclss() {
		return caclss;
	}
	public void setCaclss(Integer caclss) {
		this.caclss = caclss;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getBestClass() {
		return bestClass;
	}
	public void setBestClass(Integer bestClass) {
		this.bestClass = bestClass;
	}
	public String getExplains() {
		return explains;
	}
	public void setExplains(String explains) {
		this.explains = explains;
	}			
	
}
