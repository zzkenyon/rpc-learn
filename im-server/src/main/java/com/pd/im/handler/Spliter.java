package com.pd.im.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @description: im-server
 * @author: zhaozhengkang
 * @date: 2020-04-22 18:56
 */
public class Spliter extends LengthFieldBasedFrameDecoder {
    private static final int PACKET_HEADER_LENGTH  = 7;
    private static final int DATA_DOMAIN_LENGTH = 4;
    private static final int MAGIC_NUMBER = 305419896;

    public Spliter() {
        super(Integer.MAX_VALUE, PACKET_HEADER_LENGTH, DATA_DOMAIN_LENGTH);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception{
        if(in.getInt(in.readerIndex())!= MAGIC_NUMBER){
            ctx.channel().close();
            return null;
        }
        return super.decode(ctx,in);
    }

}
