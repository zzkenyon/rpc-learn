package com.pd.im.session;

import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: im-server
 * @author: zhaozhengkang
 * @date: 2020-05-12 16:08
 */
public class Chat {
    private Map<User, Channel> chaters = new HashMap<>(8);

    public Channel addChater(User user,Channel channel){
        return chaters.put(user,channel);
    }
}
