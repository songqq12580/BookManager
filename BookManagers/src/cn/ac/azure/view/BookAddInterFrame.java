package cn.ac.azure.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

import cn.ac.azure.dao.BookDao;
import cn.ac.azure.dao.BookTypeDao;
import cn.ac.azure.model.Book;
import cn.ac.azure.model.BookType;
import cn.ac.azure.util.DBTool;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BookAddInterFrame extends JInternalFrame {
	private JTextField bookNameText;
	private JTextField authorText;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField priceText;
	private JComboBox bookTypeComboBox;
	private JRadioButton maleBtn;
	private JRadioButton femaleBtn;
	private JTextArea bookDescText;

	private BookTypeDao bookTypeDao;
	private BookDao bookDao;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookAddInterFrame frame = new BookAddInterFrame();
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
	public BookAddInterFrame() {
		setIconifiable(true);
		setClosable(true);

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
		setTitle("\u56FE\u4E66\u6DFB\u52A0");
		setBounds(100, 100, 529, 449);
		
		JLabel label = new JLabel("\u56FE\u4E66\u540D\u79F0\uFF1A");
		
		bookNameText = new JTextField();
		bookNameText.setColumns(10);
		
		JLabel label_1 = new JLabel("\u56FE\u4E66\u4F5C\u8005\uFF1A");
		
		authorText = new JTextField();
		authorText.setColumns(10);
		
		JLabel label_2 = new JLabel("\u4F5C\u8005\u6027\u522B\uFF1A");
		
		maleBtn = new JRadioButton("\u7537");
		buttonGroup.add(maleBtn);
		
		femaleBtn = new JRadioButton("\u5973");
		buttonGroup.add(femaleBtn);
		
		JLabel label_3 = new JLabel("\u56FE\u4E66\u4EF7\u683C\uFF1A");
		
		priceText = new JTextField();
		priceText.setColumns(10);
		
		JLabel label_4 = new JLabel("\u56FE\u4E66\u7C7B\u522B\uFF1A");
		//ͼ�����������
		bookTypeComboBox = new JComboBox();
		
		JLabel label_5 = new JLabel("\u56FE\u4E66\u63CF\u8FF0\uFF1A");
		
		bookDescText = new JTextArea();
		
		//ͼ����Ӱ�ť
		JButton addBtn = new JButton("\u6DFB\u52A0");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ͼ����Ӱ�ť�¼�����
				bookAddActionPerformed(e);
			}
		});
		addBtn.setIcon(new ImageIcon(BookAddInterFrame.class.getResource("/images/add.png")));
		
		//ͼ�����ð�ť
		JButton resetBtn = new JButton("\u91CD\u7F6E");
		resetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookResetActionPerformed(e);
			}
		});
		resetBtn.setIcon(new ImageIcon(BookAddInterFrame.class.getResource("/images/reset.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(38)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(6)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(label_4)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(bookTypeComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(label)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(bookNameText, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(label_2)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(maleBtn)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(femaleBtn)))
									.addGap(44)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(label_3)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(priceText))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(label_1)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(authorText, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label_5)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(bookDescText)))
							.addPreferredGap(ComponentPlacement.RELATED, 164, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(94)
							.addComponent(addBtn)
							.addGap(96)
							.addComponent(resetBtn)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(bookNameText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1)
						.addComponent(authorText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(maleBtn)
						.addComponent(femaleBtn)
						.addComponent(label_3)
						.addComponent(priceText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_4)
						.addComponent(bookTypeComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_5)
						.addComponent(bookDescText, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE))
					.addGap(38)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(addBtn)
						.addComponent(resetBtn))
					.addContainerGap(45, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		//�����ı���߿�
		bookDescText.setBorder(new LineBorder(new java.awt.Color(127,157,185), 1, false));
		//�ڹ��캯���е���ͼ������������ʼ������
		fillBookTypeName();
		//�ڹ��캯���г�ʼ���Ա�Ĭ��Ϊ��
		maleBtn.setSelected(true);
	}
	/**
	 * ���ð�ť�¼�����
	 * @param evt ���ð�ť�¼�����
	 */
	private void bookResetActionPerformed(ActionEvent evt) {
		reset();
	}
	/**
	 * ͼ����ӽ�����Ϣ����
	 */
	private void reset(){
		bookNameText.setText("");
		authorText.setText("");
		maleBtn.setSelected(true);
		priceText.setText("");
		bookTypeComboBox.setSelectedIndex(0);
		bookDescText.setText("");
	}
	/**
	 *  ͼ����Ӱ�ť�¼�����
	 * @param evt ����¼�����
	 */
	private void bookAddActionPerformed(ActionEvent evt) {
		
		String bookName=bookNameText.getText();  //��ȡͼ������
		if(bookName==null || "".equals(bookName.trim())){
			JOptionPane.showMessageDialog(null, "ͼ�����Ʋ���Ϊ�գ�");
			return;
		}
		String author=authorText.getText();  //��ȡͼ������
		
		String sex=null;  //��ȡͼ�������Ա�
		if(maleBtn.isSelected()){
			sex="��";
		}else{
			sex="Ů";
		}
		
		String prices=priceText.getText(); //��ȡͼ��۸�
		if(prices==null || "".equals(prices.trim())){
			JOptionPane.showMessageDialog(null, "ͼ��۸���Ϊ�գ�");
			return;
		}
		float price=Float.parseFloat(prices);
		
		BookType bookType=(BookType)bookTypeComboBox.getSelectedItem(); //��ȡͼ�����
		int bookTypeId=bookType.getId(); //��ȡͼ�����id
		
		String bookDesc=bookDescText.getText(); //��ȡͼ������
		
		//���ݻ�ȡ�����ͼ������ȡ����Ϣ����ͼ�����
		Book book=new Book(bookName, author, sex, price, bookTypeId, bookDesc);

		//�������ݿ�����
		Connection con=null;
		try {
			//��ȡ���ݿ�����
			con=DBTool.getConnetion();
			//��ʼ��ͼ�����ݷ��ʶ���
			bookDao=new BookDao();
			//������ӷ����������ݿ�����鼮
			int num=bookDao.add(con, book);
			//���ݷ���ֵ�ж�ͼ���Ƿ���ӳɹ�
			if(num>0){
				JOptionPane.showMessageDialog(null, "ͼ����ӳɹ�n_n");
				//��ӳɹ�֮�����ý���
				reset();
			}else{
				JOptionPane.showMessageDialog(null, "ͼ����ӳɹ�u_u");
			}
		} catch (SQLException e) {
			//��¼��־
			e.printStackTrace();
			throw new RuntimeException("���ͼ��ʧ��",e);
		}finally{
			//�ر����ݿ�����
			DBTool.close(con);
		}
	}

	//���ͼ���������
	private void fillBookTypeName(){
		//�������ݿ����Ӷ���
		Connection con=null;
		//����ͼ�����,���ڲ�ѯ�ʹ����ѯ���鼮
		BookType bookType=null;
		try {
			//��ȡ���ݿ�����
			con=DBTool.getConnetion();
			//��ʼ��ͼ�������ʶ���
			bookTypeDao=new BookTypeDao();
			//��ѯt_bookType�к��е�ͼ�����
			ResultSet rs=bookTypeDao.search(con, bookType);
			//������ѯ���
			while(rs.next()){
				//���»�ͼ�����
				bookType=new BookType();
				//����ͼ���id
				bookType.setId(rs.getInt("id"));
				//����ͼ�������
				bookType.setBookTypeName(rs.getString("bookTypeName"));
				//��ͼ����������ӵ��������У�������Ӷ��󣬱��ڻ��id��
				bookTypeComboBox.addItem(bookType);
			}
		} catch (SQLException e) {
			//��¼��־
			e.printStackTrace();
			throw new RuntimeException("��ʼ���б�ʧ��",e);
		}finally{
			//�ر�����·����
			DBTool.close(con);
		}
	}
}
