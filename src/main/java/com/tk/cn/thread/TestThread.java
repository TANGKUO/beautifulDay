package com.tk.cn.thread;

public class TestThread {
	public static void main(String[] args) throws Exception{
		//JVM会常见一个主线程，主线程来运行main方法
		
		myThread s = new myThread();
//		s.run();
		
		//启动线程，该线程会运行自己的run方法
		s.start();
		for (int i = 0; i < 10; i++) {
			System.out.println("Main"+i);
			Thread.sleep(2000);
		}
		System.gc();
	}
}
