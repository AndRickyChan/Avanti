package com.ricky.avanti.http;


import com.ricky.avanti.http.okhttp.OkHttpManager;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * RetrofitHelper
 * Created by Ricky on 2017-5-2.
 */

public class RetrofitHelper {

    private static RetrofitHelper mInstance;

    public static RetrofitHelper getInstance() {
        if (mInstance == null) {
            synchronized (RetrofitHelper.class) {
                if (mInstance == null) {
                    mInstance = new RetrofitHelper();
                }
            }
        }
        return mInstance;
    }

    public <T> T getApiService(Class<T> clazz, OkHttpManager manager) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitUtils.getInstance().getBASE_API_URL())
                .client(manager.getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(clazz);
    }
}
