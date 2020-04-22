package com.pd.netty.http;

/**
 * @description: PD-PDTomcat
 * @author: zhaozhengkang
 * @date: 2020-04-22 09:23
 */
public abstract class PDServlet {
    public void service(PDRequest request, PDResponse response)throws Exception{
        if("GET".equals(request.getMethod())){
            doGet(request,response);
        }else {
            doPost(request,response);
        }
    }

    protected abstract void doPost(PDRequest request, PDResponse response) throws Exception;

    protected abstract void doGet(PDRequest request, PDResponse response)throws Exception;



}
