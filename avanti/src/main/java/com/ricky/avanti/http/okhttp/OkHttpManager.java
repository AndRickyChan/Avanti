package com.ricky.avanti.http.okhttp;

import android.content.Context;

import com.ricky.avanti.http.RetrofitUtils;
import com.ricky.avanti.http.cookie.CookieManger;
import com.ricky.avanti.http.interceptor.CacheInterceptor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * OkHttp管理类
 * Created by Ricky on 2017-5-2.
 */

public class OkHttpManager {

    private static OkHttpClient mOkHttpClient;

    private OkHttpManager(Context mContext, Builder builder) {
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        if (RetrofitUtils.getInstance().IS_DEBUG()) {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }

        Cache cache = new Cache(builder.cacheFile, builder.cacheSize);
        okHttpBuilder.cache(cache);//设置缓存目录和缓存大小
        okHttpBuilder.connectTimeout(builder.timeOutSeconds, TimeUnit.SECONDS);
        okHttpBuilder.readTimeout(builder.timeOutSeconds, TimeUnit.SECONDS);
        okHttpBuilder.writeTimeout(builder.timeOutSeconds, TimeUnit.SECONDS);
        okHttpBuilder.retryOnConnectionFailure(true);//设置出现错误后进行重连
        okHttpBuilder.cookieJar(new CookieManger(mContext, builder.cookiePrefs));//设置cookie本地化

        okHttpBuilder.addInterceptor(loggingInterceptor);//添加log拦截器
        okHttpBuilder.addInterceptor(new CacheInterceptor(mContext));//设置网络缓存拦截器
        okHttpBuilder.addNetworkInterceptor(new CacheInterceptor(mContext));//设置网络缓存拦截器
        if (builder.customInterceptor != null && builder.customInterceptor.size() > 0) {
            for (Interceptor interceptor : builder.customInterceptor) {
                okHttpBuilder.addInterceptor(interceptor);
            }
        }

        mOkHttpClient = okHttpBuilder.build();
    }

    public static final class Builder {

        private Context mContext;//上下文
        private File cacheFile;//缓存目录
        private int cacheSize = 1024 * 1024 * 10;//默认为10MB
        private int timeOutSeconds = 10;//默认为10秒
        private String cookiePrefs = "defaul_sp";//cookie本地化目录
        private List<Interceptor> customInterceptor;

        public Builder() {
            this.mContext = RetrofitUtils.getInstance().getmApplicationContext();
        }

        public Builder cacheFile(File cacheFile) {
            this.cacheFile = cacheFile;
            return this;
        }

        public Builder cacheSize(int cacheSize) {
            this.cacheSize = cacheSize;
            return this;
        }

        public Builder timeOut(int timeOut) {
            this.timeOutSeconds = timeOut;
            return this;
        }

        public Builder cookiePrefs(String cookiePrefs) {
            this.cookiePrefs = cookiePrefs;
            return this;
        }

        public Builder customInterceptor(Interceptor... interceptors) {
            customInterceptor = new ArrayList<>();
            for (Interceptor interceptor : interceptors) {
                customInterceptor.add(interceptor);
            }
            return this;
        }

        public OkHttpManager build() {
            if (cacheFile == null) {
                cacheFile = mContext.getCacheDir();
            }
            return new OkHttpManager(mContext, this);
        }
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }
}
