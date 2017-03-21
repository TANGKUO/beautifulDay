package com.tk.cn.core.mina;

import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * <p>Title: </p>
 * <p>Description: 加载mina配置</p>
 * <p>Company: www.tk.com</p>   
 * @author   tangkuo
 * @date    2017年3月21日 下午10:15:12
 */
public class MinaLoadPrpo {
	
	/**log4j**/
    private final static Logger logger = Logger.getLogger(MinaLoadPrpo.class);
    
    /**单例对象**/
    private final static MinaLoadPrpo INSTANCE = new MinaLoadPrpo();
    
    /**配置文件**/
    private final static String configPath = "minaConfig.properties";
    
    /**资源对象**/
    private Properties configuration = null;

    /**
     * 获取实例对象
     * @return
     */
    public final static MinaLoadPrpo getInstance() {
    	return INSTANCE;
    }

    /**构造方法**/
    private MinaLoadPrpo() {
    	load();
    }

    /**
     * 加载配置文件
     */
    private void load() {
    	
		if (configuration == null) {
		    InputStream is = MinaLoadPrpo.class.getClassLoader().getResourceAsStream(configPath);
		    try {
				configuration = new Properties();
				configuration.load(is);
				logger.info("Loading minaConfig.properties success");
			} catch (Exception e) {
				logger.error("Loading minaConfig.properties error "+e);
				System.exit(-1);
		    }
		}
    }

    /**
     * 获取value值
     * @param key
     * @return
     */
    public String getValue(String key) {
    	return configuration.getProperty(key);
    }

    /**
     * 获取value值
     * @param key
     * @return
     */
    public int getIntValue(String key) {
		String valueStr = configuration.getProperty(key);
		try {
		    return Integer.parseInt(valueStr);
		} catch (Exception e) {
			logger.error(e);
		}
		return -1;
    }
    
    
    /**
     * 获取value值
     * @param key
     * @return
     */
    public long getLongValue(String key) {
    	String valueStr = configuration.getProperty(key);
    	try {
    	    return Long.parseLong(valueStr);
    	} catch (Exception e) {
    		logger.error(e);
    	}
    	return -1;
    }

}
