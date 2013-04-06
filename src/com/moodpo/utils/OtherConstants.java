package com.moodpo.utils;

/**
 * 其他常量类
 * @author xiaoxie
 * @date 2013-4-3 下午08:40:01
 * @email yangxiaoxiehaha@gmail.com
 * @version 1.0
 */
public class OtherConstants {
	/***********************页面相关字段****************************/	
	/**
	 * 修改个人信息页面标识
	 */
	public static String BASE_INFO = "0";
	
	public static String ALERT_PWD = "1";
	
	/**
	 * 验证码
	 */
	public static String FIELD_VALIDATE_CODE = "validate_code";
	
	/**
	 * 旧密码
	 */
	public static String FIELD_OLDPWD_CODE = "oldpwd";
	
	/***********************页面提示信息****************************/	
	/**
	 * 您输入的邮箱已存在！
	 */
	public static String EMAIL_EXIST = "您输入的邮箱已存在！";
	
	/**
	 * 您输入的邮箱不存在！
	 */
	public static String EMAIL_NOT_EXIST = "您输入的邮箱不存在或已锁定！";
	
	/**
	 * 验证码输入错误！
	 */
	public static String VALIDATE_CODE_ERROR = "验证码输入错误！";
	
	/**
	 * 恭喜，注册成功，请到注册邮箱中收取邮件以获取登录信息。
	 */
	public static String SIGN_SUCCESS = "恭喜，注册成功，请到注册邮箱中收取邮件以获取登录信息。";
	
	/**
	 * 对不起，您输入的密码不正确！
	 */
	public static String PASSWORD_ERROR = "对不起，您输入的密码不正确！";
	
	/**
	 * 数据库连接异常，请检查网络或联系管理员！
	 */
	public static String DB_ERROR = "数据库连接异常，请检查网络或联系管理员！";
	
	/**
	 * 未知错误，请联系管理员！
	 */
	public static String UNKNOW_ERROR = "未知错误，请联系管理员！";
	
	/**
	 * 今日订餐还未开始，请稍候！
	 */
	public static String TODAY_ORDERING_STOP = " 今日订餐还未开始，请稍候！";
	
	
	/***************************************************/
	/**
	 * 管理员用户组ID
	 */
	public static String ADMIN_GROUP_ID = "2";
	
	/**
	 * 普通用户组ID
	 */
	public static String USER_GROUP_ID = "1";
	
	/**
	 * 状态可用
	 */
	public static String STATE_YES = "00";
	
	/**
	 * 状态不可用
	 */
	public static String STATE_NO = "01";
	
	/**
	 * 订餐是否已开启 ParamCode
	 */
	public static String CODE_START_ORDERING = "START_ORDER";
	
	/**
	 * 今日菜品公告 ParamCode
	 */
	public static String CODE_TODAY_FOOD_INFO = "TODAY_FOOD";
	
	public static String STATE_ORDERING_NO = "00";
	
	public static String STATE_ORDERING_YES = "01";
	
	/***************************************************/
	/**
	 * 已登录用户缓存session的key
	 */
	public static String CURRENT_USER = "current_user_obj";
	
	/**
	 * 今日菜单列表
	 */
	public static String TODAY_ORDERING = "today_ordering_list";
	
	/**
	 * 今日菜单公告
	 */
	public static String TODAY_ORDERING_INFO = "today_ordering_info";
	
	/**
	 * 已选择的菜单列表
	 */
	public static String SELECTED_TODAY_ORDERING = "selected_today_ordering_list";
	
	/**
	 * 订餐页面选择份数列表
	 */
	public static String NUM_LIST = "num_list";
	
	/**
	 * 订餐页面当前提交索引
	 */
	public static String CURRENT_INDEX = "current_index";
	
}
