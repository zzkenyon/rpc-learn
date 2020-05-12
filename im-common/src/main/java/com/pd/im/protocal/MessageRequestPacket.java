package com.pd.im.protocal;

import com.pd.im.protocal.codec.Packet;
import lombok.Data;

/**
 * @description: thinking
 * @author: zhaozhengkang
 * @date: 2020-01-17 18:00
 */
@Data
public class MessageRequestPacket extends Packet {
    private String toChartId;
    private String message;
    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }
}
