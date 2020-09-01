package com.annotation.test;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;

public class AESUtil {

    private static final String ALGORITHM = "AES";

    private static String UTF8 = "UTF-8";

    private AESUtil() {
    }


    /**
     * 加密
     *
     * @param plainText 明文
     * @return
     */
    public static String encrypt(String plainText, String password) {
        if (plainText==null||plainText.trim()=="") {
            return plainText;
        }
        try {
            return toHex(encrypt(plainText.getBytes(UTF8), password));
        } catch (UnsupportedEncodingException e) {
            //log.error("error in encrypt:", e);
        } catch (Exception e) {
            //log.error("error in encrypt:", e);
        }
        return null;
    }


    /**
     * 解密 以String密文输入,String明文输出
     */
    public static String decrypt(String cipherText, String password) {
        try {
            byte[] bytes = decrypt(hexTobytes(cipherText), password);
            return new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // log.error("error in encrypt:", e);
        } catch (Exception e) {
            //log.error("error in encrypt:", e);

        }
        return null;
    }

    /**
     * 加密以byte[]明文输入,byte[]密文输出
     *
     * @param byteS
     * @return
     */
    public static byte[] encrypt(byte[] byteS, String pwd) throws Exception {
        byte[] byteFina = null;
        Cipher cipher;
        try {
            cipher = Cipher.getInstance(ALGORITHM);
            SecretKeySpec keySpec = new SecretKeySpec(getKey(pwd), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            byteFina = cipher.doFinal(byteS);
        } catch (Exception e) {
            throw e;
        }
        return byteFina;
    }

    /**
     * 解密以byte[]密文输入,以byte[]明文输出
     *
     * @param byteD
     * @return
     */
    public static byte[] decrypt(byte[] byteD, String pwd) throws Exception {
        Cipher cipher;
        byte[] byteFina = null;
        try {
            cipher = Cipher.getInstance(ALGORITHM);

            SecretKeySpec keySpec = new SecretKeySpec(getKey(pwd), "AES");

            cipher.init(Cipher.DECRYPT_MODE, keySpec);

            byteFina = cipher.doFinal(byteD);

        } catch (Exception e) {
            throw e;
        }
        return byteFina;
    }

    private static byte[] getKey(String password) throws UnsupportedEncodingException {
        // 使用256位密码
        String pwd = password;
        if (pwd.length() > 16) {
            pwd = pwd.substring(0, 16);
        } else if (pwd.length() < 16) {
            int count = (16 - pwd.length());
            for (int i = 0; i < count; i++) {
                pwd += "0";
            }
        }

        return pwd.getBytes(UTF8);
    }

    public static void main(String[] args) {
        String text = "{\n" +
                "\t\"method\": \"shield.risk.data.upload.contact\",\n" +
                "\t\"sequence_id\":\"20200807000003011596788639138001\",\n" +
                "\t\"data\": [\n" +
                "\t{\n" +
                "\t\t\"name\": \"jack chen\",\n" +
                "\t\t\"phone\": \"9999999999\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"name\": \"jack chen2\",\n" +
                "\t\t\"phone\": \"9999999998\"\n" +
                "\t}\n" +
                "\t]\n" +
                "}";
        String encrypt = encrypt(text, "123456");
        System.out.println(encrypt);
    }

    /**
     * Convert byte array to hex string
     */
    public static String toHex(byte[] bytes) {
        StringBuffer sb = new StringBuffer(bytes.length * 3);
        for (int i = 0; i < bytes.length; i++) {
            int val = ((int) bytes[i]) & 0xff;
            if (val < 16)
                sb.append("0");
            sb.append(Integer.toHexString(val));

        }

        return sb.toString();
    }

    /**
     * Convert hex string to byte array
     *
     * @param str
     * @return
     */
    public static byte[] hexTobytes(String str) {
        int l = str.length();
        if ((l % 2) != 0) {
            throw new IllegalArgumentException("长度不是偶数!");
        }
        byte[] bytes = new byte[l / 2];
        for (int i = 0; i < l; i = i + 2) {
            String item = str.substring(i, i + 2);
            bytes[i / 2] = (byte) Integer.parseInt(item, 16);
        }

        return bytes;
    }
}
