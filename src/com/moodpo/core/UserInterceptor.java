package com.moodpo.core;

import java.util.Map;

import org.apache.log4j.Logger;

import com.moodpo.domain.User;
import com.moodpo.utils.OtherConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * 普通用户拦截器
 * @author xiaoxie
 * @date 2013-4-5 下午08:50:14
 * @email yangxiaoxiehaha@gmail.com
 * @version 1.0
 */
public class UserInterceptor implements Interceptor{
	
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(UserInterceptor.class);
	
	public void destroy() {}

	public void init() {
		
	}

	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext ctx = invocation.getInvocationContext();  
        Map<String,Object> session = ctx.getSession();
		User user = (User)session.get(OtherConstants.CURRENT_USER);
		if(user != null){
			logger.info("The user pass UserInterceptor.");
			return invocation.invoke();
		}
		logger.info("The user has been interceptor by UserInterceptor !");
		return Action.LOGIN;
	}
	
	
	
	
}
