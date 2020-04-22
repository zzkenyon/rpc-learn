package com.pd.bio.http;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @description: PD-PDTomcat
 * @author: zhaozhengkang
 * @date: 2020-04-22 09:23
 */
public class PDResponse {
    private OutputStream os;
    public PDResponse(OutputStream os) {
        this.os = os;
    }

    public void write(String resp){
        StringBuilder sb = new StringBuilder();
        sb.append("Http/1.1 200 OK\n")
                .append("Content-Type: text/html;\n")
                .append("\r\n")
                .append(resp);
        try {
            os.write(sb.toString().getBytes());
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
