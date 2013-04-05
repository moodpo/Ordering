package com.moodpo.action.ordering;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.moodpo.common.BaseAction;
import com.moodpo.domain.Param;
import com.moodpo.domain.Price;
import com.moodpo.service.ordering.IOrderingService;
import com.moodpo.service.param.IParamService;
import com.moodpo.utils.OtherConstants;

/**
 * 订餐动作
 * @author xiaoxie
 * @date 2013-4-4 下午08:42:48
 * @email yangxiaoxiehaha@gmail.com
 * @version 1.0
 */
public class OrderingAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(OrderingAction.class);
	
	@Resource
	IOrderingService orderingServiceImpl;
	
	@Resource
	IParamService paramServiceImpl;
	
	/**
	 * 今日订餐页
	 * @return
	 * @throws Exception
	 */
	public String todayOrdering() throws Exception {
		logger.info("init today ordering start.");
		// 查询订餐开关是否开启
		Param param = paramServiceImpl.findParamByCode(OtherConstants.CODE_START_ORDERING);
		if(!OtherConstants.STATE_ORDERING_YES.equals(param.getParamValue())){ //订餐未开始
			request.setAttribute(OtherConstants.TODAY_ORDERING_INFO, OtherConstants.TODAY_ORDERING_STOP);
			logger.info("init today ordering end.");
			return null;
		}
		// 查询今日菜单列表
		List<Price> prices = orderingServiceImpl.todayOrdering();
		// 查询今日菜单公告
		param = paramServiceImpl.findParamByCode(OtherConstants.CODE_TODAY_FOOD_INFO);
		if(param != null){
			request.setAttribute(OtherConstants.TODAY_ORDERING_INFO, param.getParamDis());
		}
		// 查询设置的订餐份数列表
		String[] numList = {"1","2","3","4","5"};
		// .......
		request.setAttribute(OtherConstants.TODAY_ORDERING, prices);
		request.setAttribute(OtherConstants.NUM_LIST, numList);
		logger.info("init today ordering end.");
		return null;
	}
	
	/**
	 * 选择今日订餐
	 * @return
	 * @throws Exception
	 */
	public String selectTodayOrdering() throws Exception {
		
		
		
		return null;
	}
	
	
	
}
