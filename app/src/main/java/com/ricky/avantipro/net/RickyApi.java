package com.ricky.avantipro.net;


import com.ricky.avanti.base.BaseBean;
import com.ricky.avantipro.bean.DemoBean;
import com.ricky.avantipro.bean.LoginBean;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * 接口地址
 * Created by Ricky on 2017-5-2.
 */

public interface RickyApi {

    @FormUrlEncoded
    @POST("user/login")
    public Observable<BaseBean<LoginBean>> login(@FieldMap Map<String, String> params);

    @GET("sportsFellow/circle/get_circle_list")
    public Observable<BaseBean<DemoBean>> getBaidu(@QueryMap Map<String, String> params);
}
