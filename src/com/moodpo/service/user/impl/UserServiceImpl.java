package com.moodpo.service.user.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moodpo.core.SysConfig;
import com.moodpo.dao.guser.IGuserDao;
import com.moodpo.dao.user.IUserDao;
import com.moodpo.domain.Guser;
import com.moodpo.domain.User;
import com.moodpo.exception.DBException;
import com.moodpo.service.user.IUserService;
import com.moodpo.utils.MD5;
import com.moodpo.utils.OtherConstants;
import com.moodpo.utils.SqlConstants;

/**
 * 用户服务类实现
 * @author xiaoxie
 * @date 2013-4-3 下午05:14:51
 * @email yangxiaoxiehaha@gmail.com
 * @version 1.0
 */

@Service
@Transactional
public class UserServiceImpl implements IUserService{
	
	private static Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Resource
	IUserDao userDaoImpl;
	
	@Resource
	IGuserDao guserDaoImpl;
	
	@SuppressWarnings("rawtypes")
	public User findUserByEmail(User user) {
		try {
			List list = userDaoImpl.query(user, SqlConstants.USER_FIND_BY_EMAIL);
			logger.info("Find user by email end !");
			if(list != null && list.size() > 0){
				return (User)list.get(0);
			}
		} catch (DBException e) {
			logger.error("Find user by email fail !",e);
		}
		return null;
	}
	
	public String addUser(User user) {
		String id = null;
		try {
			id = userDaoImpl.add(user, SqlConstants.USER_ADD);
		} catch (DBException e) {
			logger.error("User add fail !",e);
			return null;
		}
		logger.info("User add success, it's id : " + id);
		return id;
	}
	
	/**
	 * 1.添加邮箱后缀
	 * 2.生成并加密密码
	 * 3.检查邮箱是否被注册
	 * 4.发送注册邮件
	 * 5.添加用户
	 * 6.判断用户是否为管理员
	 * 7.添加用户和组关系
	 */
	public String sign(User user) {
		// 添加后缀
		String email = user.getEmail() + SysConfig.getConfig(SysConfig.EMAIL_SUFFIX);
		// 加密密码
		Long random=System.currentTimeMillis();
		@SuppressWarnings("static-access")
		String randomStr = random.toHexString(random);// 随机数
		String password = MD5.getMD5(email + randomStr);// 密码串
		
		logger.info("randomStr : " + randomStr);
		
		User u = new User();
		u.setEmail(email);
		u.setLoginName(user.getLoginName().toString());
		u.setUserPWD(password);
		// 检查邮箱是否已注册
		User fu = this.findUserByEmail(u);
		if(fu != null ){
			logger.info("Email is exist !");
			return OtherConstants.EMAIL_EXIST;
		}
		// 发送邮件
		
		
		// 添加用户
		String userID = this.addUser(u);
		if(userID == null){
			return OtherConstants.SIGN_FAIL;
		}
		// 添加到用户和组关系表中
		Guser guser = new Guser();
		guser.setUserID(userID);
		String adminEmail = SysConfig.getConfig(SysConfig.ADMIN_EMAIL);
		if(adminEmail.equals(user.getEmail())){// 管理员
			guser.setGroupID(OtherConstants.ADMIN_GROUP_ID);
		}else{
			guser.setGroupID(OtherConstants.USER_GROUP_ID);
		}
		try {
			guserDaoImpl.insert(guser, SqlConstants.GUSER_ADD);
		} catch (DBException e) {
			logger.error("Guser add fail !",e);
			return OtherConstants.SIGN_FAIL;
		}
		return null;
	}

}
