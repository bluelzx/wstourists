package com.yj.ws.util.datasource;


import javax.servlet.ServletContextEvent;



public class DataSourceUtil {
	//private final static Logger log=Logger.getLogger(DataSourceUtil.class);
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

	public void contextInitialized(ServletContextEvent sce) {
		//log.info("====================初始化数据源为mysql");
		MultipleDataSource.setDataSourceKey("dataSource");
	}

}

