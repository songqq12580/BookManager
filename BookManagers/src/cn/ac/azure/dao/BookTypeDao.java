package cn.ac.azure.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.ac.azure.model.BookType;

/**
 * 图书类别dao类
 * @author green
 *
 */
public class BookTypeDao {
	/**
	 * 图书类别添加
	 * @param con 数据库连接对象
	 * @param bookType 要添加的图书类别
	 * @return 返回数据库操作的记录数
	 * @throws SQLException 
	 */
	public int add(Connection con,BookType bookType) throws SQLException{
		//拼写sql插入语句
		String sql="insert into t_bookType values(null,?,?)";
		//创建预编译对象ps
		PreparedStatement ps=con.prepareStatement(sql);
		//设置ps参数
		ps.setString(1, bookType.getBookTypeName()); //设置bookTypeName
		ps.setString(2, bookType.getBookTypeDesc()); //设置bookTypeDesc
		//返回数据库操作的记录数
		return ps.executeUpdate();  
	}
	/**
	 * 图书类别查询
	 * @param con 数据库连接对象
	 * @param bookType 查询的图书类别
	 * @return 返回查询的结果集
	 * @throws SQLException 抛出数据库异常 
	 */
	public ResultSet search(Connection con,BookType bookType) throws SQLException{
		/*
		 * 思路：当jdbc查询数据库有多个条件从外部输入时，这是最好创建一个字符串缓冲类来添加条件到sql语句中。
		 * 同时，不知道有哪些条件是第一条件，无法确定where关键字的所在，于是添加条件都用（and 条件）
		 * 最后字符串转换成字符串时在将第一个and替换成where
		 */
		//定义一个图书类别名称
		String bookTypeName=null;
		if(bookType!=null){ //如果类别对象不为空的话，就获取它的类别名称
			bookTypeName=bookType.getBookTypeName();
		}
		//创建一个字符串缓冲类
		StringBuilder sb=new StringBuilder("select * from t_bookType");
		//添加查询语句的条件（and + 条件）
		if(!(bookTypeName==null || "".equals(bookTypeName.trim()))){
			//jdbc中，数据库字符串要用单引号括起来
			sb.append(" and bookTypeName like '%"+bookType.getBookTypeName()+"%'");
		}
		//将字符串缓冲对象转换成字符串，同时把第一个and替换成where
		String sql=sb.toString().replaceFirst("and", "where");   
		//获取预编译对象
		PreparedStatement ps=con.prepareStatement(sql);
		//返回ps执行查询之后的结果集
		return ps.executeQuery();
	}
	/**
	 * 图书类别修改
	 * @param con 数据路连接对象
	 * @param bookType 要修改的图书类别
	 * @return 返回数据库更新的记录数
	 * @throws SQLException 抛出数据库异常
	 */
	public int update(Connection con,BookType bookType) throws SQLException{
		//拼写sql更新语句
		String sql="update t_bookType set bookTypeName=? , bookTypeDesc=? where id=?";
		//获取预编译对象ps
		PreparedStatement ps=con.prepareStatement(sql);
		//设置ps参数
		ps.setString(1,bookType.getBookTypeName());
		ps.setString(2,bookType.getBookTypeDesc());
		ps.setInt(3, bookType.getId());
		//赶回ps更新数据库的记录数
		return ps.executeUpdate();
	}
	/**
	 * 删除数据库记录
	 * @param con 数据库连接对象
	 * @param id 传入删除记录的id
	 * @return 返回删除的记录数
	 * @throws SQLException 抛出数据库异常
	 */
	public int delete(Connection con,String id) throws SQLException{
		//拼写sql删除语句
		String sql="delete from t_bookType where id=?";
		//获取预编译对象ps
		PreparedStatement ps=con.prepareStatement(sql);
		//设置ps参数
		ps.setString(1, id);
		//执行ps更行操作，并返回改变的数据记录条数
		return ps.executeUpdate();
	}
}
