package com.tk.cn.utils.file;
/*package com.tk.cn.workflow.activity;

import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.springframework.stereotype.Repository;


*//**
 * 
 * <b>Application describing:</b>excel导出excel工具类 <br>
 * @author
 * @version $Revision$
 *//*
@Repository
public class ExportExcel extends B2bDaoSupport {
    private static final Logger log = LogManager.getLogger(ExportExcel.class);

    *//**
     * 
     * {设置所有PreparedStatement的参数，并对null值做处理}
     * @param pstmt
     * @param params
     * @throws SQLException
     * @author: 
     *//*
    private void setParams(PreparedStatement pstmt, Object[] params) throws SQLException {
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                if (params[i] != null) {
                    pstmt.setObject(i + 1, params[i]);
                }
                else {
                    pstmt.setNull(i + 1, Types.OTHER);
                }
            }
        }
    }

    *//**
     * 
     * {关闭资源}
     * @param conn
     * @param psmt
     * @author: 
     *//*
    public void closeResourece(Connection conn, PreparedStatement psmt) throws Exception {
        try {
            if (psmt != null) {
                psmt.close();
            }
        }
        catch (SQLException e) {
            throw new SQLException(e);
        }
        finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            }
            catch (SQLException e) {
                throw new SQLException(e);
            }
        }
    }

    *//**
     * 
     * {excel导出方法}
     * @param response      响应
     * @param sql           导出数据的sql
     * @param params        sql参数
     * @param columnZHname  列表中文列名
     * @param excelName     导出excel的文件名
     * @author: 
     *//*
    public void exportExcel(HttpServletResponse response, String sql, String[] params, String[] columnZHname,
            String excelFileName) throws Exception {
    	exportExcel(response, sql, params, columnZHname, excelFileName, 0);
    }

        
    *//**
     * 
     * {excel导出方法}
     * @param response      响应
     * @param sql           导出数据的sql
     * @param params        sql参数
     * @param columnZHname  列表中文列名
     * @param excelName     导出excel的文件名
     * @author: 
     *//*
    public void exportExcel(HttpServletResponse response, String sql, String[] params, String[] columnZHname,
            String excelFileName,int sumrow) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            HSSFWorkbook wb = new HSSFWorkbook();//
            HSSFSheet sheet = wb.createSheet("" + sdf.format(new Date()));

            HSSFCellStyle titleStyle = wb.createCellStyle();
            HSSFCellStyle stringStyle = wb.createCellStyle();
            HSSFCellStyle warnStyle = wb.createCellStyle();
            // HSSFCellStyle numStyle=wb.createCellStyle();
            // HSSFCellStyle dateStyle=wb.createCellStyle();

            warnStyle.setFillForegroundColor(HSSFFont.COLOR_RED);

            HSSFFont titleFont = wb.createFont();

            titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

            titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            titleStyle.setFillBackgroundColor(HSSFColor.BLUE_GREY.index);
            titleStyle.setFont(titleFont);
            // titleStyle.setWrapText(false);

            stringStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

            HSSFRow row = sheet.createRow(0);
            HSSFCell cell = null;//row.createCell((short) 0);
            //cell.setCellStyle(titleStyle);
            //cell.setCellValue("序号");

            for (int i = 0; i < columnZHname.length; i++) {
                cell = row.createCell((short) (i));
                cell.setCellStyle(titleStyle);
                cell.setCellValue(columnZHname[i]);
            }

            // 导出数据
            ResultSet rs = null;

            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            DataSource ds = this.getDataSource();
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            setParams(pstmt, params);
            rs = pstmt.executeQuery();
            ResultSetMetaData meta = rs.getMetaData();
            int i = 0;
            while (rs.next()) // not empty
            {
                row = sheet.createRow(i + 1); // 从第2行起插入，第一行为表头
                String cellValue = "";

                //cell = row.createCell((short) 0);
                //cell.setCellValue(i + 1);
                //cell.setCellStyle(stringStyle);

                for (int j = 1; j <= meta.getColumnCount(); j++) {
                    cell = row.createCell((short) (j - 1));
                    cell.setCellStyle(stringStyle);
                    if(j<sumrow || sumrow==0){
	                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	                    cell.setEncoding(HSSFCell.ENCODING_UTF_16);// 为了能在单元格中写入中文，设置字符编码为UTF_16。
                    }else{
                    	cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                    }
                    try {
                        cellValue = COMMON.noNull((rs.getObject(meta.getColumnName(j))));
                    }
                    catch (Exception aaa) {
                        System.err.println("Colume name error: " + meta.getColumnName(j).toUpperCase());
                    }
                    if(j<sumrow || sumrow==0){
                    	cell.setCellValue(cellValue);
                    }else{
                    	cell.setCellValue(Double.parseDouble(cellValue));
                    }
                }
                i++;
            }

            if(sumrow!=0){
	            //合并合计列单元格
	            sheet.addMergedRegion(new Region(i+1,(short)0,i+1,(short)(sumrow-2)));
	            row=sheet.createRow(i + 1);
	            cell= row.createCell((short) (0));
	            cell.setCellStyle(stringStyle);
	            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setEncoding(HSSFCell.ENCODING_UTF_16);// 为了能在单元格中写入中文，设置字符编码为UTF_16。
	            cell.setCellValue("合计");
	            //增加合计行
	            for (int k=sumrow-1;k<meta.getColumnCount(); k++){
	            	cell= row.createCell((short) (k));
	            	cell.setCellStyle(stringStyle);
	            	cell.setCellType(HSSFCell.CELL_TYPE_FORMULA);
	                cell.setCellFormula("SUM(" + getColLetter(k) + (2) +":" + getColLetter(k) + (i+1) + ")");
	            }
            }
            // 调整列宽度
            for (int k = 0; k < columnZHname.length; ++k) {
                //sheet.autoSizeColumn((short) k);
                sheet.setColumnWidth((short)k, (short)4500); 
            }
            
            //String excelName = "导出表.xls";
            response.setContentType("application/msexcel");
            response.addHeader("Content-Disposition", "attachment;filename=" + toUtf8String(excelFileName));
            response.setContentType("charset=utf-8");
            try {
                OutputStream os = response.getOutputStream();
                wb.write(os);
                os.flush();
                os.close();
            }
            catch (Exception e) {
                throw new Exception(e);
            }
            finally {
                closeResourece(conn, pstmt);
            }
        }
        catch (Exception e) {
            log.error("异常", e);
        }

        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            // 初始化一个sheet
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet();
            wb.setSheetName(0, "第一页", HSSFWorkbook.ENCODING_UTF_16);
            HSSFRow row;
            HSSFCell cell;

            // 标题样式 
            HSSFCellStyle titleStyle = wb.createCellStyle();
            titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            HSSFFont titleFont = wb.createFont();
            titleFont.setFontHeight((short) 400);
            titleStyle.setFont(titleFont);
            //sheet.addMergedRegion(new Region(0, (short) 2, 0, (short) 5));

            // 插入标题
            row = sheet.createRow(0);
            cell = row.createCell((short) 2);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setEncoding(HSSFCell.ENCODING_UTF_16);
            cell.setCellValue("列表");
            cell.setCellStyle(titleStyle);

            // 表头样式
            HSSFCellStyle cs = wb.createCellStyle();
            HSSFFont font = wb.createFont();
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            cs.setFont(font);

            // 插入表头
            row = sheet.createRow(1);
            for (int col = 0; col < columnNameCH.length; col++) {
                cell = row.createCell((short) col);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                //if (col == 0) { // 隐藏第一列id,设置列宽为0
                //    sheet.setColumnWidth((short) col, (short) (0));
                //}
                //else { // 其他列设列宽为5000
                sheet.setColumnWidth((short) col, (short) (5000));
                // }
                cell.setCellValue(columnNameCH[col]);
                cell.setCellStyle(cs);
            }

            DataSource ds = this.getDataSource();
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            setParams(pstmt, params);
            rs = pstmt.executeQuery();
            ResultSetMetaData meta = rs.getMetaData();

            int i = 0;
            while (rs.next()) // not empty
            {
                row = sheet.createRow(i + 2); // 从第三行起插入，第一行为标题,第二行为表头
                String cellValue = "";

                for (int j = 1; j <= meta.getColumnCount(); j++) {
                    cell = row.createCell((short) j);
                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                    cell.setEncoding(HSSFCell.ENCODING_UTF_16);// 为了能在单元格中写入中文，设置字符编码为UTF_16。
                    try {
                        cellValue = COMMON.noNull((rs.getObject(meta.getColumnName(j))));
                    }
                    catch (Exception aaa) {
                        System.err.println("Colume name error: " + meta.getColumnName(j).toUpperCase());
                    }
                    cell.setCellValue(cellValue);
                }
                i++;
            }

            String excelName = "导出表.xls";
            response.setContentType("application/msexcel");
            response.addHeader("Content-Disposition", "attachment;filename=" + toUtf8String(excelName));
            wb.write(response.getOutputStream());

            response.getOutputStream().flush();
            response.getOutputStream().close();
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally {
            closeResourece(conn, pstmt);
        }

    }

    *//**
     * 
     * {将列数转换为excel中的字母表示}
     * @param i 列数
     * @return  字母表示的列数
     *//*
    public static char getColLetter(int i) {
    	char c = (char)(i+65);
    	return c;
    }
    *//**
     * 
     * {将文件名中的汉字转为UTF8编码的串,以便下载时能正确显示另存的文件名}
     * @param s 原文件名
     * @return  重新编码后的文件名
     * @author: 
     *//*
    public static String toUtf8String(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ((c >= 0) && (c <= 255)) {
                sb.append(c);
            }
            else {
                byte[] b;

                try {
                    b = Character.toString(c).getBytes("UTF-8");
                }
                catch (Exception ex) {
                    System.out.println(ex);
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0) {
                        k += 256;
                    }
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }

}
*/