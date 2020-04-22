package com.pd.bio;
import com.pd.bio.http.PDRequest;
import com.pd.bio.http.PDResponse;
import com.pd.bio.http.PDServlet;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Hello world!
 *
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
                if(key.endsWith(".url")){
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
        try(ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("GP Tomcat 已启动，监听的端口是：" + port);
            while (true) {
                Socket socket = serverSocket.accept();
                process(socket);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void process(Socket socket) throws Exception{
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();
        PDRequest request = new PDRequest(is);
        PDResponse response = new PDResponse(os);

        String url = request.getUrl();
        if(servletMap.containsKey(url)){
            //6、调用实例化对象的service()方法，执行具体的逻辑doGet/doPost方法
            servletMap.get(url).service(request,response);
        }else{
            response.write("404 - Not Found");
        }

        os.flush();
        os.close();
        is.close();
        socket.close();
    }

    public static void main( String[] args ) {
        try {
            new PDTomcat().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //1 配置启动端口

    //2 配置web.xml

    //3、读取配置的servlet，启动

    //4 浏览器 http请求

    //5 解析请求 从请求中拿到url 将相应的servlet实例化

    //6 调用service方法执行servlet逻辑

    //7 包装response 返回给浏览器


}
