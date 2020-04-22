package com.pd.im.handler;


import com.pd.im.protocal.LoginRequestPacket;
import com.pd.im.protocal.LoginResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @description: thinking
 * @author: zhaozhengkang
 * @date: 2020-01-19 10:42
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {
        ctx.channel().writeAndFlush(login(msg));
    }

    private LoginResponsePacket login(LoginRequestPacket request){
        System.out.println(new Date()+ ": 客户端请求连接。。。");
        LoginResponsePacket responsePacket = new LoginResponsePacket();
        if(valid(request)){
            responsePacket.setSuccess(true);
            System.out.println("登录连接成功！");
        }else{
            responsePacket.setSuccess(false);
            responsePacket.setReason("连接失败！");
        }
        return responsePacket;
    }

    private boolean valid(LoginRequestPacket packet){
        return  true;
    }

}
