package com.moodpo.review.action;

import com.moodpo.review.domain.User;
import com.moodpo.review.utils.ResultConstants;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author xiaoxie
 * @date 2013-4-2 上午11:11:24
 * @email yangxiaoxiehaha@gmail.com
 * @version 1.0
 */
public class UserAction extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	
	private User user;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public String execute() throws Exception {
		
		return super.execute();
	}
	
	/**
	 * 登录
	 * @return
	 * @throws Exception
	 */
	public String login() throws Exception {
		
		return SUCCESS;
	}
	
	/**
	 * 退出
	 * @return
	 * @throws Exception
	 */
	public String logout() throws Exception {
		
		
		return SUCCESS;
	}
	
	/**
	 * 注册
	 * @return
	 * @throws Exception
	 */
	public String sign() throws Exception {
		
		
		
		
		
		return ResultConstants.SIGN_SUCCESS;
	}
	
}
