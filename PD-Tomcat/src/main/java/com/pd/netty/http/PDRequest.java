package com.pd.netty.http;


import io.netty.handler.codec.http.HttpRequest;

/**
 * @description: PD-PDTomcat
 * @author: zhaozhengkang
 * @date: 2020-04-22 09:23
 */
public class PDRequest {
    private HttpRequest httpRequest;

    public PDRequest(HttpRequest httpRequest) {
        this.httpRequest = httpRequest;
    }

    public String getMethod() {
        return httpRequest.method().name();
    }

    public String getUrl() {
        return httpRequest.uri();
    }
}
