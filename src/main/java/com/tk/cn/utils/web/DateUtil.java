package com.tk.cn.utils.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.XMLGregorianCalendar;

/**
 * 时间工具类
 */
public class DateUtil {
	
	
	public static void main(String[] args) throws ParseException
	{
//		//System.out.println(getTodayDate3());
		//System.out.println(changeDatetoString(new Date()));
		
		
		String date = "26/Aug/2013:22:17:17";
		String d = "Tue Aug 27 15:53:53 CST 2013";
		SimpleDateFormat d1=new SimpleDateFormat("yyMMdd");
		System.out.println(d1.parse(d));
		
	}
	
	public static String stringDate2StringDate111(String date) throws ParseException{
		SimpleDateFormat d1=new SimpleDateFormat("dd/MM/yy");
		SimpleDateFormat d2=new SimpleDateFormat("yyMMdd");
		return d2.format(d1.parse(date));
	}
	/**
	 * 当前日期
	 * @return yyyyMMdd
	 */
	public static String getTodayDate2() {
		// 初始化时间
		Calendar RightNow = Calendar.getInstance();
		return changeDatetoString2(RightNow);
	}
	
	/**
	 * 当前日期
	 * @return yyyy-MM-dd
	 */
	public static String getTodayDate() {
		// 初始化时间
		Calendar RightNow = Calendar.getInstance();
		return changeDatetoString(RightNow);
	}
	
	/**
	 * 当前日期
	 * @return yyyy-MM-dd hh:mm:ss,SSS
	 */
	public static String getTodayDate3() {
		// 初始化时间
		return DateUtil.changeLongtoString(DateUtil.getTodayDateLong());
	}
	
	
	public static Long getTodayDateLong() {
		// 初始化时间
		Calendar RightNow = Calendar.getInstance();
		return RightNow.getTimeInMillis();
	}
	
	public static Date xmlDateToDate(XMLGregorianCalendar xmlCalendar){
		GregorianCalendar calendar =xmlCalendar.toGregorianCalendar();
		return calendar.getTime();
	}
	
	public static Date getDateByLong(Long time){
		Date date = new Date();
		date.setTime(time);
		return date;
	}
	
	/** 
	* 用来获日期前后若干天的日期 
	* @param cycleNum  要去前若干天传负整数，后若干天传整数
	* @param jsDate 相对日期（20080303）
	*/ 
	public static String getJsDate2(int cycleNum,String sDate) {
		sDate = zhihuan(sDate, "yyyy/MM/dd", "yyyyMMdd");
		return getJsDate(cycleNum, sDate);
	}
	
	
	public static String zhihuan(String sDate,String newCss,String oldCss){
		SimpleDateFormat ft = new SimpleDateFormat(newCss); 
		SimpleDateFormat ft1 = new SimpleDateFormat(oldCss); 
		try {
			return ft.format(ft1.parse(sDate));
		} catch (ParseException e) {
			return "";
		}
	}
	/** 
	* 用来获日期前后若干天的日期 
	* @param cycleNum  要去前若干天传负整数，后若干天传整数
	* @param jsDate 相对日期（2008/03/03）
	*/ 
	public static String getJsDate(int cycleNum,String jsDate) {
		// 初始化时间
		Calendar RightNow = Calendar.getInstance();
		RightNow.setTime(new Date(jsDate));
		RightNow.add(Calendar.DATE, cycleNum);
		return changeDatetoString2(RightNow);
	}
	
	
	// 取得当前日期前后若干天的日期,格式yyyymmdd
	public static String getDate2(int day) {
		// 初始化时间

		Calendar RightNow = Calendar.getInstance();
		RightNow.add(Calendar.DATE, day);
		return changeDatetoString2(RightNow);
	}
	
	// 取得当前日期前后若干天的日期,格式yyyy-mm-dd
	public static String getDate(int day) {
		// 初始化时间

		Calendar RightNow = Calendar.getInstance();
		RightNow.add(Calendar.DATE, day);
		return changeDatetoString(RightNow);
	}
	
	/**
	 * 取得现在的日期，格式yyyymmddhhmmss
	 */
	public static String getCurrentDate() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(cal.YEAR);
		int month = cal.get(cal.MONTH) + 1;
		int day = cal.get(cal.DAY_OF_MONTH);
		int hour = cal.get(cal.HOUR_OF_DAY);
		int minute = cal.get(cal.MINUTE);
		int second = cal.get(cal.SECOND);
		String cDate = Integer.toString(year);
		if (month < 10) {
			cDate = cDate + "0" + Integer.toString(month);
		} else {
			cDate = cDate + Integer.toString(month);
		}
		if (day < 10) {
			cDate = cDate + "0" + Integer.toString(day);
		} else {
			cDate = cDate + Integer.toString(day);
		}
		if (hour < 10) {
			cDate = cDate + "0" + Integer.toString(hour);
		} else {
			cDate = cDate + Integer.toString(hour);
		}
		if (minute < 10) {
			cDate = cDate + "0" + Integer.toString(minute);
		} else {
			cDate = cDate + Integer.toString(minute);
		}
		if (second < 10) {
			cDate = cDate + "0" + Integer.toString(second);
		} else {
			cDate = cDate + Integer.toString(second);
		}
		return cDate.trim();
	}
	
	
	public static String stringDate2StringDate(String date) throws ParseException{
		SimpleDateFormat d1=new SimpleDateFormat("MM/dd/yy");
		SimpleDateFormat d2=new SimpleDateFormat("yyMMdd");
		return d2.format(d1.parse(date));
	}
	
	/**
	 * 获取月份的天数
	 * @param year
	 * @param month
	 * @return
	 */
	 public static int getDay(int year ,int month){
		Calendar time=Calendar.getInstance(); 
		time.clear(); 
		time.set(Calendar.YEAR,year); 
		time.set(Calendar.MONTH,month-1);//Calendar对象默认一月为0            
		int day=time.getActualMaximum(Calendar.DAY_OF_MONTH);//本月份的天数
		return day;
	 }
	 public static String addMonthAndDay(int changeMon,int changeDay) {
			String str = "";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

			Calendar lastDate = Calendar.getInstance();
			lastDate.add(Calendar.MONTH, changeMon);// 减一个月
			lastDate.set(Calendar.DATE, 1);// 把日期设置为当月第一天
			lastDate.roll(Calendar.DATE, changeDay);// 日期回滚一天，也就是本月最后一天
			str = sdf.format(lastDate.getTime());
			return str;
		}
	 public static String addMonthAndDay(String proc_date,int changeMon,int changeDay) {
			String str = "";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
			Calendar lastDate = Calendar.getInstance();
			try {
				lastDate.setTime(sdf.parse(proc_date));
				lastDate.add(Calendar.MONTH, changeMon);// 减一个月
				lastDate.set(Calendar.DATE, 1);// 把日期设置为当月第一天
				lastDate.roll(Calendar.DATE, changeDay);// 日期回滚一天，也就是本月最后一天
				str = sdf1.format(lastDate.getTime());
				return str;
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
		}
	 
	 /**
	  * 将格式为20021212080000的日期转换成2002-12-12 08:00:00时
	  */
		public static String customDateTimeFormat(String dateTime) {
			String year, month, day, hour, Second, Minute;

			year = dateTime.substring(0, 4);
			month = dateTime.substring(4, 6);
			day = dateTime.substring(6, 8);
			hour = dateTime.substring(8, 10);
			Minute = dateTime.substring(10, 12);
			Second = dateTime.substring(12, 14);
			String str = year + "-" + month + "-" + day + " " + hour + ":" + Minute
					+ ":" + Second;
			return str;
		}
		
		// 将日期转换成字符串,格式yyyy-mm-dd
		public static String changeDatetoString(Calendar cDate) {
			int Year;
			int Month;
			int Day;
			String sDate = "";

			// 初始化时间

			Year = cDate.get(Calendar.YEAR);
			Month = cDate.get(Calendar.MONTH) + 1;
			Day = cDate.get(Calendar.DAY_OF_MONTH);

			sDate = Integer.toString(Year) + "-";
			if (Month >= 10) {
				sDate = sDate + Integer.toString(Month) + "-";
			} else {
				sDate = sDate + "0" + Integer.toString(Month) + "-";
			}
			if (Day >= 10) {
				sDate = sDate + Integer.toString(Day);
			} else {
				sDate = sDate + "0" + Integer.toString(Day);
			}
			return sDate;
		}
		
		// 将LONG毫秒转换成字符串,格式yyyy-MM-dd hh:mm:ss,SSS
		public static String changeLongtoString(long ms) {
			
			Date date = new Date();
			date.setTime(ms);
			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String res = sdf.format(date);
			return res;
		}
		
		// 将LONG毫秒转换成字符串,格式yyyy-MM-dd hh:mm:ss,SSS
		public static String changeDatetoString(Date date) {
			if(date==null) return null ;
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String res = sdf.format(date);
			return res;
		}
		
		
		
		// 将格式yyyy-MM-dd hh:mm:ss,SSS 转换成 LONG毫秒
		public static long changeStringtoLong(String formatDate) {
			try{
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
				Date d = sdf.parse(formatDate);
				long l = d.getTime();
				
				return l;
			}catch(Exception ex){
				return 0;
			}
			
			
		}
		
		// 将格式yyyy-MM-dd hh:mm:ss 转换成 LONG毫秒
		public static long changeStringtoLong2(String formatDate) {
			try{
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
				Date d = sdf.parse(formatDate);
				long l = d.getTime();
				
				return l;
			}catch(Exception ex){
				return 0;
			}
			
			
		}
		
		
		/**
		 * 当前日期
		 * @return yyyyMMdd
		 */
		public static String changeDatetoString2(Calendar cDate) {
			int Year;
			int Month;
			int Day;
			String sDate = "";
			// 初始化时间
			Year = cDate.get(Calendar.YEAR);
			Month = cDate.get(Calendar.MONTH) + 1;
			Day = cDate.get(Calendar.DAY_OF_MONTH);
			sDate = Integer.toString(Year);
			if (Month >= 10) {
				sDate = sDate + Integer.toString(Month);
			} else {
				sDate = sDate + "0" + Integer.toString(Month);
			}
			if (Day >= 10) {
				sDate = sDate + Integer.toString(Day);
			} else {
				sDate = sDate + "0" + Integer.toString(Day);
			}
			return sDate;
		}
		
//		
//	 public static void main(String[] args) {
//		 //System.out.println(addMonthAndDay("2011/08/02",-2,1));
//		 //System.out.println("当前日期 yyyyMMdd = "+DateUtil.getTodayDate2());
//		 //System.out.println("当前日期 yyyy-MM-dd hh:mm:ss,SSS  = "+DateUtil.getTodayDate3());
//		 //System.out.println("当前日期 = "+DateUtil.changeDatetoString2(Calendar.getInstance()));
//		 //System.out.println();
//		 
//		 //System.out.println("获当前日期后若干天的日期 = "+DateUtil.getDate(-1));
//		 //System.out.println("获当前日期后若干天的日期 = "+DateUtil.getDate(1));
//		 //System.out.println();
//		 
//		 
//		 //System.out.println("获日期前后若干天的日期 = "+DateUtil.getJsDate(-1, "2011/08/02"));
//		 //System.out.println("获日期前后若干天的日期 = "+DateUtil.getJsDate(1, "2011/08/02"));
//		 //System.out.println("获日期前后若干天的日期 = "+DateUtil.getJsDate2(-1, "20110802"));
//		 //System.out.println("获日期前后若干天的日期 = "+DateUtil.getJsDate2(1, "20110802"));
//		 //System.out.println();
//		 
//	
//		 //System.out.println("当前时间 = "+DateUtil.getCurrentDate());
//		 //System.out.println("月份天数 = "+DateUtil.getDay(2011, 2));
//		 //System.out.println();
//		 
//		 //System.out.println("转换时间格式 = "+DateUtil.customDateTimeFormat("20110802111846"));
//		 
//		 long l = 1312368059406L;
//		 //System.out.println("毫秒转换成字符串 = "+DateUtil.changeLongtoString(l));
//		 
//		 String s = "2011-08-03 06:36:34,703";
//		 //System.out.println("毫秒字符串转换成long = "+DateUtil.changeStringtoLong(s));
//		 
//		 String ss = "2011-08-03 06:36:34";
//		 //System.out.println("秒字符串转换成long = "+DateUtil.changeStringtoLong2(ss));
//	}
}
