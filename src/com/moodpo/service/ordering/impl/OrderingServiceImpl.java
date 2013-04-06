package com.moodpo.service.ordering.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moodpo.dao.dic.IDicDao;
import com.moodpo.dao.ordering.IOrderingDao;
import com.moodpo.domain.Dic;
import com.moodpo.domain.Price;
import com.moodpo.exception.DBException;
import com.moodpo.exception.ServiceException;
import com.moodpo.service.ordering.IOrderingService;
import com.moodpo.utils.OtherConstants;
import com.moodpo.utils.SqlConstants;

/**
 * 订餐服务类实现
 * @author xiaoxie
 * @date 2013-4-4 下午09:18:47
 * @email yangxiaoxiehaha@gmail.com
 * @version 1.0
 */
@Service
@Transactional
public class OrderingServiceImpl implements IOrderingService{
	
	private static Logger logger = Logger.getLogger(OrderingServiceImpl.class);
	
	@Resource
	IOrderingDao orderingDaoImpl;
	
	@Resource
	IDicDao dicDaoImpl;
	
	@SuppressWarnings("unchecked")
	public List<Price> todayOrdering() throws ServiceException{
		logger.info("todayOrdering() start.");
		List<Price> list = new ArrayList<Price>();
		Price price = new Price();
		price.setPriceState(OtherConstants.STATE_YES);
		try {
			@SuppressWarnings("rawtypes")
			List prices = orderingDaoImpl.query(price, SqlConstants.PRICE_QUERY_BY_STATE);
			for (Object object : prices) {
				price = (Price)object;
				Dic dic = new Dic();
				dic.setPriceID(price.getId());
				dic.setDicState(OtherConstants.STATE_YES);
				// 查询菜品字典中此类型的详细信息
				@SuppressWarnings("rawtypes")
				List dics = dicDaoImpl.query(dic, SqlConstants.DIC_QUERY_BY_PRICEID);
				price.setDics(dics);
				list.add(price);
			}
			return list;
		} catch (DBException e) {
			logger.error(OtherConstants.DB_ERROR,e);
		}
		return null;
	}

	public Price selectDetail(Price price, Dic dic) throws ServiceException {
		try {
			int priceNum = price.getPriceNum();
			// 通过dic查询dic的详细内容 
			dic = (Dic)dicDaoImpl.find(dic.getId(), SqlConstants.DIC_FIND_BY_ID);
			// 通过price id 查询详细内容
			price = (Price)orderingDaoImpl.find(price.getId(), SqlConstants.PRICE_FIND_BY_ID);
			if(price != null){
				List<Dic> dics = new ArrayList<Dic>();
				dics.add(dic);
				price.setDics(dics);
				price.setPriceNum(priceNum);
				return price;
			}
		} catch (DBException e) {
			logger.error(OtherConstants.DB_ERROR,e);
		}
		return null;
	}

	
	
}
