package com.dyzlg.base;

import android.app.Application;
import android.content.Context;

/**
 * 单例的Application类
 * 
 * larry create on 2014-3-14
 */
public class App extends Application {
	private static App instance;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		instance = this;
	}

	public static App getInstance() {
		return instance;
	}

	public static Context getAppContext() {
		return getInstance().getApplicationContext();
	}
	
}
