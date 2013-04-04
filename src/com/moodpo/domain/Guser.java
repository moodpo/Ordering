package com.moodpo.domain;

import com.moodpo.common.Model;

/**
 * 用户和组关系类
 * @author xiaoxie
 * @date 2013-4-2 上午10:50:08
 * @email yangxiaoxiehaha@gmail.com
 * @version 1.0
 */
public class Guser extends Model{
	
	private static final long serialVersionUID = 1L;

	private String groupID;
	
	private String userID;

	public String getGroupID() {
		return groupID;
	}

	public void setGroupID(String groupID) {
		this.groupID = groupID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}
	
}
