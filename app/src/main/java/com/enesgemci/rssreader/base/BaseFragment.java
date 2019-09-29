package com.enesgemci.rssreader.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment<V extends BaseView, P extends BasePresenter<V>>
        extends Fragment
        implements BaseView {

    private P presenter;

    @NonNull
    protected abstract P createPresenter();

    @LayoutRes
    protected abstract int getLayoutResId();

    protected P getPresenter() {
        return presenter;
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        View view = inflater.inflate(getLayoutResId(), container, false);
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        presenter = createPresenter();
        presenter.attachView((V) this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        presenter.detachView();
    }
}
