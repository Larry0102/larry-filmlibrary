package com.dyzlg.adapter;

import java.util.ArrayList;

import com.dyzlg.R;
import com.dyzlg.model.MovieBean;
import com.lidroid.xutils.BitmapUtils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 近期热映list模式Adapter
 * larry create on 2014-3-19
 */
public class MovieListAdapter extends BaseAdapter {
	private ArrayList<MovieBean> movies;
	private Context mContext;
	private LayoutInflater mInflater;
	public static boolean hasTimeline = false;			// 是否有时间轴
	public static int currentSelect = 0;				// 当前选中的项

	public MovieListAdapter(Context mContext, ArrayList<MovieBean> movies) {
		this.mContext = mContext;
		this.movies = movies;
		this.mInflater = LayoutInflater.from(this.mContext);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return movies.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return movies.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	public void notifyDataSetChanged(ArrayList<MovieBean> movies) {
		// TODO Auto-generated method stub
		this.movies = movies;
		super.notifyDataSetChanged();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.adapter_movie_list_item_layout, null);
			holder.icon = convertView.findViewById(R.id.movie_list_item_icon);
			holder.name = (TextView)convertView.findViewById(R.id.movie_list_item_name);
			holder.nameAttach = (TextView)convertView.findViewById(R.id.movie_list_item_name_attach);
			holder.date = (TextView)convertView.findViewById(R.id.movie_list_item_date);
			holder.time1 = (TextView)convertView.findViewById(R.id.movie_list_item_time1);
			holder.time2 = (TextView)convertView.findViewById(R.id.movie_list_item_time2);
			holder.time3 = (TextView)convertView.findViewById(R.id.movie_list_item_time3);
			holder.timelineLayout = (RelativeLayout)convertView.findViewById(R.id.movie_list_item_timeline);
			holder.line = (ImageView)convertView.findViewById(R.id.movie_list_item_line);
			holder.point = (ImageView)convertView.findViewById(R.id.movie_list_item_point);
			holder.day = (TextView)convertView.findViewById(R.id.movie_list_item_timeline_day);
			holder.month = (TextView)convertView.findViewById(R.id.movie_list_item_timeline_month);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder)convertView.getTag();
		}
		if(hasTimeline){
			holder.timelineLayout.setVisibility(View.VISIBLE);
			holder.nameAttach.setVisibility(View.GONE);
		} else {
			holder.timelineLayout.setVisibility(View.GONE);
			holder.nameAttach.setVisibility(View.VISIBLE);
		}
		if(currentSelect == position){
			holder.point.setVisibility(View.VISIBLE);
		} else {
			holder.point.setVisibility(View.INVISIBLE);
		}
		holder.name.setText(movies.get(position).getName());
		holder.date.setText(movies.get(position).getShowTime());
		holder.nameAttach.setVisibility(View.GONE);
		BitmapUtils bitmapUtils = new BitmapUtils(mContext);
		// 加载网络图片
		bitmapUtils.display(holder.icon, movies.get(position).getTitleImageUrl());
//		mListView.setOnScrollListener(new PauseOnScrollListener(bitmapUtils, false, true));
		return convertView;
	}
	
	private static class ViewHolder{
		private View icon;
		private TextView name;
		private TextView nameAttach;
		private TextView date;
		private TextView time1;
		private TextView time2;
		private TextView time3;
		private RelativeLayout timelineLayout;
		private ImageView line;
		private ImageView point;
		private TextView day;
		private TextView month;
	}

}
