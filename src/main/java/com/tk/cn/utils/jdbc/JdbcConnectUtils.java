package com.tk.cn.utils.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Company: www.tk.com</p>   
 * @author   tangkuo
 * @date    2017年3月25日 下午12:37:44
 */
public class JdbcConnectUtils {
	public static void main(String[]args) throws ClassNotFoundException, SQLException{
		//1.装载OracleDriver
		Class.forName("oracle.jdbc.OracleDriver");
		//2.创建Connection的实现对象
		//DriverManager.getConnection(String url,String username,String password)
		Connection conn=DriverManager
			.getConnection("jdbc:Oracle:thin:@172.16.1.130:1521:orcl"
					,"test2017","test2017");
		System.out.println(conn);
		//创建Statement对象，传送SQL语句
		Statement smt=conn.createStatement();
		//修改时间格式
		//smt.executeUpdate("alter session set nls_date_format ='yyyy-mm-dd hh24:mi:ss'");
		//插入一条记录
		//smt.executeUpdate("insert into account_wz(id,real_name,birthdate,create_date" +
//				",login_name,LOGIN_PASSWD,IDCARD_NO)" +
//				"values(1111,'Mr_W','1988-10-23','2013-5-20','wz'," +
//				"'1234',123456789123456789)");
		smt.executeUpdate("update account_wz set birthdate='1980-01-01' where id=1111");
		//执行sql动态语句
		ResultSet rs=smt.executeQuery("select id,real_name," +
				"((sysdate-birthdate)/365) age," +
				"create_date from account_wz");
		
		
		//显示查询语句
		//rs.next()为游标指向下一条记录,其返回结果为boolean类型
		while (rs.next()) {
			System.out.println(rs.getInt("id")+" "
					+rs.getString("real_name")+" "
					+rs.getInt("age")+" "+rs.getDate(4));
		}
		smt.close();
		conn.close();
	}
}
