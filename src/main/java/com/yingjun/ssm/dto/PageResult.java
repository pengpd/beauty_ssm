package com.yingjun.ssm.dto;

import java.util.List;

public class PageResult {
	
	private int code ;
	
	private String msg;
	
	private List<?> data;
	
	private int count;
	
	public PageResult(List<?> data,int count){
		this.data = data;
		this.count = count;
	}

	public PageResult(int code, String msg, List<?> data, int count) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
		this.count = count;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	

}
