package com.tk.cn.core.mina.app;


/**
 * <p>Title: </p>
 * <p>Description: 向PHP端返回异常信息(限于在数据接收层中使用)</p>
 * <p>Company: www.tk.com</p>   
 * @author   tangkuo
 * @date    2017年3月21日 下午10:31:14
 */
public class RecErrorInfo {

	
	/**
	 * Web发送参数错误(参数为null或为"")
	 * @return JSON : {"code":1,"desc":"Web pass parameters error"}
	 */
	public static String reWebParamError(){
		return RecErrorInfo.reError(RetMsg.RECIPROCAL_PARERROR,ErrorCode.MCSSYS0004);
	}
	
	/**
	 * Web发送参数错误(Java实体bean注入错误)
	 * @return JSON : {"code":1,"desc":"Web pass parameters error to java bean into error"}
	 */
	public static String reWebParamBeanIntoError(){
		return RecErrorInfo.reError(RetMsg.RECIPROCAL_PARBEANINTOERROR,ErrorCode.MCSSYS0003);
	}
	
	
	/**
	 * Web请求方法错误(方法不存在)
	 * @return JSON : {"code":1,"desc":"Web request method not exists"}
	 */
	public static String reWebReqMethodError(){
		return RecErrorInfo.reError(RetMsg.RECIPROCAL_REQMETHODNOTEXISTS,ErrorCode.MCSSYS0002);
	}
	
	/**
	 * Web请求模块错误(模块不存在)
	 * @return JSON : {"code":1,"desc":"Web request module not exists"}
	 */
	public static String reWebReqModuleError(){
		return RecErrorInfo.reError(RetMsg.RECIPROCAL_REQMODULENOTEXISTS,ErrorCode.MCSSYS0001);
	}
	
	
	/**
	 * app与数据库交互操作错误码
	 * @return JSON : {"code":1,"desc":"Web request module not exists"}
	 */
	public static String reAppSysError(){
		return RecErrorInfo.reError("app system error",ErrorCode.MCSSYS0005);
	}
	
	/**
	 * 
	 * 返回Web错误信息
	 * @param retMsg
	 * @return JSON : {"code":1,"desc":retMsg}
	 */
	private static String reError(String retMsg,String errcode){
		
		StringBuffer reJson = new StringBuffer("");
		
		reJson.append("{\"code\":\""+errcode+"\",\"data\":\"\",\"desc\":\""+retMsg+"\"}");
		
		return reJson.toString();

	}
	
}
