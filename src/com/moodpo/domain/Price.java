package com.moodpo.domain;

import java.util.List;

import com.moodpo.common.Model;

/**
 * 类型价目类
 * @author xiaoxie
 * @date 2013-4-4 下午08:55:35
 * @email yangxiaoxiehaha@gmail.com
 * @version 1.0
 */
public class Price extends Model {

	private static final long serialVersionUID = 1L;
	
	private String priceName;
	
	private float priceValue;
	
	private int priceNum;
	
	private String priceState;
	
	private String priceRes;
	
	private List<Dic> dics;
	
	public List<Dic> getDics() {
		return dics;
	}

	public void setDics(List<Dic> dics) {
		this.dics = dics;
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

	public int getPriceNum() {
		return priceNum;
	}

	public void setPriceNum(int priceNum) {
		this.priceNum = priceNum;
	}

	public String getPriceState() {
		return priceState;
	}

	public void setPriceState(String priceState) {
		this.priceState = priceState;
	}

	public String getPriceRes() {
		return priceRes;
	}

	public void setPriceRes(String priceRes) {
		this.priceRes = priceRes;
	}
	
}
