package com.enesgemci.rssreader.base;

public interface IBasePresenter<V extends BaseView> {

    void attachView(V view);

    void detachView();
}
