package com.tk.commons.xml;

import com.tk.commons.exception.BaseRuntimeException;

/**
 * 
 * @ClassName: XMLTransformException
 * @Description: (XMLTransformException)
 * @author 唐阔
 * @date 2017年6月4日 下午2:13:00
 * 
 */

public class XMLTransformException extends BaseRuntimeException
{
	private static final long serialVersionUID = 2325300429266549778L;

	public XMLTransformException(String errorCode)
	{
		super(errorCode);
	}

	public XMLTransformException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public XMLTransformException(Throwable cause)
	{
		super(cause);
	}

	public XMLTransformException(String errorCode, String message, Throwable cause)
	{
		super(errorCode, message, cause);
	}

}
