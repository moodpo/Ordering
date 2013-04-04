package com.moodpo.core;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * 系统初始化
 * @author xiaoxie
 * @date 2013-4-3 下午09:59:22
 * @email yangxiaoxiehaha@gmail.com
 * @version 1.0
 */
public class InitThread extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void init() throws ServletException {
		
		String path = System.getProperty("webapp.root") 
			+ "WEB-INF/classes/config/sysconfig.properties";
		
		System.out.println("==================系统配置文件路径=======================");
		System.out.println(path);
		
        SysConfig.initConfig(path);
		
	}

}
