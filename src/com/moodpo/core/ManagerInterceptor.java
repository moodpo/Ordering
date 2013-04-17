package com.moodpo.core;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsStatics;

import com.moodpo.domain.User;
import com.moodpo.utils.OtherConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * 管理员拦截器
 * @author xiaoxie
 * @date 2013-4-17 下午01:03:41
 * @email yangxiaoxiehaha@gmail.com
 * @version 1.0
 */
public class ManagerInterceptor implements Interceptor {

	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(ManagerInterceptor.class);
	
	public void destroy() {}

	public void init() {}

	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext ctx = invocation.getInvocationContext();  
        Map<String,Object> session = ctx.getSession();
		User user = (User)session.get(OtherConstants.CURRENT_USER);
		if(user != null && OtherConstants.ADMIN_GROUP_ID.equals(user.getAuth())){
			logger.info("The manager pass ManagerInterceptor.");
			return invocation.invoke();
		}
		
		HttpServletRequest request = (HttpServletRequest)ctx.get(StrutsStatics.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse)ctx.get(StrutsStatics.HTTP_RESPONSE);
		String isAjaxRequest = request.getHeader("x-requested-with");
        // 如果异步请求时Session过期,不作处理
        if ("XMLHttpRequest".equals(isAjaxRequest)) {
        	response.setContentType("text/text");
        	response.setCharacterEncoding("UTF-8");
        	response.getOutputStream().print("Session Timeout.");
        	response.getOutputStream().close();
            logger.info("The AJAX has been interceptor by ManagerInterceptor !");
            return null;
        }
		
		logger.info("The manager has been interceptor by ManagerInterceptor !");
		return Action.NONE;
	}

	
	
}
