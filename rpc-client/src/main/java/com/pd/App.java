package com.pd;

import com.pd.proxy.RpcProxyClient;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        RpcProxyClient rpcProxyClient = new RpcProxyClient();
        IHelloService helloService = rpcProxyClient.clientProxy(IHelloService.class,"localhost",8080);
        Object o = helloService.sayHello("zzk");
        System.out.println((String) o);
    }
}
