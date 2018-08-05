package com.netease.timemachine.account.util;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 16:08 2018/7/28
 **/
public class ChildInvitationCode {

    /**PRIME1可以为任意随机数，最好和36以及10^n（n为用户id位数）互质*/
    private static final int PRIME1 = 13;
    /**加盐*/
    private static final int SALT = 200;
    /**验证码长度*/
    private static final int CODE_LENGTH = 6;
    /**ARY表示进制，这里是36*/
    private static final int ARY = 36;
    /**邀请码组成表*/
    private static final String HEX_36_Array = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    /**与CODE_LENGTH（本文中为7）互质*/
    private static final int PRIME2 = 7;

    public static String inviCodeGenerator(long id) {
        id = id * PRIME1;
        id = id + SALT;
        long[] b = new long[CODE_LENGTH];
        b[0] = id;
        for (int i = 0; i < 5; ++i) {
            b[i + 1] = b[i] / ARY;
            b[i] = (b[i] + b[0] * i) % ARY;
        }
        b[5] = (b[0] + b[1] + b[2]) * PRIME1 % ARY;
        //b[6] = (b[3] + b[4] + b[5]) * PRIME1 % ARY;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < CODE_LENGTH; ++i) {
            sb.append(HEX_36_Array.charAt((int)b[(i * PRIME2) % CODE_LENGTH]));
        }
        return sb.toString();
    }

    public static Long inviDecoding(String inviCode) {
        if (inviCode.length() != CODE_LENGTH) {
            return -1L;
        }
        Long res = 0L;
        int a[] = new int[CODE_LENGTH];
        int b[] = new int[CODE_LENGTH];
        char[] c = new char[CODE_LENGTH];
        for (int i = 0; i < CODE_LENGTH; ++i) {
            a[(i * PRIME2) % CODE_LENGTH] = i;
        }
        for (int i = 0; i < CODE_LENGTH; ++i) {
            c[i] = inviCode.charAt(a[i]);
        }
        for (int i = 0; i < CODE_LENGTH; ++i) {
            a[i] = HEX_36_Array.indexOf(c[i]);
        }
        b[5] = (a[0] + a[1] + a[2]) * PRIME1 % ARY;
        //b[6] = (a[3] + a[4] + a[5]) * PRIME1 % ARY;
        //if (a[5] != b[5] || a[6] != b[6]) {
        if (a[5] != b[5]) {
            return -1L;
        }
        for (int i = 4; i >= 0; --i) {
            b[i] = (a[i] - a[0] * i + ARY * i) % ARY;
        }
        for (int i = 4; i > 0; --i) {
            res += b[i];
            res *= ARY;
        }
        res = ((res + b[0]) - SALT) / PRIME1;
        return res;
    }

    public static void main(String[] args) {
        System.out.println(inviCodeGenerator(1L));
        System.out.println(inviDecoding("7C41YR"));
    }
}
