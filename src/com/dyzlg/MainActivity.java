package com.dyzlg;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import cn.sharesdk.framework.ShareSDK;

import com.dyzlg.base.BaseActivity;
import com.dyzlg.config.Config;

/**
 * 首页
 * 
 * @author larry
 */
public class MainActivity extends BaseActivity implements OnClickListener {
	private TextView movie, vip, search, news, more;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initDeviceInfo();
		initView();
		setListener();
		ShareSDK.initSDK(this);		// 初始化分享SDK
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		ShareSDK.stopSDK(this);		// 结束分享SDK的统计功能并释放资源
	}

	private void initView() {
		movie = (TextView) findViewById(R.id.main_movie);
		vip = (TextView) findViewById(R.id.main_vip);
		search = (TextView) findViewById(R.id.main_search);
		news = (TextView) findViewById(R.id.main_news);
		more = (TextView) findViewById(R.id.main_more);
	}

	private void setListener() {
		movie.setOnClickListener(this);
		vip.setOnClickListener(this);
		search.setOnClickListener(this);
		news.setOnClickListener(this);
		more.setOnClickListener(this);
	}

	// 初始化设备信息
	private void initDeviceInfo() {
		DisplayMetrics metric = new DisplayMetrics();
		this.getWindowManager().getDefaultDisplay().getMetrics(metric);
		Config.DeviceInfo.screenHeight = metric.heightPixels;
		Config.DeviceInfo.screenWidth = metric.widthPixels;
		Config.DeviceInfo.density = metric.density;
		Config.DeviceInfo.densityDpi = metric.densityDpi;
		Config.DeviceInfo.scaledDensity = metric.scaledDensity;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.main_movie:
			intent.setClass(this, MovieActivity.class);
			break;
		case R.id.main_vip:
			intent.setClass(this, LoginActivity.class);
			break;
		case R.id.main_search:
			intent.setClass(this, SearchActivity.class);
			break;
		case R.id.main_news:
			intent.setClass(this, NewsCenterActivity.class);
			break;
		case R.id.main_more:
			intent.setClass(this, SettingActivity.class);
			break;
		}
		startActivity(intent);
	}

}
