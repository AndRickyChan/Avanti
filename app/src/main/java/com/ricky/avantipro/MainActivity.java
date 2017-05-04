package com.ricky.avantipro;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.ricky.avanti.base.BaseBean;
import com.ricky.avanti.http.utils.RxSubscribe;
import com.ricky.avanti.http.utils.RxUtils;
import com.ricky.avantipro.bean.LoginBean;
import com.ricky.avantipro.net.RickyHttpUtils;


public class MainActivity extends AppCompatActivity {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        findViewById(R.id.btn_net_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });
    }
}
