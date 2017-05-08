package com.ricky.avantipro.ui.mvp;


import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.ricky.avanti.base.BaseActivity;
import com.ricky.avantipro.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * MVP Demo
 * Created by Ricky on 2017-5-8.
 */

public class MvpDemoActivity extends BaseActivity<MvpDemoPresenter> implements MvpDemoContract.View {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;


    @Override
    public MvpDemoPresenter getPresenter() {
        return new MvpDemoPresenter(this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_mvp;
    }

    @Override
    public void initEventAndData() {
        initToolbar(mToolbar);
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void success() {
        // TODO: 2017-5-8 网络请求成功
    }

    @Override
    public void failed() {
        // TODO: 2017-5-8 网络请求失败
    }

    @OnClick(R.id.btn_mvp_test)
    public void mvpTestClick(){
        mPresenter.test(this,"Ricky","123456");
    }
}
