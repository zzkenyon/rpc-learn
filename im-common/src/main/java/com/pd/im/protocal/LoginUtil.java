package com.pd.im.protocal;

import io.netty.channel.Channel;
import io.netty.util.Attribute;

/**
 * @description: thinking
 * @author: zhaozhengkang
 * @date: 2020-01-17 17:58
 */
public class LoginUtil {
    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel) {
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);
        return loginAttr.get() != null;
    }
}
