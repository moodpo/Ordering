package com.moodpo.domain;

import java.util.List;

import com.moodpo.common.Model;

/**
 * 订单详细
 * @author xiaoxie
 * @date 2013-4-8 上午08:32:57
 * @email yangxiaoxiehaha@gmail.com
 * @version 1.0
 */
public class Detail extends Model{

	private static final long serialVersionUID = 1L;
	
	private String orderID;
	
	private String priceID;
	
	private int orderDetailNum;
	
	
	// ============ 其它属性
	private Price price;
	private String startTime;
	private String endTime;
	private String orderDate;
	private String dicName;
	private String dicID;
	private String priceName;
	private float priceValue;
	
	private List<Food> foods;
	
	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getDicName() {
		return dicName;
	}

	public void setDicName(String dicName) {
		this.dicName = dicName;
	}

	public String getDicID() {
		return dicID;
	}

	public void setDicID(String dicID) {
		this.dicID = dicID;
	}

	public String getPriceName() {
		return priceName;
	}

	public void setPriceName(String priceName) {
		this.priceName = priceName;
	}

	public float getPriceValue() {
		return priceValue;
	}

	public void setPriceValue(float priceValue) {
		this.priceValue = priceValue;
	}

	public List<Food> getFoods() {
		return foods;
	}

	public void setFoods(List<Food> foods) {
		this.foods = foods;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String getPriceID() {
		return priceID;
	}

	public void setPriceID(String priceID) {
		this.priceID = priceID;
	}

	public int getOrderDetailNum() {
		return orderDetailNum;
	}

	public void setOrderDetailNum(int orderDetailNum) {
		this.orderDetailNum = orderDetailNum;
	}
	
}
