package com.tk.cn.common;

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
 * @date 2017年3月11日 下午3:17:36
 */
public enum PersonType {

	PLATFORM("PLATFORM"), SUPPLI("SUPPLI"), LOGIN("LOGIN");

	private final String type;

	private PersonType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
