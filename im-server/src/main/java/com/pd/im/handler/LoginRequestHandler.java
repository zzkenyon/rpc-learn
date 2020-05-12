package com.pd.im.handler;


import com.pd.im.protocal.login.LoginRequestPacket;
import com.pd.im.protocal.login.LoginResponsePacket;
import com.pd.im.protocal.login.LoginUtil;
import com.pd.im.session.User;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.junit.Test;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * @description: thinking
 * @author: zhaozhengkang
 * @date: 2020-01-19 10:42
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {
        LoginResponsePacket response = login(msg);
        if(response.isSuccess()){
            LoginUtil.markAsLogin(ctx.channel());
        }
        ctx.channel().writeAndFlush(response);
    }

    private LoginResponsePacket login(LoginRequestPacket request){
        System.out.println(new Date()+ ": 客户端请求连接。。。");
        LoginResponsePacket responsePacket = new LoginResponsePacket();
        if(valid(request)){
            responsePacket.setSuccess(true);
            User user = new User(request.getUserName(),randomUserId());
            System.out.println("登录连接成功！");
        }else{
            responsePacket.setSuccess(false);
            responsePacket.setReason("连接失败！");
        }
        return responsePacket;
    }

    private String randomUserId() {
        return UUID.randomUUID().toString().substring(0,6);
    }

    @Test
    public  void testRandomUserId(){
        int i =100;
        do {
            System.out.println(randomUserId());
        }while (i-->0);
    }

    private boolean valid(LoginRequestPacket packet){
        return  true;
    }

}
