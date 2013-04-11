package com.moodpo.action.user;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.google.code.kaptcha.Constants;
import com.moodpo.common.BaseAction;
import com.moodpo.domain.User;
import com.moodpo.service.user.IUserService;
import com.moodpo.utils.CookieUtils;
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
	private String newPwd; // 新密码
	private String infoFlag; // 修改个人信息类型(基本信息/修改密码)
	private boolean autoLogin; // 是否自动登录
	
	public boolean isAutoLogin() {
		return autoLogin;
	}

	public void setAutoLogin(boolean autoLogin) {
		this.autoLogin = autoLogin;
	}

	public String getInfoFlag() {
		return infoFlag;
	}

	public void setInfoFlag(String infoFlag) {
		this.infoFlag = infoFlag;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

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
	
	/**
	 * 检查验证码
	 * @param setMsg 是否要设置msg
	 * @return
	 */
	private boolean checkValidateCode(boolean setMsg){
		String code = getValidateCode();
		logger.info("User input validateCode : " + code);
		String kcode = (String) session.get(Constants.KAPTCHA_SESSION_KEY);
		if(kcode != null && kcode.equals(code)){
			return true;
		}
		if(setMsg){
			this.setMsg(OtherConstants.VALIDATE_CODE_ERROR);
		}
		return false;
	}
	
	/**
	 * 用户注册
	 * @return
	 * @throws Exception
	 */
	public String sign() throws Exception {
		logger.info("user sign start.");
		// ============ 检查验证码
		if(this.checkValidateCode(true)){
			// ============== 用户注册
			String sign = userServiceImpl.sign(user);
			if(sign != null){
				// 注册失败，设置页面提示信息
				this.setMsg(sign);
				logger.info("user sign end.");
				return ResultConstants.SIGN_FAIL;
			}
			logger.info("user sign end.");
			return ResultConstants.SIGN_SUCCESS;
		}
		logger.info("user sign end.");
		return ResultConstants.SIGN_FAIL;
	}
	
	
	/**
	 * 用户登录
	 * @return
	 * @throws Exception
	 */
	public String login() throws Exception {
		logger.info("user login start.");
		// ============ 检查验证码
		if(this.checkValidateCode(true)){
			// 调用登录逻辑
			String login = userServiceImpl.login(user,session);
			if(login != null){
				this.setMsg(login);
				logger.info("user login end.");
				return ResultConstants.LOGIN_FAIL;
			}
			// 根据用户的权限转发并初始化页面
			User currUser = (User)session.get(OtherConstants.CURRENT_USER);
			// 是否自动登录
			if(this.isAutoLogin()){
				CookieUtils.addCookie(currUser, response);
				logger.info("user cookie add.");
			}
			// 管理员登录
			if(OtherConstants.ADMIN_GROUP_ID.equals(currUser.getAuth())){
				logger.info("Admin user login system !");
				return ResultConstants.LOGIN_SUCCESS_ADMIN;
			}
			// 普通用户登录
			if(OtherConstants.USER_GROUP_ID.equals(currUser.getAuth())){
				logger.info("A user login system !");
				return ResultConstants.LOGIN_SUCCESS_USER;
			}
		}
		logger.info("user login end.");
		return ResultConstants.LOGIN_FAIL;
	}
	
	/**
	 * 退出
	 * @return
	 * @throws Exception
	 */
	public String logout() throws Exception {
		logger.info("User had logout system !");
		session.clear();
		CookieUtils.cleanCookie(request, response);
		return SUCCESS;
	}
	
	/**
	 * 找回密码
	 * @return
	 * @throws Exception
	 */
	public String findPwd() throws Exception{
		logger.info("user findPwd start.");
		// ============ 检查验证码
		if(this.checkValidateCode(true)){
			// 调用找回密码逻辑
			String findPwd = userServiceImpl.findPwd(user);
			if(findPwd != null){
				this.setMsg(findPwd);
				logger.info("user findPwd end.");
				return ResultConstants.FIND_PWD_FAIL;
			}
			// 清除cookie
			CookieUtils.cleanCookie(request, response);
			logger.info("user findPwd end.");
			return ResultConstants.FIND_PWD_SUCCESS;
		}
		logger.info("user findPwd end.");
		return ResultConstants.FIND_PWD_FAIL;
	}
	
	/**
	 * 修改中文名称
	 * @return
	 * @throws Exception
	 */
	public String alterName() throws Exception{
		this.setInfoFlag(OtherConstants.BASE_INFO);
		logger.info("user alterName start.");
		// 调用修改服务
		String alter = userServiceImpl.alterName(user);
		if(alter != null){
			this.setMsg(alter);
			logger.info("user alterName end.");
			return ResultConstants.ALTER_NAME_FAIL; 
		}
		// 更新缓存的session
		User currUser = (User)session.get(OtherConstants.CURRENT_USER);
		currUser.setLoginName(user.getLoginName());
		session.put(OtherConstants.CURRENT_USER,currUser);
		
		logger.info("user alterName end.");
		return ResultConstants.ALTER_NAME_SUCCESS;
	}
	
	/**
	 * 修改密码
	 * @return
	 * @throws Exception
	 */
	public String alterPwd() throws Exception{
		this.setInfoFlag(OtherConstants.ALERT_PWD);
		logger.info("user alterPwd start.");
		// ============ 检查验证码 不设置msg
		if(this.checkValidateCode(false)){
			// 调用修改服务
			String alter = userServiceImpl.alertPwd(user, newPwd);
			if(alter != null){
				addFieldError(OtherConstants.FIELD_OLDPWD_CODE, OtherConstants.PASSWORD_ERROR);
				logger.info("user alterPwd end.");
				return ResultConstants.ALTER_PWD_FAIL; 
			}
			// 清除cookie
			CookieUtils.cleanCookie(request, response);
			// 退出系统重新登录
			logger.info("User had logout system !");
			session.clear();
			logger.info("user alterPwd end.");
			return ResultConstants.ALTER_PWD_SUCCESS;
		}
		addFieldError(OtherConstants.FIELD_VALIDATE_CODE, OtherConstants.VALIDATE_CODE_ERROR);
		logger.info("user alterPwd end.");
		return ResultConstants.ALTER_PWD_FAIL; 
	}
	
	/**
	 * 通过cookie登录
	 * @return
	 * @throws Exception
	 */
	public String cookieLogin() throws Exception {
		logger.info("user cookieLogin start.");
		user = CookieUtils.getCookie(request);
		if(user != null){
			// 调用登录逻辑
			String login = userServiceImpl.cookieLogin(user,session);
			if(login != null){
				this.setMsg(login);
				// 清除cookie
				CookieUtils.cleanCookie(request, response);
				logger.info("user cookieLogin end.");
				return ResultConstants.LOGIN_FAIL;
			}
			// 根据用户的权限转发并初始化页面
			User currUser = (User)session.get(OtherConstants.CURRENT_USER);
			// 管理员登录
			if(OtherConstants.ADMIN_GROUP_ID.equals(currUser.getAuth())){
				logger.info("Admin user login system !");
				return ResultConstants.LOGIN_SUCCESS_ADMIN;
			}
			// 普通用户登录
			if(OtherConstants.USER_GROUP_ID.equals(currUser.getAuth())){
				logger.info("A user login system !");
				return ResultConstants.LOGIN_SUCCESS_USER;
			}
		}
		// 清除cookie
		CookieUtils.cleanCookie(request, response);
		logger.info("user cookieLogin end.");
		return ResultConstants.LOGIN_FAIL;
	}
}
