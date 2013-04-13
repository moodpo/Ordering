package com.moodpo.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.moodpo.domain.User;

/**
 * cookie工具类
 * @author xiaoxie
 * @date 2013-4-11 下午04:02:54
 * @email yangxiaoxiehaha@gmail.com
 * @version 1.0
 */
public class CookieUtils {
	
	private static Logger logger = Logger.getLogger(CookieUtils.class);
	
	/**
	 * 获取cookie
	 * @param request
	 * @return User
	 */
	public static User getCookie(HttpServletRequest request){
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			return null;
		}
		for (Cookie cookie : cookies) {
			if(OtherConstants.USER_COOKIE.equals(cookie.getName())){
				String value = cookie.getValue();
        		if(value != null && value != ""){
        			User user = new User();
        			String[] split = value.split(OtherConstants.COOKIE_SPLIT);
        			user.setEmail(split[0]);
        			user.setUserPWD(split[1]);
        			return user;
        		}
			}
		}
		
		return null;
	}
	
	/**
	 * 是否有登录cookie
	 * @param request
	 * @return
	 */
	public static boolean isHaveCookie(HttpServletRequest request){
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			return false;
		}
		for (Cookie cookie : cookies) {
			if(OtherConstants.USER_COOKIE.equals(cookie.getName())){
				logger.info("Cookie Name : " + cookie.getName());
				String value = cookie.getValue();
        		if(value != null && value != ""){
        			return true;
        		}
			}
		}
		return false;
	}
	
	/**
	 * 清除cookie
	 * @param request
	 * @param response
	 */
	public static void cleanCookie(HttpServletRequest request, HttpServletResponse response){
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			return;
		}
		Cookie cookie = new Cookie(OtherConstants.USER_COOKIE, null);
		cookie.setValue("");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
	}
	
	/**
	 * 添加cookie
	 * @param user
	 * @param response
	 */
	public static void addCookie(User user, HttpServletResponse response){
		Cookie cookie = new Cookie(OtherConstants.USER_COOKIE,
				user.getEmail() + OtherConstants.COOKIE_SPLIT
						+ user.getUserPWD());
		cookie.setMaxAge(60 * 60 * 24 * 7); // 保存一周
		cookie.setPath("/");
		response.addCookie(cookie);
	}
	
}
