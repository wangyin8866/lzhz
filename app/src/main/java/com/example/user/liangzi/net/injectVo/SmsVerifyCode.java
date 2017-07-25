package com.example.user.liangzi.net.injectVo;

import com.example.user.liangzi.net.NetUrls;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by User on 2017/4/18.
 */

public interface SmsVerifyCode {
    @FormUrlEncoded
    @POST(NetUrls.LOGIN_SMS_SEND)
    Observable<String> getSmsVerifyCode(@Field("mobile") String mobile,@Field("flag") String flag,@Field("pic_verify_code_") String code,@Field("pic_verify_id_") String id);


}
