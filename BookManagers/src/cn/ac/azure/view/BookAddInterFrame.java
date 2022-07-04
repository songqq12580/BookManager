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

		//改变系统默认字体
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
		//图书类别下拉框
		bookTypeComboBox = new JComboBox();
		
		JLabel label_5 = new JLabel("\u56FE\u4E66\u63CF\u8FF0\uFF1A");
		
		bookDescText = new JTextArea();
		
		//图书添加按钮
		JButton addBtn = new JButton("\u6DFB\u52A0");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//图书添加按钮事件处理
				bookAddActionPerformed(e);
			}
		});
		addBtn.setIcon(new ImageIcon(BookAddInterFrame.class.getResource("/images/add.png")));
		
		//图书重置按钮
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
		//设置文本域边框
		bookDescText.setBorder(new LineBorder(new java.awt.Color(127,157,185), 1, false));
		//在构造函数中调用图书类别下拉框初始化方法
		fillBookTypeName();
		//在构造函数中初始化性别。默认为男
		maleBtn.setSelected(true);
	}
	/**
	 * 重置按钮事件处理
	 * @param evt 重置按钮事件对象
	 */
	private void bookResetActionPerformed(ActionEvent evt) {
		reset();
	}
	/**
	 * 图书添加界面信息重置
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
	 *  图书添加按钮事件处理
	 * @param evt 添加事件对象
	 */
	private void bookAddActionPerformed(ActionEvent evt) {
		
		String bookName=bookNameText.getText();  //获取图书名称
		if(bookName==null || "".equals(bookName.trim())){
			JOptionPane.showMessageDialog(null, "图书名称不能为空！");
			return;
		}
		String author=authorText.getText();  //获取图书作者
		
		String sex=null;  //获取图书作者性别
		if(maleBtn.isSelected()){
			sex="男";
		}else{
			sex="女";
		}
		
		String prices=priceText.getText(); //获取图书价格
		if(prices==null || "".equals(prices.trim())){
			JOptionPane.showMessageDialog(null, "图书价格不能为空！");
			return;
		}
		float price=Float.parseFloat(prices);
		
		BookType bookType=(BookType)bookTypeComboBox.getSelectedItem(); //获取图书类别
		int bookTypeId=bookType.getId(); //获取图书类别id
		
		String bookDesc=bookDescText.getText(); //获取图书描述
		
		//根据获取的添加图书界面获取的信息创建图书对象
		Book book=new Book(bookName, author, sex, price, bookTypeId, bookDesc);

		//定义数据库连接
		Connection con=null;
		try {
			//获取数据库连接
			con=DBTool.getConnetion();
			//初始化图书数据访问对象
			bookDao=new BookDao();
			//调用添加方法，向数据库添加书籍
			int num=bookDao.add(con, book);
			//根据返回值判断图书是否添加成功
			if(num>0){
				JOptionPane.showMessageDialog(null, "图书添加成功n_n");
				//添加成功之后重置界面
				reset();
			}else{
				JOptionPane.showMessageDialog(null, "图书添加成功u_u");
			}
		} catch (SQLException e) {
			//记录日志
			e.printStackTrace();
			throw new RuntimeException("添加图书失败",e);
		}finally{
			//关闭数据库连接
			DBTool.close(con);
		}
	}

	//填充图书类别名称
	private void fillBookTypeName(){
		//定义数据库连接对象
		Connection con=null;
		//定义图书类别,用于查询和储存查询的书籍
		BookType bookType=null;
		try {
			//获取数据库连接
			con=DBTool.getConnetion();
			//初始化图书类别访问对象
			bookTypeDao=new BookTypeDao();
			//查询t_bookType中含有的图书类别
			ResultSet rs=bookTypeDao.search(con, bookType);
			//遍历查询结果
			while(rs.next()){
				//出事化图书类别
				bookType=new BookType();
				//设置图书的id
				bookType.setId(rs.getInt("id"));
				//设置图书的名称
				bookType.setBookTypeName(rs.getString("bookTypeName"));
				//将图书类别对象添加到下拉框中（这里添加对象，便于获得id）
				bookTypeComboBox.addItem(bookType);
			}
		} catch (SQLException e) {
			//记录日志
			e.printStackTrace();
			throw new RuntimeException("初始化列表失败",e);
		}finally{
			//关闭数据路连接
			DBTool.close(con);
		}
	}
}
