package com.pd.service;

import com.pd.IHelloService;
import com.pd.RpcService;
import com.pd.User;
import org.springframework.stereotype.Service;

/**
 * @description: rpc-server
 * @author: zhaozhengkang
 * @date: 2020-04-18 15:42
 */
@Service
@RpcService(value = IHelloService.class,version = "v2")
public class HelloServiceImpl_2 implements IHelloService {
    @Override
    public String sayHello(String content) {
        System.out.println("request sayHello from : " + content);
        return "v2  Response: hello, " + content;
    }

    @Override
    public String saveUser(User user) {
        System.out.println("request in saveUser: " + user);
        return "v2  SUCCESS";
    }
}
