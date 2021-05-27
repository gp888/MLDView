package com.gp.mldview.dagger.base;

import android.os.Bundle;
import androidx.annotation.Nullable;

import butterknife.ButterKnife;
import dagger.android.DaggerActivity;


//使用的是AppCompatActivity则继承自DaggerAppCompatActivity即可
public abstract class BaseActivity extends DaggerActivity implements BaseView{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
    }

    @Override
    public void fail(String tips) {
    }

    @Override
    public void success(String tips) {
    }

    @Override
    public void showLoading(){
//        if (loading != null) {
//            loading.show();
//        }
    }

    @Override
    public void hideLoading(){
//        if (loading != null && loading.isShowing()) {
//            loading.dismiss();
//        }
    }

    protected abstract int getLayoutId();
}


