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
	 * 通过id查询price
	 */
	public static String PRICE_FIND_BY_ID = "findPriceById";
	
	/**
	 * 通过priceId查询dic
	 */
	public static String DIC_QUERY_BY_PRICEID = "queryDicByPriceId";
	
	/**
	 * 通过id查询dic
	 */
	public static String DIC_FIND_BY_ID = "findDicById";
	
	/**
	 * 通过code查询param
	 */
	public static String  PARAM_FIND_BY_CODE ="findParamByCode";
	
	/**
	 * 添加order
	 */
	public static String ORDER_ADD = "addOrder";
	
	/**
	 * 通过id查询order
	 */
	public static String ORDER_FIND_BY_ID = "findOrderById";
	
	/**
	 * 通过uid查询Order分页
	 */
	public static String ORDER_QUERY_BY_UID = "queryOrderByUId";
	
	/**
	 * 通过uid查询总条数
	 */
	public static String ORDER_COUNT_BY_UID = "countOrderByUId";
	
	/**
	 * 通过id删除order
	 */
	public static String ORDER_DELETE_BY_ID = "deleteOrderById";
	
	/**
	 * 添加detail
	 */
	public static String DETAIL_ADD = "addDetail";
	
	/**
	 * 通过order id查询detail
	 */
	public static String DETAIL_QUERY_BY_ORDERID = "queryDetailByOrderId";
	
	/**
	 * 添加food
	 */
	public static String FOOD_ADD = "addFood";
	
	
}
