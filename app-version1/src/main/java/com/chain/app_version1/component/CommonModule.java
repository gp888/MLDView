package com.chain.app_version1.component;

import com.chain.app_version1.UserInfo;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class CommonModule {

    @Singleton
    @Provides
    UserInfo provideUserInfo(){
        return new UserInfo();
    }

}
