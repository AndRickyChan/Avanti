package com.ricky.avantipro.ui.mvp;

import android.content.Context;

import com.ricky.avanti.base.BasePresenter;
import com.ricky.avanti.base.BaseView;

/**
 * Created by Ricky on 2017-5-8.
 */
interface MvpDemoContract {

    interface View extends BaseView {
        void success();

        void failed();
    }

    interface Presenter extends BasePresenter {
        void test(Context mContext,String phone, String password);
    }

}
