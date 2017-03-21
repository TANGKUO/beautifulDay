package com.tk.cn.utils.web;

import java.util.HashMap;
import java.util.Map;

public class PricePeriodMapper {
	private static final Map<String, Integer> map = new HashMap<String, Integer>();
//	week:1 ,month:2 ,quarterly:3,halfyear:4,year:5
	static{
		map.put("week", 1);
		map.put("month", 2);
		map.put("quarterly", 3);
		map.put("halfyear", 4);
		map.put("year", 5);
	}
	public static final  Integer getValue(String key){
		return  map.get(key) ;
	}
}
