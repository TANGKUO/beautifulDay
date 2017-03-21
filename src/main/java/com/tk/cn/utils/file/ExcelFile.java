package com.tk.cn.utils.file;
/*package com.tk.cn.workflow.activity;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;


public class ExcelFile {

	private HSSFWorkbook wb;
	
	private HSSFSheet sheet;
	
	private int rownum = 0;
	
	private String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	private String[][] dictName;
	private String[] colCode;
	//add
	private List<String> itemValue;
	
	public HSSFWorkbook getWorkbook() {
		return this.wb;
	}
	
	public ExcelFile(HSSFWorkbook wb, String[] title) {
		this.wb = wb;
		this.sheet = wb.createSheet("Sheet1");
		sheet.createFreezePane( 0, 1, 0, 1 ); 
		sheet.setColumnWidth((short)0, (short)(256*30));
		sheet.setColumnWidth((short)1, (short)(256*30));
		sheet.setColumnWidth((short)2, (short)(256*30));
		sheet.setColumnWidth((short)3, (short)(256*30));
		sheet.setColumnWidth((short)4, (short)(256*30));
		sheet.setColumnWidth((short)5, (short)(256*30));
		sheet.setColumnWidth((short)6, (short)(256*30));
		sheet.setColumnWidth((short)7, (short)(256*30));
		sheet.setColumnWidth((short)8, (short)(256*30));
		sheet.setColumnWidth((short)8, (short)(256*30));
		
		HSSFRow row = sheet.createRow(0);
		HSSFCellStyle style = wb.createCellStyle();
		style.setFillForegroundColor(HSSFColor.ORANGE.index);
	    style.setFillPattern(HSSFCellStyle.BORDER_THIN);
	    style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平
		
		for (short i = 0; i < title.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(new HSSFRichTextString(title[i]));
			cell.setCellStyle(style);
		}
	}
	
	public void setDictName(String[][] dictName) {
		this.dictName = dictName;
	}
	
	public void setColCode(String[] colCode) {
		this.colCode = colCode;
	}
	
	public <T> void processRow(List<T> objectList, Class<?> entityClass) throws SQLException {
		Method[] method = entityClass.getDeclaredMethods();
		if (objectList != null) {
			try {
				for (T o : objectList) {
					HSSFRow row = sheet.createRow(++rownum);
					for (short i = 0; i < colCode.length; i++) {
						HSSFCell cell = row.createCell(i);
						for (Method m : method) {
							if (m.isAnnotationPresent(Column.class)) {
								Column col = m.getAnnotation(Column.class);//System.out.println(colCode[i] + "\t\t=" + col.name());
								if (colCode[i].toUpperCase().equals(col.name())) {
									String getMethodName = m.getName();
									Method getMethod = entityClass.getMethod(getMethodName);		
									Object value = getMethod.invoke(o);
									for (int j = 0; j < dictName.length; j++) {
										if (dictName[j][0].equals(colCode[i])) {
											value = DictHelper.getName(dictName[j][1], value);
										}
									}
									if (value == null) {
										cell.setCellValue(new HSSFRichTextString(null));
									} else {
										if ((Date.class).equals(value.getClass())) {
											SimpleDateFormat df = new SimpleDateFormat(DATE_PATTERN);
											cell.setCellValue(new HSSFRichTextString(df.format(value)));
										} else {
											cell.setCellValue(new HSSFRichTextString(value.toString()));
										}
									}
								}
							}
						}
					}
				}
			} catch (Exception e) {
				throw new SQLException(e);
			}
		}
	}
	
	// add by chenhs@2013/06/18
	public <T> void processRow_order(List<T> objectList, Class<?> entityClass) throws SQLException {
		HSSFCellStyle style = wb.createCellStyle();
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);	//居中
		this.itemValue = new ArrayList<String>();
		
		Method[] method = entityClass.getDeclaredMethods();
		if (objectList != null) {
			try {
				for (T o : objectList) {
					HSSFRow row = sheet.createRow(++rownum);
					for (short i = 0; i < colCode.length; i++) {
						HSSFCell cell = row.createCell(i);
						cell.setCellStyle(style);
						
						for (Method m : method) {
							if (m.isAnnotationPresent(Column.class)) {
								Column col = m.getAnnotation(Column.class);//System.out.println(colCode[i] + "\t\t=" + col.name());
								if (colCode[i].toUpperCase().equals(col.name())) {
									String getMethodName = m.getName();
									Method getMethod = entityClass.getMethod(getMethodName);		
									Object value = getMethod.invoke(o);
									
									//add
									if("ITEMID".equals(col.name())){
										itemValue.add(value.toString());
									}
									
									for (int j = 0; j < dictName.length; j++) {
										if (dictName[j][0].equals(colCode[i])) {
											value = DictHelper.getName(dictName[j][1], value);
										}
									}
									if (value == null) {
										cell.setCellValue(new HSSFRichTextString(null));
									} else {
										if ((Date.class).equals(value.getClass())) {
											SimpleDateFormat df = new SimpleDateFormat(DATE_PATTERN);
											cell.setCellValue(new HSSFRichTextString(df.format(value)));
										} else {
											cell.setCellValue(new HSSFRichTextString(value.toString()));
										}
									}
								}
							}
						}
					}
				}
				
				//add
				Object[] temp = itemValue.toArray();
				for (int j = 0; j < temp.length - 1; j++){
					if(temp[j].toString().equals(temp[j+1].toString())){
						for (short i = 0; i < colCode.length; i++) {
							//除了第3,4,5,6,7,8列,其它行如果itemid相同则合并行
							if(i != 2 && i != 3 && i != 4 && i != 5 && i != 6 && i != 7){	
								// 四个参数分别是：起始行，起始列，结束行，结束列
								sheet.addMergedRegion(new Region(j+1 , (short)i, j+2, (short)i));	
							}
						}
					}
				}
			} catch (Exception e) {
				throw new SQLException(e);
			}
		}
	}
}
*/