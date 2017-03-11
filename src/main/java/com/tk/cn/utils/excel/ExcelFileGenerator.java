package com.tk.cn.utils.excel;
import java.io.OutputStream;
import java.util.ArrayList;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
/**
 * <p>Title: </p>
 * <p>Description: 将数据导出Excel 生成器</p>
 * <p>Company: www.tk.com</p>   
 * @author   tangkuo
 * @date    2017年3月11日 下午7:51:40
 */
public class ExcelFileGenerator {

	private final int SPLIT_COUNT = 15; //Excel每个工作表的行数

	private ArrayList fieldName = null; //excel标题数据集

	private ArrayList fieldData = null; //excel数据内容	

	private HSSFWorkbook workBook = null;

	/**
	 * 构造器
	 * @param fieldName 结果集的字段名
	 * @param data
	 */
	public ExcelFileGenerator(ArrayList fieldName, ArrayList fieldData) {
		this.fieldName = fieldName;
		this.fieldData = fieldData;
	}
	/**
	 * 创建HSSFWorkbook对象
	 * @return HSSFWorkbook
	 */
	public HSSFWorkbook createWorkbook() {
		workBook = new HSSFWorkbook();//poi报表的核心对象
		int rows = fieldData.size();//获取内容的数据
		int sheetNum = 0;//共分几页

		if (rows % SPLIT_COUNT == 0) {
			sheetNum = rows / SPLIT_COUNT;
		} else {
			sheetNum = rows / SPLIT_COUNT + 1;
		}

		for (int i = 1; i <= sheetNum; i++) {
			HSSFSheet sheet = workBook.createSheet("Page " + i);//使用workbook创建sheet
			HSSFRow headRow = sheet.createRow(0); //poi特点：先要创建sheet，通过sheet创建row，第1行
			for (int j = 0; j < fieldName.size(); j++) {//循环excel的标题
				HSSFCell cell = headRow.createCell(j);//poi特点：先要创建row，通过row创建cell
				//添加样式
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定导出的类型
				//设置列（有数据的列）的宽度
				sheet.setColumnWidth(j, 6000);
				//设置样式字体加粗、颜色变红
				//使用workBook获取操作单元格的样式
				HSSFCellStyle style = workBook.createCellStyle();
				//获取字体对象
				HSSFFont font = workBook.createFont();
				//字体加粗
				font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				//字体颜色变红
				short color = HSSFColor.RED.index;
				font.setColor(color);
				//将字体的样式添加到HSSFCellStyle中
				style.setFont(font);
				if(fieldName.get(j) != null){
					cell.setCellStyle(style);
					cell.setCellValue((String) fieldName.get(j));//向excel的单元格�8�设罎
				}else{
					cell.setCellStyle(style);
					cell.setCellValue("-");
				}
			}

			for (int k = 0; k < (rows < SPLIT_COUNT ? rows : SPLIT_COUNT); k++) {
				if (((i - 1) * SPLIT_COUNT + k) >= rows)//实现excel的分页
					break;
				HSSFRow row = sheet.createRow(k + 1);//创建row，+1，从e8cel的第二行开始写入值
				//将数据内容放入excel单元ƀ�
				ArrayList rowList = (ArrayList) fieldData.get((i - 1)
						* SPLIT_COUNT + k);
			for (int n = 0; n < rowList.size(); n++) {
					HSSFCell cell = row.createCell( n);//创建cell
					if(rowList.get(n) != null){
						cell.setCellValue((String) rowList.get(n).toString());
					}else{
						cell.setCellValue("");
					}
				}
			}
		}
		return workBook;
	}

	public void expordExcel(OutputStream os) throws Exception {
		workBook = createWorkbook();
		workBook.write(os);//生成excel文件
		os.close();
	}

}
