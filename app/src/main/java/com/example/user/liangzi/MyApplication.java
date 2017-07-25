package com.example.user.liangzi;

import android.app.Application;

import com.example.user.liangzi.net.NetUtils;
import com.example.user.liangzi.utils.SharedPUtils;
import com.umeng.analytics.MobclickAgent;

import org.xutils.x;


/**
 * Created by User on 2016/12/12.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        NetUtils.getInstance().setAppContext(this);
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
    }


}
