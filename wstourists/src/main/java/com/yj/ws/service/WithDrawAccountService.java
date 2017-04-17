package com.yj.ws.service;

import java.util.List;
import java.util.Map;

public interface WithDrawAccountService {
	public List<Map<String, Object>> selectLast(Long userId);
}
