package com.pd.im.protocal;

import lombok.Data;

/**
 * @description: thinking
 * @author: zhaozhengkang
 * @date: 2020-01-17 09:34
 */
@Data
public class LoginRequestPacket extends Packet {
    private String userId;
    private String userName;
    private String password;
    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
