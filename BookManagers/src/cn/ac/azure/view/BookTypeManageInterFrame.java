package cn.ac.azure.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import cn.ac.azure.dao.BookTypeDao;
import cn.ac.azure.model.BookType;
import cn.ac.azure.util.DBTool;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BookTypeManageInterFrame extends JInternalFrame {
	private JTextField s_bookTypeNameText;
	private JTable bookTypeTable;
	private BookTypeDao bookTypeDao=new BookTypeDao();
	private JTextField idText;
	private JTextField bookTypeNameText;
	private JTextArea bookTypeDescText;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookTypeManageInterFrame frame = new BookTypeManageInterFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BookTypeManageInterFrame() {
		
		//�ı�ϵͳĬ������
		Font font = new Font("Dialog", Font.PLAIN, 12);
		java.util.Enumeration keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof javax.swing.plaf.FontUIResource) {
				UIManager.put(key, font);
			}
		}
		setIconifiable(true);
		setClosable(true);
		setTitle("\u56FE\u4E66\u7C7B\u522B\u7BA1\u7406");
		setBounds(400, 100, 535, 489);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel label = new JLabel("\u56FE\u4E66\u7C7B\u522B\u540D\u79F0\uFF1A");
		
		s_bookTypeNameText = new JTextField();
		s_bookTypeNameText.setColumns(10);
		
		//��ѯ��ť
		JButton searchBtn = new JButton("\u67E5\u8BE2");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchActionPerformed(e);
			}
		});
		searchBtn.setIcon(new ImageIcon(BookTypeManageInterFrame.class.getResource("/images/search.png")));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u8868\u5355\u64CD\u4F5C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(56)
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(s_bookTypeNameText, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
					.addComponent(searchBtn)
					.addGap(71))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(36)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(scrollPane, Alignment.LEADING))
					.addContainerGap(31, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(searchBtn)
						.addComponent(label)
						.addComponent(s_bookTypeNameText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JLabel label_1 = new JLabel("\u7F16\u53F7\uFF1A");
		
		idText = new JTextField();
		idText.setEditable(false);
		idText.setColumns(10);
		
		JLabel label_2 = new JLabel("\u56FE\u4E66\u7C7B\u522B\u540D\u79F0\uFF1A");
		
		bookTypeNameText = new JTextField();
		bookTypeNameText.setColumns(10);
		
		JLabel label_3 = new JLabel("\u63CF\u8FF0\uFF1A");
		
		bookTypeDescText = new JTextArea();
		
		//�޸İ�ť
		JButton modifyBtn = new JButton("\u4FEE\u6539");
		modifyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookTypeUpdateActionPerformed(e);
			}
		});
		modifyBtn.setIcon(new ImageIcon(BookTypeManageInterFrame.class.getResource("/images/modify.png")));
		
		//ɾ����ť
		JButton deleteBtn = new JButton("\u5220\u9664");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookTypeDeleteActionPerformed(e);
			}
		});
		deleteBtn.setIcon(new ImageIcon(BookTypeManageInterFrame.class.getResource("/images/delete.png")));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
							.addComponent(label_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(idText, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(label_2)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(bookTypeNameText, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
							.addComponent(label_3)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(bookTypeDescText))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(modifyBtn)
							.addGap(54)
							.addComponent(deleteBtn)
							.addGap(64)))
					.addContainerGap(56, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(idText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_2)
						.addComponent(bookTypeNameText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_3)
						.addComponent(bookTypeDescText, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(deleteBtn)
						.addComponent(modifyBtn))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		//���
		bookTypeTable = new JTable();
		//���������¼�
		bookTypeTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				bookTypeTableMousePressed(e);
			}
		});
		bookTypeTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u56FE\u4E66\u7C7B\u522B\u540D\u79F0", "\u56FE\u4E66\u7C7B\u522B\u63CF\u8FF0"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		bookTypeTable.getColumnModel().getColumn(1).setPreferredWidth(96);
		bookTypeTable.getColumnModel().getColumn(2).setPreferredWidth(185);
		scrollPane.setViewportView(bookTypeTable);
		getContentPane().setLayout(groupLayout);
		
		//�����ı���߿�
		bookTypeDescText.setBorder(new LineBorder(new java.awt.Color(127,157,185), 1, false));
		//���캯���е�����������ݺ�����ȫ��ͼ�������ʾ�ڱ����
		fillTable(new BookType());
	}
	/**
	 * ͼ�����ɾ���¼�����
	 * @param evt
	 */
	private void bookTypeDeleteActionPerformed(ActionEvent evt) {
		//��ñ��б�ŵ�ֵid
		String id=idText.getText();
		//�жϱ���û��ѡ�е�ͼ������¼
		if(id==null || "".equals(id.trim())){
			JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�޸ĵļ�¼��");
			return;
		}
		//����ȷ�Ͽ��Ƿ�Ҫɾ��ͼ������¼
		int res=JOptionPane.showConfirmDialog(null, "��ȷ��Ҫɾ��������¼��");
		if(res!=0){ //��
			return; //�������¼�������
		}
		//�������ݿ�����
		Connection con=null;
		try {
			//��ȡ����·����
			con=DBTool.getConnetion();
			int row=bookTypeDao.delete(con, id);
			if(row==1){//ɾ���ɹ���������ʾ��
				JOptionPane.showMessageDialog(null, "�޸����ݳɹ�(n_n)");
				//��ձ�����
				resetValue();
				//ˢ�±���¼��ʾ
				fillTable(new BookType());
			}else{//ɾ��ʧ�ܣ�������ʾ��
				JOptionPane.showMessageDialog(null, "�޸�����ʧ��(u_u)");
			}
		} catch (SQLException e) {
			//��¼��־
			e.printStackTrace();
			throw new RuntimeException("ɾ����¼ʧ�ܣ�",e);
		}finally{
			//�ر����ݿ�
			DBTool.close(con);
		}
	}

	/**
	 * ͼ������޸��¼�����
	 * @param evt
	 */
	private void bookTypeUpdateActionPerformed(ActionEvent evt) {
		//��ȡ�����������ı����ֵ
		String id=idText.getText();
		String bookTypeName=bookTypeNameText.getText();
		String bookTypeDesc=bookTypeDescText.getText();
		//�жϱ���û��ѡ�е�ͼ������¼
		if(id==null || "".equals(id.trim())){
			JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�޸ĵļ�¼��");
			return;
		}
		//ͼ��������Ʋ���Ϊ��
		if(bookTypeName==null || "".equals(bookTypeName.trim())){
			JOptionPane.showMessageDialog(null, "ͼ��������Ʋ���Ϊ�գ�");
			return;
		}
		//���ñ��������½�һ��ͼ��������
		BookType bookType=new BookType(Integer.parseInt(id), bookTypeName, bookTypeDesc);
		//�������ݿ����Ӷ���
		Connection con=null;
		try {
			//��ȡ���ݿ�����
			con=DBTool.getConnetion();
			//ִ��ͼ�����dao����޸ļ�¼����
			int res=bookTypeDao.update(con, bookType);
			if(res==1){//�޸ĳɹ���������ʾ��
				JOptionPane.showMessageDialog(null, "�޸����ݳɹ�(n_n)");
				//��ձ�����
				resetValue();
				//ˢ�±���¼��ʾ
				fillTable(new BookType());
			}else{//�޸�ʧ�ܣ�������ʾ��
				JOptionPane.showMessageDialog(null, "�޸�����ʧ��(u_u)");
			}
		} catch (SQLException e) {
			//��¼��־
			e.printStackTrace();
			throw new RuntimeException("�޸�ͼ�����ʧ��",e);
		}finally{
			//�ر�����·����
			DBTool.close(con);
		}
	}

	/**
	 * ���������¼�����
	 * @param e 
	 */
	private void bookTypeTableMousePressed(MouseEvent e) {
		//��ȡ���ѡ�е���
		int row=bookTypeTable.getSelectedRow();
		//��ȡ����ѡ���еĵ�һ�е�ֵ����ʾ��idText����
		idText.setText(String.valueOf(bookTypeTable.getValueAt(row, 0)));
		//��ȡ����ѡ���еĵڶ��е�ֵ����ʾ��bookTypeNameText����
		bookTypeNameText.setText((String)bookTypeTable.getValueAt(row, 1));
		//��ȡ����ѡ���еĵ����е�ֵ����ʾ��bookTypeDescText����
		bookTypeDescText.setText((String)bookTypeTable.getValueAt(row, 2));
	}

	/**
	 * ͼ������ѯ�¼�����
	 * @param evt
	 */
	private void searchActionPerformed(ActionEvent evt) {
		//��ȡͼ�����������������
		String s_bookTypeName=s_bookTypeNameText.getText();
		//�½�һ��ͼ����𲢳�ʼ��
		BookType bookType=new BookType();
		//���������������ó��½�ͼ������ͼ���������
		bookType.setBookTypeName(s_bookTypeName);
		//����ͼ������ѯͼ�����
		fillTable(bookType);
	}

	/**
	 * �ڱ�����������
	 * @param bookType ����bookType����
	 */
	private void fillTable(BookType bookType){
		//��ȡ����ģ��
		DefaultTableModel dtm=(DefaultTableModel) bookTypeTable.getModel();
		//��ձ��
		dtm.setRowCount(0); 
		//�������ݿ�����
		Connection con=null;
		try {
			//��ȡ���ݿ�����
			con=DBTool.getConnetion();
			//����BookTyPeDao�Ĳ�ѯ��������������ѯ�Ľ����
			ResultSet rs=bookTypeDao.search(con, bookType);
			//���������
			while(rs.next()){
				//�½�һ��vector����ʼ��
				Vector v=new Vector(); 
				v.add(rs.getInt("id"));  //��vector�����id
				v.add(rs.getString("bookTypeName")); //��vector�����bookTypeName
				v.add(rs.getString("bookTypeDesc"));  //��vector�����bookTypeDesc
				//��vector�е�������ʾ�������
				dtm.addRow(v);
			}
		} catch (SQLException e) {
			//��¼��־
			e.printStackTrace();
			throw new RuntimeException("��ѯʧ��");
		}finally{
			//�ر����ݿ�
			DBTool.close(con);
		}
	}
	/**
	 * ��ձ�����
	 */
	private void resetValue(){
		idText.setText("");
		bookTypeNameText.setText("");
		bookTypeDescText.setText("");
		s_bookTypeNameText.setText("");
	}
}
