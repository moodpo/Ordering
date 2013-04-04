package com.moodpo.common;

import java.io.Serializable;

/**
 * 实体的基类
 * @author xiaoxie
 * @date 2013-4-3 下午05:01:34
 * @email yangxiaoxiehaha@gmail.com
 * @version 1.0
 */
public class Model implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
