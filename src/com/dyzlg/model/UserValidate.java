package com.dyzlg.model;

/**
 * 用户验证Bean
 * larry create on 2014-3-29
 */
public class UserValidate extends BaseBean {

	private static final long serialVersionUID = 4962036028231426746L;
	private long code;
	private long userid;

	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

}
