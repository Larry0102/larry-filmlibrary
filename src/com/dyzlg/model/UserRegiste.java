package com.dyzlg.model;

/**
 * 用户注册Bean
 * larry create on 2014-3-29
 */
public class UserRegiste extends BaseBean {

	private static final long serialVersionUID = 8279065574171630585L;
	private long code;
	private String msg;

	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
