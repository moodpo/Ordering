package com.moodpo.service.user.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moodpo.core.SysConfig;
import com.moodpo.dao.group.IGroupDao;
import com.moodpo.dao.guser.IGuserDao;
import com.moodpo.dao.money.IMoneyDao;
import com.moodpo.dao.user.IUserDao;
import com.moodpo.domain.Group;
import com.moodpo.domain.Guser;
import com.moodpo.domain.Money;
import com.moodpo.domain.User;
import com.moodpo.exception.DBException;
import com.moodpo.exception.ServiceException;
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
	
	@Resource
	IGroupDao groupDaoImpl;
	
	@Resource
	IMoneyDao moneyDaoImpl;
	
	public User findUserByEmail(User user) throws ServiceException {
		try {
			@SuppressWarnings("rawtypes")
			List list = userDaoImpl.query(user, SqlConstants.USER_FIND_BY_EMAIL);
			if(list != null && list.size() > 0){
				return (User)list.get(0);
			}
			return null;
		} catch (DBException e) {
			logger.error(OtherConstants.DB_ERROR,e);
			throw new ServiceException(OtherConstants.DB_ERROR);
		}
	}
	
	public String addUser(User user) throws ServiceException {
		try {
			return userDaoImpl.add(user, SqlConstants.USER_ADD);
		} catch (DBException e) {
			logger.error(OtherConstants.DB_ERROR,e);
			throw new ServiceException(OtherConstants.DB_ERROR);
		}
	}
	
	public String sign(User user) throws ServiceException {
		// 添加后缀
		String email = user.getEmail() + SysConfig.getConfig(SysConfig.EMAIL_SUFFIX);
		// 加密密码
		Long random=System.currentTimeMillis();
		@SuppressWarnings("static-access")
		String randomStr = random.toHexString(random);// 随机数
		String password = MD5.getMD5(email + randomStr);// 密码串
		logger.info("################## randomStr : " + randomStr);
		
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
		String userID = null;
		try {
			u.setUserState(OtherConstants.STATE_YES);
			userID = userDaoImpl.add(u, SqlConstants.USER_ADD);
		} catch (DBException e) {
			logger.error(OtherConstants.DB_ERROR,e);
			return OtherConstants.DB_ERROR;
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
			logger.error(OtherConstants.DB_ERROR,e);
			return OtherConstants.DB_ERROR;
		}
		return null;
	}

	public String login(User user, Map<String,Object> session) throws ServiceException {
		// 添加后缀
		String email = user.getEmail() + SysConfig.getConfig(SysConfig.EMAIL_SUFFIX);
		String password = user.getUserPWD();
		String relPwd = MD5.getMD5(email + password);
		User u = new User();
		u.setEmail(email);
		u.setUserPWD(password);
		u.setUserState(OtherConstants.STATE_YES);
		// 查询邮箱
		User fu = this.findUserByEmail(u);
		// 邮箱不存在
		if(fu == null){
			logger.info("Email is not exist !");
			return OtherConstants.EMAIL_NOT_EXIST;
		}
		// 密码错误
		if(!fu.getUserPWD().equals(relPwd)){
			logger.info("Password is error !");
			return OtherConstants.PASSWORD_ERROR;
		}
		// 开始登录过程
		// 1.查询所属组列表
		String userID = fu.getId();
		try {
			@SuppressWarnings("rawtypes")
			List list = groupDaoImpl.query(fu, SqlConstants.GROUP_QUERY_BY_UID);
			List<Group> groups = new ArrayList<Group>();
			int temp = 0;
			for (Object object : list) {
				Group group = (Group)object;
				// groupID 的大小决定了权限的高低
				int groupID = Integer.parseInt(group.getId());
				if(groupID > 0){
					temp = groupID;
				}
				groups.add(group);
			}
			fu.setGroups(groups);
			fu.setAuth(String.valueOf(temp));
		} catch (DBException e) {
			logger.error(OtherConstants.DB_ERROR,e);
			return OtherConstants.DB_ERROR;
		}
		// 2.查询充值金额
		Money money = new Money();
		money.setUserID(userID);
		
		try {
			@SuppressWarnings("rawtypes")
			List list = moneyDaoImpl.query(money, SqlConstants.MONEY_FIND_BY_UID);
			if(list != null && list.size() > 0){
				money = (Money)list.get(0);
				fu.setMoneyValue(money.getMoneyValue());
			}else{
				fu.setMoneyValue(0);
			}
		} catch (DBException e) {
			logger.error(OtherConstants.DB_ERROR,e);
			return OtherConstants.DB_ERROR;
		}
		// 3.缓存到session
		session.put(OtherConstants.CURRENT_USER, fu);
		return null;
	}
	
	
	public String findPwd(User user) throws ServiceException{
		// 添加后缀
		String email = user.getEmail() + SysConfig.getConfig(SysConfig.EMAIL_SUFFIX);
		// 查询邮箱
		User u = new User();
		u.setEmail(email);
		u.setUserState(OtherConstants.STATE_YES);
		User fu = this.findUserByEmail(u);
		// 邮箱不存在
		if(fu == null){
			logger.info("Email is not exist !");
			return OtherConstants.EMAIL_NOT_EXIST;
		}
		// 重新生成加密密码
		Long random=System.currentTimeMillis();
		@SuppressWarnings("static-access")
		String randomStr = random.toHexString(random);// 随机数
		String password = MD5.getMD5(email + randomStr);// 密码串
		logger.info("################## randomStr : " + randomStr);
		fu.setUserPWD(password);
		// 更新数据
		try {
			userDaoImpl.update(fu, SqlConstants.USER_UPDATE_BY_ID);
		} catch (DBException e) {
			logger.error(OtherConstants.DB_ERROR,e);
			return OtherConstants.DB_ERROR;
		}
		// 发送邮件
		
		
		return null;
	}

	public String alterName(User user) throws ServiceException {
		try {
			userDaoImpl.update(user, SqlConstants.USER_UPDATE_BY_ID);
		} catch (DBException e) {
			logger.error(OtherConstants.DB_ERROR,e);
			return OtherConstants.DB_ERROR;
		}
		return null;
	}

	public String alertPwd(User user, String newPwd) throws ServiceException {
		// 通过id查询user
		try {
			User fu = (User)userDaoImpl.find(user.getId(), SqlConstants.USER_FIND_BY_ID);
			String password = user.getUserPWD();
			String email = fu.getEmail();
			password = MD5.getMD5(email + password);
			if(!fu.getUserPWD().equals(password)){
				// 旧密码输入错误
				logger.info("Password is error !");
				return OtherConstants.PASSWORD_ERROR;
			}
			// 修改密码
			String endPwd = MD5.getMD5(email + newPwd);
			fu.setUserPWD(endPwd);
			userDaoImpl.update(fu, SqlConstants.USER_UPDATE_BY_ID);
		} catch (DBException e) {
			logger.error(OtherConstants.DB_ERROR,e);
			return OtherConstants.DB_ERROR;
		}
		return null;
	}
}
