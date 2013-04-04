package com.moodpo.domain;

import com.moodpo.common.Model;

/**
 * 用户组类
 * @author xiaoxie
 * @date 2013-4-2 上午10:49:27
 * @email yangxiaoxiehaha@gmail.com
 * @version 1.0
 */
public class Group extends Model{
	private static final long serialVersionUID = 1L;

	private String groupName;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
}
