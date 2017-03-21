package com.tk.cn.core.mina.app.cache;

import com.tk.cn.utils.web.MD5;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Company: www.tk.com</p>   
 * @author   tangkuo
 * @date    2017年3月21日 下午10:45:44
 */
public class CacheKey {
	
	/***首页***/
	//分类列表
	public final static String INDEX_CATEGORY_LIST = MD5.getMD5("index_category_list");
	//推荐类
	public final static String PUSH = "push_";
	//热门应用
	public final static String INDEX_HOT_APPS = MD5.getMD5("index_hot_apps");
	//最新应用
	public final static String INDEX_NEW_APPS = MD5.getMD5("index_new_apps");
	//热门关键词
	public final static String INDEX_KEYWORD = MD5.getMD5("index_keyword");
	
	
	/***全部服务***/
	//服务列表
	public final static String ALLSERV_CATEGORY_LIST = MD5.getMD5("allserv_category_list");
	
	
	/***免费体验***/
	//免费服务列表
	public final static String NOFREE_SERV_LIST = MD5.getMD5("nofree_serv_list");
	
	
	/***开发商帮助与支持***/
	//分类统计列表
	public final static String SERV_CATEGORY_STATIS_LIST = MD5.getMD5("serv_category_statis_list");
	
	//查询分类
	public final static String SERV_CATEGORY_BYID_LIST = MD5.getMD5("serv_category_byid_list");
	
	//根据类型查询分类
	public final static String CATEGORY_LIST_BY_TYPE = "category_list_by_type_";
	
	
	
	//案例分享
	public final static String CASE_SHARE_LIST = "case_share_list_";
	
	//案例分享
	public final static String CASE_SHARE_DETAIL = "case_share_detail_";
	
	//app详情
	public final static String APP_DETAIL = "app_detail_";
	
	//获取服务商其他服务
	public final static String SERV_GET_OTHERS = "serv_get_others_";
	
	//获取公司信息
	public final static String SERV_GET_DEV = "serv_get_dev_";
	
	//获取服务商全部服务
	public final static String SERV_GET_ALL = "serv_get_all_";
	

}
