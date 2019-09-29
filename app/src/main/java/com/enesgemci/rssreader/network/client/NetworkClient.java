package com.enesgemci.rssreader.network.client;

public abstract class NetworkClient<RESPONSE> {

    abstract RESPONSE connect();
}
