package com.example.user.liangzi.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.user.liangzi.R;
import com.example.user.liangzi.ViewPagerAdapter;
import com.example.user.liangzi.utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by User on 2016/12/12.
 */

public class GuideActivity extends Activity implements ViewPager.OnPageChangeListener,View.OnClickListener{
    private ViewPager viewPager;
    private List<View> views = new ArrayList<>();
    private Button login;
    private static final int[] pics = {R.mipmap.guide1, R.mipmap.guide2, R.mipmap.guide3};
    private ViewPagerAdapter vpAdapter;
    private LinearLayout mainLayout;
    private int oldPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        init();
        initList();
        setData();
    }


    public void init() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        mainLayout = (LinearLayout) findViewById(R.id.mainlayout);
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(this);
    }

    private void initList() {
        for (int i = 0; i < pics.length; i++) {
            if (i == 0) {
                mainLayout.addView(setDaoHangText(R.drawable.green,6));
            } else {
                mainLayout.addView(setDaoHangText(R.drawable.grey,6));
            }
        }
    }

    private View setDaoHangText(int id, int size) {
        View text = new View(this);
        text.setLayoutParams(setParams(size));
        text.setBackgroundResource(id);
        return text;
    }

    private LinearLayout.LayoutParams setParams(int size) {
        LinearLayout.LayoutParams viewpar = new LinearLayout.LayoutParams(DensityUtil.dip2px(getApplicationContext(),size), DensityUtil.dip2px(getApplicationContext(),size));
        viewpar.setMargins(20, 20, 20, 20);
        return viewpar;
    }

    public void setData() {
        views = new ArrayList<View>();
        // 定义一个布局并设置参数
        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        // 初始化引导图片列表
        for (int i = 0; i < pics.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setLayoutParams(mParams);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            iv.setImageResource(pics[i]);
            RelativeLayout view = new RelativeLayout(this);
            // 定义一个布局并设置参数
            LinearLayout.LayoutParams mviewParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            view.setLayoutParams(mviewParams);
            view.addView(iv);
            views.add(view);
        }
        vpAdapter = new ViewPagerAdapter(views);
        // 设置数据
        viewPager.setAdapter(vpAdapter);
        viewPager.setOnPageChangeListener(this);
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
    }

    @Override
    public void onPageSelected(int position) {
        mainLayout.getChildAt(oldPosition).setBackgroundResource(R.drawable.grey);
        mainLayout.getChildAt(oldPosition).setLayoutParams(setParams(6));
        mainLayout.getChildAt(position).setBackgroundResource(R.drawable.green);
        mainLayout.getChildAt(position).setLayoutParams(setParams(6));
        oldPosition = position;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                Intent intent = new Intent(GuideActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }
}
