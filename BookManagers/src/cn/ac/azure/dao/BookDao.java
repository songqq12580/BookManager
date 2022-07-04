package cn.ac.azure.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.ac.azure.model.Book;

/**
 * ͼ��dao��
 * @author green
 *
 */
public class BookDao {
	/**
	 * ͼ�����
	 * @param con ���ݿ�����Ӷ���
	 * @param book ��ӵ�ͼ�����
	 * @return ������Ӳ��������ݿ��¼��
	 * @throws SQLException �׳����ݿ��쳣
	 */
	public int add(Connection con,Book book) throws SQLException{
		//ƴдsql�������
		String sql="insert into t_book values(null,?,?,?,?,?,?)";
		//���Ԥ�������ps
		PreparedStatement ps=con.prepareStatement(sql);
		//����ps����
		ps.setString(1,book.getBookName());  //����ͼ������
		ps.setString(2,book.getAuthor());    //����ͼ������
		ps.setString(3, book.getSex());      //���������Ա�
		ps.setFloat(4, book.getPrice());     //����ͼ��۸�
		ps.setInt(5, book.getBookTypeId());  //����ͼ�����ID
		ps.setString(6, book.getBookDesc()); //����ͼ������
		//ִ��sql��䣬�����ز���ļ�¼��
		return ps.executeUpdate();
	}
	/**
	 * ͼ���ѯ
	 * @param con ���ݿ����Ӷ���
	 * @param book ͼ�����
	 * @return ��ѯ�Ľ����
	 * @throws SQLException �׳����ݿ��쳣
	 */
	public ResultSet search(Connection con,Book book) throws SQLException{
		//����ͼ������
		String bookName=null;
		//����ͼ������
		String author=null;
		//����ͼ���������
		String bookTypeName=null;
		//���ͼ�鲻Ϊ�յĻ�����Ϊǰ�����ֶθ�ֵ������������ѯ
		if(book!=null){
			bookName=book.getBookName();
			author=book.getAuthor();
			bookTypeName=book.getBookTypeName();
		}
		//����һ���ַ������������洢��ѯ���
		StringBuilder sb=new StringBuilder("select * from t_book b , t_bookType bt where b.bookTypeId=bt.id ");
		//��ѯ������ͼ������
		if(!(bookName==null || "".equals(bookName.trim()))){
			sb.append("and b.bookName like '%"+bookName+"%' ");
		}
		//��ѯ������ͼ������
		if(!(author==null || "".equals(author.trim()))){
			sb.append("and b.author like '%"+author+"%' ");
		}
		//��ѯ������ͼ���������
		if(!(bookTypeName==null || "".equals(bookTypeName.trim()))){
			sb.append("and bt.bookTypeName like '%"+bookTypeName+"%' ");
		}
		//��sb����sql���
		String sql=sb.toString();
		//��ȡԤ�������
		PreparedStatement ps=con.prepareStatement(sql);
		//ִ�в�ѯ�������ؽ����
		return ps.executeQuery();
	}
	/**
	 * ͼ���޸�
	 * @param con ���ݿ����Ӷ���
	 * @param book �޸ĵ�ͼ�����
	 * @return �����޸ĸı�ļ�¼��
	 * @throws SQLException �׳����ݿ��쳣��ͬʱ���ѵ����߹ر����ݿ�
	 */
	public int update(Connection con,Book book) throws SQLException{
		//��дsql���
		String sql="update t_book set bookName=?,author=?,sex=?,"
				+ "price=?,bookTypeId=?,bookDesc=? where id=?";
		//��ȡԤ�������ps
		PreparedStatement ps=con.prepareStatement(sql);
		//����ps���� 
		ps.setString(1, book.getBookName()); //ͼ������
		ps.setString(2, book.getAuthor());   //ͼ������
		ps.setString(3,book.getSex()); //�����Ա�
		ps.setFloat(4, book.getPrice()); //ͼ��۸�
		ps.setInt(5, book.getBookTypeId());  //ͼ������Id
		ps.setString(6, book.getBookDesc()); //ͼ������
		ps.setInt(7, book.getId());
		//ִ���޸Ĳ����ظı�ļ�¼��
		return ps.executeUpdate(); 
	}
	
	/**
	 * ͼ��ɾ��
	 * @param con ���ݿ����Ӷ���
	 * @param id ɾ��ͼ���id
	 * @return ����ɾ���ļ�¼��
	 * @throws SQLException �׳����ݿ��쳣��ͬʱ���ѵ����߹ر����ݿ�
	 */
	public int delete(Connection con,int id) throws SQLException{
		//��дsql���
		String sql="delete from t_book where id=?";
		//��ȡԤ�������ps
		PreparedStatement ps=con.prepareStatement(sql);
		//����ps����
		ps.setInt(1, id);
		//ִ��DMLɾ����䲢����ɾ���ļ�¼��
		return ps.executeUpdate();
	}
}
