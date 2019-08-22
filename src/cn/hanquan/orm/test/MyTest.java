package cn.hanquan.orm.test;

import java.sql.Date;

import cn.hanquan.orm.core.Query;
import cn.hanquan.orm.core.QueryFactory;
import cn.hanquan.orm.po.User;

public class MyTest {
	public static void main(String[] args) {
		Query q = QueryFactory.createQuery();

		User u1 = new User();
		u1.setUname("老大");
		u1.setPwd("123456");
		u1.setSex("0");
		u1.setAge(36);
		u1.setBirth(new Date(5000000));
		q.insert(u1);
	}
}
