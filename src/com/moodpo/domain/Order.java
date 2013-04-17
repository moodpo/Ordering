package com.moodpo.domain;

import java.util.List;

import com.moodpo.common.Model;

/**
 * 订单类
 * @author xiaoxie
 * @date 2013-4-8 上午08:29:11
 * @email yangxiaoxiehaha@gmail.com
 * @version 1.0
 */
public class Order extends Model{
	private static final long serialVersionUID = 1L;
	
	private String userID;
	
	private float orderTotle;
	
	private String orderDate;
	
	private String orderState;
	
	private String orderSeq;
	
	
	
	private List<Detail> details;
	
	private String loginName;
	
	private String email;
	
	private String startDate;
	
	private String endDate;
	
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public List<Detail> getDetails() {
		return details;
	}

	public void setDetails(List<Detail> details) {
		this.details = details;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public float getOrderTotle() {
		return orderTotle;
	}

	public void setOrderTotle(float orderTotle) {
		this.orderTotle = orderTotle;
	}

	public String getOrderDate() {
		if(orderDate != null)
			orderDate = orderDate.substring(5,16);
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public String getOrderSeq() {
		return orderSeq;
	}

	public void setOrderSeq(String orderSeq) {
		this.orderSeq = orderSeq;
	}
	
}
