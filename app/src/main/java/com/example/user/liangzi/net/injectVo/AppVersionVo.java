package com.example.user.liangzi.net.injectVo;

import com.example.user.liangzi.net.NetUrls;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by User on 2017/5/10.
 */

public interface AppVersionVo {

    @FormUrlEncoded
    @POST(NetUrls.VERSION_CHECK)
    Observable<String> getVersion(@Field("version_seq") String current_version, @Field("client_id") String client_id);
}
