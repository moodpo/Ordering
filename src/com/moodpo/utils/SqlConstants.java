package com.moodpo.utils;

/**
 * sql ID 常量类
 * @author xiaoxie
 * @date 2013-4-3 下午04:13:38
 * @email yangxiaoxiehaha@gmail.com
 * @version 1.0
 */
public class SqlConstants {
	/**
	 * 添加用户
	 */
	public static String USER_ADD = "addUser";
	
	/**
	 * 通过email查询用户
	 */
	public static String USER_FIND_BY_EMAIL = "findUserByEmail";
	
	/**
	 * 通过Id查询用户
	 */
	public static String USER_FIND_BY_ID = "findUserById";
	
	/**
	 * 添加用户和组关系
	 */
	public static String GUSER_ADD = "addGuser";
	
	/**
	 * 通过uid查询用户组
	 */
	public static String GROUP_QUERY_BY_UID = "queryGroupByUId";
	
	/**
	 * 通过uid查询money
	 */
	public static String MONEY_FIND_BY_UID = "findMoneyByUId";
	
	/**
	 * 通过uid更新user
	 */
	public static String USER_UPDATE_BY_ID = "updateUserById";
	
	/**
	 * 通过state查询price
	 */
	public static String PRICE_QUERY_BY_STATE = "queryPriceByState";
	
	/**
	 * 通过priceId查询dic
	 */
	public static String DIC_QUERY_BY_PRICEID = "queryDicByPriceId";
	
	/**
	 * 通过code查询param
	 */
	public static String  PARAM_FIND_BY_CODE ="findParamByCode";
}
