package com.pd;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: rpc-server
 * @author: zhaozhengkang
 * @date: 2020-04-18 22:08
 */

public class RpcServer implements ApplicationContextAware, InitializingBean {
    private ExecutorService pool = Executors.newCachedThreadPool();
    private int port;
    private Map<String,Object> serviceObjs = new HashMap<>();

    public RpcServer(int port) {
        this.port = port;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // socket 通信 发布服务
        try (ServerSocket serverSocket = new ServerSocket(port)){
            while (true){
                Socket socket = serverSocket.accept();
                pool.execute(new ProcessorHandler(socket,serviceObjs));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // 获取容器中的服务bean 封装成map
        Map<String,Object> beans = applicationContext.getBeansWithAnnotation(RpcService.class);
        for(Object service : beans.values()){
            Class<?> clazz = service.getClass();
            RpcService rpcService = clazz.getAnnotation(RpcService.class);
            String serviceKey = rpcService.value().getName() + rpcService.version();
            serviceObjs.put(serviceKey,service);
        }
    }
}
