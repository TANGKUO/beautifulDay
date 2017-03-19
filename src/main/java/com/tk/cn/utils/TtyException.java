package com.tk.cn.utils;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:统一封装异常处理类
 * </p>
 * <p>
 * Company: www.tk.com
 * </p>
 * 
 * @author tangkuo
 * @date 2017年3月19日 下午6:45:36
 */
public class TtyException extends RuntimeException {

	private static final long serialVersionUID = -3245401318783085837L;
	private String errCode;
	private Object[] args;
	private String errMsg;

	public TtyException() {
		super();
	}

	public TtyException(String message, Throwable cause) {
		super(message, cause);
	}

	public TtyException(String message) {
		super(message);
	}

	public TtyException(Throwable cause) {
		super(cause);
	}

	public TtyException(String errCode, String errMsg) {
		super(errCode + ":" + errMsg);
		this.errCode = errCode;
		this.errMsg = errMsg;
	}

	public TtyException(String errCode, String errMsg, Object[] args) {
		super(errCode + ":" + errMsg);
		this.errCode = errCode;
		this.errMsg = errMsg;
		this.args = args;
	}

	@Override
	public synchronized Throwable fillInStackTrace() {
		return null;
	}

	public String getErrCode() {
		return this.errCode;
	}

	public String getErrMsg() {
		return this.errMsg;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}

}
