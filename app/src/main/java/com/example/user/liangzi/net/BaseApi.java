package com.example.user.liangzi.net;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * Created by User on 2017/4/15.
 */

public interface BaseApi {


    /**
     * 设置参数
     *
     * @param retrofit
     * @return
     */
    Observable getObservable(Retrofit retrofit);

}
