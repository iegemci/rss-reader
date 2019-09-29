package com.enesgemci.rssreader.di;

import com.enesgemci.rssreader.network.client.NetworkClient;
import com.enesgemci.rssreader.network.client.RssNetworkClient;
import com.enesgemci.rssreader.network.interactor.Interactor;
import com.enesgemci.rssreader.network.interactor.RssInteractor;

import java.util.HashMap;

public final class GraphFactory implements DependencyInjector {

    private static GraphFactory instance;
    private HashMap<String, Object> graph = new HashMap<>();
    private static String baseUrl;

    private GraphFactory() {
    }

    public static void init(String url) {
        baseUrl = url;
    }

    public static GraphFactory getInstance() {
        if (instance == null) {
            instance = new GraphFactory();
        }

        return instance;
    }

    @Override
    public <C extends NetworkClient<?>> C provideNetworkClient() {
        String rssClientClass = RssNetworkClient.class.getName();

        if (graph.containsKey(rssClientClass)) {
            return (C) graph.get(rssClientClass);
        }

        RssNetworkClient client = new RssNetworkClient(baseUrl);
        graph.put(rssClientClass, client);

        return (C) client;
    }

    @Override
    public <I extends Interactor> I provideInteractor() {
        String interactorClass = Interactor.class.getName();

        if (graph.containsKey(interactorClass)) {
            return (I) graph.get(interactorClass);
        }

        RssInteractor interactor = new RssInteractor();
        graph.put(interactorClass, interactor);

        return (I) interactor;
    }
}
