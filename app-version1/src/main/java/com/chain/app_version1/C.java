package com.chain.app_version1;

public class C {
    private A a;

    private B b;

    public C(A a, B b) {
        this.a = a;
        this.b = b;
    }


    public void doSomethingC(){
        a.someThingA();
        b.someThingB();
    }
}
