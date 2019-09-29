package com.enesgemci.rssreader.base;

import android.app.Application;

import com.enesgemci.rssreader.R;
import com.enesgemci.rssreader.di.GraphFactory;

public class MApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        GraphFactory.init(getString(R.string.base_url));
    }
}
