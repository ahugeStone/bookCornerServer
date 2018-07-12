package com.ahuang.bookCornerServer.util;

import java.io.*;
import java.util.Base64;

/**
 * 序列化工具类
 *
 * @author ahuang
 * @version V1.0
 * @Title: SerializationUtils
 * @Program: bookCornerServer
 * @Package com.ahuang.bookCornerServer.util
 * @create 2018-07-12 20:27
 */
public class SerializationUtils {
    /**
     * @param obj - object to serialize to a byte array
     * @return byte array containing the serialized obj
     */
    public static String serialize(Object obj) {
        byte[] result = null;
        ByteArrayOutputStream fos = null;

        try {
            fos = new ByteArrayOutputStream();
            ObjectOutputStream o = new ObjectOutputStream(fos);
            o.writeObject(obj);
            result = fos.toByteArray();
        } catch (IOException e) {
            System.err.println(e);
        } finally {
            try {
                assert fos != null;
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return Base64.getEncoder().encodeToString(result);
    }


    /**
     * @param arr - the byte array that holds the serialized object
     * @return the deserialized object
     */
    public static Object deserialize(String arr) {
        InputStream fis = null;

        try {
            fis = new ByteArrayInputStream(Base64.getDecoder().decode(arr));
            ObjectInputStream o = new ObjectInputStream(fis);
            return o.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e);
        } finally {
            try {
                assert fis != null;
                fis.close();
            } catch (Exception ignored) {
            }
        }

        return null;
    }

}
