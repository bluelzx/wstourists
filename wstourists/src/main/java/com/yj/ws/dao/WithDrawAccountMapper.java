package com.yj.ws.dao;

import java.util.List;
import java.util.Map;

public interface WithDrawAccountMapper {
	public List<Map<String, Object>> selectLast(Long userId); 
}
