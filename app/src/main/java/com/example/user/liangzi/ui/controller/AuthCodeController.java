package com.example.user.liangzi.ui.controller;

import android.app.Activity;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.example.user.liangzi.net.NetUtils;
import com.example.user.liangzi.utils.CommonUtils;
import com.example.user.liangzi.utils.ToastAlone;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2016/3/22.
 */
public class AuthCodeController  {
    private View.OnClickListener listener;
    private boolean isObtain = false;
    private Activity context;
    private Button btn;
    private NetUtils netUtils;
    private List<NameValuePair> formparams = new ArrayList<NameValuePair>(); // 请求参数

    public boolean isObtain() {
        return isObtain;
    }


    public AuthCodeController(Activity context, View.OnClickListener listener, Button btn) {
        this.context = context;
        this.listener = listener;
        this.btn = btn;
//        netUtils = new NetUtils(context,this);
    }

    public void getAuthCode(String mobile,String type) {
        if(isObtain) return;

        if (TextUtils.isEmpty(mobile)) {
            ToastAlone.showToast(context, "手机号不能为空", 0);
            return;
        }
        if (!CommonUtils.isMobile(mobile)) {
            ToastAlone.showToast(context, "手机号码不正确！", 1);
            return;
        }
        isObtain = true;
        try {
            formparams.clear();
            formparams.add(new BasicNameValuePair("mobile_phone_",mobile));
            formparams.add(new BasicNameValuePair("flag",type));
//            netUtils.postRequest(NetConstants.GENERATECODE,formparams);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void getAuthCode(String mobile,String type,String pic_verify_id_, String pic_verify_code_) {
        if(isObtain) return;

        if (TextUtils.isEmpty(mobile)) {
            ToastAlone.showToast(context, "手机号不能为空", 0);
            return;
        }
        if (!CommonUtils.isMobile(mobile)) {
            ToastAlone.showToast(context, "手机号码不正确！", 1);
            return;
        }
        isObtain = true;
        try {
            formparams.clear();
            formparams.add(new BasicNameValuePair("mobile_phone_",mobile));
            formparams.add(new BasicNameValuePair("flag",type));
            formparams.add(new BasicNameValuePair("pic_verify_id_",pic_verify_id_));
            formparams.add(new BasicNameValuePair("pic_verify_code_",pic_verify_code_));
//            netUtils.postRequest(NetConstants.GENERATE_AUTH_CODE,formparams);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public void start() {
//
//    }
//
//    @Override
//    public void success(JSONObject msg,String flag) {
//        //请求成功之后，发送验证码按钮开始计时
//        if (isObtain) {
//            isObtain = false;
//            btn.setOnClickListener(null);
//            btn.setBackgroundResource(R.drawable.gray_right_corner_bg);
//            MyCount count = new MyCount(60 * 1000, 1000);
//            count.start();
//        }
//    }
//
//    @Override
//    public void success(String msg) {
//
//    }

//    @Override
//    public void fail(String msg) {
//        if(context instanceof RegisterActivity) {
//            ((RegisterActivity)context).applyImgCode();
//        }
//        if(isObtain) {
//            isObtain = false;
//        }
//        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
//
//    }



    /* 定义一个倒计时的内部类 */
    class MyCount extends CountDownTimer {
        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            btn.setText("重新发送验证码");
            btn.setOnClickListener(listener);
            btn.setClickable(true);
//            btn.setBackgroundResource(R.drawable.blue_right_corner_bg);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            btn.setText(millisUntilFinished / 1000 + "秒");
        }
    }

}
