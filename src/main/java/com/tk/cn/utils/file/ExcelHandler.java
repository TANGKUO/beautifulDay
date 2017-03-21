package com.tk.cn.utils.file;
/*package com.tk.cn.workflow.activity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.jdbc.core.RowCallbackHandler;

public class ExcelHandler implements RowCallbackHandler {
	
	private String colCode[];
	        
	private String alignRight[];
	
	private String dictName[];
	
	private HSSFWorkbook wb;
	
	private HSSFSheet sheet;	
	
	private int rownum = 0;
	
	private Map<Long, String> eLevels;
	
	
	public void seteLevels(Map<Long, String> eLevels) {
		this.eLevels = eLevels;
	}

	public HSSFWorkbook getWorkbook() {return this.wb;}
		
	public ExcelHandler(HSSFWorkbook wb, String[] title) {
		this.wb = wb;
		this.sheet = wb.createSheet("Sheet1");
		sheet.createFreezePane( 0, 1, 0, 1 ); 
		sheet.setColumnWidth((short)0, (short)(256*9));
		sheet.setColumnWidth((short)1, (short)(256*10));
		sheet.setColumnWidth((short)2, (short)(256*15));
		sheet.setColumnWidth((short)3, (short)(256*30));
		sheet.setColumnWidth((short)4, (short)(256*10));
		sheet.setColumnWidth((short)5, (short)(256*10));
		sheet.setColumnWidth((short)6, (short)(256*10));
		sheet.setColumnWidth((short)7, (short)(256*10));
		sheet.setColumnWidth((short)8, (short)(256*10));
		sheet.setColumnWidth((short)8, (short)(256*12));
		
		HSSFRow row = sheet.createRow(0);
		
		HSSFCellStyle style = wb.createCellStyle();
		style.setFillForegroundColor(HSSFColor.ORANGE.index);
	    style.setFillPattern(HSSFCellStyle.BORDER_THIN);

		for (short i = 0; i < title.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(new HSSFRichTextString(title[i]));
			cell.setCellStyle(style);
		}
	}
	
	public void processRow(ResultSet rs) throws SQLException {
		HSSFRow row = sheet.createRow(++rownum);
		for (short i = 0; i < colCode.length; i++) {
			HSSFCell cell = row.createCell(i);
			Object obj = rs.getObject(colCode[i]);
			HSSFCellStyle style = wb.createCellStyle();
			
			// 数据字典转换
			for (int j = 0; j < dictName.length; j++) {
				if (colCode[i].equalsIgnoreCase(dictName[j])) {
					obj = DictHelper.getName(dictName[j], obj);
				}
			}
			
			// 向右对齐
			for (int j = 0; j < alignRight.length; j++) {
				if (colCode[i].equals(alignRight[j])) {									
					style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
					cell.setCellStyle(style);
					obj = new DecimalFormat("#0.00").format(obj);
				}
			}
			
			if (i == 0) {
				cell.setCellValue(rownum);
			}
			else {
				if (obj == null) {
					cell.setCellValue(new HSSFRichTextString(null));
				}
				else {
					cell.setCellValue(new HSSFRichTextString(obj.toString()));
				}
			}
			
//			if(i == colCode.length -1 ) 
//				cell.setCellValue(new HSSFRichTextString(this.getLevelName(((BigDecimal)rs.getObject("TOTAL_SCORE")).longValue())));
		}
		
	}
	
	public String getLevelName(Long score) {
		Long levelScore = 0l;
		Set<Long> keys = eLevels.keySet();
		for (Long sc : keys) {
			if (score >= sc) levelScore = sc;
			else break;
		}
		return eLevels.get(levelScore);
	}
		
	public void setAlignRight(String[] alignRight) {
		this.alignRight = alignRight;
	}

	public void setDictName(String[] dictName) {
		this.dictName = dictName;
	}

	// ===========
	public static void exportExcel(String fileName, HSSFWorkbook wb, HttpServletResponse response) throws Exception {
        ServletOutputStream os = response.getOutputStream();
        try {
        	response.setContentType("application/msexcel");
        	response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
        	response.setContentType("charset=utf-8");
        	wb.write(os);
        	os.flush();
        } catch (Exception e) {
            throw e;
        } finally {
            if (os != null) {
                try {
                	os.close();
                } catch (Exception e) {}
            }
        }
    }

	public void setColCode(String[] colCode) {
		this.colCode = colCode;
	}
}
*/