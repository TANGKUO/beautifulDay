package com.tk.cn.core.mina;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.ReadFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.session.IoSessionConfig;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.prefixedstring.PrefixedStringCodecFactory;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * <p>Title: </p>
 * <p>Description: mina客户端</p>
 * <p>Company: www.tk.com</p>   
 * @author   tangkuo
 * @date    2017年3月21日 下午10:14:40
 */
public class MinaClientManager {
	
	/**log4j**/
    private static Logger logger = Logger.getLogger(MinaClientManager.class);
    
    private Gson gson = new Gson();
    
    /**单例对象**/
    private static final MinaClientManager INSTANCE = new MinaClientManager();
    
    IoConnector connector  = null;
    /**构造方法**/
    private MinaClientManager() {
    	connInit();
    }
    
    private void connInit() {
    	/**创建连接对象**/
    	connector  =  new NioSocketConnector();
    	
    	/**设置链接超时时间**/
		connector.setConnectTimeoutMillis(MinaConfig.CONNECT_TIMEOUT);
		
		/**添加编码过滤器-前面4位表示数据包的长度，4位开始以后就是消息的主体内容**/
		PrefixedStringCodecFactory factory = new PrefixedStringCodecFactory(Charset.forName("UTF-8"));
		factory.setDecoderMaxDataLength(MinaConfig.TRANS_DATA_SIZE);
		factory.setEncoderMaxDataLength(MinaConfig.TRANS_DATA_SIZE);
		connector.getFilterChain().addLast("codec",new ProtocolCodecFilter(factory));
		
		
		/**设置线程**/
		connector.getFilterChain().addLast("sigleThread",
				new ExecutorFilter(Executors.newSingleThreadExecutor()));
		
		/**数据格式转换**/
		/*connector.getFilterChain().addLast("dataConverter",
			new DataConverterFilter());*/
		
		/**设置iosession**/
		IoSessionConfig cfg = connector.getSessionConfig();
		
		/**设置IoSession的read()方法为可用**/
		cfg.setUseReadOperation(true);
		
		/**设置读取数据的缓冲区大小**/
		cfg.setMaxReadBufferSize(MinaConfig.READBUFFER_MAX_SIZE);
    }

    /**
     * 获取minaClient对象
     * @return
     */
    public static final MinaClientManager getInstance() {
	return INSTANCE;
    }
    
    /**
     * 建立mina连接,获取服务端数据
     * @param request 传输对象
     * @return
     */
    public ResponseMessage getMessage(RequestMessage request) {
    	
		if(connector == null || connector.isDisposed())
			connInit();
		
		/**创建业务返回对象**/
    	ResponseMessage response = new ResponseMessage();
    	    	
		if(connector != null && connector.isDisposed()) {
			response.setDesc("client connect error");
		    response.setCode(ResultCode.CONNECT_ERROR);
		    return response;
		}
		
		/**与服务端建立连接**/
		
		ConnectFuture future = connector.connect(new InetSocketAddress(
	    		MinaConfig.SERVER_IP, MinaConfig.SERVER_PORT));
	 
    	ReadFuture readFuture = null;
    	IoSession session = null;
    	
		try {
		    logger.debug("connect to " + MinaConfig.SERVER_IP+"	"+MinaConfig.SERVER_PORT);

		    /**等待连接创建完成**/
		    future.awaitUninterruptibly();
		    
		    /**
		     * 若在指定时间内没连接成功，则抛出异常
		     * 不关闭的话会运行一段时间后抛出，too many open(files异常，导致无法连接)
		     */
		    if (future.isDone()) {
				if (!future.isConnected()) { 
				    //connector.dispose();
				    //connector = null;
				    logger.info("==========close conn by future disconnect");
				    //throw new Exception();
				}
		    }

		    /**获取session**/
		    session = future.getSession();
		    String sendMsg = gson.toJson(request);
		    logger.info("client send Json : " + sendMsg);
		    
		    /**向服务器发送消息**/
		    session.write(sendMsg);
		    
		    /**消息读取**/
		    readFuture = session.read();
		    
		    /**等待获取业务端返回数据**/
		    if (readFuture.awaitUninterruptibly(MinaConfig.WAIT_OUT_TIME, TimeUnit.SECONDS)) {
				response = (ResponseMessage) readFuture.getMessage();
				logger.info("client read data：" + response.getData());
			 } else {
		    	/**连接超时处理**/
				response.setDesc("wait server out time");
				response.setCode(ResultCode.TIMEOUT);
				logger.info("wait server out time");
		    }
		    
		} catch (JsonSyntaxException e) {// 异常处理
		    logger.error("json format error, " + e);
		    response.setDesc("json format error");
		    response.setCode(ResultCode.FORMAT_ERROR);
		} catch (Exception e) {// 异常处理
		    logger.error("client connect error,sess="+session+",res=" + response + "," + e);
		    e.printStackTrace();
		    response.setDesc("client connect error");
		    response.setCode(ResultCode.CONNECT_ERROR);
		} finally {
		    if ((session != null) && !session.isClosing()) {
				session.getCloseFuture().awaitUninterruptibly();
				session.close(false);
				//session.getService().dispose();
		    }
		    /*
		    if(connector != null){
		    	connector.dispose();
		    	connector = null;		    	
		    }
		    */
		    logger.info("==========close conn");
		}
		return response;
    }


}