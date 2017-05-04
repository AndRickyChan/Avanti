package com.ricky.avanti.http.utils;

import android.content.Context;
import android.content.DialogInterface;

import com.afollestad.materialdialogs.MaterialDialog;
import com.ricky.avanti.base.AvantiConstant;
import com.ricky.avanti.http.exception.ApiException;
import com.ricky.avanti.util.NetUtils;

import rx.Subscriber;

/**
 * 数据结果处理基类
 * Created by Ricky on 2017-5-2.
 */

public abstract class RxSubscribe<T> extends Subscriber<T> {

    private Context mContext;
    private String progressContent;
    private MaterialDialog mProgressDialog;

    public RxSubscribe(Context mContext) {
        this.mContext = mContext;
        progressContent = "请稍等...";
    }

    public RxSubscribe(Context mContext, String progressContent) {
        this.mContext = mContext;
        this.progressContent = progressContent;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (showProgress()) {
            mProgressDialog = new MaterialDialog.Builder(mContext)
                    .content(progressContent)
                    .progress(true, 0)
                    .dismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            if (isUnsubscribed())
                                unsubscribe();
                        }
                    })
                    .build();
            mProgressDialog.show();
        }
    }

    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    @Override
    public void onError(Throwable e) {
        if (!NetUtils.isConnected(mContext))
            _onError(AvantiConstant.COMMON_NET_ERROR, "网络状态不可用");
        else if (e instanceof ApiException)
            _onError(AvantiConstant.COMMON_SERVICE_ERROR, e.getMessage());
        else
            _onError(AvantiConstant.COMMON_OTHER_ERROR, "其他未知错误");
        //隐藏进度条
        if (mProgressDialog != null && mProgressDialog.isShowing())
            mProgressDialog.dismiss();
    }

    @Override
    public void onCompleted() {
        if (mProgressDialog != null && mProgressDialog.isShowing())
            mProgressDialog.dismiss();
    }

    public boolean showProgress() {
        return true;
    }

    public abstract void _onNext(T t);

    public abstract void _onError(int errorType, String message);
}
