package com.tk.cn.utils.web;

/**
 * 工具方法
 */
public class BaseUtils {
	
	
	/**
	 * 分页参数验证
	 * @param val
	 * @return
	 */
	public static boolean checkPagePar(Integer val){
		if(val != null && val > 0){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断是否不为空和""
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isNotEmpty(String val) {
		if (val != null && !"".equals(val.trim())) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否为空或""
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isEmpty(String val) {
		if (val == null || "".equals(val.trim())) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否为null
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isNull(Object val) {
		if (val == null) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否为null
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isNotNull(Object val) {
		if (val != null) {
			return true;
		}
		return false;
	}
}
