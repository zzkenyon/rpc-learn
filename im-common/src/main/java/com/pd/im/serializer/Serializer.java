package com.pd.im.serializer;

/**
 * @description: thinking
 * @author: zhaozhengkang
 * @date: 2020-01-17 09:37
 */
public interface Serializer {
    byte JSON_SERIALIZER = 1;
    Serializer DEFAULT = new JSONSerializer();
    /**
     * 序列化算法
     */
    byte getSerializerAlgorithm();

    /**
     * java 对象转换成二进制
     */
    byte[] serialize(Object object);

    /**
     * 二进制转换成 java 对象
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}

