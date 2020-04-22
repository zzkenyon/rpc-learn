package com.pd.bio.http;

import java.io.IOException;
import java.io.InputStream;

/**
 * @description: PD-PDTomcat
 * @author: zhaozhengkang
 * @date: 2020-04-22 09:23
 */
public class PDRequest {
    private String method;
    private String url;

    public PDRequest(InputStream is){
        byte[] buf = new byte[1024];
        String httpRequestContent;
        int len = 0;
        try {
            len = is.read(buf);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(len > 0 ){
             httpRequestContent = new String(buf,0,len);
             parseRequest(httpRequestContent);
        }
//        System.out.println(httpRequestContent);
    }

    private void parseRequest(String httpRequestContent) {
        String s = httpRequestContent.split("\\n")[0];
        String[] eles = s.split("\\s");
        method = eles[0];
        url = eles[1];
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }
}
