package com.dyzlg.view;

import com.dyzlg.R;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * 自定义双向选择按钮
 * larry create on 2014-1-20
 */
public class GemelView extends RelativeLayout {
	private Activity mActivity;
	private View view;
	private ViewGroup parent;
	
	private Button leftBtn;
	private Button rightBtn;
	
	private boolean pressLeft;
	private GemelViewOnClickListener listener;

	/**
	 * @param context	
	 * @param parent	父控件
	 */
	public GemelView(Context context, ViewGroup parent) {
		super(context);
		// TODO Auto-generated constructor stub
		initView(context, parent);
		pressLeft = true;//初始化左边按钮被选中
		setListener();
	}
	
	public GemelView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	private void initView(Context context, ViewGroup parent) {
		// TODO Auto-generated method stub
		this.mActivity = (Activity)context;
		this.parent = parent;
		view = LayoutInflater.from(mActivity).inflate(R.layout.gemelview_layout, null);
		leftBtn = (Button)view.findViewById(R.id.gemelview_left_button);
		rightBtn = (Button)view.findViewById(R.id.gemelview_right_button);
		leftBtn.setTextSize(18);
		rightBtn.setTextSize(15);
		this.parent.addView(view);
	}
	
	private void setListener(){
		leftBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (listener!=null) listener.leftClick();
				leftBtn.setTextSize(18);
				rightBtn.setTextSize(15);
				leftBtn.setBackgroundResource(R.drawable.bg_button2);
				rightBtn.setBackgroundColor(mActivity.getResources().getColor(R.color.transparent));
			}
		});
		rightBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (listener!=null) listener.rightClick();
				leftBtn.setTextSize(15);
				rightBtn.setTextSize(18);
				leftBtn.setBackgroundColor(mActivity.getResources().getColor(R.color.transparent));
				rightBtn.setBackgroundResource(R.drawable.bg_button2);
			}
		});
	}
	
	public void setOnGemelClickListener(GemelViewOnClickListener listener){
		this.listener = listener;
	}

	public interface GemelViewOnClickListener{
		public void leftClick();
		public void rightClick();
	}

}
