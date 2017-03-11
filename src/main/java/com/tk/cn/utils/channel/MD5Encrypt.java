package com.tk.cn.utils.channel;





import java.security.MessageDigest;

public class MD5Encrypt {

    public MD5Encrypt() {}

    private final static String[] hexDigits = {
                                              "0", "1", "2", "3", "4", "5", "6",
                                              "7",
                                              "8", "9", "a", "b", "c", "d", "e",
                                              "f"};

    /**
     * 转换字节数组为16进制字串
     * @param b 字节数组
     * @return 16进制字串
     */
    public static String byteArrayToString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    /**
     * MD5加密方法
     * @param origin String
     * @return String
     */
    public static String MD5Encode(String origin) {
        String resultString = null;

        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString =
                    byteArrayToString(md.digest(resultString.getBytes()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resultString;
    }
    public static void main(String[] args) {
    	//96e79218965eb72c92a549dd5a330112
	String s=	MD5Encrypt.MD5Encode("111111");
	System.out.println(s);
	}
}
