package com.pd.im.protocal;

import lombok.Data;

/**
 * @description: thinking
 * @author: zhaozhengkang
 * @date: 2020-01-17 18:00
 */
@Data
public class MessageRequestPacket extends Packet{
    private String message;
    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }
}
