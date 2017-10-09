package com.google.ssmm.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * 实现描述：
 *
 * @author lvke
 * @version v1.0.0
 * @see
 * @since 16/7/14 16:54
 */
public final class DESTools {

    private static final Logger logger= LoggerFactory.getLogger(DESTools.class);

    private static  final  String SECRET_KEY="f601f6ed1879f961625a2d7ec39f3ac4";

    private static Cipher cipher;

    private static SecretKey sk;

    static {
        try {
            sk = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(SECRET_KEY.getBytes()));
            cipher=Cipher.getInstance("DES");
        } catch (Exception e) {
            logger.error("SecretKeyFactory init error e={}",e);
            sk=null;
            cipher=null;
        }
    }

    public static byte[] encrypt(byte[] src) {
        try{
            cipher.init(Cipher.ENCRYPT_MODE, sk, new SecureRandom());
            return cipher.doFinal(src);
        }catch(Exception e){
            logger.error("encrypt error,e={}", e);
        }
        return null;
    }

    public static String encrypt(String src){
        try{
           return Base64.getEncoder().encodeToString(encrypt(src.getBytes()));
        }catch(Exception e){
            logger.error("encrypt error,e={}",e);
        }
        return null;
    }

    public static byte[] decrypt(byte[] src){
        try{
            cipher.init(Cipher.DECRYPT_MODE, sk, new SecureRandom());
            return cipher.doFinal(src);
        }catch(Exception e){
            logger.error("decrypt error,e={}",e);
        }
        return null;
    }

    public static String decrypt(String src){
        try{
            return new String(decrypt(Base64.getDecoder().decode(src)));
        }catch(Exception e){
            logger.error("decrypt error,e={}",e);
        }
        return null;
    }
}
