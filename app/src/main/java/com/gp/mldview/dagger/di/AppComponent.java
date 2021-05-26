package com.gp.mldview.dagger.di;

import com.gp.mldview.App;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

//自定义组件AppComponent

//使用@Component注解创建AppCompontent接口类，Dagger 2会生成带有Dagger前缀的Component类，
// 如：AppComponent类会自动生成DaggerAppComponent类。继承自AndroidInjector并指定Application类，
// 指定ActivityBindingModule类和Dagger 2提供的AndroidSupportInjectionModule类作为Module

@Component(modules = {ActivityBindingModule.class, AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<App> {
}
