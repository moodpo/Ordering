package com.moodpo.service.order.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moodpo.dao.detail.IDetailDao;
import com.moodpo.dao.food.IFoodDao;
import com.moodpo.dao.order.IOrderDao;
import com.moodpo.dao.ordering.IOrderingDao;
import com.moodpo.domain.Detail;
import com.moodpo.domain.Dic;
import com.moodpo.domain.Food;
import com.moodpo.domain.Order;
import com.moodpo.domain.Price;
import com.moodpo.domain.User;
import com.moodpo.exception.DBException;
import com.moodpo.exception.ServiceException;
import com.moodpo.service.order.IOrderService;
import com.moodpo.utils.MD5;
import com.moodpo.utils.OtherConstants;
import com.moodpo.utils.Pagination;
import com.moodpo.utils.SqlConstants;

/**
 * 订单服务实现类
 * @author xiaoxie
 * @date 2013-4-8 上午08:44:59
 * @email yangxiaoxiehaha@gmail.com
 * @version 1.0
 */
@Service
@Transactional
public class OrderServiceImpl implements IOrderService{
	
	private static Logger logger = Logger.getLogger(OrderServiceImpl.class);
	
	@Resource
	IOrderDao orderDaoImpl;
	
	@Resource
	IDetailDao detailDaoImpl;
	
	@Resource
	IFoodDao foodDaoImpl;
	
	@Resource
	IOrderingDao orderingDaoImpl;
	
	public String createOrder(Map<Integer, Price> priceMap,User user)
			throws ServiceException {
		if(priceMap == null){
			return OtherConstants.SELECTED_ORDERING_NULL;
		}
		float total = 0f; // 总金额
		for (Map.Entry<Integer, Price> map : priceMap.entrySet()) {
			Price price = map.getValue();
			int num = price.getPriceNum();
			total = total + num*price.getPriceValue();
		}
		Order order = new Order();
		order.setUserID(user.getId());
		order.setOrderTotle(total);
		// 生成订单序列号 防止篡改
		// 用户id+订单金额+订单状态+系统时间
		String orderSeq = MD5.getMD5(user.getId()+total+OtherConstants.STATE_NO)+System.currentTimeMillis();
		order.setOrderSeq(orderSeq);
		order.setOrderState(OtherConstants.STATE_NO);
		try {
			// 向order表中添加一条记录
			String orderID = orderDaoImpl.add(order, SqlConstants.ORDER_ADD);
			Detail detail = new Detail();
			for (Map.Entry<Integer, Price> map : priceMap.entrySet()) {
				Price price = map.getValue();
				detail.setOrderID(orderID);
				detail.setPriceID(price.getId());
				detail.setOrderDetailNum(price.getPriceNum());
				// 添加订单详细
				String detailID = detailDaoImpl.add(detail, SqlConstants.DETAIL_ADD);
				Food food = new Food();
				List<Dic> dics = price.getDics();
				for (Dic dic : dics) {
					food.setDicID(dic.getId());
					food.setOrderDetailID(detailID);
					food.setFoodNum(1);
					// 添加订餐详细
					foodDaoImpl.insert(food, SqlConstants.FOOD_ADD);
				}
			}
		} catch (DBException e) {
			logger.error(OtherConstants.DB_ERROR,e);
			return OtherConstants.DB_ERROR;
		}
		return null;
	}
	
	public String queryOrder(Order order, Pagination pagination, HttpServletRequest request) throws ServiceException {
		// 分页信息
		if(pagination == null){
			pagination = new Pagination();
		}
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put(OtherConstants.CURRENT_USER_ID, order.getUserID());
		int count = 0;
		// 查询总条数
		try {
			count = orderDaoImpl.count(queryMap, SqlConstants.ORDER_COUNT_BY_UID);
		} catch (DBException e) {
			logger.error(OtherConstants.DB_ERROR,e);
			return OtherConstants.DB_ERROR;
		}
		
		if(count == 0){
			logger.info(OtherConstants.HAVE_NO_ORDER);
			return OtherConstants.HAVE_NO_ORDER;
		}
		pagination.setCount(count);
		queryMap.put(OtherConstants.START_ROW, pagination.getStartRow());
		queryMap.put(OtherConstants.END_ROW, pagination.getEndRow());
		
		List<Order> orders = new ArrayList<Order>();
		// 查询订单
		try {
			@SuppressWarnings("rawtypes")
			List list = orderDaoImpl.query(queryMap, SqlConstants.ORDER_QUERY_BY_UID);
			for (Object object : list) {
				Order o = (Order)object;
				// 查询订单详细信息
				@SuppressWarnings("rawtypes")
				List list2 = detailDaoImpl.query(o, SqlConstants.DETAIL_QUERY_BY_ORDERID);
				List<Detail> details = new ArrayList<Detail>();
				for (Object object2 : list2) {
					Detail detail = (Detail)object2;
					Price price = (Price)orderingDaoImpl.find(detail.getPriceID(), SqlConstants.PRICE_FIND_BY_ID);
					price.setDicName(detail.getDicName());
					detail.setPrice(price);
					details.add(detail);
				}
				o.setDetails(details);
				orders.add(o);
			}
		} catch (DBException e) {
			logger.error(OtherConstants.DB_ERROR,e);
			return OtherConstants.DB_ERROR;
		}
		request.setAttribute(OtherConstants.CURRENT_ORDER_LIST, orders);
		request.setAttribute(OtherConstants.PAGE_INFO, pagination);
		return null;
	}

	public String cancelOrder(Order order) throws ServiceException {
		try {
			orderDaoImpl.delete(order, SqlConstants.ORDER_DELETE_BY_ID);
			// 不需要删除 订单详细和订餐关系表 会级联删除
		} catch (DBException e) {
			logger.error(OtherConstants.DB_ERROR,e);
			return OtherConstants.DB_ERROR;
		}
		return null;
	}
	
}
