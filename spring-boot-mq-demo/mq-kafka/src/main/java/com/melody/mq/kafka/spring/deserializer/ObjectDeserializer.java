package com.melody.mq.kafka.spring.deserializer;

import org.apache.kafka.common.serialization.Deserializer;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Map;

/**
 * 用户的反序列化器
 *
 * @author zqhuangc
 */
public class ObjectDeserializer implements Deserializer<Object> {


    @Override
    public void configure(Map configs, boolean isKey) {

    }

    @Override
    public Object deserialize(String topic, byte[] data) {

        Object object = null;

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);

        try {
            ObjectInputStream inputStream = new ObjectInputStream(byteArrayInputStream);

            object = inputStream.readObject();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println(" topic : " + topic + " , deserialized object :" + object);

        return object;
    }

    @Override
    public void close() {

    }

}
