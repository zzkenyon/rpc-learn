package com.pd.netty.handler;

import com.pd.netty.http.PDResponse;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;


/**
 * @description: PD-Tomcat
 * @author: zhaozhengkang
 * @date: 2020-04-22 15:01
 */
public class PDHttpResponseEncoder extends MessageToByteEncoder<PDResponse> {

    @Override
    protected void encode(ChannelHandlerContext ctx, PDResponse msg, ByteBuf out) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("Http/1.1 200 OK\n")
                .append("Content-Type: text/html;\n")
                .append("\r\n")
                .append(msg.getContent());
        out.writeBytes(sb.toString().getBytes());
    }
}
