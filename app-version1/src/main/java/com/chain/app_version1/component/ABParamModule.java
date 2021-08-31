package com.chain.app_version1.component;


import com.chain.app_version1.A;
import com.chain.app_version1.B;
import com.chain.app_version1.C;

import dagger.Module;
import dagger.Provides;

@Module
public class ABParamModule {
    A a;
    B b;

    public ABParamModule(A a, B b) {
        this.a = a;
        this.b = b;
    }

    @Provides
    public A provideA2() {
        return a;
    }

    @Provides
    public B provideB2() {
        return b;
    }

    @Provides
    public C provideC() {
        return new C(a,b);
    }
}
