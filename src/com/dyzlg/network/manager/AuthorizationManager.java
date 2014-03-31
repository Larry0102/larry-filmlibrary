package com.dyzlg.network.manager;

import java.io.InputStream;

import android.content.SharedPreferences;

import com.dyzlg.base.App;
import com.dyzlg.config.Config;
import com.dyzlg.network.HttpResponseListener;
import com.dyzlg.tools.HttpProxy;
import com.dyzlg.tools.Utils;

/**
 * 用户登录退出操作及存储用户登录信息 larry create on 2014-3-29
 */
public class AuthorizationManager {
	private static AuthorizationManager mManager;

	public static AuthorizationManager getInstance() {
		if (mManager == null) {
			mManager = new AuthorizationManager();
		}
		return mManager;
	}

	/**
	 * 用户登陆
	 * 
	 * @param username
	 * @param password
	 * @param listener
	 */
	public void login(final String username, final String password,
			final HttpResponseListener listener) {
		HttpProxy.requestUserValidate(username, password,
				new HttpResponseListener() {

					@Override
					public void succeed(InputStream inStream) {
						saveLoginUser(username, password, 0);
						listener.succeed(inStream);
					}

					@Override
					public void failed(String errMsg) {
						listener.failed(errMsg);
					}
				});
	}

	/**
	 * 注销用户
	 */
	public void logout() {
		// App.getInstance().getActivity().stopService();
		clearLoginUser();
	}

	/**
	 * 保存用户信息
	 * 
	 * @param userName
	 * @param userPwd
	 */
	private void saveLoginUser(String username, String password, int userId) {
		SharedPreferences sharedPreferences = (SharedPreferences) App
				.getInstance().getSharedPreferences(
						Config.Key.SHARED_PREFERENCES_TABLE, 0);
		sharedPreferences
				.edit()
				.putString(Config.Key.SHARED_USER_NAME_KEY,
						Utils.stringToMD5(username)).commit();
		sharedPreferences
				.edit()
				.putString(Config.Key.SHARED_USER_PASSWORD_KEY,
						Utils.stringToMD5(password)).commit();
	}

	/**
	 * 清除登入用户信息(用户登录帐号除外，在登录时记录上次登录的帐号)
	 */
	public void clearLoginUser() {
		SharedPreferences sharedPreferences = (SharedPreferences) App
				.getInstance().getSharedPreferences(
						Config.Key.SHARED_PREFERENCES_TABLE, 0);
		// sharedPreferences.edit().remove(AppConstants.SHARED_USER_NAME_KEY).commit();
		sharedPreferences.edit().remove(Config.Key.SHARED_USER_PASSWORD_KEY)
				.commit();
		// sharedPreferences.edit().remove(Config.Key.SHARED_IS_LOGIN_KEY).commit();
	}
}
