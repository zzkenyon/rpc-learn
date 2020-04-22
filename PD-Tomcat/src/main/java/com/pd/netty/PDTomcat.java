package com.pd.netty;
import com.pd.netty.handler.PDHttpHandler;
import com.pd.netty.handler.PDHttpRequestDecoder;
import com.pd.netty.handler.PDHttpResponseEncoder;
import com.pd.netty.http.PDServlet;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

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

    private static final int port = 8081;
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
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(parentGroup,childGroup)
                // 指定io模型为nio
                //.channel(OioServerSocketChannel.class)  指定为bio
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new PDHttpRequestDecoder());
                        ch.pipeline().addLast(new PDHttpHandler(servletMap));
                        ch.pipeline().addLast(new PDHttpResponseEncoder());
                    }
                }).bind(8080);

    }

    public static void main( String[] args ) {
        try {
            new PDTomcat().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
