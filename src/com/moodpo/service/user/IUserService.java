package com.moodpo.service.user;

import java.util.Map;

import com.moodpo.domain.User;
import com.moodpo.exception.ServiceException;

/**
 * user service
 * @author xiaoxie
 * @date 2013-4-3 下午05:14:43
 * @email yangxiaoxiehaha@gmail.com
 * @version 1.0
 */
public interface IUserService {
	
	/**
	 * 通过邮箱查询用户
	 * @param user
	 * @return
	 */
	public User findUserByEmail(User user) throws ServiceException;
	
	/**
	 * 添加用户
	 * @param user
	 * @return String id
	 */
	public String addUser(User user) throws ServiceException;
	
	/**
	 * 用户注册
	 * 1. 检查验证码
	 * 2. 检查邮箱
	 * 3. 发送邮件
	 * 4. 添加用户
	 * @param user
	 * @return 错误信息
	 */
	public String sign(User user) throws ServiceException;
	
	/**
	 * 用户登录
	 * 1. 查询邮箱
	 * 2. 检查密码
	 * 3. 初始化登录
	 * @param usr
	 * @return
	 */
	public String login(User user, Map<String,Object> session) throws ServiceException;
	
	/**
	 * 找回密码
	 * 1. 查询邮箱
	 * 2. 重置密码
	 * 3. 发送邮件
	 * @param user
	 * @return
	 * @throws ServiceException
	 */
	public String findPwd(User user) throws ServiceException;
	
	/**
	 * 修改中文名
	 * 1. 修改
	 * @param user
	 * @return
	 * @throws ServiceException
	 */
	public String alterName(User user) throws ServiceException;
	
	/**
	 * 修改密码
	 * @param user
	 * @return
	 * @throws ServiceException
	 */
	public String alertPwd(User user, String newPwd) throws ServiceException;
}
