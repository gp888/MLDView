package com.gp.mldview.dagger;

import javax.inject.Inject;

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View mView;

    @Inject
    public HomePresenter() {
    }

    @Override
    public void takeView(HomeContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
    }

    @Override
    public void logout() {
        if (mView != null) {
            mView.logout();
        }
    }
}
