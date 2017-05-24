package com.ricky.avanti.widget.imageloader;

import android.content.Context;

/**
 * 图片加载工具参数提供接口
 * Created by Ricky on 2017-5-9.
 */

public interface BaseImageLoaderProvider<T extends BaseImageConfig> {

    void loadImage(Context mContext,T config);
}
