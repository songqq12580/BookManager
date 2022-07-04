package cn.ac.azure.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.ac.azure.model.BookType;

/**
 * ͼ�����dao��
 * @author green
 *
 */
public class BookTypeDao {
	/**
	 * ͼ��������
	 * @param con ���ݿ����Ӷ���
	 * @param bookType Ҫ��ӵ�ͼ�����
	 * @return �������ݿ�����ļ�¼��
	 * @throws SQLException 
	 */
	public int add(Connection con,BookType bookType) throws SQLException{
		//ƴдsql�������
		String sql="insert into t_bookType values(null,?,?)";
		//����Ԥ�������ps
		PreparedStatement ps=con.prepareStatement(sql);
		//����ps����
		ps.setString(1, bookType.getBookTypeName()); //����bookTypeName
		ps.setString(2, bookType.getBookTypeDesc()); //����bookTypeDesc
		//�������ݿ�����ļ�¼��
		return ps.executeUpdate();  
	}
	/**
	 * ͼ������ѯ
	 * @param con ���ݿ����Ӷ���
	 * @param bookType ��ѯ��ͼ�����
	 * @return ���ز�ѯ�Ľ����
	 * @throws SQLException �׳����ݿ��쳣 
	 */
	public ResultSet search(Connection con,BookType bookType) throws SQLException{
		/*
		 * ˼·����jdbc��ѯ���ݿ��ж���������ⲿ����ʱ��������ô���һ���ַ��������������������sql����С�
		 * ͬʱ����֪������Щ�����ǵ�һ�������޷�ȷ��where�ؼ��ֵ����ڣ���������������ã�and ������
		 * ����ַ���ת�����ַ���ʱ�ڽ���һ��and�滻��where
		 */
		//����һ��ͼ���������
		String bookTypeName=null;
		if(bookType!=null){ //���������Ϊ�յĻ����ͻ�ȡ�����������
			bookTypeName=bookType.getBookTypeName();
		}
		//����һ���ַ���������
		StringBuilder sb=new StringBuilder("select * from t_bookType");
		//��Ӳ�ѯ����������and + ������
		if(!(bookTypeName==null || "".equals(bookTypeName.trim()))){
			//jdbc�У����ݿ��ַ���Ҫ�õ�����������
			sb.append(" and bookTypeName like '%"+bookType.getBookTypeName()+"%'");
		}
		//���ַ����������ת�����ַ�����ͬʱ�ѵ�һ��and�滻��where
		String sql=sb.toString().replaceFirst("and", "where");   
		//��ȡԤ�������
		PreparedStatement ps=con.prepareStatement(sql);
		//����psִ�в�ѯ֮��Ľ����
		return ps.executeQuery();
	}
	/**
	 * ͼ������޸�
	 * @param con ����·���Ӷ���
	 * @param bookType Ҫ�޸ĵ�ͼ�����
	 * @return �������ݿ���µļ�¼��
	 * @throws SQLException �׳����ݿ��쳣
	 */
	public int update(Connection con,BookType bookType) throws SQLException{
		//ƴдsql�������
		String sql="update t_bookType set bookTypeName=? , bookTypeDesc=? where id=?";
		//��ȡԤ�������ps
		PreparedStatement ps=con.prepareStatement(sql);
		//����ps����
		ps.setString(1,bookType.getBookTypeName());
		ps.setString(2,bookType.getBookTypeDesc());
		ps.setInt(3, bookType.getId());
		//�ϻ�ps�������ݿ�ļ�¼��
		return ps.executeUpdate();
	}
	/**
	 * ɾ�����ݿ��¼
	 * @param con ���ݿ����Ӷ���
	 * @param id ����ɾ����¼��id
	 * @return ����ɾ���ļ�¼��
	 * @throws SQLException �׳����ݿ��쳣
	 */
	public int delete(Connection con,String id) throws SQLException{
		//ƴдsqlɾ�����
		String sql="delete from t_bookType where id=?";
		//��ȡԤ�������ps
		PreparedStatement ps=con.prepareStatement(sql);
		//����ps����
		ps.setString(1, id);
		//ִ��ps���в����������ظı�����ݼ�¼����
		return ps.executeUpdate();
	}
}
