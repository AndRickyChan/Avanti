package com.ricky.avanti.base;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.ricky.avanti.util.AppManager;

import butterknife.ButterKnife;

/**
 * MVP模式下的Activity基类，其他activity需要继承这个类
 * Created by Ricky on 2017-5-5.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {

    protected Context mContext;
    protected T mPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mContext = this;
        ButterKnife.bind(this);
        mPresenter = getPresenter();
        initEventAndData();
        AppManager.getInstance().addActivity(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().finishActivity(this);
        if (mPresenter != null)
            mPresenter.detachView();
    }

    protected void initToolbar(Toolbar mToolbar) {
        if (mToolbar == null)
            return;
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public abstract T getPresenter();

    public abstract int getLayout();

    public abstract void initEventAndData();
}
