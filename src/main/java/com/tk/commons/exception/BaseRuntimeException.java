package com.tk.commons.exception;

/**
 * 
 * @ClassName: BaseRuntimeException
 * @Description: (自定义运行时异常)
 * @author 唐阔
 * @date 2017年6月4日 下午1:39:48
 * 
 */
public class BaseRuntimeException extends RuntimeException
{

	private static final long serialVersionUID = 2179505696855940301L;

	private String errorCode;

	/**
	 * 
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param errorCode
	 * @param message
	 * @param cause
	 */
	public BaseRuntimeException(String errorCode, String message, Throwable cause)
	{
		super(message, cause);
		this.setErrorCode(errorCode);
	}

	/**
	 * 
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param errorCode
	 * @param message
	 */
	public BaseRuntimeException(String errorCode, String message)
	{
		super(message);
		this.setErrorCode(errorCode);
	}

	public BaseRuntimeException(String message)
	{
		super(message);
	}

	/**
	 * @param errorCode
	 * @param cause
	 */
	public BaseRuntimeException(String message, Throwable cause)
	{
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public BaseRuntimeException(Throwable cause)
	{
		super(cause);
	}

	public void setErrorCode(String errorCode)
	{
		this.errorCode = errorCode;
	}

	public String getErrorCode()
	{
		return errorCode;
	}

}
