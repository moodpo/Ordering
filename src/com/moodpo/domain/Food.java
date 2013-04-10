package com.moodpo.domain;

import com.moodpo.common.Model;

/**
 * 食品关系类
 * @author xiaoxie
 * @date 2013-4-8 上午08:37:08
 * @email yangxiaoxiehaha@gmail.com
 * @version 1.0
 */
public class Food extends Model{

	private static final long serialVersionUID = 1L;
	
	private String orderDetailID;
	
	private String dicID;
	
	private int foodNum;

	public String getOrderDetailID() {
		return orderDetailID;
	}

	public void setOrderDetailID(String orderDetailID) {
		this.orderDetailID = orderDetailID;
	}

	public String getDicID() {
		return dicID;
	}

	public void setDicID(String dicID) {
		this.dicID = dicID;
	}

	public int getFoodNum() {
		return foodNum;
	}

	public void setFoodNum(int foodNum) {
		this.foodNum = foodNum;
	}
	
}
