package com.ricky.avanti.base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * MVP模式下的Presenter基类，其他需集成自这个基类
 * Created by Ricky on 2017-5-5.
 */

public abstract class RxPresenter<T extends BaseView> implements BasePresenter {

    private T mView;
    protected CompositeSubscription mCompositeSubscription;

    public RxPresenter(T mView) {
        this.mView = mView;
    }

    protected void addSubscription(Subscription subscription) {
        if (mCompositeSubscription == null)
            mCompositeSubscription = new CompositeSubscription();
        mCompositeSubscription.add(subscription);
    }

    protected void unSubscriotion() {
        if (mCompositeSubscription != null)
            mCompositeSubscription.unsubscribe();
    }

    @Override
    public void detachView() {
        unSubscriotion();
    }
}
