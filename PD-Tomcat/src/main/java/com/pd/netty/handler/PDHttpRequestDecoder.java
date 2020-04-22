package com.pd.netty.handler;

import com.pd.netty.http.PDRequest;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.concurrent.EventExecutorGroup;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @description: PD-Tomcat
 * @author: zhaozhengkang
 * @date: 2020-04-22 14:56
 */
public class PDHttpRequestDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        String httpRequestContent = in.readBytes(in.readableBytes()).toString(Charset.forName("UTF-8"));
        String s = httpRequestContent.split("\\n")[0];
        String[] eles = s.split("\\s");
        PDRequest request = new PDRequest();
        request.setMethod(eles[0]);
        request.setUrl(eles[1]);
        out.add(request);
    }
}
