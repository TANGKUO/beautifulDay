package com.tk.cn.io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestDISDOS {
	public static void main(String[]args) throws IOException{
		FileOutputStream fos=new FileOutputStream("/home/tk/tmp.dat");
		FileInputStream fis=new FileInputStream("/home/tk/tmp.dat");
		
		DataOutputStream dos=new DataOutputStream(fos);
		DataInputStream dis=new DataInputStream(fis);
		for(int i=1;i<=5;i++){
			double d=Math.random();
			dos.writeDouble(d);
		}
		for(int i=1;i<=100;i++){
		double d=dis.readDouble();
		System.out.println(d);
		}
	}
}
