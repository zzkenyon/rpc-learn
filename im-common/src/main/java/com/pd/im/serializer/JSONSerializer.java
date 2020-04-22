package com.pd.im.serializer;

import com.alibaba.fastjson.JSON;

/**
 * @description: thinking
 * @author: zhaozhengkang
 * @date: 2020-01-17 09:38
 */
public class JSONSerializer implements Serializer {
    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.json;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes,clazz);
    }
}
