package com.tk.cn.thread;

public class myThread extends Thread{
	public void run(){
		for(int i=0;i<10;i++){
			System.out.println("Thread"+i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
		}
	}
}
