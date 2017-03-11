package com.tk.cn.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tk.cn.common.sign.RSA;
import com.tk.cn.common.sign.SignCore;
import com.tk.cn.utils.ConfigUtil;

/**
 * <p>Title: </p>
 * <p>Description: 工程默认控制层处理</p>
 * <p>Company: www.tk.com</p>   
 * @author   tangkuo
 * @date    2017年3月11日 下午8:53:40
 */
@Controller
public class IndexControllor {

    private Logger log = LoggerFactory.getLogger(getClass());
    
    
    /**
     * <p>Description: </p>
     * @param model
     * @return  
     * @author  tangkuo
     * @date    2017年3月11日 下午8:55:01
     */
    @RequestMapping(value = {"/", "/index.htm"}, method = RequestMethod.GET)
    public String index(Model model) {
    	return "index";
    }
    
	/**
	 * 签名
	 */
	@ResponseBody
	@RequestMapping(value = "/sign.json", method = RequestMethod.POST)
	public Object sign(HttpServletRequest request) {
		Map<?, ?> params = request.getParameterMap();
        String[] keys = params.keySet().toArray(new String[0]);
        Map<String, String> paras = new HashMap<String, String>();
        for (String key : keys) {
            Object value = request.getParameter(key);
            if (null == value || "".equals(value)) {
                value = "";
            }
            paras.put(key, String.valueOf(value));
        }
        
        // 签名密钥
        String privateKey = ConfigUtil.getProperty("sign_key");
		Map<String, String> pList = SignCore.paraFilter(paras);
		String content = SignCore.createLinkString(pList);
		log.info("签名前内容：" + content);
		String sign = RSA.sign(content, privateKey, "UTF-8");
		return "{\"success\":true,\"sign\":\"" + sign + "\"}";
	}
}
