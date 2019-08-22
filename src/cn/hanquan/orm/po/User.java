package cn.hanquan.orm.po;

import java.sql.*;
import java.util.*;

public class User {

	private Integer uid;
	private String uname;
	private String sex;
	private java.sql.Date birth;
	private String pwd;
	private Integer age;


	public Integer getUid(){
		return uid;
	}
	public String getUname(){
		return uname;
	}
	public String getSex(){
		return sex;
	}
	public java.sql.Date getBirth(){
		return birth;
	}
	public String getPwd(){
		return pwd;
	}
	public Integer getAge(){
		return age;
	}
	public void setUid(Integer uid){
		this.uid=uid;
	}
	public void setUname(String uname){
		this.uname=uname;
	}
	public void setSex(String sex){
		this.sex=sex;
	}
	public void setBirth(java.sql.Date birth){
		this.birth=birth;
	}
	public void setPwd(String pwd){
		this.pwd=pwd;
	}
	public void setAge(Integer age){
		this.age=age;
	}
	public String toString() {
		return "" + uid+"\t"+uname+"\t"+sex+"\t"+birth+"\t"+pwd+"\t"+age;
	}
}