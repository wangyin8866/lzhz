package com.example.user.liangzi.net.injectVo;

import com.example.user.liangzi.net.NetUrls;

import io.reactivex.Observable;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by User on 2017/4/26.
 */

public interface UserInvitationVo {
    @POST(NetUrls.USER_INVITATION)
    Observable<String> getUserInvitation(@Header("juid") String juid);

}
