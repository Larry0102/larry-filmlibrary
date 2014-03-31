package com.dyzlg.model;

/**
 * 用户信息 larry create on 2014-3-29
 */
public class UserInfo extends BaseBean {

	private static final long serialVersionUID = 2732411334212848705L;

	private long id;
	private String username;
	private String password;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
