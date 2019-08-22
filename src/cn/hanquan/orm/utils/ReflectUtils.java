package cn.hanquan.orm.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 封装了反射常用的操作
 * 
 * @author gaoqi www.sxt.cn
 *
 */
@SuppressWarnings("all")
public class ReflectUtils {

	/**
	 * 调用obj对象对应属性fieldName的get方法
	 * 
	 * @param fieldName 要取出get方法的属性名
	 * @param obj       被反射的对象
	 * @return 反射得到的get方法
	 */
	public static Object invokeGet(String fieldName, Object obj) {
		try {
			Class c = obj.getClass();
			Method m = c.getDeclaredMethod("get" + StringUtils.firstChar2UpperCase(fieldName), null);
			//System.out.println("fieldName=" + fieldName);
			//System.out.println("m=" + m);
			//System.out.println("invokeGet=" + m.invoke(obj, null));
			return m.invoke(obj, null);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 调用obj对象对应列columnName的set方法，
	 * 将obj对象的columnName属性设置为columnValue值
	 * 
	 * @param obj         被反射的对象
	 * @param columnName  选择的列名
	 * @param columnValue 要设置的列值
	 */
	public static void invokeSet(Object obj, String columnName, Object columnValue) {
		try {
			Method m = obj.getClass().getDeclaredMethod("set" + StringUtils.firstChar2UpperCase(columnName),
					columnValue.getClass());
			m.invoke(obj, columnValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
