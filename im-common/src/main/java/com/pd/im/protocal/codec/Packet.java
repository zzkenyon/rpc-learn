package com.pd.im.protocal.codec;

import lombok.Data;

/**
 * @description: thinking
 * @author: zhaozhengkang
 * @date: 2020-01-17 09:32
 */
@Data
public abstract class Packet {
    private Byte version = 1;

    public abstract Byte getCommand();
}
