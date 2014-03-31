package com.dyzlg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.dyzlg.base.BaseActivity;
import com.dyzlg.model.NewsBean;

/**
 * 新闻详情页
 * larry create on 2014-3-19
 */
public class NewsDetailsActivity extends BaseActivity implements OnClickListener{
	private ImageView headerLeft;
	private ImageView headerRight;
	private TextView headerTitle, title;
	private NewsBean news;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news_details);
		Intent intent = getIntent();
		news = (NewsBean)intent.getSerializableExtra("news");
		initView();
		setlistener();
	}

	private void initView() {
		// TODO Auto-generated method stub
		headerLeft = (ImageView)findViewById(R.id.header_left);
		headerRight = (ImageView)findViewById(R.id.header_right);
		headerTitle = (TextView)findViewById(R.id.header_title);
		title = (TextView)findViewById(R.id.news_details_title);
		headerTitle.setVisibility(View.GONE);
		title.setText(news.getTitle());
	}
	

	private void setlistener() {
		// TODO Auto-generated method stub
		headerLeft.setOnClickListener(this);
		headerRight.setOnClickListener(this);
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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.header_left:
			finish();
			break;
		case R.id.header_right:
			
			break;
		default:
			break;
		}
	}

}
