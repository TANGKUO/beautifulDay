package com.tk.cn.core.mina.app;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import com.tk.cn.utils.web.GsonUtils;

/**
 * <p>Title: </p>
 * <p>Description: 访问入口</p>
 * <p>Company: www.tk.com</p>   
 * @author   tangkuo
 * @date    2017年3月21日 下午10:29:40
 */
public class ServerHandler extends IoHandlerAdapter {
	//日志独立配置,不跟业务日志混合
	//private static Logger logger = Logger.getLogger(ServerHandler.class);
	private static Logger logger = Logger.getLogger("IECCORE");
	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		String msg = message.toString();
		logger.info("request : " + msg);
		
		try {
			//Thread.sleep(100000);
			RequestBean rb = GsonUtils.getJson(msg, RequestBean.class);
			if (rb.getMethod() != null && !rb.getMethod().trim().equals("")) {
				msg = BaseMapping.getInstance().baseCall(rb.getMethod(), rb.getParams());
			} else {
				logger.error("RequestI's method is ==[" + rb.getMethod() + "]");
				msg = RecErrorInfo.reWebReqMethodError();
			}
			rb = null ;
		} catch (Exception e) {
			msg = RecErrorInfo.reWebParamBeanIntoError();
		}
		logger.info("response : " + msg);
		session.write(msg).awaitUninterruptibly();
		session.close(false);

	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		logger.info("服务端与客户端连接关闭...");
		super.sessionClosed(session);
	}
	
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		logger.error("服务端出现异常。。。" + cause);
		cause.printStackTrace();
		session.close(true);
	}

	public void sessionOpened(IoSession session) throws Exception {
		logger.info("服务端与客户端连接打开...");
	}
}