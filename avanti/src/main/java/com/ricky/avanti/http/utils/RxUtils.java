package com.ricky.avanti.http.utils;


import com.ricky.avanti.base.BaseBean;
import com.ricky.avanti.http.exception.ApiException;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Rx结果处理工具类
 * Created by Ricky on 2017-5-2.
 */

public class RxUtils {

    private static final int SUCCESS = 0;//请求成功
    private static final int LOGIC_ERROR = 1;//业务逻辑错误（用于显示于界面中的错误）

    /**
     * 统一线程处理
     */
    public static <T> Observable.Transformer<T, T> rxSchedulerHelper() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 统一结果处理
     */
    public static <T> Observable.Transformer<BaseBean<T>, T> handleResult() {
        return new Observable.Transformer<BaseBean<T>, T>() {
            @Override
            public Observable<T> call(Observable<BaseBean<T>> httpResponseObservable) {
                return httpResponseObservable.flatMap(new Func1<BaseBean<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(BaseBean<T> httpResponse) {
                        if (httpResponse == null) {
                            return Observable.empty();
                        } else if (httpResponse.getCode() == SUCCESS) {
                            return createData(httpResponse.getData());
                        } else if (httpResponse.getCode() == LOGIC_ERROR) {
                            return Observable.error(new ApiException(httpResponse.getMsg()));
                        } else {
                            return Observable.error(new ApiException("服务器繁忙，请稍后重试"));
                        }
                    }
                });
            }
        };
    }


    /**
     * 生成返回对象
     */
    public static <T> Observable<T> createData(final T t) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    subscriber.onNext(t);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }
}
