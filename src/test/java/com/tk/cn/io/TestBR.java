package com.tk.cn.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class TestBR { 
	public static void main(String[]args)
	throws Exception{
		FileInputStream fis=new FileInputStream("/home/tk/a.txt");
		
		InputStreamReader isr=new InputStreamReader(fis,"GBK");
		
		BufferedReader br=new BufferedReader(isr);
		
		String str=br.readLine();
		
		System.out.println(str);
	}
}
