package com.pd.im.session;

import io.netty.channel.Channel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @description: im-server
 * @author: zhaozhengkang
 * @date: 2020-05-12 15:52
 */
@Data
@AllArgsConstructor
public class User {
    private String userName;
    private String userId;

}
