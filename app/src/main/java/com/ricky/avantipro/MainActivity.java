package com.ricky.avantipro;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.Toast;

import com.ricky.avanti.base.BaseBean;
import com.ricky.avanti.base.SimpleActivity;
import com.ricky.avanti.http.utils.RxSubscribe;
import com.ricky.avanti.http.utils.RxUtils;
import com.ricky.avantipro.bean.LoginBean;
import com.ricky.avantipro.net.RickyHttpUtils;

import butterknife.BindView;
import butterknife.OnClick;


public class MainActivity extends SimpleActivity {


    private Context mContext;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.btn_net_test)
    Button testBtn;

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initEventAndData() {
        mContext = this;

        initToolbar(mToolbar);
    }

    @OnClick(R.id.btn_net_test)
    public void testButtonClick() {
        RickyHttpUtils.getInstance(mContext)
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
