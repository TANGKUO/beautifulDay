
package com.tk.cn.utils.channel;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

/* *
 *类名：UtilDate
 *功能：自定义订单类
 *详细：工具类，可以用作获取系统日期、订单编号等
 *版本：3.3
 *日期：2012-08-17
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */
public class UtilDate {
	
    /** 年月日时分秒(无下划线) yyyyMMddHHmmss */
    public static final String dtLong                  = "yyyyMMddHHmmss";
    
    /** 完整时间 yyyy-MM-dd HH:mm:ss */
    public static final String simple                  = "yyyy-MM-dd HH:mm:ss";
    
    /** 年月日(无下划线) yyyyMMdd */
    public static final String dtShort                 = "yyyyMMdd";
	
    
    /**
     * 返回系统当前时间(精确到毫秒),作为一个唯一的订单编号
     * @return
     *      以yyyyMMddHHmmss为格式的当前系统时间
     */
	public  static String getOrderNum(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(dtLong);
		return df.format(date);
	}
	
	/**
	 * 获取系统当前日期(精确到毫秒)，格式：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public  static String getDateFormatter(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(simple);
		return df.format(date);
	}
	
	/**
	 * 获取系统当期年月日(精确到天)，格式：yyyyMMdd
	 * @return
	 */
	public static String getDate(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(dtShort);
		return df.format(date);
	}
	/**
	 * 获取系统当期年月日(精确到天)，格式：yyyyMMdd
	 * @return
	 */
	public static String getDate(String date){
		DateFormat df=new SimpleDateFormat(dtShort);
		return df.format(date);
	}
	/**
	 * 获取昨天开始时间和结束时间
	 * @return String[] 2015-05-05 00:00:00,2015-05-05 23:59:59
	 */
	public static String[] getYesdayDate(){
		String [] dt=new String[2];
	      Calendar  cal=Calendar.getInstance();
	      cal.set(Calendar.DAY_OF_MONTH,cal.get(Calendar.DAY_OF_MONTH)-1);
	      cal.set(Calendar.HOUR_OF_DAY,0);
	      cal.set(Calendar.MINUTE,0);
	      cal.set(Calendar.SECOND,0);
	      DateFormat df=new SimpleDateFormat(simple);
	      dt[0]=df.format(cal.getTime());
	      cal.set(Calendar.HOUR_OF_DAY,23);
	      cal.set(Calendar.MINUTE,59);
	      cal.set(Calendar.SECOND,59);
	      dt[1]=df.format(cal.getTime());
	      return dt;
	}
	
	/**
	 * 产生随机的三位数
	 * @return
	 */
	public static String getThree(){
		Random rad=new Random();
		return rad.nextInt(1000)+"";
	}
	public static void main(String[] args) {
	String[] s=	UtilDate.getYesdayDate();
	for(Object t:s){
		System.out.println(t);
	}
	}
	
}
