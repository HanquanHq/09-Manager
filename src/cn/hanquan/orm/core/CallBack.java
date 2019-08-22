package cn.hanquan.orm.core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface CallBack {
	/**
	 * 查询语句的回调
	 * @param conn
	 * @param ps
	 * @param rs
	 */
	public Object doExecute(Connection conn, PreparedStatement ps, ResultSet rs);
}
