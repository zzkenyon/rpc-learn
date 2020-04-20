package com.pd.net;

import com.pd.RpcRequest;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @description: rpc-server
 * @author: zhaozhengkang
 * @date: 2020-04-18 20:32
 */
public class RpcNetTransport {
    private String host;
    private int port;

    public RpcNetTransport(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Object send(RpcRequest rpcRequest){
        try(Socket socket = new Socket(host,port);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
            out.writeObject(rpcRequest);
            return in.readObject();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
