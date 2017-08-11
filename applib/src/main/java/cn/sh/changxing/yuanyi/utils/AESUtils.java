package cn.sh.changxing.yuanyi.utils;

import android.util.Log;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by yuanyi on 17-8-5.
 */
public class AESUtils {
    private AESUtils() {

    }

    public static final String KEY_ALGORITHM = "AES";
    public static final int KEY_SIZE = 128;


    /**
     * 获取密钥
     *
     * @param seed 种子
     */
    public static byte[] getRawKey(byte[] seed) {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance(KEY_ALGORITHM);
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            sr.setSeed(seed);
            keyGen.init(KEY_SIZE, sr);
            SecretKey secretKey = keyGen.generateKey();
            return secretKey.getEncoded();
        } catch (Exception e) {
            Log.e("tag", e.getMessage(), e);
        }

        return null;
    }

    public static byte[] encrypt(byte[] src, byte[] key) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, KEY_ALGORITHM);
        try {
            //Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
             /*cipher.init(Cipher.ENCRYPT_MODE, secreKeySpec, new IvParameterSpec(
                    new byte[cipher.getBlockSize()]));*/
            return cipher.doFinal(src);
        } catch (Exception e) {
            Log.e("tag", e.getMessage(), e);
        }

        return null;
    }

    public static byte[] decrypt(byte[] encoded, byte[] key) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, KEY_ALGORITHM);
        try {
            //Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            /*cipher.init(Cipher.DECRYPT_MODE, secreKeySpec, new IvParameterSpec(
                    new byte[cipher.getBlockSize()]));*/
            return cipher.doFinal(encoded);
        } catch (Exception e) {
            Log.e("tag", e.getMessage(), e);
        }

        return null;
    }
}
