package com.ricky.avantipro.ui;

import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.ricky.avanti.base.BaseBean;
import com.ricky.avanti.base.SimpleActivity;
import com.ricky.avanti.http.utils.RxSubscribe;
import com.ricky.avanti.http.utils.RxUtils;
import com.ricky.avantipro.R;
import com.ricky.avantipro.bean.LoginBean;
import com.ricky.avantipro.net.RickyHttpUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 非MVP Demo
 * Created by Ricky on 2017-5-8.
 */

public class SimpleDemoActivity extends SimpleActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    public int getLayout() {
        return R.layout.activity_simple;
    }

    @Override
    public void initEventAndData() {
        initToolbar(mToolbar);
    }

    @OnClick(R.id.btn_simple_test)
    public void simpleTestClick(){
        RickyHttpUtils.getInstance()
                .login("Ricky", "123456")
                .compose(RxUtils.<BaseBean<LoginBean>>rxSchedulerHelper())
                .compose(RxUtils.<LoginBean>handleResult())
                .subscribe(new RxSubscribe<LoginBean>(mContext) {
                    @Override
                    public boolean showProgress() {
                        return super.showProgress();
                    }

                    @Override
                    public void _onNext(LoginBean loginBean) {
                        Toast.makeText(mContext, "登录成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void _onError(int errorType, String message) {
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
