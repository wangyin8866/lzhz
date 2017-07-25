package com.example.user.liangzi.net.injectVo;

import com.example.user.liangzi.net.NetUrls;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by User on 2017/4/14.
 */

public interface GraphicVerifyCodeVo {

    @POST(NetUrls.GRAPHIC_VERIFY_CODE)
    Observable<String> getGraphicVerifyCode();
}
