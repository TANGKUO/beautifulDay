package com.tk.cn.core.mina;

/**
 * <p>Title: </p>
 * <p>Description: :返回码统一封装工具类</p>
 * <p>Company: www.tk.com</p>   
 * @author   tangkuo
 * @date    2017年3月21日 下午10:11:18
 */
public class ResultCode {
	
	/**返回成功**/
	public static final String SUCCESS = "0" ;
	
	/**返回失败**/
	public static final String ERROR = "1"  ;
	
	/**连接超时**/
	public static final String TIMEOUT = "2" ;
	
	/**连接错误**/
	public static final String CONNECT_ERROR = "3" ;
	
	/**格式错误**/
	public static final String FORMAT_ERROR ="4";
	
	/**方法名不存在**/
	public static final String METHOD_NOT_EXIST = "5" ;
}
