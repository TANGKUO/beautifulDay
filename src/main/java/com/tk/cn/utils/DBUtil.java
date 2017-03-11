package com.tk.cn.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;
import javax.sql.DataSource;

//dbcp,c3p0,proxool
public class DBUtil {
	private static DataSource ds;//���ӳ�����
	private static ThreadLocal<Connection> connLocal = 
				new ThreadLocal<Connection>();
	//��̬����
	static {
		try{
			Properties props = new Properties();
			InputStream in = DBUtil.class.getClassLoader()
				.getResourceAsStream("data.properties");
			props.load(in);//�������ӳ����ò���
			ds = null;/*BasicDataSourceFactory
				.createDataSource(props);*/
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws Exception{
		//��ǰ�̵߳�һ�ε���ʱ���½�һ������
//		��ǰ�߳��ٴε��ã����ص�һ�δ�����
		Connection conn = connLocal.get();
		if(conn == null || conn.isClosed()){
			conn = ds.getConnection();//��ȡ����
			connLocal.set(conn);
		}
		return conn;
	}
	
	public static void closeConnection() throws Exception{
		//���threadlocal
		//�ر�conn
		Connection conn = connLocal.get();
		connLocal.set(null);
		if(conn != null){
			conn.close();//�Ż����ӳ�
		}
	}
	
	public static void beginTransaction() throws Exception{
		Connection conn = getConnection();
		conn.setAutoCommit(false);
	}
	public static void commit() {
		try{
			Connection conn = getConnection();
			conn.commit();
		}catch(Exception ex){
		}
	}
	
	public static void rollback(){
		try{
			Connection conn = getConnection();
			conn.rollback();
		}catch(Exception ex){
		}
	}
	
	
	public static void main(String[] args){
		try {
			System.out.println(getConnection().hashCode());
			System.out.println(getConnection().hashCode());
			System.out.println(getConnection().hashCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
