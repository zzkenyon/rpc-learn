package com.pd.netty.servlet;


import com.pd.netty.http.PDRequest;
import com.pd.netty.http.PDResponse;
import com.pd.netty.http.PDServlet;

/**
 * @description: PD-PDTomcat
 * @author: zhaozhengkang
 * @date: 2020-04-22 09:27
 */
public class MyServlet_1 extends PDServlet {
    public void doGet(PDRequest request, PDResponse response) throws Exception {
        this.doPost(request, response);
    }

    public void doPost(PDRequest request, PDResponse response) throws Exception {
        response.setContent("This is 1 Serlvet");
    }
}
