package com.example.user.liangzi.ui.controller;


import android.text.TextUtils;

import com.example.user.liangzi.UserManager;
import com.example.user.liangzi.net.BaseApi;
import com.example.user.liangzi.net.NetUtils;
import com.example.user.liangzi.net.injectVo.UserInvitationVo;
import com.example.user.liangzi.net.injectVo.UserProfileVo;

import org.json.JSONArray;
import org.json.JSONObject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import retrofit2.Retrofit;

/**
 * Created by User on 2017/4/13.
 */

public class UserInfoController {


    private static Consumer<Boolean> consumer;

    public static void getUserInfo(){
        getUserProfile();
        getUserInvitation();
    }

    public static void getUserProfile(){
        NetUtils.getInstance().postRequest(NetUtils.build(), new BaseApi() {
            @Override
            public Observable getObservable(Retrofit retrofit) {
                UserProfileVo requestSerives = retrofit.create(UserProfileVo.class);//这里采用的是Java的动态代理模式
                return requestSerives.getUserProfile(UserManager.getInstance().getJuid());//传入我们请求的键值对的值
            }
        }, new NetUtils.NetUtilsListener() {
            @Override
            public void success(JSONObject result, int code) {
                JSONObject user = result.optJSONObject("user");
                if(user != null) {
                    UserManager.getInstance().setAmount(user.optDouble("amount"));
                    UserManager.getInstance().setMobile(user.optString("mobile"));
                    UserManager.getInstance().setHead_pic(user.optString("head_pic"));
                    UserManager.getInstance().setName(user.optString("name"));
                    UserManager.getInstance().setTop_insure(user.optString("top_insure"));
                    UserManager.getInstance().setInsure_num(user.optInt("insure_num"));
                    UserManager.getInstance().setCount_help(user.optString("count_help"));
                    UserManager.getInstance().setCount_invite(user.optInt("count_invite"));
                    UserManager.getInstance().setRank(user.optInt("rank"));
                }
                JSONArray family_join = result.optJSONArray("family_join");
                if(family_join != null && family_join.length() > 0) {
                    String[] join = new String[family_join.length()];
                    for(int i = 0;i < family_join.length();i++) {
                        join[i] = family_join.optString(i);
                    }
                    UserManager.getInstance().setFamily_join(join);

                }

                if(consumer != null) {
                    Observable observable = Observable.create(new ObservableOnSubscribe() {
                        @Override
                        public void subscribe(ObservableEmitter e) throws Exception {
                            e.onNext(true);
                        }
                    });
                    observable.subscribe(consumer);
                }


            }

            @Override
            public void success(String msg) {

            }

            @Override
            public void fail(String msg) {

            }
        });
    }

   public static void getUserInvitation() {
       NetUtils.getInstance().postRequest(NetUtils.build(), new BaseApi() {
           @Override
           public Observable getObservable(Retrofit retrofit) {
               UserInvitationVo requestSerives = retrofit.create(UserInvitationVo.class);//这里采用的是Java的动态代理模式
               return requestSerives.getUserInvitation(UserManager.getInstance().getJuid());//传入我们请求的键值对的值
           }
       }, new NetUtils.NetUtilsListener() {
           @Override
           public void success(JSONObject result, int code) {
             UserManager.getInstance().setInvite_code(result.optString("invite_code"));


           }

           @Override
           public void success(String msg) {

           }

           @Override
           public void fail(String msg) {

           }
       });
   }

    public static void setConsumer(Consumer<Boolean> consumer) {
        UserInfoController.consumer = consumer;
    }

    public static void clear() {
        UserInfoController.consumer = null;
    }
}
