package com.yj.ws.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AutoConstituteVo extends AutoConstitute implements Serializable{

	private static final long serialVersionUID = 3586035508916190321L;
	
	private List<AutoConstituteVo> childAutoConstituteVos = new ArrayList<AutoConstituteVo>();

	public List<AutoConstituteVo> getChildAutoConstituteVos() {
		return childAutoConstituteVos;
	}

	public void setChildAutoConstituteVos(
			List<AutoConstituteVo> childAutoConstituteVos) {
		this.childAutoConstituteVos = childAutoConstituteVos;
	}


	
	
	

}
