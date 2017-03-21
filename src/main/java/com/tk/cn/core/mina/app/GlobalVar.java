package com.tk.cn.core.mina.app;

/**
 * <p>Title: </p>
 * <p>Description: 全局静态变量
 * Java与PHP接口返回参数定义</p>
 * <p>Company: www.tk.com</p>   
 * @author   tangkuo
 * @date    2017年3月21日 下午10:31:34
 */
public class GlobalVar {
	
	/**返回代码**/
	public final static String CODE = "code";
	
	/**返回成功**/
	public final static String CODE_RET_SUCCESS = "0";
	
	/**返回失败**/
	public final static int CODE_RET_FAILURE = 1;
	
	/**描述错误返回信息**/
	public final static String DESC = "desc";
	
	/**
	 * 数据集
	 * 说明: Java端需要给PHP返回数据集时(查询操作),
	 * 			   ->执行成功：data返回数据集
	 *  		   ->执行失败：data返回null
	 * 		 Java端不需要给PHP返回数据集时(增、删、改操作),
	 * 			   ->执行成功：data返回值为：true
	 *             ->执行失败：data返回值为：false
	 */
	public final static String DATA = "data";
	
	/**其他,返回额外数据(例如总数等)**/
	public final static String OTHERS = "others";
	
	/**配合数据集data使用**/
	public final static int DATA_RET_SUCCESS = 0;
	
	/**配合数据集data使用**/
	public final static int DATA_RET_FAILURE = 1;
	
	/**总数**/
	public final static String TOTAL = "total";
	
	/**数量**/
	public final static String COUNT = "count";
	
	//0 succe 1 ing
	public static int EXEC_FLAG = 0;
	
	public final static String TOTALS = "totals";
	
	public final static String ERRCODE = "errcode";
	
	
	public final static String ERRCODE_SUCC_VAL = "0000000000";
	
}
