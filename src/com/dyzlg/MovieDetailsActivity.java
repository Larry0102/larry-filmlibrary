package com.dyzlg;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dyzlg.base.BaseActivity;
import com.dyzlg.model.MovieBean;
import com.dyzlg.network.HttpResponseListener;
import com.dyzlg.tools.HttpProxy;
import com.dyzlg.tools.IOUtil;
import com.dyzlg.tools.JsonReader;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.util.LogUtils;

/**
 * 近期热映详情页
 * larry create on 2014-3-19
 */
public class MovieDetailsActivity extends BaseActivity implements OnClickListener{
	private ImageView headerLeft;
	private ImageView headerRight;
	private TextView title;
	private ImageView img;	//封面
	private View pic1, pic2, pic3;	//片花
	private Button collect;		//喜欢
	private Button remind;		//提醒
	private TextView director;		//导演
	private TextView starring;		//主演
	private TextView type;			//类型
	private TextView country;		//制片国家/地区
	private TextView language;		//语言
	private TextView length;		//片长
	private TextView showtime;		//放映
	private TextView cinema;		//场地
	private TextView intro;			//简介
	private TextView more;			//更多
	private MovieBean movie;
	
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				BitmapUtils bitmapUtils = new BitmapUtils(MovieDetailsActivity.this);
				// 加载网络图片
				bitmapUtils.display(img, movie.getTitleImageUrl());
				for(int i=0; movie.getAppImages() != null && i < movie.getAppImages().size(); i++){
					switch (i) {
					case 0:
						bitmapUtils.display(pic1, movie.getAppImages().get(i));
						break;
					case 1:
						bitmapUtils.display(pic2, movie.getAppImages().get(i));
						break;
					case 2:
						bitmapUtils.display(pic3, movie.getAppImages().get(i));
						break;
					}
					if(i > 2) break;
				}
				break;
			case 4:
				Toast.makeText(MovieDetailsActivity.this, msg.obj.toString(), Toast.LENGTH_SHORT).show();
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
		setContentView(R.layout.activity_movie_details);
		Intent intent = getIntent();
		movie = (MovieBean)intent.getSerializableExtra("movie");
		initView();
		setListener();
		getDataFromNet();
	}

	private void initView() {
		// TODO Auto-generated method stub
		headerLeft = (ImageView)findViewById(R.id.header_left);
		headerRight = (ImageView)findViewById(R.id.header_right);
		title = (TextView)findViewById(R.id.header_title);
		img = (ImageView)findViewById(R.id.movie_details_cover_img);
		pic1 = (View)findViewById(R.id.movie_details_pic1);
		pic2 = (View)findViewById(R.id.movie_details_pic2);
		pic3 = (View)findViewById(R.id.movie_details_pic3);
		more = (TextView)findViewById(R.id.movie_details_intro_more);
		intro = (TextView)findViewById(R.id.movie_details_intro);
		title.setText(movie.getName());
		more.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				intro.setMaxLines(10000);
				intro.invalidate();
			}
		});
	}

	private void setListener() {
		// TODO Auto-generated method stub
		headerLeft.setOnClickListener(this);
		headerRight.setOnClickListener(this);
		pic1.setOnClickListener(this);
		pic2.setOnClickListener(this);
		pic3.setOnClickListener(this);
	}
	
	private void getDataFromNet(){
		HttpProxy.requestMovieDetails(movie.getId(), new HttpResponseListener() {
			@Override
			public void succeed(InputStream inStream) {
				String string = IOUtil.convertInputStreamToString(inStream);
				Message msg = new Message();
				msg.what = 0;
				try {
					msg.obj = JsonReader.parseMovieJSONObject(URLDecoder.decode(string, "utf-8"));
					System.out.println("-----movieDetails-----"+ URLDecoder.decode(string, "utf-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				handler.sendMessage(msg);
			}
			
			@Override
			public void failed(String errMsg) {
				Log.d("larry", errMsg);
				Message msg = new Message();
				msg.what = 4;
				msg.obj = errMsg;
				handler.sendMessage(msg);
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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.header_left:
			finish();
			break;
		case R.id.header_right:
			
			break;
		case R.id.movie_details_pic1:
		case R.id.movie_details_pic2:
		case R.id.movie_details_pic3:
			Intent intent = new Intent(MovieDetailsActivity.this, PictureViewerActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}
	
}
