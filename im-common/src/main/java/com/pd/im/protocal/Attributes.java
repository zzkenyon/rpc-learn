package com.pd.im.protocal;

import io.netty.util.AttributeKey;

/**
 * @description: thinking
 * @author: zhaozhengkang
 * @date: 2020-01-17 17:59
 */
public interface Attributes {
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
}
