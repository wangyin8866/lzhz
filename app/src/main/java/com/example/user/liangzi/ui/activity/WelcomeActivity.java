package com.example.user.liangzi.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;

import com.example.user.liangzi.R;
import com.example.user.liangzi.UserManager;
import com.example.user.liangzi.ui.controller.UpdateAppVersionController;
import com.example.user.liangzi.utils.SharedPUtils;

import cn.sharesdk.framework.ShareSDK;

/**
 * Created by User on 2016/12/12.
 */

public class WelcomeActivity extends Activity {

    private UpdateAppVersionController controller;
    private boolean isChecked = false;
    private boolean isTimeOut = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_welcome);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        controller = new UpdateAppVersionController(this,false);

        controller.checkVersion();
//        isChecked = true;
        ShareSDK.initSDK(this);
        UserManager.getInstance().setJuid(SharedPUtils.getInstance(WelcomeActivity.this).getString("juid"));
        if(!TextUtils.isEmpty(UserManager.getInstance().getJuid())) {
            UserManager.getInstance().setLogin(true);
        }
        if(!changeToLogin(getIntent())) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    isTimeOut = true;
                    if(isChecked) {
                        jump();
                    }


                }
            }).start();
        }

    }


    public void setChecked(boolean checked) {
        isChecked = checked;
        if(isTimeOut) {
            jump();
        }
    }




    private void jump(){
        if (TextUtils.isEmpty(UserManager.getInstance().getJuid())) {
            Intent intent = new Intent(WelcomeActivity.this, GuideActivity.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent2 = new Intent(WelcomeActivity.this, MainActivity.class);
            startActivity(intent2);
            finish();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        changeToLogin(intent);
    }


    private boolean changeToLogin(Intent intent){
        if(intent == null) return false;
        if(intent.getBooleanExtra("isLogin",false)) {
            Intent intent2 = new Intent(WelcomeActivity.this, LoginActivity.class);
            startActivity(intent2);
            finish();
            return true;
        }
        return false;
    }
}
