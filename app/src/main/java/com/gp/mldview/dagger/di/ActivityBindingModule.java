package com.gp.mldview.dagger.di;

import com.gp.mldview.dagger.HomeActivity;
import com.gp.mldview.dagger.HomeModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

//用于生成Activity注入器的Module，使用@ContributesAndroidInjector注解并指定modules为HomeModule
@Module
public abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = HomeModule.class)
    abstract HomeActivity contributeHomeActivity();

}
