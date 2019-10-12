package com.game.engine.common.util.encrypt;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.concurrent.ThreadLocalRandom;

public class AES {


    // 加密
    public static byte[] Encrypt(String content, String key) throws Exception {
        if (key == null) {
            System.out.print("Key 为空 null");
            return null;
        }
        // 判断 Key是否为16 位
        if (key.length() != 16) {
            System.out.print("Key 长度不是 16位");
            return null;
        }
        // random iv
        byte[] buff = new byte[16];
        ThreadLocalRandom.current().nextBytes(buff);
        byte[] raw = key.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// "算法/模式/补码方式"
        IvParameterSpec iv = new IvParameterSpec(buff);// 使用 CBC模式，需要一个向量
        // iv，可增加加

        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(content.getBytes());
        int totalLength = iv.getIV().length + encrypted.length;
        byte[] combine = new byte[totalLength];
        System.arraycopy(iv.getIV(), 0, combine, 0, iv.getIV().length);
        System.arraycopy(encrypted, 0, combine, iv.getIV().length,
                encrypted.length);
        return combine;
    }
}
