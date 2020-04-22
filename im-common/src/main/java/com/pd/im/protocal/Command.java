package com.pd.im.protocal;

/**
 * @description: thinking
 * @author: zhaozhengkang
 * @date: 2020-01-17 09:31
 */
public interface Command {
    Byte LOGIN_REQUEST = 1;
    Byte LOGIN_RESPONSE = 2;
    Byte MESSAGE_REQUEST = 3;
    Byte MESSAGE_RESPONSE = 4;
}
