package com.pd.netty.handler;

import com.pd.netty.http.PDRequest;
import com.pd.netty.http.PDResponse;
import com.pd.netty.http.PDServlet;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;

import java.util.Map;

/**
 * @description: PD-Tomcat
 * @author: zhaozhengkang
 * @date: 2020-04-23 09:04
 */
public class PDTomcatHandler extends ChannelInboundHandlerAdapter {
    private Map<String, PDServlet> servletMap;

    public PDTomcatHandler(Map<String, PDServlet> servletMap) {
        this.servletMap = servletMap;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof HttpRequest) {
            HttpRequest request = (HttpRequest) msg;
            PDRequest pdRequest = new PDRequest(request);
            PDResponse pdResponse = new PDResponse();

            String url = pdRequest.getUrl();
            if (servletMap.containsKey(url)) {
                servletMap.get(url).service(pdRequest, pdResponse);
            } else {
                pdResponse.write("404 - Not Found");
            }
            HttpResponse httpResponse = pdResponse.getResponse();
            // 一定要用调用channel的writeAndFlush方法 才会从tail节点开始write
            ctx.channel().writeAndFlush(httpResponse);
            // 错误调用 导致writeAndFlush时从当前节点向前传播
            // 从而add在此节点后面的OutBoundHandler无法被调用到
            //ctx.writeAndFlush(httpResponse);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    }
}
