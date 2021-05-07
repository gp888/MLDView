package com.gp.mldview.dagger;

import com.gp.mldview.dagger.di.ActivityScope;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class HomeModule {
    @ActivityScope
    @Binds
    //@Binds表示将HomeContract.Presenter接口绑定到HomePresenter实现类
    abstract HomeContract.Presenter bindPresenter(HomePresenter presenter);
}
