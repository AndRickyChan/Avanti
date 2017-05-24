package com.ricky.avantipro.util;

import android.content.Context;
import android.widget.ImageView;

import com.ricky.avanti.widget.imageloader.ImageLoaderUtils;
import com.ricky.avanti.widget.imageloader.glide.GlideCacheStrategyType;
import com.ricky.avanti.widget.imageloader.glide.GlideCircleTransform;
import com.ricky.avanti.widget.imageloader.glide.GlideImageConfig;
import com.ricky.avanti.widget.imageloader.glide.GlideImageLoaderProvider;
import com.ricky.avanti.widget.imageloader.glide.GlideScaleType;
import com.ricky.avantipro.R;

/**
 * 图片加载测试类
 * Created by Ricky on 2017-5-9.
 */

public class ImageUtils {
    private static ImageUtils mInstance;
    private ImageLoaderUtils mImageLoaderUtils;

    public ImageUtils() {
        mImageLoaderUtils = new ImageLoaderUtils();
        mImageLoaderUtils.setImageLoaderProvider(new GlideImageLoaderProvider());
    }

    public static ImageUtils getInstance() {
        if (mInstance == null) {
            synchronized (ImageUtils.class) {
                if (mInstance == null) {
                    mInstance = new ImageUtils();
                }
            }
        }
        return mInstance;
    }

    public void loadHeader(Context mContext, String url, ImageView imageView) {
        mImageLoaderUtils.loadImage(mContext, new GlideImageConfig.Builder()
                .url(url)
                .errorView(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .cacheStrategy(GlideCacheStrategyType.ALL)
                .scaleType(GlideScaleType.CENTER_CROP)
                .transformation(new GlideCircleTransform(mContext))
                .into(imageView)
                .build());
    }
}
