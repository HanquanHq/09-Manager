package cn.hanquan.orm.po;

import java.sql.*;
import java.util.*;

public class Pcd {

	private String name;
	private Integer pid;
	private Integer id;


	public String getName(){
		return name;
	}
	public Integer getPid(){
		return pid;
	}
	public Integer getId(){
		return id;
	}
	public void setName(String name){
		this.name=name;
	}
	public void setPid(Integer pid){
		this.pid=pid;
	}
	public void setId(Integer id){
		this.id=id;
	}
	public String toString() {
		return "" + name+"\t"+pid+"\t"+id;
	}
}