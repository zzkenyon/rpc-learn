package com.pd.im.handler;


import com.pd.im.protocal.MessageRequestPacket;
import com.pd.im.protocal.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @description: thinking
 * @author: zhaozhengkang
 * @date: 2020-01-19 10:44
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket msg) throws Exception {
        ctx.channel().writeAndFlush(receiveMessage(msg));
    }

    private MessageResponsePacket receiveMessage(MessageRequestPacket request){
        System.out.println(new Date() + ": 收到客户端消息: " + request.getMessage());
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setMessage("服务端回复【" + request.getMessage() + "】");
        return messageResponsePacket;
    }
}
