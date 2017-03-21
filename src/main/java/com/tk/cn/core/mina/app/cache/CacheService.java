package com.tk.cn.core.mina.app.cache;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tk.cn.core.mina.base.BaseService;


@Repository
public class CacheService  extends BaseService{
	@Autowired
	private CacheDao cacheDao;
	
	public String getSysHtml(){
		return this.retJson(this.cacheDao.getSysHtml()); 
	}
}
