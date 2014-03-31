package com.dyzlg;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.dyzlg.base.BaseActivity;
import com.dyzlg.config.Config;

/**
 * 配置界面/更多
 * larry create on 2014-3-16
 */
public class SettingActivity extends BaseActivity {
	private ImageView headerLeft;
	private ImageView headerRight;
	private TextView title;
	private TextView alarmSet, clearCache, appraise, tellFriends, feedbackIdea, version, versionInfo;
	private RadioGroup radioGroup;
	private RadioButton poster, list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		initView();
		initSetting();
		setListener();
	}

	private void initView() {
		headerLeft = (ImageView)findViewById(R.id.header_left);
		headerRight = (ImageView)findViewById(R.id.header_right);
		title = (TextView)findViewById(R.id.header_title);
		alarmSet = (TextView)findViewById(R.id.setting_alarm_button);
		clearCache = (TextView)findViewById(R.id.setting_clear);
		appraise = (TextView)findViewById(R.id.setting_appraise);
		tellFriends = (TextView)findViewById(R.id.setting_tell_friends);
		feedbackIdea = (TextView)findViewById(R.id.setting_feedback_idea);
		version = (TextView)findViewById(R.id.setting_version);
		versionInfo = (TextView)findViewById(R.id.setting_version_info);
		radioGroup = (RadioGroup)findViewById(R.id.setting_radio);
		poster = (RadioButton)findViewById(R.id.setting_radio_poster);
		list = (RadioButton)findViewById(R.id.setting_radio_list);
		headerRight.setVisibility(View.INVISIBLE);
		title.setText(getResources().getString(R.string.title_user_center));
	}

	private void initSetting(){
		if(Config.Setting.moviePreviewType){
			list.setChecked(true);
		} else {
			poster.setChecked(true);
		}
		if(Config.Setting.isOpenAlarm){
			alarmSet.setBackgroundResource(R.drawable.icon_setting_alarm_on);
		} else {
			alarmSet.setBackgroundResource(R.drawable.icon_setting_alarm_off);
		}
	}
	
	private void setListener() {
		radioGroup.setOnCheckedChangeListener(radioGroupChange);
		headerLeft.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		alarmSet.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Config.Setting.isOpenAlarm = !Config.Setting.isOpenAlarm;
				if(Config.Setting.isOpenAlarm){
					alarmSet.setBackgroundResource(R.drawable.icon_setting_alarm_on);
				} else {
					alarmSet.setBackgroundResource(R.drawable.icon_setting_alarm_off);
				}
			}
		});
	}
	
	private RadioGroup.OnCheckedChangeListener radioGroupChange = new OnCheckedChangeListener() {
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
			if(checkedId == poster.getId()){
				poster.setChecked(true);
				Config.Setting.moviePreviewType = false;
			} else if (checkedId == list.getId()){
				list.setChecked(true);
				Config.Setting.moviePreviewType = true;
			}
		}
	};

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}
