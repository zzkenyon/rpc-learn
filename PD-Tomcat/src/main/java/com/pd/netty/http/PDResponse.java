package com.pd.netty.http;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @description: PD-PDTomcat
 * @author: zhaozhengkang
 * @date: 2020-04-22 09:23
 */
public class PDResponse {
    private String content;
    public PDResponse() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
