package com.tk.cn.io;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Date;

import org.junit.Test;

public class TestPW {
	@Test
	public void testExcelFileGenerator() throws Exception {
		FileOutputStream fos = new FileOutputStream("d:/tk.txt", true);

		OutputStreamWriter isw = new OutputStreamWriter(fos, "GBK");

		PrintWriter pw = new PrintWriter(isw);

		pw.write("吃饭拉");

		pw.println(new Date());
		pw.close();
	}
}
