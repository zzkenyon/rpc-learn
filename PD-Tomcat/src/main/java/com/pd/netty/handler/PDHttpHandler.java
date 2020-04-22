package com.pd.netty.handler;

import com.pd.netty.http.PDRequest;
import com.pd.netty.http.PDResponse;
import com.pd.netty.http.PDServlet;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Map;

/**
 * @description: PD-Tomcat
 * @author: zhaozhengkang
 * @date: 2020-04-22 14:58
 */
public class PDHttpHandler extends SimpleChannelInboundHandler<PDRequest> {
    public PDHttpHandler(Map<String, PDServlet> servletMap) {
        this.servletMap = servletMap;
    }

    private Map<String, PDServlet> servletMap ;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PDRequest msg) throws Exception {
        PDResponse response = new PDResponse();
        String url = msg.getUrl();
        if(servletMap.containsKey(url)){
            servletMap.get(url).service(msg,response);
        }else{
            response.setContent("404 - Not Found");
        }
        ctx.channel().writeAndFlush(response);
    }
}
