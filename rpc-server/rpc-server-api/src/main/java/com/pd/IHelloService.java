package com.pd;

/**
 * @description: rpc-server
 * @author: zhaozhengkang
 * @date: 2020-04-18 15:40
 */
public interface IHelloService {
    String sayHello(String content);
    String saveUser(User user);
}
