package com.tk.cn.thread;

import java.util.Scanner;

public class Counter {
	private StringBuffer buffer = new StringBuffer();

	public void action() {
		printThread pt = new printThread();
		pt.setDaemon(true);//是否设置为守护线程
		pt.start();

		Scanner scanner = new Scanner(System.in);
		String str;
		while ((str = scanner.next()) != null) {
			if ("exit".equals(str)) {
				break;
			}
			buffer.append(str);
		}

	}

	private class printThread extends Thread {
		//线程创建必须重写其run（）方法
		public void run() {
			while (true) {
				System.out.println(buffer);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		Counter s = new Counter();
		s.action();
	}
}
