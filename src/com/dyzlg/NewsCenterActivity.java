package com.dyzlg;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dyzlg.adapter.NewsCenterAdapter;
import com.dyzlg.base.BaseActivity;
import com.dyzlg.model.NewsBean;
import com.dyzlg.network.HttpResponseListener;
import com.dyzlg.tools.HttpProxy;
import com.dyzlg.tools.IOUtil;
import com.dyzlg.tools.JsonReader;
import com.lidroid.xutils.util.LogUtils;

/**
 * 新闻中心 larry create on 2014-3-16
 */
public class NewsCenterActivity extends BaseActivity {
	private ImageView headerLeft;
	private ImageView headerRight;
	private TextView title;
	private ListView mListView;
	private ArrayList<NewsBean> news;
	private int page = 1;
	private final int limit = 20;
	private NewsCenterAdapter mAdapter;

	private int scrolledX;
	private int scrolledY;

	@SuppressLint("HandlerLeak")
	@SuppressWarnings("unchecked")
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				if (page == 1) {
					news.clear();
				}
				news.addAll((ArrayList<NewsBean>) msg.obj);
				mAdapter.notifyDataSetChanged();
				mListView.scrollTo(scrolledX, scrolledY);
				break;

			default:
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);
		news = new ArrayList<NewsBean>();
		initView();
		setListener();
		getDataFromNet();
	}

	private void setListener() {
		headerLeft.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		mAdapter = new NewsCenterAdapter(this, news);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(new NewsCenterOnItemClickListener());
		mListView.setOnScrollListener(new OnScrollListener() {

			/**
			 * 滚动状态改变时调用
			 */
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// 不滚动时保存当前滚动到的位置
				if (scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
					// if (currentMenuInfo != null) {
					scrolledX = mListView.getScrollX();
					scrolledY = mListView.getScrollY();
					// }
				}
			}

			/**
			 * 滚动时调用
			 */
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
			}
		});
	}

	private void initView() {
		headerLeft = (ImageView) findViewById(R.id.header_left);
		headerRight = (ImageView) findViewById(R.id.header_right);
		title = (TextView) findViewById(R.id.header_title);
		mListView = (ListView) findViewById(R.id.news_list);
		headerRight.setVisibility(View.INVISIBLE);
		title.setText(getResources().getString(R.string.title_news_center));
	}

	private class NewsCenterOnItemClickListener implements OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.setClass(NewsCenterActivity.this, NewsDetailsActivity.class);
			intent.putExtra("news", news.get(position));
			startActivity(intent);
		}
	}

	private void getDataFromNet() {
		HttpProxy.requestNewsList(page, limit, new HttpResponseListener() {

			@Override
			public void succeed(InputStream inStream) {
				// TODO Auto-generated method stub
				String string = IOUtil.convertInputStreamToString(inStream);
				Message msg = new Message();
				msg.what = 0;
				try {
					msg.obj = JsonReader.parseNewsListJSONObject(URLDecoder.decode(string, "utf-8"));
					System.out.println("-----aaa-----" + URLDecoder.decode(string, "utf-8"));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				handler.sendMessage(msg);
			}

			@Override
			public void failed(String errMsg) {
				// TODO Auto-generated method stub
				Log.d("larry", errMsg);
			}
		});
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
