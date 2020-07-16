package com.zfs.mqttdemo2;

/**
 * @ClassName Test01.java
 * @Description TODO
 * @Author 朱福盛
 * @Date 2020/7/15 16:46
 * @Version 1.0
 */
public class Test01 {
    public static void main(String[] args) {

        byte[] bytes = hexStringToBytes("0a");
        System.out.println(bytes);
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
}
