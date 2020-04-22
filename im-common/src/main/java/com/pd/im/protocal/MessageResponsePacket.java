package com.pd.im.protocal;

import lombok.Data;

/**
 * @description: thinking
 * @author: zhaozhengkang
 * @date: 2020-01-17 18:16
 */
@Data
public class MessageResponsePacket extends Packet {
    String message;
    @Override
    public Byte getCommand() {
        return Command.MESSAGE_RESPONSE;
    }
}
