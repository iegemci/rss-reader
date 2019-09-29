package com.enesgemci.rssreader.di;

import com.enesgemci.rssreader.network.client.NetworkClient;
import com.enesgemci.rssreader.network.interactor.Interactor;

public interface DependencyInjector {

    <C extends NetworkClient<?>> C provideNetworkClient();

    <I extends Interactor> I provideInteractor();
}
