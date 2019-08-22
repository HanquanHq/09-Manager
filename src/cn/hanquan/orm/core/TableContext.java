package cn.hanquan.orm.core;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import cn.hanquan.orm.bean.ColumnInfo;
import cn.hanquan.orm.bean.TableInfo;
import cn.hanquan.orm.utils.JavaFileUtils;
import cn.hanquan.orm.utils.StringUtils;
import cn.hanquan.servlet.UserServlet;

/**
 * 负责获取管理数据库所有表结构和类结构的关系，并可以根据表结构生成类结构。
 * 
 * @author gaoqi www.sxt.cn
 *
 */
public class TableContext {

	/**
	 * 表名为key，表信息对象为value
	 */
	public static Map<String, TableInfo> tables = new HashMap<String, TableInfo>();

	/**
	 * 将po的class对象和表信息对象关联起来，便于重用！
	 */
	public static Map<Class, TableInfo> poClassTableMap = new HashMap<Class, TableInfo>();

	private TableContext() {
	}

	static {
		Logger logger = Logger.getLogger(UserServlet.class);

		try {
			// 初始化获得表的信息
			Connection con = DBManager.getConn();
			DatabaseMetaData dbmd = con.getMetaData();

			ResultSet tableRet = dbmd.getTables(con.getCatalog(), "%", "%", new String[] { "TABLE" });

			while (tableRet.next()) {
				String tableName = (String) tableRet.getObject("TABLE_NAME");
				TableInfo ti = new TableInfo(tableName, new ArrayList<ColumnInfo>(), new HashMap<String, ColumnInfo>());
				tables.put(tableName, ti);

				ResultSet set = dbmd.getColumns(con.getCatalog(), "%", tableName, "%"); // 查询表中的所有字段
				while (set.next()) {
					ColumnInfo ci = new ColumnInfo(set.getString("COLUMN_NAME"), set.getString("TYPE_NAME"), 0);
					ti.getColumns().put(set.getString("COLUMN_NAME"), ci);
				}

				ResultSet set2 = dbmd.getPrimaryKeys(con.getCatalog(), "%", tableName); // 查询表中的主键
				while (set2.next()) {
					ColumnInfo ci2 = (ColumnInfo) ti.getColumns().get(set2.getObject("COLUMN_NAME"));
					ci2.setKeyType(1); // 设置为主键类型
					ti.getPriKeys().add(ci2);
				}

				if (ti.getPriKeys().size() > 0) { // 取唯一主键。。方便使用。如果是联合主键。则为空！
					ti.setOnlyPriKey(ti.getPriKeys().get(0));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 更新类结构
		updateJavaPOFile();

		// 加载po包下面所有的类，便于重用，提高效率
		// 如果po包下面的.java文件刚生成，此时还没有被编译过啊，这里是无法加载的吧，会抛出ClassNotFoundException异常
		// 所以我觉得，当数据库表信息更新之后，首先要运行JavaFileUtils是必要的准备动作，然后才能执行sql语句，或者在updateJavaPOFile()执行之后，把po下面的类动态的编译一下
		//2019-8-13 22:48:00已解决：不抛异常了，之前的分析是对的。解决方式：在updateJavaPOFile()执行之后，把po下面的类动态的编译一下，因为无法指定路径，所有编译之后再把文件复制到bin中。
		//过程中注意一下流的关闭问题。不关闭流的话，可能无法读取某些文件。
		loadPOTables();
		
		logger.debug("已根据表结构生成po包下的java文件");
	}

	/**
	 * 根据表结构，更新配置的po包下面的java类 实现了从表结构转化到类结构
	 */
	public static void updateJavaPOFile() {
		Map<String, TableInfo> map = TableContext.tables;
		for (TableInfo t : map.values()) {
			JavaFileUtils.createJavaPOFile(t, new MySqlTypeConvertor());//自动生成类的.java文件
		}
	}

	/**
	 * 加载po包下面的类
	 */
	public static void loadPOTables() {

		for (TableInfo tableInfo : tables.values()) {
			try {
				Class c = Class.forName(DBManager.getConf().getPoPackage() + "."
						+ StringUtils.firstChar2UpperCase(tableInfo.getTname()));
				poClassTableMap.put(c, tableInfo);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		Map<String, TableInfo> tables = TableContext.tables;
		System.out.println(tables);
	}

}
