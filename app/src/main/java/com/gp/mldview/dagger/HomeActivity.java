package com.gp.mldview.dagger;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.gp.mldview.R;
import com.gp.mldview.dagger.base.BaseActivity;

import javax.inject.Inject;

import butterknife.OnClick;

public class HomeActivity extends BaseActivity implements HomeContract.View{

    @Inject
    HomeContract.Presenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.takeView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.dropView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home_dagger;
    }


    @OnClick(R.id.btn_logout)
    public void onClick(){
        mPresenter.logout();
    }

    @Override
    public void logout() {
        Toast.makeText(this, "hh", Toast.LENGTH_SHORT).show();
    }
}
