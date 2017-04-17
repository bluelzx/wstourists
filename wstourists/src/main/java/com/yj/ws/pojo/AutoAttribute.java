package com.yj.ws.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class AutoAttribute {
    private String id;

    private String attributeCategory;

    private Object nameCn;

    private String nameEn;

    private Object memo;

    private String pinyin;

    private String operator;

    private Date operateDate;

    private String lastModifior;

    private Date lastModifiedDate;

    private String parentId;

    private String attributeCode;

    private String vehicleType;

    private String countryCategory;

    private String modelCode;

    private Date createDate;

    private BigDecimal dataFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAttributeCategory() {
        return attributeCategory;
    }

    public void setAttributeCategory(String attributeCategory) {
        this.attributeCategory = attributeCategory;
    }

    public Object getNameCn() {
        return nameCn;
    }

    public void setNameCn(Object nameCn) {
        this.nameCn = nameCn;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public Object getMemo() {
        return memo;
    }

    public void setMemo(Object memo) {
        this.memo = memo;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getAttributeCode() {
        return attributeCode;
    }

    public void setAttributeCode(String attributeCode) {
        this.attributeCode = attributeCode;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getCountryCategory() {
        return countryCategory;
    }

    public void setCountryCategory(String countryCategory) {
        this.countryCategory = countryCategory;
    }

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
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