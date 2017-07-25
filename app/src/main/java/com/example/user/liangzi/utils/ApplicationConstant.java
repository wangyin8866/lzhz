package com.example.user.liangzi.utils;

import android.app.Application;
import android.content.Context;

import java.lang.reflect.Method;

/**
 * Created by User on 2017/4/15.
 */

public class ApplicationConstant {
    private static Application app;

//    public static Application app() {
//        if(app == null) {
//            synchronized (ApplicationConstant.class) {
//                if (app == null) {
//                    try {
//                        Class ignored = Class.forName("com.android.layoutlib.bridge.impl.RenderAction");
//                        Method method = ignored.getDeclaredMethod("getCurrentContext", new Class[0]);
//                        Context context = (Context) method.invoke((Object) null, new Object[0]);
//                        app = new MockApplication(context);
//                    } catch (Throwable var3) {
//                        throw new RuntimeException("please invoke x.Ext.init(app) on Application#onCreate() and register your Application in manifest.");
//                    }
//                }
//            }
//
//        }
//
//        return app;
//    }


    private static class MockApplication extends Application {
        public MockApplication(Context baseContext) {
            this.attachBaseContext(baseContext);
        }
    }

}
