package com.ricky.avantipro;

import android.content.Intent;
import android.support.v7.widget.Toolbar;

import com.ricky.avanti.base.SimpleActivity;
import com.ricky.avantipro.ui.SimpleDemoActivity;
import com.ricky.avantipro.ui.mvp.MvpDemoActivity;

import butterknife.BindView;
import butterknife.OnClick;


public class MainActivity extends SimpleActivity {


    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initEventAndData() {
        mContext = this;

        initToolbar(mToolbar);
    }

    @OnClick(R.id.btn_simple_demo)
    public void simpleDemoClick() {
        Intent mIntent = new Intent(this, SimpleDemoActivity.class);
        startActivity(mIntent);
    }

    @OnClick(R.id.btn_mvp_demo)
    public void mvpDemoClick() {
        Intent mIntent = new Intent(this, MvpDemoActivity.class);
        startActivity(mIntent);
    }
}
