package com.moodpo.service.ordering;

import java.util.List;

import com.moodpo.domain.Dic;
import com.moodpo.domain.Price;
import com.moodpo.exception.ServiceException;

/**
 * 订餐服务类
 * @author xiaoxie
 * @date 2013-4-4 下午09:17:44
 * @email yangxiaoxiehaha@gmail.com
 * @version 1.0
 */
public interface IOrderingService {
	/**
	 * 今日订餐列表
	 * @return 详细的订餐列表
	 */
	public List<Price> todayOrdering() throws ServiceException;
	
	/**
	 * 查询用户选择的菜品详细信息
	 * 通过dic查询dic的详细内容 ，
	 * 通过price id 查询详细内容
	 * @return
	 * @throws ServiceException
	 */
	public Price selectDetail(Price price, Dic dic) throws ServiceException;
}
