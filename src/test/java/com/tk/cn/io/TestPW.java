package com.tk.cn.io;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Date;

public class TestPW {
	public static void main(String[] args) 
	throws Exception{
		FileOutputStream fos=new FileOutputStream("/home/tk/a.txt",true);
		
		OutputStreamWriter isw=new OutputStreamWriter(fos,"GBK");
		
		PrintWriter pw=new PrintWriter(isw);
		
		pw.write("吃饭拉");
		
		pw.println(new Date());
		pw.close();
	}
}
