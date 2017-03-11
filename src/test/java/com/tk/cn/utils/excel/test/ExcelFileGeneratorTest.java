package com.tk.cn.utils.excel.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description: 测试将数据库信息导出到Excel表格
 * </p>
 * <p>
 * Company: www.tk.com
 * </p>
 * 
 * @author tangkuo
 * @date 2017年3月11日 下午7:46:43
 */
public class ExcelFileGeneratorTest {

	@Before
	public void setup() {

	}

	@After
	public void clearup() {

	}

	@Test
	public void testExcelFileGenerator() {
		// 创建一个工作薄
		HSSFWorkbook hwb = new HSSFWorkbook();
		// 创建一张工作表
		HSSFSheet sheet = hwb.createSheet();
		HSSFRow row = sheet.createRow(0);// 0 创建的是第一行
		// 创建三列
		HSSFCell cell1 = row.createCell(0);
		HSSFCell cell2 = row.createCell(1);
		HSSFCell cell3 = row.createCell(2);
		cell1.setCellValue("你好");
		cell2.setCellValue("不好");
		cell3.setCellValue("嘿嘿");
		try {
			hwb.write(new FileOutputStream(new File("d:/aaa.xls")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
