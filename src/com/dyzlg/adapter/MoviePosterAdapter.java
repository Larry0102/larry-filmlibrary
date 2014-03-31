package com.dyzlg.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.dyzlg.model.MovieBean;
import com.lidroid.xutils.BitmapUtils;

/**
 * 近期热映poster模式Adapter 
 * larry create on 2014-3-20
 */
public class MoviePosterAdapter extends PagerAdapter {
	private ArrayList<MovieBean> movies;
	private Context mContext;

	public MoviePosterAdapter(Context mContext, ArrayList<MovieBean> movies) {
		this.mContext = mContext;
		this.movies = movies;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return movies.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object obj) {
		// TODO Auto-generated method stub
		return view == (View) obj;
	}

	@Override
	public int getItemPosition(Object object) {
		// TODO Auto-generated method stub
		return super.getItemPosition(object);
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		container.removeView((View) object);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		View iv = new View(mContext);
		BitmapUtils bitmapUtils = new BitmapUtils(mContext);
		// 加载网络图片
		bitmapUtils.display(iv, movies.get(position).getTitleImageUrl());
		((ViewPager) container).addView(iv, 0);
		return iv;
	}

}
