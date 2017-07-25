package com.example.user.liangzi.net.injectVo;

import com.example.user.liangzi.net.NetUrls;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by User on 2017/4/18.
 */

public interface LoginVo {
    @FormUrlEncoded
    @POST(NetUrls.LOGIN)
    Observable<String> login(@Field("mobile") String mobile, @Field("verifycode") String verifycode,@Field("flag") String flag);

}
