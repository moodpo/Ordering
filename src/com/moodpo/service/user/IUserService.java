package com.moodpo.service.user;

import com.moodpo.domain.User;

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
	public User findUserByEmail(User user);
	
	/**
	 * 添加用户
	 * @param user
	 * @return String id
	 */
	public String addUser(User user);
	
	/**
	 * 用户注册
	 * 1. 检查验证码
	 * 2. 检查邮箱
	 * 3. 发送邮件
	 * 4. 添加用户
	 * @param user
	 * @return 错误信息
	 */
	public String sign(User user);
	
}
