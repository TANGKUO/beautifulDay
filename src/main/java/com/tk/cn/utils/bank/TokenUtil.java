package com.tk.cn.utils.bank;

import java.util.Calendar;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Company: www.tk.com</p>   
 * @author   tangkuo
 * @date    2017年3月19日 下午6:42:41
 */
public class TokenUtil {

	/**
	 * 生产token：由登录账号、终端类型和时间戳进过MD5生产
	 * 
	 * @param lname
	 *            登录账号
	 * @param app
	 *            手机唯一标识
	 * @return
	 */
	public static String createToken(String lname, String app) {
		Calendar c = Calendar.getInstance();
		return Md5Utils.encryptMD5(lname + app + c.getTimeInMillis());
	}
}
