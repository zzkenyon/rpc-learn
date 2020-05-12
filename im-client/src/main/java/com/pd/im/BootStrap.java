package com.pd.im;

import com.pd.im.handler.LoginResponseHandler;
import com.pd.im.handler.MessageResponseHandler;
import com.pd.im.protocal.*;
import com.pd.im.protocal.codec.PacketCodeC;
import com.pd.im.protocal.codec.PacketDecoder;
import com.pd.im.protocal.codec.PacketEncoder;
import com.pd.im.protocal.login.LoginRequestPacket;
import com.pd.im.protocal.login.LoginUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.util.Scanner;

/**
 * @description: im-client
 * @author: zhaozhengkang
 * @date: 2020-04-22 16:25
 */
public class BootStrap {
    public static void main(String[] args) {
        NioEventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 7, 4));
                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new PacketEncoder());
                        ch.pipeline().addLast(new LoginResponseHandler());
                        ch.pipeline().addLast(new MessageResponseHandler());
                    }
                });
        connect(bootstrap,"localhost",8000,5);
    }

    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                Channel channel = ((ChannelFuture) future).channel();
                // 连接成功之后，启动控制台线程
                startConsoleThread(channel);
            }
            // ...
        });
    }
    private static void startConsoleThread(Channel channel) {
        Scanner scanner = new Scanner(System.in);
        LoginRequestPacket loginRequest = new LoginRequestPacket();
        new Thread(() -> {
            while (!Thread.interrupted()) {
                if (LoginUtil.hasLogin(channel)) {
                    Scanner sc = new Scanner(System.in);
                    String line = sc.nextLine();
                    MessageRequestPacket packet = new MessageRequestPacket();
                    packet.setMessage(line);
                    ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(channel.alloc(), packet);
                    channel.writeAndFlush(byteBuf);
                }else {
                    System.out.print("输入用户名登录: ");
                    String username = scanner.nextLine();
                    loginRequest.setUserName(username);
                    // 密码使用默认的
                    loginRequest.setPassword("pwd");
                    // 发送登录数据包
                    channel.writeAndFlush(loginRequest);
                    waitForLoginResponse();
                }
            }
        }).start();
    }

    private static void waitForLoginResponse() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
    }
}
