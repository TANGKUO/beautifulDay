package com.tk.cn.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestBISBOS {
	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream("/home/tk/API.zip");
		FileOutputStream fos = new FileOutputStream("/home/tk/API1.zip");
//		InputStreamReader is=new InputStreamReader(new FileInputStream(""));
		BufferedInputStream bis = new BufferedInputStream(fis);
		BufferedOutputStream bos = new BufferedOutputStream(fos);

		int count;
		while ((count = bis.read()) != -1) {
			bos.write(count);
		}
		bis.close();
		bos.close();
	}
}
