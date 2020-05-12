package com.pd.im.session;

import io.netty.channel.Channel;
import lombok.Data;

/**
 * @description: im-server
 * @author: zhaozhengkang
 * @date: 2020-05-12 15:48
 */
@Data
public class Session {
    private User user;
    private Channel channel;

    public void bindToSession(Chat chat){
        chat.addChater(this.user,this.channel);
    }
}
