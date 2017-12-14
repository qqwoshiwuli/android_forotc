package com.gqfbtc.Utils;

import android.util.Log;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by 郭青枫 on 2017/11/9.
 */

public class AESUtil {

    public static String algorithm ="AES";

    public static String enctrypt(String content,String password){
        SecretKey key = getKey(algorithm,password);
        byte [] enCodeFormat = key.getEncoded();
        SecretKeySpec ss = new SecretKeySpec(password.getBytes(), algorithm);
        try {
            IvParameterSpec iv = new IvParameterSpec(password.getBytes("UTF-8"));
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, ss,iv);
            //            cipher.init(Cipher.ENCRYPT_MODE, ss);
            byte b [] = cipher.doFinal(content.getBytes());
            return byteToHex(b);
        } catch (Exception e) {
            Log.e("AES 加密失败",e.getLocalizedMessage());
        }

        return null;
    }


    public static String decrypt(byte [] content,String password){
        //        SecretKey key = getKey(algorithm,password);
        //        byte [] enCodeFormat = key.getEncoded();
        SecretKeySpec ss = new SecretKeySpec(password.getBytes(), algorithm);

        try {
            IvParameterSpec iv = new IvParameterSpec(password.getBytes("UTF-8"));
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, ss,iv);
            //            cipher.init(Cipher.DECRYPT_MODE, ss);
            byte b [] = cipher.doFinal(content);
            return new String(b);
        } catch (Exception e) {
            Log.e("AES 解密失败",e.getLocalizedMessage());
        }

        return null;
    }
    /**
     * 使用keygenerator 依赖平台，导致Android和java加密出来的密文不一致
     *
     * @param algorithm
     * @param password
     * @return
     */
    public static SecretKey getKey(String algorithm,String password){

        try {
            KeyGenerator gen = KeyGenerator.getInstance(algorithm);
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(password.getBytes());

            gen.init(128, secureRandom);
            //            gen.init(128);
            return gen.generateKey();
        } catch (NoSuchAlgorithmException e) {
            Log.e("getKey 获取加密密钥失败 ",e.getLocalizedMessage());
            e.printStackTrace();
        }
        return null;
    }

    private static String byteToHex(byte [] b){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            String s = Integer.toHexString(b[i] & 0xFF);
            if(s.length()==1){
                s = '0'+s;
            }
            sb.append(s.toUpperCase());
        }
        return sb.toString();
    }

    public static byte[] hexStrToByte(String hexStr) {
        if (hexStr == null || hexStr.equals("")) {
            return null;
        }
        hexStr = hexStr.toUpperCase();
        int length = hexStr.length() / 2;
        char[] hexChars = hexStr.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    private static byte charToByte(char c) {
        int index = "0123456789ABCDEF".indexOf(c);
        if(index==-1){
            index = "0123456789abcdef".indexOf(c);
        }
        return (byte)index;
    }


}