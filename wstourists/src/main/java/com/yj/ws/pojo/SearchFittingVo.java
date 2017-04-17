package com.yj.ws.pojo;

import com.yj.ws.util.code.PageUtil;


public class SearchFittingVo {

	private String autoConstituteId;
	private String keyword;
	private String fittingMerchantId;
	private String brandId;
	private String seriesId;
	private String seriesSonId;
	private String modelId;
	private String searchKey;
	private String regionId;
	private PageUtil pageUtil;
	
	
	public PageUtil getPageUtil() {
		return pageUtil;
	}
	public void setPageUtil(PageUtil pageUtil) {
		this.pageUtil = pageUtil;
	}
	public String getRegionId() {
		return regionId;
	}
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
	public String getAutoConstituteId() {
		return autoConstituteId;
	}
	public void setAutoConstituteId(String autoConstituteId) {
		this.autoConstituteId = autoConstituteId;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getFittingMerchantId() {
		return fittingMerchantId;
	}
	public void setFittingMerchantId(String fittingMerchantId) {
		this.fittingMerchantId = fittingMerchantId;
	}
	public String getBrandId() {
		return brandId;
	}
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
	public String getSeriesId() {
		return seriesId;
	}
	public void setSeriesId(String seriesId) {
		this.seriesId = seriesId;
	}
	public String getSeriesSonId() {
		return seriesSonId;
	}
	public void setSeriesSonId(String seriesSonId) {
		this.seriesSonId = seriesSonId;
	}
	public String getModelId() {
		return modelId;
	}
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}
	public String getSearchKey() {
		return searchKey;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	
	
	
}

