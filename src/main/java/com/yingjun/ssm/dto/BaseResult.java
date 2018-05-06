package com.yingjun.ssm.dto;

import java.io.Serializable;
import java.util.List;
/**
 * 
 * @author yingjun
 *
 * ajax 请求的返回类型封装JSON结果
 */
public class BaseResult implements Serializable {


	private static final long serialVersionUID = -4185151304730685014L;

	private boolean success;

    private List<?> data;

    private String error;
    
    private String code;
    
    private String msg;
    
    private int count;

    public BaseResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public BaseResult(boolean success, List<?> data) {
        this.success = success;
        this.data = data;
    }

	public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
