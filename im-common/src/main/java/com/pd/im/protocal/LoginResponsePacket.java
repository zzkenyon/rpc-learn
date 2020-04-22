package com.pd.im.protocal;

import lombok.Data;

/**
 * @description: thinking
 * @author: zhaozhengkang
 * @date: 2020-01-17 18:17
 */
@Data
public class LoginResponsePacket extends Packet {
    boolean success;
    String reason;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
