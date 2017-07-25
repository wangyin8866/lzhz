package com.example.user.liangzi.view;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.liangzi.R;


public class CustomBottomLayout extends LinearLayout {
	public static final String PAGE_KEY = "page_key";

	Context mContext;

	LinearLayout[] mButtons;
	
	private TextView tvHint, newsRedDot;
	

	// OnClickListener mListener;

	int mCurrentIndex = -1;

	public CustomBottomLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
        View v;
		mContext = context;
		v = LayoutInflater.from(context).inflate(R.layout.ui_bottom_menu, this);
		mButtons = new LinearLayout[4];
		mButtons[0] = (LinearLayout) v.findViewById(R.id.bottom_menu_button1);
		mButtons[1] = (LinearLayout) v.findViewById(R.id.bottom_menu_button2);
		mButtons[2] = (LinearLayout) v.findViewById(R.id.bottom_menu_button3);
		mButtons[3] = (LinearLayout) v.findViewById(R.id.bottom_menu_button4);
		tvHint = (TextView) v.findViewById(R.id.basic_course_hint);
//		newsRedDot = (TextView) v.findViewById(R.id.newsRedDot);
	}

	public void setOnBottomClickListener(OnClickListener l) {
		mButtons[0].setOnClickListener(l);
		mButtons[1].setOnClickListener(l);
		mButtons[2].setOnClickListener(l);
		mButtons[3].setOnClickListener(l);
	}
	
	

	/**
	 * 外部代码中进行了Screen跳转,调用此函数,使导航栏处于正确的选中状态
	 * 
	 * @param i
	 */
	public void selectedIndex(int i) {
		
		if (mCurrentIndex != -1) {
			mButtons[mCurrentIndex].setSelected(false);
		}
		mButtons[i].setSelected(true);
		mCurrentIndex = i;
	}
	
	public int getCurrentIndex(){
		return mCurrentIndex;
	}

	
	
	/**
	 * @return 当前选中index
	 */
	public int getSelectedIndex() {
		return mCurrentIndex;
	}
	
	public void showBasicCourseHint(){
//		if(tvHint != null && tvHint.getVisibility() == View.GONE){
//			tvHint.setVisibility(View.VISIBLE);
//		}
	}
	
	public void hideBasicCourseHint(){
//		if(tvHint != null && tvHint.getVisibility() == View.VISIBLE){
//			tvHint.setVisibility(View.GONE);
//		}
	}
	
	public boolean isBasicCourseHintShow(){
		return tvHint.getVisibility() == View.VISIBLE;
	}

	public void showNewsRedDot() {
		if(newsRedDot != null && newsRedDot.getVisibility() != View.VISIBLE) {
			newsRedDot.setVisibility(View.VISIBLE);
		}
	}
	
	public void hideNewsRedDot() {
		if(newsRedDot != null && newsRedDot.getVisibility() != View.GONE) {
			newsRedDot.setVisibility(View.GONE);
		}
	}
	
	public boolean isNewsRedDotShow() {
		if(newsRedDot != null && newsRedDot.getVisibility() == View.VISIBLE) {
			return true;
		}
		return false;
	}
}
