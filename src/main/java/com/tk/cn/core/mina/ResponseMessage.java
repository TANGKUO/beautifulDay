package com.tk.cn.core.mina;

import java.io.Serializable;


/**
 * <p>Title: </p>
 * <p>Description: mina传输对象</p>
 * <p>Company: www.tk.com</p>   
 * @author   tangkuo
 * @date    2017年3月21日 下午10:11:59
 */
public class ResponseMessage implements Serializable {
	
    private static final long serialVersionUID = -4692119596934798174L;

    /**返回码**/
    private String code;
    
    /**返回数据集**/
    private String data;
    
    /**返回结果描述**/
    private String desc;
    
    public ResponseMessage(){}
    
    public ResponseMessage(String code,String data,String desc){
    	this.code = code;
    	this.data = data;
    	this.desc = desc;
    }
    
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}