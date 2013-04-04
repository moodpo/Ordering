package com.moodpo.domain;

import com.moodpo.common.Model;

/**
 * 充值金额
 * @author xiaoxie
 * @date 2013-4-4 下午02:52:57
 * @email yangxiaoxiehaha@gmail.com
 * @version 1.0
 */
public class Money extends Model{

	private static final long serialVersionUID = 1L;
	
	private float moneyValue;
	
	private String userID;

	public float getMoneyValue() {
		return moneyValue;
	}

	public void setMoneyValue(float moneyValue) {
		this.moneyValue = moneyValue;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}
	
}
