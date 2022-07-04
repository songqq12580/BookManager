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
 * 图书类别内部添加窗体
 * @author green
 *
 */
public class BookTypeAddInterFrame extends JInternalFrame {
	//图书类别名称输入框
	private JTextField bookTypeNameText;
	//图书类别描述输入框
	private JTextArea bookTypeDescText;
	//重置按钮
	private JButton resetBtn;
	//添加按钮
	private JButton addBtn;
	//图书类别数据库访问对象
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
		setClosable(true);
		setIconifiable(true);
		setTitle("\u56FE\u4E66\u7C7B\u522B\u6DFB\u52A0");
		setBounds(100, 100, 487, 342);
		
		JLabel label = new JLabel("\u56FE\u4E66\u7C7B\u522B\u540D\u79F0\uFF1A");
		
		bookTypeNameText = new JTextField();
		bookTypeNameText.setColumns(10);
		
		JLabel label_1 = new JLabel("\u56FE\u4E66\u7C7B\u522B\u63CF\u8FF0\uFF1A");
		
		bookTypeDescText = new JTextArea();
		
		//添加按钮
		addBtn = new JButton("\u6DFB\u52A0");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addActionPerformed(e);
			}
		});
		//重置按钮
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
		
		//设置文本域边框
		bookTypeDescText.setBorder(new LineBorder(new java.awt.Color(127,157,185), 1, false));
	}
	/**
	 * 添加事件处理
	 * @param evt
	 */
	private void addActionPerformed(ActionEvent evt) {
		//获取输入框的值
		String bookTypeName=bookTypeNameText.getText();
		String bookTypeDesc=bookTypeDescText.getText();
		if(bookTypeName==null || "".equals(bookTypeName.trim())){
			JOptionPane.showMessageDialog(null,"图书类别不能为空！");
			return;
		}
		//新建图书类别实体对象
		BookType bookType=new BookType(bookTypeName, bookTypeDesc);
		//定义数据库连接
		Connection con=null;
		try {
			//获取数据库连接
			con=DBTool.getConnetion();
			//初始化图书类别对象BookTypeDao
			bookTypeDao=new BookTypeDao();
			//调用图书类别dao对象的添加方法
			int res=bookTypeDao.add(con, bookType);
			if(res!=0){
				//提示添加成功
				JOptionPane.showMessageDialog(null, "图书添加成功(n_n)");
				//清空输入框
				bookTypeNameText.setText("");
				bookTypeDescText.setText("");
			}else{
				//提示添加失败
				JOptionPane.showMessageDialog(null,"图书添加失败(u_u)");
			}
		} catch (SQLException e) {
			//记录日志
			e.printStackTrace();
			throw new RuntimeException("添加图书失败",e);
		}finally{
			//关闭数据库
			DBTool.close(con);
		}
	}

	/**
	 * 重置事件处理
	 * @param evt
	 */
	private void resetActionPerformed(ActionEvent evt) {
		//置空图书类别名称输入框
		bookTypeNameText.setText("");
		//置空图书类别描述输入框
		bookTypeDescText.setText("");
	}
}
