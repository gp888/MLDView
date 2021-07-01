package com.gp.mldview;


import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;

import androidx.multidex.MultiDex;

import com.gp.mldview.dagger.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

//修改Application类继承自DaggerApplication，实现抽象方法applicationInjector，
// 编译下工程，返回Dagger2自动生成的Component类的create方法
public class App extends DaggerApplication {

    private static App app;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        //初始化语言设置
        LocaleUtil.changeAppLanguage(this);
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.create();
    }

    public static App getApp() {
        return app;
    }

    //当系统设置语言变化时进行语言设置
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.e("TAG", "onConfigurationChanged");
        LocaleUtil.setLanguage(this, newConfig);
    }

}
