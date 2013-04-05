package com.moodpo.core;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.moodpo.domain.User;
import com.moodpo.utils.OtherConstants;

/**
 * 安全过滤器
 * @author xiaoxie
 * @date 2013-4-5 下午09:40:29
 * @email yangxiaoxiehaha@gmail.com
 * @version 1.0
 */
public class UserFilter implements Filter {
	
	private static Logger logger = Logger.getLogger(UserFilter.class);
	
	private static String IS_OPEN = "IS_OPEN";
	
	private static String INDEX_PAGE = "INDEX_PAGE";
	
	private static String LOGIN_PAGE = "LOGIN_PAGE";
	
	private static String SIGN_PAGE = "SIGN_PAGE";
	
	private static String HELP_PAGE = "HELP_PAGE";
	
	private static String FINDPWD_PAGE = "FINDPWD_PAGE";
	
	private static String IS_OPEN_VALUE;
	
	private static String INDEX_PAGE_VALUE;
	
	private static String LOGIN_PAGE_VALUE;
	
	private static String SIGN_PAGE_VALUE;
	
	private static String HELP_PAGE_VALUE;
	
	private static String FINDPWD_PAGE_VALUE;
	
	public void destroy() {
		
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
		// 在web.xml中可配置参数
		IS_OPEN_VALUE = fConfig.getInitParameter(IS_OPEN);
		INDEX_PAGE_VALUE = fConfig.getInitParameter(INDEX_PAGE);
		LOGIN_PAGE_VALUE = fConfig.getInitParameter(LOGIN_PAGE);
		SIGN_PAGE_VALUE = fConfig.getInitParameter(SIGN_PAGE);
		HELP_PAGE_VALUE = fConfig.getInitParameter(HELP_PAGE);
		FINDPWD_PAGE_VALUE = fConfig.getInitParameter(FINDPWD_PAGE);
	}
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		if(!"true".equals(IS_OPEN_VALUE)){
			chain.doFilter(request, response);
			return;
		}
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		String url = req.getServletPath();
		logger.info("Request url : " + url);
		
		if(url.indexOf(INDEX_PAGE_VALUE) > -1 || url.indexOf(LOGIN_PAGE_VALUE) > -1
				|| url.indexOf(SIGN_PAGE_VALUE) > -1 || url.indexOf(HELP_PAGE_VALUE) > -1
				|| url.indexOf(FINDPWD_PAGE_VALUE) > -1){
			chain.doFilter(request, response);
			return;
		}
		
		User user = (User)req.getSession().getAttribute(OtherConstants.CURRENT_USER);
		if(user == null){
			 String isAjaxRequest = req.getHeader("x-requested-with");
             // 如果异步请求时Session过期,不作处理
             if ("XMLHttpRequest".equals(isAjaxRequest)) {
            	 res.setContentType("text/text");
            	 res.setCharacterEncoding("UTF-8");
            	 res.getOutputStream().print("Session Timeout.");
            	 res.getOutputStream().close();
                 logger.info("XMLHttpRequest be filter!");
                 return;
             }
			logger.info("The user be filter!");
			req.getRequestDispatcher(LOGIN_PAGE_VALUE).forward(req, res);
			return;
		}
		chain.doFilter(request, response);
	}
}
