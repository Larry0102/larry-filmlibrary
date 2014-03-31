package com.dyzlg;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dyzlg.adapter.MoviePosterAdapter;
import com.dyzlg.base.BaseActivity;

/**
 * 图片浏览器
 * larry create on 2014-3-23
 */
public class PictureViewerActivity extends BaseActivity implements OnClickListener{
	private ImageView headerLeft;
	private ImageView headerRight;
	private TextView title;
	private RelativeLayout share;
	private ViewPager mViewPager;
	private MoviePosterAdapter mMoviePosterAdapater;
	
	private boolean hasShare = false;			// 是否需要打开分享面板
	
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_picture_viewer);
		initView();
		setListener();
	}

	private void initView() {
		headerLeft = (ImageView)findViewById(R.id.header_left);
		headerRight = (ImageView)findViewById(R.id.header_right);
		title = (TextView)findViewById(R.id.header_title);
		mViewPager = (ViewPager)findViewById(R.id.picture_viewer_viewpager);
		share = (RelativeLayout)findViewById(R.id.picture_viewer_share);
		
//		mMoviePosterAdapater = new MoviePosterAdapter(this, pics);
//		mViewPager.setAdapter(mMoviePosterAdapater);
	}

	private void setListener() {
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
			hasShare = !hasShare;
			initShareLayout(hasShare);
			break;
		default:
			break;
		}
	}
	
	
	/**
	 * 初始化分享面板
	 */
	private void initShareLayout(boolean visibility){
		if(visibility){
			headerRight.setBackgroundResource(R.drawable.bg_button1);
			share.setVisibility(View.VISIBLE);
		} else {
			headerRight.setBackgroundResource(0);
			share.setVisibility(View.GONE);
		}
	}

}
