package com.moodpo.action.order;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.moodpo.common.BaseAction;
import com.moodpo.domain.Order;
import com.moodpo.domain.Price;
import com.moodpo.domain.User;
import com.moodpo.service.order.IOrderService;
import com.moodpo.utils.OtherConstants;
import com.moodpo.utils.ResultConstants;

/**
 * 订单动作
 * @author xiaoxie
 * @date 2013-4-8 上午08:18:10
 * @email yangxiaoxiehaha@gmail.com
 * @version 1.0
 */
public class OrderAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(OrderAction.class);
	
	@Resource
	IOrderService orderServiceImpl;
	
	/**
	 * 查询参数
	 */
	private Order order;
	
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	/**
	 * 生成订单方法
	 * @return
	 * @throws Exception
	 */
	public String createOrder() throws Exception {
		logger.info("createOrder start.");
		// 获取用户选择的菜品
		@SuppressWarnings("unchecked")
		Map<Integer,Price> priceMap = (Map<Integer, Price>) session.get(OtherConstants.SELECTED_TODAY_ORDERING);
		User user = (User)session.get(OtherConstants.CURRENT_USER);
		// 调用服务层生成订单及详细订单
		String order = orderServiceImpl.createOrder(priceMap, user);
		if(order != null){
			this.setMsg(order);
			logger.info("createOrder end.");
			return ResultConstants.CREATE_ORDER_FAIL;
		}
		session.remove(OtherConstants.SELECTED_TODAY_ORDERING);
		logger.info("createOrder end.");
		return ResultConstants.CREATE_ORDER_SUCCESS;
	}
	
	/**
	 * 分页查询某人的订单 按时间排序
	 * @return
	 * @throws Exception
	 */
	public String queryOrder() throws Exception {
		logger.info("queryOrder start.");
		User user = (User)session.get(OtherConstants.CURRENT_USER);
		order = new Order();
		order.setUserID(user.getId());
		// 调用服务查询某人的所有订单
		String res = orderServiceImpl.queryOrder(order, this.getPagination(), request);
		if(res != null){
			this.setMsg(res);
			logger.info("queryOrder end.");
			return res;
		}
		logger.info("queryOrder end.");
		return ResultConstants.QUERY_ORDER_LIST;
	}
}
