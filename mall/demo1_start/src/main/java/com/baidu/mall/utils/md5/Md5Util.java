package com.baidu.mall.utils.md5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Md5Util {

    public static String getMd5(String content) {
        //获得消息消息摘要算法
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("md5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] contentBytes = content.getBytes();
        byte[] resultBytes = messageDigest.digest(contentBytes);//byte 8 2^8 ff
        int length = resultBytes.length;
        System.out.println("length:" + length);
        StringBuffer stringBuffer = new StringBuffer();
        for (byte resultByte : resultBytes) {
            int temp = resultByte & 0xff; //0-255
            String s = Integer.toHexString(temp); //0-15 0a  05 0-f
            if (s.length() == 1) {
                stringBuffer.append(0);
            }
            stringBuffer.append(s);
        }
        return stringBuffer.toString();
    }
}
