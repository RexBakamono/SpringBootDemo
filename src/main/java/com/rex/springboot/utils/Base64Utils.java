package com.rex.springboot.utils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * base64 工具
 */
public class Base64Utils {
    /**
     * 加密
     * @param str
     * @return
     */
    public static String Encoder(String str) throws UnsupportedEncodingException {
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] textByte = str.getBytes("UTF-8");
        return encoder.encodeToString(textByte);
    }

    /**
     * 解密
     * @param str
     * @return
     */
    public static String Decoder(String str) throws UnsupportedEncodingException {
        Base64.Decoder decoder = Base64.getDecoder();
        return new String(decoder.decode(str), "UTF-8");
    }
}
