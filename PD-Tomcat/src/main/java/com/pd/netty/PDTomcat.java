package com.pd.netty;
import com.pd.netty.handler.PDTomcatHandler;
import com.pd.netty.http.PDServlet;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 1 配置启动端口
 * 2 配置web.xml
 * 3 读取配置的servlet，启动
 * 4 浏览器 http请求
 * 5 解析请求 从请求中拿到url 将相应的servlet实例化
 * 6 调用service方法执行servlet逻辑
 * 7 包装response 返回给浏览器
 */
public class PDTomcat {

    private static final int port = 8080;
    private Map<String, PDServlet> servletMap = new HashMap<>();
    private Properties webxml = new Properties();
    private void init(){
        //加载web.xml文件,同时初始化 ServletMap对象
        try{
            String WEB_INF = this.getClass().getResource("/").getPath();
            FileInputStream fis = new FileInputStream(WEB_INF + "web.properties");

            webxml.load(fis);

            for (Object k : webxml.keySet()) {
                String key = k.toString();
                if(key.endsWith(".url") && key.startsWith("netty.")){
                    String servletName = key.replaceAll("\\.url$", "");
                    String url = webxml.getProperty(key);
                    String className = webxml.getProperty(servletName + ".className");
                    PDServlet obj = (PDServlet)Class.forName(className).newInstance();
                    servletMap.put(url, obj);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void start(){
        init();
        //监听线程组，boss
        NioEventLoopGroup parentGroup = new NioEventLoopGroup();
        //工作线程组，worker
        NioEventLoopGroup childGroup = new NioEventLoopGroup();
        //服务端启动引导类
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(parentGroup,childGroup)
                    // 指定io模型为nio
                    //.channel(OioServerSocketChannel.class)  指定为bio
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel ch) throws Exception {
                            //使用自定义的编解码器
                        /*ch.pipeline().addLast(new PDHttpRequestDecoder());
                        ch.pipeline().addLast(new PDHttpHandler(servletMap));
                        ch.pipeline().addLast(new PDHttpResponseEncoder());*/
                            //使用netty内置的http编解码器,只需关注业务内容

                            ch.pipeline().addLast(new HttpRequestDecoder());

                            ch.pipeline().addLast(new PDTomcatHandler(servletMap));

                            ch.pipeline().addLast(new HttpResponseEncoder());

                        }
                    })
                    // 针对主线程的配置 分配线程最大数量 128
                    .option(ChannelOption.SO_BACKLOG, 128)
                    // 针对子线程的配置 保持长连接
                    .childOption(ChannelOption.SO_KEEPALIVE, false);

            // 启动服务器
            ChannelFuture f = serverBootstrap.bind(port).sync();
            System.out.println("GP Tomcat 已启动，监听的端口是：" + port);
            f.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main( String[] args ) {
        try {
            new PDTomcat().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
