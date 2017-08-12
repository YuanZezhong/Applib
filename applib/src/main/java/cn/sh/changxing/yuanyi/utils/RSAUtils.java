package cn.sh.changxing.yuanyi.utils;

import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;

/**
 * Created by yuanyi on 17-8-5.
 */
public class RSAUtils {
    private RSAUtils() {

    }

    /**
     * 加密算法RSA
     */
    public static final String KEY_ALGORITHM = "RSA";

    /**
     * 签名算法
     */
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    /**
     * RSA最大加密明文大小
     */
    public static final int MAX_ENCRYPT_BLOCK = 128 - 11;

    /**
     * RSA最大解密密文大小
     */
    public static final int MAX_DECRYPT_BLOCK = 128;


    /**
     * 用Base64给数据加密
     */
    public static String encryptByBase64(byte[] data) {
        return Base64.encodeToString(data, Base64.DEFAULT);
    }


    /**
     * 解密使用Base64加密的内容
     */
    public static byte[] decryptByBase64(String base64String) {
        return Base64.decode(base64String, Base64.DEFAULT);
    }


    /**
     * 获得数据的MD5
     */
    public static byte[] getMD5(byte[] data) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(data);
            return md5.digest();
        } catch (Exception e) {
            Log.e("tag", e.getMessage(), e);
        }

        return null;
    }

    /**
     * 获取SHA1
     */
    public static byte[] getSHA1(byte[] data) {
        try {
            MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
            return sha1.digest(data);
        } catch (NoSuchAlgorithmException e) {
            Log.e("tag", e.getMessage(), e);
        }
        return null;
    }

    /**
     * 将MD5数据转换成字符串
     */
    public static String digestToString(byte[] digest) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < digest.length; ++i) {
            if ((digest[i] & 0XFF) < 0X10) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(digest[i] & 0XFF));
            if (i < digest.length - 1) {
                sb.append(":");
            }
        }

        return sb.toString().toUpperCase();
    }


    /**
     * 密钥类型
     */
    public enum KeyType {
        PRIVATE, PUBLIC
    }


    /**
     * 获取密钥对(私钥， 公钥)
     */
    public static Map<KeyType, Object> getKeyPair(int keySize) {
        try {
            KeyPairGenerator gen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
            gen.initialize(keySize);
            KeyPair keyPair = gen.generateKeyPair();
            PrivateKey priKey = keyPair.getPrivate();
            PublicKey pubKey = keyPair.getPublic();
            HashMap<KeyType, Object> map = new HashMap<>();
            map.put(KeyType.PRIVATE, priKey);
            map.put(KeyType.PUBLIC, pubKey);

            return map;
        } catch (NoSuchAlgorithmException e) {
            Log.e("tag", e.getMessage(), e);
        }

        return null;
    }


    /**
     * 从密钥对中获取公钥
     */
    public static RSAPublicKey getPublicKey(Map<KeyType, Object> map) {
        RSAPublicKey publicKey = (RSAPublicKey) map.get(KeyType.PUBLIC);
        return publicKey;
    }


    /**
     * 从密钥对中获取私钥
     */
    public static RSAPrivateKey getPrivateKey(Map<KeyType, Object> map) {
        RSAPrivateKey privateKey = (RSAPrivateKey) map.get(KeyType.PRIVATE);
        return privateKey;
    }


    /**
     * 用私钥生成数字签名
     *
     * @param data 已加密的数据
     * @param key  私钥
     */
    public static byte[] sign(byte[] data, RSAPrivateKey key) {
        try {
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initSign(key);
            signature.update(data);
            return signature.sign();
        } catch (Exception e) {
            Log.e("tag", e.getMessage(), e);
        }

        return null;
    }


    /**
     * 校验数字签名
     *
     * @param data 已加密的数据
     * @param key  公钥
     * @param sign 数字签名
     */
    public static boolean verify(byte[] data, RSAPublicKey key, byte[] sign) {
        try {
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initVerify(key);
            signature.update(data);
            return signature.verify(sign);
        } catch (Exception e) {
            Log.e("tag", e.getMessage(), e);
        }
        return false;
    }


    /**
     * 使用公钥加密给定数据
     *
     * @param src 需要加密的数据
     * @param key 加密用的公钥
     */
    public static byte[] encryptByPublicKey(byte[] src, RSAPublicKey key) {
        return encryptByKey(src, key.getModulus().bitLength() / 8 - 11, key);
    }


    /**
     * 使用私钥加密给定数据
     *
     * @param src 需要加密的数据
     * @param key 加密用的私钥
     */
    public static byte[] encryptByPrivateKey(byte[] src, RSAPrivateKey key) {
        return encryptByKey(src, key.getModulus().bitLength() / 8 - 11, key);
    }


    /**
     * 加密给定数据
     *
     * @param src      需要加密的数据
     * @param blockLen 每次加密的最大长度
     * @param key      加密用的钥匙
     */
    private static byte[] encryptByKey(byte[] src, int blockLen, Key key) {
        List<byte[]> allData = splitData(src, blockLen);
        try {
            Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            for (int i = 0; i < allData.size(); ++i) {
                allData.set(i, cipher.doFinal(allData.get(i)));
            }
            return mergeData(allData);
        } catch (Exception e) {
            Log.e("tag", e.getMessage(), e);
        }

        return null;
    }


    /**
     * 使用公钥解密
     *
     * @param src 需要解密的数据
     * @param key 用于解密的公钥
     */
    public static byte[] decryptByPublicKey(byte[] src, RSAPublicKey key) {
        return decryptByKey(src, key.getModulus().bitLength() / 8, key);
    }


    /**
     * 使用私钥解密
     *
     * @param src 需要解密的数据
     * @param key 用于解密的私钥
     */
    public static byte[] decryptByPrivateKey(byte[] src, RSAPrivateKey key) {
        return decryptByKey(src, key.getModulus().bitLength() / 8, key);
    }


    /**
     * 解密给定数据
     *
     * @param src      需要解密的数据
     * @param blockLen 每次解密的最大长度
     * @param key      解密的钥匙
     */
    private static byte[] decryptByKey(byte[] src, int blockLen, Key key) {
        List<byte[]> allData = splitData(src, blockLen);
        try {
            Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            for (int i = 0; i < allData.size(); ++i) {
                allData.set(i, cipher.doFinal(allData.get(i)));
            }
            return mergeData(allData);
        } catch (Exception e) {
            Log.e("tag", e.getMessage(), e);
        }
        return null;
    }


    /**
     * 合并一组数据
     *
     * @param allData 需要合并的分组数据
     */
    private static byte[] mergeData(List<byte[]> allData) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            for (byte[] data : allData) {
                baos.write(data);
            }
            baos.flush();
            return baos.toByteArray();
        } catch (IOException e) {
            Log.e("tag", e.getMessage(), e);
        } finally {
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    Log.e("tag", e.getMessage(), e);
                }
            }
        }
        return null;
    }

    /**
     * 切割数据(将数据分块)
     *
     * @param data     需要处理的数据
     * @param blockLen 每一块的长度
     */
    private static List<byte[]> splitData(byte[] data, int blockLen) {
        List<byte[]> result = new ArrayList<byte[]>();
        if (data.length <= blockLen) {
            result.add(data);
        } else {
            int end = data.length % blockLen;
            int total = data.length / blockLen + (end == 0 ? 0 : 1);
            for (int i = 0; i < total; ++i) {
                if (end != 0 && i == total - 1) {
                    result.add(Arrays.copyOfRange(data, i * blockLen, i * blockLen + end));
                } else {
                    result.add(Arrays.copyOfRange(data, i * blockLen, (i + 1) * blockLen));
                }
            }
        }
        return result;
    }
}
