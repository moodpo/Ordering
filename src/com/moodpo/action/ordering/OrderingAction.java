package com.moodpo.action.ordering;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.moodpo.common.BaseAction;
import com.moodpo.domain.Dic;
import com.moodpo.domain.Param;
import com.moodpo.domain.Price;
import com.moodpo.service.ordering.IOrderingService;
import com.moodpo.service.param.IParamService;
import com.moodpo.utils.OtherConstants;
import com.moodpo.utils.ResultConstants;

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
	
	
	private Price price;
	private Dic dic;
	private String index;
	
	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public Dic getDic() {
		return dic;
	}

	public void setDic(Dic dic) {
		this.dic = dic;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

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
			return ResultConstants.TODAY_ORDERING;
		}
		// 查询今日菜单列表
		List<Price> prices = orderingServiceImpl.todayOrdering();
		// 查询今日菜单公告
		param = paramServiceImpl.findParamByCode(OtherConstants.CODE_TODAY_FOOD_INFO);
		if(param != null){
			request.setAttribute(OtherConstants.TODAY_ORDERING_INFO, param.getParamDis(
					));
		}
		// 查询设置的订餐份数列表
		String[] numList = {"1","2","3","4","5"};
		// .......
		request.setAttribute(OtherConstants.TODAY_ORDERING, prices);
		request.setAttribute(OtherConstants.NUM_LIST, numList);
		logger.info("init today ordering end.");
		return ResultConstants.TODAY_ORDERING;
	}
	
	/**
	 * 选择今日订餐
	 * @return
	 * @throws Exception
	 */
	public String selectOrdering() throws Exception {
		logger.info("selectOrdering start.");
		// 通过dic查询dic的详细内容 ，通过price id 查询详细内容
		price = orderingServiceImpl.selectDetail(price, dic);
		if(price == null){
			response.getWriter().print(ERROR);
			logger.info("selectOrdering end.");
			return null;
		}
		// 检查之前是否已经选择
		@SuppressWarnings("unchecked")
		Map<Integer,Price> priceMap = (Map<Integer, Price>) session.get(OtherConstants.SELECTED_TODAY_ORDERING);
		if(priceMap == null){
			priceMap = new LinkedHashMap<Integer, Price>();
		}
		Integer current_index = (Integer)session.get(OtherConstants.CURRENT_INDEX);
		if(current_index == null){
			current_index = 0;
		}
		current_index = current_index + 1;
		priceMap.put(current_index, price);
		session.put(OtherConstants.SELECTED_TODAY_ORDERING, priceMap);
		session.put(OtherConstants.CURRENT_INDEX,current_index);
		response.getWriter().print(SUCCESS+":"+current_index);
		logger.info("selectOrdering end.");
		return null;
	}
	
	/**
	 * 取消今日订餐
	 * @return
	 * @throws Exception
	 */
	public String cancelOrdering() throws Exception {
		logger.info("cancelOrdering start. " + index);
		@SuppressWarnings("unchecked")
		Map<Integer,Price> priceMap = (Map<Integer, Price>) session.get(OtherConstants.SELECTED_TODAY_ORDERING);
		priceMap.remove(Integer.parseInt(index));
		session.put(OtherConstants.SELECTED_TODAY_ORDERING, priceMap);
		response.getWriter().print(SUCCESS);
		logger.info("cancelOrdering end.");
		return null;
	}
	
}
