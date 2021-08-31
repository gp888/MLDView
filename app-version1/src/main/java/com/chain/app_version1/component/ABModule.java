package com.chain.app_version1.component;


import com.chain.app_version1.A2;
import com.chain.app_version1.B2;

import dagger.Module;
import dagger.Provides;

@Module
public class ABModule {
    @Provides
    public A2 provideA2() {
        return new A2();
    }

    @Provides
    public B2 provideB2() {
        return new B2();
    }
}
