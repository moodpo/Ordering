package com.moodpo.domain;

import com.moodpo.common.Model;

/**
 * 系统参数
 * @author xiaoxie
 * @date 2013-4-5 下午02:41:53
 * @email yangxiaoxiehaha@gmail.com
 * @version 1.0
 */
public class Param extends Model{

	private static final long serialVersionUID = 1L;
	
	private String paramCode;
	
	private String paramName;
	
	private String paramDis;
	
	private String paramValue;

	public String getParamCode() {
		return paramCode;
	}

	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamDis() {
		return paramDis;
	}

	public void setParamDis(String paramDis) {
		this.paramDis = paramDis;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	
}
