package com.pd.proxy;

import java.lang.reflect.Proxy;

/**
 * @description: rpc-client
 * @author: zhaozhengkang
 * @date: 2020-04-18 19:25
 */
public class RpcProxyFactory {
    public <T> T newProxyInstance(final Class<T> interfaceClass, final String host, final int port){
        return (T)Proxy.newProxyInstance(interfaceClass.getClassLoader(),new Class<?>[] {interfaceClass},
                new RemoteInvocationHandler(host,port));
    }
}
