package cn.hanquan.orm.core;

/**
 * QueryFactory是所有类的入口，是最先加载的
 * 需要在配置文件中配置queryClass
 * 创建Query对象的工厂类。 对于不同的数据库，根据配置信息配置不同的Query对象。 
 * 使用克隆模式、单例模式、工厂模式。
 * 获得Query对象的时候，全部通过此QueryFactory获取。
 * 
 * @author gaoqi
 */
@SuppressWarnings("all")
public class QueryFactory {

	private QueryFactory factory=new QueryFactory();
	private static Query prototypeObj; // 原型对象
	static {
		try {
			Class c = Class.forName(DBManager.getConf().getQueryClass()); // 根据properties配置文件，加载指定的(mysql/oracle)query类
			prototypeObj = (Query) c.newInstance();//单例模式：类加载的时候执行一次
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 加载po包下面所有的类，便于重用，提高效率
		TableContext.loadPOTables();
	}

	private QueryFactory() { // 私有构造器
	}

	public static Query createQuery() {
		try {
			return (Query) prototypeObj.clone();//克隆模式：以后加载对象的时候返回clone的对象
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}
}
