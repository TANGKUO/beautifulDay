package com.tk.cn.core.mina.app;


/**
 * <p>Title: </p>
 * <p>Description: 记录返回PHP异常信息</p>
 * <p>Company: www.tk.com</p>   
 * @author   tangkuo
 * @date    2017年3月21日 下午10:36:31
 */
public class RetMsg {
	
	/**SQL语句执行失败**/
	public final static String DAO_DB_SQLFAILURE = "sql exec failure";
	
	/**存储过程执行失败**/
	public final static String DAO_DB_PROCEDUREFAILURE = "procedure exec failure";
	
	/**PHP传递参数错误**/
	public final static String RECIPROCAL_PARERROR = "php pass parameters error";
	
	/**PHP传递参数错误 Java实体bean注入错误**/
	public final static String RECIPROCAL_PARBEANINTOERROR = "php pass parameters error to java bean into error";
	
	/**PHP请求方法不存在**/
	public final static String RECIPROCAL_REQMETHODNOTEXISTS = "php request method not exists";
	
	/**PHP请求模块不存在**/
	public final static String RECIPROCAL_REQMODULENOTEXISTS = "php request module not exists";

	
}
