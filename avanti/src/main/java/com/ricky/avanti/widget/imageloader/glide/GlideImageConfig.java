package com.ricky.avanti.widget.imageloader.glide;

import android.widget.ImageView;

import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.ricky.avanti.widget.imageloader.BaseImageConfig;

/**
 * Glide加载图片配置参数
 * Created by Ricky on 2017-5-9.
 */

public class GlideImageConfig extends BaseImageConfig {

    private int cacheStrategy;//缓存加载策略
    private int scaleType;//缩放模式；Glide提供两种缩放模式：fitCenter,centerCrop
    private BitmapTransformation transformation;//图形转换方法

    private GlideImageConfig(Builder builder) {
        this.url = builder.url;
        this.imageView = builder.imageView;
        this.errorView = builder.errorView;
        this.placeholderView = builder.placeholderView;
        this.cacheStrategy = builder.cacheStrategy;
        this.scaleType = builder.scaleType;
    }

    public int getCacheStrategy() {
        return cacheStrategy;
    }

    public void setCacheStrategy(int cacheStrategy) {
        this.cacheStrategy = cacheStrategy;
    }

    public int getScaleType() {
        return scaleType;
    }

    public void setScaleType(int scaleType) {
        this.scaleType = scaleType;
    }

    public BitmapTransformation getTransformation() {
        return transformation;
    }

    public void setTransformation(BitmapTransformation transformation) {
        this.transformation = transformation;
    }

    public static class Builder {
        private String url;
        private ImageView imageView;
        private int errorView;
        private int placeholderView;
        private int cacheStrategy;
        private int scaleType;
        private BitmapTransformation transformation;

        public Builder() {
            //通过构造方法初始化参数
            this.url = "";
            this.imageView = null;
            this.errorView = 0;
            this.placeholderView = 0;
            this.cacheStrategy = GlideCacheStrategyType.NONE;
            this.scaleType = GlideScaleType.CENTER_CROP;
            this.transformation = null;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder into(ImageView imageView) {
            this.imageView = imageView;
            return this;
        }

        public Builder errorView(int errorView) {
            this.errorView = errorView;
            return this;
        }

        public Builder placeholder(int placeholderView) {
            this.placeholderView = placeholderView;
            return this;
        }

        public Builder scaleType(int scaleType) {
            this.scaleType = scaleType;
            return this;
        }

        public Builder cacheStrategy(int cacheStrategy) {
            this.cacheStrategy = cacheStrategy;
            return this;
        }

        public Builder transformation(BitmapTransformation transformation) {
            this.transformation = transformation;
            return this;
        }

        public GlideImageConfig build() {
            return new GlideImageConfig(this);
        }

    }
}
