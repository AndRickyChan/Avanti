package com.ricky.avantipro.net;

import android.content.Context;


import com.ricky.avanti.base.BaseBean;
import com.ricky.avanti.http.RetrofitHelper;
import com.ricky.avanti.http.okhttp.OkHttpManager;
import com.ricky.avantipro.bean.DemoBean;
import com.ricky.avantipro.bean.LoginBean;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import rx.Observable;

/**
 * 网络测试
 * Created by Ricky on 2017-5-2.
 */

public class RickyHttpUtils {
    private static RickyHttpUtils mInstance;
    private RickyApi mApi;

    public RickyHttpUtils() {
        mApi = RetrofitHelper.getInstance().getApiService(RickyApi.class
                , new OkHttpManager.Builder()
                        .cacheFile(new File("cacheFile"))
                        .cacheSize(1024 * 1024 * 100)
                        .cookiePrefs("cookie_prefs")
                        .timeOut(15)
                        .build());
    }

    public static RickyHttpUtils getInstance() {
        if (mInstance == null) {
            synchronized (RickyHttpUtils.class) {
                if (mInstance == null) {
                    mInstance = new RickyHttpUtils();
                }
            }
        }
        return mInstance;
    }

    /**
     * 登录
     */
    public Observable<BaseBean<LoginBean>> login(String phone, String password) {
        Map<String, String> params = new HashMap<>();
        params.put("phone", phone);
        params.put("password", password);
        return mApi.login(params);
    }

    public Observable<BaseBean<DemoBean>> getBaidu(String name){
        Map<String,String> params = new HashMap<>();
        params.put("wd",name);
        return mApi.getBaidu(params);
    }


}
