package com.example.user.liangzi.net;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;

import com.example.user.liangzi.ui.activity.WelcomeActivity;
import com.example.user.liangzi.utils.ToastAlone;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import org.json.JSONObject;

import java.lang.reflect.Field;
import java.security.SecureRandom;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.example.user.liangzi.net.NetUtils.Builder.TYPE_FULL;
import static com.example.user.liangzi.net.NetUtils.Builder.TYPE_RESULT;

/**
 * Created by User on 2017/4/13.
 */

public class NetUtils {
    public static final int SUCCESS_CODE = 0;
    public static final int SYSTEM_ERROR_CODE = 1;
    public static final int OPERATION_ERROR_CODE = 2;
    public static final int EXPIRE_ERROR_CODE = 6;

    private OkHttpClient sClient = new OkHttpClient();

    public static interface NetUtilsListener {

        void success(JSONObject result, int code);

        void success(String msg);

        void fail(String msg);
    }

    private volatile static NetUtils instance;
    private Context appContext;
    private boolean mNetAvailable = false;
    private int mConnectType = -2;

    private NetUtils(){
        SSLContext sc = null;
        try {
            sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws java.security.cert.CertificateException {

                }

                @Override
                public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws java.security.cert.CertificateException {

                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            }}, new SecureRandom());
        } catch (Exception e) {
            e.printStackTrace();
        }

        HostnameVerifier hv1 = new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };

        String workerClassName = "okhttp3.OkHttpClient";
        try {
            Class workerClass = Class.forName(workerClassName);
            Field hostnameVerifier = workerClass.getDeclaredField("hostnameVerifier");
            hostnameVerifier.setAccessible(true);
            hostnameVerifier.set(sClient, hv1);

            Field sslSocketFactory = workerClass.getDeclaredField("sslSocketFactory");
            sslSocketFactory.setAccessible(true);
            sslSocketFactory.set(sClient, sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static NetUtils getInstance(){
          if(instance == null) {
              synchronized (NetUtils.class) {
                  if(instance == null) {
                      instance = new NetUtils();
                  }
              }
          }

        return instance;
    }

    public void setAppContext(Context appContext) {
        this.appContext = appContext;
    }

    public static Builder build(){
      return new Builder();
  }


    public void postRequest(final Builder builder, BaseApi baseApi, final NetUtilsListener listener) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(builder.ipAddress)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(sClient)
                .build();
        Observable<String> call = baseApi.getObservable(retrofit);

        call.subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("xiongzhu == " + s);
                if (TextUtils.isEmpty(s)) {
                    if (listener != null)
                        listener.fail("请求失败，请重试");
                    return;
                } else {
                    if (listener != null) {
                        if (builder.returnType == TYPE_RESULT) {
                            JSONObject jsonObject = new JSONObject(s);
                            int code = jsonObject.optInt("appcode");
                            String msg = jsonObject.optString("appmsg");
                            JSONObject result = jsonObject.optJSONObject("result");
                            switch (code) {
                                case SUCCESS_CODE:
                                    if (builder.successToast) {
                                        ToastAlone.showShortToast(appContext, msg);
                                    }
                                    listener.success(result, code);
                                    break;
                                case SYSTEM_ERROR_CODE:
                                    if (builder.failToast) {
                                        ToastAlone.showShortToast(appContext, msg);
                                    }
                                    listener.fail(msg);
                                    break;
                                case OPERATION_ERROR_CODE:
                                    if (builder.failToast) {
                                        ToastAlone.showShortToast(appContext, msg);
                                    }
                                    listener.fail(msg);
                                    break;
                                case EXPIRE_ERROR_CODE:
                                    Intent intent = new Intent(appContext, WelcomeActivity.class);
                                    intent.putExtra("isLogin", true);
                                    appContext.startActivity(intent);
                                    if (appContext instanceof Activity) {
                                        ((Activity) appContext).finish();
                                    }
                                    appContext = appContext.getApplicationContext();
                                    break;
                                default:
                                    ToastAlone.showShortToast(appContext, msg);
                                    listener.fail(msg);
                                    break;
                            }

                        } else if (builder.returnType == TYPE_FULL) {
                            listener.success(s);
                        }
                    }

                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.out.println("xiongzhu throwable " + throwable.getMessage());
                validateConnectionReceiver(appContext);
                   if(mNetAvailable) {
                       if (builder.failToast) {
                           ToastAlone.showShortToast(appContext, "请求失败，请重试");
                       }
                   } else {
                       ToastAlone.showShortToast(appContext, "网络未连接");
                   }
                if (listener != null) {
                    listener.fail("请求失败，请重试");
                }
            }
        });


    }


    public boolean isNetworkAvailable() {
        return mNetAvailable;
    }
    public int validateConnectionReceiver(Context context) {
        if(context == null) return 0;
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo[] info = connMgr.getAllNetworkInfo();
        if (info != null) {
            boolean isHaveConnected = false;
            for (int i = 0; i < info.length; i++) {
                if (info[i].isConnected()) {
                    int mType = info[i].getType();
                    isHaveConnected = true;
                    if (mType != mConnectType) {
                        mConnectType = mType;
                        mNetAvailable = true;
                        return 1;
                    }
                }
            }
            if (!isHaveConnected && mConnectType != -1) {
                mConnectType = -1;
                mNetAvailable = false;
                return 2;
            }
        }
        return 0;
    }


    public static class Builder{
        public static final int TYPE_RESULT = 0;
        public static final int TYPE_FULL = 1;
        private int returnType = TYPE_RESULT;

        private String ipAddress = NetUrls.URL_IP;
        private boolean successToast = false;
        private boolean failToast = true;

        public Builder(){

        }

       public Builder returnType(int returnType) {
           this.returnType = returnType;
           return this;
       }

        public Builder ipAddress(String ipAddress) {
            this.ipAddress = ipAddress;
            return this;
        }

        public Builder successToast(boolean successToast) {
            this.successToast = successToast;
            return this;
        }

        public Builder failToast(boolean failToast) {
            this.failToast = failToast;
            return this;
        }


    }

}
