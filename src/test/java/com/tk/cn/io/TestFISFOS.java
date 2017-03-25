package com.tk.cn.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class TestFISFOS {
	public static void main(String[] args) throws Exception {
		FileInputStream fis = new FileInputStream("/home/tk/API.zip");
		FileOutputStream fos = new FileOutputStream("API.zip");

		BufferedInputStream bis = new BufferedInputStream(fis);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		int b;
		while ((b = bis.read()) != -1) {
			bos.write(b);
		}
		bis.close();
		bos.close();
	}
}
