package com.tk.cn.core.mina;

/**
 * <p>Title: </p>
 * <p>Description: 加载mina配置</p>
 * <p>Company: www.tk.com</p>   
 * @author   tangkuo
 * @date    2017年3月21日 下午10:14:56
 */
public class MinaConfig {
	
	/**mina配置对象**/
    private static MinaLoadPrpo config = MinaLoadPrpo.getInstance();
    
    /**连接IP地址**/
	public final static String SERVER_IP = config.getValue("server_ip");
	
	/**连接IP端口**/
	public final static int SERVER_PORT = config.getIntValue("server_port");
	
	/**连接超时时间**/
	public final static int CONNECT_TIMEOUT = config.getIntValue("connect_timeout");
	
	/**传输数据包大小**/
	public final static int TRANS_DATA_SIZE = config.getIntValue("trans_data_size");
	
	/**读取数据的缓冲区大小**/
	public final static int READBUFFER_MAX_SIZE = config.getIntValue("readbuffer_max_size");
	
	/**等待服务端返回数据超时时间**/
	public final static int WAIT_OUT_TIME = config.getIntValue("wait_out_time");
	

}
