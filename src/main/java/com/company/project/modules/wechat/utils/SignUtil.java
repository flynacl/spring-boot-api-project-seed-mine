package com.company.project.modules.wechat.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * 生成签名
 */
public class SignUtil {

    /** 生成签名需要按照字典序拼接成如下字符串 */
    public final static String signString = "jsapi_ticket=TICKET&noncestr=NONCESTR&timestamp=TIMESTAMP&url=URL";

    /**
     * 
     * @param jsapi_ticket
     *            获取到的ticket
     * @param noncestr
     *            前台传入-随机生成的字符串
     * @param timeStamp
     *            生成签名的时间戳
     * @param url
     *            前台传入-当前的url
     * @return 生成的签名
     */
    public static String generateSignature(String jsapi_ticket, String noncestr, String timeStamp,
            String url) {
        // 将所有参数按照字典序拼接成一个字符串进行sha1加密
        String temp = signString.replace("TICKET", jsapi_ticket);
        temp = temp.replace("NONCESTR", noncestr);
        temp = temp.replace("TIMESTAMP", timeStamp);
        temp = temp.replace("URL", url);

        MessageDigest md = null;
        String signStr = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
            byte[] digest = md.digest(temp.getBytes());
            signStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return signStr;
    }

    /**
     * 方法名：byteToStr</br>
     * 详述：将字节数组转换为十六进制字符串</br>
     * 开发人员： 徐钊 </br>
     * 创建时间： 2016-1-20 </br>
     * 
     * @param byteArray
     * @return
     */
    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    /**
     * 方法名：byteToHexStr</br>
     * 详述：将字节转换为十六进制字符串</br>
     * 开发人员： 徐钊 </br>
     * 创建时间： 2016-1-20 </br>
     * 
     * @param mByte
     * @return
     */
    private static String byteToHexStr(byte mByte) {
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
                'F' };
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];
        String s = new String(tempArr);
        return s;
    }

    /**
     * 生成随机字符串 不超过32位
     * 
     * @param length
     */
    public static String generateRandomString(int length) {
        if (length > 32) {
            length = 32;
        }

        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }

        return sb.toString();
    }
}