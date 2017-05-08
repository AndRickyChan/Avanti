package com.ricky.avantipro.ui.mvp;

import android.content.Context;

import com.ricky.avanti.base.BaseBean;
import com.ricky.avanti.base.RxPresenter;
import com.ricky.avanti.http.utils.RxSubscribe;
import com.ricky.avanti.http.utils.RxUtils;
import com.ricky.avantipro.bean.LoginBean;
import com.ricky.avantipro.net.RickyHttpUtils;

/**
 * Created by Ricky on 2017-5-8.
 */

public class MvpDemoPresenter extends RxPresenter<MvpDemoContract.View> implements MvpDemoContract.Presenter {

    public MvpDemoPresenter(MvpDemoContract.View mView) {
        super(mView);
    }

    @Override
    public void test(Context mContext, String phone, String password) {
        RickyHttpUtils.getInstance().login(phone,password)
                .compose(RxUtils.<BaseBean<LoginBean>>rxSchedulerHelper())
                .compose(RxUtils.<LoginBean>handleResult())
                .subscribe(new RxSubscribe<LoginBean>(mContext) {
                    @Override
                    public void _onNext(LoginBean loginBean) {
                        mView.success();
                    }

                    @Override
                    public void _onError(int errorType, String message) {
                        mView.failed();
                    }
                });
    }
}
