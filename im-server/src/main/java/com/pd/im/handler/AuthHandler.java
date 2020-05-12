package com.pd.im.handler;

import com.pd.im.protocal.login.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @description: im-server
 * @author: zhaozhengkang
 * @date: 2020-05-12 14:38
 */
public class AuthHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        boolean hasLogin = LoginUtil.hasLogin(ctx.channel());
        if(!hasLogin){
            ctx.close();
        }else {
            ctx.pipeline().remove(this);
            super.channelRead(ctx,msg);
        }
    }
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        if (LoginUtil.hasLogin(ctx.channel())) {
            System.out.println("当前连接登录验证完毕，无需再次验证, AuthHandler 被移除");
        } else {
            System.out.println("无登录验证，强制关闭连接!");
        }
    }
}
