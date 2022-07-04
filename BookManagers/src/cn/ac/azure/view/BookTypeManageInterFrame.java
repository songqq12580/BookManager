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
		setTitle("\u56FE\u4E66\u7C7B\u522B\u7BA1\u7406");
		setBounds(400, 100, 535, 489);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel label = new JLabel("\u56FE\u4E66\u7C7B\u522B\u540D\u79F0\uFF1A");
		
		s_bookTypeNameText = new JTextField();
		s_bookTypeNameText.setColumns(10);
		
		//查询按钮
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
		
		//修改按钮
		JButton modifyBtn = new JButton("\u4FEE\u6539");
		modifyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookTypeUpdateActionPerformed(e);
			}
		});
		modifyBtn.setIcon(new ImageIcon(BookTypeManageInterFrame.class.getResource("/images/modify.png")));
		
		//删除按钮
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
		
		//表格
		bookTypeTable = new JTable();
		//表格鼠标点击事件
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
		
		//设置文本域边框
		bookTypeDescText.setBorder(new LineBorder(new java.awt.Color(127,157,185), 1, false));
		//构造函数中调用填充表格数据函数，全部图书类别显示在表格中
		fillTable(new BookType());
	}
	/**
	 * 图书类别删除事件处理
	 * @param evt
	 */
	private void bookTypeDeleteActionPerformed(ActionEvent evt) {
		//获得表单中编号的值id
		String id=idText.getText();
		//判断表单有没有选中的图书类别记录
		if(id==null || "".equals(id.trim())){
			JOptionPane.showMessageDialog(null, "请选择要修改的记录！");
			return;
		}
		//弹出确认框，是否要删除图书类别记录
		int res=JOptionPane.showConfirmDialog(null, "你确定要删除该条记录吗？");
		if(res!=0){ //否
			return; //结束该事件处理函数
		}
		//定义数据库连接
		Connection con=null;
		try {
			//获取数据路连接
			con=DBTool.getConnetion();
			int row=bookTypeDao.delete(con, id);
			if(row==1){//删除成功，弹出提示框
				JOptionPane.showMessageDialog(null, "修改数据成功(n_n)");
				//清空表单数据
				resetValue();
				//刷新表格记录显示
				fillTable(new BookType());
			}else{//删除失败，弹出提示框
				JOptionPane.showMessageDialog(null, "修改数据失败(u_u)");
			}
		} catch (SQLException e) {
			//记录日志
			e.printStackTrace();
			throw new RuntimeException("删除记录失败！",e);
		}finally{
			//关闭数据库
			DBTool.close(con);
		}
	}

	/**
	 * 图书类别修改事件处理
	 * @param evt
	 */
	private void bookTypeUpdateActionPerformed(ActionEvent evt) {
		//获取表单操作各个文本框的值
		String id=idText.getText();
		String bookTypeName=bookTypeNameText.getText();
		String bookTypeDesc=bookTypeDescText.getText();
		//判断表单有没有选中的图书类别记录
		if(id==null || "".equals(id.trim())){
			JOptionPane.showMessageDialog(null, "请选择要修改的记录！");
			return;
		}
		//图书类别名称不能为空
		if(bookTypeName==null || "".equals(bookTypeName.trim())){
			JOptionPane.showMessageDialog(null, "图书类别名称不能为空！");
			return;
		}
		//利用表单的数据新建一个图书类别对象
		BookType bookType=new BookType(Integer.parseInt(id), bookTypeName, bookTypeDesc);
		//定义数据库连接对象
		Connection con=null;
		try {
			//获取数据库连接
			con=DBTool.getConnetion();
			//执行图书类别dao类的修改记录方法
			int res=bookTypeDao.update(con, bookType);
			if(res==1){//修改成功，弹出提示框
				JOptionPane.showMessageDialog(null, "修改数据成功(n_n)");
				//清空表单数据
				resetValue();
				//刷新表格记录显示
				fillTable(new BookType());
			}else{//修改失败，弹出提示框
				JOptionPane.showMessageDialog(null, "修改数据失败(u_u)");
			}
		} catch (SQLException e) {
			//记录日志
			e.printStackTrace();
			throw new RuntimeException("修改图书类别失败",e);
		}finally{
			//关闭数据路连接
			DBTool.close(con);
		}
	}

	/**
	 * 表格鼠标点击事件处理
	 * @param e 
	 */
	private void bookTypeTableMousePressed(MouseEvent e) {
		//获取表格选中的行
		int row=bookTypeTable.getSelectedRow();
		//获取表中选中行的第一列的值并显示在idText框中
		idText.setText(String.valueOf(bookTypeTable.getValueAt(row, 0)));
		//获取表中选中行的第二列的值并显示在bookTypeNameText框中
		bookTypeNameText.setText((String)bookTypeTable.getValueAt(row, 1));
		//获取表中选中行的第三列的值并显示在bookTypeDescText框中
		bookTypeDescText.setText((String)bookTypeTable.getValueAt(row, 2));
	}

	/**
	 * 图书类别查询事件处理
	 * @param evt
	 */
	private void searchActionPerformed(ActionEvent evt) {
		//获取图书类别输入框里的内容
		String s_bookTypeName=s_bookTypeNameText.getText();
		//新建一个图书类别并初始化
		BookType bookType=new BookType();
		//将输入框的内容设置成新建图书类别的图书类别名称
		bookType.setBookTypeName(s_bookTypeName);
		//根据图书类别查询图书类别
		fillTable(bookType);
	}

	/**
	 * 在表格中填充数据
	 * @param bookType 传入bookType对象
	 */
	private void fillTable(BookType bookType){
		//获取表格的模型
		DefaultTableModel dtm=(DefaultTableModel) bookTypeTable.getModel();
		//清空表格
		dtm.setRowCount(0); 
		//定义数据库连接
		Connection con=null;
		try {
			//获取数据库连接
			con=DBTool.getConnetion();
			//调用BookTyPeDao的查询方法，并获得其查询的结果集
			ResultSet rs=bookTypeDao.search(con, bookType);
			//遍历结果集
			while(rs.next()){
				//新建一个vector并初始化
				Vector v=new Vector(); 
				v.add(rs.getInt("id"));  //向vector中添加id
				v.add(rs.getString("bookTypeName")); //向vector中添加bookTypeName
				v.add(rs.getString("bookTypeDesc"));  //向vector中添加bookTypeDesc
				//将vector中的数据显示到表格中
				dtm.addRow(v);
			}
		} catch (SQLException e) {
			//记录日志
			e.printStackTrace();
			throw new RuntimeException("查询失败");
		}finally{
			//关闭数据库
			DBTool.close(con);
		}
	}
	/**
	 * 清空表单数据
	 */
	private void resetValue(){
		idText.setText("");
		bookTypeNameText.setText("");
		bookTypeDescText.setText("");
		s_bookTypeNameText.setText("");
	}
}
