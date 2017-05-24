package com.ricky.avanti.widget.imageloader;

import android.content.Context;

/**
 * 图片加载工具
 * Created by Ricky on 2017-5-9.
 */

public class ImageLoaderUtils {

    private BaseImageLoaderProvider mProvider;

    public <T extends BaseImageConfig> void loadImage(Context mContext, T config) {
        if (mProvider == null) {
            throw new IllegalStateException("ImageLoaderProvider is required");
        }
        mProvider.loadImage(mContext, config);
    }

    public <T extends BaseImageLoaderProvider> void setImageLoaderProvider(T provider) {
        this.mProvider = provider;
    }
}
