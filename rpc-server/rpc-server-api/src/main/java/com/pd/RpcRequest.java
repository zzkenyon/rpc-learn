package com.pd;

import java.io.Serializable;

/**
 * @description: rpc-server
 * @author: zhaozhengkang
 * @date: 2020-04-18 16:00
 */
public class RpcRequest implements Serializable {
    private String className;
    private String methodName;
    private Object[] args;
    private String version;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}
