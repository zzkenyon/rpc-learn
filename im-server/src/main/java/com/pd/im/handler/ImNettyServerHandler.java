package com.pd.im.handler;

import com.pd.im.protocal.*;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * @description: thinking
 * @author: zhaozhengkang
 * @date: 2020-01-17 10:02
 */
public class ImNettyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        ByteBuf buf = (ByteBuf)msg;
        Packet packet = PacketCodeC.INSTANCE.decode(buf);
        if(packet instanceof LoginRequestPacket){
            /*LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;
            System.out.println(new Date()+ ": 客户端请求连接。。。");
            LoginResponsePacket responsePacket = new LoginResponsePacket();
            if(valid(loginRequestPacket)){
                responsePacket.setSuccess(true);
                System.out.println("登录连接成功！");
            }else{
                responsePacket.setSuccess(false);
                responsePacket.setReason("连接失败！");
            }
            ByteBuf responseBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(),responsePacket);
            ctx.channel().writeAndFlush(responseBuf);*/
        }else if(packet instanceof MessageRequestPacket){
            MessageRequestPacket messageRequestPacket = (MessageRequestPacket) packet;
            System.out.println(new Date() + ": 收到客户端消息: " + messageRequestPacket.getMessage());
            MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
            messageResponsePacket.setMessage("服务端回复【" + messageRequestPacket.getMessage() + "】");
            ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), messageResponsePacket);
            ctx.channel().writeAndFlush(responseByteBuf);
        }
    }

    private boolean valid(LoginRequestPacket packet){
        return  true;
    }
}
