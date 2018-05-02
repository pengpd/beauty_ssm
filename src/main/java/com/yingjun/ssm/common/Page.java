package com.yingjun.ssm.common;

public class Page {

	// private String start = "0";
	private String page = "1";
	private String limit = "20";
	private int totals = 0;

	public Integer getStart() {
		return Integer.parseInt(String.valueOf((Integer.parseInt(page) - 1) * Integer.parseInt(limit)));
	}

	public Integer getLimit() {
		return Integer.parseInt(limit);
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

//	public static int getTotals() {
//		return SysUtils.parseInt((String) SessionManager.getAttribute(com.leagsoft.dao.Constant.TOTAL_COUNT));
//	}

	/**
	 * 每页记录数
	 * 
	 * @param rows
	 */
	public void setRows(String rows) {
		this.limit = rows;
	}

	/**
	 * 显示第几页
	 * @param pageNo
	 */
	public void setPage(String pageNo) {
		this.page = pageNo;
	}

	public int getTotals() {
		return totals;
	}

	public void setTotals(int totals) {
		this.totals = totals;
	}
}

