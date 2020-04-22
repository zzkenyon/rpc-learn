package com.pd.bio.servlet;

import com.pd.bio.http.PDRequest;
import com.pd.bio.http.PDResponse;
import com.pd.bio.http.PDServlet;

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
        response.write("This is 1 Serlvet");
    }
}
