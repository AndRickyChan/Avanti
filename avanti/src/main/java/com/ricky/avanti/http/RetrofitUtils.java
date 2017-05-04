package com.ricky.avanti.http;

import android.content.Context;

/**
 * 初始化retrofit连接
 * Created by Ricky on 2017-5-2.
 */

public class RetrofitUtils {

    private static RetrofitUtils mInstance;

    private Context mApplicationContext;
    private String BASE_API_URL = "";
    private boolean IS_DEBUG = false;

    public void init(Context mApplicationContext, String baseUrl, boolean isDebug) {
        this.mApplicationContext = mApplicationContext;
        BASE_API_URL = baseUrl;
        IS_DEBUG = isDebug;
    }

    public static RetrofitUtils getInstance() {
        if (mInstance == null) {
            synchronized (RetrofitUtils.class) {
                if (mInstance == null) {
                    mInstance = new RetrofitUtils();
                }
            }
        }
        return mInstance;
    }

    public Context getmApplicationContext() {
        return mApplicationContext;
    }

    public void setmApplicationContext(Context mApplicationContext) {
        this.mApplicationContext = mApplicationContext;
    }

    public String getBASE_API_URL() {
        return BASE_API_URL;
    }

    public void setBASE_API_URL(String BASE_API_URL) {
        this.BASE_API_URL = BASE_API_URL;
    }

    public boolean IS_DEBUG() {
        return IS_DEBUG;
    }

    public void setIS_DEBUG(boolean IS_DEBUG) {
        this.IS_DEBUG = IS_DEBUG;
    }
}
