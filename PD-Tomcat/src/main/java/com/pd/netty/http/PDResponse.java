package com.pd.netty.http;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @description: PD-PDTomcat
 * @author: zhaozhengkang
 * @date: 2020-04-22 09:23
 */
public class PDResponse {
    private HttpResponse response;

    public void write(String out) throws Exception{
        if (out == null || out.length() == 0) {
            return;
        }
        // 设置 http协议及请求头信息
         response = new DefaultFullHttpResponse(
                // 设置http版本为1.1
                HttpVersion.HTTP_1_1,
                // 设置响应状态码
                HttpResponseStatus.OK,
                // 将输出值写出 编码为UTF-8
                Unpooled.wrappedBuffer(out.getBytes("UTF-8")));

        response.headers().set("Content-Type", "text/html;");
        // 当前是否支持长连接
//            if (HttpUtil.isKeepAlive(r)) {
//                // 设置连接内容为长连接
//                response.headers().set(CONNECTION, HttpHeaderValues.KEEP_ALIVE);
//            }
    }

    public HttpResponse getResponse() {
        return response;
    }
}
