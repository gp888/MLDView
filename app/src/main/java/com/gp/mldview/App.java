package com.gp.mldview;


import com.gp.mldview.dagger.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

//修改Application类继承自DaggerApplication，实现抽象方法applicationInjector，
// 编译下工程，返回Dagger2自动生成的Component类的create方法
public class App extends DaggerApplication {

    private static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.create();
    }

    public static App getApp() {
        return app;
    }

}
