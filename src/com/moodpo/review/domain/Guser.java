package com.moodpo.review.domain;

/**
 * 用户和组关系类
 * @author xiaoxie
 * @date 2013-4-2 上午10:50:08
 * @email yangxiaoxiehaha@gmail.com
 * @version 1.0
 */
public class Guser {
	
	private String guserID;
	
	private String groupID;
	
	private String userID;

	public String getGuserID() {
		return guserID;
	}

	public void setGuserID(String guserID) {
		this.guserID = guserID;
	}

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
