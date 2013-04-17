package com.moodpo.service.order;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.moodpo.domain.Order;
import com.moodpo.domain.Price;
import com.moodpo.domain.User;
import com.moodpo.exception.ServiceException;
import com.moodpo.utils.Pagination;

/**
 * 订单服务类
 * @author xiaoxie
 * @date 2013-4-8 上午08:44:04
 * @email yangxiaoxiehaha@gmail.com
 * @version 1.0
 */
public interface IOrderService {
	
	/**
	 * 生成订单及详细订单
	 * @param priceMap
	 * @param user
	 * @param request
	 * @return
	 * @throws ServiceException
	 */
	public String createOrder(Map<Integer,Price> priceMap,User user) throws ServiceException;
	
	/**
	 * 通过userid查询所有订单 分页
	 * @param order
	 * @return
	 * @throws ServiceException
	 */
	public String queryOrder(Order order, Pagination pagination, HttpServletRequest request) throws ServiceException;
	
	/**
	 * 取消订单
	 * 1.删除订单
	 * 2.删除详细订单
	 * @param order
	 * @return
	 * @throws ServiceException
	 */
	public String cancelOrder(Order order) throws ServiceException;
	
	/**
	 * 分页查询所有order
	 * @param order
	 * @param pagination
	 * @param request
	 * @return
	 * @throws ServiceException
	 */
	public String queryAllOrder(Order order, Pagination pagination, HttpServletRequest request) throws ServiceException;
}
