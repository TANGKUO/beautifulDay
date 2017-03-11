package com.tk.cn.common.sign;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.SignatureException;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class SignUtils {
	private static Logger log = LoggerFactory.getLogger(SignUtils.class);
	
    private SignUtils() {
    }

    /**
     * 给TOP请求做MD5签名。
     * 
     * @param sortedParams 所有字符型的TOP请求参数
     * @param secret 签名密钥
     * @return 签名
     * @throws IOException
     */
    public static String signRequestNew(TreeMap<String, String> sortedParams, String secret) throws IOException {
        // 第一步：把字典按Key的字母顺序排序,参数使用TreeMap已经完成排序
        Set<Entry<String, String>> paramSet = sortedParams.entrySet();

        // 第二步：把所有参数名和参数值串在一起
        StringBuilder query = new StringBuilder();
        for (Entry<String, String> param : paramSet) {
            if (!StringUtils.isEmpty(param.getKey())
                && !StringUtils.isEmpty(param.getValue())) {
                query.append(param.getKey()).append("=").append(param.getValue());
            }
        }

        log.info("获取当APP请求参数，签名前值为：" + query.toString());
        // 第三步：使用MD5/HMAC加密
        String data = query.toString() + secret;
        return DigestUtils.md5Hex(getContentBytes(data, "UTF-8"));
    }
    
    /**
     * 签名字符串
     * @param content 需要签名的字符串
     * @param secret 密钥
     * @param input_charset 编码格式
     * @return 签名结果
     */
    public static String sign(String content, String secret, String input_charset) {
    	content = content + secret;
        return DigestUtils.md5Hex(getContentBytes(content, input_charset));
    }
    
    /**
     * 签名字符串
     * @param content 需要签名的字符串
     * @param sign 签名结果
     * @param secret 密钥
     * @param input_charset 编码格式
     * @return 签名结果
     */
    public static boolean verify(String content, String sign, String secret, String input_charset) {
    	content = content + secret;
    	String mysign = DigestUtils.md5Hex(getContentBytes(content, input_charset));
    	if(mysign.equals(sign)) {
    		return true;
    	}
    	return false;
    }

    /**
     * @param content
     * @param charset
     * @return
     * @throws SignatureException
     * @throws UnsupportedEncodingException 
     */
    private static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }
    
    /**
     * 获取文件的真实后缀名。目前只支持JPG, GIF, PNG, BMP四种图片文件。
     * 
     * @param bytes 文件字节流
     * @return JPG, GIF, PNG or null
     */
    public static String getFileSuffix(byte[] bytes) {
        if (bytes == null || bytes.length < 10) {
            return null;
        }

        if (bytes[0] == 'G' && bytes[1] == 'I' && bytes[2] == 'F') {
            return "GIF";
        }
        if (bytes[1] == 'P' && bytes[2] == 'N' && bytes[3] == 'G') {
            return "PNG";
        }  
        if (bytes[6] == 'J' && bytes[7] == 'F' && bytes[8] == 'I' && bytes[9] == 'F') {
            return "JPG";
        }
        if (bytes[0] == 'B' && bytes[1] == 'M') {
            return "BMP";
        } 
        return null;
    }

    /**
     * 获取文件的真实媒体类型。目前只支持JPG, GIF, PNG, BMP四种图片文件。
     * 
     * @param bytes 文件字节流
     * @return 媒体类型(MEME-TYPE)
     */
    public static String getMimeType(byte[] bytes) {
        String suffix = getFileSuffix(bytes);
        if ("JPG".equals(suffix)) {
            return "image/jpeg";
        } 
        if ("GIF".equals(suffix)) {
            return "image/gif";
        } 
        if ("PNG".equals(suffix)) {
            return "image/png";
        } 
        if ("BMP".equals(suffix)) {
            return "image/bmp";
        }
        return "application/octet-stream";
    }
}
