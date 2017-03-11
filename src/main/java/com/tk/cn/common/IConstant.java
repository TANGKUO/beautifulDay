package com.tk.cn.common;

import com.tk.cn.utils.PermissionConstant;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.tk.com
 * </p>
 * 
 * @author tangkuo
 * @date 2017年3月11日 下午3:15:50
 */
public interface IConstant {

	boolean isDebug = true;

	/**
	 * 管理中登录用户的SessionKey
	 */
	String MGT_LOGIN_USER_SESSION_KEY = PermissionConstant.LOGIN_USER;

	int userState_ok = 0;
	int userState_lock = 1;
}
