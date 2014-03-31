package com.dyzlg;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dyzlg.base.BaseActivity;

/**
 * 搜索页 
 * larry create on 2014-3-16
 */
public class SearchActivity extends BaseActivity {
	private ImageView headerLeft;
	private ImageView headerRight;
	private TextView title;
	private EditText searchEdit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		initView();
		setListener();
	}
	
	private void setListener() {
		headerLeft.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		searchEdit.setOnKeyListener(searchOnkeyListener);
	}
	
	private OnKeyListener searchOnkeyListener = new OnKeyListener() {
		@Override
		public boolean onKey(View v, int keyCode, KeyEvent event) {
			// TODO Auto-generated method stub
			if (keyCode == KeyEvent.KEYCODE_ENTER) {// 修改回车键功能
				if (event.getAction() == KeyEvent.ACTION_UP) {
					gotoSearch();
				}
			}
			return false;
		}
	};

	private void initView() {
		headerLeft = (ImageView)findViewById(R.id.header_left);
		headerRight = (ImageView)findViewById(R.id.header_right);
		title = (TextView)findViewById(R.id.header_title);
		searchEdit = (EditText)findViewById(R.id.search_edit);
		headerRight.setVisibility(View.INVISIBLE);
		title.setText(getResources().getString(R.string.title_user_center));
	}
	
	/**
	 * 搜索方法
	 */
	private void gotoSearch(){
		
	}

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
