package com.moodpo.review.domain;

import java.util.List;

/**
 * 用户类
 * @author xiaoxie
 * @date 2013-4-2 上午10:46:53
 * @email yangxiaoxiehaha@gmail.com
 * @version 1.0
 */
public class User {
	
	private String userID;
	
	private String email;
	
	private String loginName;
	
	private String userPWD;
	
	private String userState;
	
	private String userCode;
	
	private float moneyValue;
	
	private List<Group> groups;
	
	private String auth;
	
	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserPWD() {
		return userPWD;
	}

	public void setUserPWD(String userPWD) {
		this.userPWD = userPWD;
	}

	public String getUserState() {
		return userState;
	}

	public void setUserState(String userState) {
		this.userState = userState;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public float getMoneyValue() {
		return moneyValue;
	}

	public void setMoneyValue(float moneyValue) {
		this.moneyValue = moneyValue;
	}
	
}
