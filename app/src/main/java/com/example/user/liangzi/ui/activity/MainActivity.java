package com.example.user.liangzi.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.liangzi.R;
import com.example.user.liangzi.net.NetUrls;
import com.example.user.liangzi.net.NetUtils;
import com.example.user.liangzi.ui.BaseActivity;
import com.example.user.liangzi.ui.BaseFragment;
import com.example.user.liangzi.ui.FragmentStack;
import com.example.user.liangzi.ui.controller.UserInfoController;
import com.example.user.liangzi.ui.fragment.WebFragment;
import com.example.user.liangzi.view.CustomBottomLayout;
import com.example.user.liangzi.view.CustomerDialog;




public class MainActivity extends BaseActivity implements View.OnClickListener,View.OnKeyListener {
    private ImageButton ib_share;
    private ImageView icon_back;
    private TextView tv_title;
    private RelativeLayout rll;
    private CustomerDialog dialog;
    private String juid,appcode,ms,curl,res;
    private Button but_share;
    public static String invite;

    private FragmentManager mFragManager;
    private BaseFragment mCurrentFragment;
    private int mCurrentTab = -1;
    private int mTabIndex;
    private int oldBottomIndex;
    private FragmentStack mFmtStack = new FragmentStack();
    private CustomBottomLayout bottomLayout;
    private Bundle bundle;
    private LinearLayout back_layout;
    private int shareType = 2;

    @Override
    public void getIntentData(Bundle savedInstanceState) {

    }

    @Override
    public void loadXml() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void init() {
        mFragManager = getSupportFragmentManager();
        rll= (RelativeLayout) findViewById(R.id.rl1);
//        but_share= (Button) findViewById(R.id.but_share);
        back_layout = (LinearLayout) findViewById(R.id.back_layout);
        icon_back= (ImageView) findViewById(R.id.icon_back);
        ib_share= (ImageButton) findViewById(R.id.ib_share);
        tv_title= (TextView) findViewById(R.id.title);
        bottomLayout = (CustomBottomLayout) findViewById(R.id.bottom);
        bottomLayout.setOnBottomClickListener(this);
//
        back_layout.setOnClickListener(this);
        icon_back.setOnClickListener(this);
        ib_share.setOnClickListener(this);
//        but_share.setOnClickListener(this);

        dialog=new CustomerDialog(this);
        bundle = new Bundle();
        bundle.putString("address", NetUrls.H5_SERVICE_ADDERSS_HELP);
        showPage(FragmentStack.HELP_PAGE_TAB, bundle, false, FragmentStack.HELP_PAGE_TAB);
        NetUtils.getInstance().setAppContext(this);
        UserInfoController.clear();
        UserInfoController.getUserInfo();
    }

    @Override
    public void setListener() {

    }

    @Override
    public void setData() {

    }

    @Override
    public void setOther() {

    }


    private void  showPage(int spec, Bundle bundle, boolean withAnimation, int tabIndex) {
        if (spec == mCurrentTab) return;
        shareType = 2;
        mCurrentTab = spec;
        mTabIndex = tabIndex;
        BaseFragment temp = mCurrentFragment;
        if (null == mFragManager) {
            return;
        }
        BaseFragment page = mFmtStack.getFragment(spec);
        int tab = FragmentStack.getTabBySpec(spec);
        int bottomIndex = 0;
        switch (tab) {
            case FragmentStack.FIRST_PAGE_TAB:
                bottomIndex = 0;
                break;
            case FragmentStack.SECOND_PAGE_TAB:
                bottomIndex = 1;
                break;
            case FragmentStack.THIRD_PAGE_TAB:
                bottomIndex = 2;
                break;
            case FragmentStack.FOURTH_PAGE_TAB:
                bottomIndex = 3;
                break;
            case FragmentStack.FIFTH_PAGE_TAB:
                bottomIndex = 4;
                break;
            case FragmentStack.SIXTH_PAGE_TAB:
                bottomIndex = 5;// Integer.MAX_VALUE;
                break;
            case FragmentStack.TWELFTH_PAGE_TAB:
                bottomIndex = 6;
                break;
            case FragmentStack.THIRTEENTH_PAGE_TAB:
                bottomIndex = 7;
                break;
            default:
                bottomIndex = oldBottomIndex;
                break;
        }
        bottomLayout.selectedIndex(bottomIndex);
        showTitle(bottomIndex);
        if (mCurrentFragment != page) {
            mCurrentFragment = page;//保存当前显示的Fragment对象
        }


        FragmentTransaction ft = mFragManager.beginTransaction();
        if (withAnimation) {
            if (oldBottomIndex > bottomIndex) {
                ft.setCustomAnimations(R.anim.slide_right_enter, R.anim.slide_right_exit);//后退：动画  效果 页面向右移出 左边进
            } else {
                ft.setCustomAnimations(R.anim.slide_left_enter, R.anim.slide_left_exit);//前进：动画  效果 页面向左移出  右边进
            }
        }
        if (temp != null) {
            ft.hide(temp);//隐蔽当前显示Fragment
        }
        if (bundle != null) {
            page.setBundle(bundle);//如果有bundle对传递参数，就设置
        }
        if (!page.isAdded()) {
            ft.add(R.id.fragment, page);//添加到对应 的FragmentLayout  显示
        } else {
            ft.show(page);//已经存在了，直拉显示
        }
        ft.commitAllowingStateLoss();//提交所有有事务修改
        oldBottomIndex = bottomIndex;
    }

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.back_layout:
           case R.id.icon_back:
               handleBack();
               break;
           case R.id.ib_share:
               dialog.showShareDialog(shareType);
               dialog.show();
               break;
//           case R.id.but_share:
//               if (curl.contains("activity/inviteFriends.html")){
//                   sendInvite();
//               }else{
//                   dialog.showShareDialog(2);
//                   dialog.show();
//               }
//               break;
           case R.id.bottom_menu_button1:
               bundle.putString("address", NetUrls.H5_SERVICE_ADDERSS_HELP);
               showPage(FragmentStack.HELP_PAGE_TAB, bundle, false, FragmentStack.HELP_PAGE_TAB);
               break;
           case R.id.bottom_menu_button2:
               bundle.putString("address", NetUrls.H5_SERVICE_ADDERSS_DISCOVER);
               showPage(FragmentStack.DISCOVER_PAGE_TAB, bundle, false, FragmentStack.DISCOVER_PAGE_TAB);
               break;
           case R.id.bottom_menu_button3:
               bundle.putString("address", NetUrls.H5_SERVICE_ADDERSS_NOTICE);
               showPage(FragmentStack.NOTICE_PAGE_TAB, bundle, false, FragmentStack.NOTICE_PAGE_TAB);
               break;
           case R.id.bottom_menu_button4:
//               bundle.putString("address", NetUrls.H5_SERVICE_ADDERSS_);
               showPage(FragmentStack.HOME_PAGE_TAB, bundle, false, FragmentStack.HOME_PAGE_TAB);
               break;
           default:
               break;
       }
    }

    private void showTitle(int index) {
        String title = "量子互助";
        switch (index) {
            case 3:
                title = "我的量子";
                break;

        }
        showTitle(title);
    }

    public void showTitle(String title) {
        tv_title.setText(title);
    }


    public void setShareType(int shareType) {
        this.shareType = shareType;
    }

    private void handleBack() {
        switch (oldBottomIndex) {
            case 0:
            case 1:
            case 2:
                if(mCurrentFragment instanceof WebFragment) {
                    if (!((WebFragment) mCurrentFragment).onBack()) {
                        finish();
                    }
                }
                break;
            case 3:
            default:
                finish();
                break;

        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
//            if (keyCode == KeyEvent.KEYCODE_BACK && web.canGoBack()) { // 表示按返回键
//                web.goBack(); // 后退
//                return true;
//            }
        }
        return false;
    }

    private void sendInvite(){
//        String id=MyApplication.spUtil.getString("juid");
//        RequestParams params=new RequestParams();
//        params.addBodyParameter("juid",id);
//        params.setContentType(CONTENTTYPE);
//        setStateCheckTwo(params);
    }
//    public void setStateCheckTwo(RequestParams params) {
//        HttpUtils.HttpUtilHeader(params,Constants.INVITE, new HttpCallBackTwo());
//    }
//    private class HttpCallBackTwo implements HttpUtilsCallBack {
//
//        @Override
//        public void startCallBack() {
//
//        }
//
//        @Override
//        public void loadingCallBack(long l, long l1, boolean b) {
//
//        }
//
//        @Override
//        public void successCallBack(ResponseInfo<String> responseInfo) {
//            try {
//                String result=responseInfo.result.toString();
//                if (result.isEmpty()){
//                    return;
//                }else{
//                    JSONObject object=new JSONObject(result);
//                    appcode=object.optString("appcode");
//                    ms=object.optString("appmsg");
//                    res=object.optString("result");
//                    JSONObject object1=new JSONObject(res);
//                    invite=object1.optString("invite_code");
//                    if(appcode.equals("0")){
//                        handler.sendEmptyMessage(1);
//                    }else{
//                        handler.sendEmptyMessage(4);
//                    }
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }
//
//        @Override
//        public void failCallBack(HttpException e, String s) {
//        }
//    }
//    private void exit(){
//        String id=MyApplication.spUtil.getString("juid");
//        RequestParams params=new RequestParams();
//        params.addBodyParameter("juid",id);
//        params.setContentType(CONTENTTYPE);
//        setStateCheckOne(params);
//    }

//    public void setStateCheckOne(RequestParams params) {
//        HttpUtils.HttpUtilHeader(params,Constants.EXIT, new HttpCallBackOne());
//    }
//    private class HttpCallBackOne implements HttpUtilsCallBack {
//
//        @Override
//        public void startCallBack() {
//
//        }
//
//        @Override
//        public void loadingCallBack(long l, long l1, boolean b) {
//
//        }
//
//        @Override
//        public void successCallBack(ResponseInfo<String> responseInfo) {
//            try {
//                String result=responseInfo.result.toString();
//                if (result.isEmpty()){
//                    return;
//                }else{
//                    JSONObject object=new JSONObject(result);
//                    appcode=object.optString("appcode");
//                    ms=object.optString("appmsg");
//                    if(appcode.equals("0")){
//                        handler.sendEmptyMessage(3);
//                    }else if(appcode.equals("6")){
//                        handler.sendEmptyMessage(3);
//                    }else{
//                        handler.sendEmptyMessage(4);
//                    }
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }
//
//        @Override
//        public void failCallBack(HttpException e, String s) {
//        }
//    }
   private Handler handler=new Handler(){
       @Override
       public void handleMessage(Message msg) {
           switch (msg.what){
               case 1:
                   dialog.showShareDialog(1);
                   dialog.show();
                   break;
               case 3:
//                   MyApplication.spUtil.setString("juid","");
                   Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                   startActivity(intent);
                   finish();
                   break;
               case 4:
                   Toast.makeText(MainActivity.this, ms, Toast.LENGTH_SHORT).show();
                   break;
               default:
                   break;
           }
       }
   };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UserInfoController.clear();
    }
}
