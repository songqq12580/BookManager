package cn.ac.azure.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * ���ݿ����ӹ�����
 * @author green
 *
 */
public class DBTool {
	private static String driver;  //���ݿ�����
	private static String url;  //���ݿ����ӵ�ַ
	private static String user; //���ݿ������û�
	private static String password;  //���ݿ���������
	
	static{
		//�½�һ��properties,���ڶ�ȡdb.properties�����ļ�
		Properties p=new Properties();
		//�½�һ���ַ���,���������ļ���·��
		String path="cn//ac//azure//util//db.properties";
		try {
			//����Properties.loadͨ������ػ�������ļ���������
			p.load(DBTool.class.getClassLoader().getResourceAsStream(path));
			//��ȡ�����ļ��е����ò���
			driver=p.getProperty("driver");  //��ȡ����
			url=p.getProperty("url");  //��ȡ���ݿ����ӵ�ַ
			user=p.getProperty("user");  //��ȡ���ݿ��û�
			password=p.getProperty("password");  //��ȡ���ݿ�����
			try {
				//�������ݿ������ൽ������
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				throw new RuntimeException("��������ʧ��",e);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("�Ҳ��������ļ�",e);
		}
	}
	/**
	 * ��ȡ���ݿ�����
	 * @return ���ݿ����Ӷ���
	 * @throws SQLException ���ѵ����߲����쳣,����finally�йرչر��쳣
	 */
	public static Connection getConnetion() throws SQLException{
		//ͨ��DriverManager������ݿ�����
		return DriverManager.getConnection(url, user, password);
	}
	/**
	 * �ر����ݿ�����
	 * @param con
	 */
	public static void close(Connection con){
		if(con!=null){ //����������Ӳ�Ϊ��
			try {
				//�ر����ݿ�����
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("���ݿ�ر�ʧ��",e);
			}
		}
	}
//	/**
//	 * �������ݿ����ӹ����Ƿ����
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		Connection con=null;
//		try {
//			con=DBTool.getConnetion();
//			System.out.println("���ݿ����ӳɹ�!");
//		} catch (SQLException e) {
//			System.out.println("���ݿ�����ʧ��!");
//			e.printStackTrace();
//		}finally{
//			DBTool.close(con);
//		}
//	}
}
