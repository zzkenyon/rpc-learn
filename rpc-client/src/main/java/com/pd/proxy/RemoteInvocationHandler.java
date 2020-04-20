package com.pd.proxy;

import com.pd.net.RpcNetTransport;
import com.pd.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @description: rpc-client
 * @author: zhaozhengkang
 * @date: 2020-04-18 19:39
 */
public class RemoteInvocationHandler implements InvocationHandler {
    private String host;
    private int port;

    public RemoteInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * 这里封装rpc请求
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setArgs(args);
        rpcRequest.setVersion("v2");
        return new RpcNetTransport(host,port).send(rpcRequest);
    }
}
