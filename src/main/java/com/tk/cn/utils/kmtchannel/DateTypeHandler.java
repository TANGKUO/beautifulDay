package com.tk.cn.utils.kmtchannel;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

/**
 * 日期转换
 * 
 * @author Administrator
 * 
 */
public class DateTypeHandler implements TypeHandler {

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public String getResult(ResultSet rs, String name) throws SQLException {
		if (null != rs.getTimestamp(name))
			return this.sdf.format(rs.getTimestamp(name));
		return "";
	}

	@Override
	public String getResult(CallableStatement arg0, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
		// TODO Auto-generated method stub

	}

}
