package com.enesgemci.rssreader.network;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

public final class MExecutor {

    protected static Executor executor;

    public static void init(ExecutorService executorService) {
        executor = executorService;
    }

    public static void execute(Runnable runnable) {
        executor.execute(runnable);
    }
}
