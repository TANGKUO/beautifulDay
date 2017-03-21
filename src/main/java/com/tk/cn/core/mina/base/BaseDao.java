package com.tk.cn.core.mina.base;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * <p>Title: </p>
 * <p>Description: 基础的dao</p>
 * <p>Company: www.tk.com</p>   
 * @author   tangkuo
 * @date    2017年3月21日 下午10:39:26
 */
public class BaseDao extends JdbcDaoSupport {
	
	
    @Autowired
    public void setBaseJdbcTemplate(JdbcTemplate b2bJdbcTemplate) {
        setJdbcTemplate(b2bJdbcTemplate);
    }
    
    
    /**
     * 查询数量
     * @param sql
     * @param params
     * @return
     */
    protected int queryCnt(String sql,Object[] params){
    	//return this.getJdbcTemplate().queryForInt(sql,params);
    	//return this.getJdbcTemplate().queryForInt(sql,params);
    	return 1;
    }
    
    /**
     * 分页
     * @param sql
     * @param params
     * @param currentpage
     * @param pagesize
     * @return
     */
    protected List<Map<String,Object>> basePage(String sql,Object[] params,int currentpage,int pagesize){
    	
    	StringBuffer pageSql = new StringBuffer("");
		pageSql.append(" SELECT * FROM (");
		pageSql.append(" SELECT A.*,ROWNUM RN FROM (");
		pageSql.append(sql).append(" ) A ");
		pageSql.append(" WHERE ROWNUM <=? ) B WHERE B.RN >=?");
		return this.getJdbcTemplate().queryForList(pageSql.toString(), this.setPageArray(params, currentpage, pagesize));
    }
    
    
	/**
	 * 处理分页数组参数
	 * @param parPage
	 * @param page
	 * @param pagesize
	 * @return Object[]
	 */
	private Object[] setPageArray(Object[] parPage,int page,int pagesize){
		
		int beforeNum = 0;
		int afterNum = 0;
		
	 	beforeNum =  page * pagesize;
	 	
	 	afterNum = beforeNum - pagesize + 1;
		
	 	Object[] obj = null; 
	 	
	 	if(parPage != null){
	 		obj = new Object[parPage.length+2];
	 		for(int i=0;i<parPage.length;i++){
	 			obj[i] = parPage[i];
	 		}
		 	obj[parPage.length] = beforeNum;
		 	obj[parPage.length+1] = afterNum;
	 	}else{
	 		obj = new Object[2];
		 	obj[0] = beforeNum;
		 	obj[1] = afterNum;
	 	}
	 	
	 	return obj;
		
	}
}
