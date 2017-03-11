package com.tk.cn.utils.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

/**
* 工具类，连接获得： dbcp连接池
*  连接池好处:
*1.避免频繁创建和销毁connection
*2.将connection控制在一定数量范围内 保证服务器稳定.
*/
public class DBUtil {
	private static DataSource dataSource;
	//建议查阅jdk帮助文档
	private static ThreadLocal<Connection> connLocal 
				= new ThreadLocal<Connection>();
	
	static {
		try{
			Properties props = new Properties();
			props.load(DBUtil.class.
					getClassLoader().
					getResourceAsStream("dbcp.properties"));
			dataSource = BasicDataSourceFactory.
						createDataSource(props);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public synchronized static Connection getConnection() throws SQLException {
		Connection conn = connLocal.get();
		if(conn == null){
			conn = dataSource.getConnection();
			connLocal.set(conn);
		}
		return conn;
	}
	

	public synchronized static void closeConnection() throws SQLException{
		Connection conn = connLocal.get();
		connLocal.set(null);
		if(conn != null && !conn.isClosed()){
			conn.close();
		}
	}
	
	
	
	
	
	
	
	
	
	/*
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}		
	}	
	public static Connection getConn()throws Exception{
		Connection conn = 
			DriverManager.getConnection("jdbc:mysql://localhost:3306/tarena","root","root");
		return conn;
	}
	
	public static void closed(ResultSet rs,Statement stmt,Connection conn){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				if(stmt!=null){
					try {
						stmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}finally{
						if(conn!=null){
							try {
								conn.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
		
	}
	public static void main(String[] args) {
		try {
			getConn();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
}
