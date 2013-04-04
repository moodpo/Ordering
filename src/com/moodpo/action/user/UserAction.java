package com.moodpo.action.user;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.google.code.kaptcha.Constants;
import com.moodpo.common.BaseAction;
import com.moodpo.domain.User;
import com.moodpo.service.user.IUserService;
import com.moodpo.utils.OtherConstants;
import com.moodpo.utils.ResultConstants;

/**
 * 
 * @author xiaoxie
 * @date 2013-4-2 上午11:11:24
 * @email yangxiaoxiehaha@gmail.com
 * @version 1.0
 */
public class UserAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(UserAction.class);
	
	@Resource
	private IUserService userServiceImpl;
	
	private User user;
	private String validateCode;
	
	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

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
	 * 用户注册
	 * @return
	 * @throws Exception
	 */
	public String sign() throws Exception {
		logger.info("user sign start");
		// ============ 检查验证码
		String code = getValidateCode();
		logger.info("User input validateCode : " + code);
		String kcode = (String) session.get(Constants.KAPTCHA_SESSION_KEY);
		if(kcode != null && kcode.equals(code)){
			logger.info("validate code success");
			// ============== 用户注册
			String sign = userServiceImpl.sign(user);
			if(sign != null){
				// 注册失败，设置页面提示信息
				this.setMsg(sign);
				logger.info("validate email: " + user.getEmail() + " fail!");
				logger.info("user sign end.");
				return ResultConstants.SIGN_FAIL;
			}
			logger.info("user sign end.");
			return ResultConstants.SIGN_SUCCESS;
		}
		this.setMsg(OtherConstants.VALIDATE_CODE_ERROR);
		logger.info("validate code fail!");
		logger.info("user sign end.");
		return ResultConstants.SIGN_FAIL;
	}
	
}
