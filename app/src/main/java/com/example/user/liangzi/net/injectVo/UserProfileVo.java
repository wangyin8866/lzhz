package com.example.user.liangzi.net.injectVo;

import com.example.user.liangzi.net.NetUrls;

import io.reactivex.Observable;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by User on 2017/4/21.
 */

public interface UserProfileVo {
    @POST(NetUrls.USER_PROFILE)
    Observable<String> getUserProfile(@Header("juid") String juid);

}
