package com.tk.cn.utils.web;


import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 获取服务器/客户端真实IP地址
 */
public class SystemIp {
	
	private static InetAddress inetAddress = null;
	//静态代码块,初始化对象
	static {
		try {
			inetAddress = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	//获取系统IP地址
	public static String getSysIp(){
		return inetAddress.getHostAddress();
	}
	//获取系统IP对应的主机名
	public static String getSysLoc(){
		return inetAddress.getHostName();
	}
	

	
	/**
	 * 测试
	 */
	public static void main(String[] args) {
		
		//System.out.println("服务端IP：  "+SystemIp.getSysIp()+"  "+SystemIp.getSysLoc());
		
	}
	
}
