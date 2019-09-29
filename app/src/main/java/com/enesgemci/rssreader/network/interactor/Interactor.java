package com.enesgemci.rssreader.network.interactor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public abstract class Interactor {

    protected Executor executor;

    public Interactor() {
        executor = Executors.newCachedThreadPool();
    }
}
