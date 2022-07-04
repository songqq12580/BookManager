package cn.ac.azure.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

import cn.ac.azure.dao.BookTypeDao;
import cn.ac.azure.model.BookType;
import cn.ac.azure.util.DBTool;
/**
 * ͼ������ڲ���Ӵ���
 * @author green
 *
 */
public class BookTypeAddInterFrame extends JInternalFrame {
	//ͼ��������������
	private JTextField bookTypeNameText;
	//ͼ��������������
	private JTextArea bookTypeDescText;
	//���ð�ť
	private JButton resetBtn;
	//��Ӱ�ť
	private JButton addBtn;
	//ͼ��������ݿ���ʶ���
	private BookTypeDao bookTypeDao;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookTypeAddInterFrame frame = new BookTypeAddInterFrame();
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
	public BookTypeAddInterFrame() {
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
		setClosable(true);
		setIconifiable(true);
		setTitle("\u56FE\u4E66\u7C7B\u522B\u6DFB\u52A0");
		setBounds(100, 100, 487, 342);
		
		JLabel label = new JLabel("\u56FE\u4E66\u7C7B\u522B\u540D\u79F0\uFF1A");
		
		bookTypeNameText = new JTextField();
		bookTypeNameText.setColumns(10);
		
		JLabel label_1 = new JLabel("\u56FE\u4E66\u7C7B\u522B\u63CF\u8FF0\uFF1A");
		
		bookTypeDescText = new JTextArea();
		
		//��Ӱ�ť
		addBtn = new JButton("\u6DFB\u52A0");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addActionPerformed(e);
			}
		});
		//���ð�ť
		resetBtn = new JButton("\u91CD\u7F6E");
		resetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetActionPerformed(e);
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(128)
							.addComponent(addBtn)
							.addGap(91)
							.addComponent(resetBtn))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(89)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(bookTypeDescText)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(bookTypeNameText, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE))
								.addComponent(label_1))))
					.addContainerGap(105, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(49)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(bookTypeNameText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(label_1)
					.addGap(10)
					.addComponent(bookTypeDescText, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
					.addGap(41)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(resetBtn)
						.addComponent(addBtn))
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
		
		//�����ı���߿�
		bookTypeDescText.setBorder(new LineBorder(new java.awt.Color(127,157,185), 1, false));
	}
	/**
	 * ����¼�����
	 * @param evt
	 */
	private void addActionPerformed(ActionEvent evt) {
		//��ȡ������ֵ
		String bookTypeName=bookTypeNameText.getText();
		String bookTypeDesc=bookTypeDescText.getText();
		if(bookTypeName==null || "".equals(bookTypeName.trim())){
			JOptionPane.showMessageDialog(null,"ͼ�������Ϊ�գ�");
			return;
		}
		//�½�ͼ�����ʵ�����
		BookType bookType=new BookType(bookTypeName, bookTypeDesc);
		//�������ݿ�����
		Connection con=null;
		try {
			//��ȡ���ݿ�����
			con=DBTool.getConnetion();
			//��ʼ��ͼ��������BookTypeDao
			bookTypeDao=new BookTypeDao();
			//����ͼ�����dao�������ӷ���
			int res=bookTypeDao.add(con, bookType);
			if(res!=0){
				//��ʾ��ӳɹ�
				JOptionPane.showMessageDialog(null, "ͼ����ӳɹ�(n_n)");
				//��������
				bookTypeNameText.setText("");
				bookTypeDescText.setText("");
			}else{
				//��ʾ���ʧ��
				JOptionPane.showMessageDialog(null,"ͼ�����ʧ��(u_u)");
			}
		} catch (SQLException e) {
			//��¼��־
			e.printStackTrace();
			throw new RuntimeException("���ͼ��ʧ��",e);
		}finally{
			//�ر����ݿ�
			DBTool.close(con);
		}
	}

	/**
	 * �����¼�����
	 * @param evt
	 */
	private void resetActionPerformed(ActionEvent evt) {
		//�ÿ�ͼ��������������
		bookTypeNameText.setText("");
		//�ÿ�ͼ��������������
		bookTypeDescText.setText("");
	}
}
