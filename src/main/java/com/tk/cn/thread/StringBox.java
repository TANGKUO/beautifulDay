package com.tk.cn.thread;

import java.util.ArrayList;
import java.util.List;

public class StringBox {
	private List<String> list = new ArrayList<String>();
	private Object obj = new Object();

	public void addString(String str) {
		synchronized (obj) {
			list.add(str);
			
		}
		System.out.println(list);
	}

	public void removeString(String str) {
		synchronized (obj) {
			list.remove(str);

		}
		System.out.println(list);
	}
}
