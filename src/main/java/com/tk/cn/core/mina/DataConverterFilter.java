package com.tk.cn.core.mina;

import org.apache.log4j.Logger;
import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.session.IoSession;

import com.google.gson.Gson;

/**
 * <p>Title: </p>
 * <p>Description: 数据格式转换</p>
 * <p>Company: www.tk.com</p>   
 * @author   tangkuo
 * @date    2017年3月21日 下午10:14:24
 */
public class DataConverterFilter extends IoFilterAdapter{
	
	/**log4j**/
	private static Logger logger = Logger.getLogger(DataConverterFilter.class);

	private Gson gson = new Gson();
	
	@Override
	public void messageReceived(NextFilter nextFilter, IoSession session,Object message) throws Exception {
		logger.info("#messageReceived# message:"+message);
		ResponseMessage response = null;
		try {
			response = gson.fromJson(message.toString(), ResponseMessage.class);
		 }catch (Exception e) {
			response = getErrorMsg(ResultCode.FORMAT_ERROR, "messageReceived：Json format error.");
		}
		 super.messageReceived(nextFilter, session, response);
	}
	
	private ResponseMessage getErrorMsg(String status, String msg) {
		logger.error("#getErrorMsg# status[" + status +"],msg[" + msg+ "]");
		ResponseMessage response = new ResponseMessage(status, msg, "");
		return response;
	}
	
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		logger.error("连接出现异常。。。" + cause.getMessage());
		session.close(true);
	}

}
