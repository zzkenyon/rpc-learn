package com.pd.bio.servlet;

import com.pd.bio.http.PDRequest;
import com.pd.bio.http.PDResponse;
import com.pd.bio.http.PDServlet;

/**
 * @description: PD-PDTomcat
 * @author: zhaozhengkang
 * @date: 2020-04-22 09:27
 */
public class MyServlet_2 extends PDServlet {
    protected void doPost(PDRequest request, PDResponse response) throws Exception{
        response.write("This is 2 Serlvet");

    }

    protected void doGet(PDRequest request, PDResponse response) throws Exception{
        this.doPost(request, response);
    }
}
