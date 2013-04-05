package com.moodpo.domain;

import com.moodpo.common.Model;

/**
 * 饭菜字典类
 * @author xiaoxie
 * @date 2013-4-4 下午08:59:31
 * @email yangxiaoxiehaha@gmail.com
 * @version 1.0
 */
public class Dic extends Model{

	private static final long serialVersionUID = 1L;
	
	private String dicName;
	
	private String priceID;
	
	private String dicState;

	public String getDicName() {
		return dicName;
	}

	public void setDicName(String dicName) {
		this.dicName = dicName;
	}

	public String getPriceID() {
		return priceID;
	}

	public void setPriceID(String priceID) {
		this.priceID = priceID;
	}

	public String getDicState() {
		return dicState;
	}

	public void setDicState(String dicState) {
		this.dicState = dicState;
	}
	
}
