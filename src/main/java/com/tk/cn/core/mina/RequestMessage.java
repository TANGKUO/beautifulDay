package com.tk.cn.core.mina;

import java.io.Serializable;

/**
 * <p>Title: </p>
 * <p>Description: mina传输对象</p>
 * <p>Company: www.tk.com</p>   
 * @author   tangkuo
 * @date    2017年3月21日 下午10:12:20
 */
public class RequestMessage implements Serializable {
	
	private static final long serialVersionUID = -6111220358041616436L;

	/**请求方法名**/
    private String method;
    
    /**请求参数JSON**/
    private String params;
    
    public RequestMessage(){}

    public RequestMessage(String method,String params){
    	this.method = method;
    	this.params = params;
    }
    
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
