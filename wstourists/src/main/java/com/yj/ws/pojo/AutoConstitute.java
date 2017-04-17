package com.yj.ws.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AutoConstitute implements Serializable{
	
	private static final long serialVersionUID = -5274196232849994145L;

	private String id;

    private String constituteId;

    private Object constituteName;

    private String parentConstitute;

    private Date operateDate;

    private String operator;

    private String lastModifior;

    private Date lastModifiedDate;

    private String status;

    private String pinyin;

    private Object memo;

    private String showOrder;

    private Date createDate;

    private BigDecimal dataFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConstituteId() {
        return constituteId;
    }

    public void setConstituteId(String constituteId) {
        this.constituteId = constituteId;
    }

    public Object getConstituteName() {
        return constituteName;
    }

    public void setConstituteName(Object constituteName) {
        this.constituteName = constituteName;
    }

    public String getParentConstitute() {
        return parentConstitute;
    }

    public void setParentConstitute(String parentConstitute) {
        this.parentConstitute = parentConstitute;
    }

    public Date getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getLastModifior() {
        return lastModifior;
    }

    public void setLastModifior(String lastModifior) {
        this.lastModifior = lastModifior;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public Object getMemo() {
        return memo;
    }

    public void setMemo(Object memo) {
        this.memo = memo;
    }

    public String getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(String showOrder) {
        this.showOrder = showOrder;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public BigDecimal getDataFlag() {
        return dataFlag;
    }

    public void setDataFlag(BigDecimal dataFlag) {
        this.dataFlag = dataFlag;
    }
}