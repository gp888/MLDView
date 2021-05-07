package com.gp.mldview.dagger;

import com.gp.mldview.dagger.base.BasePresenter;
import com.gp.mldview.dagger.base.BaseView;

public interface HomeContract {

    interface View extends BaseView {
        void logout();
    }


    interface Presenter extends BasePresenter<View> {
        void logout();
    }
}
