package com.tk.cn.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class TestISROSW {
	public static void main(String[] args) throws Exception {
		String str = "我爱NI,生活如此无聊！";
//		System.out.println(str.indexOf("我"));
//		System.out.println(str.charAt(str.length() - 1));
		FileOutputStream fos = new FileOutputStream("/home/tk/a.txt");

		FileInputStream fis = new FileInputStream("/home/tk/a.txt");

		InputStreamReader isr = new InputStreamReader(fis,"GBK");
		
		OutputStreamWriter osw = new OutputStreamWriter(fos, "GBK");
		
		BufferedReader br=new BufferedReader(isr);
		
		osw.write(str);
		
		osw.close();
		
//		System.out.println(br.readLine());
		
		for (int i = 0; i < str.length(); i++) {
			System.out.print((char) (isr.read()));
		}
		br.close();
	}
}
