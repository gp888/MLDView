package com.gp.mldview.dagger.base;

public interface BasePresenter<T>{

    void takeView(T view);

    void dropView();
}
