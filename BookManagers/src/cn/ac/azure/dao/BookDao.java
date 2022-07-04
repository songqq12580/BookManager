package cn.ac.azure.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.ac.azure.model.Book;

/**
 * 图书dao类
 * @author green
 *
 */
public class BookDao {
	/**
	 * 图书添加
	 * @param con 数据库库连接对象
	 * @param book 添加的图书对象
	 * @return 返回添加操作的数据库记录数
	 * @throws SQLException 抛出数据库异常
	 */
	public int add(Connection con,Book book) throws SQLException{
		//拼写sql插入语句
		String sql="insert into t_book values(null,?,?,?,?,?,?)";
		//获得预编译对象ps
		PreparedStatement ps=con.prepareStatement(sql);
		//设置ps参数
		ps.setString(1,book.getBookName());  //设置图书名称
		ps.setString(2,book.getAuthor());    //设置图书作者
		ps.setString(3, book.getSex());      //设置作者性别
		ps.setFloat(4, book.getPrice());     //设置图书价格
		ps.setInt(5, book.getBookTypeId());  //设置图书类别ID
		ps.setString(6, book.getBookDesc()); //设置图书描述
		//执行sql语句，并返回插入的记录数
		return ps.executeUpdate();
	}
	/**
	 * 图书查询
	 * @param con 数据库连接对象
	 * @param book 图书对象
	 * @return 查询的结果集
	 * @throws SQLException 抛出数据库异常
	 */
	public ResultSet search(Connection con,Book book) throws SQLException{
		//定义图书名称
		String bookName=null;
		//定义图书作者
		String author=null;
		//定义图书类别名称
		String bookTypeName=null;
		//如果图书不为空的话，就为前三个字段赋值，按照条件查询
		if(book!=null){
			bookName=book.getBookName();
			author=book.getAuthor();
			bookTypeName=book.getBookTypeName();
		}
		//定义一个字符串缓冲对象类存储查询添加
		StringBuilder sb=new StringBuilder("select * from t_book b , t_bookType bt where b.bookTypeId=bt.id ");
		//查询条件有图书名称
		if(!(bookName==null || "".equals(bookName.trim()))){
			sb.append("and b.bookName like '%"+bookName+"%' ");
		}
		//查询条件有图书作者
		if(!(author==null || "".equals(author.trim()))){
			sb.append("and b.author like '%"+author+"%' ");
		}
		//查询条件有图书类别名称
		if(!(bookTypeName==null || "".equals(bookTypeName.trim()))){
			sb.append("and bt.bookTypeName like '%"+bookTypeName+"%' ");
		}
		//从sb生成sql语句
		String sql=sb.toString();
		//获取预处理对象
		PreparedStatement ps=con.prepareStatement(sql);
		//执行查询，并返回结果集
		return ps.executeQuery();
	}
	/**
	 * 图书修改
	 * @param con 数据库连接对象
	 * @param book 修改的图书对象
	 * @return 返回修改改变的记录数
	 * @throws SQLException 抛出数据库异常，同时提醒调用者关闭数据库
	 */
	public int update(Connection con,Book book) throws SQLException{
		//编写sql语句
		String sql="update t_book set bookName=?,author=?,sex=?,"
				+ "price=?,bookTypeId=?,bookDesc=? where id=?";
		//获取预编译对象ps
		PreparedStatement ps=con.prepareStatement(sql);
		//设置ps对象 
		ps.setString(1, book.getBookName()); //图书名称
		ps.setString(2, book.getAuthor());   //图书作者
		ps.setString(3,book.getSex()); //作者性别
		ps.setFloat(4, book.getPrice()); //图书价格
		ps.setInt(5, book.getBookTypeId());  //图书类型Id
		ps.setString(6, book.getBookDesc()); //图书描述
		ps.setInt(7, book.getId());
		//执行修改并返回改变的记录数
		return ps.executeUpdate(); 
	}
	
	/**
	 * 图书删除
	 * @param con 数据库连接对象
	 * @param id 删除图书的id
	 * @return 返回删除的记录数
	 * @throws SQLException 抛出数据库异常，同时提醒调用者关闭数据库
	 */
	public int delete(Connection con,int id) throws SQLException{
		//编写sql语句
		String sql="delete from t_book where id=?";
		//获取预编译对象ps
		PreparedStatement ps=con.prepareStatement(sql);
		//设置ps参数
		ps.setInt(1, id);
		//执行DML删除语句并返回删除的记录数
		return ps.executeUpdate();
	}
}
