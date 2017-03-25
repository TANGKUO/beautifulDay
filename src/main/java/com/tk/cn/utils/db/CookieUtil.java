package com.tk.cn.utils.db;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
	private static final String path = "/Shopping";
	private static final int maxAge = Integer.MAX_VALUE;
	
	public static void addCookie(String name,String value,HttpServletResponse response,int age) throws UnsupportedEncodingException{
		Cookie co = new Cookie(name,URLEncoder.encode(value,"utf-8"));
		co.setMaxAge(age);
		co.setPath(path);
		response.addCookie(co);
	}
	
	public static void addCookie(String name,String value,HttpServletResponse response) throws UnsupportedEncodingException{
		Cookie co = new Cookie(name,URLEncoder.encode(value,"utf-8"));
		co.setMaxAge(maxAge);
		co.setPath(path);
		response.addCookie(co);
	}
	
	public static String findCookie(String name,HttpServletRequest request) throws UnsupportedEncodingException{
		String value = null;
		Cookie[] co = request.getCookies();
		if(co!=null){
			for(int i=0;i<co.length;i++){
				String val = co[i].getValue();
				if(name.equals(co[i].getName())){
					value = URLDecoder.decode(val,"utf-8");
				}
			}
		}
		return value;
	}
}
