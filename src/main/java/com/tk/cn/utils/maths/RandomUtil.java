package com.tk.cn.utils.maths;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * <p>Title: </p>
 * <p>Description: 随机数工具</p>
 * <p>Company: www.tk.com</p>   
 * @author   tangkuo
 * @date    2017年3月19日 下午6:54:41
 */
public class RandomUtil {

    /**
     * 随机生成六位长度的手机验证码
     * 
     * @return
     */
    public static String createRandom() {
    	return RandomStringUtils.randomNumeric(6);
    }
    
    /**
     * 8位验证消费码
     * @return
     */
    public static String createOrderChkNum() {
		return RandomStringUtils.randomNumeric(8);
    }

    public static void main(String[] args) {
		System.out.println(RandomStringUtils.random(32, true, true));
	}
}
