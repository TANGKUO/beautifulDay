package com.tk.cn.core.mina.base;

import java.util.HashMap;
import java.util.Map;

import com.tk.cn.core.mina.app.GlobalVar;
import com.tk.cn.utils.web.GsonUtils;
import com.tk.cn.utils.web.LoadProperties;

public class BaseService {

	
	/**
	 * 返回格式 : {"code":"0","data":"",desc:"exec success"}
	 * @return
	 */
	public String retJson(){
		return this.retJson(GlobalVar.CODE_RET_SUCCESS, "","success");
	}
	
	
	/**
	 * 返回格式 : {"code":"0","data":{}/[{},{}...],desc:"exec success"}
	 * @return
	 */
	public String retJson(Object data){
		return this.retJson(GlobalVar.CODE_RET_SUCCESS, data,"success");
	}
	
	/**
	 * 返回格式 : {"code":"MCSSYS0001","data":"",desc:"exec success"}
	 * @return
	 */
	public String retJson(String errcode,String errdesc){
		return this.retJson(errcode,errdesc);
	}
	
	
	/**
	 * 用户自定义Json
	 * @param code
	 * @param data
	 * @param others
	 * @param totals
	 * @param errcode
	 * @param desc
	 * @return
	 */
	public String retJson(String code,Object data,String desc){
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		String retJson = null;
		
		map.put("code",code);
		
		if(data != null && !"".equals(data.toString().trim())){
			map.put("data",GsonUtils.toJson(data));
		}else{
			map.put("data","");
		}
		if(desc != null && !"".equals(desc)){
			map.put("desc", desc);
		}else{
			map.put("desc", "");
		}

		retJson = GsonUtils.toJson(map);
		map.clear();map = null;
		return retJson;
		
	}
	
	public boolean isCacheFilter(){
		int flag = Integer.parseInt(LoadProperties.getloadProManager().getMap().get("is_cache").toString());
		if(flag == 0){
			return true;
		}else{
			return false;
		}
	}
	
}
