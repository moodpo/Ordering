package com.moodpo.utils;

/**
 * 分页类
 * @author xiaoxie
 * @date 2013-4-8 下午07:37:34
 * @email yangxiaoxiehaha@gmail.com
 * @version 1.0
 */
public class Pagination {
	
	public Pagination() {
		this.count = 0;
		this.currentPage = 1;
		this.pageSize = 5;
	}
	
	/**
	 * 总条数
	 */
	private int count = 0;
	
	/**
	 * 每页条数
	 */
	private int pageSize = 5; 
	
	/**
	 * 当前页
	 */
	private int currentPage = 1;
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	/**
	 * 总页数
	 * @return
	 */
	public int getPageCount() {
		if(this.getCount()%this.getPageSize() == 0){
			return this.getCount()/this.getPageSize();
		}else{
			return this.getCount()/this.getPageSize() + 1;
		}
	}
	/**
	 * 起始行数
	 * @return
	 */
	public int getStartRow() {
		return (this.getCurrentPage()-1)*this.pageSize + 1;
	}
	/**
	 * 结束行数
	 * @return
	 */
	public int getEndRow() {
		return this.getCurrentPage()*this.getPageSize();
	}
	
	
	public String toString() {
		StringBuffer sb = new StringBuffer("");
		int currentPage = this.getCurrentPage();
		
		sb.append("<div class='well mypagination'>");
		sb.append("<ul><li class='page-first'>");
		if(currentPage > 1){ // 当前不是首页
			sb.append("<a href='#' onclick='toPage('first','pagination-form')'>首页</a>");
		} else { // 当前是首页
			
		}
		
		sb.append("</li>");
		
		
		
		
		
		
		return null;
	}
	
}
