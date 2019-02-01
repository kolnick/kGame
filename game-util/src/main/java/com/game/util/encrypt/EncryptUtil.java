package com.game.util.encrypt;

import com.game.meta.SplitSymbolConst;
import com.game.util.convert.ConvertUtil;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Base64;

/**
 * 加解密工具类
 *
 * @author caochaojie
 * 2018/10/25 10:36
 */
public class EncryptUtil {
    
    /**
     * 异或加密
     *
     * @param code        待加密字符串
     * @param key         秘钥
     * @param charsetName 字符编码
     */
    public static String xor(String code, byte[] key, String charsetName) {
        if (code == null || key == null) {
            return null;
        }
        byte[] cd = code.getBytes();
        byte[] encodes = new byte[cd.length];
        int x = 0;
        for (int i = 0; i < cd.length; i++) {
            encodes[i] = (byte) (cd[i] ^ key[x]);
            x++;
            if (x == key.length) {
                x = 0;
            }
        }
        String str = null;
        try {
            str = new String(encodes, charsetName);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }
    
    /**
     * base64编码
     *
     * @param str 字符串
     */
    public static String encodeBase64(String str) {
        Base64.Encoder encoder = Base64.getEncoder();
        try {
            return encoder.encodeToString(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 解码Base64
     *
     * @param str 字符串
     */
    public static String decodeBase64(String str) {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decode = decoder.decode(str);
        return new String(decode, 0, decode.length, Charset.forName("UTF-8"));
    }
    
    
    /**
     * MD5
     *
     * @param str 字符串
     * @param key 秘钥
     */
    public static String md5(String str, String key) {
        return DigestUtils.md5Hex(str + "|" + key);
    }
    
    /**
     * MD5
     *
     * @param str 字符串
     */
    public static String md5(String str) {
        return DigestUtils.md5Hex(str);
    }
    
    /**
     * @param md5  md5字符串
     * @param str  明文
     * @param keys 加密参数
     */
    public static boolean verifyMd5(String md5, String str, String... keys) {
        if (keys == null || str == null || md5 == null) {
            return false;
        }
        StringBuilder sb = new StringBuilder(str);
        sb.append(SplitSymbolConst.ShuXian);
        String verifyText = ConvertUtil.toString(SplitSymbolConst.ShuXian, keys);
        sb.append(verifyText);
        return md5.equalsIgnoreCase(DigestUtils.md5Hex(sb.toString()));
    }
    
    
}
