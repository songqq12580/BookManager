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
		
		//修改按钮
		JButton modifyBtn = new JButton("\u4FEE\u6539");
		modifyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifyBookActionPerformed(e);
			}
		});
		modifyBtn.setIcon(new ImageIcon(BookManageInterFrame.class.getResource("/images/modify.png")));
		
		//删除按钮
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
		
		//表格
		bookTable = new JTable();
		//表格鼠标按下事件
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
		
		//图书查询按钮
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
		//初始化搜索栏图书类别下拉框
		fillBookTypeComboBox("search");
		//初始化操作栏图书类别下拉框
		fillBookTypeComboBox("modify");
		//初始化表格显示，显示所有的书籍
		fillBookTable(new Book());
	}
	
	/**
	 * 图书修改事件
	 * @param evt
	 */
	private void modifyBookActionPerformed(ActionEvent evt) {
		//获取图书id
		String id=idText.getText();
		//获取图书名称
		String bookName=bookNameText.getText();
		//获取图书作者
		String author=authorText.getText();
		//或者作者性别
		String sex="男";
		if(femaleBtn.isSelected()){
			sex="女";
		}
		//获取图书价格
		String price=priceText.getText();
		//获取图书id
		BookType bookType=(BookType)bookTypeComboBox.getSelectedItem();
		Integer bookTypeId=bookType.getId();
		//获取图书描述
		String bookDesc=bookDescText.getText();
		
		//判断是否id是否为空
		if(id==null || "".equals(id)){ //为空
			JOptionPane.showMessageDialog(null, "请选中要删除的行！");  //给用户提示
			return;
		}
		//判断图书名称是否为空
		if(bookName==null || "".equals(bookName)){ //为空
			JOptionPane.showMessageDialog(null, "图书名称不能为空！");  //给用户提示
			return;
		}
		//判断图书作者是否为空
		if(author==null || "".equals(author)){ //为空
			JOptionPane.showMessageDialog(null, "图书作者不能为空！");  //给用户提示
			return;
		}
		//判断图书价格是否为空
		if(price==null || "".equals(price)){ //为空
			JOptionPane.showMessageDialog(null, "图书价格不能为空！");  //给用户提示
			return;
		}
		//从获取的图书信息创建图书对象
		Book book=new Book(Integer.parseInt(id),bookName, author, sex, Float.parseFloat(price), bookTypeId, bookDesc);
		//定义数据库连接
		Connection con=null;
		try {
			//获取数据库连接
			con=DBTool.getConnetion();
			//初始化图书数据访问对象
			bookDao=new BookDao();
			//执行图书访问对象的修改方法，并获得修改的记录数
			int res=bookDao.update(con, book);
			if(res==1){ //为1
				JOptionPane.showMessageDialog(null,"图书修改成功n_n");
				//刷新图书表格显示
				fillBookTable(new Book());
				//重置操作栏
				resetValue();
			}else{ //为0
				JOptionPane.showMessageDialog(null,"图书修改失败u_u");
			}
		} catch (SQLException e) {
			//记录日志
			e.printStackTrace();
			throw new RuntimeException("修改图书失败",e);
		}finally{
			//关闭数据库连接
			DBTool.close(con);
		}
	}
	/**
	 * 图书删除事件
	 * @param evt
	 */
	private void deleteBookActionPerformed(ActionEvent evt) {
		//获取图书id
		String id=idText.getText();
		//判断是否id是否为空
		if(id==null || "".equals(id)){ //为空
			JOptionPane.showMessageDialog(null, "请选中要删除的行！");  //给用户提示
			return;
		}
		//定义数据库连接对象
		Connection con=null;
		try {
			//初始化数据库连接对象
			con=DBTool.getConnetion(); 
			//初始化图书数据访问对象
			bookDao=new BookDao();
			//执行图书访问对象的删除方法并返回删除的记录数
			int res=bookDao.delete(con, Integer.parseInt(id));
			if(res==1){ //为1
				JOptionPane.showMessageDialog(null, "图书删除成功n_n");
				//刷新图书表格显示
				fillBookTable(new Book());
				//重置操作栏
				resetValue();
			}else{ //为其他
				JOptionPane.showMessageDialog(null, "图书删除失败u_u");
			}
		} catch (SQLException e) {
			//记录日志
			e.printStackTrace();
			throw new RuntimeException("删除图书失败",e);
		}finally{
			//记得关闭数据库（******）
			DBTool.close(con);
		}
	}
	/**
	 * 重置操作栏的所有值
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
	 * 表格鼠标按下事件处理
	 * @param evt
	 */
	private void tableMousePressed(MouseEvent evt) {
		//获取图书表格选中的行的行号
		int row=bookTable.getSelectedRow();
		//获取选中行第一个数据并设置显示在操作栏的id框
		idText.setText((Integer)bookTable.getValueAt(row,0)+"");
		//获取选中行第二个数据并设置显示在操作栏的图书名称框
		bookNameText.setText((String)bookTable.getValueAt(row, 1));
		//获取选中行第三个数据并设置显示在操作栏的图书作者框
		authorText.setText((String)bookTable.getValueAt(row, 2));
		//获取选中行第四个数据并设置显示在操作栏的作者性别单选框
		String sex=(String)bookTable.getValueAt(row, 3);
		if("男".equals(sex)){
			maleBtn.setSelected(true);
		}else{
			femaleBtn.setSelected(true);
		}
		//获取选中行第五个数据并设置显示在操作栏的图书价格框
		priceText.setText((Float)bookTable.getValueAt(row, 4)+"");
		//获取选中行第六个数据并设置显示在操作栏的图书类别下拉框中
		String bookTypeName=(String)bookTable.getValueAt(row, 5);
		int rows=bookTypeComboBox.getItemCount();  //获取下拉框总共的选项
		for(int i=0;i<rows;i++){ //遍历下拉框
			BookType item=(BookType) bookTypeComboBox.getItemAt(i); //获取每一个选项并强转图书类别对象
			if(item.getBookTypeName().equals(bookTypeName)){  //将获取的图书类别和下拉框中的图书类别比较，若相同
				bookTypeComboBox.setSelectedIndex(i); //则该下拉框选项被选中
			}
		}
		//获取选中行第七个数据并设置显示在操作栏的图书描述框
		bookDescText.setText((String)bookTable.getValueAt(row, 6));
	}

	/**
	 * 图书查询事件处理
	 * @param evt 事件对象
	 */
	private void searchActionPerformed(ActionEvent evt) {
		//获取查询的条件
		String bookName=s_bookNameText.getText();  //图书名称
		String author=s_authorText.getText();  //图书作者
		String bookTypeName=s_bookTypecomboBox.getSelectedItem().toString(); //图书类别
		//对图书类别"请选择..."换成""
		if("请选择...".equals(bookTypeName)){
			bookTypeName="";
		}
		//生成带有条件的图书对象
		Book book=new Book();
		book.setBookName(bookName);  //设置图书名称条件
		book.setAuthor(author);   //设置图书作者条件
		book.setBookTypeName(bookTypeName);  //设置图书类别条件
		//调用table填充函数，根据查询结果重新填充表格
		fillBookTable(book);  
	}

	/**
	 *  初始化图书类别下拉框
	 * @param type 根据不同的参数填充不同的下拉框
	 */
	
	private void fillBookTypeComboBox(String type){
		
		//定义一个图书类别，用于存储查询的图书类别
		BookType s_bookType=null;
		//定义一个数据库连接
		Connection con=null;
		try {
			//获取数据库连接
			con=DBTool.getConnetion();
			//初始化图书类别访问数据对象
			bookTypeDao=new BookTypeDao();
			//查询图书类别，得到结果集
			ResultSet rs=bookTypeDao.search(con, new BookType());
			if("search".equals(type)){
				BookType bookType=new BookType();
				bookType.setBookTypeName("请选择...");
				bookType.setId(-1);
				s_bookTypecomboBox.addItem(bookType);
			}
			//遍历结果集
			while(rs.next()){ //如果有数据的话
				//初始化接受查询的图书类别
				s_bookType=new BookType();
				//根据查询结果设置id
				s_bookType.setId(rs.getInt("id"));
				//根据查询结果设置图书类别名称
				s_bookType.setBookTypeName(rs.getString("bookTypeName"));
				if("search".equals(type)){
					//将查询的图书类别添加到下拉框中
					s_bookTypecomboBox.addItem(s_bookType);
				}
				if("modify".equals(type)){
					//将查询的图书类别添加到表单操作下拉框中
					bookTypeComboBox.addItem(s_bookType);
				}
			}
		} catch (SQLException e) {
			//记录日志
			e.printStackTrace();
			throw new RuntimeException("初始化下拉框失败",e);
		}finally{
			//关闭数据库连接
			DBTool.close(con);
		}
	}
	/**
	 * 初始化表格，列出所有的书籍
	 * @param book
	 */
	private void fillBookTable(Book book){
		//获取表格的模型
		DefaultTableModel dtm=(DefaultTableModel) bookTable.getModel();
		//填充表格时设置成0行（相当于归零处理）
		dtm.setRowCount(0);
		//定义数据库连接
		Connection con=null;
		try {
			//获取数据库连接
			con=DBTool.getConnetion();
			//初始化图书数据访问对象
			bookDao=new BookDao();
			//按条件查询图书（这里没有条件，查出所有书籍）
			ResultSet rs=bookDao.search(con, book);
			//遍历查询结果
			while(rs.next()){
				//定义一个集合，由于存储图书信息
				Vector v=new Vector();
				v.add(rs.getInt("id"));  //添加编号
				v.add(rs.getString("bookName"));  //添加图书名称
				v.add(rs.getString("author"));  //添加图书作者
				v.add(rs.getString("sex"));     //添加作者性别
				v.add(rs.getFloat("price"));    //添加图书价格
				v.add(rs.getString("bookTypeName"));  //添加图书类别
				v.add(rs.getString("bookDesc"));
				//添加表格新行
				dtm.addRow(v);
			}
		} catch (SQLException e) {
			//记录日志
			e.printStackTrace();
			throw new RuntimeException("初始化表格失败",e);
		}finally{
			//关闭数据库连接
			DBTool.close(con);
		}
	}
}
