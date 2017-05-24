package com.ricky.avanti.widget.imageloader.glide;

import android.content.Context;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ricky.avanti.widget.imageloader.BaseImageLoaderProvider;

/**
 * Glide加载图片提供器
 * Created by Ricky on 2017-5-9.
 */

public class GlideImageLoaderProvider implements BaseImageLoaderProvider<GlideImageConfig> {
    @Override
    public void loadImage(Context mContext, GlideImageConfig config) {
        if (mContext == null) {
            throw new IllegalStateException("Context is required");
        }
        if (config == null) {
            throw new IllegalStateException("Config is required");
        }
        if (config.getImageView() == null) {
            throw new IllegalStateException("ImageView is required");
        }

        RequestManager manager = Glide.with(mContext);
        DrawableRequestBuilder<String> requestBuilder = manager.load(config.getUrl());
        //设置显示渐变动画类型
        requestBuilder.crossFade();
        //设置缩放模式
        switch (config.getScaleType()) {
            case GlideScaleType.CENTER_CROP:
                requestBuilder.centerCrop();
                break;
            case GlideScaleType.FIT_CENTER:
                requestBuilder.fitCenter();
                break;
        }
        //设置缓存策略
        switch (config.getCacheStrategy()) {
            case GlideCacheStrategyType.ALL:
                requestBuilder.diskCacheStrategy(DiskCacheStrategy.ALL);
                break;
            case GlideCacheStrategyType.NONE:
                requestBuilder.diskCacheStrategy(DiskCacheStrategy.NONE);
                break;
            case GlideCacheStrategyType.RESULT:
                requestBuilder.diskCacheStrategy(DiskCacheStrategy.RESULT);
                break;
            case GlideCacheStrategyType.SOURCE:
                requestBuilder.diskCacheStrategy(DiskCacheStrategy.SOURCE);
                break;
        }

        //设置图片转换
        if (config.getTransformation() != null) {
            requestBuilder.transform(config.getTransformation());
        }

        //设置错误图片
        if (config.getErrorView() != 0) {
            requestBuilder.error(config.getErrorView());
        }

        //设置占位图片
        if (config.getPlaceholderView() != 0) {
            requestBuilder.placeholder(config.getPlaceholderView());
        }

        //图片加载
        requestBuilder.into(config.getImageView());

    }
}
