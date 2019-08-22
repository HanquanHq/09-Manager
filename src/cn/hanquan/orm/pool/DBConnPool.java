package cn.hanquan.orm.pool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.hanquan.orm.core.DBManager;

/**
 * 连接池的类：使用连接池，比不使用连接池效率提高大约10倍
 * @author gaoqi
 *
 */
public class DBConnPool {
	/**
	 * 连接池对象
	 */
	private List<Connection> pool;

	/**
	 * 最大连接数
	 */
	private static final int POOL_MAX_SIZE = DBManager.getConf().getPoolMaxSize();
	/**
	 * 最小连接池
	 */
	private static final int POOL_MIN_SIZE = DBManager.getConf().getPoolMinSize();
	
	/**
	 * 初始化连接池，使池中的连接数达到最小值
	 */
	public void initPool() {
		if (pool == null) {
			pool = new ArrayList<Connection>();
		}

		while (pool.size() < DBConnPool.POOL_MIN_SIZE) {
			pool.add(DBManager.createConn());
		}
		System.out.println("初始化池，池中连接数：" + pool.size());
	}
	
	/**
	 * 从连接池中取出一个连接，类似栈。
	 * 使用synchronized，避免两人同时取到同一个连接。
	 * @return
	 */
	public synchronized Connection getConnection() {
		int last_index = pool.size()-1;
		Connection conn = pool.get(last_index);
		pool.remove(last_index);//删除连接， 避免一个连接同时被多次使用
		return conn;
	}
	
	/**
	 * 将连接放回池中
	 * 
	 * @param conn
	 */
	public synchronized void close(Connection conn) {

		if (pool.size() >= POOL_MAX_SIZE) {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			pool.add(conn);
		}
	}

	public DBConnPool() {
		initPool();
	}
	
}
