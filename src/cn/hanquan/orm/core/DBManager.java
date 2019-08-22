package cn.hanquan.orm.core;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import cn.hanquan.orm.bean.Configuration;
import cn.hanquan.orm.pool.DBConnPool;

/**
 * 根据db.properties配置信息，维持连接对象的管理(增加连接池功能)
 * @author Administrator
 *
 */
public class DBManager {
	/**
	 * 配置信息
	 */
	private static Configuration conf;
	/**
	 * 连接池
	 */
	private static DBConnPool pool;
	static {
		Properties pros = new Properties();
		try {
			pros.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 读取、加载所有的配置信息
		conf = new Configuration();
		conf.setDriver(pros.getProperty("driver"));
		conf.setPoPackage(pros.getProperty("poPackage"));
		conf.setPwd(pros.getProperty("pwd"));
		conf.setSrcPath(pros.getProperty("srcPath"));
		conf.setUrl(pros.getProperty("url"));
		conf.setUser(pros.getProperty("user"));
		conf.setUsingDB(pros.getProperty("usingDB"));
		conf.setQueryClass(pros.getProperty("queryClass"));
		conf.setPoolMinSize(Integer.parseInt(pros.getProperty("poolMinSize")));
		conf.setPoolMaxSize(Integer.parseInt(pros.getProperty("poolMaxSize")));

		// 加载TableContext类
		System.out.println("加载"+TableContext.class);
	}

	/**
	 * 创造一个获取数据库连接，供连接池使用
	 * @return
	 */
	public static Connection createConn(){
		try {
			Class.forName(conf.getDriver());
			return DriverManager.getConnection(conf.getUrl(),
					conf.getUser(),conf.getPwd());     //直接建立连接，后期增加连接池处理，提高效率！！！
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 获取数据库连接：使用连接池
	 * @return
	 */
	public static Connection getConn() {
		if (pool == null) {
			pool = new DBConnPool();
		}
		return pool.getConnection();
	}
	
	public static void close(ResultSet rs,Statement ps,Connection conn){
		try {
			if(rs!=null){
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(ps!=null){
				ps.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		try {
//			if(conn!=null){
//				conn.close();
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		pool.close(conn);
	}
	
	public static void close(Statement ps,Connection conn){
		try {
			if(ps!=null){
				ps.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		try {
//			if(conn!=null){
//				conn.close();
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		pool.close(conn);
	}
	public static void close(Connection conn){
//		try {
//			if(conn!=null){
//				conn.close();
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		pool.close(conn);
	}
	
	/**
	 * 返回Configuration对象
	 * @return
	 */
	public static Configuration getConf(){
		return conf;
	}
}
