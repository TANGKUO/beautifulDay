package com.tk.cn.utils.web;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description: 正则表达式验证工具类
 * </p>
 * <p>
 * Company: www.tk.com
 * </p>
 * 
 * @author tangkuo
 * @date 2017年3月19日 下午7:13:03
 */
public class ValidateElment {

	public static final String DOUBLE_PATTERN = "^([+-]?)\\d*\\.?\\d+$";
	public static final String NAME_PATTERN = "^(?:[0-9]|[\u4e00-\u9fa5]|[a-zA-Z])*$";
	/**
	 * 判断日期的正则表达式
	 */
	public static final String DATE_PATTERN = "^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))$";

	/**
	 * 字母、数字或符号(空格除外)不能是纯数字/纯字母
	 */
	public static final String PASSWORD_PATTERN = "^(?=.*\\d.*)(?=.*[a-zA-Z].*)([^ ]{6,20})$";

	/**
	 * 是否是邮件
	 * 
	 * @param value
	 */
	public static boolean isEmail(String value) {
		return compare(value, "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$");
	}

	public static boolean isMobile(String value) {
		return compare(value, "^1[3|4|5|7|8][0-9]\\d{4,8}$");
	}

	public static boolean compare(String value, String patternFormat) {
		Pattern pattern = Pattern.compile(patternFormat);
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	}

	public static void main(String[] args) {
		System.out.println(compare("88aa", PASSWORD_PATTERN));

		System.out.println(compare(String.valueOf(new Date()), DATE_PATTERN));
	}

}
