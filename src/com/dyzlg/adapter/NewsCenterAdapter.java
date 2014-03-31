package com.dyzlg.adapter;

import java.util.ArrayList;

import com.dyzlg.R;
import com.dyzlg.model.NewsBean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * 新闻中心适配器
 * larry create on 2014-3-17
 */
public class NewsCenterAdapter extends BaseAdapter {
	private LayoutInflater mInflater;
	private Context mContext;
	private ArrayList<NewsBean> news;
	
	public NewsCenterAdapter (Context mContext, ArrayList<NewsBean> news) {
		this.mContext = mContext;
		this.news = news;
		this.mInflater = LayoutInflater.from(this.mContext);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return news.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return news.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if(convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.adapter_news_item_layout, null);
			holder.date = (TextView)convertView.findViewById(R.id.news_item_time);
			holder.newsTitle = (TextView)convertView.findViewById(R.id.news_item_content);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder)convertView.getTag();
		}
		holder.date.setText(news.get(position).getCreateDate());
		holder.newsTitle.setText(news.get(position).getTitle());
		return convertView;
	}
	
	private static class ViewHolder {
		TextView date;
		TextView newsTitle;
	}

}
