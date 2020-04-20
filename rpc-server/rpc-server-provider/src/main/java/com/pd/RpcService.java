package com.pd;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description: rpc-server
 * @author: zhaozhengkang
 * @date: 2020-04-18 22:05
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RpcService {
    Class<?> value();
    String version() default "";
}
