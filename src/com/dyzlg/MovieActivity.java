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
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dyzlg.adapter.MovieListAdapter;
import com.dyzlg.adapter.MoviePosterAdapter;
import com.dyzlg.base.BaseActivity;
import com.dyzlg.config.Config;
import com.dyzlg.model.MovieBean;
import com.dyzlg.network.HttpResponseListener;
import com.dyzlg.tools.HttpProxy;
import com.dyzlg.tools.IOUtil;
import com.dyzlg.tools.JsonReader;
import com.dyzlg.view.GemelView;
import com.dyzlg.view.GemelView.GemelViewOnClickListener;
import com.lidroid.xutils.util.LogUtils;

/**
 * 电影近期热映 larry create on 2014-3-16
 */
public class MovieActivity extends BaseActivity implements OnClickListener {
	private RelativeLayout titleLayout;
	private ImageView headerLeft;
	private ImageView headerRight;
	private RelativeLayout posterLayout, listLayout;
//	private ImageView image, imageReflection, imageAnim;// poster模式下的Image和其映象
	private ViewPager mViewPager;
	private MoviePosterAdapter mMoviePosterAdapater;
	private ListView mListView;
	private MovieListAdapter mMovieListAdapter;
	private RelativeLayout footerLayout;
	private TextView movieName, movieNameAttach, date, time1, time2, time3;
	private GemelView title;
	private ArrayList<MovieBean> movies, movies2;//近期热映、排片表
	private int pageRecent = 1;
	private int pageList = 1;
	private final int limit = 20;

	private String[] pics = {
			"http://img02.nduoa.com/apk/531/531420/0.watermark.jpg",
			"http://10010app.cn/ires/360/images/v2/mobi/adm/devplatform/snap/20120312/1331552039487.jpg",
			"http://img.159.com/desk/user/2012/5/5/Jiker201246162714187.jpg",
			"http://img.159.com/desk/user/2012/3/10/Jiker201226224041149.jpg",
			"http://pic.159.com/desk/user/2012/12/24/Jiker2012111192312984.jpg",

			"http://cdn.image.market.hiapk.com/data/upload/2013/03_12/11/201303121134214866.jpg",
			"http://img.159.com/desk/user/2012/11/15/Jiker2012104112237421.jpg",
			"http://img.pconline.com.cn/images/upload/upc/tx/wallpaper/1206/29/c1/12171465_1340966371366.jpg",
			"http://cdn2.image.apk.gfan.com/asdf/PImages/2013/3/14/514813_292216041-3f73-4542-bff9-6d572bbe7e2c.jpg",
			"http://static.nduoa.com/apk/34/34669/3.jpg",

			"http://img.159.com/desk/user/2012/3/10/Jiker201226224238305.jpg",
			"http://www.tuku123.com/uploadfile88/2013/0109/20130109060307832.jpg",
			"http://stimgcn4.s-msn.com/msnportal/wp/2013/06/24/641277b1-000f-4659-9479-5cb32e1a717e.jpg",
			"http://pic.159.com/desk/user/2012/10/17/Jiker2012938651140.jpg",
			"http://pic.159.com/desk/user/2012/12/24/Jiker201211119165915.jpg",

			"http://img4.duitang.com/uploads/item/201302/22/20130222194820_e3xHZ.thumb.600_0.jpeg",
			"http://cdn.image.market.hiapk.com/data/upload/2012/09_12/12/201209121224229584.jpg",
			"http://cdn2.image.apk.gfan.com/asdf/PImages/2013/3/26/524006_21bae38c8-7992-4791-b66e-0a33ef9e704d.jpg",
			"http://pic.7kk.com/images/9/b2/78699_0.jpg",
			"http://apps.qudong.com/uploads/image/201210/2012101610094.jpg",

			"http://i3.shouyou.itc.cn/2012/a/2012/10/10/and_pic_jcbz_20120726_023.jpg",
			"http://image.hiapk.com/pic/2011/08/31/and_pic_bzqingchun20110831_1.jpg",
			"http://img.pconline.com.cn/images/upload/upc/tx/wallpaper/1306/25/c1/22555006_1372150279307.jpg",
			"http://cdn2.image.apk.gfan.com/asdf/PImages/2012/11/1/404487_2d16e26b4-8465-498d-9135-2adadaf0709d.jpg",
			"http://cdn.image.market.hiapk.com/data/upload/2012/05_02/201205021644261221.jpg",

			"http://u5.mm-img.com/rs/res1/21/2013/01/29/a384/839/24839384/picture1480x8009425727925.jpg",
			"http://img.159.com/desk/user/2011/11/11/Jiker2011105151511562.jpg",
			"http://pic.159.com/desk/user/2012/12/24/Jiker2012111192311984.jpg",
			"http://img02.nduoa.com/apk/412/412819/2.watermark.jpg",
			"http://img.pconline.com.cn/images/upload/upc/tx/wallpaper/1206/05/c1/11884414_1338882182371.jpg" };

	@SuppressLint("HandlerLeak")
	@SuppressWarnings("unchecked")
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case 0://list模式列表
				if(MovieListAdapter.hasTimeline){//排片表	
					if(pageList == 1){
						movies2.clear();
					}
					movies2.addAll((ArrayList<MovieBean>)msg.obj);
					mMovieListAdapter.notifyDataSetChanged(movies2);
				} else {//近期热映
					if(pageRecent == 1){
						movies.clear();
					}
					movies.addAll((ArrayList<MovieBean>)msg.obj);
					if(Config.Setting.moviePreviewType){
						mMovieListAdapter.notifyDataSetChanged(movies);
					} else {
						mMoviePosterAdapater.notifyDataSetChanged();
					}
				}
				
				break;
			case 1:
				
				break;
			case 4:
				Toast.makeText(MovieActivity.this, msg.obj.toString(), Toast.LENGTH_SHORT).show();
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
		setContentView(R.layout.activity_movie);
		movies = new ArrayList<MovieBean>();
		movies2 = new ArrayList<MovieBean>();
		initView();
		setlistener();
		getDataFromNet(false);
	}

	private void initView() {
		headerLeft = (ImageView) findViewById(R.id.header_left);
		headerRight = (ImageView) findViewById(R.id.header_right);
		titleLayout = (RelativeLayout) findViewById(R.id.header_title);
		posterLayout = (RelativeLayout) findViewById(R.id.movie_type_poster_layout);
		listLayout = (RelativeLayout) findViewById(R.id.movie_type_list_layout);
		mListView = (ListView) findViewById(R.id.movie_list);
		mViewPager = (ViewPager) findViewById(R.id.movie_viewpager);
		footerLayout = (RelativeLayout) findViewById(R.id.movie_footer_layout);
		title = new GemelView(this, titleLayout);
		headerRight.setImageResource(R.drawable.select_type_list_poster);
		
		movieName = (TextView)findViewById(R.id.movie_footer_name);
		date = (TextView)findViewById(R.id.movie_footer_date);
		initPreviewType();
	}
	
	private void initPreviewType() {
		if (Config.Setting.moviePreviewType) {
			headerRight.setImageLevel(0);
			posterLayout.setVisibility(View.GONE);
			listLayout.setVisibility(View.VISIBLE);
			if(MovieListAdapter.hasTimeline){
				mMovieListAdapter = new MovieListAdapter(this, movies2);
			} else {				
				mMovieListAdapter = new MovieListAdapter(this, movies);
			}
			mListView.setAdapter(mMovieListAdapter);
		} else {
			headerRight.setImageLevel(1);
			posterLayout.setVisibility(View.VISIBLE);
			listLayout.setVisibility(View.GONE);
			mMoviePosterAdapater = new MoviePosterAdapter(this, movies);
			mViewPager.setAdapter(mMoviePosterAdapater);
		}
	}

	private void setlistener() {
		headerLeft.setOnClickListener(this);
		headerRight.setOnClickListener(this);
		footerLayout.setOnClickListener(this);
		title.setOnGemelClickListener(new GemelViewOnClickListener() {
			@Override
			public void rightClick() {
				posterLayout.setVisibility(View.GONE);
				listLayout.setVisibility(View.VISIBLE);
				MovieListAdapter.hasTimeline = true;
				if (!Config.Setting.moviePreviewType && mMovieListAdapter == null) {
					mMovieListAdapter = new MovieListAdapter(MovieActivity.this, movies2);
					mListView.setAdapter(mMovieListAdapter);
				} else {
					mMovieListAdapter.notifyDataSetChanged(movies2);
				}
			}

			@Override
			public void leftClick() {
				// TODO Auto-generated method stub
				MovieListAdapter.hasTimeline = false;
				if (!Config.Setting.moviePreviewType) {
					posterLayout.setVisibility(View.VISIBLE);
					listLayout.setVisibility(View.GONE);
					if (mMoviePosterAdapater == null) {
						mMoviePosterAdapater = new MoviePosterAdapter(MovieActivity.this, movies);
						mViewPager.setAdapter(mMoviePosterAdapater);
					}
				} else {
					mMovieListAdapter.notifyDataSetChanged(movies);
				}
			}
		});
		mListView.setOnItemClickListener(new MovieOnItemListener());
		mViewPager.setOnPageChangeListener(new MovieOnPageChangeListener());
	}
	
	private class MovieOnPageChangeListener implements OnPageChangeListener {
		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			System.out.println("onPageScrollStateChanged" + arg0);
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
			System.out.println("onPageScrolled" + arg0 + " ---- " + arg1 + " --arg2-- " + arg2);
			if(movies.size() > 0 && movies.get(arg0)!=null){				
				movieName.setText(movies.get(arg0).getName());
				date.setText(movies.get(arg0).getShowTime());
			}
		}

		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
			System.out.println("onPageScrollStateChanged " + arg0);
		}
		
	}

	private class MovieOnItemListener implements OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			gotoMovieDetails(position);
			if (MovieListAdapter.hasTimeline) {
				MovieListAdapter.currentSelect = position;
				mMovieListAdapter.notifyDataSetChanged(movies2);
			}
		}
	}

	private void gotoMovieDetails(int position) {
		Intent intent = new Intent();
		intent.setClass(MovieActivity.this, MovieDetailsActivity.class);
		intent.putExtra("movie", movies.get(position));
		startActivity(intent);
	}

	// 请求网络数据
	private void getDataFromNet(boolean hasTimeline) {
//		if(hasTimeline){//========排片表==========//
			HttpProxy.requestMovieList(pageList, limit, new HttpResponseListener() {
				@Override
				public void succeed(InputStream inStream) {
					// TODO Auto-generated method stub
					String string = "[{\"sum\":\"608\",\"data\":[{\"name\":\"妗冭姳娉ｈ璁� The Peach Girl\",\"filmid\":\"6\",\"showtime\":\"2010-11-18  \",\"titleimage\":\"http://www.cfa.gov.cn/s/images/bf3dd64e-9382-4d63-97a5-edfd7f8e4b82.jpg\"},{\"name\":\"鏂板コ鎬� New Women\",\"filmid\":\"8\",\"showtime\":\"2010-11-12  \",\"titleimage\":\"http://www.cfa.gov.cn/s/images/dd625163-a681-42c2-b491-bafbe4c1a257.jpg\"}]}]";
					Message msg = new Message();
					msg.what = 0;
					try {
						msg.obj = JsonReader.parseMovieListJSONObject(URLDecoder.decode(string, "utf-8"));
						System.out.println("-----aaa-----"+ URLDecoder.decode(string, "utf-8"));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					handler.sendMessage(msg);
				}
				
				@Override
				public void failed(String errMsg) {
					// TODO Auto-generated method stub
					Log.d("larry", errMsg);
					Message msg = new Message();
					msg.what = 4;
					msg.obj = errMsg;
					handler.sendMessage(msg);
				}
			});
//		} else {//========近期热映=========//
			HttpProxy.requestMovieRecentList(pageRecent, limit,new HttpResponseListener() {
				
				@Override
				public void succeed(InputStream inStream) {
					// TODO Auto-generated method stub
//						String string = IOUtil.convertInputStreamToString(inStream);
					String string = "[{\"sum\":\"608\",\"data\":[{\"name\":\"妗冭姳娉ｈ璁� The Peach Girl\",\"filmid\":\"6\",\"showtime\":\"2014-11-11  \",\"titleimage\":\"http://www.cfa.gov.cn/s/images/bf3dd64e-9382-4d63-97a5-edfd7f8e4b82.jpg\"},{\"name\":\"鏂板コ鎬� New Women\",\"filmid\":\"8\",\"showtime\":\"2014-11-12  \",\"titleimage\":\"http://www.cfa.gov.cn/s/images/dd625163-a681-42c2-b491-bafbe4c1a257.jpg\"}]}]";
					Message msg = new Message();
					msg.what = 0;
					try {
						msg.obj = JsonReader.parseMovieListJSONObject(URLDecoder.decode(string, "utf-8"));
						System.out.println("-----aaa-----"+ URLDecoder.decode(string, "utf-8"));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					handler.sendMessage(msg);
				}
				
				@Override
				public void failed(String errMsg) {
					// TODO Auto-generated method stub
					Log.d("larry", errMsg);
					Message msg = new Message();
					msg.what = 4;
					msg.obj = errMsg;
					handler.sendMessage(msg);
				}
			});
//		}
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
			Config.Setting.moviePreviewType = !Config.Setting.moviePreviewType;
			initPreviewType();
			break;
		case R.id.movie_footer_layout:
			gotoMovieDetails(mViewPager.getCurrentItem());
			System.out.println("***mViewPager.getCurrentItem()***"
					+ mViewPager.getCurrentItem());
			break;
		default:
			break;
		}
	}

}
