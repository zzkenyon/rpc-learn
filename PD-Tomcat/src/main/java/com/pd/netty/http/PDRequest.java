package com.pd.netty.http;


/**
 * @description: PD-PDTomcat
 * @author: zhaozhengkang
 * @date: 2020-04-22 09:23
 */
public class PDRequest {
    private String method;

    private String url;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
