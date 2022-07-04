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
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import cn.ac.azure.dao.BookDao;
import cn.ac.azure.dao.BookTypeDao;
import cn.ac.azure.model.Book;
import cn.ac.azure.model.BookType;
import cn.ac.azure.util.DBTool;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BookManageInterFrame extends JInternalFrame {
	private JTextField s_bookNameText;
	private JTextField s_authorText;
	private JTable bookTable;
	private JComboBox s_bookTypecomboBox;
	private BookTypeDao bookTypeDao;
	private BookDao bookDao;
	private JTextField idText;
	private JTextField bookNameText;
	private JTextField priceText;
	private JTextField authorText;
	private JTextField bookDescText;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JComboBox bookTypeComboBox;
	private JRadioButton maleBtn;
	private JRadioButton femaleBtn;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookManageInterFrame frame = new BookManageInterFrame();
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
	public BookManageInterFrame() {
		
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
		setTitle("\u56FE\u4E66\u7BA1\u7406");
		setBounds(100, 100, 744, 528);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u641C\u7D22\u6761\u4EF6", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "\u8868\u5355\u64CD\u4F5C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(29)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE))
					.addGap(38))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(29)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(20, Short.MAX_VALUE))
		);
		
		JLabel label_2 = new JLabel("\u7F16\u53F7\uFF1A");
		
		idText = new JTextField();
		idText.setColumns(10);
		
		JLabel label_3 = new JLabel("\u56FE\u4E66\u540D\u79F0\uFF1A");
		
		bookNameText = new JTextField();
		bookNameText.setColumns(10);
		
		JLabel label_4 = new JLabel("\u4F5C\u8005\u6027\u522B\uFF1A");
		
		maleBtn = new JRadioButton("\u7537");
		buttonGroup.add(maleBtn);
		
		femaleBtn = new JRadioButton("\u5973");
		buttonGroup.add(femaleBtn);
		
		JLabel label_5 = new JLabel("\u4EF7\u683C\uFF1A");
		
		priceText = new JTextField();
		priceText.setColumns(10);
		
		JLabel label_6 = new JLabel("\u56FE\u4E66\u4F5C\u8005\uFF1A");
		
		authorText = new JTextField();
		authorText.setColumns(10);
		
		JLabel label_7 = new JLabel("\u56FE\u4E66\u7C7B\u522B\uFF1A");
		
		bookTypeComboBox = new JComboBox();
		
		JLabel label_8 = new JLabel("\u56FE\u4E66\u63CF\u8FF0\uFF1A");
		
		bookDescText = new JTextField();
		bookDescText.setColumns(10);
		
		//�޸İ�ť
		JButton modifyBtn = new JButton("\u4FEE\u6539");
		modifyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifyBookActionPerformed(e);
			}
		});
		modifyBtn.setIcon(new ImageIcon(BookManageInterFrame.class.getResource("/images/modify.png")));
		
		//ɾ����ť
		JButton deleteBtn = new JButton("\u5220\u9664");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteBookActionPerformed(e);
			}
		});
		deleteBtn.setIcon(new ImageIcon(BookManageInterFrame.class.getResource("/images/delete.png")));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(44)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(label_8)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(bookDescText))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addComponent(label_2)
								.addComponent(label_5))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
								.addComponent(priceText)
								.addComponent(idText, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))
							.addGap(37)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(label_3)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(bookNameText, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(label_6)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(authorText)))
							.addGap(35)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(label_4)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(maleBtn)
									.addGap(18)
									.addComponent(femaleBtn))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(label_7)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(bookTypeComboBox, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(34, Short.MAX_VALUE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap(201, Short.MAX_VALUE)
					.addComponent(modifyBtn)
					.addGap(104)
					.addComponent(deleteBtn)
					.addGap(190))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(maleBtn)
						.addComponent(bookNameText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_3)
						.addComponent(label_2)
						.addComponent(idText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(femaleBtn)
						.addComponent(label_4))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(priceText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_5)
						.addComponent(label_6)
						.addComponent(authorText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(bookTypeComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_7))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_8)
						.addComponent(bookDescText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(modifyBtn)
						.addComponent(deleteBtn))
					.addContainerGap())
		);
		panel_1.setLayout(gl_panel_1);
		
		//���
		bookTable = new JTable();
		//�����갴���¼�
		bookTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				tableMousePressed(e);
			}
		});
		bookTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u56FE\u4E66\u540D\u79F0", "\u56FE\u4E66\u4F5C\u8005", "\u4F5C\u8005\u6027\u522B", "\u56FE\u4E66\u4EF7\u683C", "\u56FE\u4E66\u7C7B\u522B", "\u56FE\u4E66\u63CF\u8FF0"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		bookTable.getColumnModel().getColumn(0).setPreferredWidth(56);
		bookTable.getColumnModel().getColumn(1).setPreferredWidth(100);
		bookTable.getColumnModel().getColumn(2).setPreferredWidth(63);
		bookTable.getColumnModel().getColumn(3).setPreferredWidth(63);
		bookTable.getColumnModel().getColumn(4).setPreferredWidth(61);
		bookTable.getColumnModel().getColumn(5).setPreferredWidth(94);
		bookTable.getColumnModel().getColumn(6).setPreferredWidth(163);
		scrollPane.setViewportView(bookTable);
		
		JLabel lblL = new JLabel("\u56FE\u4E66\u540D\u79F0\uFF1A");
		
		s_bookNameText = new JTextField();
		s_bookNameText.setColumns(10);
		
		JLabel label = new JLabel("\u56FE\u4E66\u4F5C\u8005\uFF1A");
		
		s_authorText = new JTextField();
		s_authorText.setColumns(10);
		
		JLabel label_1 = new JLabel("\u56FE\u4E66\u7C7B\u522B\uFF1A");
		
		s_bookTypecomboBox = new JComboBox();
		
		//ͼ���ѯ��ť
		JButton s_searchBtn = new JButton("\u67E5\u8BE2");
		s_searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchActionPerformed(e);
			}
		});
		s_searchBtn.setIcon(new ImageIcon(BookManageInterFrame.class.getResource("/images/search.png")));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(20)
					.addComponent(lblL)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(s_bookNameText, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(s_authorText, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(label_1)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(s_bookTypecomboBox, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(s_searchBtn)
					.addGap(29))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblL)
						.addComponent(s_bookNameText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label)
						.addComponent(s_authorText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1)
						.addComponent(s_bookTypecomboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(s_searchBtn))
					.addContainerGap(19, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
		//��ʼ��������ͼ�����������
		fillBookTypeComboBox("search");
		//��ʼ��������ͼ�����������
		fillBookTypeComboBox("modify");
		//��ʼ�������ʾ����ʾ���е��鼮
		fillBookTable(new Book());
	}
	
	/**
	 * ͼ���޸��¼�
	 * @param evt
	 */
	private void modifyBookActionPerformed(ActionEvent evt) {
		//��ȡͼ��id
		String id=idText.getText();
		//��ȡͼ������
		String bookName=bookNameText.getText();
		//��ȡͼ������
		String author=authorText.getText();
		//���������Ա�
		String sex="��";
		if(femaleBtn.isSelected()){
			sex="Ů";
		}
		//��ȡͼ��۸�
		String price=priceText.getText();
		//��ȡͼ��id
		BookType bookType=(BookType)bookTypeComboBox.getSelectedItem();
		Integer bookTypeId=bookType.getId();
		//��ȡͼ������
		String bookDesc=bookDescText.getText();
		
		//�ж��Ƿ�id�Ƿ�Ϊ��
		if(id==null || "".equals(id)){ //Ϊ��
			JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ�����У�");  //���û���ʾ
			return;
		}
		//�ж�ͼ�������Ƿ�Ϊ��
		if(bookName==null || "".equals(bookName)){ //Ϊ��
			JOptionPane.showMessageDialog(null, "ͼ�����Ʋ���Ϊ�գ�");  //���û���ʾ
			return;
		}
		//�ж�ͼ�������Ƿ�Ϊ��
		if(author==null || "".equals(author)){ //Ϊ��
			JOptionPane.showMessageDialog(null, "ͼ�����߲���Ϊ�գ�");  //���û���ʾ
			return;
		}
		//�ж�ͼ��۸��Ƿ�Ϊ��
		if(price==null || "".equals(price)){ //Ϊ��
			JOptionPane.showMessageDialog(null, "ͼ��۸���Ϊ�գ�");  //���û���ʾ
			return;
		}
		//�ӻ�ȡ��ͼ����Ϣ����ͼ�����
		Book book=new Book(Integer.parseInt(id),bookName, author, sex, Float.parseFloat(price), bookTypeId, bookDesc);
		//�������ݿ�����
		Connection con=null;
		try {
			//��ȡ���ݿ�����
			con=DBTool.getConnetion();
			//��ʼ��ͼ�����ݷ��ʶ���
			bookDao=new BookDao();
			//ִ��ͼ����ʶ�����޸ķ�����������޸ĵļ�¼��
			int res=bookDao.update(con, book);
			if(res==1){ //Ϊ1
				JOptionPane.showMessageDialog(null,"ͼ���޸ĳɹ�n_n");
				//ˢ��ͼ������ʾ
				fillBookTable(new Book());
				//���ò�����
				resetValue();
			}else{ //Ϊ0
				JOptionPane.showMessageDialog(null,"ͼ���޸�ʧ��u_u");
			}
		} catch (SQLException e) {
			//��¼��־
			e.printStackTrace();
			throw new RuntimeException("�޸�ͼ��ʧ��",e);
		}finally{
			//�ر����ݿ�����
			DBTool.close(con);
		}
	}
	/**
	 * ͼ��ɾ���¼�
	 * @param evt
	 */
	private void deleteBookActionPerformed(ActionEvent evt) {
		//��ȡͼ��id
		String id=idText.getText();
		//�ж��Ƿ�id�Ƿ�Ϊ��
		if(id==null || "".equals(id)){ //Ϊ��
			JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ�����У�");  //���û���ʾ
			return;
		}
		//�������ݿ����Ӷ���
		Connection con=null;
		try {
			//��ʼ�����ݿ����Ӷ���
			con=DBTool.getConnetion(); 
			//��ʼ��ͼ�����ݷ��ʶ���
			bookDao=new BookDao();
			//ִ��ͼ����ʶ����ɾ������������ɾ���ļ�¼��
			int res=bookDao.delete(con, Integer.parseInt(id));
			if(res==1){ //Ϊ1
				JOptionPane.showMessageDialog(null, "ͼ��ɾ���ɹ�n_n");
				//ˢ��ͼ������ʾ
				fillBookTable(new Book());
				//���ò�����
				resetValue();
			}else{ //Ϊ����
				JOptionPane.showMessageDialog(null, "ͼ��ɾ��ʧ��u_u");
			}
		} catch (SQLException e) {
			//��¼��־
			e.printStackTrace();
			throw new RuntimeException("ɾ��ͼ��ʧ��",e);
		}finally{
			//�ǵùر����ݿ⣨******��
			DBTool.close(con);
		}
	}
	/**
	 * ���ò�����������ֵ
	 */
	private void resetValue(){
		idText.setText("");
		bookNameText.setText("");
		authorText.setText("");
		maleBtn.setSelected(true);
		priceText.setText("");
		fillBookTypeComboBox("modify");
		bookDescText.setText("");
	}
	/**
	 * �����갴���¼�����
	 * @param evt
	 */
	private void tableMousePressed(MouseEvent evt) {
		//��ȡͼ����ѡ�е��е��к�
		int row=bookTable.getSelectedRow();
		//��ȡѡ���е�һ�����ݲ�������ʾ�ڲ�������id��
		idText.setText((Integer)bookTable.getValueAt(row,0)+"");
		//��ȡѡ���еڶ������ݲ�������ʾ�ڲ�������ͼ�����ƿ�
		bookNameText.setText((String)bookTable.getValueAt(row, 1));
		//��ȡѡ���е��������ݲ�������ʾ�ڲ�������ͼ�����߿�
		authorText.setText((String)bookTable.getValueAt(row, 2));
		//��ȡѡ���е��ĸ����ݲ�������ʾ�ڲ������������Ա�ѡ��
		String sex=(String)bookTable.getValueAt(row, 3);
		if("��".equals(sex)){
			maleBtn.setSelected(true);
		}else{
			femaleBtn.setSelected(true);
		}
		//��ȡѡ���е�������ݲ�������ʾ�ڲ�������ͼ��۸��
		priceText.setText((Float)bookTable.getValueAt(row, 4)+"");
		//��ȡѡ���е��������ݲ�������ʾ�ڲ�������ͼ�������������
		String bookTypeName=(String)bookTable.getValueAt(row, 5);
		int rows=bookTypeComboBox.getItemCount();  //��ȡ�������ܹ���ѡ��
		for(int i=0;i<rows;i++){ //����������
			BookType item=(BookType) bookTypeComboBox.getItemAt(i); //��ȡÿһ��ѡ�ǿתͼ��������
			if(item.getBookTypeName().equals(bookTypeName)){  //����ȡ��ͼ�������������е�ͼ�����Ƚϣ�����ͬ
				bookTypeComboBox.setSelectedIndex(i); //���������ѡ�ѡ��
			}
		}
		//��ȡѡ���е��߸����ݲ�������ʾ�ڲ�������ͼ��������
		bookDescText.setText((String)bookTable.getValueAt(row, 6));
	}

	/**
	 * ͼ���ѯ�¼�����
	 * @param evt �¼�����
	 */
	private void searchActionPerformed(ActionEvent evt) {
		//��ȡ��ѯ������
		String bookName=s_bookNameText.getText();  //ͼ������
		String author=s_authorText.getText();  //ͼ������
		String bookTypeName=s_bookTypecomboBox.getSelectedItem().toString(); //ͼ�����
		//��ͼ�����"��ѡ��..."����""
		if("��ѡ��...".equals(bookTypeName)){
			bookTypeName="";
		}
		//���ɴ���������ͼ�����
		Book book=new Book();
		book.setBookName(bookName);  //����ͼ����������
		book.setAuthor(author);   //����ͼ����������
		book.setBookTypeName(bookTypeName);  //����ͼ���������
		//����table��亯�������ݲ�ѯ������������
		fillBookTable(book);  
	}

	/**
	 *  ��ʼ��ͼ�����������
	 * @param type ���ݲ�ͬ�Ĳ�����䲻ͬ��������
	 */
	
	private void fillBookTypeComboBox(String type){
		
		//����һ��ͼ��������ڴ洢��ѯ��ͼ�����
		BookType s_bookType=null;
		//����һ�����ݿ�����
		Connection con=null;
		try {
			//��ȡ���ݿ�����
			con=DBTool.getConnetion();
			//��ʼ��ͼ�����������ݶ���
			bookTypeDao=new BookTypeDao();
			//��ѯͼ����𣬵õ������
			ResultSet rs=bookTypeDao.search(con, new BookType());
			if("search".equals(type)){
				BookType bookType=new BookType();
				bookType.setBookTypeName("��ѡ��...");
				bookType.setId(-1);
				s_bookTypecomboBox.addItem(bookType);
			}
			//���������
			while(rs.next()){ //��������ݵĻ�
				//��ʼ�����ܲ�ѯ��ͼ�����
				s_bookType=new BookType();
				//���ݲ�ѯ�������id
				s_bookType.setId(rs.getInt("id"));
				//���ݲ�ѯ�������ͼ���������
				s_bookType.setBookTypeName(rs.getString("bookTypeName"));
				if("search".equals(type)){
					//����ѯ��ͼ�������ӵ���������
					s_bookTypecomboBox.addItem(s_bookType);
				}
				if("modify".equals(type)){
					//����ѯ��ͼ�������ӵ���������������
					bookTypeComboBox.addItem(s_bookType);
				}
			}
		} catch (SQLException e) {
			//��¼��־
			e.printStackTrace();
			throw new RuntimeException("��ʼ��������ʧ��",e);
		}finally{
			//�ر����ݿ�����
			DBTool.close(con);
		}
	}
	/**
	 * ��ʼ������г����е��鼮
	 * @param book
	 */
	private void fillBookTable(Book book){
		//��ȡ����ģ��
		DefaultTableModel dtm=(DefaultTableModel) bookTable.getModel();
		//�����ʱ���ó�0�У��൱�ڹ��㴦��
		dtm.setRowCount(0);
		//�������ݿ�����
		Connection con=null;
		try {
			//��ȡ���ݿ�����
			con=DBTool.getConnetion();
			//��ʼ��ͼ�����ݷ��ʶ���
			bookDao=new BookDao();
			//��������ѯͼ�飨����û����������������鼮��
			ResultSet rs=bookDao.search(con, book);
			//������ѯ���
			while(rs.next()){
				//����һ�����ϣ����ڴ洢ͼ����Ϣ
				Vector v=new Vector();
				v.add(rs.getInt("id"));  //��ӱ��
				v.add(rs.getString("bookName"));  //���ͼ������
				v.add(rs.getString("author"));  //���ͼ������
				v.add(rs.getString("sex"));     //��������Ա�
				v.add(rs.getFloat("price"));    //���ͼ��۸�
				v.add(rs.getString("bookTypeName"));  //���ͼ�����
				v.add(rs.getString("bookDesc"));
				//��ӱ������
				dtm.addRow(v);
			}
		} catch (SQLException e) {
			//��¼��־
			e.printStackTrace();
			throw new RuntimeException("��ʼ�����ʧ��",e);
		}finally{
			//�ر����ݿ�����
			DBTool.close(con);
		}
	}
}
