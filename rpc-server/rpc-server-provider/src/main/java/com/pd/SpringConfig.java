package com.pd;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @description: rpc-server
 * @author: zhaozhengkang
 * @date: 2020-04-18 21:42
 */
@Configuration
@ComponentScan(basePackages = "com.pd")
public class SpringConfig{
    @Bean
    public RpcServer rpcServer(){
        return new RpcServer(8080);
    }
}
