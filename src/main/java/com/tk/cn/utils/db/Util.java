package com.tk.cn.utils.db;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
	private static String driverName;
	private static String server;
	private static String userName;
	private static String passWord;
	
	static{
		try {
			Properties prop = new Properties();
			InputStream is = 
					Util.class.getClassLoader()
					.getResourceAsStream("utils/db.properties");
			prop.load(is);
			
			driverName=prop.getProperty("driverName");
			server=prop.getProperty("server");
			userName=prop.getProperty("userName");
			passWord=prop.getProperty("passWord");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(){
		Connection conn=null;
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(server,userName,passWord);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	private static ThreadLocal<Connection> tl=new ThreadLocal<Connection>();
	public static Connection getConnection2(){
		Connection conn=tl.get();
		if(conn==null){
			conn=getConnection();
		}
		return conn;
	}
	
	public static void closeConnection(){
		Connection conn=tl.get();
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
	}
}
