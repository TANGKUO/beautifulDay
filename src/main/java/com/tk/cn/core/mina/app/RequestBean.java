package com.tk.cn.core.mina.app;


/**
 * <p>Title: </p>
 * <p>Description: 请求对象</p>
 * <p>Company: www.tk.com</p>   
 * @author   tangkuo
 * @date    2017年3月21日 下午10:29:16
 */
public class RequestBean {
	
	/**方法名**/
	private String method;
	
	/**方法参数**/
	private String params;
	
	public String getMethod() {
		return method;
	}
	
	public void setMethod(String method) {
		this.method = method;
	}
	
	public String getParams() {
		return params;
	}
	
	public void setParams(String params) {
		this.params = params;
	}

}
