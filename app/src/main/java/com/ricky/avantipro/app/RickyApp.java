package com.ricky.avantipro.app;

import android.app.Application;

import com.ricky.avanti.http.RetrofitUtils;


/**
 * application
 * Created by Ricky on 2017-5-2.
 */

public class RickyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化网络请求参数
        RetrofitUtils.getInstance().init(this,"https://www.baidu.com/", true);
    }
}
