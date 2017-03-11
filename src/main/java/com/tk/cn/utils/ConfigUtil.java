package com.tk.cn.utils;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * <p>Title: </p>
 * <p>Description: 读取配置文件内容工具类</p>
 * <p>Company: www.tk.com</p>   
 * @author   tangkuo
 * @date    2017年3月11日 下午8:13:42
 */
public class ConfigUtil {
	
	public static Map<String, String> configMap = null;
	private static String FILENAME = "/config.properties";
	
	static {
		if(configMap == null){
			init();
		}
	}
	
	public static void init(){
		try {
			configMap = new HashMap<String, String>();
			Properties prop = new Properties();
			prop.load(ConfigUtil.class.getClassLoader().getResourceAsStream(FILENAME));
			String env = prop.getProperty("env");
			configMap.put("jedis_port", prop.getProperty("jedis_port"));
			if("test".equals(env)){
				configMap.put("jedis_url", prop.getProperty("jedis_url_test"));
			}else{
				configMap.put("jedis_url", prop.getProperty("jedis_url_pro"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <p>Description: </p>
	 * @param string
	 * @return  
	 * @author  tangkuo
	 * @date    2017年3月11日 下午9:32:19
	 */
	public static String getProperty(String key) {
		if (null == configMap) {
			init();
		}
		String value = configMap.get(key);
		return value;
	}
}
