package com.pd;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;

/**
 * @description: rpc-server
 * @author: zhaozhengkang
 * @date: 2020-04-18 15:52
 */
public class ProcessorHandler implements Runnable {
    private Socket socket;
    private Map<String,Object> serviceObj;
    public ProcessorHandler(Socket socket,Map<String,Object> serviceObj) {
        this.socket = socket;
        this.serviceObj = serviceObj;
    }
    @Override
    public void run() {
        try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())){

            RpcRequest rpcRequest = (RpcRequest) in.readObject();
            Object result = invoke(rpcRequest);
            out.writeObject(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Object invoke(RpcRequest rpcRequest) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object[] args = rpcRequest.getArgs();
        Class<?>[] types = new Class[args.length];
        for(int i = 0; i < args.length; i++){
            types[i]= args[i].getClass();
        }
        Class clazz = Class.forName(rpcRequest.getClassName());
        Method method = clazz.getMethod(rpcRequest.getMethodName(),types);
        String serviceKey = rpcRequest.getClassName() + rpcRequest.getVersion();
        return method.invoke(serviceObj.get(serviceKey),args);
    }
}
