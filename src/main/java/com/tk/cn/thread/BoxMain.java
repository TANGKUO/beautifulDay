package com.tk.cn.thread;

public class BoxMain {
	public static void main(String[] args) {
		final StringBox box = new StringBox();

		Thread s = new Thread() {
			public void run() {
				for (int i = 0; i < 100; i++) {
					box.addString("%");
				}
			}
		};

		s.start();
		for (int i = 0; i < 100; i++) {
			box.addString("#");
		}
	}
}
